package com.example.co.squeeg.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;

import com.example.co.squeeg.Model.POSTCancelGigs;
import com.example.co.squeeg.Model.POSTLanguageModel;
import com.example.co.squeeg.R;
import com.example.co.squeeg.fragment.MyPurchasesFragment;
import com.example.co.squeeg.interfaces.CancelRequestDialog;
import com.example.co.squeeg.interfaces.UpdateRequestData;
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
 * Created by Prasad on 11/8/2017.
 */

public class PurchaseCancelDialogFragment extends DialogFragment implements CancelRequestDialog {

    private EditText editReason, editPaypalEID, editStripeReason, editAcc_Holder_name, editAcc_num, editIBAN, editBankName, editBankAddress, editSortCode, editRoutingNum,
            editIFSCCode;
    private Button btnUpdate;
    private TextView textError;
    private CustomTextView ctvCancelDialog;
    private TextInputLayout tilReason;
    String product_id = "", source = "";
    private HashMap<String, String> postCancelData = new HashMap<String, String>();
    private HashMap<String, String> postStripeCancelData = new HashMap<String, String>();
    private Context mContext;
    private CustomProgressDialog mCustomProgressDialog;
    private static UpdateRequestData callbacks;
    View rootView;
    ApiInterface apiInterface;
    public POSTLanguageModel.Common_strings commonStrings = new POSTLanguageModel().new Common_strings();
    public POSTLanguageModel.My_activity_screen my_activity_screen = new POSTLanguageModel().new My_activity_screen();

    public PurchaseCancelDialogFragment(Context mContext) {
        this.mContext = mContext;
        mCustomProgressDialog = new CustomProgressDialog(mContext);
    }

    public PurchaseCancelDialogFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        getDialog().setTitle("");
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCancelable(false);
        rootView = inflater.inflate(R.layout.cancel_dialog_fragment, container,
                false);
        setLanguageValues();
        editReason = (EditText) rootView.findViewById(R.id.input_reason);
        editPaypalEID = (EditText) rootView.findViewById(R.id.input_paypal_email);
        btnUpdate = (Button) rootView.findViewById(R.id.btn_update_request);
        textError = (TextView) rootView.findViewById(R.id.tv_details_err);
        ctvCancelDialog = (CustomTextView) rootView.findViewById(R.id.customTextView);
        tilReason=(TextInputLayout)rootView.findViewById(R.id.input_layout_reason);
        ctvCancelDialog.setText(my_activity_screen.getTxt_fld_cancel_order().getName());
        tilReason.setHint(my_activity_screen.getErr_enter_reason().getName());
        editReason.setHint(my_activity_screen.getErr_enter_reason().getName());
        textError.setText(my_activity_screen.getErr_add_stripe().getName());
        btnUpdate.setText(my_activity_screen.getTxt_fld_update().getName());
        if (source.equalsIgnoreCase("stripe")) {
            editPaypalEID.setVisibility(View.GONE);
            if (SessionHandler.getInstance().get(mContext, AppConstants.stripe_Acc_name) == null) {
                textError.setVisibility(View.VISIBLE);
            }
        }
        if (SessionHandler.getInstance().get(mContext, AppConstants.PayPal_User_Email) != null) {
            editPaypalEID.setText(SessionHandler.getInstance().get(mContext, AppConstants.PayPal_User_Email));
        }
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (source.equalsIgnoreCase("stripe")) {
                    if (editReason.getText().toString().isEmpty()) {
                        editReason.setError(my_activity_screen.getErr_enter_reason().getName());
                        editReason.requestFocus();
                        return;
                    } else if (SessionHandler.getInstance().get(mContext, AppConstants.stripe_Acc_name) == null) {
                        Toast.makeText(mContext, my_activity_screen.getErr_enter_stripe_details().getName(), Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        postCancelData.put("order_id", product_id);
                        postCancelData.put("cancel_reason", editReason.getText().toString());
                        //postCancelData.put("user_id", SessionHandler.getInstance().get(mContext, AppConstants.TOKEN_ID));
                        postCancelData.put("time_zone", SessionHandler.getInstance().get(mContext, AppConstants.TIMEZONE_ID));
                        postCancelData.put("cancel_by", "stripe");
                        callCancelApi();
                    }
                    return;
                } else if (source.equalsIgnoreCase("paypal")) {
                    if (editReason.getText().toString().isEmpty()) {
                        editReason.setError("Enter reason");
                        editReason.requestFocus();
                        return;
                    } else if (editPaypalEID.getText().toString().isEmpty()) {
                        editPaypalEID.setError("Enter PayPal id");
                        editPaypalEID.requestFocus();
                        return;
                    } else if (!Utils.isValidEmail(editPaypalEID.getText().toString())) {
                        Toast.makeText(mContext, "Enter valid Email Address", Toast.LENGTH_SHORT).show();
                    } else {
                        postCancelData.put("order_id", product_id);
                        postCancelData.put("cancel_reason", editReason.getText().toString());
                        postCancelData.put("paypal_email", editPaypalEID.getText().toString());
                        //postCancelData.put("user_id", SessionHandler.getInstance().get(mContext, AppConstants.TOKEN_ID));
                        postCancelData.put("time_zone", SessionHandler.getInstance().get(mContext, AppConstants.TIMEZONE_ID));
                        postCancelData.put("cancel_by", "paypal");
                        callCancelApi();
                    }
                    return;
                }
            }
        });


        /*if (source.equalsIgnoreCase("stripe")) {
            rootView = inflater.inflate(R.layout.cancel_dialog_fragment, container,
                    false);
            btnUpdate = (Button) rootView.findViewById(R.id.bt_update);
            editStripeReason = (EditText) rootView.findViewById(R.id.et_reason);
            *//*editAcc_Holder_name = (EditText) rootView.findViewById(R.id.et_acc_holder_name);
            editAcc_num = (EditText) rootView.findViewById(R.id.et_acc_num);
            editIBAN = (EditText) rootView.findViewById(R.id.et_IBAN_num);
            editBankName = (EditText) rootView.findViewById(R.id.et_bank_name);
            editBankAddress = (EditText) rootView.findViewById(R.id.et_bank_addr);
            editSortCode = (EditText) rootView.findViewById(R.id.et_sort_code);
            editRoutingNum = (EditText) rootView.findViewById(R.id.et_routing_num);
            editIFSCCode = (EditText) rootView.findViewById(R.id.et_IFSC_code);*//*

            *//*if (SessionHandler.getInstance().get(mContext, AppConstants.stripe_Acc_name) != null) {
                editAcc_Holder_name.setText(SessionHandler.getInstance().get(mContext, AppConstants.stripe_Acc_name));
                editAcc_num.setText(SessionHandler.getInstance().get(mContext, AppConstants.stripe_Acc_num));
                editIBAN.setText(SessionHandler.getInstance().get(mContext, AppConstants.stripe_Iban));
                editBankName.setText(SessionHandler.getInstance().get(mContext, AppConstants.stripe_bank_name));
                editBankAddress.setText(SessionHandler.getInstance().get(mContext, AppConstants.stripe_bank_address));
                editSortCode.setText(SessionHandler.getInstance().get(mContext, AppConstants.stripe_sort_code));
                editRoutingNum.setText(SessionHandler.getInstance().get(mContext, AppConstants.stripe_routing_number));
                editIFSCCode.setText(SessionHandler.getInstance().get(mContext, AppConstants.stripe_account_ifsc));
            }*//*

            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (editStripeReason.getText().toString().isEmpty()) {
                        editStripeReason.setError(getString(R.string.stripe_reason));
                        editStripeReason.requestFocus();
                    } *//*else if (editAcc_Holder_name.getText().toString().isEmpty()) {
                        editAcc_Holder_name.setError(getString(R.string.err_stripe_acc_holder_name));
                        editAcc_Holder_name.requestFocus();
                    } else if (editAcc_num.getText().toString().isEmpty()) {
                        editAcc_num.setError(getString(R.string.err_stripe_acc_num));
                        editAcc_num.requestFocus();
                    } else if (editBankName.getText().toString().isEmpty()) {
                        editBankName.setError(getString(R.string.err_stripe_bank_name));
                        editBankName.requestFocus();
                    } else if (editBankAddress.getText().toString().isEmpty()) {
                        editBankAddress.setError(getString(R.string.err_stripe_bank_addr));
                        editBankAddress.requestFocus();
                    } else if (editSortCode.getText().toString().isEmpty()) {
                        editSortCode.setError(getString(R.string.err_stripe_sort_code));
                        editSortCode.requestFocus();
                    } else if (editRoutingNum.getText().toString().isEmpty()) {
                        editRoutingNum.setError(getString(R.string.err_stripe_routing_num));
                        editRoutingNum.requestFocus();
                    } else if (editIFSCCode.getText().toString().isEmpty()) {
                        editIFSCCode.setError(getString(R.string.err_stripe_IFSC_code));
                        editIFSCCode.requestFocus();
                    } *//* else {
                        mCustomProgressDialog.showDialog();
                        postStripeCancelData.put("time_zone", SessionHandler.getInstance().get(mContext, AppConstants.TIMEZONE_ID));
                        postStripeCancelData.put("order_id", product_id);
                        postStripeCancelData.put("cancel_reason", editStripeReason.getText().toString());
                        postStripeCancelData.put("user_id", SessionHandler.getInstance().get(mContext, AppConstants.TOKEN_ID));
                        postStripeCancelData.put("cancel_by", "stripe");

                        *//*postStripeCancelData.put("account_holder_name", editAcc_Holder_name.getText().toString());
                        postStripeCancelData.put("account_number", editAcc_num.getText().toString());
                        postStripeCancelData.put("account_iban", editIBAN.getText().toString());
                        postStripeCancelData.put("bank_name", editBankName.getText().toString());
                        postStripeCancelData.put("bank_address", editBankAddress.getText().toString());
                        postStripeCancelData.put("sort_code", editSortCode.getText().toString());
                        postStripeCancelData.put("routing_number", editRoutingNum.getText().toString());
                        postStripeCancelData.put("account_ifsc", editIFSCCode.getText().toString());*//*
                        apiInterface.postStripeCancel(postStripeCancelData).enqueue(new Callback<POSTPaymentSuccess>() {
                            @Override
                            public void onResponse(Call<POSTPaymentSuccess> call, Response<POSTPaymentSuccess> response) {
                                if (response.body().getCode().equals(200)) {
                                    Toast.makeText(mContext, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                    callbacks.updateRequestData(product_id);
                                    getDialog().dismiss();
                                } else {
                                    Toast.makeText(mContext, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                                mCustomProgressDialog.dismiss();
                            }

                            @Override
                            public void onFailure(Call<POSTPaymentSuccess> call, Throwable t) {
                                mCustomProgressDialog.dismiss();
                            }
                        });
                    }
                }
            });


        } else {
            rootView = inflater.inflate(R.layout.cancel_dialog_fragment, container,
                    false);

            editReason = (EditText) rootView.findViewById(R.id.input_reason);
            editPaypalEID = (EditText) rootView.findViewById(R.id.input_paypal_email);
            btnUpdate = (Button) rootView.findViewById(R.id.btn_update_request);

            if (SessionHandler.getInstance().get(mContext, AppConstants.PayPal_User_Email) != null) {
                editPaypalEID.setText(SessionHandler.getInstance().get(mContext, AppConstants.PayPal_User_Email));
            }
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (editReason.getText().toString().isEmpty()) {
                        editReason.setError("Enter reason");
                        editReason.requestFocus();
                        return;
                    }
                    if (editPaypalEID.getText().toString().isEmpty()) {
                        editPaypalEID.setError("Enter paypalID");
                        editPaypalEID.requestFocus();
                        return;
                    }

                    if (!Patterns.EMAIL_ADDRESS.matcher(editPaypalEID.getText().toString()).matches()) {
                        editPaypalEID.setError("Invaild Email Id");
                        editPaypalEID.requestFocus();
                        return;
                    }
                    postCancelData.put("order_id", product_id);
                    postCancelData.put("cancel_reason", editReason.getText().toString());
                    postCancelData.put("paypal_email", editPaypalEID.getText().toString());
                    postCancelData.put("user_id", SessionHandler.getInstance().get(mContext, AppConstants.TOKEN_ID));
                    postCancelData.put("time_zone", SessionHandler.getInstance().get(mContext, AppConstants.TIMEZONE_ID));

                    if (NetworkChangeReceiver.isConnected()) {
                        mCustomProgressDialog.showDialog();
                        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                        apiInterface.purchaseCancelGigs(postCancelData).enqueue(new Callback<POSTCancelGigs>() {
                            @Override
                            public void onResponse(Call<POSTCancelGigs> call, Response<POSTCancelGigs> response) {
                                if (response.body().getCode().equals(200)) {
                                    Toast.makeText(mContext, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                    callbacks.updateRequestData(product_id);
                                } else {
                                    Toast.makeText(mContext, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                                mCustomProgressDialog.dismiss();
                            }

                            @Override
                            public void onFailure(Call<POSTCancelGigs> call, Throwable t) {
                                Log.i("TAG", t.getMessage());
                                mCustomProgressDialog.dismiss();
                            }
                        });
                    }


                    getDialog().dismiss();

                }
            });
        }*/

        return rootView;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (editReason != null) {
            editReason.setText("");
        }
    }

    public void callCancelApi() {
        if (NetworkChangeReceiver.isConnected()) {
            mCustomProgressDialog.showDialog();
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            apiInterface.purchaseCancelGigs(postCancelData, SessionHandler.getInstance().get(mContext, AppConstants.TOKEN_ID), SessionHandler.getInstance().get(getActivity(), AppConstants.Language)).enqueue(new Callback<POSTCancelGigs>() {
                @Override
                public void onResponse(Call<POSTCancelGigs> call, Response<POSTCancelGigs> response) {
                    if (response.body().getCode().equals(200)) {
                        Toast.makeText(mContext, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        SessionHandler.getInstance().save(mContext, AppConstants.PayPal_User_Email, editPaypalEID.getText().toString());
                        callbacks.updateRequestData(product_id);
                    } else if (response.body().getCode().equals(AppConstants.INVALID_RESPONSE_CODE)) {
//                    mCustomProgressDialog.dismiss();
                        NetworkAlertDialog.networkAlertDialog(mContext, "", response.body().getMessage(), null, null);
                    } else {
                        Toast.makeText(mContext, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    mCustomProgressDialog.dismiss();
                    getDialog().dismiss();
                }

                @Override
                public void onFailure(Call<POSTCancelGigs> call, Throwable t) {
                    Log.i("TAG", t.getMessage());
                    mCustomProgressDialog.dismiss();
                }
            });
        }
    }


    @Override
    public void cancelRequestData(String product_id, String source) {
        this.product_id = product_id;
        this.source = source;
    }

    public static void callDialgFrag(MyPurchasesFragment mMyPurchasesFragment) {
        callbacks = mMyPurchasesFragment;
    }

    public void setLanguageValues() {
        commonStrings = new Gson().fromJson(SessionHandler.getInstance().get(getActivity(), AppConstants.COMMONSTRINGS), POSTLanguageModel.Common_strings.class);
        my_activity_screen = new Gson().fromJson(SessionHandler.getInstance().get(getActivity(), AppConstants.MYACTIVITY), POSTLanguageModel.My_activity_screen.class);
    }
}
