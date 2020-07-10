package com.example.co.squeeg.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;


import com.facebook.drawee.view.SimpleDraweeView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.example.co.squeeg.Model.POSTGigsReview;
import com.example.co.squeeg.R;
import com.example.co.squeeg.utils.AppConstants;

/**
 * Created by user5 on 15-12-2017.
 */

public class GigsReviewAdapter extends BaseAdapter {
    LayoutInflater mInflater;
    private Context mContext;
    private List<POSTGigsReview.Datum> SellerReviews;
    SimpleDateFormat dateFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    public GigsReviewAdapter(Context mContext, List<POSTGigsReview.Datum> SellerReviews) {
        this.mContext = mContext;
        this.SellerReviews = SellerReviews;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return SellerReviews.size();
    }

    @Override
    public POSTGigsReview.Datum getItem(int position) {
        return SellerReviews.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder mHolder;
        if (convertView == null) {
            mHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.adapter_review_gigs, null);
            mHolder.inputGigsBuyername = (TextView) convertView.findViewById(R.id.tv_gigs_username);
            mHolder.inputGigscomment = (TextView) convertView.findViewById(R.id.tv_gigs_comment);
            mHolder.inputGigsReviewRatingBar = (RatingBar) convertView.findViewById(R.id.rating_gigs);
            mHolder.inputUserimage = (SimpleDraweeView) convertView.findViewById(R.id.iv_user_profile);
            try {
                Date oldDate = dateFormat.parse(SellerReviews.get(position).getCreated_date());
                Date currentDate = new Date();
                long diff = currentDate.getTime() - oldDate.getTime();
                long seconds = diff / 1000;
                long minutes = seconds / 60;
                long hours = minutes / 60;
                long days = hours / 24;
                if (oldDate.before(currentDate)) {

                    if (seconds < 60) {
                        mHolder.inputGigsBuyername.setText(SellerReviews.get(position).getBuyername() + " ("
                                + seconds + " seconds ago)");
                    } else if (minutes < 60) {
                        mHolder.inputGigsBuyername.setText(SellerReviews.get(position).getBuyername() + " ("
                                + minutes + " minutes ago)");
                    } else if (hours < 24) {
                        mHolder.inputGigsBuyername.setText(SellerReviews.get(position).getBuyername() + " ("
                                + hours + " hours ago)");
                    } else {
                        mHolder.inputGigsBuyername.setText(SellerReviews.get(position).getBuyername() + " ("
                                + days + " days ago)");
                    }

                    Log.e("oldDate", "is previous date");
                    Log.e("Difference: ", " seconds: " + seconds + " minutes: " + minutes
                            + " hours: " + hours + " days: " + days);

                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }


        mHolder.inputGigscomment.setText(SellerReviews.get(position).getComment());
        if (SellerReviews.get(position).getRating() != null) {
            mHolder.inputGigsReviewRatingBar.setRating(Float.parseFloat(SellerReviews.get(position).getRating()));
        }

        if (SellerReviews.get(position).getProfile_img() != null && !SellerReviews.get(position).getProfile_img().isEmpty()) {
            mHolder.inputUserimage.setImageURI(Uri.parse(AppConstants.ImageBaseURL + SellerReviews.get(position).getProfile_img()));
        }

        return convertView;
    }


    private class ViewHolder {
        TextView inputGigsBuyername, inputGigscomment;
        RatingBar inputGigsReviewRatingBar;
        SimpleDraweeView inputUserimage;
    }
}
