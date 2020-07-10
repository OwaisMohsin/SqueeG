package com.example.co.squeeg;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.co.squeeg.Model.AddExtraGigs;
import com.example.co.squeeg.Model.GETCategory;
import com.example.co.squeeg.Model.GETPriceDetails;
import com.example.co.squeeg.Model.POSTCreategigs;
import com.example.co.squeeg.Model.POSTEditGigs;
import com.example.co.squeeg.Model.POSTExtraGigs;
import com.example.co.squeeg.Model.POSTLanguageModel;
import com.example.co.squeeg.Model.POSTSubCategory;
import com.example.co.squeeg.network.ApiClient;
import com.example.co.squeeg.network.ApiInterface;
import com.example.co.squeeg.receiver.NetworkChangeReceiver;
import com.example.co.squeeg.utils.AppConstants;
import com.example.co.squeeg.utils.CustomButton;
import com.example.co.squeeg.utils.CustomProgressDialog;
import com.example.co.squeeg.utils.CustomTextView;
import com.example.co.squeeg.utils.InputFilterMinMax;
import com.example.co.squeeg.utils.NetworkAlertDialog;
import com.example.co.squeeg.utils.SessionHandler;
import com.example.co.squeeg.utils.Utils;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.apache.http.HttpException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Prasad on 11/6/2017.
 */

public class EditGigs extends BaseActivity {


    @BindView(R.id.input_layout_title_gigs)
    TextInputLayout inputLayoutTitleGigs;
    @BindView(R.id.input_layout_deliver_day)
    TextInputLayout inputLayoutDeliverDay;
    @BindView(R.id.input_layout_gig_cost)
    TextInputLayout inputLayoutGigCost;
    @BindView(R.id.input_layout_gig_desc)
    TextInputLayout inputLayoutGigDesc;
    @BindView(R.id.input_layout_extras)
    TextInputLayout inputLayoutExtras;
    @BindView(R.id.input_layout_extras_cost)
    TextInputLayout inputLayoutExtrasCost;
    @BindView(R.id.input_layout_extras_day)
    TextInputLayout inputLayoutExtrasDay;
    @BindView(R.id.input_layout_fast_extras)
    TextInputLayout inputLayoutFastExtras;
    @BindView(R.id.input_layout_fast_extras_cost)
    TextInputLayout inputLayoutFastExtrasCost;
    @BindView(R.id.input_layout_fast_extras_day)
    TextInputLayout inputLayoutFastExtrasDay;
    @BindView(R.id.input_layout_gig_requirement)
    TextInputLayout inputLayoutGigRequirement;
    Unbinder unbinder;
    @BindView(R.id.tv_select_category)
    CustomTextView tvSelectCategory;
    @BindView(R.id.tv_earn_more_money)
    CustomTextView tvEarnMoreMoney;
    @BindView(R.id.tv_title_superfast_delivery)
    CustomTextView tvTitleSuperfastDelivery;
    @BindView(R.id.tv_work_option)
    CustomTextView tvWorkOption;
    @BindView(R.id.rb_remote)
    RadioButton rbRemote;
    @BindView(R.id.rb_onsite)
    RadioButton rbOnsite;
    @BindView(R.id.button_create_gigs)
    CustomButton buttonCreateGigs;

    private EditText titleGigs, gigsDeliverDay, gigsCosts, gigsDesc,
            gigsFastextras, gigsFastextrasCost, gigsFastextrasDay,
            gigsRequirement, extrasTitle, extrasCost, extrasDay;
    private int count, tempCount;
    private POSTExtraGigs mPOSTExtraGigs = new POSTExtraGigs();
    List<POSTExtraGigs> arrExtrasGigs = new ArrayList<POSTExtraGigs>();
    List<AddExtraGigs> arrAddExtrasGigs = new ArrayList<AddExtraGigs>();
    private String gigs_id;

    private LinearLayout llMoreGigs;
    private ImageView closeMoreExtras, doneExtras, inputSelectImage;

    private Spinner spinCategory, spinSubCategory;

    private TextView addMoreGigs;

    private Toolbar mToolbar;

    private String category_id = "", fastExtras = "", fastExtrastitle = "", fastExtrasCost = "", fastExtrasDay = "";

    private CustomProgressDialog mCustomProgressDialog;
    private HashMap<String, String> postGigsDetails = new HashMap<String, String>();
    private HashMap<String, String> postCategoryDetails = new HashMap<String, String>();
    ArrayList<String> subCategoryArray = new ArrayList<String>();
    ArrayList<String> categoryArray = new ArrayList<String>();
    private HashMap<String, String> postgigsDetails = new HashMap<String, String>();
    private Gson mGson;
    private LinearLayout LnrChildLayout;
    MultipartBody.Part body;
    RequestBody requestFile;
    private CircleImageView ivImage;

    RequestBody gig_id;
    RequestBody user_id;
    RequestBody title;
    RequestBody delivering_time;
    RequestBody gig_price;
    RequestBody gig_tags;
    RequestBody category_ids;
    RequestBody gig_details;
    RequestBody super_fast_delivery;
    RequestBody super_fast_delivery_desc;
    RequestBody super_fast_delivery_date;
    RequestBody super_fast_charges;
    RequestBody requirements;
    RequestBody work_option;
    RequestBody terms_conditions;
    RequestBody extra_gigs;
    RequestBody time_zone;
    private RadioGroup workOption;
    private RadioButton rb, remote, onsite;
    String role = "", workoption, cost_price = "", gigs_cost = "", extrascost = "", gigsPriceCost = "", gigsPriceExtraCost = "", priceType = "";

    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    Bitmap thumbnail;
    private String userChoosenTask;
    ByteArrayOutputStream bytes;
    String encodeImage;
    public POSTLanguageModel.Sell_gigs_screen sell_gigs_screen = new POSTLanguageModel().new Sell_gigs_screen();
    public POSTLanguageModel.Common_strings commonStrings = new POSTLanguageModel().new Common_strings();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_gigs);

        mCustomProgressDialog = new CustomProgressDialog(this);
        mGson = new Gson();
        Utils.freeMemory();
        if (getIntent().getStringExtra(AppConstants.GIGS_ID) != null) {
            gigs_id = getIntent().getStringExtra(AppConstants.GIGS_ID);
        } else {
            gigs_id = "";
        }

        mToolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        setSupportActionBar(mToolbar);
        unbinder = ButterKnife.bind(this);
        titleGigs = (EditText) findViewById(R.id.input_title_gigs);
        gigsDeliverDay = (EditText) findViewById(R.id.input_deliver_day);
        gigsCosts = (EditText) findViewById(R.id.input_gig_cost);
        gigsDesc = (EditText) findViewById(R.id.input_desc);
        extrasCost = (EditText) findViewById(R.id.input_extras_cost);
        extrasDay = (EditText) findViewById(R.id.input_extras_day);
        gigsFastextras = (EditText) findViewById(R.id.input_fast_extras);
        gigsFastextrasCost = (EditText) findViewById(R.id.input_fast_extras_cost);
        gigsFastextrasDay = (EditText) findViewById(R.id.input_fast_extras_day);
        gigsRequirement = (EditText) findViewById(R.id.input_requirement);
        addMoreGigs = (TextView) findViewById(R.id.tv_add_more_items);
        llMoreGigs = (LinearLayout) findViewById(R.id.ll_extras);
        spinCategory = (Spinner) findViewById(R.id.spinner_category);
        spinSubCategory = (Spinner) findViewById(R.id.spinner_sub_category);
        inputSelectImage = (ImageView) findViewById(R.id.input_btn_select_picture);
        ivImage = (CircleImageView) findViewById(R.id.input_profile_picture);
        workOption = (RadioGroup) findViewById(R.id.rg_work_option);
        remote = (RadioButton) findViewById(R.id.rb_remote);
        onsite = (RadioButton) findViewById(R.id.rb_onsite);

        setLanguageValues();
        gigsCosts.setFilters(new InputFilter[]{new InputFilterMinMax("1", "10000")});
        extrasCost.setFilters(new InputFilter[]{new InputFilterMinMax("1", "1000")});
        gigsDeliverDay.setFilters(new InputFilter[]{new InputFilterMinMax("1", "29")});
        extrasCost.setHint(AppConstants.DOLLAR_SIGN);
        gigsFastextrasCost.setHint(AppConstants.DOLLAR_SIGN);
        if (NetworkChangeReceiver.isConnected()) {
            mCustomProgressDialog.showDialog();
            if (SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID) != null) {
                postGigsDetails.put("user_id", SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID));
                postGigsDetails.put("gig_id", gigs_id);
            }
            getPriceDetails();
            getEditGigs();
        } else {
            Utils.toastMessage(EditGigs.this, commonStrings.getLbl_enable_internet().getName());
        }

        if (gigsDeliverDay.getText().toString().isEmpty()) {
            handleFastExtrasFocus(false);
        } else {
            handleFastExtrasFocus(true);
        }

        workOption.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                rb = (RadioButton) radioGroup.findViewById(checkedId);
                role = rb.getText().toString();
            }
        });

        gigsDeliverDay.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() != 0) {
                    handleFastExtrasFocus(true);
                    fastExtras = "yes";
                } else {
                    handleFastExtrasFocus(false);
                    fastExtras = "no";
                }


            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                if (s.length() != 0) {
                    handleFastExtrasFocus(true);
                    fastExtras = "yes";
                } else {
                    handleFastExtrasFocus(false);
                    fastExtras = "no";
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                try {
                    if (!gigsDeliverDay.getText().toString().isEmpty())
                        gigsFastextrasCost.setFilters(new InputFilter[]{new InputFilterMinMax("1", gigsDeliverDay.getText().toString())});
                } catch (NullPointerException npe) {
                    npe.printStackTrace();
                }

            }
        });


        inputSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                selectImage();
               /* CropImage.activity(null)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setScaleType(CropImageView.ScaleType.CENTER_CROP)
                        .setActivityTitle("Select Image")
                        .setAspectRatio(2, 2)
                        .setAutoZoomEnabled(false)
                        .setCropShape(CropImageView.CropShape.RECTANGLE)
                        .start(EditGigs.this);*/

            }
        });

        gigsFastextrasDay.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                try {
                    if (!gigsDeliverDay.getText().toString().isEmpty()) {
                        gigsFastextrasDay.setFilters(new InputFilter[]{new InputFilterMinMax("1", gigsDeliverDay.getText().toString())});
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });


        addMoreGigs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (llMoreGigs.indexOfChild(LnrChildLayout) <= 8) {
                    if (tempCount > arrExtrasGigs.size()) {
                        Toast.makeText(EditGigs.this, getResources().getString(R.string.txt_enter_required_details), Toast.LENGTH_SHORT).show();
                    } else {

                        LayoutInflater mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        LnrChildLayout = (LinearLayout) mInflater
                                .inflate(R.layout.activity_add_more_items, null);
                        LnrChildLayout.setLayoutParams(new LinearLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT));
                        llMoreGigs.addView(LnrChildLayout);
                        tempCount++;
                        closeMoreExtras = (ImageView) LnrChildLayout.findViewById(R.id.iv_close);
                        extrasTitle = (EditText) LnrChildLayout.findViewById(R.id.input_sub_extras);
                        extrasCost = (EditText) LnrChildLayout.findViewById(R.id.input_sub_extras_cost);
                        extrasDay = (EditText) LnrChildLayout.findViewById(R.id.input_sub_extras_day);
                        doneExtras = (ImageView) LnrChildLayout.findViewById(R.id.iv_done);
                        extrasCost.setHint(AppConstants.DOLLAR_SIGN);
                        extrasCost.setFilters(new InputFilter[]{new InputFilterMinMax("1", "1000")});


                        /*if (extrascost != null && !extrascost.equalsIgnoreCase("") && cost_price.equalsIgnoreCase("0")) {
                            extrasCost.setText(extrascost);
                            extrasCost.setClickable(false);
                            extrasCost.setEnabled(false);
                        }*/


                        doneExtras.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (LnrChildLayout != null) {
                                    if (!extrasDay.getText().toString().isEmpty() &&
                                            !extrasTitle.getText().toString().isEmpty() &&
                                            !extrasCost.getText().toString().isEmpty()) {
                                        mPOSTExtraGigs = new POSTExtraGigs();

                                        mPOSTExtraGigs.setExtra_gigs(extrasTitle.getText().toString());
                                        mPOSTExtraGigs.setExtra_gigs_amount(extrasCost.getText().toString());
                                        mPOSTExtraGigs.setExtra_gigs_delivery(extrasDay.getText().toString());
                                        count = llMoreGigs.indexOfChild(LnrChildLayout);
                                        mPOSTExtraGigs.setPosition(count);
                                        LnrChildLayout.setTag(count);
                                        arrExtrasGigs.add(count, mPOSTExtraGigs);
                                        closeMoreExtras.setVisibility(View.VISIBLE);
                                        closeMoreExtras.setTag(count);
                                        doneExtras.setVisibility(View.GONE);
                                        if (count == 9) {
                                            addMoreGigs.setVisibility(View.GONE);
                                        }
                                        extrasTitle.setClickable(false);
                                        extrasTitle.setFocusable(false);
                                        extrasDay.setClickable(false);
                                        extrasDay.setFocusable(false);
                                        extrasCost.setClickable(false);
                                        extrasDay.setFocusable(false);

                                    } else {
                                        Toast.makeText(EditGigs.this, getResources().getString(R.string.txt_enter_all_fields), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
                        closeMoreExtras.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                int pos = (Integer) v.getTag();
                                for (int i = 0; i < arrExtrasGigs.size(); i++) {
                                    if (arrExtrasGigs.get(i).getPosition() == pos) {
                                        arrExtrasGigs.remove(i);
                                        llMoreGigs.removeViewAt(i);
                                        count--;
                                        tempCount--;
                                        if (count < 9) {
                                            addMoreGigs.setVisibility(View.VISIBLE);
                                        }

                                    }
                                }


                            }
                        });
                    }


                } else {
                    Toast.makeText(EditGigs.this, getResources().getString(R.string.txt_limit_exceeded), Toast.LENGTH_SHORT).show();
                }
            }
        });


        gigsDesc.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View view, MotionEvent event) {

                if (view.getId() == R.id.input_desc) {
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                    switch (event.getAction() & MotionEvent.ACTION_MASK) {
                        case MotionEvent.ACTION_UP:
                            view.getParent().requestDisallowInterceptTouchEvent(false);
                            break;
                    }
                }
                return false;
            }
        });

        gigsRequirement.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View view, MotionEvent event) {

                if (view.getId() == R.id.input_requirement) {
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                    switch (event.getAction() & MotionEvent.ACTION_MASK) {
                        case MotionEvent.ACTION_UP:
                            view.getParent().requestDisallowInterceptTouchEvent(false);
                            break;
                    }
                }
                return false;
            }
        });


    }

    public void getPriceDetails() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.getPriceDetails(SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID), SessionHandler.getInstance().get(this, AppConstants.Language)).enqueue(new Callback<GETPriceDetails>() {
            @Override
            public void onResponse(Call<GETPriceDetails> call, Response<GETPriceDetails> response) {
                if (response.body().getCode().equals(200)) {
                    SessionHandler.getInstance().save(EditGigs.this, AppConstants.SellGigsPriceOption, String.valueOf(response.body().getData().getPrice_option()));
                    if (response.body().getData().getPrice_option().equalsIgnoreCase("fixed")) {
                        gigsPriceCost = response.body().getData().getGig_price();
                        priceType = response.body().getData().getPrice_option();
                        gigsPriceExtraCost = response.body().getData().getExtra_gig_price();
                        extrascost = response.body().getData().getExtra_gig_price();
                        /*gigsCosts.setText(response.body().getData().getGig_price());
                        gigsCosts.setClickable(false);
                        gigsCosts.setFocusable(false);*/
                    } else {
                        gigsCosts.setClickable(true);
                        gigsCosts.setFocusable(true);
                    }
                } else {
                    /*Toast.makeText(EditGigs.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();*/
                }
            }

            @Override
            public void onFailure(Call<GETPriceDetails> call, Throwable t) {

            }
        });
    }


    private void getEditGigs() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.editGigs(postGigsDetails, SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID), SessionHandler.getInstance().get(this, AppConstants.Language)).enqueue(new Callback<POSTEditGigs>() {
            @Override
            public void onResponse(Call<POSTEditGigs> call, Response<POSTEditGigs> response) {
                if (response.body().getCode().equals(200)) {
                    if (response.body().getData().size() > 0) {

                        POSTEditGigs.Datum data = response.body().getData().get(0);
                        titleGigs.setText(data.getGig_details().getTitle());
                        gigsDeliverDay.setText(data.getGig_details().getDelivering_time());
                        gigsCosts.setText(data.getGig_details().getGig_price());
                        gigsDesc.setText(data.getGig_details().getGig_details());
                        getCategoryList(data.getGig_details());

                        if (!data.getGig_details().getSub_categoryid().equalsIgnoreCase("0")) {
                            spinSubCategory.setVisibility(View.GONE);

                        }

                        if (response.body().getData().get(0).getGig_details().getCost_type().equalsIgnoreCase("0")) {
                            cost_price = "0";
                            gigsCosts.setClickable(false);
                            gigsCosts.setFocusable(false);
                            gigsCosts.setText(response.body().getData().get(0).getGig_details().getGig_price());
                            //gigs_cost = response.body().getData().get(0).getGig_details().getGig_price();
                            /*if (gigsPriceCost.isEmpty()) {
                                gigsCosts.setText(response.body().getData().get(0).getGig_details().getGig_price());
                            } else {
                                gigsCosts.setText(gigsPriceCost);
                            }*/


                            /*if (response.body().getData().get(0).getExtra_gigs() != null && response.body().getData().get(0).getExtra_gigs().size() > 0) {
                                extrascost = response.body().getData().get(0).getExtra_gigs().get(0).getExtra_gigs_amount();
                            }*/
                        } else {
                            gigsCosts.setClickable(true);
                            gigsCosts.setFocusable(true);
                            //gigs_cost = response.body().getData().get(0).getGig_details().getGig_price();
                            gigsCosts.setText(response.body().getData().get(0).getGig_details().getGig_price());
                            cost_price = "1";
                        }


                        if (response.body().getData().get(0).getGig_details().getWork_option().equalsIgnoreCase("0")) {
                            role = "Remote";
                            remote.setChecked(true);
                        } else {
                            role = "Onsite";
                            onsite.setChecked(true);
                        }
                        if (data.getGig_details().getSuper_fast_delivery().equalsIgnoreCase("yes")) {
                            fastExtras = data.getGig_details().getSuper_fast_delivery();
                            gigsFastextras.setText(data.getGig_details().getSuper_fast_delivery_desc());
                            gigsFastextrasCost.setText(data.getGig_details().getSuper_fast_charges());
                            gigsFastextrasDay.setText(data.getGig_details().getSuper_fast_delivery_date());
                        }

                        /*if (!data.getGig_details().getSuper_fast_delivery_date().equalsIgnoreCase("0")) {
                            gigsFastextrasDay.setText(data.getGig_details().getSuper_fast_delivery_date());
                        } else {
                            gigsFastextrasDay.setText("");
                        }
                        if (!data.getGig_details().getSuper_fast_charges().equalsIgnoreCase("0")) {
                            gigsFastextrasCost.setText(data.getGig_details().getSuper_fast_charges());
                        } else {
                            gigsFastextrasCost.setText("");
                        }*/
                        if (response.body().getData().get(0).getGig_images().get(0).getImage_path() != null) {
                            Picasso.get()
                                    .load(AppConstants.ImageBaseURL + response.body().getData().get(0).getGig_images().get(0).getImage_path())
                                    .placeholder(R.drawable.ic_app_icons)
                                    .error(R.drawable.ic_app_icons)
                                    .into(ivImage);
                            //ivImage.setImageURI(Uri.parse(AppConstants.ImageBaseURL + response.body().getData().get(0).getGig_images().get(0).getImage_path()));

                        }
                        gigsRequirement.setText(data.getGig_details().getRequirements());
                        if (data.getExtra_gigs().size() > 0) {
                            if (data.getExtra_gigs().size() == 10) {
                                addMoreGigs.setVisibility(View.GONE);
                            }
                            tempCount = data.getExtra_gigs().size();
                            arrExtrasGigs.clear();
                            for (int i = 0; i < data.getExtra_gigs().size(); i++) {
                                POSTEditGigs.Extra_gig extra_gig_detail = data.getExtra_gigs().get(i);
                                LayoutInflater mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                final LinearLayout LnrChildLayout = (LinearLayout) mInflater
                                        .inflate(R.layout.activity_add_more_items, null);
                                LnrChildLayout.setLayoutParams(new LinearLayout.LayoutParams(
                                        ViewGroup.LayoutParams.MATCH_PARENT,
                                        ViewGroup.LayoutParams.WRAP_CONTENT));
                                llMoreGigs.addView(LnrChildLayout);

                                closeMoreExtras = (ImageView) LnrChildLayout.findViewById(R.id.iv_close);
                                extrasTitle = (EditText) LnrChildLayout.findViewById(R.id.input_sub_extras);
                                extrasCost = (EditText) LnrChildLayout.findViewById(R.id.input_sub_extras_cost);
                                extrasDay = (EditText) LnrChildLayout.findViewById(R.id.input_sub_extras_day);
                                doneExtras = (ImageView) LnrChildLayout.findViewById(R.id.iv_done);

                                extrasTitle.setText(extra_gig_detail.getExtra_gigs());
                                if (response.body().getData().get(0).getGig_details().getCost_type().equalsIgnoreCase("0")) {
                                    if (gigsPriceExtraCost.isEmpty()) {
                                        extrasCost.setText(extra_gig_detail.getExtra_gigs_amount());
                                    } else {
                                        extrasCost.setText(gigsPriceExtraCost);
                                    }

                                } else {
                                    extrasCost.setText(extra_gig_detail.getExtra_gigs_amount());
                                }
                                extrasDay.setText(extra_gig_detail.getExtra_gigs_delivery());

                                extrasTitle.setClickable(false);
                                extrasTitle.setFocusable(false);
                                extrasDay.setClickable(false);
                                extrasDay.setFocusable(false);
                                extrasCost.setClickable(false);
                                extrasCost.setFocusable(false);

                                mPOSTExtraGigs = new POSTExtraGigs();
                                mPOSTExtraGigs.setExtra_gigs(extrasTitle.getText().toString());
                                mPOSTExtraGigs.setExtra_gigs_amount(extrasCost.getText().toString());
                                mPOSTExtraGigs.setExtra_gigs_delivery(extrasDay.getText().toString());
                                count = llMoreGigs.indexOfChild(LnrChildLayout);
                                closeMoreExtras.setTag(count);

                                mPOSTExtraGigs.setPosition(count);
                                LnrChildLayout.setTag(count);
                                arrExtrasGigs.add(count, mPOSTExtraGigs);
                                closeMoreExtras.setVisibility(View.VISIBLE);
                                doneExtras.setVisibility(View.GONE);

                                closeMoreExtras.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        int pos = Integer.parseInt(LnrChildLayout.getTag().toString());
                                        for (int i = 0; i < arrExtrasGigs.size(); i++) {
                                            if (arrExtrasGigs.get(i).getPosition() == pos) {
                                                arrExtrasGigs.remove(i);
                                                llMoreGigs.removeViewAt(i);

                                            }
                                        }
                                        addMoreGigs.setVisibility(View.VISIBLE);
                                        count--;
                                        tempCount--;
                                    }
                                });

                            }

                        }
                    } else {
                        Toast.makeText(EditGigs.this, commonStrings.getLbl_no_data_found().getName(), Toast.LENGTH_SHORT).show();
                    }
                } else if (response.body().getCode().equals(Integer.parseInt(AppConstants.INVALID_RESPONSE_CODE))) {
//                    mCustomProgressDialog.dismiss();
                    NetworkAlertDialog.networkAlertDialog(EditGigs.this, "", response.body().getMessage(), null, null);
                } else if (response.body().getCode().equals(AppConstants.InactiveStatusResponse)) {
                    Utils.createUserInActiceAlert(EditGigs.this, response.body().getMessage());
                } else {
                    Toast.makeText(EditGigs.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }

                mCustomProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<POSTEditGigs> call, Throwable t) {
                mCustomProgressDialog.dismiss();
                Log.i("TAG", t.getMessage());
            }
        });
    }


    private void getCategoryList(final POSTEditGigs.Gig_details gig_details) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.getCategories(SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID), SessionHandler.getInstance().get(this, AppConstants.Language)).enqueue(new Callback<GETCategory>() {
            @Override
            public void onResponse(Call<GETCategory> call, final Response<GETCategory> response) {

                if (response.body().getCode().equals(200)) {
                    if (response.body().getPrimary().size() > 0) {

                        for (int i = 0; i < response.body().getPrimary().size(); i++) {
                            if (!categoryArray.contains(response.body().getPrimary().get(i).getName())) {
                                categoryArray.add(response.body().getPrimary().get(i).getName());
                            }
                        }
                        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(EditGigs.this,
                                android.R.layout.simple_spinner_item, categoryArray);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinCategory.setAdapter(adapter);

                        if (gig_details != null) {
                            for (int i = 0; i < response.body().getPrimary().size(); i++) {
                                if (gig_details.getCategory_id().equalsIgnoreCase(response.body().getPrimary().get(i).getCid())) {
                                    spinCategory.setSelection(i);
                                    category_id = response.body().getPrimary().get(i).getCid();
                                }

                            }
                        }


                        spinCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                spinSubCategory.clearFocus();
                                if (categoryArray.get(position).equalsIgnoreCase(response.body().getPrimary().get(position).getName())) {
                                    category_id = response.body().getPrimary().get(position).getCid();
                                    if (!category_id.isEmpty()) {
                                        postCategoryDetails.put("category_id", category_id);
                                        if (SessionHandler.getInstance().get(EditGigs.this, AppConstants.TOKEN_ID) != null) {
                                            postCategoryDetails.put("user_id", SessionHandler.getInstance().get(EditGigs.this, AppConstants.TOKEN_ID));
                                        }
                                        postCategoryDetails.put("services", "All");
                                        if (response.body().getPrimary().get(position).getSubcategory().equalsIgnoreCase("1")) {
                                            spinSubCategory.setVisibility(View.VISIBLE);
                                            getSubCategory(gig_details);
                                        } else {
                                            spinSubCategory.setVisibility(View.GONE);
                                        }

                                    }

                                }

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    } else {
                        Utils.toastMessage(EditGigs.this, response.body().getMessage());
                    }

                } else {
                    Utils.toastMessage(EditGigs.this, response.body().getMessage());
                }
                mCustomProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<GETCategory> call, Throwable t) {
                Log.i("TAG", t.getMessage());
                mCustomProgressDialog.dismiss();
            }
        });
    }

    private void getSubCategory(final POSTEditGigs.Gig_details gig_details) {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.getSubCategory(postCategoryDetails, SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID), SessionHandler.getInstance().get(this, AppConstants.Language)).enqueue(new Callback<POSTSubCategory>() {
            @Override
            public void onResponse(Call<POSTSubCategory> call, final Response<POSTSubCategory> response) {
                if (response.body().getCode().equals(200)) {
                    if (response.body().getData().size() > 0) {

                        for (int i = 0; i < response.body().getData().size(); i++) {
                            if (!subCategoryArray.contains(response.body().getData().get(i).getName())) {
                                subCategoryArray.add(response.body().getData().get(i).getName());
                            }
                        }
                        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(EditGigs.this,
                                android.R.layout.simple_spinner_item, subCategoryArray);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinSubCategory.setAdapter(adapter);

                        if (gig_details != null) {
                            for (int i = 0; i < response.body().getData().size(); i++) {
                                if (gig_details.getCategory_id().equalsIgnoreCase(response.body().getData().get(i).getCid())) {
                                    spinSubCategory.setSelection(i);
                                    category_id = response.body().getData().get(i).getCid();
                                }

                            }
                        }

                        spinSubCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                spinSubCategory.clearFocus();
                                if (subCategoryArray.get(position).equalsIgnoreCase(response.body().getData().get(position).getName())) {
                                    category_id = response.body().getData().get(position).getCid();
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });


                    } else {
                        Utils.toastMessage(EditGigs.this, response.body().getMessage());
                    }

                } else {
                    Utils.toastMessage(EditGigs.this, response.body().getMessage());
                }
                mCustomProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<POSTSubCategory> call, Throwable t) {
                Log.i("TAG", t.getMessage());
                mCustomProgressDialog.dismiss();
            }
        });

    }

    public void EditGigs(View view) {
        if (titleGigs.getText().toString().isEmpty()) {
            titleGigs.setError(sell_gigs_screen.getTxt_fld_title_gigs().getValidation1());
            return;
        }

        if (gigsDeliverDay.getText().toString().isEmpty()) {
            gigsDeliverDay.setError(sell_gigs_screen.getTxt_fld_deliver_gig().getValidation1());
            return;
        }
        if (gigsCosts.getText().toString().isEmpty()) {
            gigsCosts.setError(sell_gigs_screen.getTxt_fld_gig_cost().getValidation1());
            return;
        }
        if (gigsDesc.getText().toString().isEmpty()) {
            gigsDesc.setError(sell_gigs_screen.getTxt_fld_provide_info().getValidation1());
            return;
        }

        if (gigsRequirement.getText().toString().isEmpty()) {
            gigsRequirement.setError(sell_gigs_screen.getTxt_fld_buyer_needs().getValidation1());
            return;
        }


        if (gigsFastextras.getText().toString().isEmpty() &&
                gigsFastextrasDay.getText().toString().isEmpty()) {
            fastExtras = "No";
            fastExtrasCost = "";
            fastExtrasDay = "";
            fastExtrastitle = "";
        } else {
            fastExtras = "Yes";
            if (!gigsFastextrasDay.getText().toString().isEmpty() &&
                    !gigsFastextras.getText().toString().isEmpty()
                    ) {
                fastExtrasCost = gigsFastextrasCost.getText().toString();
                fastExtrasDay = gigsFastextrasDay.getText().toString();
                fastExtrastitle = gigsFastextras.getText().toString();
            }
        }

        if (role.isEmpty()) {
            Toast.makeText(EditGigs.this, sell_gigs_screen.getLbl_work_option().getValidation1(), Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (role.equalsIgnoreCase("Remote")) {
                workoption = "0";
            } else {
                workoption = "1";
            }
        }


        if (NetworkChangeReceiver.isConnected()) {
            mCustomProgressDialog.showDialog();

            gig_id = RequestBody.create(MediaType.parse("text/plain"), gigs_id);
            user_id = RequestBody.create(MediaType.parse("text/plain"), SessionHandler.getInstance().get(EditGigs.this, AppConstants.TOKEN_ID));
            title = RequestBody.create(MediaType.parse("text/plain"), titleGigs.getText().toString());
            delivering_time = RequestBody.create(MediaType.parse("text/plain"), gigsDeliverDay.getText().toString());
            gig_price = RequestBody.create(MediaType.parse("text/plain"), gigsCosts.getText().toString());
            gig_tags = RequestBody.create(MediaType.parse("text/plain"), "");
            category_ids = RequestBody.create(MediaType.parse("text/plain"), category_id);
            gig_details = RequestBody.create(MediaType.parse("text/plain"), gigsDesc.getText().toString());
            super_fast_delivery = RequestBody.create(MediaType.parse("text/plain"), fastExtras);
            super_fast_delivery_desc = RequestBody.create(MediaType.parse("text/plain"), fastExtrastitle);
            super_fast_delivery_date = RequestBody.create(MediaType.parse("text/plain"), fastExtrasDay);
            super_fast_charges = RequestBody.create(MediaType.parse("text/plain"), fastExtrasCost);
            requirements = RequestBody.create(MediaType.parse("text/plain"), gigsRequirement.getText().toString());
            work_option = RequestBody.create(MediaType.parse("text/plain"), workoption);
            terms_conditions = RequestBody.create(MediaType.parse("text/plain"), "1");


            if (body == null) {
                requestFile = RequestBody.create(MediaType.parse("image/jpeg"), "");
                body = MultipartBody.Part.createFormData("image", "", requestFile);
            }


            if (arrExtrasGigs.size() > 0) {
                arrAddExtrasGigs.clear();
                for (int i = 0; i < arrExtrasGigs.size(); i++) {
                    AddExtraGigs mAddExtraGigs = new AddExtraGigs();
                    mAddExtraGigs.setExtra_gigs(arrExtrasGigs.get(i).getExtra_gigs());
                    mAddExtraGigs.setExtra_gigs_amount(arrExtrasGigs.get(i).getExtra_gigs_amount());
                    mAddExtraGigs.setExtra_gigs_delivery(arrExtrasGigs.get(i).getExtra_gigs_delivery());
                    arrAddExtrasGigs.add(mAddExtraGigs);
                }
            }
            String jsonString = mGson.toJson(arrAddExtrasGigs);

            if (jsonString.equalsIgnoreCase("[]")) {
                extra_gigs = RequestBody.create(MediaType.parse("text/plain"), "");
            } else {
                extra_gigs = RequestBody.create(MediaType.parse("text/plain"), jsonString);
            }

            time_zone = RequestBody.create(MediaType.parse("text/plain"), SessionHandler.getInstance().get(EditGigs.this, AppConstants.TIMEZONE_ID));

            creategigs();
        } else {
            Utils.toastMessage(this, commonStrings.getLbl_enable_internet().getName());
        }
    }

    private void creategigs() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.updateGigs(gig_id, user_id, title, delivering_time, gig_price, gig_tags, gig_details, super_fast_delivery, super_fast_delivery_desc, super_fast_delivery_date,
                super_fast_charges, requirements, work_option, terms_conditions, extra_gigs, category_ids, body, SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID), SessionHandler.getInstance().get(this, AppConstants.Language))
                .enqueue(new Callback<POSTCreategigs>() {
                    @Override
                    public void onResponse(Call<POSTCreategigs> call, Response<POSTCreategigs> response) {
                        if (response.body().getCode().equals(200)) {
                            Toast.makeText(EditGigs.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                        } else if (response.body().getCode().equals(AppConstants.InactiveStatusResponse)) {
                            Utils.createUserInActiceAlert(EditGigs.this, response.body().getMessage());
                        } else {
                            Toast.makeText(EditGigs.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        mCustomProgressDialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<POSTCreategigs> call, Throwable t) {
                        Log.i("TAG", t.getMessage());
                        mCustomProgressDialog.dismiss();
                        if (t instanceof UnknownHostException || t instanceof HttpException || t instanceof ConnectException || t instanceof SocketTimeoutException || t instanceof SocketException || t instanceof IOException) {
                            Toast.makeText(EditGigs.this, commonStrings.getLbl_server_prblm().getName(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void handleFastExtrasFocus(boolean type) {
        gigsFastextras.setFocusable(type);
        gigsFastextras.setFocusableInTouchMode(type);
        gigsFastextras.setClickable(type);
        gigsFastextrasCost.setFocusable(type);
        gigsFastextrasCost.setFocusableInTouchMode(type);
        gigsFastextrasCost.setClickable(type);
        gigsFastextrasDay.setFocusable(type);
        gigsFastextrasDay.setFocusableInTouchMode(type);
        gigsFastextrasDay.setClickable(type);
    }

    /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        // handle result of CropImageActivity
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == Activity.RESULT_OK) {
//                ivImage.setImageURI(result.getUri());
                Bitmap bm = null;
                try {
                    bm = MediaStore.Images.Media.getBitmap(EditGigs.this.getContentResolver(), result.getUri());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                bytes = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
                ivImage.setImageURI(result.getUri());
                requestFile = RequestBody.create(MediaType.parse("image/jpeg"), bytes.toByteArray());
                body = MultipartBody.Part.createFormData("image", "image.jpg", requestFile);
                if (!body.toString().isEmpty()) {

                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(EditGigs.this, "Cropping failed: " + result.getError(), Toast.LENGTH_LONG).show();
            }
        }
    }*/


    private void cameraIntent() {
        try {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, REQUEST_CAMERA);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void galleryIntent() {
        try {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, SELECT_FILE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case Utils.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (userChoosenTask.equals(commonStrings.getTxt_take_photo().getName()))
                        cameraIntent();
                    else if (userChoosenTask.equals(commonStrings.getTxt_choose_from_gallery().getName()))
                        galleryIntent();
                } else {
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    private void selectImage() {
        final CharSequence[] items = {commonStrings.getTxt_take_photo().getName(), commonStrings.getTxt_choose_from_gallery().getName(),
                commonStrings.getLbl_cancel().getName()};
        AlertDialog.Builder builder = new AlertDialog.Builder(EditGigs.this);
        builder.setTitle(commonStrings.getLbl_add_photo().getName());
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utils.checkPermission(EditGigs.this);
                if (items[item].equals(commonStrings.getTxt_take_photo().getName())) {
                    userChoosenTask = commonStrings.getTxt_take_photo().getName();
                    if (result)
                        cameraIntent();
                } else if (items[item].equals(commonStrings.getTxt_choose_from_gallery().getName())) {
                    userChoosenTask = commonStrings.getTxt_choose_from_gallery().getName();
                    if (result)
                        galleryIntent();
                } else if (items[item].equals(commonStrings.getLbl_cancel().getName())) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {
        thumbnail = null;
        try {
            thumbnail = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
        } catch (IOException e) {
            e.printStackTrace();
        }
        bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        Uri selectedImage = data.getData();

        encodeImage = Base64.encodeToString(bytes.toByteArray(), Base64.DEFAULT);

        requestFile = RequestBody.create(MediaType.parse("image/jpeg"), bytes.toByteArray());
        body = MultipartBody.Part.createFormData("image", "image.jpg", requestFile);
        ivImage.setImageBitmap(thumbnail);
        /*ivImage.setImageURI(Uri.parse(data.toURI()));*/
    }

    private void onCaptureImageResult(Intent data) {
        thumbnail = (Bitmap) data.getExtras().get("data");
        bytes = new ByteArrayOutputStream();
        if (thumbnail != null) {
            thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
            encodeImage = Base64.encodeToString(bytes.toByteArray(), Base64.DEFAULT);
        }
        requestFile = RequestBody.create(MediaType.parse("image/jpeg"), bytes.toByteArray());
        body = MultipartBody.Part.createFormData("image", "image.jpg", requestFile);
        ivImage.setImageBitmap(thumbnail);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE) {
                onSelectFromGalleryResult(data);
            } else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    public void freeMemory() {
        System.runFinalization();
        Runtime.getRuntime().gc();
        System.gc();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void setLanguageValues() {
        sell_gigs_screen = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.SELLGIGS), POSTLanguageModel.Sell_gigs_screen.class);
        commonStrings = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.COMMONSTRINGS), POSTLanguageModel.Common_strings.class);
        inputLayoutTitleGigs.setHint(sell_gigs_screen.getTxt_fld_title_gigs().getName());
        inputLayoutDeliverDay.setHint(sell_gigs_screen.getTxt_fld_deliver_gig().getName());
        inputLayoutExtras.setHint(sell_gigs_screen.getLbl_Ican().getName());
        inputLayoutExtrasDay.setHint(sell_gigs_screen.getLbl_day().getName());
        inputLayoutFastExtras.setHint(sell_gigs_screen.getLbl_Ican().getName());
        inputLayoutFastExtrasDay.setHint(sell_gigs_screen.getLbl_day().getName());
        inputLayoutGigCost.setHint(sell_gigs_screen.getTxt_fld_gig_cost().getName());
        inputLayoutGigDesc.setHint(sell_gigs_screen.getTxt_fld_provide_info().getName());
        inputLayoutGigRequirement.setHint(sell_gigs_screen.getTxt_fld_buyer_needs().getName());
        tvEarnMoreMoney.setText(sell_gigs_screen.getLbl_earn_money().getName());
        tvSelectCategory.setText(sell_gigs_screen.getLbl_select_category().getName());
        tvTitleSuperfastDelivery.setText(sell_gigs_screen.getLbl_super_fast_delivery().getName());
        tvWorkOption.setText(sell_gigs_screen.getLbl_work_option().getName());
        addMoreGigs.setText(sell_gigs_screen.getLbl_add_more_items().getName());
        rbOnsite.setText(sell_gigs_screen.getLbl_onsite().getName());
        rbRemote.setText(sell_gigs_screen.getLbl_remote().getName());
        buttonCreateGigs.setText(sell_gigs_screen.getLbl_update_a_gig().getName());
    }
}
