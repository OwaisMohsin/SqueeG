package com.example.co.squeeg.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.TextView;

import org.apache.http.HttpException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.co.squeeg.Model.GETAllGigs;
import com.example.co.squeeg.R;
import com.example.co.squeeg.adapter.AllGigsListsAdapter;
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


public class BuyFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private GridView gridGigsWidgets;
    private View inflateGigsLayouts;
    private CustomProgressDialog mCustomProgressDialog;
    private String token = "";
    private HashMap<String, String> postDetails = new HashMap<String, String>();
    private AllGigsListsAdapter aAllGigsListsAdapter;
    private TextView inputNoGigs;
    private int total_page, page_num = 1;
    private List<GETAllGigs.Category_detail> mData = new ArrayList<GETAllGigs.Category_detail>();
    boolean isLoading = false;

    public BuyFragment() {

    }

    public static BuyFragment newInstance(String param1, String param2) {
        BuyFragment fragment = new BuyFragment();
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

        inflateGigsLayouts = inflater.inflate(R.layout.fragment_buy, container, false);
        mCustomProgressDialog = new CustomProgressDialog(getActivity());
        gridGigsWidgets = (GridView) inflateGigsLayouts.findViewById(R.id.gv_gigs_list);
        inputNoGigs = (TextView) inflateGigsLayouts.findViewById(R.id.tv_noGigs);
        getGigs(isLoading);


        gridGigsWidgets.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                int threshold = 1;
                int count = gridGigsWidgets.getCount();
                if (gridGigsWidgets.getLastVisiblePosition() >= count - threshold && page_num <= total_page) {
                    // Execute LoadMoreDataTask AsyncTask
                    /*page_num++;*/
                    getGigs(isLoading);
                    //isLoading = true;
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });


        return inflateGigsLayouts;
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

    private void getGigs(final boolean Loading) {
        if (NetworkChangeReceiver.isConnected() && !Loading) {
            isLoading = true;
            if (!mCustomProgressDialog.isShowing())
                mCustomProgressDialog.showDialog();
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            if (SessionHandler.getInstance().get(getActivity(), AppConstants.TOKEN_ID) != null) {
                token = SessionHandler.getInstance().get(getActivity(), AppConstants.TOKEN_ID);
            } else {
                token = AppConstants.GUEST_TOKEN;
            }
            /*if (!token.isEmpty()) {
                postDetails.put("services", "ALL");
                postDetails.put("device_type", AppConstants.DeviceType);
                postDetails.put("page", String.valueOf(page_num));
            } else {
                postDetails.put("services", "ALL");
                postDetails.put("device_type", AppConstants.DeviceType);
                postDetails.put("page", String.valueOf(page_num));
            }*/
            postDetails.put("services", "ALL");
            postDetails.put("device_type", AppConstants.DeviceType);
            postDetails.put("page", String.valueOf(page_num));
            apiInterface.getUserGigs(postDetails, token, SessionHandler.getInstance().get(getActivity(), AppConstants.Language)).enqueue(new Callback<GETAllGigs>() {
                @Override
                public void onResponse(Call<GETAllGigs> call, Response<GETAllGigs> response) {
                    /*mCustomProgressDialog.dismiss();*/
                    mCustomProgressDialog.stop();
                    if (response.isSuccessful()) {
                        if (response.body().getCode().equals(200)) {
                            isLoading = false;
                            total_page = response.body().getData().getTotal_pages();
                            page_num++;
                            if (response.body().getData().getCategory_details().size() > 0) {

                                if (mData.size() > 0) {
                                    mData.addAll(response.body().getData().getCategory_details());
                                    aAllGigsListsAdapter.notifyDataSetChanged();
                                } else {
                                    mData.addAll(response.body().getData().getCategory_details());
                                    aAllGigsListsAdapter = new AllGigsListsAdapter(getActivity(), mData);
                                    gridGigsWidgets.setAdapter(aAllGigsListsAdapter);
                                }
                            } else {
                                if (mData.size() == 0) {
                                    inputNoGigs.setVisibility(View.VISIBLE);
                                    gridGigsWidgets.setVisibility(View.GONE);
                                }
                            }
                        } else if (response.body().getCode().equals(AppConstants.InactiveStatusResponse)) {
                            Utils.createUserInActiceAlert(getActivity(), response.body().getMessage());
                        } else if (response.body().getCode().equals(Integer.parseInt(AppConstants.INVALID_RESPONSE_CODE))) {
                            mCustomProgressDialog.dismiss();
                            NetworkAlertDialog.networkAlertDialog(getActivity(), "", response.body().getMessage(), null, null);
                        }else {
                            inputNoGigs.setVisibility(View.VISIBLE);
                            gridGigsWidgets.setVisibility(View.GONE);
                            mCustomProgressDialog.dismiss();
                            NetworkAlertDialog.networkAlertDialog(getActivity(), "",
                                    response.body().getMessage(), null, null);
                        }
                    } else {
                        Log.i("ERROR_ALL_GIGS", response.errorBody().toString());
                    }
                }

                @Override
                public void onFailure(Call<GETAllGigs> call, Throwable t) {
                    Log.i("TAG", t.getMessage());
                    mCustomProgressDialog.dismiss();
                    if (t instanceof UnknownHostException || t instanceof HttpException || t instanceof ConnectException || t instanceof SocketTimeoutException || t instanceof SocketException || t instanceof IOException) {
                        NetworkAlertDialog.networkAlertDialog(getActivity(), getResources().getString(R.string.err_network_error),
                                getResources().getString(R.string.err_try_again), buygigsRunn, null);
                    }
                }
            });

        } else {
            Utils.toastMessage(getActivity(), getString(R.string.err_internet_connection));
        }
    }


    Runnable buygigsRunn = new Runnable() {
        @Override
        public void run() {
            //TODO: API call
            getGigs(isLoading);
        }
    };
}
