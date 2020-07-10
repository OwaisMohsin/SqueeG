package com.example.co.squeeg;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.co.squeeg.Model.POSTForgotPassword;
import com.example.co.squeeg.Model.POSTLanguageModel;
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
 * Created by Prasad on 10/24/2017.
 */

public class ForgotPassword extends BaseActivity {
    @BindView(R.id.tv_know_pwd)
    CustomTextView tvKnowPwd;
    @BindView(R.id.tv_login)
    CustomTextView tvLogin;
    @BindView(R.id.tb_forgot_pwd)
    CustomTextView tbForgotPwd;
    private TextInputLayout textInputLayoutEmail;
    private EditText editEmail;
    private Button btnSubmit;
    private CustomProgressDialog mCustomProgressDialog;
    private final HashMap<String, String> forgotData = new HashMap<String, String>();
    private Toolbar mToolbar;
    public POSTLanguageModel.Common_strings commonStrings = new POSTLanguageModel().new Common_strings();
    public POSTLanguageModel.Forgot_password_screen forgotPasswordScreen = new POSTLanguageModel().new Forgot_password_screen();
    public POSTLanguageModel.Register_screen registerScreen = new POSTLanguageModel().new Register_screen();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);
        mToolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        setSupportActionBar(mToolbar);
        mCustomProgressDialog = new CustomProgressDialog(this);
        initLayouts();
        editEmail.addTextChangedListener(new ForgetPasswordTextWatcher(editEmail));
    }

    private void initLayouts() {
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        editEmail = (EditText) findViewById(R.id.input_email);
        btnSubmit = (Button) findViewById(R.id.button_login);
        setLanguageValues();
    }

    public void goToLogin(View view) {
        startActivity(new Intent(this, LoginScreen.class));
    }

    public void submitForgot(View view) {
        if (!validateEmail()) {
            return;
        }
        forgotData.put("forget_email", editEmail.getText().toString());

        if (NetworkChangeReceiver.isConnected()) {
            mCustomProgressDialog.showDialog();
            postForgotPassword();
        } else {
            showToast();
        }
    }

    private void postForgotPassword() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.postForgot(forgotData, AppConstants.GUEST_TOKEN, SessionHandler.getInstance().get(this, AppConstants.Language)).enqueue(new Callback<POSTForgotPassword>() {
            @Override
            public void onResponse(Call<POSTForgotPassword> call, Response<POSTForgotPassword> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode().equals(200)) {
                        Toast.makeText(ForgotPassword.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(ForgotPassword.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.i("ERROR_FORGOT", response.errorBody().toString());
                }
                mCustomProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<POSTForgotPassword> call, Throwable t) {
                Log.i("TAG", t.getMessage());
                mCustomProgressDialog.dismiss();
                if (t instanceof UnknownHostException || t instanceof HttpException || t instanceof ConnectException || t instanceof SocketTimeoutException || t instanceof SocketException) {
                    Toast.makeText(ForgotPassword.this, commonStrings.getLbl_server_prblm().getName(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean validateEmail() {
        String email = editEmail.getText().toString().trim();

        if (email.isEmpty() || !Utils.isValidEmail(email)) {
            textInputLayoutEmail.setError(forgotPasswordScreen.getTxt_fld_email().getValidation1());
            requestFocus(editEmail);
            return false;
        } else {
            textInputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private class ForgetPasswordTextWatcher implements TextWatcher {

        private View view;

        private ForgetPasswordTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (view.getId() == R.id.input_email) {
                validateEmail();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (view.getId() == R.id.input_email) {
                if (editEmail.getText().toString().isEmpty()) {
                    textInputLayoutEmail.setErrorEnabled(false);
                    textInputLayoutEmail.requestFocus();
                    textInputLayoutEmail.setError(null);
                }
            }
        }
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private void showToast() {
        Toast.makeText(this, commonStrings.getLbl_enable_internet().getName(), Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    public void setLanguageValues() {
        registerScreen = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.REGISTER), POSTLanguageModel.Register_screen.class);
        forgotPasswordScreen = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.FORGOTPASSWORD), POSTLanguageModel.Forgot_password_screen.class);
        commonStrings = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.COMMONSTRINGS), POSTLanguageModel.Common_strings.class);
        editEmail.setHint(forgotPasswordScreen.getTxt_fld_email().getName());
        textInputLayoutEmail.setHint(forgotPasswordScreen.getTxt_fld_email().getName());
        mToolbar.setTitle(forgotPasswordScreen.getLbl_forgot_password().getName());
        btnSubmit.setText(registerScreen.getBtn_submit().getName());
        tbForgotPwd.setText(forgotPasswordScreen.getLbl_forgot_password().getName());
        tvLogin.setText(forgotPasswordScreen.getLbl_login().getName());
        tvKnowPwd.setText(forgotPasswordScreen.getLbl_fpwd_info().getName());

    }

}
