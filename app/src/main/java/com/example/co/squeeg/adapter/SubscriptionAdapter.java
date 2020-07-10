package com.example.co.squeeg.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.co.squeeg.MainActivity;
import com.example.co.squeeg.Model.POSTSubscriptionModel;
import com.example.co.squeeg.Model.POSTSubscriptionSuccess;
import com.example.co.squeeg.R;
import com.example.co.squeeg.network.ApiClient;
import com.example.co.squeeg.network.ApiInterface;
import com.example.co.squeeg.payment.StripeSubscriptionPayment;
import com.example.co.squeeg.receiver.NetworkChangeReceiver;
import com.example.co.squeeg.utils.AppConstants;
import com.example.co.squeeg.utils.CustomButton;
import com.example.co.squeeg.utils.CustomProgressDialog;
import com.example.co.squeeg.utils.CustomTextView;
import com.example.co.squeeg.utils.SessionHandler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Hari on 08-01-2019.
 */

public class SubscriptionAdapter extends RecyclerView.Adapter<SubscriptionAdapter.ViewHolder> {


    public List<POSTSubscriptionModel.Subscription> itemsData = new ArrayList<>();
    String subscriptionKey = "";
    Context mContext;
    HashMap<String, String> postDetails = new HashMap<String, String>();
    CustomProgressDialog mCustomProgressDialog;

    public SubscriptionAdapter(Context mContext, List<POSTSubscriptionModel.Subscription> subscriptionData, String subscriptionKey) {
        this.mContext = mContext;
        this.itemsData = subscriptionData;
        this.subscriptionKey = subscriptionKey;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_subscription, parent, false);
        // create ViewHolder
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        mCustomProgressDialog = new CustomProgressDialog(mContext);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {


//        if (!subscriptionKey.equalsIgnoreCase("3") && !itemsData.get(position).getSubscription_rate().equalsIgnoreCase("0")) {
//            holder.ctvSubName.setText(itemsData.get(position).getSubscription_name());
//            holder.ctvCharges.setText(itemsData.get(position).getSubscription_rate());
//            holder.ctvPeriod.setText(" / " + itemsData.get(position).getSubscription_period() + " " + itemsData.get(position).getPeriod_type());
//            holder.ctvNumGigs.setText("Number of Gigs : " + itemsData.get(position).getNo_of_gigs());
//        }
        holder.ctvSubName.setText(itemsData.get(position).getSubscription_name());
        holder.ctvCharges.setText(itemsData.get(position).getSubscription_rate());
        holder.ctvPeriod.setText(" / " + itemsData.get(position).getSubscription_period() + " " + itemsData.get(position).getPeriod_type());
        holder.ctvNumGigs.setText(mContext.getResources().getString(R.string.txt_sub_no_of_gigs) + itemsData.get(position).getNo_of_gigs());
        holder.cbtnActivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!itemsData.get(position).getSubscription_rate().equalsIgnoreCase("0")) {
                    Intent callStripeSubscriptionAct = new Intent(mContext, StripeSubscriptionPayment.class);
                    callStripeSubscriptionAct.putExtra("subscription_id", itemsData.get(position).getId());
                    callStripeSubscriptionAct.putExtra("subscription_name", itemsData.get(position).getSubscription_name());
                    callStripeSubscriptionAct.putExtra("subscription_period", itemsData.get(position).getSubscription_period() + " " + itemsData.get(position).getPeriod_type());
                    callStripeSubscriptionAct.putExtra("subscription_amount", itemsData.get(position).getSubscription_rate());
                    callStripeSubscriptionAct.putExtra("subscription_gigs", itemsData.get(position).getNo_of_gigs());
                    mContext.startActivity(callStripeSubscriptionAct);
                } else {
                    freeSubscription(position);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return itemsData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ctv_sub_name)
        CustomTextView ctvSubName;
        @BindView(R.id.ctv_charges)
        CustomTextView ctvCharges;
        @BindView(R.id.cbtn_activate)
        CustomButton cbtnActivate;
        @BindView(R.id.ctv_num_gigs)
        CustomTextView ctvNumGigs;
        @BindView(R.id.ctv_period)
        CustomTextView ctvPeriod;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public void freeSubscription(int position) {
        if (NetworkChangeReceiver.isConnected()) {
            mCustomProgressDialog.showDialog();
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            postDetails.put("currency_type", "USD");
            postDetails.put("time_zone", SessionHandler.getInstance().get(mContext, AppConstants.TIMEZONE_ID));
            postDetails.put("subscription_id", itemsData.get(position).getId());
            postDetails.put("subscription_name", itemsData.get(position).getSubscription_name());
            postDetails.put("subscription_period", itemsData.get(position).getSubscription_period() + " " + itemsData.get(position).getPeriod_type());
            postDetails.put("subscription_gigs", itemsData.get(position).getNo_of_gigs());
            postDetails.put("subscription_amount", itemsData.get(position).getSubscription_rate());
            postDetails.put("transcation_id", "Free Subscription");
            apiInterface.postSubscriptionPayment(postDetails, SessionHandler.getInstance().get(mContext, AppConstants.TOKEN_ID), SessionHandler.getInstance().get(mContext, AppConstants.Language))
                    .enqueue(new Callback<POSTSubscriptionSuccess>() {
                        @Override
                        public void onResponse(Call<POSTSubscriptionSuccess> call, Response<POSTSubscriptionSuccess> response) {
                            mCustomProgressDialog.dismiss();
                            if (response.body().getCode().equals(200)) {
                                Intent callHome = new Intent(mContext, MainActivity.class);
                                callHome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                callHome.putExtra("From", "MainPage");
                                callHome.putExtra("LoadFragment", 1);
                                mContext.startActivity(callHome);
                            } else {
                                Toast.makeText(mContext, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<POSTSubscriptionSuccess> call, Throwable t) {
                            mCustomProgressDialog.dismiss();
                        }
                    });
        } else {
            Toast.makeText(mContext, mContext.getString(R.string.err_internet_connection), Toast.LENGTH_SHORT).show();
        }

    }

}
