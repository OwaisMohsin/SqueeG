package com.example.co.squeeg.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

import com.example.co.squeeg.DetailGigs;
import com.example.co.squeeg.FavouriteAnimationLib.LikeButton;
import com.example.co.squeeg.FavouriteAnimationLib.OnLikeListener;
import com.example.co.squeeg.Model.GETHomeGigs;
import com.example.co.squeeg.Model.POSTAddFav;
import com.example.co.squeeg.Model.POSTLanguageModel;
import com.example.co.squeeg.Model.POSTRemoveFav;
import com.example.co.squeeg.R;
import com.example.co.squeeg.network.ApiClient;
import com.example.co.squeeg.network.ApiInterface;
import com.example.co.squeeg.receiver.NetworkChangeReceiver;
import com.example.co.squeeg.utils.AppConstants;
import com.example.co.squeeg.utils.SessionHandler;
import com.example.co.squeeg.utils.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HorizontalPopularGigsAdapter extends RecyclerView.Adapter<HorizontalPopularGigsAdapter.MyViewHolder> {
    private final Context mContext;
    private final List<GETHomeGigs.Popular_gigs_list> popular_gigs_list;
    private HashMap<String, String> postDetails;
    public POSTLanguageModel.Common_strings commonStrings = new POSTLanguageModel().new Common_strings();

    public HorizontalPopularGigsAdapter(Context mContext, List<GETHomeGigs.Popular_gigs_list> popular_gigs_list) {
        this.mContext = mContext;
        this.popular_gigs_list = popular_gigs_list;
        postDetails = new HashMap<String, String>();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.horizontal_popular_gigs_item_view, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        setLanguageValues();
        holder.gigsTitle.setText(popular_gigs_list.get(position).getTitle());
        holder.gigsAuthor.setText(popular_gigs_list.get(position).getFullname());
        AppConstants.DOLLAR_SIGN = popular_gigs_list.get(position).getCurrency_sign();
        AppConstants.CURRENCY_TYPE = popular_gigs_list.get(position).getCurrency_type();

        holder.gigsRate.setText(AppConstants.DOLLAR_SIGN + popular_gigs_list.get(position).getGig_price());
        holder.gigsLocation.setText(popular_gigs_list.get(position).getCountry() + "," + popular_gigs_list.get(position).getState_name());


        Picasso.get().load(AppConstants.BASE_URL + popular_gigs_list.get(position).getImage()).placeholder(R.drawable.no_image).into(holder.gigsImages);
        //holder.gigsImages.setImageURI(Uri.parse(AppConstants.BASE_URL + popular_gigs_list.get(position).getImage()));
        holder.gigsRating.setText(popular_gigs_list.get(position).getGig_rating());
        holder.gigsReviewCount.setText("(" + popular_gigs_list.get(position).getGig_usercount() + ")");

        if (SessionHandler.getInstance().get(mContext, AppConstants.TOKEN_ID) != null) {
            if (popular_gigs_list.get(position).getFavourite().equalsIgnoreCase("1")) {
                holder.isSelected = true;
                postDetails.put("gig_id", popular_gigs_list.get(position).getId());
//                holder.favGigsIcons.setImageTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.colorPrimary)));
//                holder.favGigsIcons.setVisibility(View.VISIBLE);
//                holder.favGigsIcons.setImageResource(R.drawable.ic_favorite_filled_24dp);
                holder.likeButton.setLiked(true);

            } else {
                holder.isSelected = false;
                postDetails.put("gig_id", popular_gigs_list.get(position).getId());
//                holder.favGigsIcons.setImageTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.colorPrimary)));
//                holder.favGigsIcons.setVisibility(View.VISIBLE);
//                holder.favGigsIcons.setImageResource(R.drawable.ic_favorite_border_purple_24dp);
                holder.likeButton.setLiked(false);

            }

            holder.favGigsIcons.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (NetworkChangeReceiver.isConnected()) {
                        postDetails.put("gig_id", popular_gigs_list.get(position).getId());
                        //postDetails.put("user_id", SessionHandler.getInstance().get(mContext, AppConstants.TOKEN_ID));
                        if (holder.isSelected) {
                            holder.isSelected = false;
                            removeFavAPI(holder, position);
                        } else {
                            holder.isSelected = true;
                            addFavAPI(holder, position);
                        }
                    } else {
                        Utils.toastMessage(mContext, commonStrings.getLbl_enable_internet().getName());
                    }
                }
            });


            holder.likeButton.setOnLikeListener(new OnLikeListener() {
                @Override
                public void liked(LikeButton likeButton) {
                    postDetails.put("gig_id", popular_gigs_list.get(position).getId());
                    holder.isSelected = true;
                    addFavAPI(holder, position);
                }

                @Override
                public void unLiked(LikeButton likeButton) {
                    postDetails.put("gig_id", popular_gigs_list.get(position).getId());
                    holder.isSelected = false;
                    removeFavAPI(holder, position);
                }
            });

        } else {
            holder.favGigsIcons.setVisibility(View.GONE);
        }
    }

    private void removeFavAPI(final MyViewHolder holder, int position) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.removeFav(postDetails, SessionHandler.getInstance().get(mContext, AppConstants.TOKEN_ID), SessionHandler.getInstance().get(mContext, AppConstants.Language)).enqueue(new Callback<POSTRemoveFav>() {
            @Override
            public void onResponse(Call<POSTRemoveFav> call, Response<POSTRemoveFav> response) {
                if (response.body().getCode().equals(200)) {
//                    Utils.toastMessage(mContext, response.body().getMessage());
                    holder.favGigsIcons.setImageTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.colorPrimary)));
                    holder.favGigsIcons.setImageResource(R.drawable.ic_favorite_border_purple_24dp);
                } else {
                    Utils.toastMessage(mContext, response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<POSTRemoveFav> call, Throwable t) {
                Log.i("TAG", t.getMessage());
            }
        });
    }

    private void addFavAPI(final MyViewHolder holder, final int position) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.addFav(postDetails, SessionHandler.getInstance().get(mContext, AppConstants.TOKEN_ID), SessionHandler.getInstance().get(mContext, AppConstants.Language)).enqueue(new Callback<POSTAddFav>() {
            @Override
            public void onResponse(Call<POSTAddFav> call, Response<POSTAddFav> response) {
                if (response.body().getCode().equals(200)) {
//                    Utils.toastMessage(mContext, response.body().getMessage());
                    holder.favGigsIcons.setImageTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.colorPrimary)));
                    holder.favGigsIcons.setImageResource(R.drawable.ic_favorite_filled_24dp);
                } else {
                    Utils.toastMessage(mContext, response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<POSTAddFav> call, Throwable t) {
                Log.i("TAG", t.getMessage());
            }
        });

    }

    @Override
    public int getItemCount() {
        return popular_gigs_list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        final TextView gigsTitle;
        final TextView gigsAuthor;
        final TextView gigsLocation;
        final TextView gigsReviewCount;
        final TextView gigsRate;
        LikeButton likeButton;

        final ImageView gigsImages;
        TextView gigsRating;
        final ImageView favGigsIcons;
        Boolean isSelected;

        MyViewHolder(View itemView) {
            super(itemView);
            gigsTitle = (TextView) itemView.findViewById(R.id.tv_gigs_title);
            gigsAuthor = (TextView) itemView.findViewById(R.id.author);
            gigsReviewCount = (TextView) itemView.findViewById(R.id.rating_count);
            gigsImages = (ImageView) itemView.findViewById(R.id.iv_gigs_images);
            gigsRate = (TextView) itemView.findViewById(R.id.tv_gigs_rate);
            gigsLocation = (TextView) itemView.findViewById(R.id.tv_loc);
            gigsRating = (TextView) itemView.findViewById(R.id.rating_value);
            favGigsIcons = (ImageView) itemView.findViewById(R.id.iv_fav_gigs);
            likeButton = (LikeButton) itemView.findViewById(R.id.heart_button);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent callGigsDetails = new Intent(mContext, DetailGigs.class);
                    callGigsDetails.putExtra(AppConstants.USER_TYPE,"" );
                    callGigsDetails.putExtra(AppConstants.GIGS_ID, popular_gigs_list.get(getAdapterPosition()).getId());
                    callGigsDetails.putExtra(AppConstants.GIGS_TITLE, popular_gigs_list.get(getAdapterPosition()).getTitle());
                    mContext.startActivity(callGigsDetails);
                }
            });

        }
    }

    public void setLanguageValues() {
        commonStrings = new Gson().fromJson(SessionHandler.getInstance().get(mContext, AppConstants.COMMONSTRINGS), POSTLanguageModel.Common_strings.class);
    }
}
