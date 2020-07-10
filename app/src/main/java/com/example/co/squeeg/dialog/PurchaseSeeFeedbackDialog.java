package com.example.co.squeeg.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.example.co.squeeg.Model.POSTLanguageModel;
import com.example.co.squeeg.Model.POSTLeaveFeedback;
import com.example.co.squeeg.Model.POSTMyActivity;
import com.example.co.squeeg.Model.POSTPurchaseSeeFedBck;
import com.example.co.squeeg.R;
import com.example.co.squeeg.fragment.MyPurchasesFragment;
import com.example.co.squeeg.network.ApiClient;
import com.example.co.squeeg.network.ApiInterface;
import com.example.co.squeeg.receiver.NetworkChangeReceiver;
import com.example.co.squeeg.utils.AppConstants;
import com.example.co.squeeg.utils.CustomProgressDialog;
import com.example.co.squeeg.utils.CustomTextView;
import com.example.co.squeeg.utils.NetworkAlertDialog;
import com.example.co.squeeg.utils.SessionHandler;
import com.example.co.squeeg.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Prasad on 11/9/2017.
 */

public class PurchaseSeeFeedbackDialog extends DialogFragment {

    View inflateSeeFeBack;
    EditText editComment;
    TextView fromChatName, toChatName, fromComments, toComments, fromTime, toTime;
    SimpleDraweeView iv_seller_image;
    RatingBar rateUsStars, fromRating, toRating;
    Button btnSendFeedback;
    Context mContext;
    HashMap<String, String> postGigsdetails = new HashMap<String, String>();
    CustomProgressDialog mCustomProgressDialog;
    POSTMyActivity.My_purchase my_purchase;
    static MyPurchasesFragment callbacks;
    SimpleDraweeView fromImage, toImage;
    List<POSTPurchaseSeeFedBck.User_feed> user_feed;
    int type;
    SimpleDraweeView ivBuyerImg;
    CustomTextView tvUsername;
    RatingBar buyerRating;
    public POSTLanguageModel.Common_strings commonStrings = new POSTLanguageModel().new Common_strings();
    public POSTLanguageModel.My_activity_screen my_activity_screen = new POSTLanguageModel().new My_activity_screen();

    public PurchaseSeeFeedbackDialog(Context mContext) {
        this.mContext = mContext;
        mCustomProgressDialog = new CustomProgressDialog(mContext);
    }


    public PurchaseSeeFeedbackDialog() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final LinearLayout root = new LinearLayout(getActivity());

        setLanguageValues();
        //setStyle(DialogFragment.STYLE_NORMAL, R.style.dialog_theme);
        if (my_purchase.getFeedback_val().equals(1)) {
            inflateSeeFeBack = inflater.inflate(R.layout.dialog_leave_feedback, container, false);
            inflateSeeFeBack.setPadding(10, 10, 10, 10);
            editComment = (EditText) inflateSeeFeBack.findViewById(R.id.et_comments);
            rateUsStars = (RatingBar) inflateSeeFeBack.findViewById(R.id.rb_comments);
            btnSendFeedback = (Button) inflateSeeFeBack.findViewById(R.id.btn_send_feeedback);
            ivBuyerImg = (SimpleDraweeView) inflateSeeFeBack.findViewById(R.id.iv_gigs_images);
            tvUsername = (CustomTextView) inflateSeeFeBack.findViewById(R.id.tv_username);
            buyerRating = (RatingBar) inflateSeeFeBack.findViewById(R.id.buyer_rating);

            //iv_seller_image = (SimpleDraweeView) inflateSeeFeBack.findViewById(R.id.iv_gigs_images);
            tvUsername.setText(my_purchase.getSeller_name());
            ivBuyerImg.setImageURI(Uri.parse(AppConstants.BASE_URL + my_purchase.getSeller_thumb_image()));
            btnSendFeedback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (editComment.getText().toString().isEmpty() && rateUsStars.getRating() == 0) {
                        Toast.makeText(mContext, my_activity_screen.getLbl_send_feedback().getValidation1(), Toast.LENGTH_SHORT).show();
                    } else if (editComment.getText().toString().isEmpty()) {
                        Toast.makeText(mContext, my_activity_screen.getTxt_fld_comments().getValidation1(), Toast.LENGTH_SHORT).show();
                        return;
                    } else if (rateUsStars.getRating() == 0) {
                        Toast.makeText(mContext, my_activity_screen.getLbl_rating().getValidation1(), Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        if (type == 1) {
                            postGigsdetails.put("from_user_id", my_purchase.getUser_id());
                            postGigsdetails.put("to_user_id", SessionHandler.getInstance().get(mContext, AppConstants.TOKEN_ID));
                            postGigsdetails.put("gig_id", my_purchase.getGigs_id());
                            postGigsdetails.put("order_id", my_purchase.getOrder_id());
                            postGigsdetails.put("comment", editComment.getText().toString());
                            postGigsdetails.put("rating", String.valueOf(rateUsStars.getRating()));
                            postGigsdetails.put("time_zone", SessionHandler.getInstance().get(mContext, AppConstants.TIMEZONE_ID));
                            postGigsdetails.put("type", String.valueOf(type));
                        } else {
                            postGigsdetails.put("from_user_id", SessionHandler.getInstance().get(mContext, AppConstants.TOKEN_ID));
                            postGigsdetails.put("to_user_id", my_purchase.getUser_id());
                            postGigsdetails.put("gig_id", my_purchase.getGigs_id());
                            postGigsdetails.put("order_id", my_purchase.getOrder_id());
                            postGigsdetails.put("comment", editComment.getText().toString());
                            postGigsdetails.put("rating", String.valueOf(rateUsStars.getRating()));
                            postGigsdetails.put("time_zone", SessionHandler.getInstance().get(mContext, AppConstants.TIMEZONE_ID));
                            postGigsdetails.put("type", String.valueOf(type));
                        }
                        if (NetworkChangeReceiver.isConnected()) {
                            mCustomProgressDialog.showDialog();
                            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                            apiInterface.postLeaveFeedback(postGigsdetails, SessionHandler.getInstance().get(mContext, AppConstants.TOKEN_ID), SessionHandler.getInstance().get(mContext, AppConstants.Language)).enqueue(new Callback<POSTLeaveFeedback>() {
                                @Override
                                public void onResponse(Call<POSTLeaveFeedback> call, Response<POSTLeaveFeedback> response) {

                                    if (response.body().getCode().equals(200)) {
                                        Utils.toastMessage(mContext, response.body().getMessage());
                                        callbacks.UpdateFeedbackRequest(my_purchase.getOrder_id());
                                        getDialog().dismiss();
                                    } else if (response.body().getCode().equals(AppConstants.INVALID_RESPONSE_CODE)) {
//                    mCustomProgressDialog.dismiss();
                                        NetworkAlertDialog.networkAlertDialog(mContext, "", response.body().getMessage(), null, null);
                                    } else {
                                        Utils.toastMessage(mContext, response.body().getMessage());
                                    }

                                    mCustomProgressDialog.dismiss();
                                }

                                @Override
                                public void onFailure(Call<POSTLeaveFeedback> call, Throwable t) {
                                    Log.i("TAG", t.getMessage());
                                    mCustomProgressDialog.dismiss();
                                }
                            });

                        } else {
                            Utils.toastMessage(mContext, commonStrings.getLbl_enable_internet().getName());
                        }
                    }
                }
            });

        } else {
            inflateSeeFeBack = inflater.inflate(R.layout.dialog_purchase_see_feedback, null);
            //unbinder = ButterKnife.bind(this, inflateSeeFeBack);
            //inflateSeeFeBack.setPadding(10, 10, 10, 10);
            fromChatName = (TextView) inflateSeeFeBack.findViewById(R.id.from_chat_name);
            toChatName = (TextView) inflateSeeFeBack.findViewById(R.id.to_chat_name);
            fromComments = (TextView) inflateSeeFeBack.findViewById(R.id.from_chat_msg);
            toComments = (TextView) inflateSeeFeBack.findViewById(R.id.to_chat_msg);
            fromTime = (TextView) inflateSeeFeBack.findViewById(R.id.from_chat_date);
            toTime = (TextView) inflateSeeFeBack.findViewById(R.id.to_chat_date);
            toRating = (RatingBar) inflateSeeFeBack.findViewById(R.id.to_rb_comments);
            fromRating = (RatingBar) inflateSeeFeBack.findViewById(R.id.from_rb_comments);
            fromImage = (SimpleDraweeView) inflateSeeFeBack.findViewById(R.id.iv_from_prf_image);
            toImage = (SimpleDraweeView) inflateSeeFeBack.findViewById(R.id.iv_to_prf_image);
            iv_seller_image = (SimpleDraweeView) inflateSeeFeBack.findViewById(R.id.iv_gigs_images);
            iv_seller_image.setImageURI(Uri.parse(AppConstants.BASE_URL + my_purchase.getSeller_thumb_image()));
            fromChatName.setText(user_feed.get(0).getFb_user_name());
            fromComments.setText(user_feed.get(0).getFb_user_comment());
            fromRating.setRating(Float.parseFloat(user_feed.get(0).getFb_user_rating()));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm a");
            try {
                @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm a");
                //simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                Date dates = simpleDateFormat.parse(user_feed.get(0).getFb_user_time());
                //simpleDateFormat.setTimeZone(TimeZone.getDefault());
                String formattedDate = simpleDateFormat2.format(dates);
                fromTime.setText(formattedDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            fromImage.setImageURI(Uri.parse(user_feed.get(0).getFb_user_img()));
            //Picasso.with(mContext).load(user_feed.get(0).getFb_user_img()).placeholder(R.drawable.no_image).transform(new CircleTransform()).into(fromImage);
            if (user_feed.size() > 1) {
                toChatName.setText(user_feed.get(1).getFb_user_name());
                toComments.setText(user_feed.get(1).getFb_user_comment());
                toRating.setRating(Float.parseFloat(user_feed.get(1).getFb_user_rating()));
                toTime.setText(user_feed.get(1).getFb_user_time());
                toImage.setImageURI(Uri.parse(user_feed.get(1).getFb_user_img()));
                //Picasso.with(mContext).load(user_feed.get(1).getFb_user_img()).placeholder(R.drawable.no_image).transform(new CircleTransform()).into(toImage);
            } else {
                toChatName.setVisibility(View.GONE);
                toComments.setVisibility(View.GONE);
                toImage.setVisibility(View.GONE);
                toRating.setVisibility(View.GONE);
                toTime.setVisibility(View.GONE);
            }


        }


        return inflateSeeFeBack;
    }

    public void setData(POSTMyActivity.My_purchase my_purchase, int type, List<POSTPurchaseSeeFedBck.User_feed> user_feed) {
        this.my_purchase = my_purchase;
        this.user_feed = user_feed;
        this.type = type;
    }

    public static void callDialgFrag(MyPurchasesFragment mMyPurchasesFragment) {
        callbacks = mMyPurchasesFragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog d = getDialog();
        if (d != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            d.getWindow().setLayout(width, height);
        }
    }

    public void setLanguageValues() {
        commonStrings = new Gson().fromJson(SessionHandler.getInstance().get(mContext, AppConstants.COMMONSTRINGS), POSTLanguageModel.Common_strings.class);
        my_activity_screen = new Gson().fromJson(SessionHandler.getInstance().get(mContext, AppConstants.MYACTIVITY), POSTLanguageModel.My_activity_screen.class);
    }
}
