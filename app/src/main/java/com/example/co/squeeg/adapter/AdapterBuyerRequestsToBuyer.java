package com.example.co.squeeg.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.co.squeeg.Model.buyer_requests_for_buyer.BuyerRequestsForBuyer;
import com.example.co.squeeg.R;
import com.example.co.squeeg.SellerOffersToBuyerScreen;
import com.example.co.squeeg.utils.AppConstants;
import com.example.co.squeeg.utils.SessionHandler;
import com.google.gson.Gson;

import java.util.List;

public class AdapterBuyerRequestsToBuyer extends RecyclerView.Adapter<AdapterBuyerRequestsToBuyer.ViewHolder> {

    private Context mContext;
    private List<BuyerRequestsForBuyer> buyerRequestForBuyerLists;

    public AdapterBuyerRequestsToBuyer(Context mContext, List<BuyerRequestsForBuyer> buyerRequestForBuyerLists) {
        this.mContext = mContext;
        this.buyerRequestForBuyerLists = buyerRequestForBuyerLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_buyer_requests_to_buyer, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        BuyerRequestsForBuyer buyerRequestForBuyer = buyerRequestForBuyerLists.get(position);

//        holder.tvNoOfOffers.setText("Offers: " + buyerRequest.getNoOfOffers());
        holder.tvDate.setText(buyerRequestForBuyer.getDate());
        holder.tvDescription.setText(buyerRequestForBuyer.getDescription());
    }

    @Override
    public int getItemCount() {
        return buyerRequestForBuyerLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvDate,
                tvDescription;

        public ViewHolder(View itemView) {
            super(itemView);

//            tvNoOfOffers = itemView.findViewById(R.id.tv_no_of_offers);
            tvDate = itemView.findViewById(R.id.tv_request_date);
            tvDescription = itemView.findViewById(R.id.tv_request_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent callSellerOffersScreen = new Intent(mContext, SellerOffersToBuyerScreen.class);
                    Gson gson = new Gson();
                    String sBuyerRequest = gson.toJson(buyerRequestForBuyerLists.get(getAdapterPosition()));
                    SessionHandler.getInstance().save(mContext, AppConstants.BUYER_REQUEST, sBuyerRequest);
                    mContext.startActivity(callSellerOffersScreen);
                }
            });
        }
    }
}