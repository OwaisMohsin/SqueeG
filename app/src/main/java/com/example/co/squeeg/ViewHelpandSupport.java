package com.example.co.squeeg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.example.co.squeeg.utils.Utils;

/**
 * Created by Hari on 23-03-2018.
 */

public class ViewHelpandSupport extends BaseActivity {


    Toolbar mToolbar;
    private TextView txtPageDescription, toolbarTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.freeMemory();
        setContentView(R.layout.activity_view_help);
        mToolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        setSupportActionBar(mToolbar);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        txtPageDescription = (TextView) findViewById(R.id.tv_page_desc);
        toolbarTitle.setText(getIntent().getStringExtra("title"));
        txtPageDescription.setText(Html.fromHtml(getIntent().getStringExtra("page_desc")));

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
}
