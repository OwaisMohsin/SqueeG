package com.example.co.squeeg.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

import com.example.co.squeeg.DetailGigs;
import com.example.co.squeeg.Model.GETAllGigs;
import com.example.co.squeeg.Model.POSTAddFav;
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

public class AllGigsListsAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<GETAllGigs.Category_detail> data;
    private final LayoutInflater mInflater;
    private HashMap<String, String> postDetails;

    public AllGigsListsAdapter(Context mContext, List<GETAllGigs.Category_detail> data) {
        this.mContext = mContext;
        this.data = data;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        postDetails = new HashMap<String, String>();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public GETAllGigs.Category_detail getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder mHolder;
        if (convertView == null) {
            mHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.adapter_all_gigs, null);
            mHolder.gigsTitle = (TextView) convertView.findViewById(R.id.tv_gigs_title);
            mHolder.gigsAuthor = (TextView) convertView.findViewById(R.id.author);
            mHolder.gigsReviewCount = (TextView) convertView.findViewById(R.id.rating_count);
            mHolder.gigsImages = (ImageView) convertView.findViewById(R.id.iv_gigs_images);
            mHolder.gigsRate = (TextView) convertView.findViewById(R.id.tv_gigs_rate);
            mHolder.ratingBar = (TextView) convertView.findViewById(R.id.rating_value);
            mHolder.gigsLocation = (TextView) convertView.findViewById(R.id.tv_loc);
            mHolder.favGigsIcons = (ImageView) convertView.findViewById(R.id.iv_fav_gigs);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }

        mHolder.gigsTitle.setText(data.get(position).getTitle());
        mHolder.gigsAuthor.setText(data.get(position).getFullname());
        AppConstants.DOLLAR_SIGN = data.get(position).getCurrency_sign();
        mHolder.gigsRate.setText(AppConstants.DOLLAR_SIGN + data.get(position).getGig_price());
        mHolder.gigsLocation.setText(data.get(position).getCountry() + "," + data.get(position).getState_name());
        //mHolder.gigsImages.setImageURI(Uri.parse(AppConstants.BASE_URL + data.get(position).getImage()));
        Picasso.get().load(AppConstants.BASE_URL + data.get(position).getImage()).placeholder(R.drawable.no_image).into(mHolder.gigsImages);
        mHolder.ratingBar.setText(data.get(position).getGig_rating());
        mHolder.gigsReviewCount.setText("(" + data.get(position).getGig_usercount() + ")");

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callGigsDetails = new Intent(mContext, DetailGigs.class);
                callGigsDetails.putExtra(AppConstants.GIGS_ID, data.get(position).getId());
                callGigsDetails.putExtra(AppConstants.USER_TYPE,"" );
                callGigsDetails.putExtra(AppConstants.GIGS_TITLE, data.get(position).getTitle());
                mContext.startActivity(callGigsDetails);
            }
        });

        if (SessionHandler.getInstance().get(mContext, AppConstants.TOKEN_ID) != null) {
            if (data.get(position).getFavourite().equalsIgnoreCase("1")) {
                mHolder.isSelected = true;
                mHolder.favGigsIcons.setVisibility(View.VISIBLE);
                mHolder.favGigsIcons.setImageTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.colorPrimary)));
                mHolder.favGigsIcons.setImageResource(R.drawable.ic_favorite_filled_24dp);
            } else {
                mHolder.isSelected = false;
                mHolder.favGigsIcons.setVisibility(View.VISIBLE);
                mHolder.favGigsIcons.setImageTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.colorPrimary)));
                mHolder.favGigsIcons.setImageResource(R.drawable.ic_favorite_border_purple_24dp);

            }

            mHolder.favGigsIcons.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (NetworkChangeReceiver.isConnected()) {
                        postDetails.put("gig_id", data.get(position).getId());
                        //postDetails.put("user_id", SessionHandler.getInstance().get(mContext, AppConstants.TOKEN_ID));
                        if (mHolder.isSelected) {
                            mHolder.isSelected = false;
                            removeFavAPI(mHolder, position);
                        } else {
                            mHolder.isSelected = true;
                            addFavAPI(mHolder, position);
                        }
                    } else {
                        Utils.toastMessage(mContext, mContext.getString(R.string.err_internet_connection));
                    }
                }
            });


        } else {
            mHolder.favGigsIcons.setVisibility(View.GONE);
        }


        return convertView;
    }

    private void removeFavAPI(final ViewHolder holder, final int position) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.removeFav(postDetails, SessionHandler.getInstance().get(mContext, AppConstants.TOKEN_ID), SessionHandler.getInstance().get(mContext, AppConstants.Language)).enqueue(new Callback<POSTRemoveFav>() {
            @Override
            public void onResponse(Call<POSTRemoveFav> call, Response<POSTRemoveFav> response) {
                if (response.body().getCode().equals(200)) {
                    Utils.toastMessage(mContext, response.body().getMessage());
                    holder.favGigsIcons.setImageTintList(ColorStateList.valueOf(mContext.getResources().getColor(R.color.colorPrimary)));
                    holder.favGigsIcons.setImageResource(R.drawable.ic_favorite_border_purple_24dp);
                    data.get(position).setFavourite("0");
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

    private void addFavAPI(final ViewHolder holder, final int position) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.addFav(postDetails, SessionHandler.getInstance().get(mContext, AppConstants.TOKEN_ID), SessionHandler.getInstance().get(mContext, AppConstants.Language)).enqueue(new Callback<POSTAddFav>() {
            @Override
            public void onResponse(Call<POSTAddFav> call, Response<POSTAddFav> response) {
                if (response.body().getCode().equals(200)) {
                    Utils.toastMessage(mContext, response.body().getMessage());
                    data.get(position).setFavourite("1");
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

    private class ViewHolder {
        TextView gigsTitle, gigsAuthor, gigsLocation, gigsReviewCount, gigsRate;
        ImageView gigsImages;
        TextView ratingBar;
        ImageView favGigsIcons;
        Boolean isSelected;
    }

}
