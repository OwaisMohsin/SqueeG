package com.example.co.squeeg.payment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.braintreepayments.cardform.OnCardFormSubmitListener;
import com.braintreepayments.cardform.view.CardForm;
import com.stripe.android.BuildConfig;
import com.stripe.android.Stripe;
import com.stripe.android.TokenCallback;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.example.co.squeeg.MainActivity;
import com.example.co.squeeg.Model.GETStripeConfig;
import com.example.co.squeeg.Model.POSTSubscriptionSuccess;
import com.example.co.squeeg.R;
import com.example.co.squeeg.network.ApiClient;
import com.example.co.squeeg.network.ApiInterface;
import com.example.co.squeeg.receiver.NetworkChangeReceiver;
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

/**
 * Created by Hari on 09-01-2019.
 */

public class StripeSubscriptionPayment extends AppCompatActivity {


    @BindView(R.id.tb_toolbar)
    Toolbar tbToolbar;
    @BindView(R.id.iv_card)
    ImageView ivCard;
    @BindView(R.id.card_form)
    CardForm cardForm;
    @BindView(R.id.btnBuy)
    Button btnBuy;
    @BindView(R.id.cardNumber)
    CustomEditext cardNumber;
    @BindView(R.id.month)
    CustomEditext month;
    @BindView(R.id.textView)
    CustomTextView textView;
    @BindView(R.id.year)
    CustomEditext year;
    @BindView(R.id.cvc)
    CustomEditext cvc;
    @BindView(R.id.submitButton)
    CustomButton submitButton;
    CustomProgressDialog mCustomProgressDialog;
    String transaction_id = "", id = "", stripeKey;
    String publicKey = "", secretKey = "";
    String title;
    double total_cost;
    HashMap<String, String> postDetails = new HashMap<String, String>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stripe_pay);
        ButterKnife.bind(this);
        mCustomProgressDialog = new CustomProgressDialog(this);
        btnBuy.setText("Pay");
        getStripeDetails();
        title = getIntent().getStringExtra("subscription_name");
        total_cost = Double.parseDouble(getIntent().getStringExtra("subscription_amount"));
        postDetails.put("currency_type", "USD");
        postDetails.put("time_zone", SessionHandler.getInstance().get(this, AppConstants.TIMEZONE_ID));
        postDetails.put("subscription_id", getIntent().getStringExtra("subscription_id"));
        postDetails.put("subscription_name", getIntent().getStringExtra("subscription_name"));
        postDetails.put("subscription_period", getIntent().getStringExtra("subscription_period"));
        postDetails.put("subscription_gigs", getIntent().getStringExtra("subscription_gigs"));
        postDetails.put("subscription_amount", getIntent().getStringExtra("subscription_amount"));

        cardForm.cardRequired(true)
                .expirationRequired(true)
                .cvvRequired(true)
                .setup(this);
        ivCard.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);

        cardForm.setOnCardFormSubmitListener(new OnCardFormSubmitListener() {
            @Override
            public void onCardFormSubmit() {
                submitCard();

            }
        });

    }

    @OnClick(R.id.btnBuy)
    public void onViewClicked() {
        submitCard();
    }

    public void submitCard() {
        // TODO: replace with your own test key
        if (!cardForm.getCardNumber().isEmpty() && !cardForm.getExpirationMonth().isEmpty() && !cardForm.getExpirationYear().isEmpty() && !cardForm.getCvv().isEmpty()) {
            mCustomProgressDialog.showDialog();
            final String publishableApiKey = BuildConfig.DEBUG ?
                    stripeKey :
                    publicKey;
            Card card = new Card(cardForm.getCardNumber(),
                    Integer.valueOf(cardForm.getExpirationMonth()),
                    Integer.valueOf(cardForm.getExpirationYear()),
                    cardForm.getCvv());
            card.setCurrency(AppConstants.CURRENCY_TYPE);
            Stripe stripe = new Stripe();
            stripe.createToken(card, publishableApiKey, new TokenCallback() {
                public void onSuccess(Token token) {
                    mCustomProgressDialog.dismiss();
                    com.stripe.Stripe.apiKey = secretKey;
                    total_cost = total_cost * 100;
                    // Charge the user's card:
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("amount", (int) total_cost);
                    params.put("currency", "USD");
                    params.put("description", title);
                    params.put("source", token.getId());

                    try {
                        int SDK_INT = Build.VERSION.SDK_INT;
                        if (SDK_INT > 8) {
                            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                                    .permitAll().build();
                            StrictMode.setThreadPolicy(policy);
                            //your codes here
                            Charge charge = Charge.create(params);
                            if (charge != null) {
                                transaction_id = charge.getId();
                                if (charge.getStatus().equalsIgnoreCase("succeeded")) {
                                    final Dialog dialog = new Dialog(StripeSubscriptionPayment.this);
                                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                    dialog.setCancelable(false);
                                    dialog.setContentView(R.layout.dialog_thank_you_subscription);
                                    int width = ViewGroup.LayoutParams.MATCH_PARENT;
                                    int height = ViewGroup.LayoutParams.MATCH_PARENT;
                                    dialog.getWindow().setLayout(width, height);
                                    Button btn_return = (Button) dialog.findViewById(R.id.bt_return);
                                    btn_return.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            if (NetworkChangeReceiver.isConnected()) {
                                                mCustomProgressDialog.showDialog();
                                                if (!transaction_id.isEmpty() && id != null) {
                                                    postDetails.put("transcation_id", transaction_id);
                                                    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                                                    apiInterface.postSubscriptionPayment(postDetails, SessionHandler.getInstance().get(StripeSubscriptionPayment.this, AppConstants.TOKEN_ID), SessionHandler.getInstance().get(StripeSubscriptionPayment.this, AppConstants.Language))
                                                            .enqueue(new Callback<POSTSubscriptionSuccess>() {
                                                                @Override
                                                                public void onResponse(Call<POSTSubscriptionSuccess> call, Response<POSTSubscriptionSuccess> response) {
                                                                    mCustomProgressDialog.dismiss();
                                                                    if (response.body() != null) {
                                                                        if (response.body().getCode().equals(200)) {
                                                                            Intent callHome = new Intent(StripeSubscriptionPayment.this, MainActivity.class);
                                                                            callHome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                                            callHome.putExtra("From", "MainPage");
                                                                            callHome.putExtra("LoadFragment", 1);
                                                                            startActivity(callHome);
                                                                            dialog.dismiss();
                                                                            finish();
                                                                        }
                                                                    }
                                                                }

                                                                @Override
                                                                public void onFailure(Call<POSTSubscriptionSuccess> call, Throwable t) {
                                                                    Log.i("TAG", t.getMessage());
                                                                    mCustomProgressDialog.dismiss();
                                                                }
                                                            });
                                                }
                                            } else {
                                                Toast.makeText(StripeSubscriptionPayment.this, getString(R.string.err_internet_connection), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                                    dialog.show();
                                }
                            }
                        }
                    } catch (AuthenticationException e) {
                        e.printStackTrace();
                    } catch (InvalidRequestException e) {
                        e.printStackTrace();
                    } catch (APIConnectionException e) {
                        e.printStackTrace();
                    } catch (CardException e) {
                        e.printStackTrace();
                    } catch (APIException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                public void onError(Exception error) {
                    mCustomProgressDialog.dismiss();
                    Toast.makeText(StripeSubscriptionPayment.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("Stripe", error.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(this, "Please fill above required information...", Toast.LENGTH_SHORT).show();
        }
    }

    public void getStripeDetails() {
        if (NetworkChangeReceiver.isConnected()) {
            mCustomProgressDialog.showDialog();
            final ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            apiInterface.getStripeConfiDetails(SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID), SessionHandler.getInstance().get(this, AppConstants.Language))
                    .enqueue(new Callback<GETStripeConfig>() {
                        @Override
                        public void onResponse(Call<GETStripeConfig> call, Response<GETStripeConfig> response) {
                            mCustomProgressDialog.dismiss();
                            if (response.body().getCode().equals(200)) {
                                if (response.body().getData().getStripePublicKey() != null && response.body().getData().getStripeSecretKey() != null) {
                                    if (!response.body().getData().getStripeSecretKey().isEmpty() && !response.body().getData().getStripePublicKey().isEmpty()) {
                                        publicKey = response.body().getData().getStripePublicKey();
                                        secretKey = response.body().getData().getStripeSecretKey();
                                    }
                                }
                            } else if (response.body().getCode().equals(Integer.parseInt(AppConstants.INVALID_RESPONSE_CODE))) {
                                NetworkAlertDialog.networkAlertDialog(StripeSubscriptionPayment.this, "", getResources().getString(R.string.add_stripe_details_admin), null, null);
                            } else {
                                Toast.makeText(StripeSubscriptionPayment.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<GETStripeConfig> call, Throwable t) {
                            mCustomProgressDialog.dismiss();
                        }
                    });
        } else {
            Toast.makeText(this, getString(R.string.err_internet_connection), Toast.LENGTH_SHORT).show();
        }

    }

}
