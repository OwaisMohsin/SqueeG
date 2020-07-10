package com.example.co.squeeg.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;


import java.util.HashMap;
import java.util.List;

import com.example.co.squeeg.DetailGigs;
import com.example.co.squeeg.FavouriteAnimationLib.LikeButton;
import com.example.co.squeeg.FavouriteAnimationLib.OnAnimationEndListener;
import com.example.co.squeeg.FavouriteAnimationLib.OnLikeListener;
import com.example.co.squeeg.Favourites;
import com.example.co.squeeg.Model.POSTFavGigs;
import com.example.co.squeeg.Model.POSTLanguageModel;
import com.example.co.squeeg.Model.POSTRemoveFav;
import com.example.co.squeeg.R;
import com.example.co.squeeg.network.ApiClient;
import com.example.co.squeeg.network.ApiInterface;
import com.example.co.squeeg.receiver.NetworkChangeReceiver;
import com.example.co.squeeg.utils.AppConstants;
import com.example.co.squeeg.utils.CustomProgressDialog;
import com.example.co.squeeg.utils.SessionHandler;
import com.example.co.squeeg.utils.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.MyViewHolder> {
    private final Context mContext;
    private List<POSTFavGigs.Favourite_detail> fav_gigs;
    private CustomProgressDialog mCustomProgressDialog;
    private HashMap<String, String> postDetails;
    public POSTLanguageModel.Common_strings commonStrings = new POSTLanguageModel().new Common_strings();

    public FavouriteAdapter(Context mContext, List<POSTFavGigs.Favourite_detail> fav_gigs) {
        this.mContext = mContext;
        this.fav_gigs = fav_gigs;
        postDetails = new HashMap<String, String>();
        mCustomProgressDialog = new CustomProgressDialog(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.horizontal_gigs_item_view, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        setLanguageValues();
        holder.gigsTitle.setText(fav_gigs.get(position).getTitle());
        holder.gigsAuthor.setText(fav_gigs.get(position).getFullname());
        holder.gigsRate.setText(AppConstants.DOLLAR_SIGN + fav_gigs.get(position).getGig_price());
        holder.gigsLocation.setText(fav_gigs.get(position).getCountry() + "," + fav_gigs.get(position).getState_name());
        Picasso.get().load(AppConstants.BASE_URL + fav_gigs.get(position).getImage()).placeholder(R.drawable.ic_app_icons).into(holder.gigsImages);
        //holder.gigsImages.setImageURI(Uri.parse(AppConstants.BASE_URL + fav_gigs.get(position).getImage()));
        holder.gigsRating.setRating(Float.parseFloat(fav_gigs.get(position).getGig_rating()));
        holder.gigsReviewCount.setText("(" + fav_gigs.get(position).getGig_usercount() + ")");

        holder.likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {

            }

            @Override
            public void unLiked(LikeButton likeButton) {
                if (NetworkChangeReceiver.isConnected()) {
                    mCustomProgressDialog.showDialog();
                    postDetails.put("gig_id", fav_gigs.get(position).getId());
                    postDetails.put("user_id", SessionHandler.getInstance().get(mContext, AppConstants.TOKEN_ID));
                    removeFavAPI(holder, position);
                } else {
                    Utils.toastMessage(mContext, commonStrings.getLbl_enable_internet().getName());
                }
            }
        });
        holder.likeButton.setOnAnimationEndListener(new OnAnimationEndListener() {
            @Override
            public void onAnimationEnd(LikeButton likeButton) {

            }
        });
        if (fav_gigs.get(position).getFavourite().equalsIgnoreCase("1")) {
//            holder.favGigsIcons.setImageTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.colorPrimary)));
//            holder.favGigsIcons.setImageResource(R.drawable.ic_favorite_filled_24dp);
//            holder.favGigsIcons.setVisibility(View.VISIBLE);
            holder.likeButton.setVisibility(View.VISIBLE);
            holder.likeButton.setLiked(true);
        }
        holder.favGigsIcons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NetworkChangeReceiver.isConnected()) {
                    mCustomProgressDialog.showDialog();
                    postDetails.put("gig_id", fav_gigs.get(position).getId());
                    postDetails.put("user_id", SessionHandler.getInstance().get(mContext, AppConstants.TOKEN_ID));
                    removeFavAPI(holder, position);
                } else {
                    Utils.toastMessage(mContext, commonStrings.getLbl_enable_internet().getName());
                }
            }
        });
    }

    private void removeFavAPI(final MyViewHolder holder, final int position) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.removeFav(postDetails, SessionHandler.getInstance().get(mContext, AppConstants.TOKEN_ID), SessionHandler.getInstance().get(mContext, AppConstants.Language)).enqueue(new Callback<POSTRemoveFav>() {
            @Override
            public void onResponse(Call<POSTRemoveFav> call, Response<POSTRemoveFav> response) {
                if (response.body().getCode().equals(200)) {
                    Utils.toastMessage(mContext, response.body().getMessage());
                    ((Favourites) mContext).getFavRemGigIds(true);
                    fav_gigs.remove(position);
                    notifyDataSetChanged();
                    if (fav_gigs.size() == 0) {
                        ((Favourites) mContext).finish();

                    }
                } else {
                    Utils.toastMessage(mContext, response.body().getMessage());
                }

                mCustomProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<POSTRemoveFav> call, Throwable t) {
                Log.i("TAG", t.getMessage());
                mCustomProgressDialog.dismiss();
            }
        });
    }


    @Override
    public int getItemCount() {
        return fav_gigs.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        final TextView gigsTitle;
        final TextView gigsAuthor;
        final TextView gigsLocation;
        final TextView gigsReviewCount;
        final TextView gigsRate;
        final ImageView gigsImages;
        RatingBar gigsRating;
        final ImageView favGigsIcons;
        LikeButton likeButton;

        MyViewHolder(View itemView) {
            super(itemView);
            gigsTitle = (TextView) itemView.findViewById(R.id.tv_gigs_title);
            gigsAuthor = (TextView) itemView.findViewById(R.id.author);
            gigsReviewCount = (TextView) itemView.findViewById(R.id.rating_count);
            gigsImages = (ImageView) itemView.findViewById(R.id.iv_gigs_images);
            gigsRate = (TextView) itemView.findViewById(R.id.tv_gigs_rate);
            gigsLocation = (TextView) itemView.findViewById(R.id.tv_loc);
            gigsRating = (RatingBar) itemView.findViewById(R.id.rating_gigs);
            favGigsIcons = (ImageView) itemView.findViewById(R.id.iv_fav_gigs);
            likeButton = (LikeButton) itemView.findViewById(R.id.heart_button);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent callGigsDetails = new Intent(mContext, DetailGigs.class);
                    callGigsDetails.putExtra(AppConstants.USER_TYPE,"" );
                    callGigsDetails.putExtra(AppConstants.GIGS_ID, fav_gigs.get(getAdapterPosition()).getId());
                    callGigsDetails.putExtra(AppConstants.GIGS_TITLE, fav_gigs.get(getAdapterPosition()).getTitle());
                    mContext.startActivity(callGigsDetails);
                }
            });
        }
    }

    public void setLanguageValues() {
        commonStrings = new Gson().fromJson(SessionHandler.getInstance().get(mContext, AppConstants.COMMONSTRINGS), POSTLanguageModel.Common_strings.class);
    }

}
