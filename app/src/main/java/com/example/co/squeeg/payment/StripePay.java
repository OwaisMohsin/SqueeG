package com.example.co.squeeg.payment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
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

import com.example.co.squeeg.Model.POSTPaymentSuccess;
import com.example.co.squeeg.MyActivity;
import com.example.co.squeeg.R;
import com.example.co.squeeg.network.ApiClient;
import com.example.co.squeeg.network.ApiInterface;
import com.example.co.squeeg.receiver.NetworkChangeReceiver;
import com.example.co.squeeg.utils.AppConstants;
import com.example.co.squeeg.utils.CustomProgressDialog;
import com.example.co.squeeg.utils.SessionHandler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StripePay extends AppCompatActivity {

    String title;
    double total_cost;
    CustomProgressDialog mCustomProgressDialog;
    Button pay;
    HashMap<String, String> postDetails = new HashMap<String, String>();
    String transaction_id = "", id = "", stripeKey;
    CardForm cardForm;
    @BindView(R.id.btnBuy)
    Button btnBuy;
    @BindView(R.id.iv_card)
    ImageView ivCard;
    String publicKey = "", secretKey = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stripe_pay);
        ButterKnife.bind(this);

        cardForm = findViewById(R.id.card_form);
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

        pay = (Button) findViewById(R.id.submitButton);
        mCustomProgressDialog = new CustomProgressDialog(this);
        total_cost = getIntent().getDoubleExtra("total_cost", 0);
        id = getIntent().getStringExtra("id");
        title = getIntent().getStringExtra("title");
        if (!getIntent().getStringExtra(AppConstants.public_key).isEmpty() &&
                !getIntent().getStringExtra(AppConstants.secret_key).isEmpty()) {
            publicKey = getIntent().getStringExtra(AppConstants.public_key);
            secretKey = getIntent().getStringExtra(AppConstants.secret_key);
        }
        if (getIntent().getStringExtra(AppConstants.stripe_apiKey) != null) {
            stripeKey = getIntent().getStringExtra(AppConstants.stripe_apiKey);
        }

        pay.setText("Pay " + AppConstants.DOLLAR_SIGN + total_cost);

    }

    public void submitCard() {
        // TODO: replace with your own test key
        if (!cardForm.getCardNumber().isEmpty() && !cardForm.getExpirationMonth().isEmpty() && !cardForm.getExpirationYear().isEmpty() && !cardForm.getCvv().isEmpty())
        {
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
                                    final Dialog dialog = new Dialog(StripePay.this);
                                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                                    dialog.setCancelable(false);
                                    dialog.setContentView(R.layout.dialog_thank_you);
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
                                                    postDetails.put("paypal_uid", transaction_id);
                                                    postDetails.put("item_number", id);
                                                    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                                                    apiInterface.postPaymentSuccess(postDetails, SessionHandler.getInstance().get(StripePay.this, AppConstants.TOKEN_ID), SessionHandler.getInstance().get(StripePay.this, AppConstants.Language)).enqueue(new Callback<POSTPaymentSuccess>() {
                                                        @Override
                                                        public void onResponse(Call<POSTPaymentSuccess> call, Response<POSTPaymentSuccess> response) {
                                                            mCustomProgressDialog.dismiss();
                                                            if (response.body() != null) {
                                                                if (response.body().getCode().equals(200)) {
                                                                    Intent callHome = new Intent(StripePay.this, MyActivity.class);
                                                                    callHome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                                    startActivity(callHome);
                                                                    dialog.dismiss();
                                                                    finish();
                                                                }
                                                            }
                                                        }

                                                        @Override
                                                        public void onFailure(Call<POSTPaymentSuccess> call, Throwable t) {
                                                            Log.i("TAG", t.getMessage());
                                                            mCustomProgressDialog.dismiss();
                                                        }
                                                    });
                                                }
                                            } else {
                                                Toast.makeText(StripePay.this, getString(R.string.err_internet_connection), Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(StripePay.this, error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("Stripe", error.getLocalizedMessage());
                }
            });
        } else {
        }
    }


    @OnClick(R.id.btnBuy)
    public void onViewClicked() {
        submitCard();
    }
}
