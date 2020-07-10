package com.example.co.squeeg;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.apache.http.HttpException;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.co.squeeg.Model.GETFooterMenu;
import com.example.co.squeeg.Model.POSTLanguageModel;
import com.example.co.squeeg.adapter.ExpandableListAdapter;
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

public class HelpandSupport extends BaseActivity {

    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    TextView txt_no_information;
    private List<GETFooterMenu.Primary> expandableListTitle = new ArrayList<>();
    private GETFooterMenu.Primary loadData;
    private GETFooterMenu.Sub_menu childData;
    int i, j;
    private Toolbar mToolbar;
    HashMap<Integer, List<GETFooterMenu.Sub_menu>> expandableListDetail = new HashMap<Integer, List<GETFooterMenu.Sub_menu>>();
    private CustomProgressDialog mCustomProgressDialog;
    public POSTLanguageModel.Common_strings commonStrings = new POSTLanguageModel().new Common_strings();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_and_support);
        Utils.freeMemory();
        mToolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        setSupportActionBar(mToolbar);
        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);
        txt_no_information = (TextView) findViewById(R.id.txt_no_info);
        mCustomProgressDialog = new CustomProgressDialog(this);
        setLanguageValues();

        if (NetworkChangeReceiver.isConnected()) {
            mCustomProgressDialog.showDialog();
            getHelpInfo();
        } else {
            Utils.showToast(this);
        }


        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                if (expandableListDetail.get(groupPosition).get(childPosition).getTitle().equalsIgnoreCase("Contact Us")) {
                    Intent i = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(AppConstants.ImageBaseURL + "contact-us"));
                    startActivity(i);
                } else {
                    Intent callViewHelpActivity = new Intent(HelpandSupport.this, ViewHelpandSupport.class);
                    callViewHelpActivity.putExtra("title", expandableListDetail.get(groupPosition).get(childPosition).getTitle());
                    callViewHelpActivity.putExtra("page_desc", expandableListDetail.get(groupPosition).get(childPosition).getPage_desc());
                    startActivity(callViewHelpActivity);
                }

//                Intent callViewHelpActivity = new Intent(HelpandSupport.this, ViewHelpandSupport.class);
//                callViewHelpActivity.putExtra("title", expandableListDetail.get(groupPosition).get(childPosition).getTitle());
//                callViewHelpActivity.putExtra("page_desc", expandableListDetail.get(groupPosition).get(childPosition).getPage_desc());
//                startActivity(callViewHelpActivity);

                return true;
            }
        });
    }


    private void getHelpInfo() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.getHelpInformation(SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID), SessionHandler.getInstance().get(this, AppConstants.Language)).enqueue(new Callback<GETFooterMenu>() {
            @Override
            public void onResponse(Call<GETFooterMenu> call, Response<GETFooterMenu> response) {
                if (response.body().getCode().equals(200)) {

                    for (i = 0; i < response.body().getPrimary().size(); i++) {
                        loadData = new GETFooterMenu.Primary();
                        loadData.setMain_menu(response.body().getPrimary().get(i).getMain_menu());
                        expandableListTitle.add(i, loadData);
                        List<GETFooterMenu.Sub_menu> child = new ArrayList<GETFooterMenu.Sub_menu>();
                        for (j = 0; j < response.body().getPrimary().get(i).getSub_menu().size(); j++) {
                            child.add(response.body().getPrimary().get(i).getSub_menu().get(j));

                        }
                        expandableListDetail.put(i, child);

                    }
                    expandableListAdapter = new ExpandableListAdapter(HelpandSupport.this, expandableListTitle, expandableListDetail);
                    expandableListView.setAdapter(expandableListAdapter);
                    mCustomProgressDialog.dismiss();
                } else if (response.body().getCode().equals(Integer.parseInt(AppConstants.INVALID_RESPONSE_CODE))) {
                    mCustomProgressDialog.dismiss();
                    NetworkAlertDialog.networkAlertDialog(HelpandSupport.this, "", response.body().getMessage(), null, null);
                } else {
                    txt_no_information.setVisibility(View.VISIBLE);
                    txt_no_information.setText(response.body().getMessage());
                    expandableListView.setVisibility(View.GONE);
                    mCustomProgressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<GETFooterMenu> call, Throwable t) {
                mCustomProgressDialog.dismiss();
                if (t instanceof UnknownHostException || t instanceof HttpException || t instanceof ConnectException) {
                    Toast.makeText(HelpandSupport.this, commonStrings.getLbl_server_prblm().getName(), Toast.LENGTH_SHORT).show();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    public void setLanguageValues() {
        commonStrings = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.COMMONSTRINGS), POSTLanguageModel.Common_strings.class);

    }
}
