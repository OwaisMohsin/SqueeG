package com.example.co.squeeg.fragment;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.co.squeeg.Model.POSTLanguageModel;
import com.example.co.squeeg.Model.buyer_requests_for_seller.BuyerRequestForSeller;
import com.example.co.squeeg.Model.buyer_requests_for_seller.POSTBuyerRequestForSellerList;
import com.example.co.squeeg.R;
import com.example.co.squeeg.adapter.BuyerRequestsToSellerAdapter;
import com.example.co.squeeg.network.ApiClient;
import com.example.co.squeeg.network.ApiInterface;
import com.example.co.squeeg.receiver.NetworkChangeReceiver;
import com.example.co.squeeg.utils.AppConstants;
import com.example.co.squeeg.utils.CustomProgressDialog;
import com.example.co.squeeg.utils.NetworkAlertDialog;
import com.example.co.squeeg.utils.SessionHandler;
import com.example.co.squeeg.utils.Utils;
import com.google.gson.Gson;

import org.apache.http.HttpException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuyerRequestsToSellerFragment extends Fragment {

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private View inflateGigsLayouts;
    private Context mContext;
    Handler mHandler;

    private TextView txtNoInformation;
    private CustomProgressDialog mCustomProgressDialog;
    private RecyclerView rvBuyerRequests;
    private BuyerRequestsToSellerAdapter buyerRequestsToSellerAdapter;

    public POSTLanguageModel.Common_strings commonStrings = new POSTLanguageModel().new Common_strings();

    private RequestBody user_id;

    private List<BuyerRequestForSeller> buyerRequestForSellerList = new ArrayList<>();

    public static BuyerRequestFragment newInstance(String param1, String param2) {
        BuyerRequestFragment fragment = new BuyerRequestFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        Utils.freeMemory();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        inflateGigsLayouts = inflater.inflate(R.layout.buyer_requests_to_seller_fragment, container, false);
        mContext = getActivity();
        mCustomProgressDialog = new CustomProgressDialog(mContext);

        Utils.freeMemory();
        initLayouts();
        mHandler = new Handler();
        setLanguageLocale();

        if (NetworkChangeReceiver.isConnected()) {
            mCustomProgressDialog.showDialog();
            loadBuyerRequests();
        } else {
            Utils.showToast(mContext);
        }
        return inflateGigsLayouts;
    }

    public void setLanguageLocale() {
        commonStrings = new Gson().fromJson(SessionHandler.getInstance().get(getActivity(), AppConstants.COMMONSTRINGS), POSTLanguageModel.Common_strings.class);
//        sell_gigs_screen = new Gson().fromJson(SessionHandler.getInstance().get(getActivity(), AppConstants.SELLGIGS), POSTLanguageModel.Sell_gigs_screen.class);
//        inputLayoutTitleGigs.setHint(sell_gigs_screen.getTxt_fld_title_gigs().getName());
//        inputLayoutDeliverDay.setHint(sell_gigs_screen.getTxt_fld_deliver_gig().getName());
//        inputLayoutExtras.setHint(sell_gigs_screen.getLbl_Ican().getName());
//        inputLayoutExtrasDay.setHint(sell_gigs_screen.getLbl_day().getName());
//        inputLayoutFastExtras.setHint(sell_gigs_screen.getLbl_Ican().getName());
//        inputLayoutFastExtrasDay.setHint(sell_gigs_screen.getLbl_day().getName());
//        inputLayoutGigCost.setHint(sell_gigs_screen.getTxt_fld_gig_cost().getName());
//        inputLayoutGigDesc.setHint(sell_gigs_screen.getTxt_fld_provide_info().getName());
//        inputLayoutGigRequirement.setHint(sell_gigs_screen.getTxt_fld_buyer_needs().getName());
//        tvEarnMoreMoney.setText(sell_gigs_screen.getLbl_earn_money().getName());
//        tvSelectCategory.setText(sell_gigs_screen.getLbl_select_category().getName());
//        tvTitleSuperfastDelivery.setText(sell_gigs_screen.getLbl_super_fast_delivery().getName());
//        tvWorkOption.setText(sell_gigs_screen.getLbl_work_option().getName());
//        addMoreGigs.setText(sell_gigs_screen.getLbl_add_more_items().getName());
//        rbOnsite.setText(sell_gigs_screen.getLbl_onsite().getName());
//        rbRemote.setText(sell_gigs_screen.getLbl_remote().getName());
//        mCheckBox.setText(sell_gigs_screen.getLbl_terms_condition().getName());
//        createGigs.setText(sell_gigs_screen.getBtn_create_gig().getName());
    }

    private void loadBuyerRequests() {
        user_id = RequestBody.create(MediaType.parse("text/plain"),
                SessionHandler.getInstance().get(getActivity(), AppConstants.TOKEN_ID));

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.loadBuyerRequestsForSeller(user_id,
                SessionHandler.getInstance().get(getActivity(), AppConstants.TOKEN_ID),
                SessionHandler.getInstance().get(getActivity(), AppConstants.Language))
                .enqueue(new Callback<POSTBuyerRequestForSellerList>() {
                    @Override
                    public void onResponse(Call<POSTBuyerRequestForSellerList> call, Response<POSTBuyerRequestForSellerList> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getCode().equals(200)) {
                                if (response.body().getData().size() > 0) {
                                    buyerRequestForSellerList.clear();
                                    buyerRequestForSellerList.addAll(response.body().getData());
                                    buyerRequestsToSellerAdapter = new BuyerRequestsToSellerAdapter(getActivity(), buyerRequestForSellerList, response.body().getCurrencyData());
                                    rvBuyerRequests.setAdapter(buyerRequestsToSellerAdapter);
                                    buyerRequestsToSellerAdapter.notifyDataSetChanged();
                                }
                            } else if (response.body().getCode().equals(Integer.parseInt(AppConstants.INVALID_RESPONSE_CODE))) {
                                NetworkAlertDialog.networkAlertDialog(getActivity(), "", response.body().getMessage(), null, null);
                            } else {
                                Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        txtNoInformation.setVisibility(View.GONE);
                        mCustomProgressDialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<POSTBuyerRequestForSellerList> call, Throwable t) {
                        Log.i("TAG", t.getMessage());
                        mCustomProgressDialog.dismiss();
                        txtNoInformation.setText(R.string.no_request_found);
                        txtNoInformation.setVisibility(View.VISIBLE);
                        if (t instanceof UnknownHostException || t instanceof HttpException || t instanceof ConnectException || t instanceof SocketTimeoutException || t instanceof SocketException || t instanceof IOException) {
                            NetworkAlertDialog.networkAlertDialog(getActivity(), commonStrings.getLbl_network_err().getName(),
                                    commonStrings.getLbl_server_err().getName(), creategigsRunn, null);
                        }
                    }
                });
    }

    Runnable creategigsRunn = new Runnable() {
        @Override
        public void run() {
            //TODO: API call
            loadBuyerRequests();
        }
    };

    private void initLayouts() {
        txtNoInformation = (TextView) inflateGigsLayouts.findViewById(R.id.txt_no_info);

        rvBuyerRequests = (RecyclerView) inflateGigsLayouts.findViewById(R.id.rv_buyer_requests);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        rvBuyerRequests.setLayoutManager(layoutManager);
    }
}