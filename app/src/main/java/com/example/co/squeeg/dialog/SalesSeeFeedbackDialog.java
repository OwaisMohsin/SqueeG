package com.example.co.squeeg.dialog;

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

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
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

public class SalesSeeFeedbackDialog extends DialogFragment {

    View inflateSeeFeBack;
    EditText editComment;
    TextView fromChatName, toChatName, fromComments, toComments, fromTime, toTime;
    RatingBar rateUsStars, fromRating, toRating;
    LinearLayout ll_userComments;
    Button btnSendFeedback;
    Context mContext;
    HashMap<String, String> postGigsdetails = new HashMap<String, String>();
    CustomProgressDialog mCustomProgressDialog;
    POSTMyActivity.My_sale my_purchase;
    static MyPurchasesFragment callbacks;
    SimpleDraweeView fromImage, toImage;
    List<POSTPurchaseSeeFedBck.User_feed> user_feed;
    SimpleDraweeView iv_seller_image;
    int type;
    public POSTLanguageModel.Common_strings commonStrings = new POSTLanguageModel().new Common_strings();
    public POSTLanguageModel.My_activity_screen my_activity_screen = new POSTLanguageModel().new My_activity_screen();
    @BindView(R.id.tv_username)
    CustomTextView tvUsername;
    @BindView(R.id.ctv_leave_feedback)
    CustomTextView ctvLeaveFeedback;
    @BindView(R.id.ctv_rate_your_feel)
    CustomTextView ctvRateYourFeel;
    @BindView(R.id.ctv_anything_else)
    CustomTextView ctvAnythingElse;
    Unbinder unbinder;
    @BindView(R.id.ctv_see_your_feedback)
    CustomTextView ctvSeeYourFeedback;
    @BindView(R.id.ctv_comments)
    CustomTextView ctvComments;
    Unbinder unbinder1;

    public SalesSeeFeedbackDialog(Context mContext) {
        this.mContext = mContext;
        mCustomProgressDialog = new CustomProgressDialog(mContext);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setLanguageValues();
        if (my_purchase.getFeedback_val().equals(1)) {
            inflateSeeFeBack = inflater.inflate(R.layout.dialog_leave_feedback, null);
            unbinder = ButterKnife.bind(this, inflateSeeFeBack);
            ctvAnythingElse.setText(my_activity_screen.getLbl_thanks_rating().getName());
            ctvLeaveFeedback.setText(my_activity_screen.getLbl_leave_feedback().getName());
            ctvRateYourFeel.setText(my_activity_screen.getLbl_rate_feedback().getName());
            inflateSeeFeBack.setPadding(10, 10, 10, 10);
            editComment = (EditText) inflateSeeFeBack.findViewById(R.id.et_comments);
            rateUsStars = (RatingBar) inflateSeeFeBack.findViewById(R.id.rb_comments);
            btnSendFeedback = (Button) inflateSeeFeBack.findViewById(R.id.btn_send_feeedback);
            btnSendFeedback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (editComment.getText().toString().isEmpty()) {
                        return;
                    }

                    if (rateUsStars.getRating() == 0) {
                        return;
                    }

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
            });

        } else {
            inflateSeeFeBack = inflater.inflate(R.layout.dialog_see_feedback, null);
            unbinder1 = ButterKnife.bind(this, inflateSeeFeBack);
            inflateSeeFeBack.setPadding(10, 10, 10, 10);
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
            ll_userComments = (LinearLayout) inflateSeeFeBack.findViewById(R.id.ll_user_comment);
            iv_seller_image = (SimpleDraweeView) inflateSeeFeBack.findViewById(R.id.iv_gigs_images);
            ctvSeeYourFeedback.setText(my_activity_screen.getLbl_see_feedback().getName());
            ctvComments.setText(my_activity_screen.getLbl_comments().getName());
            //iv_seller_image.setImageURI(Uri.parse(AppConstants.BASE_URL + m));
            for (int i = 0; i < user_feed.size(); i++) {
                if (user_feed.get(i).getFb_from_role().equalsIgnoreCase("Buyer")) {
                    fromChatName.setText("Posted by: " + user_feed.get(i).getFb_user_name());
                    fromTime.setText(user_feed.get(i).getFb_user_time());
                    if (user_feed.get(i).getFb_user_rating() != null)
                        toRating.setRating(Float.parseFloat(user_feed.get(i).getFb_user_rating()));
                    toComments.setText(user_feed.get(i).getFb_user_comment());
                    if (user_feed.get(i).getFb_user_img() != null && !user_feed.get(i).getFb_user_img().isEmpty()) {
                        iv_seller_image.setImageURI(Uri.parse(user_feed.get(i).getFb_user_img()));
                    }

                }
            }
           /* if (user_feed != null && user_feed.size() == 1) {
                fromChatName.setText(user_feed.get(0).getFb_user_name());
                fromComments.setText(user_feed.get(0).getFb_user_comment());
                if (user_feed.get(0).getFb_user_rating() != null) {
                    fromRating.setRating(Float.parseFloat(user_feed.get(0).getFb_user_rating()));
                }
                fromTime.setText(user_feed.get(0).getFb_user_time());
                //Picasso.with(mContext).load(user_feed.get(0).getFb_user_url() + user_feed.get(0).getFb_user_img()).placeholder(R.drawable.no_image).transform(new CircleTransform()).into(fromImage);
                fromImage.setImageURI(Uri.parse(user_feed.get(0).getFb_user_img()));
                ll_userComments.setVisibility(View.GONE);

            }
            if (user_feed != null && user_feed.size() == 2) {
                fromChatName.setText(user_feed.get(0).getFb_user_name());
                fromComments.setText(user_feed.get(0).getFb_user_comment());
                if (user_feed.get(0).getFb_user_rating() != null) {
                    fromRating.setRating(Float.parseFloat(user_feed.get(0).getFb_user_rating()));
                }
                fromTime.setText(user_feed.get(0).getFb_user_time());
                //Picasso.with(mContext).load(user_feed.get(0).getFb_user_url() + user_feed.get(0).getFb_user_img()).placeholder(R.drawable.no_image).transform(new CircleTransform()).into(fromImage);
                fromImage.setImageURI(Uri.parse(user_feed.get(0).getFb_user_url() + user_feed.get(0).getFb_user_img()));
                toChatName.setText(user_feed.get(1).getFb_user_name());
                toComments.setText(user_feed.get(1).getFb_user_comment());
                if (user_feed.get(1).getFb_user_rating() != null) {
                    toRating.setRating(Float.parseFloat(user_feed.get(1).getFb_user_rating()));
                }
                toTime.setText(user_feed.get(1).getFb_user_time());
                toImage.setImageURI(Uri.parse(user_feed.get(0).getFb_user_url() + user_feed.get(1).getFb_user_img()));
                //Picasso.with(mContext).load(user_feed.get(0).getFb_user_url() + user_feed.get(1).getFb_user_img()).placeholder(R.drawable.no_image).transform(new CircleTransform()).into(toImage);
            }*/

        }


        return inflateSeeFeBack;
    }

    public void setData(POSTMyActivity.My_sale my_purchase, int type, List<POSTPurchaseSeeFedBck.User_feed> user_feed) {
        this.my_purchase = my_purchase;
        this.user_feed = user_feed;
        this.type = type;
    }

    public static void callDialgFrag(MyPurchasesFragment mMyPurchasesFragment) {
        callbacks = mMyPurchasesFragment;
    }

    public void setLanguageValues() {
        commonStrings = new Gson().fromJson(SessionHandler.getInstance().get(mContext, AppConstants.COMMONSTRINGS), POSTLanguageModel.Common_strings.class);
        my_activity_screen = new Gson().fromJson(SessionHandler.getInstance().get(mContext, AppConstants.MYACTIVITY), POSTLanguageModel.My_activity_screen.class);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
