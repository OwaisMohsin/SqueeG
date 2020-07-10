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
import com.example.co.squeeg.Model.seller_services.POSTSellerServicesList;
import com.example.co.squeeg.adapter.AdapterSellerServices;
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

public class ChooseSellerServiceScreen extends AppCompatActivity {

    private TextView text_no_gigs_found;

    public static Activity activity;

    private ShimmerFrameLayout mShimmerViewContainer;
    private RecyclerView rvSellerServices;
    private AdapterSellerServices sellerServicesAdapter;
    private Toolbar mToolbar;

    public POSTLanguageModel.Common_strings commonStrings = new POSTLanguageModel().new Common_strings();

    private RequestBody user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_seller_service_screen);

        mToolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        setSupportActionBar(mToolbar);

        activity = this;

        Utils.freeMemory();
        initLayouts();

        if (NetworkChangeReceiver.isConnected()) {
            loadSellerServices();
        } else {
            Utils.showToast(ChooseSellerServiceScreen.this);
        }
    }

//    @Override
//    protected void onPostResume() {
//        super.onPostResume();
//        getData();
//
//        if (NetworkChangeReceiver.isConnected()) {
//            loadSellerServices();
//        } else {
//            Utils.showToast(ChooseServiceScreen.this);
//        }
//    }

    private void initLayouts() {
        text_no_gigs_found = (TextView) findViewById(R.id.tv_no_gigs_found);
        mShimmerViewContainer = (ShimmerFrameLayout) findViewById(R.id.shimmer_view_container);

        rvSellerServices = (RecyclerView) findViewById(R.id.rv_seller_services);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(ChooseSellerServiceScreen.this, LinearLayoutManager.VERTICAL, false);
        rvSellerServices.setLayoutManager(layoutManager);
    }

    private void loadSellerServices() {
        mShimmerViewContainer.startShimmerAnimation();
        user_id = RequestBody.create(MediaType.parse("text/plain"),
                SessionHandler.getInstance().get(ChooseSellerServiceScreen.this, AppConstants.TOKEN_ID));

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.loadSellerServices(user_id,
                SessionHandler.getInstance().get(ChooseSellerServiceScreen.this, AppConstants.TOKEN_ID),
                SessionHandler.getInstance().get(ChooseSellerServiceScreen.this, AppConstants.Language))
                .enqueue(new Callback<POSTSellerServicesList>() {
                    @Override
                    public void onResponse(Call<POSTSellerServicesList> call, Response<POSTSellerServicesList> response) {

                        if (response.body().getCode().equals(200)) {
                            if (response.body().getData().size() > 0) {
                                rvSellerServices.setVisibility(View.VISIBLE);
                                text_no_gigs_found.setVisibility(View.GONE);

                                sellerServicesAdapter = new AdapterSellerServices(ChooseSellerServiceScreen.this, response.body().getData());
                                rvSellerServices.setAdapter(sellerServicesAdapter);
                                sellerServicesAdapter.notifyDataSetChanged();
                            } else {
                                rvSellerServices.setVisibility(View.GONE);
                                text_no_gigs_found.setVisibility(View.VISIBLE);
                            }
                        } else if (response.body().getCode().equals(Integer.parseInt(AppConstants.INVALID_RESPONSE_CODE))) {
                            NetworkAlertDialog.networkAlertDialog(ChooseSellerServiceScreen.this, "", response.body().getMessage(), null, null);
                        } else {
                            text_no_gigs_found.setVisibility(View.VISIBLE);
                        }
                        mShimmerViewContainer.stopShimmerAnimation();
                        mShimmerViewContainer.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<POSTSellerServicesList> call, Throwable t) {
                        Log.i("TAG", t.getMessage());
                        try {
                            if (t instanceof UnknownHostException || t instanceof HttpException || t instanceof ConnectException || t instanceof SocketTimeoutException || t instanceof SocketException || t instanceof IOException) {
                                NetworkAlertDialog.networkAlertDialog(ChooseSellerServiceScreen.this, commonStrings.getLbl_network_err().getName(),
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