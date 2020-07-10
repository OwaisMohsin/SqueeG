package com.example.co.squeeg.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.co.squeeg.DetailGigs;
import com.example.co.squeeg.Model.buyer_requests_for_seller.Currency;
import com.example.co.squeeg.Model.seller_offers_for_buyer.SellerOffersForBuyer;
import com.example.co.squeeg.R;
import com.example.co.squeeg.utils.AppConstants;

import java.util.List;

public class AdapterSellerOffersToBuyer extends RecyclerView.Adapter<AdapterSellerOffersToBuyer.MyViewHolder> {

    private Context mContext;
    private List<SellerOffersForBuyer> sellerOffersForBuyerList;
    private Currency currency;
    private int size = 0;
    String currencySign = "$";

    public AdapterSellerOffersToBuyer(Context context, List<SellerOffersForBuyer> sellerOffersForBuyerList, Currency currency) {
        this.mContext = context;
        this.sellerOffersForBuyerList = sellerOffersForBuyerList;
        this.currency = currency;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_seller_offer_to_buyer, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        final SellerOffersForBuyer sellerOffersForBuyer = sellerOffersForBuyerList.get(position);

        if (currency.getCurrencySign() != null && !currency.getCurrencySign().isEmpty())
            currencySign = currency.getCurrencySign();

//        String profileImage = "";
//        if (!sellerOffersForBuyer.getUserProfileImage().isEmpty()) {
//            if (sellerOffersForBuyer.getUserProfileImage().contains("https")) {
//                profileImage = sellerOffersForBuyer.getUserProfileImage();
//            } else {
//                profileImage = AppConstants.ImageBaseURL + sellerOffersForBuyer.getUserProfileImage();
//            }
//        }

//        Picasso.get().load(profileImage).placeholder(R.drawable.ic_no_image_100).into(holder.ivSellerImage);
//
//        holder.tvSellerName.setText(sellerOffersForBuyer.getUserName());
//        holder.tvDate.setText(sellerOffersForBuyer.getDate());
//        holder.tvDescription.setText(sellerOffersForBuyer.getDescription());
//        holder.tvRevisions.setText(sellerOffersForBuyer.getNoOfOffers() + " " + mContext.getString(R.string.offers));
//        holder.tvDurationDays.setText(sellerOffersForBuyer.getDeliveryTime());
//        holder.tvBudget.setText(currencySign + sellerOffersForBuyer.getBudget());
//        holder.tvOffer.setText((position + 1) + "/" + size);

        holder.btnAcceptOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                callAPi
            }
        });

        holder.cvSellerGig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callGigsDetails = new Intent(mContext, DetailGigs.class);
                callGigsDetails.putExtra(AppConstants.USER_TYPE, "Buyer");
//                callGigsDetails.putExtra(AppConstants.GIGS_ID, );
//                callGigsDetails.putExtra(AppConstants.GIGS_TITLE, );
                mContext.startActivity(callGigsDetails);
            }
        });
    }

    @Override
    public int getItemCount() {
        size = sellerOffersForBuyerList.size();
        return size;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private CardView cvSellerGig;
        private ImageView ivSellerImage,
                ivService;
        private TextView tvSellerName,
                tvServiceTitle,
                tvServiceDescription,
                tvDate,
                tvDescription,
                tvRevisions,
                tvDurationDays,
                tvBudget,
                tvOffer;

        private Button btnAcceptOffer;

        public MyViewHolder(View itemView) {
            super(itemView);

            cvSellerGig = itemView.findViewById(R.id.cv_seller_gig);

            ivSellerImage = itemView.findViewById(R.id.iv_seller_picture);
            ivService = itemView.findViewById(R.id.iv_service);

            tvSellerName = itemView.findViewById(R.id.tv_seller_name);
            tvServiceTitle = itemView.findViewById(R.id.tv_service_title);
            tvServiceDescription = itemView.findViewById(R.id.tv_service_description);
            tvDate = itemView.findViewById(R.id.tv_Request_date);
            tvDescription = itemView.findViewById(R.id.tv_description);
            tvRevisions = itemView.findViewById(R.id.tv_revision);
            tvDurationDays = itemView.findViewById(R.id.tv_duration_days);
            tvBudget = itemView.findViewById(R.id.tv_budget);
            tvOffer = itemView.findViewById(R.id.tv_offer);

            btnAcceptOffer = itemView.findViewById(R.id.btn_accept_offer);
        }
    }
}