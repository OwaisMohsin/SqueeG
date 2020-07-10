package com.example.co.squeeg.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;


import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import com.example.co.squeeg.Model.POSTAcceptSellerDeclineRequest;
import com.example.co.squeeg.Model.POSTLanguageModel;
import com.example.co.squeeg.Model.POSTMyActivity;
import com.example.co.squeeg.Model.POSTPurchaseCompletedAcceptStatus;
import com.example.co.squeeg.Model.POSTPurchaseSeeFedBck;
import com.example.co.squeeg.Model.POSTRejectResponse;
import com.example.co.squeeg.R;
import com.example.co.squeeg.dialog.PurchaseCancelDialogFragment;
import com.example.co.squeeg.dialog.PurchaseSeeFeedbackDialog;
import com.example.co.squeeg.network.ApiClient;
import com.example.co.squeeg.network.ApiInterface;
import com.example.co.squeeg.receiver.NetworkChangeReceiver;
import com.example.co.squeeg.utils.AppConstants;
import com.example.co.squeeg.utils.CustomProgressDialog;
import com.example.co.squeeg.utils.CustomTextView;
import com.example.co.squeeg.utils.SessionHandler;
import com.example.co.squeeg.utils.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Prasad on 10/25/2017.
 */

public class MyPurchaseAdapter extends RecyclerView.Adapter<MyPurchaseAdapter.MyViewHolder> {
    private Context mContext;
    private List<POSTMyActivity.My_purchase> data;
    private HashMap<String, String> postGigsdetails;
    private PurchaseCancelDialogFragment mPurchaseCancelDialogFragment;
    private FragmentManager fm;
    private PurchaseSeeFeedbackDialog mPurchaseSeeFeedbackDialog;
    private CustomProgressDialog mCustomProgressDialog;
    HashMap<String, String> postPurchaseCompletedStatusData = new HashMap<>();
    HashMap<String, String> postPurchaseRejectStatusData = new HashMap<>();
    HashMap<String, String> postAcceptDeclineRequestData = new HashMap<>();
    public POSTLanguageModel.Common_strings commonStrings = new POSTLanguageModel().new Common_strings();
    public POSTLanguageModel.My_activity_screen my_activity_screen = new POSTLanguageModel().new My_activity_screen();


    public MyPurchaseAdapter(Context mContext, List<POSTMyActivity.My_purchase> data, FragmentManager fm) {
        this.mContext = mContext;
        this.data = data;
        postGigsdetails = new HashMap<String, String>();
        mCustomProgressDialog = new CustomProgressDialog(mContext);
        this.fm = fm;
        mPurchaseCancelDialogFragment = new PurchaseCancelDialogFragment(mContext);
        mPurchaseSeeFeedbackDialog = new PurchaseSeeFeedbackDialog(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_purchase_gigs, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        setLanguageValues();
        holder.ctvOrderStatus.setText(my_activity_screen.getLbl_order_status().getName());
        holder.ctvOrderCancel.setText(my_activity_screen.getLbl_order_cancel().getName());
        holder.ctvFeedback.setText(my_activity_screen.getLbl_feedback().getName());
        Picasso.get().load(AppConstants.BASE_URL + data.get(position).getGig_image_thumb()).placeholder(R.drawable.no_image).into(holder.gigsImages);
        //holder.gigsImages.setImageURI(Uri.parse(AppConstants.BASE_URL + data.get(position).getGig_image_thumb()));
        holder.sellerName.setText(my_activity_screen.getLbl_seller_name().getName() + data.get(position).getSeller_name());
        holder.gigsDDate.setText(my_activity_screen.getLbl_delivery_date().getName() + data.get(position).getDelivery());
        holder.gigsOrderId.setText(my_activity_screen.getLbl_order_id().getName() + data.get(position).getOrder_id());
        holder.gigsTitle.setText(data.get(position).getTitle());
        holder.postedDate.setText(data.get(position).getCreated_date());
        AppConstants.DOLLAR_SIGN = data.get(position).getCurrency_sign();
        holder.gigsRate.setText(AppConstants.DOLLAR_SIGN + " " + data.get(position).getAmount());

        if (data.get(position).getFeedback_val().equals(0)) {
            holder.pending.setText(data.get(position).getFeedback());
        } else if (data.get(position).getFeedback_val().equals(1)) {
            holder.pending.setText(data.get(position).getFeedback());
            holder.pending.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //type:1(Buyer) type:0(Seller)
                    if (mPurchaseSeeFeedbackDialog != null) {
                        mPurchaseSeeFeedbackDialog.setData(data.get(position), 1, null);
                        mPurchaseSeeFeedbackDialog.show(fm, "see feedback");

                    }
                }
            });

        } else {
            holder.pending.setText(data.get(position).getFeedback());
            holder.pending.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callSeeFedBck(data.get(position));
                }
            });
        }
        holder.cancel.setText(data.get(position).getOrder_cancel());
        if (data.get(position).getOrder_cancel_val().equals(1)) {
            holder.cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mPurchaseCancelDialogFragment != null) {
                        mPurchaseCancelDialogFragment.cancelRequestData(data.get(position).getOrder_id(), data.get(position).getSource());
                        mPurchaseCancelDialogFragment.show(fm, "cancel_dialog");
                    }
                }
            });
        }
        holder.newStatus.setText(data.get(position).getOrder_status());
        holder.newStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (data.get(position).getSeller_status().equalsIgnoreCase("7")) {
                    final Dialog dialog = new Dialog(mContext);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                    dialog.setCancelable(false);
                    dialog.setContentView(R.layout.dialog_order_status);
                    int width = ViewGroup.LayoutParams.MATCH_PARENT;
                    int height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    dialog.getWindow().setLayout(width, height);
                    final LinearLayout layout1 = (LinearLayout) dialog.findViewById(R.id.ll_order_status);
                    final LinearLayout layout2 = (LinearLayout) dialog.findViewById(R.id.ll_reject_order);
                    final TextView acceptOrder = (TextView) dialog.findViewById(R.id.tv_accept_complete);
                    TextView rejectOrder = (TextView) dialog.findViewById(R.id.tv_reject);
                    final EditText reason = (EditText) dialog.findViewById(R.id.et_reason);
                    Button btnRejectSubmit = (Button) dialog.findViewById(R.id.bt_submit);
                    acceptOrder.setText(my_activity_screen.getLbl_activate_order().getName());
                    rejectOrder.setText(my_activity_screen.getLbl_reject_order().getName());
                    acceptOrder.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            postPurchaseCompletedStatusData.put("payment_id", data.get(position).getOrder_id());
                            postPurchaseCompletedStatusData.put("status", "6");
                            postPurchaseCompletedStatusData.put("time_zone", SessionHandler.getInstance().get(mContext, AppConstants.TIMEZONE_ID));
                            mCustomProgressDialog.showDialog();
                            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                            apiInterface.postPurchaseCompletedAcceptStatus(postPurchaseCompletedStatusData, SessionHandler.getInstance().get(mContext, AppConstants.TOKEN_ID), SessionHandler.getInstance().get(mContext, AppConstants.Language)).enqueue(new Callback<POSTPurchaseCompletedAcceptStatus>() {
                                @Override
                                public void onResponse(Call<POSTPurchaseCompletedAcceptStatus> call, Response<POSTPurchaseCompletedAcceptStatus> response) {
                                    if (response.body().getCode().equals(200)) {
                                        dialog.dismiss();
                                        data.get(position).setOrder_status(my_activity_screen.getLbl_completed().getName());
                                        data.get(position).setSeller_status("6");
                                        holder.newStatus.setText(data.get(position).getOrder_status());
                                        mCustomProgressDialog.dismiss();
                                    } else {
                                        dialog.dismiss();
                                        mCustomProgressDialog.dismiss();
                                        Toast.makeText(mContext, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<POSTPurchaseCompletedAcceptStatus> call, Throwable t) {
                                    dialog.dismiss();
                                    mCustomProgressDialog.dismiss();
                                }
                            });
                        }
                    });
                    rejectOrder.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            layout1.setVisibility(View.GONE);
                            layout2.setVisibility(View.VISIBLE);
                        }
                    });

                    btnRejectSubmit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (reason.getText().toString().isEmpty()) {
                                Toast.makeText(mContext, mContext.getResources().getString(R.string.txt_enter_reason), Toast.LENGTH_SHORT).show();
                                return;
                            } else {
                                postPurchaseRejectStatusData.put("order_id", data.get(position).getOrder_id());
                                postPurchaseRejectStatusData.put("seller_id", data.get(position).getUser_id());
                                postPurchaseRejectStatusData.put("gig_id", data.get(position).getGigs_id());
                                postPurchaseRejectStatusData.put("reject_reason", reason.getText().toString().trim());
                                mCustomProgressDialog.showDialog();
                                ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                                apiInterface.postRejectResponse(postPurchaseRejectStatusData, SessionHandler.getInstance().get(mContext, AppConstants.TOKEN_ID), SessionHandler.getInstance().get(mContext, AppConstants.Language)).enqueue(new Callback<POSTRejectResponse>() {
                                    @Override
                                    public void onResponse(Call<POSTRejectResponse> call, Response<POSTRejectResponse> response) {
                                        if (response.body().getCode().equals(200)) {
                                            dialog.dismiss();
                                            mCustomProgressDialog.dismiss();
                                            Toast.makeText(mContext, mContext.getResources().getString(R.string.txt_reject_request), Toast.LENGTH_SHORT).show();
                                            data.get(position).setOrder_status(my_activity_screen.getLbl_fld_reject_request().getName());
                                            data.get(position).setSeller_status("9");
                                            holder.newStatus.setText(data.get(position).getOrder_status());
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<POSTRejectResponse> call, Throwable t) {

                                    }
                                });
                            }
                        }
                    });

                    dialog.show();
//                    Button submit = (Button) dialog.findViewById(R.id.text_dialog);


                } else if (data.get(position).getSeller_status().equalsIgnoreCase("5")) {
                    if (data.get(position).getDecline_accept().equalsIgnoreCase("0")) {
                        postAcceptDeclineRequestData.put("payment_id", data.get(position).getOrder_id());
                        //postAcceptDeclineRequestData.put("user_id", SessionHandler.getInstance().get(mContext, AppConstants.TOKEN_ID));
                        postAcceptDeclineRequestData.put("time_zone", SessionHandler.getInstance().get(mContext, AppConstants.TIMEZONE_ID));
                        if (data.get(position).getSource().equalsIgnoreCase("paypal")) {
                            if (SessionHandler.getInstance().get(mContext, AppConstants.PayPal_User_Email) != null && !SessionHandler.getInstance().get(mContext, AppConstants.PayPal_User_Email).isEmpty()) {
                                postAcceptDeclineRequestData.put("payment_email", SessionHandler.getInstance().get(mContext, AppConstants.PayPal_User_Email));
                                purchaseDeclineRequest(position, holder);
                            } else {
                                Toast.makeText(mContext, my_activity_screen.getErr_add_stripe().getName(), Toast.LENGTH_SHORT).show();
                            }
                        } else if (data.get(position).getSource().equalsIgnoreCase("stripe")) {
                            if (SessionHandler.getInstance().get(mContext, AppConstants.stripe_Acc_name) != null && !SessionHandler.getInstance().get(mContext, AppConstants.stripe_Acc_name).isEmpty()) {
                                postAcceptDeclineRequestData.put("payment_email", "");
                                purchaseDeclineRequest(position, holder);
                            } else {
                                Toast.makeText(mContext, my_activity_screen.getErr_add_stripe().getName(), Toast.LENGTH_SHORT).show();
                            }
                        }


                    }
                }
            }
        });


    }

    private void purchaseDeclineRequest(final int position, final MyViewHolder holder) {
        mCustomProgressDialog.showDialog();
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.postAcceptSellerDeclineRequest(postAcceptDeclineRequestData, SessionHandler.getInstance().get(mContext, AppConstants.TOKEN_ID), SessionHandler.getInstance().get(mContext, AppConstants.Language)).enqueue(new Callback<POSTAcceptSellerDeclineRequest>() {
            @Override
            public void onResponse(Call<POSTAcceptSellerDeclineRequest> call, Response<POSTAcceptSellerDeclineRequest> response) {
                mCustomProgressDialog.dismiss();
                if (response.body().getCode().equals(200)) {
                    data.get(position).setOrder_status(my_activity_screen.getLbl_declined().getName());
                    data.get(position).setSeller_status("5");
                    data.get(position).setDecline_accept("1");
                    holder.newStatus.setText(data.get(position).getOrder_status());
                } else {
                    Toast.makeText(mContext, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<POSTAcceptSellerDeclineRequest> call, Throwable t) {
                mCustomProgressDialog.dismiss();
            }
        });
    }


    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        final TextView gigsTitle;
        final TextView postedDate;
        final TextView gigsOrderId;
        final TextView gigsDDate;
        final TextView sellerName;
        final TextView pending;
        final TextView cancel;
        final TextView newStatus;
        final TextView gigsRate;
        CustomTextView ctvFeedback;
        CustomTextView ctvOrderStatus, ctvOrderCancel;
        final CircleImageView gigsImages;

        MyViewHolder(View itemView) {
            super(itemView);
            gigsTitle = (TextView) itemView.findViewById(R.id.tv_gigs_title);
            postedDate = (TextView) itemView.findViewById(R.id.posted_dated);
            pending = (TextView) itemView.findViewById(R.id.tv_pending);
            cancel = (TextView) itemView.findViewById(R.id.tv_cancel);
            newStatus = (TextView) itemView.findViewById(R.id.tv_new_status);
            gigsImages = (CircleImageView) itemView.findViewById(R.id.iv_gigs_images);
            sellerName = (TextView) itemView.findViewById(R.id.sellerName);
            gigsOrderId = (TextView) itemView.findViewById(R.id.order_id);
            gigsDDate = (TextView) itemView.findViewById(R.id.delivery_date);
            gigsRate = (TextView) itemView.findViewById(R.id.tv_gigs_rate);
            ctvFeedback = (CustomTextView) itemView.findViewById(R.id.ctv_feedback);
            ctvOrderCancel = (CustomTextView) itemView.findViewById(R.id.ctv_order_cancel);
            ctvOrderStatus = (CustomTextView) itemView.findViewById(R.id.ctv_order_status);
        }
    }


    private void callSeeFedBck(final POSTMyActivity.My_purchase my_purchase) {
        postGigsdetails.put("from_user_id", my_purchase.getUser_id());
        //postGigsdetails.put("to_user_id", SessionHandler.getInstance().get(mContext, AppConstants.TOKEN_ID));
        postGigsdetails.put("gig_id", my_purchase.getGigs_id());
        postGigsdetails.put("order_id", my_purchase.getOrder_id());
        //postGigsdetails.put("user_id", SessionHandler.getInstance().get(mContext, AppConstants.TOKEN_ID));

        if (NetworkChangeReceiver.isConnected()) {
            mCustomProgressDialog.showDialog();
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            apiInterface.postpurchsseFedBck(postGigsdetails, SessionHandler.getInstance().get(mContext, AppConstants.TOKEN_ID), SessionHandler.getInstance().get(mContext, AppConstants.Language)).enqueue(new Callback<POSTPurchaseSeeFedBck>() {
                @Override
                public void onResponse(Call<POSTPurchaseSeeFedBck> call, Response<POSTPurchaseSeeFedBck> response) {

                    if (response.body().getCode().equals(200)) {
                        if (response.body().getData().getUser_feed().size() > 0) {
                            if (mPurchaseSeeFeedbackDialog != null) {
                                mPurchaseSeeFeedbackDialog.setData(my_purchase, 1, response.body().getData().getUser_feed());
                                mPurchaseSeeFeedbackDialog.show(fm, mContext.getResources().getString(R.string.see_feedback));
                            }
                           /* */
                        } else {
                            Utils.toastMessage(mContext, commonStrings.getLbl_no_feedback_found().getName());
                        }

                    } else {
                        Utils.toastMessage(mContext, response.body().getMessage());
                    }

                    mCustomProgressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<POSTPurchaseSeeFedBck> call, Throwable t) {
                    mCustomProgressDialog.dismiss();
                    Log.i("TAG", t.getMessage());
                }
            });


        } else {
            Utils.toastMessage(mContext, commonStrings.getLbl_enable_internet().getName());
        }

    }

    public void setLanguageValues() {
        commonStrings = new Gson().fromJson(SessionHandler.getInstance().get(mContext, AppConstants.COMMONSTRINGS), POSTLanguageModel.Common_strings.class);
        my_activity_screen = new Gson().fromJson(SessionHandler.getInstance().get(mContext, AppConstants.MYACTIVITY), POSTLanguageModel.My_activity_screen.class);
    }

}
