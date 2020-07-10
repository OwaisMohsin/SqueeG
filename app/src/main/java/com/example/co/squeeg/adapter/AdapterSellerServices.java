package com.example.co.squeeg.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.co.squeeg.Model.seller_services.SellerService;
import com.example.co.squeeg.R;
import com.example.co.squeeg.SellerOfferScreen;
import com.example.co.squeeg.utils.AppConstants;
import com.example.co.squeeg.utils.SessionHandler;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterSellerServices extends RecyclerView.Adapter<AdapterSellerServices.ViewHolder> {

    private Context mContext;
    private List<SellerService> sellerServiceList;

    public AdapterSellerServices(Context mContext, List<SellerService> sellerServiceList) {
        this.mContext = mContext;
        this.sellerServiceList = sellerServiceList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_seller_services, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        SellerService sellerService = sellerServiceList.get(position);

        String sServiceImage = "";
        if (!sellerService.getImagePath().isEmpty()) {
            sServiceImage = AppConstants.ImageBaseURL + sellerService.getImagePath();
        }

        Picasso.get().load(sServiceImage).placeholder(R.drawable.ic_no_image_100).into(holder.ivServiceImage);

        holder.tvTitle.setText(sellerService.getTitle());
        holder.tvDescription.setText(sellerService.getDescription());
    }

    @Override
    public int getItemCount() {
        return sellerServiceList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivServiceImage;
        private TextView tvTitle,
                tvDescription;

        public ViewHolder(View itemView) {
            super(itemView);

            ivServiceImage = itemView.findViewById(R.id.iv_service);

            tvTitle = itemView.findViewById(R.id.tv_service_title);
            tvDescription = itemView.findViewById(R.id.tv_service_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent callSellerOfferScreen = new Intent(mContext, SellerOfferScreen.class);
                    Gson gson = new Gson();
                    String sSellerService = gson.toJson(sellerServiceList.get(getAdapterPosition()));
                    SessionHandler.getInstance().save(mContext, AppConstants.SELLER_OFFER, sSellerService);
                    mContext.startActivity(callSellerOfferScreen);
                }
            });
        }
    }
}