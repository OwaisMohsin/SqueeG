package com.example.co.squeeg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.apache.http.HttpException;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;

import com.example.co.squeeg.Model.POSTChangePassword;
import com.example.co.squeeg.Model.POSTLanguageModel;
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
 * Created by Prasad on 10/25/2017.
 */

public class ChangePassword extends BaseActivity {

    private TextInputLayout textInputLayoutCPassword;
    private TextInputLayout textInputLayoutNPassword;
    private TextInputLayout textInputLayoutRPassword;
    private EditText editCPassword;
    private EditText editNPassword;
    private EditText editRPassword;
    Button btnSubmit;
    private Toolbar mToolbar;
    private CustomTextView ctvToolbar;
    private CustomProgressDialog mCustomProgressDialog;
    private final HashMap<String, String> changePasswordData = new HashMap<String, String>();
    public POSTLanguageModel.Change_password_screen changePasswordScreen = new POSTLanguageModel().new Change_password_screen();
    public POSTLanguageModel.Common_strings commonStrings = new POSTLanguageModel().new Common_strings();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        mToolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        setSupportActionBar(mToolbar);
        mCustomProgressDialog = new CustomProgressDialog(this);
        initLayouts();
        setLanguageValues();

    }

    private void initLayouts() {


        textInputLayoutCPassword = (TextInputLayout) findViewById(R.id.input_layout_cpassword);
        textInputLayoutNPassword = (TextInputLayout) findViewById(R.id.input_layout_npassword);
        textInputLayoutRPassword = (TextInputLayout) findViewById(R.id.input_layout_rpassword);

        editCPassword = (EditText) findViewById(R.id.input_cpassword);
        editNPassword = (EditText) findViewById(R.id.input_npassword);
        editRPassword = (EditText) findViewById(R.id.input_rpassword);
        btnSubmit = (Button) findViewById(R.id.button_cpassword);
        ctvToolbar = (CustomTextView) findViewById(R.id.ctv_toolbar);

    }

    private boolean validateBothPassword() {
        if (!editNPassword.getText().toString().trim().equalsIgnoreCase(editRPassword.getText().toString().trim())) {
            textInputLayoutNPassword.setError(changePasswordScreen.getValidate_both_pwd().getName());
            textInputLayoutRPassword.setError(changePasswordScreen.getValidate_both_pwd().getName());
            requestFocus(editNPassword);
            requestFocus(editRPassword);
            return false;
        } else {
            textInputLayoutRPassword.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (editCPassword.getText().toString().trim().isEmpty()) {
            textInputLayoutCPassword.setError(changePasswordScreen.getLbl_current_pwd().getValidation1());
            requestFocus(editCPassword);
            return false;
        } else {
            textInputLayoutCPassword.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateNPassword() {
        if (editNPassword.getText().toString().trim().isEmpty()) {
            textInputLayoutNPassword.setError(changePasswordScreen.getTxt_fld_new_pwd().getValidation1());
            requestFocus(editNPassword);
            return false;
        } else if (!editNPassword.getText().toString().matches(AppConstants.passwordMatch)) {
            textInputLayoutNPassword.setError(changePasswordScreen.getTxt_fld_new_pwd().getValidation2());
            requestFocus(editNPassword);
            return false;
        } else {
            textInputLayoutNPassword.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateRPassword() {
        if (editRPassword.getText().toString().trim().isEmpty()) {
            textInputLayoutRPassword.setError(changePasswordScreen.getTxt_fld_confirm_pwd().getValidation1());
            requestFocus(editRPassword);
            return false;
        } else if (!editRPassword.getText().toString().matches(AppConstants.passwordMatch)) {
            textInputLayoutRPassword.setError(changePasswordScreen.getTxt_fld_confirm_pwd().getValidation2());
            requestFocus(editRPassword);
            return false;
        } else if (!editRPassword.getText().toString().equalsIgnoreCase(editNPassword.getText().toString())) {
            textInputLayoutRPassword.setError(changePasswordScreen.getTxt_fld_confirm_pwd().getValidation3());
            requestFocus(editRPassword);
            return false;
        } else {
            textInputLayoutRPassword.setErrorEnabled(false);
        }

        return true;
    }


    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public void changePassword(View view) {
        if (!validatePassword()) {
            return;
        }
        if (!validateNPassword()) {
            return;
        }
        if (!validateRPassword()) {
            return;
        }

        if (!validateBothPassword()) {
            return;
        }
        changePasswordData.put("current_password", editCPassword.getText().toString());
        changePasswordData.put("new_password", editNPassword.getText().toString());
        /*changePasswordData.put("id", SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID));*/


        if (NetworkChangeReceiver.isConnected()) {
            mCustomProgressDialog.showDialog();
            postChangePassword();
        } else {
            showToast();
        }
    }

    private void postChangePassword() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.postChangePassword(changePasswordData, SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID), SessionHandler.getInstance().get(this, AppConstants.Language)).enqueue(new Callback<POSTChangePassword>() {
            @Override
            public void onResponse(Call<POSTChangePassword> call, Response<POSTChangePassword> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode().equals(200)) {
                        Toast.makeText(ChangePassword.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    } else if (response.body().getCode().equals(AppConstants.InactiveStatusResponse)) {
                        Utils.createUserInActiceAlert(ChangePassword.this, response.body().getMessage());
                    } else {
                        Toast.makeText(ChangePassword.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else if (response.body().getCode().equals(Integer.parseInt(AppConstants.INVALID_RESPONSE_CODE))) {
                    mCustomProgressDialog.dismiss();
                    NetworkAlertDialog.networkAlertDialog(ChangePassword.this, "", response.body().getMessage(), null, null);
                } else {
                    Log.i("ERROR_CHANGE_PASSWORD", response.errorBody().toString());
                }
                mCustomProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<POSTChangePassword> call, Throwable t) {
                Log.i("TAG", t.getMessage());
                mCustomProgressDialog.dismiss();
                if (t instanceof UnknownHostException || t instanceof HttpException || t instanceof ConnectException || t instanceof SocketTimeoutException || t instanceof SocketException) {
                    //Toast.makeText(ChangePassword.this, getString(R.string.err_server_msg), Toast.LENGTH_SHORT).show();
                    NetworkAlertDialog.networkAlertDialog(ChangePassword.this, commonStrings.getLbl_network_err().getName(),
                            commonStrings.getLbl_server_err().getName(), changepwdRunn, null);

                }
            }
        });
    }

    Runnable changepwdRunn = new Runnable() {
        @Override
        public void run() {
            //TODO: API call
            postChangePassword();
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    private void showToast() {
        Toast.makeText(this, commonStrings.getLbl_enable_internet().getName(), Toast.LENGTH_SHORT).show();
    }


    public void setLanguageValues() {

        changePasswordScreen = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.CHANGEPASSWORD), POSTLanguageModel.Change_password_screen.class);
        commonStrings = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.COMMONSTRINGS), POSTLanguageModel.Common_strings.class);
        textInputLayoutCPassword.setHint(changePasswordScreen.getLbl_current_pwd().getName());
        textInputLayoutNPassword.setHint(changePasswordScreen.getTxt_fld_new_pwd().getName());
        textInputLayoutRPassword.setHint(changePasswordScreen.getTxt_fld_confirm_pwd().getName());
        btnSubmit.setText(changePasswordScreen.getBtn_submit().getName());
//        mToolbar.setTitle(changePasswordScreen.getLbl_change_password().getName());
        ctvToolbar.setText(changePasswordScreen.getLbl_change_password().getName());
    }
}
