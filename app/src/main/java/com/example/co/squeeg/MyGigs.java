package com.example.co.squeeg;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
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

import com.example.co.squeeg.Model.GETMyGigs;
import com.example.co.squeeg.Model.POSTLanguageModel;
import com.example.co.squeeg.adapter.MyGigsListAdapter;
import com.example.co.squeeg.network.ApiClient;
import com.example.co.squeeg.network.ApiInterface;
import com.example.co.squeeg.utils.AppConstants;
import com.example.co.squeeg.utils.CustomProgressDialog;
import com.example.co.squeeg.utils.NetworkAlertDialog;
import com.example.co.squeeg.utils.SessionHandler;
import com.example.co.squeeg.utils.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user5 on 06-11-2017.
 */

public class MyGigs extends BaseActivity implements View.OnClickListener {

    GridView gridGigsList;
    private MyGigsListAdapter myGigsListAdapter;
    private Toolbar toolbar;
    private CustomProgressDialog mCustomProgressDialog;
    private TextView inputNoGigs;
    ProgressBar progressBar;
    private int total_page, page_num = 1;
    private final HashMap<String, String> postData = new HashMap<String, String>();
    private List<GETMyGigs.Gigs_detail> mData = new ArrayList<GETMyGigs.Gigs_detail>();
    boolean isLoading = false;
    public POSTLanguageModel.Common_strings commonStrings = new POSTLanguageModel().new Common_strings();
    public POSTLanguageModel.Navigation_screen navStrings = new POSTLanguageModel().new Navigation_screen();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_details_seller_gigs);
        Utils.freeMemory();
        Fresco.initialize(this);
        toolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        gridGigsList = (GridView) findViewById(R.id.customGridview);
        inputNoGigs = (TextView) findViewById(R.id.tv_noGigs);
        setSupportActionBar(toolbar);

        setLangugeValues();
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        mCustomProgressDialog = new CustomProgressDialog(this);


        gridGigsList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                int threshold = 1;
                int count = gridGigsList.getCount();
                if (!isLoading && gridGigsList.getLastVisiblePosition() >= count - threshold && page_num < total_page) {
                    // Execute LoadMoreDataTask AsyncTask
                    page_num++;
                    postMyGigs();
                    isLoading = true;
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });

    }


    private void postMyGigs() {
        mCustomProgressDialog.showDialog();
        postData.put("user_id", SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID));
        postData.put("device_type", "Android");
        postData.put("page", String.valueOf(page_num));
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.postMyGigs(postData, SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID), SessionHandler.getInstance().get(this, AppConstants.Language)).enqueue(new Callback<GETMyGigs>() {
            @Override
            public void onResponse(Call<GETMyGigs> call, Response<GETMyGigs> response) {
                mCustomProgressDialog.dismiss();
                if (response.body().getCode().equals(200)) {
                    isLoading = false;
                    total_page = response.body().getData().getTotal_pages();
                    if (response.body().getData().getGigs_details().size() > 0) {

                        if (mData.size() > 0) {
                            mData.addAll(response.body().getData().getGigs_details());
                            myGigsListAdapter.notifyDataSetChanged();
                        } else {
                            mData.addAll(response.body().getData().getGigs_details());
                            myGigsListAdapter = new MyGigsListAdapter(MyGigs.this, mData);
                            gridGigsList.setAdapter(myGigsListAdapter);
                        }

                        /*for (int i = 0; i < response.body().getData().getGigs_details().size(); i++) {
                            mData.add(response.body().getData().getGigs_details().get(i));
                        }*/
                    } else {
                        if (mData.size() == 0) {
                            inputNoGigs.setVisibility(View.VISIBLE);
                            gridGigsList.setVisibility(View.GONE);
                        }
                    }

                } else if (response.body().getCode().equals(Integer.parseInt(AppConstants.INVALID_RESPONSE_CODE))) {
//                    mCustomProgressDialog.dismiss();
                    NetworkAlertDialog.networkAlertDialog(MyGigs.this, "", response.body().getMessage(), null, null);
                } else if (response.body().getCode().equals(AppConstants.InactiveStatusResponse)) {
                    Utils.createUserInActiceAlert(MyGigs.this, response.body().getMessage());
                } else if (response.body().getCode().equals(404)) {
                    inputNoGigs.setVisibility(View.VISIBLE);
                    gridGigsList.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<GETMyGigs> call, Throwable t) {
                mCustomProgressDialog.dismiss();
                if (t instanceof IOException || t instanceof UnknownHostException || t instanceof HttpException || t instanceof ConnectException || t instanceof SocketTimeoutException || t instanceof SocketException) {
                    Toast.makeText(MyGigs.this, commonStrings.getLbl_server_prblm().getName(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent gotoMain = new Intent(MyGigs.this, MainActivity.class);
            startActivity(gotoMain);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mData.size() > 0) {
            mData.clear();
        }
        postMyGigs();
    }

    public void setLangugeValues() {
        commonStrings = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.COMMONSTRINGS), POSTLanguageModel.Common_strings.class);
        inputNoGigs.setText(commonStrings.getLbl_no_gigs_created().getName());
        navStrings = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.NAVSCREEN), POSTLanguageModel.Navigation_screen.class);
        this.getSupportActionBar().setTitle(navStrings.getLbl_my_gigs().getName());
    }
}
