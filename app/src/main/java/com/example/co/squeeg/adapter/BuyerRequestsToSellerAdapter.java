package com.example.co.squeeg.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.co.squeeg.ChooseSellerServiceScreen;
import com.example.co.squeeg.Model.buyer_requests_for_seller.BuyerRequestForSeller;
import com.example.co.squeeg.Model.buyer_requests_for_seller.Currency;
import com.example.co.squeeg.R;
import com.example.co.squeeg.utils.AppConstants;
import com.example.co.squeeg.utils.SessionHandler;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BuyerRequestsToSellerAdapter extends RecyclerView.Adapter<BuyerRequestsToSellerAdapter.MyViewHolder> {

    private Context mContext;
    private List<BuyerRequestForSeller> buyerRequestForSellerList;
    private Currency currency;
    private int size = 0;
    String currencySign = "$";

    public BuyerRequestsToSellerAdapter(Context context, List<BuyerRequestForSeller> buyerRequestForSellerList, Currency currency) {
        this.mContext = context;
        this.buyerRequestForSellerList = buyerRequestForSellerList;
        this.currency = currency;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_buyer_requests_to_seller, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        final BuyerRequestForSeller buyerRequestForSeller = buyerRequestForSellerList.get(position);

        if (currency.getCurrencySign() != null && !currency.getCurrencySign().isEmpty())
            currencySign = currency.getCurrencySign();

        String profileImage = "";
        if (!buyerRequestForSeller.getUserProfileImage().isEmpty()) {
            if (buyerRequestForSeller.getUserProfileImage().contains("https")) {
                profileImage = buyerRequestForSeller.getUserProfileImage();
            } else {
                profileImage = AppConstants.ImageBaseURL + buyerRequestForSeller.getUserProfileImage();
            }
        }

        Picasso.get().load(profileImage).placeholder(R.drawable.ic_no_image_100).into(holder.ivBuyerImage);

        holder.tvBuyerName.setText(buyerRequestForSeller.getUserName());
        holder.tvDate.setText(buyerRequestForSeller.getDate());
        holder.tvDescription.setText(buyerRequestForSeller.getDescription());
        holder.tvNoOffers.setText(buyerRequestForSeller.getNoOfOffers() + " " + mContext.getString(R.string.offers));
        holder.tvDurationDays.setText(buyerRequestForSeller.getDeliveryTime());
        holder.tvBudget.setText(currencySign + buyerRequestForSeller.getBudget());
        holder.tvOffer.setText((position + 1) + "/" + size);

        holder.btnSubmitOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callChooseGigScreen = new Intent(mContext, ChooseSellerServiceScreen.class);

                Gson gson = new Gson();
                String sBuyerRequest = gson.toJson(buyerRequestForSeller);
                SessionHandler.getInstance().save(mContext, AppConstants.BUYER_REQUEST, sBuyerRequest);

                mContext.startActivity(callChooseGigScreen);
            }
        });
    }

    @Override
    public int getItemCount() {
        size = buyerRequestForSellerList.size();
        return size;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivBuyerImage;
        private TextView tvBuyerName,
                tvDate,
                tvDescription,
                tvNoOffers,
                tvDurationDays,
                tvBudget,
                tvOffer;

        private Button btnSubmitOffer;

        public MyViewHolder(View itemView) {
            super(itemView);

            ivBuyerImage = itemView.findViewById(R.id.iv_buyer_picture);

            tvBuyerName = itemView.findViewById(R.id.tv_buyer_name);
            tvDate = itemView.findViewById(R.id.tv_Request_date);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvNoOffers = itemView.findViewById(R.id.tv_no_of_offers);
            tvDurationDays = itemView.findViewById(R.id.tv_duration_days);
            tvBudget = itemView.findViewById(R.id.tv_budget);
            tvOffer = itemView.findViewById(R.id.tv_offer);

            btnSubmitOffer = itemView.findViewById(R.id.btn_submit_offer);
        }
    }
}