package com.example.co.squeeg.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.co.squeeg.Model.POSTSellerReviews;
import com.example.co.squeeg.R;
import com.example.co.squeeg.adapter.SellerReviewsGigsAdpater;
import com.example.co.squeeg.network.ApiClient;
import com.example.co.squeeg.network.ApiInterface;
import com.example.co.squeeg.receiver.NetworkChangeReceiver;
import com.example.co.squeeg.utils.AppConstants;
import com.example.co.squeeg.utils.CustomProgressDialog;
import com.example.co.squeeg.utils.NetworkAlertDialog;
import com.example.co.squeeg.utils.SessionHandler;
import com.example.co.squeeg.utils.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user5 on 06-11-2017.
 */

public class DetailReviewGigsFragment extends Fragment {

    View view;
    HashMap<String, String> postGigDetails = new HashMap<String, String>();
    HashMap<String, String> postSellerReviews = new HashMap<String, String>();
    Context mContext;
    SellerReviewsGigsAdpater sellerReviewsGigsAdpater;
    String gigs_id;
    private ListView mDetailGigsReview;
    CustomProgressDialog mCustomProgressDialog;
    List<POSTSellerReviews.Review_detail> mData = new ArrayList<POSTSellerReviews.Review_detail>();
    private int total_page, page_num = 1;
    TextView noReviews;
    String seller_user_id="";


    public DetailReviewGigsFragment(Context mContext,String user_id) {
        this.mContext = mContext;
        this.seller_user_id = user_id;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.freeMemory();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_details_gigs_user_review, container, false);
        mCustomProgressDialog = new CustomProgressDialog(mContext);
        gigs_id = SessionHandler.getInstance().get(mContext, AppConstants.GIGS_ID);
        mDetailGigsReview = (ListView) view.findViewById(R.id.lv_review_gigs);
        noReviews = (TextView) view.findViewById(R.id.tv_no_reviews);
        getSellerReviews();


        mDetailGigsReview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                int threshold = 1;
                int count = mDetailGigsReview.getCount();
                if (mDetailGigsReview.getFooterViewsCount() == 0) {
                    if (mDetailGigsReview.getLastVisiblePosition() >= count - threshold && page_num < total_page) {
                        // Execute LoadMoreDataTask AsyncTask
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                page_num++;
                                getSellerReviews();
                            }
                        }, 2000);
                    }
                }

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });

        return view;
    }


    private void getSellerReviews() {
        if (NetworkChangeReceiver.isConnected()) {
            mCustomProgressDialog.showDialog();
            if (SessionHandler.getInstance().get(mContext, AppConstants.TOKEN_ID) != null) {
                postSellerReviews.put("user_id", seller_user_id);
                //postSellerReviews.put("user_id", SessionHandler.getInstance().get(mContext, AppConstants.GigUserID));

            } else {
                postGigDetails.put("userid", "");
            }
            postGigDetails.put("gig_id", gigs_id);
            postSellerReviews.put("device_type", AppConstants.DeviceType);
            postSellerReviews.put("page", String.valueOf(page_num));
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            apiInterface.getSellerReviews(postSellerReviews,SessionHandler.getInstance().get(getActivity(), AppConstants.TOKEN_ID), SessionHandler.getInstance().get(mContext, AppConstants.Language)).enqueue(new Callback<POSTSellerReviews>() {
                @Override
                public void onResponse(Call<POSTSellerReviews> call, Response<POSTSellerReviews> response) {

                    if(response.body().getCode().equals(200)){
                        if (response.body().getData().getReview_details().size() > 0) {
                            total_page = response.body().getData().getTotal_pages();
                            if (mData.size() > 0) {
                                mData.addAll(response.body().getData().getReview_details());
                                sellerReviewsGigsAdpater.notifyDataSetChanged();
                            } else {
                                mData.addAll(response.body().getData().getReview_details());
                                sellerReviewsGigsAdpater = new SellerReviewsGigsAdpater(mContext, mData);
                                mDetailGigsReview.setAdapter(sellerReviewsGigsAdpater);
                            }
                            mCustomProgressDialog.dismiss();
                            Utils.getListViewSize(mDetailGigsReview);
                        } else {
                            mDetailGigsReview.setVisibility(View.GONE);
                            noReviews.setVisibility(View.VISIBLE);
                            mCustomProgressDialog.dismiss();
                        }
                    } else if (response.body().getCode().equals(AppConstants.INVALID_RESPONSE_CODE)) {
                        mCustomProgressDialog.dismiss();
                        NetworkAlertDialog.networkAlertDialog(getActivity(), "", response.body().getMessage(), null, null);
                    }else if (response.body().getCode().equals(AppConstants.InactiveStatusResponse)) {
                        Utils.createUserInActiceAlert(getActivity(), response.body().getMessage());
                    }


                }

                @Override
                public void onFailure(Call<POSTSellerReviews> call, Throwable t) {
                    Log.i("TAG", t.getMessage());
                    mCustomProgressDialog.dismiss();
                    if (t instanceof UnknownHostException || t instanceof HttpException || t instanceof ConnectException || t instanceof SocketTimeoutException || t instanceof SocketException||t instanceof IOException) {
                        Toast.makeText(getActivity(), getString(R.string.err_server_msg), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        } else {
            Utils.toastMessage(mContext, getString(R.string.err_internet_connection));
        }

    }


}
