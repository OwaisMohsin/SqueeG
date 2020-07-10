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

import com.example.co.squeeg.Model.POSTFavGigs;
import com.example.co.squeeg.Model.POSTLanguageModel;
import com.example.co.squeeg.adapter.FavouriteAdapter;
import com.example.co.squeeg.fragment.HomeFragment;
import com.example.co.squeeg.interfaces.GetfavRemovedGigIdenitifer;
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

public class Favourites extends BaseActivity implements GetfavRemovedGigIdenitifer {
    RecyclerView mFavRecycler;
    FavouriteAdapter aFavouriteAdapter;
    CustomProgressDialog mCustomProgressDialog;
    HashMap<String, String> postUserdetails = new HashMap<String, String>();
    Toolbar mToolbar;
    HomeFragment mHomeFragment;
    boolean gigsRemovedBool;
    private TextView inputNoGigs;
    LinearLayoutManager horizontalLayoutManagaer;
    int visibleItemCount, totalItemCount, pastVisiblesItems;
    boolean isLoading = false;
    int page_num = 1, total_pages;
    List<POSTFavGigs.Favourite_detail> mData = new ArrayList<POSTFavGigs.Favourite_detail>();
    public POSTLanguageModel.Common_strings commonStrings = new POSTLanguageModel().new Common_strings();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        Utils.freeMemory();
        mCustomProgressDialog = new CustomProgressDialog(this);
        mFavRecycler = (RecyclerView) findViewById(R.id.rcv_favourites);
        mToolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        inputNoGigs = (TextView) findViewById(R.id.tv_noGigs);
        setSupportActionBar(mToolbar);
        setLanguageValues();
        horizontalLayoutManagaer
                = new LinearLayoutManager(Favourites.this, LinearLayoutManager.VERTICAL, false);
        mFavRecycler.setLayoutManager(horizontalLayoutManagaer);

        mHomeFragment = new HomeFragment();

        mFavRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (!isLoading)
                    if (dy > 0) {
                        visibleItemCount = horizontalLayoutManagaer.getChildCount();
                        totalItemCount = horizontalLayoutManagaer.getItemCount();
                        pastVisiblesItems = horizontalLayoutManagaer.findFirstVisibleItemPosition();
                        int displayedPosition = horizontalLayoutManagaer.findFirstCompletelyVisibleItemPosition();
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            Log.v("...", " Reached Last Item");
                            page_num++;
                            getUserFavGigs();
                            isLoading = true;
                        }
                    }

            }
        });

    }

    private void getUserFavGigs() {

        if (NetworkChangeReceiver.isConnected()) {
            mCustomProgressDialog.showDialog();
            //postUserdetails.put("user_id", SessionHandler.getInstance().get(Favourites.this, AppConstants.TOKEN_ID));
            postUserdetails.put("device_type", AppConstants.DeviceType);
            postUserdetails.put("page", String.valueOf(page_num));
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            apiInterface.getFavLists(postUserdetails, SessionHandler.getInstance().get(Favourites.this, AppConstants.TOKEN_ID), SessionHandler.getInstance().get(this, AppConstants.Language)).enqueue(new Callback<POSTFavGigs>() {
                @Override
                public void onResponse(Call<POSTFavGigs> call, Response<POSTFavGigs> response) {
                    mCustomProgressDialog.dismiss();
                    if (response.body().getCode().equals(200)) {
                        isLoading = false;
                        if (response.body().getData().getFavourite_details().size() > 0) {
                            if (mData.size() > 0) {
                                mData.addAll(response.body().getData().getFavourite_details());
                                aFavouriteAdapter.notifyDataSetChanged();
                            } else {
                                mData.addAll(response.body().getData().getFavourite_details());
                                aFavouriteAdapter = new FavouriteAdapter(Favourites.this, mData);
                                mFavRecycler.setAdapter(aFavouriteAdapter);
                            }
                        } else {
                            if (mData.size() == 0) {
                                inputNoGigs.setVisibility(View.VISIBLE);
                                mFavRecycler.setVisibility(View.GONE);

                            }
                        }
                    } else if (response.body().getCode().equals(Integer.parseInt(AppConstants.INVALID_RESPONSE_CODE))) {
//                    mCustomProgressDialog.dismiss();
                        NetworkAlertDialog.networkAlertDialog(Favourites.this, "", response.body().getMessage(), null, null);
                    } else if (response.body().getCode().equals(AppConstants.InactiveStatusResponse)) {
                        Utils.createUserInActiceAlert(Favourites.this, response.body().getMessage());
                    } else {
                        inputNoGigs.setVisibility(View.VISIBLE);
                        mFavRecycler.setVisibility(View.GONE);
                    }

                }

                @Override
                public void onFailure(Call<POSTFavGigs> call, Throwable t) {
                    Log.i("TAG", t.getMessage());
                    mCustomProgressDialog.dismiss();
                    if (t instanceof UnknownHostException || t instanceof HttpException || t instanceof ConnectException || t instanceof SocketTimeoutException || t instanceof SocketException || t instanceof IOException) {
                        //Toast.makeText(Favourites.this, getString(R.string.err_server_msg), Toast.LENGTH_SHORT).show();
                        NetworkAlertDialog.networkAlertDialog(Favourites.this, commonStrings.getLbl_network_err().getName(),
                                commonStrings.getLbl_server_err().getName(), favouritesRunn, null);
                    }
                }
            });

        } else {
            Utils.toastMessage(Favourites.this, commonStrings.getLbl_enable_internet().getName());
        }
    }

    Runnable favouritesRunn = new Runnable() {
        @Override
        public void run() {
            //TODO: API call
            getUserFavGigs();
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent gotoMain = new Intent(Favourites.this, MainActivity.class);
            startActivity(gotoMain);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getFavRemGigIds(boolean removed) {
        this.gigsRemovedBool = removed;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mData.size() > 0) {
            mData.clear();
        }
        getUserFavGigs();
    }

    public void setLanguageValues() {
        commonStrings = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.COMMONSTRINGS), POSTLanguageModel.Common_strings.class);

    }
}


