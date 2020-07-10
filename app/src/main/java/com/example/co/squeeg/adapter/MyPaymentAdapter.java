package com.example.co.squeeg.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import com.example.co.squeeg.Model.POSTAcceptBuyRequest;
import com.example.co.squeeg.Model.POSTLanguageModel;
import com.example.co.squeeg.Model.POSTMyActivity;
import com.example.co.squeeg.R;
import com.example.co.squeeg.dialog.PurchaseCancelDialogFragment;
import com.example.co.squeeg.dialog.SaleStatusDialog;
import com.example.co.squeeg.dialog.SalesSeeFeedbackDialog;
import com.example.co.squeeg.interfaces.UpdateRequestData;
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

/**
 * Created by Prasad on 10/25/2017.
 */

public class MyPaymentAdapter extends RecyclerView.Adapter<MyPaymentAdapter.MyViewHolder> {
    private Context mContext;
    private List<POSTMyActivity.My_payment> data;
    private HashMap<String, String> postDetails;
    private PurchaseCancelDialogFragment mPurchaseCancelDialogFragment;
    private FragmentManager fm;
    private UpdateRequestData callbacks;
    private SalesSeeFeedbackDialog mSalesSeeFeedbackDialog;
    private HashMap<String, String> postGigsdetails;
    private CustomProgressDialog mCustomProgressDialog;
    private SaleStatusDialog mSaleStatusDialog;
    private TextView currentAmount;
    private int VIEW_NO = 0;
    String WalletBalance;
    int value;

    private EditText editReason, editPaypalEID, editStripeReason, editAcc_Holder_name, editAcc_num, editIBAN, editBankName, editBankAddress, editSortCode, editRoutingNum,
            editIFSCCode;
    private Button btnUpdate;
    private TextView reasonTitle;
    public POSTLanguageModel.Common_strings commonStrings = new POSTLanguageModel().new Common_strings();
    public POSTLanguageModel.My_activity_screen my_activity_screen = new POSTLanguageModel().new My_activity_screen();


    public MyPaymentAdapter(Context mContext, List<POSTMyActivity.My_payment> data, TextView currentAmount, String WalletBalance, FragmentManager fm) {
        this.mContext = mContext;
        this.data = data;
        this.currentAmount = currentAmount;
        this.fm = fm;
        this.WalletBalance = WalletBalance;
        postGigsdetails = new HashMap<String, String>();
        mCustomProgressDialog = new CustomProgressDialog(mContext);
        mPurchaseCancelDialogFragment = new PurchaseCancelDialogFragment(mContext);
        mSalesSeeFeedbackDialog = new SalesSeeFeedbackDialog(mContext);
        mSaleStatusDialog = new SaleStatusDialog(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_my_payment, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        setLanguageValues();
        Picasso.get().load(AppConstants.BASE_URL + data.get(position).getGig_image_thumb()).placeholder(R.drawable.no_image).into(holder.gigsImages);
        //holder.gigsImages.setImageURI(Uri.parse(AppConstants.BASE_URL + data.get(position).getGig_image_thumb()));
        holder.sellerName.setText(my_activity_screen.getLbl_buyser_name().getName() + data.get(position).getBuyer_name());
        holder.gigsDDate.setText(my_activity_screen.getLbl_delivery_date().getName() + data.get(position).getDelivery_date());
        holder.gigsOrderId.setText(my_activity_screen.getLbl_order_id().getName() + data.get(position).getOrder_id());
        holder.gigsTitle.setText(data.get(position).getTitle());
        holder.postedDate.setText(data.get(position).getCreated_date());
        holder.gigsRate.setText(AppConstants.DOLLAR_SIGN + " " + data.get(position).getAmount());
        if (data.get(position).getWithdraw_val().equalsIgnoreCase("1")) {
            holder.withdrawAmout.setText(data.get(position).getWithdraw_message());
        } else if (data.get(position).getWithdraw_val().equalsIgnoreCase("2")) {
            holder.withdrawAmout.setText(data.get(position).getWithdraw_message());
        } else {
            holder.withdrawAmout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   /* if (data.get(position).getSource().equalsIgnoreCase("stripe")) {
//                        View view = View.inflate(mContext, R.layout.dialog_cancel_stripe_pay_act, null);
                        Dialog rootView = new Dialog(mContext);
                        rootView.requestWindowFeature(Window.FEATURE_NO_TITLE);
                        rootView.setCancelable(true);
                        rootView.setContentView(R.layout.dialog_cancel_stripe_pay_act);

                        btnUpdate = (Button) rootView.findViewById(R.id.bt_update);
                        editStripeReason = (EditText) rootView.findViewById(R.id.et_reason);
                        editAcc_Holder_name = (EditText) rootView.findViewById(R.id.et_acc_holder_name);
                        editAcc_num = (EditText) rootView.findViewById(R.id.et_acc_num);
                        editIBAN = (EditText) rootView.findViewById(R.id.et_IBAN_num);
                        editBankName = (EditText) rootView.findViewById(R.id.et_bank_name);
                        editBankAddress = (EditText) rootView.findViewById(R.id.et_bank_addr);
                        editSortCode = (EditText) rootView.findViewById(R.id.et_sort_code);
                        editRoutingNum = (EditText) rootView.findViewById(R.id.et_routing_num);
                        editIFSCCode = (EditText) rootView.findViewById(R.id.et_IFSC_code);
                        reasonTitle = (TextView) rootView.findViewById(R.id.tv_reason_title);
                        reasonTitle.setVisibility(View.GONE);
                        editStripeReason.setVisibility(View.GONE);
                        rootView.show();
                    }*/

                    postGigsdetails.put("order_id", data.get(position).getOrder_id());
                    if (data.get(position).getSource().equalsIgnoreCase("stripe")) {
                        if (SessionHandler.getInstance().get(mContext, AppConstants.stripe_Acc_num) != null) {
                            callWithdrawApi(position);
                        } else {
                            Toast.makeText(mContext, my_activity_screen.getErr_add_stripe().getName(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        callWithdrawApi(position);
                    }


                }
            });
        }


    }


    public void callWithdrawApi(final int position) {
        if (NetworkChangeReceiver.isConnected()) {
            mCustomProgressDialog.showDialog();
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            apiInterface.postWithdrawRequest(postGigsdetails, SessionHandler.getInstance().get(mContext, AppConstants.TOKEN_ID), SessionHandler.getInstance().get(mContext, AppConstants.Language)).enqueue(new Callback<POSTAcceptBuyRequest>() {
                @Override
                public void onResponse(Call<POSTAcceptBuyRequest> call, Response<POSTAcceptBuyRequest> response) {
                    if (response.body().getCode().equals(200)) {
                        data.get(position).setWithdraw_val("1");
                        data.get(position).setWithdraw_message(my_activity_screen.getLbl_request_sent().getName());
                        notifyItemChanged(position);

                        Utils.toastMessage(mContext, response.body().getMessage());
                    } else {
                        Utils.toastMessage(mContext, response.body().getMessage());
                    }
                    mCustomProgressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<POSTAcceptBuyRequest> call, Throwable t) {
                    Log.i("TAG", t.getMessage());
                    mCustomProgressDialog.dismiss();
                }
            });
        } else {
            Utils.toastMessage(mContext, commonStrings.getLbl_enable_internet().getName());
        }
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
        final CircleImageView gigsImages;
        final Button withdrawAmout;

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
            withdrawAmout = (Button) itemView.findViewById(R.id.amount_request);
        }
    }

    public void setLanguageValues() {
        commonStrings = new Gson().fromJson(SessionHandler.getInstance().get(mContext, AppConstants.COMMONSTRINGS), POSTLanguageModel.Common_strings.class);
        my_activity_screen = new Gson().fromJson(SessionHandler.getInstance().get(mContext, AppConstants.MYACTIVITY), POSTLanguageModel.My_activity_screen.class);
    }
}
