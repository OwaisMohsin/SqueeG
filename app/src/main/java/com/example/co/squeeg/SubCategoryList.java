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

import java.util.HashMap;
import java.util.List;

import com.example.co.squeeg.Model.POSTLanguageModel;
import com.example.co.squeeg.Model.POSTSubCategory;
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

public class SubCategoryList extends BaseActivity {

    private ListView categoryLists;
    private Toolbar toolbar;
    TextView tv_noGigs;
    private CategoryListAdapter aCategoryListAdapter;
    private CustomProgressDialog mCustomProgressDialog;
    private String category_id = "", category_name = "", user_id = "";
    private HashMap<String, String> postCategoryDetails = new HashMap<String, String>();
    private TextView toolTitle;
    String token = "";
    public POSTLanguageModel.Common_strings commonStrings = new POSTLanguageModel().new Common_strings();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_category);
        Utils.freeMemory();
        category_id = getIntent().getStringExtra(AppConstants.CAT_ID);
        category_name = getIntent().getStringExtra(AppConstants.CAT_NAME);

        mCustomProgressDialog = new CustomProgressDialog(this);
        initLayouts();

        if (SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID) != null) {
            token = SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID);
        } else {
            token = AppConstants.GUEST_TOKEN;
        }

        if (NetworkChangeReceiver.isConnected()) {
            mCustomProgressDialog.showDialog();
            if (!category_id.isEmpty()) {
                postCategoryDetails.put("category_id", category_id);
            }
            /*if (SessionHandler.getInstance().get(SubCategoryList.this, AppConstants.TOKEN_ID) != null) {
                postCategoryDetails.put("user_id", user_id);
            }*/
            postCategoryDetails.put("services", "All");
            getCategoryLists();
        } else {
            showToast();
        }
    }

    private void getCategoryLists() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.getSubCategory(postCategoryDetails, token, SessionHandler.getInstance().get(this, AppConstants.Language)).enqueue(new Callback<POSTSubCategory>() {
            @Override
            public void onResponse(Call<POSTSubCategory> call, Response<POSTSubCategory> response) {
                if (response.body().getCode().equals(200)) {
                    if (response.body().getData().size() > 0) {
                        aCategoryListAdapter = new CategoryListAdapter(SubCategoryList.this, response.body().getData());
                        categoryLists.setAdapter(aCategoryListAdapter);
                    } else {
                        tv_noGigs.setVisibility(View.VISIBLE);
                        categoryLists.setVisibility(View.GONE);
                    }

                } else if (response.body().getCode().equals(Integer.parseInt(AppConstants.INVALID_RESPONSE_CODE))) {
//                    mCustomProgressDialog.dismiss();
                    NetworkAlertDialog.networkAlertDialog(SubCategoryList.this, "", response.body().getMessage(), null, null);
                } else if (response.body().getCode().equals(AppConstants.InactiveStatusResponse)) {
                    Utils.createUserInActiceAlert(SubCategoryList.this, response.body().getMessage());
                } else {
                    Utils.toastMessage(SubCategoryList.this, response.body().getMessage());
                }
                mCustomProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<POSTSubCategory> call, Throwable t) {
                Log.i("TAG", t.getMessage());
                mCustomProgressDialog.dismiss();
            }
        });
    }


    private void initLayouts() {
        toolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        setSupportActionBar(toolbar);
        categoryLists = (ListView) findViewById(R.id.lv_category_list);
        tv_noGigs = (TextView) findViewById(R.id.tv_no_gigs);
        toolTitle = (TextView) findViewById(R.id.tool_title);
        toolTitle.setText(category_name);
        setLangugeValues();

    }

    private class CategoryListAdapter extends BaseAdapter {
        final Context mContext;
        final List<POSTSubCategory.Datum> primary;
        final LayoutInflater mInflater;

        CategoryListAdapter(Context mContext, List<POSTSubCategory.Datum> primary) {
            this.mContext = mContext;
            this.primary = primary;
            mInflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return primary.size();
        }

        @Override
        public POSTSubCategory.Datum getItem(int position) {
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
                    Intent callSubCategory = new Intent(mContext, GigsLists.class);
                    callSubCategory.putExtra(AppConstants.CAT_ID, category_id);
                    callSubCategory.putExtra(AppConstants.SUB_CAT_ID, getItem(position).getCid());
                    startActivity(callSubCategory);
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

    public void setLangugeValues() {
        commonStrings = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.COMMONSTRINGS), POSTLanguageModel.Common_strings.class);
        tv_noGigs.setText(commonStrings.getLbl_no_gigs_available().getName());
    }
}
