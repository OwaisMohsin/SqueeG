package com.example.co.squeeg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by user5 on 12-01-2018.
 */

public class AppInformation extends BaseActivity {


    private TextView inputInformation, inputTitle;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        mToolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        setSupportActionBar(mToolbar);
        inputInformation = (TextView) findViewById(R.id.tv_about_us);
        inputTitle = (TextView) findViewById(R.id.toolbar_text);
        //getInfo();
        if (getIntent().getStringExtra("id").equalsIgnoreCase("1")) {
            inputTitle.setText("FAQs");
        }else {
            inputTitle.setText("About");
        }


    }

   /* public void getInfo() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.getInformation().enqueue(new Callback<GETFooterInformation>() {
            @Override
            public void onResponse(Call<GETFooterInformation> call, Response<GETFooterInformation> response) {
                if (response.body().getMessage().equalsIgnoreCase("SUCCESS")) {
                    if (getIntent().getStringExtra("id").equalsIgnoreCase("1")) {
                        inputInformation.setText(Html.fromHtml(response.body().getPrimary().get(6).getPageDesc()));
                    } else if (getIntent().getStringExtra("id").equalsIgnoreCase("2")) {
                        inputInformation.setText(Html.fromHtml(response.body().getPrimary().get(2).getPageDesc()));
                    }
                }
            }

            @Override
            public void onFailure(Call<GETFooterInformation> call, Throwable t) {
                Log.i("TAG", t.getMessage());
            }
        });
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
