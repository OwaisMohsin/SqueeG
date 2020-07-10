package com.example.co.squeeg;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.apache.http.HttpException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

import com.example.co.squeeg.FavouriteAnimationLib.LikeButton;
import com.example.co.squeeg.FavouriteAnimationLib.OnLikeListener;
import com.example.co.squeeg.Model.GETProfession;
import com.example.co.squeeg.Model.POSTAddFav;
import com.example.co.squeeg.Model.POSTDetailGig;
import com.example.co.squeeg.Model.POSTLanguageModel;
import com.example.co.squeeg.Model.POSTRemoveFav;
import com.example.co.squeeg.Model.POSTVisitGig;
import com.example.co.squeeg.adapter.DetailGigsReviewAdapter;
import com.example.co.squeeg.adapter.HorizontalRecommendedGigsAdapter;
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

public class DetailGigs extends BaseActivity implements View.OnClickListener {

    TextView toolTitle, username, gigsTitle, userprofession, gigsDesc, gigsNos, gigsCountry, gigsUserCount, gigsSpeaks, gigsReviews;
    Toolbar toolbar;
    String gigs_title = "", gigs_id = "", user_type;
    CircleImageView profileUserImage;
    ImageView inputDescShow, inputDescHide, inputGigsFav, mGigsImages;
    Button orderNow;
    CustomProgressDialog mCustomProgressDialog;
    HashMap<String, String> postGigDetails = new HashMap<String, String>();
    HashMap<String, String> postVisitGigs = new HashMap<String, String>();
    Gson gson;
    private GETProfession[] getProfession;
    private RecyclerView horizontalRecommendedGigs;
    DetailGigsReviewAdapter detailGigsReviewAdapter;
    private RatingBar gigsRating;
    private double sum;
    private ListView mDetailGigsReview;
    private RelativeLayout GigsReviews, GigsSimilar, GigsExtras;
    private LinearLayout inputGigsExtras, inputGigsSuperFastExtras, inputGigsUserCount, inputGigsTotalViews, inputGigsUserCountry, inputGigsUserSpeaks, inputGigsUserProfile;
    private ArrayList<POSTDetailGig.Extra_gig> OrderGigs = new ArrayList<>();
    private String seller_gigs_user_id = "";
    private POSTDetailGig.Gigs_details mGigs_details;
    private String SUPERFAST_DESC, SUPERFAST_DAYS, SUPERFAST_CHARGES;
    private HashMap<String, String> postDetails = new HashMap<String, String>();
    //Dynamic Extras view
    CheckBox inputCheckBox, inputSuperFastCheckBox;
    TextView inputCost, inputSuperFastCost, inputSimilarGigs;
    String gigsUserId = "", deliveryDays = "", category_id = "";
    LinearLayout ldetailGigs;
    LikeButton likeButton;
    int lineCount;
    private Boolean isFav;
    public String token;
    public POSTLanguageModel.Detail_gigs detail_gigs = new POSTLanguageModel().new Detail_gigs();
    public POSTLanguageModel.Common_strings commonStrings = new POSTLanguageModel().new Common_strings();
    private CustomTextView ctvDescription, ctvUserInfo, ctvTotalViews, ctvCountry, ctvUserCount, ctvSpeaks, ctvExtras, ctvReviews, ctvSimilarGigs, ctvSuperfast;
    private String userType;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_gigs);

        mCustomProgressDialog = new CustomProgressDialog(this);
        Fresco.initialize(this);
        Utils.freeMemory();

        gson = new Gson();
        user_type = getIntent().getStringExtra(AppConstants.USER_TYPE);
        gigs_id = getIntent().getStringExtra(AppConstants.GIGS_ID);
        gigs_title = getIntent().getStringExtra(AppConstants.GIGS_TITLE);
        initLayouts();

        if (user_type.equalsIgnoreCase("Buyer"))
            orderNow.setVisibility(View.GONE);

        setLanguageValues();
        getProfession = gson.fromJson(SessionHandler.getInstance().get(DetailGigs.this, AppConstants.PROFESSION), GETProfession[].class);
        postGigDetails.put("gig_id", gigs_id);
        postVisitGigs.put("gig_id", gigs_id);

        if (NetworkChangeReceiver.isConnected()) {
            mCustomProgressDialog.showDialog();
            if (SessionHandler.getInstance().get(DetailGigs.this, AppConstants.TOKEN_ID) != null) {
                postVisitGigs.put("user_id", SessionHandler.getInstance().get(DetailGigs.this, AppConstants.TOKEN_ID));
                postGigDetails.put("userid", SessionHandler.getInstance().get(DetailGigs.this, AppConstants.TOKEN_ID));
                token = SessionHandler.getInstance().get(DetailGigs.this, AppConstants.TOKEN_ID);
                postVisitGigs();
            } else {
                token = AppConstants.GUEST_TOKEN;
                //postGigDetails.put("userid", "");
            }
            getDetailGig();

        } else {
            Utils.toastMessage(DetailGigs.this, commonStrings.getLbl_enable_internet().getName());
        }

        inputGigsFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postDetails.put("user_id", SessionHandler.getInstance().get(DetailGigs.this, AppConstants.TOKEN_ID));
                if (isFav) {
                    removeFavAPI();
                    isFav = false;
                } else {
                    isFav = true;
                    addFavAPI();
                }
            }
        });

        likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                postDetails.put("user_id", SessionHandler.getInstance().get(DetailGigs.this, AppConstants.TOKEN_ID));
                addFavAPI();
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                postDetails.put("user_id", SessionHandler.getInstance().get(DetailGigs.this, AppConstants.TOKEN_ID));
                removeFavAPI();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void postVisitGigs() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.postVisitedGigs(postVisitGigs, token, SessionHandler.getInstance().get(this, AppConstants.Language)).enqueue(new Callback<POSTVisitGig>() {
            @Override
            public void onResponse(Call<POSTVisitGig> call, Response<POSTVisitGig> response) {
//                if (response.body().getCode().equals(200)) {
//                    Log.d("TAG", response.body().getMessage());
//                } else {
//                    Log.d("TAG", response.body().getMessage());
//                }
            }

            @Override
            public void onFailure(Call<POSTVisitGig> call, Throwable t) {

            }
        });
    }

    private void getDetailGig() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.getDetailGigs(postGigDetails, token, SessionHandler.getInstance().get(this, AppConstants.Language)).enqueue(new Callback<POSTDetailGig>() {
            @Override
            public void onResponse(Call<POSTDetailGig> call, final Response<POSTDetailGig> response) {
                if (response.body().getCode().equals(200)) {
                    if (response.body().getData().size() > 0) {
                        POSTDetailGig.Gigs_details gigs_details = response.body().getData().get(0).getGigs_details();

                        sellerLogin(response);

                        Picasso.get().load(AppConstants.BASE_URL + gigs_details.getImage()).placeholder(R.drawable.no_image).into(mGigsImages);
                        //mGigsImages.setImageURI(Uri.parse(AppConstants.ImageBaseURL + gigs_details.getImage()));
                        Picasso.get().load(AppConstants.BASE_URL + gigs_details.getUser_thumb_image()).placeholder(R.drawable.ic_no_image_100).into(profileUserImage);
                        //profileUserImage.setImageURI(Uri.parse(AppConstants.ImageBaseURL + gigs_details.getUser_thumb_image()));
                        username.setText(gigs_details.getFullname());
                        if (getProfession != null) {
                            for (int i = 0; i < getProfession.length; i++) {
                                if (getProfession[i].getId().equalsIgnoreCase(gigs_details.getProfession())) {
                                    userprofession.setText(getProfession[i].getProfession_name());
                                }
                            }
                        }

                        if (gigs_details.getCategory_id() != null && !gigs_details.getCategory_id().isEmpty()) {
                            category_id = gigs_details.getCategory_id();
                        }

                        if (gigs_details.getCountry() != null && !gigs_details.getCountry().isEmpty()) {
                            gigsCountry.setText(gigs_details.getCountry());
                        } else {
                            inputGigsUserCountry.setVisibility(View.GONE);
                        }
                        if (gigs_details.getTotal_views() != null && !gigs_details.getTotal_views().isEmpty()) {
                            gigsNos.setText(gigs_details.getTotal_views());
                        } else {
                            inputGigsTotalViews.setVisibility(View.GONE);
                        }
                        if (gigs_details.getGig_usercount() != null && !gigs_details.getGig_usercount().isEmpty()) {
                            gigsUserCount.setText(gigs_details.getGig_usercount());
                        } else {
                            inputGigsUserCount.setVisibility(View.GONE);
                        }
                        if (gigs_details.getLang_speaks() != null && !gigs_details.getLang_speaks().isEmpty()) {
                            if (gigs_details.getLang_speaks().endsWith(",")) {
                                gigsSpeaks.setText(gigs_details.getLang_speaks().substring(0, gigs_details.getLang_speaks().length() - 1));
                            } else {
                                gigsSpeaks.setText(gigs_details.getLang_speaks());
                            }

                        } else {
                            inputGigsUserSpeaks.setVisibility(View.GONE);
                        }

                        AppConstants.DOLLAR_SIGN = gigs_details.getCurrency_sign();
                        AppConstants.CURRENCY_TYPE = gigs_details.getCurrency_type();

                        String description = Html.fromHtml(gigs_details.getGig_details()).toString();
                        gigsDesc.setText(description);

                        gigsDesc.post(new Runnable() {
                            @Override
                            public void run() {
                                lineCount = gigsDesc.getLineCount();
                                if (lineCount > 3) {
                                    inputDescShow.setVisibility(View.VISIBLE);
                                    inputDescHide.setVisibility(View.GONE);
                                    gigsDesc.setMaxLines(4);
                                }
                            }
                        });

                        if (SessionHandler.getInstance().get(DetailGigs.this, AppConstants.TOKEN_ID) != null) {
                            if (SessionHandler.getInstance().get(DetailGigs.this, AppConstants.TOKEN_ID).equalsIgnoreCase(gigs_details.getUnique_code())) {
                                orderNow.setText(detail_gigs.getBtn_edit_gigs().getName());
                                likeButton.setVisibility(View.GONE);
                                inputGigsFav.setVisibility(View.GONE);
                                inputSimilarGigs.setVisibility(View.GONE);
                                gigsReviews.setVisibility(View.GONE);

                            }
                        } else {
                            likeButton.setVisibility(View.GONE);
                            inputGigsFav.setVisibility(View.GONE);
                            inputSimilarGigs.setVisibility(View.GONE);
                            gigsReviews.setVisibility(View.GONE);
                        }

                        gigsRating.setRating(Float.parseFloat(gigs_details.getGig_rating()));
                    } else {
                        Utils.toastMessage(DetailGigs.this, response.body().getMessage());
                    }
                } else if (response.body().getCode().equals(Integer.parseInt(AppConstants.INVALID_RESPONSE_CODE))) {
                    NetworkAlertDialog.networkAlertDialog(DetailGigs.this, "", response.body().getMessage(), null, null);
                } else if (response.body().getCode().equals(AppConstants.InactiveStatusResponse)) {
                    Utils.createUserInActiceAlert(DetailGigs.this, response.body().getMessage());
                } else {
                    Utils.toastMessage(DetailGigs.this, response.body().getMessage());
                }

                ldetailGigs.setVisibility(View.VISIBLE);
                mCustomProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<POSTDetailGig> call, Throwable t) {
                Log.i("TAG", t.getMessage());
                mCustomProgressDialog.dismiss();
                if (t instanceof UnknownHostException || t instanceof HttpException || t instanceof ConnectException || t instanceof SocketTimeoutException || t instanceof SocketException || t instanceof IOException) {
                    //Toast.makeText(DetailGigs.this, getString(R.string.err_server_msg), Toast.LENGTH_SHORT).show();
                    NetworkAlertDialog.networkAlertDialog(DetailGigs.this, commonStrings.getLbl_network_err().getName(),
                            commonStrings.getLbl_server_err().getName(), detailGigsRunn, null);
                }
            }
        });
    }

    Runnable detailGigsRunn = new Runnable() {
        @Override
        public void run() {
            //TODO: API call
            getDetailGig();
        }
    };

    @SuppressLint("ResourceType")
    private void sellerLogin(final Response<POSTDetailGig> response) {
        for (int i = 0; i < response.body().getData().size(); i++) {
            mGigs_details = response.body().getData().get(i).getGigs_details();
            gigsUserId = response.body().getData().get(i).getGigs_details().getUnique_code();
            SessionHandler.getInstance().save(DetailGigs.this, AppConstants.GigUserID, gigsUserId);
            final int position = i;

            if (SessionHandler.getInstance().get(DetailGigs.this, AppConstants.TOKEN_ID) != null) {
                if (SessionHandler.getInstance().get(DetailGigs.this, AppConstants.TOKEN_ID).equalsIgnoreCase(gigsUserId)) {
                    inputGigsFav.setVisibility(View.GONE);
                    likeButton.setVisibility(View.GONE);
                } else {
//                    inputGigsFav.setVisibility(View.VISIBLE);
                    likeButton.setVisibility(View.VISIBLE);
                    if (response.body().getData().get(i).getGigs_details().getFavourite().equalsIgnoreCase("1")) {
                        isFav = true;
                        likeButton.setLiked(Boolean.TRUE);
                        inputGigsFav.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
                        inputGigsFav.setImageResource(R.drawable.ic_favorite_filled_24dp);
                        postDetails.put("gig_id", response.body().getData().get(position).getGigs_details().getId());

                    } else {
                        isFav = false;
                        likeButton.setLiked(Boolean.FALSE);
                        inputGigsFav.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
                        postDetails.put("gig_id", response.body().getData().get(position).getGigs_details().getId());
                        inputGigsFav.setImageResource(R.drawable.ic_favorite_border_purple_24dp);

                    }
                }
            } else {
                inputGigsFav.setVisibility(View.GONE);
                likeButton.setVisibility(View.GONE);
            }

            Float.parseFloat(response.body().getData().get(i).getGigs_details().getGig_price());

            sum = Double.parseDouble(response.body().getData().get(i).getGigs_details().getGig_price());
            orderNow.setText(detail_gigs.getBtn_order_now().getName() + AppConstants.DOLLAR_SIGN + new DecimalFormat("0.00").format(sum));
            deliveryDays = response.body().getData().get(i).getGigs_details().getDelivering_days();

            if (response.body().getData().get(i).getSimilar_gigs().size() > 0) {
                HorizontalRecommendedGigsAdapter horizontalRecommendedGigsAdapter = new HorizontalRecommendedGigsAdapter(DetailGigs.this, response.body().getData().get(i).getSimilar_gigs());
                horizontalRecommendedGigs.setAdapter(horizontalRecommendedGigsAdapter);
            } else {
                horizontalRecommendedGigs.setVisibility(View.GONE);
                GigsSimilar.setVisibility(View.GONE);
            }


            if (response.body().getData().get(i).getGigs_details().getExtra_gigs().size() > 0) {
                GigsExtras.setVisibility(View.VISIBLE);
                for (int j = 0; j < response.body().getData().get(i).getGigs_details().getExtra_gigs().size(); j++) {
                    View checkBoxView = getLayoutInflater().inflate(R.layout.checkbox_view_extras, null);
                    inputGigsExtras.addView(checkBoxView);
                    inputCheckBox = (CheckBox) checkBoxView.findViewById(R.id.cb_extras);
                    inputCost = (TextView) checkBoxView.findViewById(R.id.cost);

                    if (SessionHandler.getInstance().get(DetailGigs.this, AppConstants.TOKEN_ID) != null) {
                        if (SessionHandler.getInstance().get(DetailGigs.this, AppConstants.TOKEN_ID).equalsIgnoreCase(gigsUserId)) {
                            inputCheckBox.setButtonDrawable(null);
                        }
                        inputCheckBox.setFocusable(true);
                        inputCheckBox.setClickable(true);
                        inputCheckBox.setFocusableInTouchMode(true);
                    } else {
                        inputCheckBox.setButtonDrawable(null);
                        inputCheckBox.setFocusable(false);
                        inputCheckBox.setClickable(false);
                        inputCheckBox.setFocusableInTouchMode(false);
                    }

                    inputCheckBox.setText(response.body().getData().get(i).getGigs_details().getExtra_gigs().get(j).getExtra_gigs());
                    inputCost.setText(AppConstants.DOLLAR_SIGN + response.body().getData().get(i).getGigs_details().getExtra_gigs().get(j).getExtra_gigs_amount() + " in " +
                            response.body().getData().get(i).getGigs_details().getExtra_gigs().get(j).getExtra_gigs_delivery() + " day");

                    final int finalI = i;
                    final int finalJ = j;

                    inputCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                            if (SessionHandler.getInstance().get(DetailGigs.this, AppConstants.TOKEN_ID) != null) {
                                if (!orderNow.getText().toString().equalsIgnoreCase(detail_gigs.getBtn_edit_gigs().getName())) {
                                    if (checked) {
                                        sum = sum + Double.parseDouble(response.body().getData().get(finalI).getGigs_details().getExtra_gigs().get(finalJ).getExtra_gigs_amount());
                                        orderNow.setText(detail_gigs.getBtn_order_now().getName() + AppConstants.DOLLAR_SIGN + new DecimalFormat("0.00").format(sum));
                                        OrderGigs.add(response.body().getData().get(finalI).getGigs_details().getExtra_gigs().get(finalJ));
                                    } else {
                                        sum = sum - Double.parseDouble(response.body().getData().get(finalI).getGigs_details().getExtra_gigs().get(finalJ).getExtra_gigs_amount());
                                        orderNow.setText(detail_gigs.getBtn_order_now().getName() + AppConstants.DOLLAR_SIGN + new DecimalFormat("0.00").format(sum));
                                        if (OrderGigs.contains(response.body().getData().get(finalI).getGigs_details().getExtra_gigs().get(finalJ))) {
                                            OrderGigs.remove(response.body().getData().get(finalI).getGigs_details().getExtra_gigs().get(finalJ));
                                        }
                                    }
                                }
                            } else {
                                Toast.makeText(DetailGigs.this, getResources().getString(R.string.txt_need_login_for_extras), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }
            } else {
                inputGigsExtras.setVisibility(View.GONE);
                GigsExtras.setVisibility(View.GONE);
            }

            if (response.body().getData().get(i).getGigs_details().getSuper_fast_charges().isEmpty()
                    && response.body().getData().get(i).getGigs_details().getSuper_fast_delivery_desc().isEmpty() &&
                    response.body().getData().get(i).getGigs_details().getSuper_fast_days().isEmpty()) {
                inputGigsSuperFastExtras.setVisibility(View.GONE);
                GigsExtras.setVisibility(View.GONE);
                SUPERFAST_CHARGES = "";
                SUPERFAST_DAYS = "";
                SUPERFAST_DESC = "";
            } else {
                inputGigsSuperFastExtras.setVisibility(View.VISIBLE);
                GigsExtras.setVisibility(View.VISIBLE);

                if (SessionHandler.getInstance().get(DetailGigs.this, AppConstants.TOKEN_ID) != null) {
                    if (SessionHandler.getInstance().get(DetailGigs.this, AppConstants.TOKEN_ID).equalsIgnoreCase(gigsUserId)) {
                        inputSuperFastCheckBox.setButtonDrawable(null);
                    }
                    inputSuperFastCheckBox.setFocusableInTouchMode(true);
                    inputSuperFastCheckBox.setFocusable(true);
                    inputSuperFastCheckBox.setClickable(true);
                } else {
                    inputSuperFastCheckBox.setButtonDrawable(null);
                    inputSuperFastCheckBox.setFocusableInTouchMode(false);
                    inputSuperFastCheckBox.setFocusable(false);
                    inputSuperFastCheckBox.setClickable(false);
                }


                inputSuperFastCheckBox.setText(response.body().getData().get(i).getGigs_details().getSuper_fast_delivery_desc());
                final int finalI1 = i;
                inputSuperFastCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                        if (SessionHandler.getInstance().get(DetailGigs.this, AppConstants.TOKEN_ID) != null) {
                            if (!SessionHandler.getInstance().get(DetailGigs.this, AppConstants.TOKEN_ID).equalsIgnoreCase(gigsUserId)) {
                                if (checked) {
                                    sum = sum + Double.parseDouble(response.body().getData().get(finalI1).getGigs_details().getSuper_fast_charges());
                                    SUPERFAST_CHARGES = response.body().getData().get(finalI1).getGigs_details().getSuper_fast_charges();
                                    SUPERFAST_DAYS = response.body().getData().get(finalI1).getGigs_details().getSuper_fast_days();
                                    SUPERFAST_DESC = response.body().getData().get(finalI1).getGigs_details().getSuper_fast_delivery_desc();
                                    orderNow.setText(detail_gigs.getBtn_order_now().getName() + AppConstants.DOLLAR_SIGN + new DecimalFormat("0.00").format(sum));
                                } else {
                                    sum = sum - Double.parseDouble(response.body().getData().get(finalI1).getGigs_details().getSuper_fast_charges());
                                    SUPERFAST_CHARGES = "";
                                    SUPERFAST_DAYS = "";
                                    SUPERFAST_DESC = "";

                                    orderNow.setText(detail_gigs.getBtn_order_now().getName() + AppConstants.DOLLAR_SIGN + new DecimalFormat("0.00").format(sum));

                                }
                            }

                        } else {
                            Toast.makeText(DetailGigs.this, getResources().getString(R.string.txt_need_login_for_extras), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                inputSuperFastCost.setText("For " + AppConstants.DOLLAR_SIGN + response.body().getData().get(i).getGigs_details().getSuper_fast_charges() + " in "
                        + response.body().getData().get(i).getGigs_details().getSuper_fast_days() + " day");
            }

            if (response.body().getData().get(i).getReviews().size() > 0) {
                AppConstants.reviewList = response.body().getData().get(i).getReviews();
                detailGigsReviewAdapter = new DetailGigsReviewAdapter(DetailGigs.this, response.body().getData().get(i).getReviews());
                mDetailGigsReview.setAdapter(detailGigsReviewAdapter);
                Utils.getListViewSize(mDetailGigsReview);
            } else {
                GigsReviews.setVisibility(View.GONE);
                mDetailGigsReview.setVisibility(View.GONE);
            }
        }
    }

    private void addFavAPI() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.addFav(postDetails, SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID), SessionHandler.getInstance().get(this, AppConstants.Language)).enqueue(new Callback<POSTAddFav>() {
            @Override
            public void onResponse(Call<POSTAddFav> call, Response<POSTAddFav> response) {
                if (response.body().getCode().equals(200)) {
//                    Utils.toastMessage(DetailGigs.this, response.body().getMessage());
                    inputGigsFav.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
                    inputGigsFav.setImageResource(R.drawable.ic_favorite_filled_24dp);
                } else {
                    Utils.toastMessage(DetailGigs.this, response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<POSTAddFav> call, Throwable t) {
                Log.i("TAG", t.getMessage());
            }
        });

    }

    private void initLayouts() {
        toolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        setSupportActionBar(toolbar);
        toolTitle = (TextView) findViewById(R.id.tv_gigs_title);
        profileUserImage = (CircleImageView) findViewById(R.id.iv_profile_image);
        mGigsImages = (ImageView) findViewById(R.id.iv_gigs_images);
        username = (TextView) findViewById(R.id.tv_gigs_username);
        userprofession = (TextView) findViewById(R.id.tv_gigs_profession);
        orderNow = (Button) findViewById(R.id.btn_order_now);
        gigsDesc = (TextView) findViewById(R.id.tv_gigs_desc);
        gigsNos = (TextView) findViewById(R.id.gigs_nos_views);
        gigsCountry = (TextView) findViewById(R.id.country);
        gigsUserCount = (TextView) findViewById(R.id.gigs_user_count);
        gigsSpeaks = (TextView) findViewById(R.id.tv_user_speaks);
        gigsRating = (RatingBar) findViewById(R.id.rating_gigs);
        horizontalRecommendedGigs = (RecyclerView) findViewById(R.id.horizontal_recent_popular_gigs);
        inputSuperFastCost = (TextView) findViewById(R.id.superfastcost);
        inputSuperFastCheckBox = (CheckBox) findViewById(R.id.superfastdesc);
        mDetailGigsReview = (ListView) findViewById(R.id.lv_review_gigs);
        inputDescShow = (ImageView) findViewById(R.id.input_show);
        inputDescHide = (ImageView) findViewById(R.id.input_hide);
        GigsReviews = (RelativeLayout) findViewById(R.id.rl_reviews);
        GigsSimilar = (RelativeLayout) findViewById(R.id.rl_similar_gigs);
        inputSimilarGigs = (TextView) findViewById(R.id.tv_recent_gigs_more);
        inputGigsExtras = (LinearLayout) findViewById(R.id.ll_gigs_extras);
        inputGigsSuperFastExtras = (LinearLayout) findViewById(R.id.ll_gigs_superfast_extras);
        GigsExtras = (RelativeLayout) findViewById(R.id.rl_gigs_extras);
        inputGigsUserCount = (LinearLayout) findViewById(R.id.ll_gigs_user_count);
        inputGigsTotalViews = (LinearLayout) findViewById(R.id.ll_gigs_total_views);
        inputGigsUserCountry = (LinearLayout) findViewById(R.id.ll_gigs_user_country);
        inputGigsUserSpeaks = (LinearLayout) findViewById(R.id.ll_gigs_user_speaks);
        inputGigsUserProfile = (LinearLayout) findViewById(R.id.ll_gigs_user_profile);
        inputGigsFav = (ImageView) findViewById(R.id.AD_iv_fav);
        gigsReviews = (TextView) findViewById(R.id.tv_review_gigs_more);
        ldetailGigs = (LinearLayout) findViewById(R.id.ll_detail_gigs);
        ctvCountry = (CustomTextView) findViewById(R.id.ctv_country);
        ctvDescription = (CustomTextView) findViewById(R.id.ctv_description);
        ctvExtras = (CustomTextView) findViewById(R.id.ctv_extras);
        ctvReviews = (CustomTextView) findViewById(R.id.ctv_reviews);
        ctvSimilarGigs = (CustomTextView) findViewById(R.id.ctv_similar_gigs);
        ctvSpeaks = (CustomTextView) findViewById(R.id.ctv_speaks);
        ctvTotalViews = (CustomTextView) findViewById(R.id.ctv_total_views);
        ctvUserCount = (CustomTextView) findViewById(R.id.ctv_user_count);
        ctvUserInfo = (CustomTextView) findViewById(R.id.ctv_user_info);
        ctvSuperfast = (CustomTextView) findViewById(R.id.ctv_superfast);
        likeButton = (LikeButton) findViewById(R.id.heart_button);

        LinearLayoutManager recentPopularGigsLayoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        horizontalRecommendedGigs.setLayoutManager(recentPopularGigsLayoutManager);
        toolTitle.setText(gigs_title);
        inputDescHide.setOnClickListener(this);
        inputDescShow.setOnClickListener(this);
        inputSimilarGigs.setOnClickListener(this);
        inputGigsUserProfile.setOnClickListener(this);
        gigsReviews.setOnClickListener(this);
    }

    public void goToBuy(View view) {

        if (SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID) != null) {
            if (SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID).equalsIgnoreCase(gigsUserId)) {
                Intent CallEditGigs = new Intent(DetailGigs.this, EditGigs.class);
                CallEditGigs.putExtra(AppConstants.GIGS_ID, gigs_id);
                startActivity(CallEditGigs);
                finish();
            } else {
                Bundle bundle = new Bundle();
                bundle.putSerializable("order_gigs", OrderGigs);
                Intent callOrderDetails = new Intent(DetailGigs.this, CartModule.class);
                callOrderDetails.putExtra("Gigs_Detail", mGigs_details);
                callOrderDetails.putExtra("total_cost", sum);
                callOrderDetails.putExtra("delivery_days", deliveryDays);
                callOrderDetails.putExtra(AppConstants.GIGS_ID, gigs_id);
                callOrderDetails.putExtra(AppConstants.SUPERFAST_CHARGES, SUPERFAST_CHARGES);
                callOrderDetails.putExtra(AppConstants.SUPERFAST_DAYS, SUPERFAST_DAYS);
                callOrderDetails.putExtra(AppConstants.SUPERFAST_DELIVERY_DESC, SUPERFAST_DESC);
                callOrderDetails.putExtras(bundle);
                startActivity(callOrderDetails);
            }
        } else {
            Intent CallEditGigs = new Intent(DetailGigs.this, LoginScreen.class);
            CallEditGigs.putExtra(AppConstants.GIGS_ID, gigs_id);
            CallEditGigs.putExtra(AppConstants.GIGS_TITLE, gigs_title);
            startActivity(CallEditGigs);
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.input_show) {
            inputDescShow.setVisibility(View.GONE);
            inputDescHide.setVisibility(View.VISIBLE);
            gigsDesc.setMaxLines(Integer.MAX_VALUE);
        } else if (view.getId() == R.id.input_hide) {
            inputDescShow.setVisibility(View.VISIBLE);
            inputDescHide.setVisibility(View.GONE);
            gigsDesc.setMaxLines(4);
        } else if (view.getId() == R.id.tv_recent_gigs_more) {
            Intent CallGigsList = new Intent(DetailGigs.this, SearchGigsList.class);
            CallGigsList.putExtra("cat_id", category_id);
            startActivity(CallGigsList);
        } else if (view.getId() == R.id.ll_gigs_user_profile) {
            if (SessionHandler.getInstance().get(DetailGigs.this, AppConstants.TOKEN_ID) != null) {
                Intent callUserProfile = new Intent(DetailGigs.this, DetailGigsUserInformation.class);
                callUserProfile.putExtra(AppConstants.GIGS_TITLE, gigs_title);
                callUserProfile.putExtra(AppConstants.GIGS_ID, gigs_id);
                callUserProfile.putExtra(AppConstants.GigUserID, gigsUserId);
                startActivity(callUserProfile);
            } else {
                Intent CallEditGigs = new Intent(DetailGigs.this, LoginScreen.class);
                startActivity(CallEditGigs);
            }


        } else if (view.getId() == R.id.tv_review_gigs_more) {
            Intent callUserProfile = new Intent(DetailGigs.this, UserReviews.class);
            callUserProfile.putExtra(AppConstants.GIGS_ID, gigs_id);
            startActivity(callUserProfile);
        }
    }


    private void removeFavAPI() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.removeFav(postDetails, SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID), SessionHandler.getInstance().get(this, AppConstants.Language)).enqueue(new Callback<POSTRemoveFav>() {
            @Override
            public void onResponse(Call<POSTRemoveFav> call, Response<POSTRemoveFav> response) {
                if (response.body().getCode().equals(200)) {
                    inputGigsFav.setImageTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
//                    Utils.toastMessage(DetailGigs.this, response.body().getMessage());
                    inputGigsFav.setImageResource(R.drawable.ic_favorite_border_purple_24dp);
                } else {
                    Utils.toastMessage(DetailGigs.this, response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<POSTRemoveFav> call, Throwable t) {
                Log.i("TAG", t.getMessage());
            }
        });
    }

    public void setLanguageValues() {
        detail_gigs = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.DETAILGIGS), POSTLanguageModel.Detail_gigs.class);
        commonStrings = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.COMMONSTRINGS), POSTLanguageModel.Common_strings.class);
        ctvUserInfo.setText(detail_gigs.getLbl_user_info().getName());
        ctvUserCount.setText(detail_gigs.getLbl_user_count().getName());
        ctvTotalViews.setText(detail_gigs.getLbl_total_views().getName());
        ctvSpeaks.setText(detail_gigs.getLbl_speaks().getName());
        ctvSimilarGigs.setText(detail_gigs.getLbl_similar_gigs().getName());
        ctvReviews.setText(detail_gigs.getLbl_reviews().getName());
        ctvExtras.setText(detail_gigs.getLbl_extras().getName());
        ctvDescription.setText(detail_gigs.getLbl_description().getName());
        ctvCountry.setText(detail_gigs.getLbl_country().getName());
        inputSimilarGigs.setText(detail_gigs.getLbl_more().getName());
        gigsReviews.setText(detail_gigs.getLbl_more().getName());
        orderNow.setText(detail_gigs.getBtn_order_now().getName());
        ctvSuperfast.setText(detail_gigs.getLbl_super_fast().getName());

        userType = SessionHandler.getInstance().get(DetailGigs.this, AppConstants.USER_TYPE);
        if (userType.equalsIgnoreCase("Seller"))
            orderNow.setVisibility(View.GONE);

    }
}