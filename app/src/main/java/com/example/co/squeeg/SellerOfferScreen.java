package com.example.co.squeeg;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.co.squeeg.Model.POSTLanguageModel;
import com.example.co.squeeg.Model.POSTSellerOfferResponse;
import com.example.co.squeeg.Model.buyer_requests_for_seller.BuyerRequestForSeller;
import com.example.co.squeeg.Model.seller_services.SellerService;
import com.example.co.squeeg.network.ApiClient;
import com.example.co.squeeg.network.ApiInterface;
import com.example.co.squeeg.receiver.NetworkChangeReceiver;
import com.example.co.squeeg.utils.AppConstants;
import com.example.co.squeeg.utils.CustomProgressDialog;
import com.example.co.squeeg.utils.NetworkAlertDialog;
import com.example.co.squeeg.utils.SessionHandler;
import com.example.co.squeeg.utils.Utils;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.apache.http.HttpException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SellerOfferScreen extends AppCompatActivity {

    private ImageView ivServiceImage;

    private TextView tvChangeService,
            tvTitle,
            tvDescription,
            tvDeliveryDays,
            tvRevisions;

    Handler mHandler;

    private String[] arrayRevisions;
    private String[] arrayDeliveryDays;

    private EditText etDescription,
            etBudget;

    private Button btnSendOffer;

    private String sDescription = null,
            sDeliveryDays = null,
            sBudget = null,
            sRevisions = null;

    private RequestBody user_id;
    RequestBody buyer_id;
    RequestBody gig_id;
    RequestBody request_id;
    RequestBody revision;
    RequestBody duration;
    RequestBody description;
    RequestBody budget;
    RequestBody date;
    RequestBody time;

    String sServiceImage = "";

    private BuyerRequestForSeller buyerRequestForSeller;
    private SellerService sellerService;
    private Toolbar mToolbar;

    public POSTLanguageModel.Common_strings commonStrings = new POSTLanguageModel().new Common_strings();

    private CustomProgressDialog mCustomProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_offer_screen);

        mToolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        setSupportActionBar(mToolbar);
        mHandler = new Handler();

        mCustomProgressDialog = new CustomProgressDialog(this);
        Utils.freeMemory();
        initLayouts();
        setLanguageLocale();

//        getData();

        arrayRevisions = getResources().getStringArray(R.array.revisions);
        arrayDeliveryDays = getResources().getStringArray(R.array.days);

//        showDataToView();

        tvChangeService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoOtherActivity();
            }
        });

        tvRevisions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRevisionDialog((TextView) view);
            }
        });

        tvDeliveryDays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDeliveryDaysDialog((TextView) view);
            }
        });

        btnSendOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getDateFromView();

                if (validateOffer())
                    return;

                if (NetworkChangeReceiver.isConnected()) {
                    mCustomProgressDialog.showDialog();
                    putOfferData();
                } else {
                    Utils.toastMessage(SellerOfferScreen.this, commonStrings.getLbl_enable_internet().getName());
                }
            }
        });
    }

    public void setLanguageLocale() {
        commonStrings = new Gson().fromJson(SessionHandler.getInstance().get(SellerOfferScreen.this, AppConstants.COMMONSTRINGS), POSTLanguageModel.Common_strings.class);
//        sell_gigs_screen = new Gson().fromJson(SessionHandler.getInstance().get(getActivity(), AppConstants.SELLGIGS), POSTLanguageModel.Sell_gigs_screen.class);
//        inputLayoutTitleGigs.setHint(sell_gigs_screen.getTxt_fld_title_gigs().getName());
//        inputLayoutDeliverDay.setHint(sell_gigs_screen.getTxt_fld_deliver_gig().getName());
//        inputLayoutExtras.setHint(sell_gigs_screen.getLbl_Ican().getName());
//        inputLayoutExtrasDay.setHint(sell_gigs_screen.getLbl_day().getName());
//        inputLayoutFastExtras.setHint(sell_gigs_screen.getLbl_Ican().getName());
//        inputLayoutFastExtrasDay.setHint(sell_gigs_screen.getLbl_day().getName());
//        inputLayoutGigCost.setHint(sell_gigs_screen.getTxt_fld_gig_cost().getName());
//        inputLayoutGigDesc.setHint(sell_gigs_screen.getTxt_fld_provide_info().getName());
//        inputLayoutGigRequirement.setHint(sell_gigs_screen.getTxt_fld_buyer_needs().getName());
//        tvEarnMoreMoney.setText(sell_gigs_screen.getLbl_earn_money().getName());
//        tvSelectCategory.setText(sell_gigs_screen.getLbl_select_category().getName());
//        tvTitleSuperfastDelivery.setText(sell_gigs_screen.getLbl_super_fast_delivery().getName());
//        tvWorkOption.setText(sell_gigs_screen.getLbl_work_option().getName());
//        addMoreGigs.setText(sell_gigs_screen.getLbl_add_more_items().getName());
//        rbOnsite.setText(sell_gigs_screen.getLbl_onsite().getName());
//        rbRemote.setText(sell_gigs_screen.getLbl_remote().getName());
//        mCheckBox.setText(sell_gigs_screen.getLbl_terms_condition().getName());
//        createGigs.setText(sell_gigs_screen.getBtn_create_gig().getName());
    }

    private void getDateFromView() {
        sDescription = etDescription.getText().toString().trim();
        sDeliveryDays = tvDeliveryDays.getText().toString().trim();
        sRevisions = tvRevisions.getText().toString().trim();
        sBudget = etBudget.getText().toString();
    }

    private void putOfferData() {

        user_id = RequestBody.create(MediaType.parse("text/plain"), SessionHandler.getInstance().get(SellerOfferScreen.this, AppConstants.TOKEN_ID));
        buyer_id = RequestBody.create(MediaType.parse("text/plain"), buyerRequestForSeller.getUserId());
        gig_id = RequestBody.create(MediaType.parse("text/plain"), sellerService.getId());
        request_id = RequestBody.create(MediaType.parse("text/plain"), buyerRequestForSeller.getRequestId());

        revision = RequestBody.create(MediaType.parse("text/plain"), sRevisions);
        duration = RequestBody.create(MediaType.parse("text/plain"), sDeliveryDays);
        description = RequestBody.create(MediaType.parse("text/plain"), sDescription);
        budget = RequestBody.create(MediaType.parse("text/plain"), sBudget);
        date = RequestBody.create(MediaType.parse("text/plain"), Utils.getCurrentDate());
        time = RequestBody.create(MediaType.parse("text/plain"), Utils.getCurrentTimeUsingDate());

        createOffer();
    }

    private void createOffer() {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        apiInterface.createSellerOffer(user_id, buyer_id, gig_id, request_id, revision, duration, description, budget, date,
                time,
                SessionHandler.getInstance().get(SellerOfferScreen.this, AppConstants.TOKEN_ID),
                SessionHandler.getInstance().get(SellerOfferScreen.this, AppConstants.Language))
                .enqueue(new Callback<POSTSellerOfferResponse>() {
                    @Override
                    public void onResponse(Call<POSTSellerOfferResponse> call, Response<POSTSellerOfferResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getCode().equals(200)) {
                                Toast.makeText(SellerOfferScreen.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                mHandler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        ChooseSellerServiceScreen.activity.finish();
                                        finish();
                                    }
                                }, 3000);
                            } else if (response.body().getCode().equals(Integer.parseInt(AppConstants.INVALID_RESPONSE_CODE))) {
                                NetworkAlertDialog.networkAlertDialog(SellerOfferScreen.this, "", response.body().getMessage(), null, null);
                            } else {
                                Toast.makeText(SellerOfferScreen.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        mCustomProgressDialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<POSTSellerOfferResponse> call, Throwable t) {
                        Log.i("TAG", t.getMessage());
                        mCustomProgressDialog.dismiss();
                        if (t instanceof UnknownHostException || t instanceof HttpException || t instanceof ConnectException || t instanceof SocketTimeoutException || t instanceof SocketException || t instanceof IOException) {
                            NetworkAlertDialog.networkAlertDialog(SellerOfferScreen.this, commonStrings.getLbl_network_err().getName(),
                                    commonStrings.getLbl_server_err().getName(), creategigsRunn, null);
                        }
                    }
                });
    }

    Runnable creategigsRunn = new Runnable() {
        @Override
        public void run() {
            //TODO: API call
            createOffer();
        }
    };

    @Override
    protected void onPostResume() {
        super.onPostResume();

        getData();

        showDataToView();
    }

    private void initLayouts() {
        tvChangeService = (TextView) findViewById(R.id.tv_change_service);
        ivServiceImage = (ImageView) findViewById(R.id.iv_service);

        tvTitle = (TextView) findViewById(R.id.tv_service_title);
        tvDescription = (TextView) findViewById(R.id.tv_service_description);
        tvDeliveryDays = (TextView) findViewById(R.id.tv_delivery_days);
        tvRevisions = (TextView) findViewById(R.id.tv_revision);

        etDescription = (EditText) findViewById(R.id.et_description);
        etBudget = (EditText) findViewById(R.id.et_offer_amount);

        btnSendOffer = (Button) findViewById(R.id.btn_send_offer);
    }

    private void getData() {

        Gson gson = new Gson();
        String sBuyerRequest = SessionHandler.getInstance().get(SellerOfferScreen.this, AppConstants.BUYER_REQUEST);
        String sSellerService = SessionHandler.getInstance().get(SellerOfferScreen.this, AppConstants.SELLER_OFFER);

        sellerService = gson.fromJson(sSellerService, SellerService.class);
        buyerRequestForSeller = gson.fromJson(sBuyerRequest, BuyerRequestForSeller.class);
    }

    private void showDataToView() {
        if (!sellerService.getImagePath().isEmpty()) {
            sServiceImage = AppConstants.ImageBaseURL + sellerService.getImagePath();
        }
        Picasso.get().load(sServiceImage).placeholder(R.drawable.ic_no_image_100).into(ivServiceImage);

        tvTitle.setText(sellerService.getTitle());
        tvDescription.setText(sellerService.getDescription());
    }

    private void showRevisionDialog(final TextView tv) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(SellerOfferScreen.this);
        builder.setCancelable(true);
        builder.setSingleChoiceItems(arrayRevisions, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                tv.setText(arrayRevisions[which]);
            }
        });
        builder.show();
    }

    private void showDeliveryDaysDialog(final TextView tv) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(SellerOfferScreen.this);
        builder.setCancelable(true);
        builder.setSingleChoiceItems(arrayDeliveryDays, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                tv.setText(arrayDeliveryDays[which]);
            }
        });
        builder.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            gotoOtherActivity();
        }
        return super.onOptionsItemSelected(item);
    }

    private void gotoOtherActivity() {
//        Intent gotoActivity = new Intent(SellerOfferScreen.this, ChooseServiceScreen.class);
//        startActivity(gotoActivity);
        finish();
    }

    private boolean validateOffer() {

        if (sRevisions.isEmpty()) {
            Utils.toastMessage(SellerOfferScreen.this, "Select Revisions");
            return true;
        }

        if (sDeliveryDays.isEmpty()) {
            Utils.toastMessage(SellerOfferScreen.this, "Select Delivery Time");
            return true;
        }

        if (sBudget.isEmpty() || Float.parseFloat(sBudget) < 5.0) {
            Utils.toastMessage(SellerOfferScreen.this, "Enter Budget Min. $5");
            return true;
        }

        if (sDescription.isEmpty() || sDescription.length() > 800) {
            Utils.toastMessage(SellerOfferScreen.this, "Enter description Max 800 Chars");
            return true;
        }
        return false;
    }

}