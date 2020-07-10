package com.example.co.squeeg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpException;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.HashMap;

import com.example.co.squeeg.Model.POSTPaypalSettings;
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
 * Created by Prasad on 10/25/2017.
 */

public class PaypalSettings extends BaseActivity {
    private TextInputLayout inputLayoutEmail;
    private EditText editEmail;
    private Toolbar mToolbar;
    private CustomProgressDialog mCustomProgressDialog;
    private final HashMap<String, String> paypalSetttings = new HashMap<String, String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paypal_settings);
        mCustomProgressDialog = new CustomProgressDialog(this);
        initLayouts();
        if (SessionHandler.getInstance().get(PaypalSettings.this, AppConstants.PayPal_User_Email) != null) {
            editEmail.setText(SessionHandler.getInstance().get(PaypalSettings.this, AppConstants.PayPal_User_Email));
        }

    }

    private void initLayouts() {
        mToolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        setSupportActionBar(mToolbar);

        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        editEmail = (EditText) findViewById(R.id.input_email);
    }

    public void submitPaypal(View view) {
        if (!validateEmail()) {
            return;
        }

        if (NetworkChangeReceiver.isConnected()) {
            mCustomProgressDialog.showDialog();
            paypalSetttings.put("paypal_email", editEmail.getText().toString());
            //paypalSetttings.put("user_id", SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID));

            postPaypalSettings();
        } else {
            showToast();
        }
        finish();
    }

    private void postPaypalSettings() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.postPaypalSettings(paypalSetttings, SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID), SessionHandler.getInstance().get(this, AppConstants.Language)).enqueue(new Callback<POSTPaypalSettings>() {
            @Override
            public void onResponse(Call<POSTPaypalSettings> call, Response<POSTPaypalSettings> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode().equals(200)) {
                        SessionHandler.getInstance().save(PaypalSettings.this, AppConstants.PayPal_User_Email, editEmail.getText().toString());
                        Toast.makeText(PaypalSettings.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    }else if (response.body().getCode().equals(Integer.parseInt(AppConstants.INVALID_RESPONSE_CODE))) {
//                    mCustomProgressDialog.dismiss();
                        NetworkAlertDialog.networkAlertDialog(PaypalSettings.this, "", response.body().getMessage(), null, null);
                    } else if (response.body().getCode().equals(AppConstants.InactiveStatusResponse)) {
                        Utils.createUserInActiceAlert(PaypalSettings.this, response.body().getMessage());
                    } else {
                        Toast.makeText(PaypalSettings.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.i("ERROR_PAYPAL_SETTINGS", response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<POSTPaypalSettings> call, Throwable t) {
                Log.i("TAG", t.getMessage());
                if (t instanceof UnknownHostException || t instanceof HttpException || t instanceof ConnectException) {
                    Toast.makeText(PaypalSettings.this, getString(R.string.err_server_msg), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void showToast() {
        Toast.makeText(this, getString(R.string.err_internet_connection), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean validateEmail() {
        String email = editEmail.getText().toString().trim();

        if (email.isEmpty() || !Utils.isValidEmail(email)) {
            inputLayoutEmail.setError(getString(R.string.err_msg_email));
            requestFocus(editEmail);
            return false;
        } else {
            inputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}
