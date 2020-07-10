package com.example.co.squeeg;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.apache.http.HttpException;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.co.squeeg.Model.GETStripeConfig;
import com.example.co.squeeg.Model.POSTBuyNow;
import com.example.co.squeeg.Model.POSTDetailGig;
import com.example.co.squeeg.Model.POSTLanguageModel;
import com.example.co.squeeg.network.ApiClient;
import com.example.co.squeeg.network.ApiInterface;
import com.example.co.squeeg.payment.StripePay;
import com.example.co.squeeg.receiver.NetworkChangeReceiver;
import com.example.co.squeeg.utils.AppConstants;
import com.example.co.squeeg.utils.CustomButton;
import com.example.co.squeeg.utils.CustomProgressDialog;
import com.example.co.squeeg.utils.CustomTextView;
import com.example.co.squeeg.utils.NetworkAlertDialog;
import com.example.co.squeeg.utils.SessionHandler;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartModule extends BaseActivity {
    View HeaderLayout, FooterLayout;
    ListView CartLayout;
    double totalCost;
    POSTDetailGig.Gigs_details mGigs_details;
    List<POSTDetailGig.Gigs_details> fillGigs = new ArrayList<POSTDetailGig.Gigs_details>();
    @BindView(R.id.ctv_item)
    CustomTextView ctvItem;
    @BindView(R.id.ctv_productname)
    CustomTextView ctvProductname;
    @BindView(R.id.ctv_quantity)
    CustomTextView ctvQuantity;
    @BindView(R.id.total)
    CustomTextView total;
    //    @BindView(R.id.ctv_select_payment)
    CustomTextView ctvSelectPayment;
    CustomButton buttonRegister;

    CustomTextView ctvCartInfo;
    @BindView(R.id.tb_cart)
    CustomTextView tbCart;
    private String SUPERFAST_DESC, SUPERFAST_DAYS, SUPERFAST_CHARGES, deliveryDays;
    List<POSTDetailGig.Extra_gig> order_gigs;
    RadioGroup choosePaymentGateway;
    String paymentType = "", fastDeliveryStatus = "", extraGigs = "";
    private HashMap<String, String> postGigsDetails = new HashMap<String, String>();
    Gson mGson;
    CustomProgressDialog mCustomProgressDialog;
    Toolbar mToolbar;
    String publicKey = "", secretKey = "";
    String stripe_apiKey, stripeValue, stripeGatewayType, PayPal_apiKey, PayPal_Value, PayPal_GatewayType;
    public POSTLanguageModel.Detail_gigs detail_gigs = new POSTLanguageModel().new Detail_gigs();
    public POSTLanguageModel.Change_password_screen changePasswordScreen = new POSTLanguageModel().new Change_password_screen();
    public POSTLanguageModel.Cart_screen cartScreen = new POSTLanguageModel().new Cart_screen();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_module);

        ButterKnife.bind(this);
        fillGigs.clear();
        mGigs_details = (POSTDetailGig.Gigs_details) getIntent().getSerializableExtra("Gigs_Detail");
        mCustomProgressDialog = new CustomProgressDialog(this);
        mGson = new Gson();

        Bundle bundle = this.getIntent().getExtras();
        SUPERFAST_CHARGES = getIntent().getExtras().getString(AppConstants.SUPERFAST_CHARGES);
        SUPERFAST_DAYS = getIntent().getExtras().getString(AppConstants.SUPERFAST_DAYS);
        SUPERFAST_DESC = getIntent().getExtras().getString(AppConstants.SUPERFAST_DELIVERY_DESC);
        order_gigs = (List<POSTDetailGig.Extra_gig>) bundle.getSerializable("order_gigs");
        deliveryDays = getIntent().getExtras().getString("delivery_days");

        if (mGigs_details != null) {
            fillGigs.add(mGigs_details);
        }
//        totalCost = Double.parseDouble(getIntent().getExtras().getString("total_cost"));
        totalCost = getIntent().getDoubleExtra("total_cost", 0);
        CartLayout = (ListView) findViewById(R.id.lv_cart);
        mToolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        setSupportActionBar(mToolbar);

        HeaderLayout = getLayoutInflater().inflate(R.layout.include_header_cart, null);
        FooterLayout = getLayoutInflater().inflate(R.layout.include_footer_cart, null);

        choosePaymentGateway = (RadioGroup) FooterLayout.findViewById(R.id.rg_payment_type);
        ctvSelectPayment = (CustomTextView) FooterLayout.findViewById(R.id.ctv_select_payment);
        ctvCartInfo = (CustomTextView) FooterLayout.findViewById(R.id.ctv_cart_info);
        buttonRegister = (CustomButton)FooterLayout.findViewById(R.id.button_register);
        setLanguageValues();
        /*int defaultId = choosePaymentGateway.getCheckedRadioButtonId();
        RadioButton defaultRB = (RadioButton) findViewById(defaultId);
        paymentType = defaultRB.getText().toString();*/

        choosePaymentGateway.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checked) {
                int id = radioGroup.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) findViewById(id);
                paymentType = rb.getText().toString();
            }
        });

//        CartLayout.addHeaderView(HeaderLayout);

        if (fillGigs.size() > 0) {
            CartAdapter aCartAdapter = new CartAdapter(this, fillGigs);
            CartLayout.setAdapter(aCartAdapter);
        }
        CartLayout.addFooterView(FooterLayout);
    }

    public void buyNow(View view) {
        if (SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID) != null) {
            if ( paymentType != null && !paymentType.isEmpty()) {

                buyingGigsHere();
//                switch (paymentType) {
//                    case paypal:
//                        buyingGigsHere();
//                        break;
//                    case stripe:
//                        buyingGigsHere();
//                        break;
//                }
            } else {
                Toast.makeText(this, getResources().getString(R.string.choose_payment_type), Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, getResources().getString(R.string.please_login_to_buy), Toast.LENGTH_SHORT).show();
        }
    }

    private void buyingGigsHere() {
        postGigsDetails.put("gig_id", mGigs_details.getId());
        postGigsDetails.put("seller_id", mGigs_details.getUser_id());
        postGigsDetails.put("gig_rate", "" + Double.valueOf(totalCost));
        //postGigsDetails.put("buyer_id", SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID));
        postGigsDetails.put("super_fast_delivery", fastDeliveryStatus);
        if (!deliveryDays.isEmpty()) {
            postGigsDetails.put("total_delivery_days", deliveryDays);
        } else {
            postGigsDetails.put("total_delivery_days", "");
        }
        postGigsDetails.put("options", extraGigs);
        postGigsDetails.put("source", paymentType.toLowerCase());

        if (NetworkChangeReceiver.isConnected()) {
            mCustomProgressDialog.showDialog();
            final ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            apiInterface.getStripeConfiDetails(SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID), SessionHandler.getInstance().get(this, AppConstants.Language))
                    .enqueue(new Callback<GETStripeConfig>() {
                        @Override
                        public void onResponse(Call<GETStripeConfig> call, Response<GETStripeConfig> response) {
                            if (response.body().getCode().equals(200)) {
                                if (response.body().getData().getStripePublicKey() != null && response.body().getData().getStripeSecretKey() != null) {
                                    if (!response.body().getData().getStripeSecretKey().isEmpty() && !response.body().getData().getStripePublicKey().isEmpty()) {
                                        publicKey = response.body().getData().getStripePublicKey();
                                        secretKey = response.body().getData().getStripeSecretKey();
                                        apiInterface.postBuyingGigsdetails(postGigsDetails, SessionHandler.getInstance().get(CartModule.this, AppConstants.TOKEN_ID), SessionHandler.getInstance().get(CartModule.this, AppConstants.Language)).enqueue(new Callback<POSTBuyNow>() {
                                            @Override
                                            public void onResponse(Call<POSTBuyNow> call, Response<POSTBuyNow> response) {
                                                switch (paymentType) {
                                                    /*case "Paypal":
                                                        if (response.body().getCode().equals(200)) {
                                                            Intent intentPayAct = new Intent(CartModule.this, PaypalAct.class);
                                                            intentPayAct.putExtra("total_cost", totalCost);
                                                            intentPayAct.putExtra("title", mGigs_details.getTitle());
                                                            intentPayAct.putExtra("id", response.body().getData().getGig_order_id().toString());
                                                            intentPayAct.putExtra(AppConstants.PayPal_ApiKey, PayPal_apiKey);
                                                            intentPayAct.putExtra(AppConstants.PayPal_Value, PayPal_Value);
                                                            intentPayAct.putExtra(AppConstants.PayPal_GatewayType, PayPal_GatewayType);
                                                            intentPayAct.putExtra("bundle", mGigs_details);
                                                            startActivity(intentPayAct);
                                                        } else if (response.body().getCode().equals(Integer.parseInt(AppConstants.INVALID_RESPONSE_CODE))) {
                                                            NetworkAlertDialog.networkAlertDialog(CartModule.this, "", response.body().getMessage(), null, null);
                                                        }
                                                        break;*/
                                                    case "Stripe":
                                                        if (!response.body().getCode().equals(Integer.parseInt(AppConstants.INVALID_RESPONSE_CODE))) {
                                                            Intent intentPayAct = new Intent(CartModule.this, StripePay.class);
                                                            intentPayAct.putExtra("total_cost", totalCost);
                                                            intentPayAct.putExtra("title", mGigs_details.getTitle());
                                                            intentPayAct.putExtra("id", response.body().getData().getGig_order_id().toString());
                                                            intentPayAct.putExtra(AppConstants.stripe_apiKey, stripe_apiKey);
                                                            intentPayAct.putExtra(AppConstants.stripeValue, stripeValue);
                                                            intentPayAct.putExtra(AppConstants.public_key, publicKey);
                                                            intentPayAct.putExtra(AppConstants.secret_key, secretKey);
                                                            intentPayAct.putExtra(AppConstants.stripeGatewayType, stripeGatewayType);
                                                            intentPayAct.putExtra("bundle", mGigs_details);
                                                            startActivity(intentPayAct);
                                                        } else {
                                                            NetworkAlertDialog.networkAlertDialog(CartModule.this, "", response.body().getMessage(), null, null);
                                                        }
                                                        break;
                                                }
                                                mCustomProgressDialog.dismiss();
                                            }

                                            @Override
                                            public void onFailure(Call<POSTBuyNow> call, Throwable t) {
                                                mCustomProgressDialog.dismiss();
                                                Log.i("TAG", t.getMessage());
                                                if (t instanceof UnknownHostException || t instanceof HttpException || t instanceof ConnectException) {
                                                    NetworkAlertDialog.networkAlertDialog(CartModule.this, getResources().getString(R.string.err_network_error),
                                                            getResources().getString(R.string.err_try_again), cartmoduleRunn, null);
                                                    //Toast.makeText(CartModule.this, getString(R.string.err_server_msg), Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                        });
                                    }
                                }
                            } else if (response.body().getCode().equals(Integer.parseInt(AppConstants.INVALID_RESPONSE_CODE))) {
                                mCustomProgressDialog.dismiss();
                                NetworkAlertDialog.networkAlertDialog(CartModule.this, "", getResources().getString(R.string.add_stripe_details_admin), null, null);
                            } else {
                                mCustomProgressDialog.dismiss();
                                Toast.makeText(CartModule.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
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

    Runnable cartmoduleRunn = new Runnable() {
        @Override
        public void run() {
            buyingGigsHere();
        }
    };


    private class CartAdapter extends BaseAdapter {
        Context mContext;
        List<POSTDetailGig.Gigs_details> mGigs_details;
        LayoutInflater mInflater;

        CartAdapter(Context mContext, List<POSTDetailGig.Gigs_details> mGigs_details) {
            this.mContext = mContext;
            this.mGigs_details = mGigs_details;
            mInflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return mGigs_details.size();
        }

        @Override
        public POSTDetailGig.Gigs_details getItem(int position) {
            return mGigs_details.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {

            View convertView = view;
            ViewHolder mViewHolder;

            if (convertView == null) {
                mViewHolder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.adapter_cart, null);
                mViewHolder.itemNo = (TextView) convertView.findViewById(R.id.item_no);
                mViewHolder.productName = (TextView) convertView.findViewById(R.id.product_name);
                mViewHolder.quantity = (TextView) convertView.findViewById(R.id.quantity);
                mViewHolder.gigsPrices = (TextView) convertView.findViewById(R.id.gigs_price);
                mViewHolder.totalcost = (TextView) convertView.findViewById(R.id.total_cost);
                mViewHolder.fastextraNames = (TextView) convertView.findViewById(R.id.extras_name);
                mViewHolder.fastextraQuantity = (TextView) convertView.findViewById(R.id.extra_quantity);
                mViewHolder.fastextraCost = (TextView) convertView.findViewById(R.id.extra_cost);
                mViewHolder.llExtrasGigs = (LinearLayout) convertView.findViewById(R.id.ll_extra_gigs);
                mViewHolder.ll_dynamic_extras_gigs = (LinearLayout) convertView.findViewById(R.id.ll_dynamic_extras_gigs);
                mViewHolder.ctvTotalamount = (CustomTextView) convertView.findViewById(R.id.ctv_total_amount);

                mViewHolder.ctvTotalamount.setText(cartScreen.getLbl_total_amount().getName());

                convertView.setTag(mViewHolder);
            } else {
                mViewHolder = (ViewHolder) convertView.getTag();
            }

            mViewHolder.itemNo.setText("1");
            mViewHolder.productName.setText(getItem(position).getTitle());
            mViewHolder.quantity.setText("1");
            AppConstants.DOLLAR_SIGN = getItem(position).getCurrency_sign();
            AppConstants.CURRENCY_TYPE = getItem(position).getCurrency_type();
            mViewHolder.gigsPrices.setText(AppConstants.DOLLAR_SIGN + getItem(position).getGig_price());

            if (order_gigs.size() > 0) {
                for (int i = 0; i < order_gigs.size(); i++) {
                    View dynamicview = getLayoutInflater().inflate(R.layout.include_extras_gigs, null);
                    TextView extraNames = (TextView) dynamicview.findViewById(R.id.super_fast_extras_name);
                    TextView extraQuantity = (TextView) dynamicview.findViewById(R.id.super_fast_extra_quantity);
                    TextView extraCost = (TextView) dynamicview.findViewById(R.id.super_fast_extra_cost);
                    extraNames.setText(order_gigs.get(i).getExtra_gigs());
                    extraQuantity.setText("1");
                    extraCost.setText(AppConstants.DOLLAR_SIGN + order_gigs.get(i).getExtra_gigs_amount());
                    mViewHolder.ll_dynamic_extras_gigs.addView(dynamicview);
                    mViewHolder.ll_dynamic_extras_gigs.invalidate();
                }

                extraGigs = mGson.toJson(order_gigs);

                Log.d("Options:", extraGigs);
            } else {
                mViewHolder.ll_dynamic_extras_gigs.setVisibility(View.GONE);
            }

            if (SUPERFAST_CHARGES != null && SUPERFAST_DAYS != null && SUPERFAST_DESC != null) {
                if (!SUPERFAST_CHARGES.isEmpty() && !SUPERFAST_DAYS.isEmpty() && !SUPERFAST_DESC.isEmpty()) {
                    mViewHolder.fastextraNames.setText(SUPERFAST_DESC);
                    mViewHolder.fastextraQuantity.setText("1");
                    mViewHolder.fastextraCost.setText(AppConstants.DOLLAR_SIGN + SUPERFAST_CHARGES);
                    fastDeliveryStatus = "1";
                } else {
                    mViewHolder.llExtrasGigs.setVisibility(View.GONE);
                    fastDeliveryStatus = "0";
                }
            } else {
                mViewHolder.llExtrasGigs.setVisibility(View.GONE);
                fastDeliveryStatus = "0";
            }


            mViewHolder.totalcost.setText(AppConstants.DOLLAR_SIGN + totalCost);
            return convertView;
        }

        class ViewHolder {
            TextView itemNo, productName, quantity, totalcost, gigsPrices, fastextraNames, fastextraQuantity, fastextraCost;
            CustomTextView ctvTotalamount;
            LinearLayout llExtrasGigs, ll_dynamic_extras_gigs;
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);

    }

    public void setLanguageValues() {
        cartScreen = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.CARTSCREEN), POSTLanguageModel.Cart_screen.class);
        ctvCartInfo.setText(cartScreen.getLbl_cart_bottom_content().getName());
        ctvItem.setText(cartScreen.getLbl_item().getName());
        ctvProductname.setText(cartScreen.getLbl_product_name().getName());
        ctvQuantity.setText(cartScreen.getLbl_quantity().getName());
        ctvSelectPayment.setText(cartScreen.getLbl_select_payment_method().getName());
        total.setText(cartScreen.getLbl_total().getName());
        tbCart.setText(cartScreen.getLbl_header_title().getName());
        buttonRegister.setText(cartScreen.getBtn_buy_now().getName());
    }

}
