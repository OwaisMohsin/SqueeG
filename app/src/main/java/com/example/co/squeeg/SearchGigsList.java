package com.example.co.squeeg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.GridView;
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

import com.example.co.squeeg.Model.POSTLanguageModel;
import com.example.co.squeeg.Model.POSTSearchGigs;
import com.example.co.squeeg.adapter.SearchGigsListAdapter;
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
 * Created by user5 on 27-12-2017.
 */

public class SearchGigsList extends BaseActivity {


    SearchGigsListAdapter mSearchGigsListAdapter;
    private CustomProgressDialog mCustomProgressDialog;
    private Toolbar mToolbar;
    private HashMap<String, String> postDetails = new HashMap<String, String>();
    private GridView gridGigsList;
    private TextView inputNoGigs;
    private int total_page, page_num = 1;
    private boolean isLoading = false;
    private List<POSTSearchGigs.Result_detail> mData = new ArrayList<POSTSearchGigs.Result_detail>();
    public POSTLanguageModel.Common_strings commonStrings = new POSTLanguageModel().new Common_strings();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gigs);
        Utils.freeMemory();
        mToolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        setSupportActionBar(mToolbar);
        gridGigsList = (GridView) findViewById(R.id.gv_gigs_list);
        mCustomProgressDialog = new CustomProgressDialog(this);
        inputNoGigs = (TextView) findViewById(R.id.tv_nogigs);
        setLangugeValues();
        postSearchGigs();


        gridGigsList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                int threshold = 1;
                int count = gridGigsList.getCount();
                if (!isLoading && gridGigsList.getLastVisiblePosition() >= count - threshold && page_num < total_page) {
                    // Execute LoadMoreDataTask AsyncTask
                    page_num++;
                    postSearchGigs();
                    isLoading = true;
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });

    }


    public void postSearchGigs() {
        mCustomProgressDialog.showDialog();
        if (getIntent().getStringExtra("title") != null) {
            postDetails.put("title", getIntent().getStringExtra("title"));
        }

        if (getIntent().getStringExtra("state") != null) {
            postDetails.put("state", getIntent().getStringExtra("state"));
        }
        if (getIntent().getStringExtra("country") != null) {
            postDetails.put("country", getIntent().getStringExtra("country"));
        }


        if (getIntent().getStringExtra("cat_id") != null) {
            postDetails.put("category_id", getIntent().getStringExtra("cat_id"));
        } else {
            postDetails.put("category_id", "");
        }
        postDetails.put("device_type", AppConstants.DeviceType);
        postDetails.put("page", String.valueOf(page_num));

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.postSearchGigs(postDetails, SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID), SessionHandler.getInstance().get(this, AppConstants.Language)).enqueue(new Callback<POSTSearchGigs>() {
            @Override
            public void onResponse(Call<POSTSearchGigs> call, Response<POSTSearchGigs> response) {
                mCustomProgressDialog.dismiss();
                if (response.body().getCode().equals(200)) {
                    isLoading = false;
                    total_page = response.body().getData().getTotal_pages();

                    if (response.body().getData().getResult_details().size() > 0) {
                        if (mData.size() > 0) {
                            mData.addAll(response.body().getData().getResult_details());
                            mSearchGigsListAdapter.notifyDataSetChanged();
                        } else {
                            mData.addAll(response.body().getData().getResult_details());
                            mSearchGigsListAdapter = new SearchGigsListAdapter(SearchGigsList.this, mData);
                            gridGigsList.setAdapter(mSearchGigsListAdapter);
                        }
                    } else {
                        if (mData.size() == 0) {
                            gridGigsList.setVisibility(View.GONE);
                            inputNoGigs.setVisibility(View.VISIBLE);
                        }
                    }
                } else if (response.body().getCode().equals(Integer.parseInt(AppConstants.INVALID_RESPONSE_CODE))) {
//                    mCustomProgressDialog.dismiss();
                    NetworkAlertDialog.networkAlertDialog(SearchGigsList.this, "", response.body().getMessage(), null, null);
                } else if (response.body().getCode().equals(AppConstants.InactiveStatusResponse)) {
                    Utils.createUserInActiceAlert(SearchGigsList.this, response.body().getMessage());
                } else {
                    gridGigsList.setVisibility(View.GONE);
                    inputNoGigs.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<POSTSearchGigs> call, Throwable t) {
                gridGigsList.setVisibility(View.GONE);
                inputNoGigs.setVisibility(View.VISIBLE);
                mCustomProgressDialog.dismiss();
                if (t instanceof UnknownHostException || t instanceof HttpException || t instanceof ConnectException || t instanceof SocketTimeoutException || t instanceof SocketException || t instanceof IOException) {
                    Toast.makeText(SearchGigsList.this, commonStrings.getLbl_server_prblm().getName(), Toast.LENGTH_SHORT).show();
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

    public void setLangugeValues() {
        commonStrings = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.COMMONSTRINGS), POSTLanguageModel.Common_strings.class);
        inputNoGigs.setText(commonStrings.getLbl_no_gigs_available().getName());
    }
}
