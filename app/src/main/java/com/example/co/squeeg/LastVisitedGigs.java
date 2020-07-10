package com.example.co.squeeg;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.apache.http.HttpException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.example.co.squeeg.Model.POSTLanguageModel;
import com.example.co.squeeg.Model.POSTLastVisitedGigs;
import com.example.co.squeeg.adapter.LastVisitedGigsAdapter;
import com.example.co.squeeg.network.ApiClient;
import com.example.co.squeeg.network.ApiInterface;
import com.example.co.squeeg.receiver.NetworkChangeReceiver;
import com.example.co.squeeg.utils.AppConstants;
import com.example.co.squeeg.utils.CustomProgressDialog;
import com.example.co.squeeg.utils.CustomTextView;
import com.example.co.squeeg.utils.NetworkAlertDialog;
import com.example.co.squeeg.utils.SessionHandler;
import com.example.co.squeeg.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LastVisitedGigs extends BaseActivity {
    RecyclerView mFavRecycler;
    LastVisitedGigsAdapter aLastVisitedGigsAdapter;
    CustomProgressDialog mCustomProgressDialog;
    HashMap<String, String> postUserdetails = new HashMap<String, String>();
    Toolbar mToolbar;
    @BindView(R.id.tb_last_visited_gigs)
    CustomTextView tbLastVisitedGigs;
    private TextView inputNoGigs;
    LinearLayoutManager horizontalLayoutManagaer;
    int visibleItemCount, totalItemCount, pastVisiblesItems;
    boolean isLoading = false;
    int page_num = 1, total_pages;
    List<POSTLastVisitedGigs.Visited_detail> mData = new ArrayList<POSTLastVisitedGigs.Visited_detail>();
    public POSTLanguageModel.Common_strings commonStrings = new POSTLanguageModel().new Common_strings();
    public POSTLanguageModel.Navigation_screen navStrings = new POSTLanguageModel().new Navigation_screen();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_visited_gigs);
        ButterKnife.bind(this);
        mCustomProgressDialog = new CustomProgressDialog(this);
        mFavRecycler = (RecyclerView) findViewById(R.id.rcv_last_visited_gigs);
        inputNoGigs = (TextView) findViewById(R.id.tv_noGigs);
        mToolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        Utils.freeMemory();
        setSupportActionBar(mToolbar);
        horizontalLayoutManagaer
                = new LinearLayoutManager(LastVisitedGigs.this, LinearLayoutManager.VERTICAL, false);
        mFavRecycler.setLayoutManager(horizontalLayoutManagaer);
        setLangugeValues();
        getUserFavGigs();

        mFavRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                    int displayedPosition = horizontalLayoutManagaer.findFirstCompletelyVisibleItemPosition();
                    if (!isLoading && (visibleItemCount + pastVisiblesItems) >= totalItemCount && page_num < total_pages) {
                        Log.v("...", " Reached Last Item");
                        getUserFavGigs();
                        isLoading = true;
                    }
                }

            }
        });


    }

    private void getUserFavGigs() {
        if (NetworkChangeReceiver.isConnected()) {
            postUserdetails.put("user_id", SessionHandler.getInstance().get(LastVisitedGigs.this, AppConstants.TOKEN_ID));
            postUserdetails.put("device_type", "Android");
            postUserdetails.put("page", String.valueOf(page_num));

            mCustomProgressDialog.showDialog();
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            apiInterface.getLastVisitedGigs(postUserdetails, SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID), SessionHandler.getInstance().get(this, AppConstants.Language)).enqueue(new Callback<POSTLastVisitedGigs>() {
                @Override
                public void onResponse(Call<POSTLastVisitedGigs> call, Response<POSTLastVisitedGigs> response) {
                    mCustomProgressDialog.dismiss();
                    if (response.body().getCode().equals(200)) {
                        total_pages = response.body().getData().getTotal_pages();
                        page_num++;
                        isLoading = false;
                        if (response.body().getData().getVisited_details().size() > 0) {
                            if (mData.size() > 0) {
                                mData.addAll(response.body().getData().getVisited_details());
                                aLastVisitedGigsAdapter.notifyDataSetChanged();
                            } else {
                                mData.addAll(response.body().getData().getVisited_details());
                                aLastVisitedGigsAdapter = new LastVisitedGigsAdapter(LastVisitedGigs.this, mData);
                                mFavRecycler.setAdapter(aLastVisitedGigsAdapter);
                            }
                        } else {
                            if (mData.size() == 0) {
                                inputNoGigs.setVisibility(View.VISIBLE);
                                mFavRecycler.setVisibility(View.GONE);
                            }
                        }

                    } else if (response.body().getCode().equals(Integer.parseInt(AppConstants.INVALID_RESPONSE_CODE))) {
//                    mCustomProgressDialog.dismiss();
                        NetworkAlertDialog.networkAlertDialog(LastVisitedGigs.this, "", response.body().getMessage(), null, null);
                    } else if (response.body().getCode().equals(AppConstants.InactiveStatusResponse)) {
                        Utils.createUserInActiceAlert(LastVisitedGigs.this, response.body().getMessage());
                    } else {
                        Utils.toastMessage(LastVisitedGigs.this, response.body().getMessage());
                    }


                }

                @Override
                public void onFailure(Call<POSTLastVisitedGigs> call, Throwable t) {
                    mCustomProgressDialog.dismiss();
                    if (t instanceof UnknownHostException || t instanceof HttpException || t instanceof ConnectException || t instanceof SocketTimeoutException || t instanceof SocketException || t instanceof IOException) {
                        Toast.makeText(LastVisitedGigs.this, commonStrings.getLbl_server_prblm().getName(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Utils.toastMessage(LastVisitedGigs.this, commonStrings.getLbl_enable_internet().getName());
        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent gotoMain = new Intent(LastVisitedGigs.this, MainActivity.class);
            startActivity(gotoMain);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void setLangugeValues() {
        commonStrings = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.COMMONSTRINGS), POSTLanguageModel.Common_strings.class);
        navStrings = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.NAVSCREEN), POSTLanguageModel.Navigation_screen.class);
        tbLastVisitedGigs.setText(navStrings.getLbl_last_visited_gigs().getName());
    }
}


