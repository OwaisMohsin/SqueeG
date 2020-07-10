package com.example.co.squeeg;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.co.squeeg.Model.POSTLanguageModel;
import com.example.co.squeeg.Model.buyer_requests_for_buyer.BuyerRequestsForBuyer;
import com.example.co.squeeg.Model.seller_offers_for_buyer.POSTSellerOffersForBuyerList;
import com.example.co.squeeg.Model.seller_offers_for_buyer.SellerOffersForBuyer;
import com.example.co.squeeg.adapter.AdapterSellerOffersToBuyer;
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

public class SellerOffersToBuyerScreen extends AppCompatActivity {

    Handler mHandler;

    private Toolbar mToolbar;

    private TextView txtNoInformation;
    private CustomProgressDialog mCustomProgressDialog;
    private RecyclerView rvBuyerRequests;
    private AdapterSellerOffersToBuyer adapterSellerOffersToBuyer;

    public POSTLanguageModel.Common_strings commonStrings = new POSTLanguageModel().new Common_strings();

    private RequestBody buyer_id,
            request_id;

    private BuyerRequestsForBuyer buyerRequestsForBuyer;
    private List<SellerOffersForBuyer> buyerRequestsForBuyerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_offers_to_buyer_screen);

        mToolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        setSupportActionBar(mToolbar);

        getData();

        mCustomProgressDialog = new CustomProgressDialog(SellerOffersToBuyerScreen.this);

        Utils.freeMemory();
        initLayouts();
        mHandler = new Handler();
        setLanguageLocale();

        if (NetworkChangeReceiver.isConnected()) {
            mCustomProgressDialog.showDialog();
            loadSellerOffers();
        } else {
            Utils.showToast(SellerOffersToBuyerScreen.this);
        }
    }

    private void getData() {
        Gson gson = new Gson();
        String sBuyerRequest = SessionHandler.getInstance().get(SellerOffersToBuyerScreen.this, AppConstants.BUYER_REQUEST);

        buyerRequestsForBuyer = gson.fromJson(sBuyerRequest, BuyerRequestsForBuyer.class);
    }

    public void setLanguageLocale() {
        commonStrings = new Gson().fromJson(SessionHandler.getInstance().get(SellerOffersToBuyerScreen.this, AppConstants.COMMONSTRINGS), POSTLanguageModel.Common_strings.class);
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

    private void loadSellerOffers() {
        buyer_id = RequestBody.create(MediaType.parse("text/plain"),
                SessionHandler.getInstance().get(SellerOffersToBuyerScreen.this, AppConstants.TOKEN_ID));

        request_id = RequestBody.create(MediaType.parse("text/plain"),
                buyerRequestsForBuyer.getRequestId());

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.loadSellerOffersForBuyer(buyer_id, request_id,
                SessionHandler.getInstance().get(SellerOffersToBuyerScreen.this, AppConstants.TOKEN_ID),
                SessionHandler.getInstance().get(SellerOffersToBuyerScreen.this, AppConstants.Language))
                .enqueue(new Callback<POSTSellerOffersForBuyerList>() {
                    @Override
                    public void onResponse(Call<POSTSellerOffersForBuyerList> call, Response<POSTSellerOffersForBuyerList> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getCode().equals(200)) {
                                if (response.body().getData().size() > 0) {
                                    buyerRequestsForBuyerList.clear();
                                    buyerRequestsForBuyerList.addAll(response.body().getData());
                                    adapterSellerOffersToBuyer = new AdapterSellerOffersToBuyer(SellerOffersToBuyerScreen.this, buyerRequestsForBuyerList, response.body().getCurrencyData());
                                    rvBuyerRequests.setAdapter(adapterSellerOffersToBuyer);
                                    adapterSellerOffersToBuyer.notifyDataSetChanged();
                                }
                            } else if (response.body().getCode().equals(Integer.parseInt(AppConstants.INVALID_RESPONSE_CODE))) {
                                NetworkAlertDialog.networkAlertDialog(SellerOffersToBuyerScreen.this, "", response.body().getMessage(), null, null);
                            } else {
                                Toast.makeText(SellerOffersToBuyerScreen.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        txtNoInformation.setVisibility(View.GONE);
                        mCustomProgressDialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<POSTSellerOffersForBuyerList> call, Throwable t) {
                        Log.i("TAG", t.getMessage());
                        mCustomProgressDialog.dismiss();
                        txtNoInformation.setText(R.string.some_error_occur);
                        txtNoInformation.setVisibility(View.VISIBLE);
                        if (t instanceof UnknownHostException || t instanceof HttpException || t instanceof ConnectException || t instanceof SocketTimeoutException || t instanceof SocketException || t instanceof IOException) {
                            NetworkAlertDialog.networkAlertDialog(SellerOffersToBuyerScreen.this, commonStrings.getLbl_network_err().getName(),
                                    commonStrings.getLbl_server_err().getName(), creategigsRunn, null);
                        }
                    }
                });
    }

    Runnable creategigsRunn = new Runnable() {
        @Override
        public void run() {
            //TODO: API call
            loadSellerOffers();
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            gotoOtherActivity();
        }
        return super.onOptionsItemSelected(item);
    }

    private void gotoOtherActivity() {
        finish();
    }

    private void initLayouts() {
        txtNoInformation = (TextView) findViewById(R.id.txt_no_info);

        rvBuyerRequests = (RecyclerView) findViewById(R.id.rv_seller_offers);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(SellerOffersToBuyerScreen.this, LinearLayoutManager.HORIZONTAL, false);
        rvBuyerRequests.setLayoutManager(layoutManager);
    }
}