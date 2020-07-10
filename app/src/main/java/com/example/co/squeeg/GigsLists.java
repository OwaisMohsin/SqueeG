package com.example.co.squeeg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

import com.example.co.squeeg.Model.GETAllGigs;
import com.example.co.squeeg.Model.POSTLanguageModel;
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

/**
 * Created by Prasad on 10/26/2017.
 */

public class GigsLists extends BaseActivity {

    private GridView gridGigsList;
    private AllGigsListsAdapter aAllGigsListsAdapter;
    private CustomProgressDialog mCustomProgressDialog;
    private String token = "", cat_id = "", sub_cat_id = "";
    private Toolbar toolbar;
    private HashMap<String, String> postDetails = new HashMap<String, String>();
    private int total_page, page_num = 1;
    private List<GETAllGigs.Category_detail> mData = new ArrayList<GETAllGigs.Category_detail>();
    private boolean isLoading = false;
    private TextView txt_noGigs, txt_toolbar_title;
    public POSTLanguageModel.Common_strings commonStrings = new POSTLanguageModel().new Common_strings();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gigs);
        Utils.freeMemory();

        mCustomProgressDialog = new CustomProgressDialog(this);
        initLayouts();
        setLanguageValues();

        gridGigsList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                int threshold = 1;
                int count = gridGigsList.getCount();
                if (!isLoading && gridGigsList.getLastVisiblePosition() >= count - threshold && page_num < total_page) {
                    // Execute LoadMoreDataTask AsyncTask
                    page_num++;
                    getGigs();
                    isLoading = true;
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });

    }

    private void getGigs() {
        if (NetworkChangeReceiver.isConnected()) {
            mCustomProgressDialog.showDialog();
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            if (SessionHandler.getInstance().get(GigsLists.this, AppConstants.TOKEN_ID) != null) {
                token = SessionHandler.getInstance().get(GigsLists.this, AppConstants.TOKEN_ID);
            } else {
                token = AppConstants.GUEST_TOKEN;
            }
            if (getIntent().getStringExtra(AppConstants.CAT_ID) != null) {
                cat_id = getIntent().getStringExtra(AppConstants.CAT_ID);
            } else {
                cat_id = "";
            }


            if (getIntent().getStringExtra(AppConstants.SUB_CAT_ID) != null) {
                sub_cat_id = getIntent().getStringExtra(AppConstants.SUB_CAT_ID);
            } else {
                sub_cat_id = "";
            }

            if (!cat_id.isEmpty() && !sub_cat_id.isEmpty()) {
                postDetails.put("category_id", cat_id);
                postDetails.put("sub_category_id", sub_cat_id);
                //postDetails.put("user_id", user_id);
                postDetails.put("services", "ALL");
                postDetails.put("device_type", AppConstants.DeviceType);
                postDetails.put("page", String.valueOf(page_num));
            } else if (!cat_id.isEmpty()) {
                postDetails.put("category_id", cat_id);
                //postDetails.put("user_id", user_id);
                postDetails.put("services", "ALL");
                postDetails.put("device_type", AppConstants.DeviceType);
                postDetails.put("page", String.valueOf(page_num));
            } else if (!cat_id.isEmpty() && !sub_cat_id.isEmpty()) {
                postDetails.put("category_id", cat_id);
                postDetails.put("sub_category_id", sub_cat_id);
                postDetails.put("services", "ALL");
                postDetails.put("device_type", AppConstants.DeviceType);
                postDetails.put("page", String.valueOf(page_num));
            } else if (!cat_id.isEmpty()) {
                postDetails.put("category_id", cat_id);
                postDetails.put("services", "ALL");
                postDetails.put("device_type", AppConstants.DeviceType);
                postDetails.put("page", String.valueOf(page_num));
            } else {
                postDetails.put("services", "ALL");
                postDetails.put("device_type", AppConstants.DeviceType);
                postDetails.put("page", String.valueOf(page_num));
            }

            apiInterface.getUserGigs(postDetails, token, SessionHandler.getInstance().get(this, AppConstants.Language)).enqueue(new Callback<GETAllGigs>() {
                @Override
                public void onResponse(Call<GETAllGigs> call, Response<GETAllGigs> response) {
                    mCustomProgressDialog.dismiss();
                    if (response.body().getCode().equals(200)) {
                        total_page = response.body().getData().getTotal_pages();
                        isLoading = false;
                        if (response.body().getData().getCategory_details().size() > 0) {

                            if (mData.size() > 0) {
                                mData.addAll(response.body().getData().getCategory_details());
                                aAllGigsListsAdapter.notifyDataSetChanged();

                            } else {
                                mData.addAll(response.body().getData().getCategory_details());
                                aAllGigsListsAdapter = new AllGigsListsAdapter(GigsLists.this, mData);
                                gridGigsList.setAdapter(aAllGigsListsAdapter);

                            }

                        } else {

                            if (mData.size() == 0) {
                                gridGigsList.setVisibility(View.GONE);
                                txt_noGigs.setVisibility(View.VISIBLE);
                            } else {
                                Toast.makeText(GigsLists.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }


                        }
                    } else if (response.body().getCode().equals(Integer.parseInt(AppConstants.INVALID_RESPONSE_CODE))) {
//                    mCustomProgressDialog.dismiss();
                        NetworkAlertDialog.networkAlertDialog(GigsLists.this, "", response.body().getMessage(), null, null);
                    } else if (response.body().getCode().equals(AppConstants.InactiveStatusResponse)) {
                        Utils.createUserInActiceAlert(GigsLists.this, response.body().getMessage());
                    } else {
                        Toast.makeText(GigsLists.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<GETAllGigs> call, Throwable t) {
                    Log.i("TAG", t.getMessage());
                    isLoading = false;
                    mCustomProgressDialog.dismiss();
                    if (t instanceof UnknownHostException || t instanceof HttpException || t instanceof ConnectException || t instanceof SocketTimeoutException || t instanceof SocketException || t instanceof IOException) {
                        Toast.makeText(GigsLists.this, commonStrings.getLbl_server_prblm().getName(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            showToast();
        }


    }

    private void showToast() {
        Toast.makeText(this, commonStrings.getLbl_enable_internet().getName(), Toast.LENGTH_SHORT).show();
    }

    private void initLayouts() {
        gridGigsList = (GridView) findViewById(R.id.gv_gigs_list);
        txt_noGigs = (TextView) findViewById(R.id.tv_nogigs);
        toolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        txt_toolbar_title = (TextView) findViewById(R.id.tv_title);
        setSupportActionBar(toolbar);
        if (getIntent().getStringExtra(AppConstants.CAT_NAME) != null && !getIntent().getStringExtra(AppConstants.CAT_NAME).isEmpty()) {
            txt_toolbar_title.setText(getIntent().getStringExtra(AppConstants.CAT_NAME));
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mData.size() > 0) {
            mData.clear();
        }
        getGigs();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void setLanguageValues() {
        commonStrings = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.COMMONSTRINGS), POSTLanguageModel.Common_strings.class);

    }
}
