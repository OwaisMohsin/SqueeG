package com.example.co.squeeg;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.onesignal.OneSignal;

import org.apache.http.HttpException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashMap;

import com.example.co.squeeg.Model.POSTAcceptBuyRequest;
import com.example.co.squeeg.Model.POSTLanguageModel;
import com.example.co.squeeg.Model.POSTLogin;
import com.example.co.squeeg.network.ApiClient;
import com.example.co.squeeg.network.ApiInterface;
import com.example.co.squeeg.receiver.NetworkChangeReceiver;
import com.example.co.squeeg.utils.AppConstants;
import com.example.co.squeeg.utils.CustomProgressDialog;
import com.example.co.squeeg.utils.NetworkAlertDialog;
import com.example.co.squeeg.utils.PreferenceStorage;
import com.example.co.squeeg.utils.SessionHandler;
import com.example.co.squeeg.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginScreen extends BaseActivity implements GoogleApiClient.OnConnectionFailedListener {

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private EditText editEmail;
    private EditText editPassword;
    private Button btnLogin;
    private TextView tvFacebook, tvGoogle, tvSocialAcc, tvNotAMember, tvForgotPwd, tvRegister, tvToolbar;
    private final HashMap<String, String> loginData = new HashMap<String, String>();
    private CustomProgressDialog mCustomProgressDialog;
    private Toolbar mToolbar;
    private HashMap<String, String> postPush = new HashMap<String, String>();
    private CallbackManager callbackManager;
    private static final String TAG = LoginScreen.class.getSimpleName();
    String source = "";
    private static final int RC_SIGN_IN = 007;
    private GoogleApiClient mGoogleApiClient;
    public POSTLanguageModel.Login_screen loginScreen = new POSTLanguageModel().new Login_screen();

//    POSTLanguageModel.Login_screen login_screen = new POSTLanguageModel().new Login_screen();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        OneSignal.startInit(this).init();

        mCustomProgressDialog = new CustomProgressDialog(this);
        initLayouts();

        editEmail.addTextChangedListener(new LoginTextWatcher(editEmail));
        editPassword.addTextChangedListener(new LoginTextWatcher(editPassword));

        facebookLogin();
        googleLogin();
    }

    private void initLayouts() {
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_username);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        tvFacebook = (TextView) findViewById(R.id.tv_facebook);
        tvGoogle = (TextView) findViewById(R.id.tv_google);
        editEmail = (EditText) findViewById(R.id.input_username);
        editPassword = (EditText) findViewById(R.id.input_password);
        tvNotAMember = (TextView) findViewById(R.id.tv_not_a_member);
        tvForgotPwd = (TextView) findViewById(R.id.tv_forgot_pwd);
        tvSocialAcc = (TextView) findViewById(R.id.tv_social_acc);
        tvRegister = (TextView) findViewById(R.id.tv_register);
        btnLogin = (Button) findViewById(R.id.button_login);
        mToolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        tvToolbar = (TextView) findViewById(R.id.tv_toolbar);
        setSupportActionBar(mToolbar);
        setLanguageValues();
    }

    public void forgotpasswordHere(View view) {
        startActivity(new Intent(this, ForgotPassword.class));
//        clearFocus();
    }

    public void goToRegister(View view) {
        startActivity(new Intent(this, RegisterScreen.class));
//        clearFocus();
    }

    private boolean validateUsername() {
        String email = editEmail.getText().toString().trim();

        if (email.isEmpty()) {
            textInputLayoutEmail.setError(Utils.cleanLangStr(this, loginScreen.getTxt_fld_username().getValidation1(), R.string.err_msg_uname));
//            textInputLayoutEmail.setError(getString(R.string.err_msg_uname));
            requestFocus(editEmail);
            return false;
        } else {
            textInputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (editPassword.getText().toString().trim().isEmpty()) {
            textInputLayoutEmail.setError(Utils.cleanLangStr(this, loginScreen.getTxt_fld_pwd().getValidation1(), R.string.err_msg_password));
//            textInputLayoutPassword.setError(getString(R.string.err_msg_password));
            requestFocus(editPassword);
            return false;
        } else {
            textInputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public void goToHome(View view) {

        if (!validateUsername()) {
            return;
        }
        if (!validatePassword()) {
            return;
        }

        loginData.put("username", editEmail.getText().toString());
        loginData.put("password", editPassword.getText().toString());
        loginData.put("auth", "Normal");

        if (NetworkChangeReceiver.isConnected()) {
            mCustomProgressDialog.showDialog();
            postLogin();
        } else {
            showToast();
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }


    private class LoginTextWatcher implements TextWatcher {

        private View view;

        private LoginTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (view.getId() == R.id.input_username) {
                validateUsername();
            } else if (view.getId() == R.id.input_password) {
                validatePassword();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (view.getId() == R.id.input_username) {
                if (editEmail.getText().toString().isEmpty()) {
                    textInputLayoutEmail.setErrorEnabled(false);
                    textInputLayoutEmail.requestFocus();
                    textInputLayoutEmail.setError(null);
                }
            } else if (view.getId() == R.id.input_password) {
                if (editPassword.getText().toString().isEmpty()) {
                    textInputLayoutPassword.setErrorEnabled(false);
                    textInputLayoutPassword.requestFocus();
                    textInputLayoutPassword.setError(null);
                }
            }
        }
    }

    private void postLogin() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.postLogin(loginData, AppConstants.GUEST_TOKEN, SessionHandler.getInstance().get(this, AppConstants.Language)).enqueue(new Callback<POSTLogin>() {
            @Override
            public void onResponse(Call<POSTLogin> call, Response<POSTLogin> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode().equals(200)) {
                        POSTLogin.Datum data = response.body().getData().get(0);
                        SessionHandler.getInstance().save(LoginScreen.this, AppConstants.USER_NAME, data.getFullname());
                        SessionHandler.getInstance().save(LoginScreen.this, AppConstants.TOKEN_ID, data.getUnique_code());
                        SessionHandler.getInstance().save(LoginScreen.this, AppConstants.EMAIL_ID, data.getEmail());
                        SessionHandler.getInstance().save(LoginScreen.this, AppConstants.USER_IMAGE, data.getUser_profile_image());
                        SessionHandler.getInstance().save(LoginScreen.this, AppConstants.PayPal_User_Email, data.getPaypal_email());
                        /*SessionHandler.getInstance().save(Login.this, AppConstants.SellGigsPriceOption, String.valueOf(data.getPrice_option()));
                        if (data.getPrice_option().equalsIgnoreCase("fixed")) {
                            SessionHandler.getInstance().save(Login.this, AppConstants.SellGigsPrice, String.valueOf(data.getGig_price()));
                            SessionHandler.getInstance().save(Login.this, AppConstants.SellExtraGigsPrice, String.valueOf(data.getExtra_gig_price()));
                        }*/

                        if (data.getStripe_bank() != null) {
                            SessionHandler.getInstance().save(LoginScreen.this, AppConstants.stripe_Acc_name, data.getStripe_bank().getAccount_holder_name());
                            SessionHandler.getInstance().save(LoginScreen.this, AppConstants.stripe_Acc_num, data.getStripe_bank().getAccount_number());
                            SessionHandler.getInstance().save(LoginScreen.this, AppConstants.stripe_Iban, data.getStripe_bank().getAccount_iban());
                            SessionHandler.getInstance().save(LoginScreen.this, AppConstants.stripe_bank_name, data.getStripe_bank().getBank_name());
                            SessionHandler.getInstance().save(LoginScreen.this, AppConstants.stripe_bank_address, data.getStripe_bank().getBank_address());
                            SessionHandler.getInstance().save(LoginScreen.this, AppConstants.stripe_sort_code, data.getStripe_bank().getSort_code());
                            SessionHandler.getInstance().save(LoginScreen.this, AppConstants.stripe_routing_number, data.getStripe_bank().getRouting_number());
                            SessionHandler.getInstance().save(LoginScreen.this, AppConstants.stripe_account_ifsc, data.getStripe_bank().getAccount_ifsc());
                        }

                        new BackGroundPushAPiCall().execute();

                        if (getIntent().getStringExtra(AppConstants.GIGS_ID) != null) {
                            Intent gotoMain = new Intent(LoginScreen.this, DetailGigs.class);
                            gotoMain.putExtra(AppConstants.GIGS_ID, getIntent().getStringExtra(AppConstants.GIGS_ID));
                            gotoMain.putExtra(AppConstants.GIGS_TITLE, getIntent().getStringExtra(AppConstants.GIGS_TITLE));
                            startActivity(gotoMain);
                        } else {
                            Intent gotoMain = new Intent(LoginScreen.this, MainActivity.class);
                            gotoMain.putExtra("From", getIntent().getStringExtra("From"));
                            gotoMain.putExtra("LoadFragment", getIntent().getIntExtra("LoadFragment", 0));
                            gotoMain.putExtra("LoadNav", getIntent().getIntExtra("LoadNav", 0));
                            startActivity(gotoMain);
                        }
                        finish();
                    } else if (response.body().getCode().equals(AppConstants.InactiveStatusResponse)) {
                        Utils.createUserInActiceLoginAlert(LoginScreen.this, response.body().getMessage());
                    } else {
                        Toast.makeText(LoginScreen.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        View view = getCurrentFocus();
                        if (view != null) {
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                    }
                } else {
                    try {
                        Log.i("ERROR_LOGIN", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                mCustomProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<POSTLogin> call, Throwable t) {
                mCustomProgressDialog.dismiss();
                Log.i("TAG", t.getMessage());
                if (t instanceof UnknownHostException || t instanceof HttpException || t instanceof ConnectException || t instanceof SocketTimeoutException || t instanceof SocketException) {
                    NetworkAlertDialog.networkAlertDialog(LoginScreen.this, getResources().getString(R.string.err_network_error),
                            getResources().getString(R.string.err_try_again), loginRunn, null);
                    //Toast.makeText(Login.this, getString(R.string.err_server_msg), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    Runnable loginRunn = new Runnable() {
        @Override
        public void run() {
            //TODO: API call
            postLogin();
        }
    };

    private void showToast() {
        Toast.makeText(this, getString(R.string.err_internet_connection), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            startActivity(new Intent(LoginScreen.this, MainActivity.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (SessionHandler.getInstance().get(LoginScreen.this, AppConstants.TOKEN_ID) == null) {
            Intent gotoMain = new Intent(LoginScreen.this, MainActivity.class);
            gotoMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(gotoMain);
            finish();
        } else {
            if (getIntent() != null && getIntent().getStringExtra("From") != null && getIntent().getStringExtra("From").equalsIgnoreCase("MainPage")) {
                Intent gotoMain = new Intent(LoginScreen.this, MainActivity.class);
                gotoMain.putExtra("From", "MainPage");
                startActivity(gotoMain);
                finish();
            }
        }
    }

    private void clearFocus() {
        editEmail.setText("");
        editPassword.setText("");
    }

    @SuppressLint("StaticFieldLeak")
    private class BackGroundPushAPiCall extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            //postPush.put("user_id", SessionHandler.getInstance().get(getApplicationContext(), AppConstants.TOKEN_ID));
            postPush.put("device_id", SessionHandler.getInstance().get(getApplicationContext(), AppConstants.NOTIFICATION_IDS));
            postPush.put("device", "Android");
            if (NetworkChangeReceiver.isConnected()) {
                ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                apiInterface.postPushDetails(postPush, SessionHandler.getInstance().get(LoginScreen.this, AppConstants.TOKEN_ID), SessionHandler.getInstance().get(LoginScreen.this, AppConstants.Language)).enqueue(new Callback<POSTAcceptBuyRequest>() {
                    @Override
                    public void onResponse(Call<POSTAcceptBuyRequest> call, Response<POSTAcceptBuyRequest> response) {
                        if (response.body() != null) {
                            if (response.body().getCode().equals(200)) {
                                Log.i("TAG", response.body().getMessage());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<POSTAcceptBuyRequest> call, Throwable t) {
                        Log.i("TAG", t.getMessage());
                    }
                });
            }

            return null;
        }
    }

    public void facebookLogin() {
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                try {
                                    String email = "", facebookId = "", fullname = "";
                                    Log.d(TAG, object.toString());
                                    fullname = object.getString("first_name") + " " + object.getString("last_name");
                                    String profilepic = object.getJSONObject("picture").getJSONObject("data").get("url").toString();
                                    if (object.has("email")) {
                                        email = object.getString("email");
                                        Log.e(TAG, "email = " + email);
                                    }
                                    facebookId = object.getString("id");
                                    Log.e(TAG, "facebookId = " + facebookId);
                                    loginData.put("email", email);
                                    loginData.put("auth", "Facebook");
                                    loginData.put("profileid", facebookId);
                                    loginData.put("fullname", fullname);
                                    loginData.put("profileurl", profilepic.replaceAll("\\\\", ""));
                                    loginData.put("user_timezone", SessionHandler.getInstance().get(LoginScreen.this, AppConstants.TIMEZONE_ID));
                                        postLogin();
                                } catch (Exception e) {
                                    Log.e(TAG, e.getLocalizedMessage());
                                }
                            }
                        });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,first_name,last_name,cover,picture.type(large),email,birthday");
                graphRequest.setParameters(parameters);
                graphRequest.executeAsync();
            }

            @Override
            public void onCancel() {
                if (mCustomProgressDialog != null)
                    mCustomProgressDialog.dismiss();
            }

            @Override
            public void onError(FacebookException error) {
                if (mCustomProgressDialog != null)
                    mCustomProgressDialog.dismiss();
                Log.d("error", String.valueOf(error));
            }
        });
    }

    public void googleLogin() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }


    public void callFacebookAPI(View view) {
        LoginManager.getInstance().logOut();
        LoginManager.getInstance().logInWithReadPermissions(LoginScreen.this, Arrays.asList("email"));
    }

    public void callGoogleLogin(View view) {
        signIn();
    }

    private void signIn() {
        signOut();
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            mCustomProgressDialog.showDialog();
            handleSignInResult(result);
        } else {
            mCustomProgressDialog.showDialog();
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.getStatus());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

//            Log.e(TAG, "display name: " + acct.getDisplayName());
//            String personName = acct.getDisplayName();
//            String personPhotoUrl = acct.getPhotoUrl().toString();
//            loginThrough = Constants.REG_GPLUS;
//            socialName = personName;
            loginData.put("email", acct.getEmail());
            loginData.put("auth", "Google");
            loginData.put("profileid", acct.getId());
            loginData.put("fullname", acct.getDisplayName());
            if (acct.getPhotoUrl() != null) {
                loginData.put("profileurl", acct.getPhotoUrl().toString());
            } else {
                loginData.put("profileurl", "");
            }

            loginData.put("user_timezone", SessionHandler.getInstance().get(LoginScreen.this, AppConstants.TIMEZONE_ID));
            postLogin();
        } else {
            mCustomProgressDialog.dismiss();
        }
    }


    public void setLanguageValues() {
        try {
            String commonDataStr = PreferenceStorage.getKey(AppConstants.LOGIN);
            loginScreen = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.LOGIN), POSTLanguageModel.Login_screen.class);
            textInputLayoutEmail.setHint(loginScreen.getTxt_fld_username().getName());
            textInputLayoutPassword.setHint(loginScreen.getTxt_fld_pwd().getName());
            tvRegister.setText(loginScreen.getLbl_register_now().getName());
            tvSocialAcc.setText(loginScreen.getLbl_social_login().getName());
            tvForgotPwd.setText(loginScreen.getLbl_forgot_pwd().getName());
            tvNotAMember.setText(loginScreen.getLbl_not_a_member().getName());
            btnLogin.setText(loginScreen.getTxt_fld_login().getName());
            tvToolbar.setText(loginScreen.getTxt_fld_login().getName());
            tvFacebook.setText(loginScreen.getLbl_facebook().getName());
            tvGoogle.setText(loginScreen.getLbl_google().getName());
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
    }


}
