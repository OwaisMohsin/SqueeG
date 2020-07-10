package com.example.co.squeeg.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.apache.http.HttpException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.example.co.squeeg.Model.POSTMessages;
import com.example.co.squeeg.R;
import com.example.co.squeeg.adapter.MessageChatAdapter;
import com.example.co.squeeg.network.ApiClient;
import com.example.co.squeeg.network.ApiInterface;
import com.example.co.squeeg.receiver.NetworkChangeReceiver;
import com.example.co.squeeg.utils.AppConstants;
import com.example.co.squeeg.utils.CustomProgressDialog;
import com.example.co.squeeg.utils.NetworkAlertDialog;
import com.example.co.squeeg.utils.SessionHandler;
import com.example.co.squeeg.utils.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChatFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View mChatLayout;
    private TextView txtNoChat;


    private OnFragmentInteractionListener mListener;

    RecyclerView recyclerChatList;
    MessageChatAdapter aMessageChatAdapter;
    CustomProgressDialog mCustomProgressDialog;
    List<POSTMessages.Chat_detail> mData = new ArrayList<POSTMessages.Chat_detail>();
    private int total_page, pagenum = 1;
    LinearLayoutManager horizontalLayoutManagaer;
    int visibleItemCount, totalItemCount, pastVisiblesItems;
    boolean isLoading = false;


    public ChatFragment() {

    }

    public static ChatFragment newInstance(String param1, String param2) {
        ChatFragment fragment = new ChatFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.freeMemory();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mChatLayout = inflater.inflate(R.layout.fragment_chat, container, false);

        recyclerChatList = (RecyclerView) mChatLayout.findViewById(R.id.rv_message_chat_list);
        txtNoChat = (TextView) mChatLayout.findViewById(R.id.tv_no_chat_available);
        mCustomProgressDialog = new CustomProgressDialog(getActivity());

        getMessageList(isLoading);

        recyclerChatList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    visibleItemCount = horizontalLayoutManagaer.getChildCount();
                    totalItemCount = horizontalLayoutManagaer.getItemCount();
                    pastVisiblesItems = horizontalLayoutManagaer.findFirstVisibleItemPosition();
                    if (!isLoading && (visibleItemCount + pastVisiblesItems) >= totalItemCount && pagenum < total_page) {

                        Log.v("...", " Reached Last Item");
                        getMessageList(isLoading);
                    }
                }
            }
        });
        return mChatLayout;
    }


    public void getMessageList(boolean loading) {
        if (NetworkChangeReceiver.isConnected() && !loading) {
            isLoading = true;
            if (!mCustomProgressDialog.isShowing())
                mCustomProgressDialog.showDialog();
            if (SessionHandler.getInstance().get(getActivity(), AppConstants.TOKEN_ID) != null) {
                ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                apiInterface.postmessages(String.valueOf(pagenum), SessionHandler.getInstance().get(getActivity(), AppConstants.TOKEN_ID), SessionHandler.getInstance().get(getActivity(), AppConstants.Language)).enqueue(new Callback<POSTMessages>() {
                    @Override
                    public void onResponse(Call<POSTMessages> call, Response<POSTMessages> response) {
                        if (response.body() != null)
                        if (response.body().getCode().equals(200)) {
                            mCustomProgressDialog.dismiss();
                            isLoading = false;
                            total_page = response.body().getData().getTotal_pages();
                            pagenum++;
                            if (response.body().getData().getChat_details().size() > 0) {
                                for (int i = 0; i < response.body().getData().getChat_details().size(); i++) {
                                    mData.add(response.body().getData().getChat_details().get(i));
                                }
                                aMessageChatAdapter = new MessageChatAdapter(getActivity(), mData);
                                horizontalLayoutManagaer = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                                recyclerChatList.setHasFixedSize(true);
                                recyclerChatList.setLayoutManager(horizontalLayoutManagaer);
                                recyclerChatList.setAdapter(aMessageChatAdapter);
                            } else {
                                if (mData.size() == 0) {
                                    recyclerChatList.setVisibility(View.GONE);
                                    txtNoChat.setVisibility(View.VISIBLE);
                                    mCustomProgressDialog.dismiss();
                                } else {
                                    mCustomProgressDialog.dismiss();
                                }
                            }
                        } else if (response.body().getCode().equals(Integer.parseInt(AppConstants.INVALID_RESPONSE_CODE))) {
                            mCustomProgressDialog.dismiss();
                            NetworkAlertDialog.networkAlertDialog(getActivity(), "", response.body().getMessage(), null, null);
                        } else if (response.body().getCode().equals(AppConstants.InactiveStatusResponse)) {
                            Utils.createUserInActiceAlert(getActivity(), response.body().getMessage());
                        } else {
                            Utils.toastMessage(getActivity(), getString(R.string.no_data_found));
                            mCustomProgressDialog.dismiss();
                            NetworkAlertDialog.networkAlertDialog(getActivity(), "",
                                    response.body().getMessage(), null, null);
                        }


                    }

                    @Override
                    public void onFailure(Call<POSTMessages> call, Throwable t) {
                        Log.i("TAG", t.getMessage());
                        if (t instanceof UnknownHostException || t instanceof HttpException || t instanceof ConnectException || t instanceof SocketTimeoutException || t instanceof SocketException || t instanceof IOException) {
                            //Toast.makeText(getActivity(), getString(R.string.err_server_msg), Toast.LENGTH_SHORT).show();
                            NetworkAlertDialog.networkAlertDialog(getActivity(), getResources().getString(R.string.err_network_error),
                                    getResources().getString(R.string.err_try_again), buygigsRunn, null);
                        }
                        mCustomProgressDialog.dismiss();
                    }
                });
            }
        } else {
            Utils.toastMessage(getActivity(), getString(R.string.err_internet_connection));
        }
    }

    Runnable buygigsRunn = new Runnable() {
        @Override
        public void run() {
            //TODO: API call
            getMessageList(isLoading);
        }
    };


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
