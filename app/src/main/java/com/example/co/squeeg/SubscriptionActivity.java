package com.example.co.squeeg;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.co.squeeg.Model.POSTSubscriptionModel;
import com.example.co.squeeg.adapter.SubscriptionAdapter;
import com.example.co.squeeg.utils.CustomTextView;

/**
 * Created by Hari on 08-01-2019.
 */

public class SubscriptionActivity extends AppCompatActivity {


    @BindView(R.id.rv_subscription)
    RecyclerView rvSubscription;
    LinearLayoutManager mLayoutManager;
    List<POSTSubscriptionModel.Subscription> subscriptionData = new ArrayList<>();
    List<POSTSubscriptionModel.Subscription> subscriptionDataItems = new ArrayList<>();
    SubscriptionAdapter subscriptionAdapter;
    @BindView(R.id.tb_toolbar)
    Toolbar tbToolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.ctv_skip)
    CustomTextView ctvSkip;
    String subscriptionKey = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);
        ButterKnife.bind(this);
        setSupportActionBar(tbToolbar);
        mLayoutManager = new GridLayoutManager(this, 2);
        rvSubscription.setLayoutManager(mLayoutManager);
        Bundle bundle = this.getIntent().getExtras();
        subscriptionKey = getIntent().getStringExtra("subscription_type");
        subscriptionData = (List<POSTSubscriptionModel.Subscription>) bundle.getSerializable("subscription_data");


        if (subscriptionKey.equalsIgnoreCase("3")) {
            for (int i = 0; i < subscriptionData.size(); i++) {
                if (!subscriptionData.get(i).getSubscription_rate().equalsIgnoreCase("0")) {
                    subscriptionDataItems.add(subscriptionData.get(i));
                }
            }
        } else {
            subscriptionDataItems = subscriptionData;
        }
        subscriptionAdapter = new SubscriptionAdapter(this, subscriptionDataItems, subscriptionKey);
        rvSubscription.setAdapter(subscriptionAdapter);
    }


    @OnClick(R.id.ctv_skip)
    public void onViewClicked() {
        finish();
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
        Intent callMainAct = new Intent(this, MainActivity.class);
        callMainAct.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        callMainAct.putExtra("From", "MainPage");
        callMainAct.putExtra("LoadFragment", 0);
        startActivity(callMainAct);

    }
}
