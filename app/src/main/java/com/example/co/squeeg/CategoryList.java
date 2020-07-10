package com.example.co.squeeg;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.apache.http.HttpException;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.List;

import com.example.co.squeeg.Model.GETCategory;
import com.example.co.squeeg.Model.POSTLanguageModel;
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

public class CategoryList extends BaseActivity {

    private ListView categoryLists;
    private CategoryListAdapter aCategoryListAdapter;
    private CustomProgressDialog mCustomProgressDialog;
    private Toolbar toolbar;
    private String token;
    public POSTLanguageModel.Common_strings commonStrings = new POSTLanguageModel().new Common_strings();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_category);
        mCustomProgressDialog = new CustomProgressDialog(this);
        initLayouts();
        setLanguageValues();
        if (SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID) != null) {
            token = SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID);
        } else {
            token = AppConstants.GUEST_TOKEN;
        }
        if (NetworkChangeReceiver.isConnected()) {
            mCustomProgressDialog.showDialog();
            getCategoryLists();
        } else {
            showToast();
        }
    }

    private void getCategoryLists() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.getCategories(token, SessionHandler.getInstance().get(this, AppConstants.Language)).enqueue(new Callback<GETCategory>() {
            @Override
            public void onResponse(Call<GETCategory> call, Response<GETCategory> response) {

                if (response.body().getCode().equals(200)) {
                    if (response.body().getPrimary().size() > 0) {
                        aCategoryListAdapter = new CategoryListAdapter(CategoryList.this, response.body().getPrimary());
                        categoryLists.setAdapter(aCategoryListAdapter);
                    } else {
                        Utils.toastMessage(CategoryList.this, response.body().getMessage());
                    }

                } else if (response.body().getCode().equals(AppConstants.INVALID_RESPONSE_CODE)) {
                    mCustomProgressDialog.dismiss();
                    NetworkAlertDialog.networkAlertDialog(CategoryList.this, "", response.body().getMessage(), null, null);
                } else {
                    Utils.toastMessage(CategoryList.this, response.body().getMessage());
                }
                mCustomProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<GETCategory> call, Throwable t) {
                Log.i("TAG", t.getMessage());
                mCustomProgressDialog.dismiss();
                if (t instanceof UnknownHostException || t instanceof HttpException || t instanceof ConnectException) {
                    //Toast.makeText(CategoryList.this, getString(R.string.err_server_msg), Toast.LENGTH_SHORT).show();
                    NetworkAlertDialog.networkAlertDialog(CategoryList.this, commonStrings.getLbl_network_err().getName(),
                            commonStrings.getLbl_server_err().getName(), categoryRunn, null);
                }
            }
        });
    }

    Runnable categoryRunn = new Runnable() {
        @Override
        public void run() {
            //TODO: API call
            getCategoryLists();
        }
    };

    private void initLayouts() {
        toolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        setSupportActionBar(toolbar);

        categoryLists = (ListView) findViewById(R.id.lv_category_list);
    }

    private class CategoryListAdapter extends BaseAdapter {
        final Context mContext;
        final List<GETCategory.Primary> primary;
        final LayoutInflater mInflater;

        CategoryListAdapter(Context mContext, List<GETCategory.Primary> primary) {
            this.mContext = mContext;
            this.primary = primary;
            mInflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return primary.size();
        }

        @Override
        public GETCategory.Primary getItem(int position) {
            return primary.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder mHolder;

            if (convertView == null) {
                mHolder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.adapter_settings, null);
                mHolder.categoryNames = (TextView) convertView.findViewById(R.id.tv_settings_items);
                convertView.setTag(mHolder);
            } else {
                mHolder = (ViewHolder) convertView.getTag();
            }

            mHolder.categoryNames.setText(getItem(position).getName());

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getItem(position).getSubcategory().equalsIgnoreCase("1")) {
                        Intent callSubcategory = new Intent(mContext, SubCategoryList.class);
                        callSubcategory.putExtra(AppConstants.CAT_ID, getItem(position).getCid());
                        callSubcategory.putExtra(AppConstants.CAT_NAME, getItem(position).getName());
                        callSubcategory.putExtra(AppConstants.SUB_ID, getItem(position).getSubcategory());
                        startActivity(callSubcategory);
                    } else {
                        Intent callSubcategory = new Intent(mContext, GigsLists.class);
                        callSubcategory.putExtra(AppConstants.CAT_ID, getItem(position).getCid());
                        startActivity(callSubcategory);
                    }
                }
            });

            return convertView;
        }

        class ViewHolder {
            TextView categoryNames;
        }
    }

    private void showToast() {
        Toast.makeText(this, commonStrings.getLbl_enable_internet().getName(), Toast.LENGTH_SHORT).show();
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
        toolbar.setTitle(commonStrings.getLbl_categories().getName());
    }
}
