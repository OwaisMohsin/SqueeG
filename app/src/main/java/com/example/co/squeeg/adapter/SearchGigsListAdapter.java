package com.example.co.squeeg.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import com.example.co.squeeg.DetailGigs;
import com.example.co.squeeg.Model.POSTSearchGigs;
import com.example.co.squeeg.R;
import com.example.co.squeeg.utils.AppConstants;

/**
 * Created by user5 on 27-12-2017.
 */

public class SearchGigsListAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<POSTSearchGigs.Result_detail> data;
    private final LayoutInflater mInflater;

    public SearchGigsListAdapter(Context mContext, List<POSTSearchGigs.Result_detail> data) {
        this.mContext = mContext;
        this.data = data;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public POSTSearchGigs.Result_detail getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder mHolder;
        if (convertView == null) {
            mHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.adapter_all_gigs, null);
            mHolder.gigsTitle = (TextView) convertView.findViewById(R.id.tv_gigs_title);
            mHolder.gigsAuthor = (TextView) convertView.findViewById(R.id.author);
            mHolder.gigsReviewCount = (TextView) convertView.findViewById(R.id.rating_count);
            mHolder.gigsImages = (ImageView) convertView.findViewById(R.id.iv_gigs_images);
            mHolder.gigsRate = (TextView) convertView.findViewById(R.id.tv_gigs_rate);
            mHolder.ratingBar = (RatingBar) convertView.findViewById(R.id.rating_gigs);
            mHolder.gigsLocation = (TextView) convertView.findViewById(R.id.tv_loc);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }

        mHolder.gigsTitle.setText(data.get(position).getTitle());
        mHolder.gigsAuthor.setText(data.get(position).getFullname());
        mHolder.gigsRate.setText(AppConstants.DOLLAR_SIGN + data.get(position).getGig_price());
        mHolder.gigsLocation.setText(data.get(position).getCountry() + "," + data.get(position).getState_name());
        //mHolder.gigsImages.setImageURI(Uri.parse(AppConstants.BASE_URL + data.get(position).getImage()));
        Picasso.get().load(AppConstants.BASE_URL + data.get(position).getImage()).placeholder(R.drawable.no_image).into(mHolder.gigsImages);
        mHolder.ratingBar.setRating(Float.parseFloat(data.get(position).getGig_rating()));
        mHolder.gigsReviewCount.setText("(" + data.get(position).getGig_usercount() + ")");

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callGigsDetails = new Intent(mContext, DetailGigs.class);
                callGigsDetails.putExtra(AppConstants.USER_TYPE,"" );
                callGigsDetails.putExtra(AppConstants.GIGS_ID, data.get(position).getId());
                callGigsDetails.putExtra(AppConstants.GIGS_TITLE, data.get(position).getTitle());
                mContext.startActivity(callGigsDetails);
            }
        });

        return convertView;
    }

    private class ViewHolder {
        TextView gigsTitle, gigsAuthor, gigsLocation, gigsReviewCount, gigsRate;
        ImageView gigsImages;
        RatingBar ratingBar;
    }
}
