package com.example.co.squeeg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.apache.http.HttpException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;

import com.example.co.squeeg.Model.POSTGigsReview;
import com.example.co.squeeg.Model.POSTLanguageModel;
import com.example.co.squeeg.adapter.GigsReviewAdapter;
import com.example.co.squeeg.network.ApiClient;
import com.example.co.squeeg.network.ApiInterface;
import com.example.co.squeeg.utils.AppConstants;
import com.example.co.squeeg.utils.CustomProgressDialog;
import com.example.co.squeeg.utils.CustomTextView;
import com.example.co.squeeg.utils.NetworkAlertDialog;
import com.example.co.squeeg.utils.SessionHandler;
import com.example.co.squeeg.utils.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user5 on 15-12-2017.
 */

public class UserReviews extends BaseActivity {

    ListView mViewReviews;
    GigsReviewAdapter mGigsReviewAdapter;
    HashMap<String, String> postSellerReviews = new HashMap<String, String>();
    CustomProgressDialog mCustomProgressDialog;
    Toolbar toolbar;
    private String Gig_id;
    CustomTextView tvTitle;
    public POSTLanguageModel.Detail_gigs detail_gigs = new POSTLanguageModel().new Detail_gigs();
    public POSTLanguageModel.Common_strings commonStrings = new POSTLanguageModel().new Common_strings();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reviews);
        Utils.freeMemory();
        toolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        tvTitle = (CustomTextView) findViewById(R.id.tv_gigs_title);
        setSupportActionBar(toolbar);
        setLanguageValues();
        Gig_id = getIntent().getStringExtra(AppConstants.GIGS_ID);
        mCustomProgressDialog = new CustomProgressDialog(UserReviews.this);
        mViewReviews = (ListView) findViewById(R.id.lv_review_list);
        postSellerReviews.put("gig_id", Gig_id);
        getSellerReviews();
    }

    private void getSellerReviews() {
        mCustomProgressDialog.showDialog();
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.getGigsReviews(postSellerReviews, SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID), SessionHandler.getInstance().get(this, AppConstants.Language)).enqueue(new Callback<POSTGigsReview>() {
            @Override
            public void onResponse(Call<POSTGigsReview> call, Response<POSTGigsReview> response) {
                if (response.body().getCode().equals(200)) {
                    if (response.body().getData().size() > 0) {
                        mGigsReviewAdapter = new GigsReviewAdapter(UserReviews.this, response.body().getData());
                        mViewReviews.setAdapter(mGigsReviewAdapter);
                        mCustomProgressDialog.dismiss();
                    }
                } else if (response.body().getCode().equals(Integer.parseInt(AppConstants.INVALID_RESPONSE_CODE))) {
//                    mCustomProgressDialog.dismiss();
                    NetworkAlertDialog.networkAlertDialog(UserReviews.this, "", response.body().getMessage(), null, null);
                } else if (response.body().getCode().equals(AppConstants.InactiveStatusResponse)) {
                    Utils.createUserInActiceAlert(UserReviews.this, response.body().getMessage());
                }

            }

            @Override
            public void onFailure(Call<POSTGigsReview> call, Throwable t) {
                Log.i("TAG", t.getMessage());
                mCustomProgressDialog.dismiss();
                if (t instanceof UnknownHostException || t instanceof HttpException || t instanceof ConnectException || t instanceof SocketTimeoutException || t instanceof SocketException || t instanceof IOException) {
                    Toast.makeText(UserReviews.this, commonStrings.getLbl_server_prblm().getName(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void setLanguageValues() {
        detail_gigs = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.DETAILGIGS), POSTLanguageModel.Detail_gigs.class);
        commonStrings = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.COMMONSTRINGS), POSTLanguageModel.Common_strings.class);
        tvTitle.setText(detail_gigs.getLbl_reviews().getName());
    }

}
