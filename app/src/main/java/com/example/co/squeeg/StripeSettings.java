package com.example.co.squeeg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.co.squeeg.Model.POSTLanguageModel;
import com.example.co.squeeg.Model.POSTPaymentSuccess;
import com.example.co.squeeg.network.ApiClient;
import com.example.co.squeeg.network.ApiInterface;
import com.example.co.squeeg.utils.AppConstants;
import com.example.co.squeeg.utils.CustomButton;
import com.example.co.squeeg.utils.CustomEditext;
import com.example.co.squeeg.utils.CustomProgressDialog;
import com.example.co.squeeg.utils.CustomTextView;
import com.example.co.squeeg.utils.NetworkAlertDialog;
import com.example.co.squeeg.utils.SessionHandler;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StripeSettings extends BaseActivity {


    @BindView(R.id.tb_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.et_acc_holder_name)
    CustomEditext editAcc_Holder_name;
    @BindView(R.id.et_acc_num)
    CustomEditext editAcc_num;
    @BindView(R.id.et_IBAN_num)
    CustomEditext editIBAN;
    @BindView(R.id.et_bank_name)
    CustomEditext editBankName;
    @BindView(R.id.et_bank_addr)
    CustomEditext editBankAddress;
    @BindView(R.id.et_sort_code)
    CustomEditext editSortCode;
    @BindView(R.id.et_routing_num)
    CustomEditext editRoutingNum;
    @BindView(R.id.et_IFSC_code)
    CustomEditext editIFSCCode;
    @BindView(R.id.bt_update)
    CustomButton btUpdate;
    @BindView(R.id.ctv_acc_holder_name)
    CustomTextView ctvAccHolderName;
    @BindView(R.id.ctv_acc_num)
    CustomTextView ctvAccNum;
    @BindView(R.id.ctv_iban)
    CustomTextView ctvIban;
    @BindView(R.id.ctv_bank_name)
    CustomTextView ctvBankName;
    @BindView(R.id.ctv_bank_address)
    CustomTextView ctvBankAddress;
    @BindView(R.id.ctv_sort_code)
    CustomTextView ctvSortCode;
    @BindView(R.id.ctv_swift_code)
    CustomTextView ctvSwiftCode;
    @BindView(R.id.ctv_ifsc_code)
    CustomTextView ctvIfscCode;
    private CustomProgressDialog mCustomProgressDialog;
    private HashMap<String, String> postStripeRegisterData = new HashMap<String, String>();
    public POSTLanguageModel.Stripe_payment_screen stripePaymentScreen = new POSTLanguageModel().new Stripe_payment_screen();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stripe_settings);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        setLanguageValues();
        mCustomProgressDialog = new CustomProgressDialog(this);
        if (SessionHandler.getInstance().get(StripeSettings.this, AppConstants.stripe_Acc_name) != null) {
            editAcc_Holder_name.setText(SessionHandler.getInstance().get(StripeSettings.this, AppConstants.stripe_Acc_name));
            editAcc_num.setText(SessionHandler.getInstance().get(StripeSettings.this, AppConstants.stripe_Acc_num));
            if (SessionHandler.getInstance().get(StripeSettings.this, AppConstants.stripe_Iban) != null && !SessionHandler.getInstance().get(StripeSettings.this, AppConstants.stripe_Iban).isEmpty()) {
                editIBAN.setText(SessionHandler.getInstance().get(StripeSettings.this, AppConstants.stripe_Iban));
            }
            editBankName.setText(SessionHandler.getInstance().get(StripeSettings.this, AppConstants.stripe_bank_name));
            editBankAddress.setText(SessionHandler.getInstance().get(StripeSettings.this, AppConstants.stripe_bank_address));
            editSortCode.setText(SessionHandler.getInstance().get(StripeSettings.this, AppConstants.stripe_sort_code));
            editRoutingNum.setText(SessionHandler.getInstance().get(StripeSettings.this, AppConstants.stripe_routing_number));
            editIFSCCode.setText(SessionHandler.getInstance().get(StripeSettings.this, AppConstants.stripe_account_ifsc));
        } else {
//            btUpdate.setText("SAVE");
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.bt_update)
    public void onViewClicked() {
        if (editAcc_Holder_name.getText().toString().isEmpty()) {
            editAcc_Holder_name.setError(stripePaymentScreen.getTxt_fld_acc_name().getValidation1());
            editAcc_Holder_name.requestFocus();
        } else if (editAcc_num.getText().toString().isEmpty()) {
            editAcc_num.setError(stripePaymentScreen.getTxt_fld_acc_num().getValidation1());
            editAcc_num.requestFocus();
        } else if (editBankName.getText().toString().isEmpty()) {
            editBankName.setError(stripePaymentScreen.getTxt_fld_bank_name().getValidation1());
            editBankName.requestFocus();
        } else if (editBankAddress.getText().toString().isEmpty()) {
            editBankAddress.setError(stripePaymentScreen.getTxt_fld_bank_addr().getValidation1());
            editBankAddress.requestFocus();
        } else if (editSortCode.getText().toString().isEmpty() && editRoutingNum.getText().toString().isEmpty() && editIFSCCode.getText().toString().isEmpty()) {
            Toast.makeText(this, stripePaymentScreen.getBtn_update().getValidation1(), Toast.LENGTH_SHORT).show();
        } else {
            mCustomProgressDialog.showDialog();
            postStripeRegisterData.put("time_zone", SessionHandler.getInstance().get(StripeSettings.this, AppConstants.TIMEZONE_ID));
            //postStripeRegisterData.put("user_id", SessionHandler.getInstance().get(StripeSettings.this, AppConstants.TOKEN_ID));
            postStripeRegisterData.put("account_holder_name", editAcc_Holder_name.getText().toString());
            postStripeRegisterData.put("account_number", editAcc_num.getText().toString());
            postStripeRegisterData.put("account_iban", editIBAN.getText().toString());
            postStripeRegisterData.put("bank_name", editBankName.getText().toString());
            postStripeRegisterData.put("bank_address", editBankAddress.getText().toString());
            postStripeRegisterData.put("sort_code", editSortCode.getText().toString());
            postStripeRegisterData.put("routing_number", editRoutingNum.getText().toString());
            postStripeRegisterData.put("account_ifsc", editIFSCCode.getText().toString());
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            apiInterface.postRegisterStripeDetails(postStripeRegisterData, SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID), SessionHandler.getInstance().get(this, AppConstants.Language)).enqueue(new Callback<POSTPaymentSuccess>() {
                @Override
                public void onResponse(Call<POSTPaymentSuccess> call, Response<POSTPaymentSuccess> response) {
                    if (response.body().getCode().equals(200)) {
                        mCustomProgressDialog.dismiss();
                        SessionHandler.getInstance().save(StripeSettings.this, AppConstants.stripe_Acc_name, editAcc_Holder_name.getText().toString());
                        SessionHandler.getInstance().save(StripeSettings.this, AppConstants.stripe_Acc_num, editAcc_num.getText().toString());
                        SessionHandler.getInstance().save(StripeSettings.this, AppConstants.stripe_Iban, editIBAN.getText().toString());
                        SessionHandler.getInstance().save(StripeSettings.this, AppConstants.stripe_bank_name, editBankName.getText().toString());
                        SessionHandler.getInstance().save(StripeSettings.this, AppConstants.stripe_bank_address, editBankAddress.getText().toString());
                        SessionHandler.getInstance().save(StripeSettings.this, AppConstants.stripe_sort_code, editSortCode.getText().toString());
                        SessionHandler.getInstance().save(StripeSettings.this, AppConstants.stripe_routing_number, editRoutingNum.getText().toString());
                        SessionHandler.getInstance().save(StripeSettings.this, AppConstants.stripe_account_ifsc, editIFSCCode.getText().toString());
                        finish();
                    } else if (response.body().getCode().equals(AppConstants.INVALID_RESPONSE_CODE)) {
                        mCustomProgressDialog.dismiss();
                        NetworkAlertDialog.networkAlertDialog(StripeSettings.this, "", response.body().getMessage(), null, null);
                    } else {
                        mCustomProgressDialog.dismiss();
                        Toast.makeText(StripeSettings.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<POSTPaymentSuccess> call, Throwable t) {
                    mCustomProgressDialog.dismiss();
                }
            });
        }

    }


    public void setLanguageValues() {
        stripePaymentScreen = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.STRIPEPAYMENT), POSTLanguageModel.Stripe_payment_screen.class);
        ctvAccHolderName.setText(stripePaymentScreen.getTxt_fld_acc_name().getName());
        ctvAccNum.setText(stripePaymentScreen.getTxt_fld_acc_num().getName());
        ctvBankAddress.setText(stripePaymentScreen.getTxt_fld_bank_addr().getName());
        ctvBankName.setText(stripePaymentScreen.getTxt_fld_bank_name().getName());
        ctvIban.setText(stripePaymentScreen.getTxt_fld_IBan().getName());
        ctvIfscCode.setText(stripePaymentScreen.getTxt_fld_ifsc_code().getName());
        ctvSortCode.setText(stripePaymentScreen.getTxt_fld_sort_code().getName());
        ctvSwiftCode.setText(stripePaymentScreen.getTxt_fld_swift_num().getName());
        btUpdate.setText(stripePaymentScreen.getBtn_update().getName());
    }

}
