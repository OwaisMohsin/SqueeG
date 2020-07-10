package com.example.co.squeeg;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.co.squeeg.Model.POSTLanguageModel;
import com.example.co.squeeg.Model.buyer_requests_for_buyer.POSTBuyerRequestForBuyerList;
import com.example.co.squeeg.adapter.AdapterBuyerRequestsToBuyer;
import com.example.co.squeeg.network.ApiClient;
import com.example.co.squeeg.network.ApiInterface;
import com.example.co.squeeg.receiver.NetworkChangeReceiver;
import com.example.co.squeeg.utils.AppConstants;
import com.example.co.squeeg.utils.NetworkAlertDialog;
import com.example.co.squeeg.utils.SessionHandler;
import com.example.co.squeeg.utils.Utils;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.apache.http.HttpException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuyerRequestsToBuyerScreen extends AppCompatActivity {

    private TextView text_no_requests_found;

    public static Activity activity;

    private ShimmerFrameLayout mShimmerViewContainer;
    private RecyclerView rvSellerServices;
    private AdapterBuyerRequestsToBuyer buyerRequestsAdapter;
    private Toolbar mToolbar;

    public POSTLanguageModel.Common_strings commonStrings = new POSTLanguageModel().new Common_strings();

    private RequestBody user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_requests_to_buyer_screen);

        mToolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        setSupportActionBar(mToolbar);

        activity = this;

        Utils.freeMemory();
        initLayouts();

        if (NetworkChangeReceiver.isConnected()) {
            loadSellerServices();
        } else {
            Utils.showToast(BuyerRequestsToBuyerScreen.this);
        }
    }

    private void initLayouts() {
        text_no_requests_found = (TextView) findViewById(R.id.tv_no_request_found);
        mShimmerViewContainer = (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container);

        rvSellerServices = (RecyclerView) findViewById(R.id.rv_buyer_requests);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(BuyerRequestsToBuyerScreen.this, LinearLayoutManager.VERTICAL, false);
        rvSellerServices.setLayoutManager(layoutManager);
    }

    private void loadSellerServices() {
        mShimmerViewContainer.startShimmerAnimation();
        user_id = RequestBody.create(MediaType.parse("text/plain"),
                SessionHandler.getInstance().get(BuyerRequestsToBuyerScreen.this, AppConstants.TOKEN_ID));

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.loadBuyerRequestsForBuyer(user_id,
                SessionHandler.getInstance().get(BuyerRequestsToBuyerScreen.this, AppConstants.TOKEN_ID),
                SessionHandler.getInstance().get(BuyerRequestsToBuyerScreen.this, AppConstants.Language))
                .enqueue(new Callback<POSTBuyerRequestForBuyerList>() {
                    @Override
                    public void onResponse(Call<POSTBuyerRequestForBuyerList> call, Response<POSTBuyerRequestForBuyerList> response) {

                        if (response.body().getCode().equals(200)) {
                            if (response.body().getData().size() > 0) {
                                rvSellerServices.setVisibility(View.VISIBLE);
                                text_no_requests_found.setVisibility(View.GONE);

                                buyerRequestsAdapter = new AdapterBuyerRequestsToBuyer(BuyerRequestsToBuyerScreen.this, response.body().getData());
                                rvSellerServices.setAdapter(buyerRequestsAdapter);
                                buyerRequestsAdapter.notifyDataSetChanged();
                            } else {
                                rvSellerServices.setVisibility(View.GONE);
                                text_no_requests_found.setVisibility(View.VISIBLE);
                            }
                        } else if (response.body().getCode().equals(Integer.parseInt(AppConstants.INVALID_RESPONSE_CODE))) {
                            NetworkAlertDialog.networkAlertDialog(BuyerRequestsToBuyerScreen.this, "", response.body().getMessage(), null, null);
                        } else {
                            text_no_requests_found.setVisibility(View.VISIBLE);
                        }
                        mShimmerViewContainer.stopShimmerAnimation();
                        mShimmerViewContainer.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<POSTBuyerRequestForBuyerList> call, Throwable t) {
                        Log.i("TAG", t.getMessage());
                        try {
                            if (t instanceof UnknownHostException || t instanceof HttpException || t instanceof ConnectException || t instanceof SocketTimeoutException || t instanceof SocketException || t instanceof IOException) {
                                NetworkAlertDialog.networkAlertDialog(BuyerRequestsToBuyerScreen.this, commonStrings.getLbl_network_err().getName(),
                                        commonStrings.getLbl_server_prblm().getName(), homegigsRunn, null);
                            }
                        } catch (Resources.NotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    Runnable homegigsRunn = new Runnable() {
        @Override
        public void run() {
            //TODO: API call
            //mCustomProgressDialog.showDialog();
            loadSellerServices();
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
//        Intent gotoActivity = new Intent(ChooseServiceScreen.this, MainActivity.class);
//        gotoActivity.putExtra("From", "MainPage");
//        gotoActivity.putExtra("LoadFragment", 6);
//        gotoActivity.putExtra("LoadNav", 6);
//        startActivity(gotoActivity);
        finish();
    }
}