package com.example.co.squeeg;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
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

import com.example.co.squeeg.Model.POSTLanguageModel;
import com.example.co.squeeg.Model.POSTMyActivity;
import com.example.co.squeeg.fragment.MyPaymentFragment;
import com.example.co.squeeg.fragment.MyPurchasesFragment;
import com.example.co.squeeg.fragment.MySalesFragment;
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

public class MyActivity extends BaseActivity {

    CustomProgressDialog mCustomProgressDialog;
    MyPurchasesFragment purchasesFragment;
    MySalesFragment salesFragment;
    MyPaymentFragment paymentFragment;
    TabLayout tabLayout;
    ViewPager viewPager;
    TextView tvTitle;
    Toolbar mToolbar;
    public POSTLanguageModel.Common_strings commonStrings = new POSTLanguageModel().new Common_strings();
    public POSTLanguageModel.My_activity_screen my_activity_screen = new POSTLanguageModel().new My_activity_screen();
    private String userType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myactivity);
        Utils.freeMemory();
        mToolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        setLanguageValues();
        mCustomProgressDialog = new CustomProgressDialog(this);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
        if (NetworkChangeReceiver.isConnected()) {
            mCustomProgressDialog.showDialog();
            getMyActivity();
        } else {
            Utils.toastMessage(this, commonStrings.getLbl_enable_internet().getName());
        }
    }

    private void getMyActivity() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        if (SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID) != null) {
            apiInterface.getMyActivity(SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID), SessionHandler.getInstance().get(this, AppConstants.Language)).enqueue(new Callback<POSTMyActivity>() {
                @Override
                public void onResponse(Call<POSTMyActivity> call, Response<POSTMyActivity> response) {
                    if (response.body().getCode().equals(200)) {
                        if (response.body().getData().getMy_purchases().size() > 0) {
                            Bundle bundle = new Bundle();
                            bundle.putParcelableArrayList(AppConstants.MY_PURARRAY_KEY, (ArrayList<? extends Parcelable>) response.body().getData().getMy_purchases());
                            purchasesFragment = new MyPurchasesFragment();
                            purchasesFragment.setArguments(bundle);
                        } else {
                            purchasesFragment = new MyPurchasesFragment();
                        }

                        if (response.body().getData().getMy_sale().size() > 0) {
                            Bundle bundle = new Bundle();
                            bundle.putParcelableArrayList(AppConstants.MY_SALEARRAY_KEY, (ArrayList<? extends Parcelable>) response.body().getData().getMy_sale());
                            salesFragment = new MySalesFragment();
                            salesFragment.setArguments(bundle);
                        } else {
                            salesFragment = new MySalesFragment();
                        }

                        if (response.body().getData().getMy_payments().size() > 0) {
                            Bundle bundle = new Bundle();
                            bundle.putString(AppConstants.WALLET_BALANCE, String.valueOf(response.body().getData().getWallet_balance()));
                            bundle.putParcelableArrayList(AppConstants.MY_PAYMENTARRAY_KEY, (ArrayList<? extends Parcelable>) response.body().getData().getMy_payments());
                            paymentFragment = new MyPaymentFragment();
                            paymentFragment.setArguments(bundle);
                        } else {
                            paymentFragment = new MyPaymentFragment();
                        }
                        viewPager.setAdapter(new SectionPagerAdapter(getSupportFragmentManager()));
                        tabLayout.setupWithViewPager(viewPager);
                    } else if (response.body().getCode().equals(Integer.parseInt(AppConstants.INVALID_RESPONSE_CODE))) {
//                    mCustomProgressDialog.dismiss();
                        NetworkAlertDialog.networkAlertDialog(MyActivity.this, "", response.body().getMessage(), null, null);
                    } else if (response.body().getCode().equals(AppConstants.InactiveStatusResponse)) {
                        Utils.createUserInActiceAlert(MyActivity.this, response.body().getMessage());
                    } else {
                        Toast.makeText(MyActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    mCustomProgressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<POSTMyActivity> call, Throwable t) {
                    mCustomProgressDialog.dismiss();
                    Log.i("TAG", t.getMessage());
                    if (t instanceof UnknownHostException || t instanceof HttpException || t instanceof ConnectException || t instanceof SocketTimeoutException || t instanceof SocketException || t instanceof IOException) {
                        //Toast.makeText(MyActivity.this, getString(R.string.err_server_msg), Toast.LENGTH_SHORT).show();
                        NetworkAlertDialog.networkAlertDialog(MyActivity.this, commonStrings.getLbl_network_err().getName(),
                                commonStrings.getLbl_server_err().getName(), myActRunn, null);
                    }
                }
            });
        }
    }

    Runnable myActRunn = new Runnable() {
        @Override
        public void run() {
            getMyActivity();
        }
    };

    private class SectionPagerAdapter extends FragmentPagerAdapter {

        SectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            if (position == 0) {
                if (userType.equalsIgnoreCase("Buyer") && purchasesFragment != null)
                    return purchasesFragment;
                else if (salesFragment != null)
                    return salesFragment;
            } else if (position == 1)
                if (paymentFragment != null)
                    return paymentFragment;
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    if (userType.equalsIgnoreCase("Buyer"))
                        return my_activity_screen.getLbl_purchases().getName();
                    else
                        return my_activity_screen.getLbl_sales().getName();
                case 1:
                    return my_activity_screen.getLbl_payment().getName();
            }
            return null;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Intent gotoMain = new Intent(MyActivity.this, MainActivity.class);
            startActivity(gotoMain);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    public void setLanguageValues() {
        commonStrings = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.COMMONSTRINGS), POSTLanguageModel.Common_strings.class);
        my_activity_screen = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.MYACTIVITY), POSTLanguageModel.My_activity_screen.class);
        tvTitle.setText(my_activity_screen.getLbl_activity().getName());
        userType = SessionHandler.getInstance().get(MyActivity.this, AppConstants.USER_TYPE);
    }

}
