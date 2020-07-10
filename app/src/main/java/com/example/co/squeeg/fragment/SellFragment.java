package com.example.co.squeeg.fragment;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.Html;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.co.squeeg.MainActivity;
import com.example.co.squeeg.Model.AddExtraGigs;
import com.example.co.squeeg.Model.GETCategory;
import com.example.co.squeeg.Model.GETPriceDetails;
import com.example.co.squeeg.Model.GETTermsAndConditions;
import com.example.co.squeeg.Model.POSTCreategigs;
import com.example.co.squeeg.Model.POSTExtraGigs;
import com.example.co.squeeg.Model.POSTLanguageModel;
import com.example.co.squeeg.Model.POSTSubCategory;
import com.example.co.squeeg.Model.POSTSubscriptionModel;
import com.example.co.squeeg.R;
import com.example.co.squeeg.SubscriptionActivity;
import com.example.co.squeeg.network.ApiClient;
import com.example.co.squeeg.network.ApiInterface;
import com.example.co.squeeg.receiver.NetworkChangeReceiver;
import com.example.co.squeeg.utils.AppConstants;
import com.example.co.squeeg.utils.CustomProgressDialog;
import com.example.co.squeeg.utils.CustomTextView;
import com.example.co.squeeg.utils.InputFilterMinMax;
import com.example.co.squeeg.utils.NetworkAlertDialog;
import com.example.co.squeeg.utils.SessionHandler;
import com.example.co.squeeg.utils.Utils;
import com.google.gson.Gson;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

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

import static com.example.co.squeeg.R.id.input_fast_extras;


public class SellFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
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

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View sellViewLayout;

    private Context mContext;

    private EditText titleGigs, gigsDeliverDay, gigsCosts, gigsDesc,
            gigsFastextras, gigsFastextrasCost, gigsFastextrasDay,
            gigsRequirement, extrasTitle, extrasCost, extrasDay, extrasTitle2, extrasCost2, extrasDay2, extrasTitle3, extrasCost3, extrasDay3;
    private LinearLayout llMoreGigs;
    private ImageView closeMoreExtras, doneExtras;

    private Spinner spinCategory, spinSubCategory;

    private TextView addMoreGigs, termsandcondtions;

    private Button createGigs;
    private CustomProgressDialog mCustomProgressDialog;
    private OnFragmentInteractionListener mListener;

    private int count;
    private POSTExtraGigs mPOSTExtraGigs = new POSTExtraGigs();

    List<POSTExtraGigs> arrExtrasGigs = new ArrayList<POSTExtraGigs>();

    private String fastExtras = "",
            category_id = "",
            sSubCategoryId = "";

    private Gson mGson;
    ArrayList<String> subCategoryArray = new ArrayList<String>();
    ArrayList<String> categoryArray = new ArrayList<String>();
    private HashMap<String, String> postCategoryDetails = new HashMap<String, String>();
    List<AddExtraGigs> arrAddExtrasGigs = new ArrayList<AddExtraGigs>();
    LinearLayout LnrChildLayout;
    CheckBox mCheckBox;
    MultipartBody.Part body;
    RequestBody requestFile;
    private ImageView inputSelectImage;
    private CircleImageView ivImage;
    private int tempCount;
    Boolean isAdded = false;
    Handler mHandler;
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
    RequestBody price_option;
    private RadioGroup workOption;
    private RadioButton rb;
    String role = "", workoption;

    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    Bitmap thumbnail;
    private String userChoosenTask;
    ByteArrayOutputStream bytes;
    String encodeImage, price_type = "";
    private String extra_gigs_price = "", gigs_fast_price = "";
    boolean isGranted;
    public POSTLanguageModel.Sell_gigs_screen sell_gigs_screen = new POSTLanguageModel().new Sell_gigs_screen();
    public POSTLanguageModel.Common_strings commonStrings = new POSTLanguageModel().new Common_strings();
    private ArrayList<POSTSubscriptionModel.Subscription> subscriptions = new ArrayList<>();

    public SellFragment() {
        // Required empty public constructor
    }

    public static SellFragment newInstance(String param1, String param2) {
        SellFragment fragment = new SellFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        Utils.freeMemory();

        askPermissions();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mCustomProgressDialog = new CustomProgressDialog(getActivity());
        sellViewLayout = inflater.inflate(R.layout.fragment_sell, container, false);
        mGson = new Gson();

        mContext = getActivity();

        mHandler = new Handler();

        titleGigs = (EditText) sellViewLayout.findViewById(R.id.input_title_gigs);
        gigsDeliverDay = (EditText) sellViewLayout.findViewById(R.id.input_deliver_day);
        gigsCosts = (EditText) sellViewLayout.findViewById(R.id.input_gig_cost);
        gigsDesc = (EditText) sellViewLayout.findViewById(R.id.input_desc);
        extrasCost = (EditText) sellViewLayout.findViewById(R.id.input_extras_cost);
        /* extrasDay = (EditText) sellViewLayout.findViewById(R.id.input_extras_day);*/
        gigsFastextras = (EditText) sellViewLayout.findViewById(input_fast_extras);
        gigsFastextrasCost = (EditText) sellViewLayout.findViewById(R.id.input_fast_extras_cost);
        gigsFastextrasDay = (EditText) sellViewLayout.findViewById(R.id.input_fast_extras_day);
        gigsRequirement = (EditText) sellViewLayout.findViewById(R.id.input_requirement);
        createGigs = (Button) sellViewLayout.findViewById(R.id.button_create_gigs);
        addMoreGigs = (TextView) sellViewLayout.findViewById(R.id.tv_add_more_items);
        llMoreGigs = (LinearLayout) sellViewLayout.findViewById(R.id.ll_extras);
        spinCategory = (Spinner) sellViewLayout.findViewById(R.id.spinner_category);
        spinSubCategory = (Spinner) sellViewLayout.findViewById(R.id.spinner_sub_category);
        mCheckBox = (CheckBox) sellViewLayout.findViewById(R.id.cb_agree_conditions);
        ivImage = (CircleImageView) sellViewLayout.findViewById(R.id.input_profile_picture);
        inputSelectImage = (ImageView) sellViewLayout.findViewById(R.id.input_btn_select_picture);
        gigsFastextrasCost.setHint(AppConstants.DOLLAR_SIGN);
        termsandcondtions = (TextView) sellViewLayout.findViewById(R.id.termsandConditons);
        workOption = (RadioGroup) sellViewLayout.findViewById(R.id.rg_work_option);
        unbinder = ButterKnife.bind(this, sellViewLayout);
//        getPriceDetails();

        if (NetworkChangeReceiver.isConnected()) {
            mCustomProgressDialog.showDialog();
            postSubscribeDetails();
        } else {
            mCustomProgressDialog.dismiss();
            Utils.toastMessage(getActivity(), commonStrings.getLbl_enable_internet().getName());
        }

        gigsFastextrasCost.setFilters(new InputFilter[]{new InputFilterMinMax("1", "1000000")});

        setLanguageLocale();

        if (NetworkChangeReceiver.isConnected()) {
//            mCustomProgressDialog.showDialog();
            getCategoryList();
        } else {
//            mCustomProgressDialog.dismiss();
            Utils.toastMessage(getActivity(), commonStrings.getLbl_enable_internet().getName());
        }

        if (gigsDeliverDay.getText().toString().isEmpty()) {
            handleFastExtrasFocus(false);
        } else {
            handleFastExtrasFocus(true);
        }

        inputSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
            }
        });
        gigsDeliverDay.setFilters(new InputFilter[]{new InputFilterMinMax("1 ", "29")});

        gigsCosts.setFilters(new InputFilter[]{new InputFilterMinMax("1", "1000000")});

        gigsDeliverDay.addTextChangedListener(new TextWatcher() {

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
                        extrasDay.setFilters(new InputFilter[]{new InputFilterMinMax("1", gigsDeliverDay.getText().toString())});

                    }
                } catch (NullPointerException npe) {
                    npe.printStackTrace();
                }

                if (!s.toString().isEmpty()) {
                    if (s.length() != 0) {
                        handleFastExtrasFocus(true);
                        fastExtras = "yes";
                        gigsFastextrasDay.setFilters(new InputFilter[]{new InputFilterMinMax("1", s.toString())});
                    } else {
                        handleFastExtrasFocus(false);
                        fastExtras = "no";
                        gigsFastextras.setText("");
                        gigsFastextrasCost.setText("");
                        gigsFastextrasDay.setText("");
                    }
                } else {
                    handleFastExtrasFocus(false);
                    fastExtras = "no";
                    gigsFastextras.setText("");
                    gigsFastextrasCost.setText("");
                    gigsFastextrasDay.setText("");
                }
            }
        });
        addMoreGigs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (llMoreGigs.indexOfChild(LnrChildLayout) <= 8) {

                    if (tempCount > arrExtrasGigs.size()) {
                        Toast.makeText(getActivity(), R.string.enter_required_details, Toast.LENGTH_SHORT).show();
                    } else {
                        LayoutInflater mInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

                        /*if (price_type.equalsIgnoreCase("0")) {
                            extrasCost.setText(extra_gigs_price);
                            *//*extrasCost.setClickable(false);
                            extrasCost.setFocusable(false);*//*
                        }*/

                        extrasDay.setFilters(new InputFilter[]{new InputFilterMinMax("1", "14")});
                        extrasCost.setFilters(new InputFilter[]{new InputFilterMinMax("1", "1000000")});

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

                                        extrasTitle.setTag(count);
                                        extrasCost.setTag(count);
                                        extrasDay.setTag(count);

                                        mPOSTExtraGigs.setPosition(count);
                                        llMoreGigs.setTag(count);
                                        arrExtrasGigs.add(count, mPOSTExtraGigs);

                                        closeMoreExtras.setVisibility(View.VISIBLE);
                                        closeMoreExtras.setTag(count);
                                        isAdded = true;
                                        doneExtras.setVisibility(View.GONE);
                                        if (count == 9) {
                                            addMoreGigs.setVisibility(View.GONE);
                                            Log.d("Child Count", llMoreGigs.getTag().toString());
                                        }

                                        extrasTitle.setClickable(false);
                                        extrasTitle.setFocusable(false);
                                        extrasDay.setClickable(false);
                                        extrasDay.setFocusable(false);
                                        extrasCost.setClickable(false);
                                        extrasCost.setFocusable(false);


                                        Log.d("Extras Size", String.valueOf(arrExtrasGigs.size()));

                                    } else {
                                        Toast.makeText(getActivity(), "Please enter all the fields...", Toast.LENGTH_SHORT).show();
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
                                addMoreGigs.setClickable(true);

                            }
                        });
                    }
                }
            }
        });

        createGigs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (body == null) {
                    Toast.makeText(getActivity(), sell_gigs_screen.getErr_select_gig_image().getName(), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (titleGigs.getText().toString().isEmpty()) {
                    titleGigs.setError(sell_gigs_screen.getTxt_fld_title_gigs().getValidation1());
                    titleGigs.requestFocus();
                    return;
                }

                if (gigsDeliverDay.getText().toString().isEmpty()) {
                    gigsDeliverDay.setError(sell_gigs_screen.getTxt_fld_deliver_gig().getValidation1());
                    gigsDeliverDay.requestFocus();
                    return;
                }

                if (gigsCosts.getText().toString().isEmpty()) {
                    gigsCosts.setError(sell_gigs_screen.getTxt_fld_gig_cost().getValidation1());
                    gigsCosts.requestFocus();
                    return;
                }

                if (gigsCosts.getText().toString().equalsIgnoreCase("0")) {
                    gigsCosts.setError(sell_gigs_screen.getTxt_fld_gig_cost().getValidation1());
                    gigsCosts.requestFocus();
                    return;
                }

                if (gigsDesc.getText().toString().isEmpty()) {
                    gigsDesc.setError(sell_gigs_screen.getTxt_fld_provide_info().getValidation1());
                    gigsDesc.requestFocus();
                    return;
                }

                if (category_id.isEmpty()) {
                    Utils.toastMessage(mContext, "Select Category");
                    return;
                }

                if (spinSubCategory.getVisibility() == View.VISIBLE && sSubCategoryId.isEmpty()) {
                    sSubCategoryId = "";
                    Utils.toastMessage(mContext, "Select Sub Category");
                    return;
                }

                if (gigsRequirement.getText().toString().isEmpty()) {
                    gigsRequirement.setError(sell_gigs_screen.getTxt_fld_buyer_needs().getValidation1());
                    gigsRequirement.requestFocus();
                    return;
                }

                if (gigsFastextras.getText().toString().isEmpty() &&
                        gigsFastextras.getText().toString().isEmpty() &&
                        gigsFastextras.getText().toString().isEmpty()) {

                    fastExtras = "No";
                } else {
                    fastExtras = "Yes";
                }

                if (role.isEmpty()) {
                    Toast.makeText(getActivity(), sell_gigs_screen.getLbl_work_option().getValidation1(), Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (role.equalsIgnoreCase(sell_gigs_screen.getLbl_remote().getName())) {
                        workoption = "0";
                    } else {
                        workoption = "1";
                    }
                }

                if (!mCheckBox.isChecked()) {
                    Toast.makeText(getActivity(), sell_gigs_screen.getLbl_terms_condition().getValidation1(), Toast.LENGTH_SHORT).show();
                    return;
                }


                if (NetworkChangeReceiver.isConnected()) {
                    mCustomProgressDialog.showDialog();

                    user_id = RequestBody.create(MediaType.parse("text/plain"), SessionHandler.getInstance().get(getActivity(), AppConstants.TOKEN_ID));
                    title = RequestBody.create(MediaType.parse("text/plain"), titleGigs.getText().toString());
                    delivering_time = RequestBody.create(MediaType.parse("text/plain"), gigsDeliverDay.getText().toString());
                    gig_price = RequestBody.create(MediaType.parse("text/plain"), gigsCosts.getText().toString());
                    gig_tags = RequestBody.create(MediaType.parse("text/plain"), "");
                    if (spinSubCategory.getVisibility() != View.VISIBLE)
                        category_ids = RequestBody.create(MediaType.parse("text/plain"), category_id);
                    else
                        category_ids = RequestBody.create(MediaType.parse("text/plain"), sSubCategoryId);

                    gig_details = RequestBody.create(MediaType.parse("text/plain"), gigsDesc.getText().toString());
                    super_fast_delivery = RequestBody.create(MediaType.parse("text/plain"), fastExtras);
                    super_fast_delivery_desc = RequestBody.create(MediaType.parse("text/plain"), gigsFastextras.getText().toString());
                    super_fast_delivery_date = RequestBody.create(MediaType.parse("text/plain"), gigsFastextrasCost.getText().toString());
                    super_fast_charges = RequestBody.create(MediaType.parse("text/plain"), gigsFastextrasDay.getText().toString());
                    requirements = RequestBody.create(MediaType.parse("text/plain"), gigsRequirement.getText().toString());
                    work_option = RequestBody.create(MediaType.parse("text/plain"), workoption);
                    terms_conditions = RequestBody.create(MediaType.parse("text/plain"), "1");
                    price_option = RequestBody.create(MediaType.parse("text/plain"), price_type);
                   /* if (SessionHandler.getInstance().get(getActivity(), AppConstants.SellGigsPriceOption).equalsIgnoreCase("fixed")) {

                    } else {
                        price_option = RequestBody.create(MediaType.parse("text/plain"), "1");
                    }*/


                    if (arrExtrasGigs.size() > 0) {
                        for (int i = 0; i < arrExtrasGigs.size(); i++) {
                            AddExtraGigs mAddExtraGigs = new AddExtraGigs();
                            mAddExtraGigs.setExtra_gigs(arrExtrasGigs.get(i).getExtra_gigs());
                            mAddExtraGigs.setExtra_gigs_amount(arrExtrasGigs.get(i).getExtra_gigs_amount());
                            mAddExtraGigs.setExtra_gigs_delivery(arrExtrasGigs.get(i).getExtra_gigs_delivery());
                            arrAddExtrasGigs.add(mAddExtraGigs);
                        }
                    }

                    String jsonString = mGson.toJson(arrAddExtrasGigs);
                    Log.i("EXTRAS------> ", jsonString);

                    extra_gigs = RequestBody.create(MediaType.parse("text/plain"), jsonString);
                    time_zone = RequestBody.create(MediaType.parse("text/plain"), SessionHandler.getInstance().get(getActivity(), AppConstants.TIMEZONE_ID));

                    creategigs();
                } else {
                    Utils.toastMessage(getActivity(), commonStrings.getLbl_enable_internet().getName());
                }
            }
        });


        termsandcondtions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTermsandConditions();
            }
        });


        workOption.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                rb = (RadioButton) radioGroup.findViewById(checkedId);
                role = rb.getText().toString();
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
        return sellViewLayout;
    }

    public void postSubscribeDetails() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.postSubscription(SessionHandler.getInstance().get(getActivity(), AppConstants.TOKEN_ID), SessionHandler.getInstance().get(getActivity(), AppConstants.TIMEZONE_ID)).enqueue(new Callback<POSTSubscriptionModel>() {
            @Override
            public void onResponse(Call<POSTSubscriptionModel> call, Response<POSTSubscriptionModel> response) {
                mCustomProgressDialog.dismiss();
                if (response.isSuccessful()) {
                    if (response.body().getMessage().equalsIgnoreCase("commission")) {
                        setPriceValues(response);
                    } else {
                        if (response.body().getMessage_key().equalsIgnoreCase("1")) {
                            setPriceValues(response);
                        } else if (response.body().getMessage_key().equalsIgnoreCase("2")) {
                            setPriceValues(response);
                            Bundle bundle = new Bundle();
                            subscriptions.addAll(response.body().getData().getSubscription());
                            bundle.putSerializable("subscription_data", subscriptions);
                            Intent callSubscriptionAct = new Intent(getActivity(), SubscriptionActivity.class);
                            callSubscriptionAct.putExtra("subscription_type", response.body().getMessage_key());
                            callSubscriptionAct.putExtras(bundle);
                            startActivity(callSubscriptionAct);
                        } else if (response.body().getMessage_key().equalsIgnoreCase("3")) {
                            setPriceValues(response);
                            Bundle bundle = new Bundle();
                            subscriptions.addAll(response.body().getData().getSubscription());
                            bundle.putSerializable("subscription_data", subscriptions);
                            Intent callSubscriptionAct = new Intent(getActivity(), SubscriptionActivity.class);
                            callSubscriptionAct.putExtra("subscription_type", response.body().getMessage_key());
                            callSubscriptionAct.putExtras(bundle);
                            startActivity(callSubscriptionAct);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<POSTSubscriptionModel> call, Throwable t) {

            }
        });
    }

    private void setPriceValues(Response<POSTSubscriptionModel> response) {
        try {
            SessionHandler.getInstance().save(getActivity(), AppConstants.SellGigsPriceOption, String.valueOf(response.body().getData().getPrice_option()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (response.body().getData().getPrice_option().equalsIgnoreCase("fixed")) {
            price_type = "0";
            gigs_fast_price = response.body().getData().getExtra_gig_price();
            gigsCosts.setText(response.body().getData().getGig_price());
            gigsCosts.setClickable(false);
            gigsCosts.setFocusable(false);
            extra_gigs_price = response.body().getData().getExtra_gig_price();
            /*gigsFastextrasCost.setText(gigs_fast_price);
            gigsFastextrasCost.setClickable(false);
            gigsFastextrasCost.setFocusable(false);*/
        } else {
            price_type = "1";
        }
    }


    public void getPriceDetails() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.getPriceDetails(SessionHandler.getInstance().get(getActivity(), AppConstants.TOKEN_ID), SessionHandler.getInstance().get(getActivity(), AppConstants.Language)).enqueue(new Callback<GETPriceDetails>() {
            @Override
            public void onResponse(Call<GETPriceDetails> call, Response<GETPriceDetails> response) {
                if (response.body().getCode().equals(200)) {
                    try {
                        SessionHandler.getInstance().save(getActivity(), AppConstants.SellGigsPriceOption, String.valueOf(response.body().getData().getPrice_option()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (response.body().getData().getPrice_option().equalsIgnoreCase("fixed")) {
                        price_type = "0";
                        gigs_fast_price = response.body().getData().getExtra_gig_price();
                        gigsCosts.setText(response.body().getData().getGig_price());
                        gigsCosts.setClickable(false);
                        gigsCosts.setFocusable(false);
                        extra_gigs_price = response.body().getData().getExtra_gig_price();
                        /*gigsFastextrasCost.setText(gigs_fast_price);
                        gigsFastextrasCost.setClickable(false);
                        gigsFastextrasCost.setFocusable(false);*/
                    } else {
                        price_type = "1";
                    }
                } /*else {
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }*/
            }

            @Override
            public void onFailure(Call<GETPriceDetails> call, Throwable t) {
            }
        });
    }

   /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        // handle result of CropImageActivity
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == Activity.RESULT_OK) {
                Bitmap bm = null;
                try {
                    bm = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), result.getUri());
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
                Toast.makeText(getActivity(), "Cropping failed: " + result.getError(), Toast.LENGTH_LONG).show();
            }
        }
    }*/


    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    private void galleryIntent() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, SELECT_FILE);
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

    private void selectImage() {
        final CharSequence[] items = {commonStrings.getTxt_take_photo().getName(), commonStrings.getTxt_choose_from_gallery().getName(),
                commonStrings.getLbl_cancel().getName()};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(commonStrings.getLbl_add_photo().getName());
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utils.checkPermission(getActivity());
                if (items[item].equals(getResources().getString(R.string.txt_take_photo))) {
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
            thumbnail = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
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

    private void getTermsandConditions() {
        mCustomProgressDialog.showDialog();
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.getTermsandConditions(SessionHandler.getInstance().get(getActivity(), AppConstants.TOKEN_ID), SessionHandler.getInstance().get(getActivity(), AppConstants.Language)).enqueue(new Callback<GETTermsAndConditions>() {
            @Override
            public void onResponse(Call<GETTermsAndConditions> call, Response<GETTermsAndConditions> response) {
                if (response.body().getCode().equals(200)) {
                    mCustomProgressDialog.dismiss();
                    showTermsAndConditions(response.body().getData().getGigs_terms_and_conditions());
                } else {
                    mCustomProgressDialog.dismiss();
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GETTermsAndConditions> call, Throwable t) {
                mCustomProgressDialog.dismiss();
            }
        });
    }

    public void showTermsAndConditions(String user_terms_and_conditions) {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        dialog.setContentView(R.layout.show_termsandconditions_dialog);
        // set the custom dialog components - text, image and button
        final TextView inputTermsandConditions = (TextView) dialog.findViewById(R.id.tv_termsandConditions);
        inputTermsandConditions.setText(Html.fromHtml(user_terms_and_conditions));
        dialog.show();
        //dialog.getWindow().setLayout((int) (width * 0.96f), (int) (height * 0.8f));
    }

    private void getCategoryList() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.getCategories(SessionHandler.getInstance().get(getActivity(), AppConstants.TOKEN_ID), SessionHandler.getInstance().get(getActivity(), AppConstants.Language)).enqueue(new Callback<GETCategory>() {
            @Override
            public void onResponse(Call<GETCategory> call, final Response<GETCategory> response) {

                if (response.isSuccessful()) {
                    if (response.body().getCode().equals(200)) {
                        if (response.body().getPrimary().size() > 0) {
                            categoryArray.add(sell_gigs_screen.getLbl_select_category().getName());
                            for (int i = 0; i < response.body().getPrimary().size(); i++) {
                                if (!categoryArray.contains(response.body().getPrimary().get(i).getName())) {
                                    categoryArray.add(response.body().getPrimary().get(i).getName());
                                }
                            }
                            try {
                                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                                        android.R.layout.simple_spinner_item, categoryArray);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinCategory.setAdapter(adapter);

                                spinCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        spinSubCategory.clearFocus();
                                        if (position == 0) {
                                            spinSubCategory.setVisibility(View.GONE);
                                            category_id = "";
                                            sSubCategoryId = "";
                                        } else {
                                            spinSubCategory.setVisibility(View.VISIBLE);
                                            if (categoryArray.get(position).equalsIgnoreCase(response.body().getPrimary().get(position - 1).getName())) {
                                                category_id = response.body().getPrimary().get(position - 1).getCid();
                                                sSubCategoryId = "";
                                                if (!category_id.isEmpty()) {
                                                    if (!category_id.isEmpty()) {
                                                        postCategoryDetails.put("category_id", category_id);
                                                    }
                                                    if (SessionHandler.getInstance().get(getActivity(), AppConstants.TOKEN_ID) != null) {
                                                        //postCategoryDetails.put("user_id", SessionHandler.getInstance().get(getActivity(), AppConstants.TOKEN_ID));
                                                    }
                                                    postCategoryDetails.put("services", "All");
                                                    if (response.body().getPrimary().get(position - 1).getSubcategory().equalsIgnoreCase("1")) {
                                                        spinSubCategory.setVisibility(View.VISIBLE);
                                                        if (subCategoryArray.size() > 0) {
                                                            subCategoryArray.clear();
                                                        }
                                                        getSubCategory();
                                                    } else {
                                                        spinSubCategory.setVisibility(View.GONE);
                                                    }
                                                }
                                            }
                                        }
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {

                                    }
                                });
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            Utils.toastMessage(getActivity(), response.body().getMessage());
                        }
                    } else if (response.body().getCode().equals(Integer.parseInt(AppConstants.INVALID_RESPONSE_CODE))) {
                        NetworkAlertDialog.networkAlertDialog(getActivity(), "", response.body().getMessage(), null, null);
                    } else if (response.body().getCode().equals(AppConstants.InactiveStatusResponse)) {
                        Utils.createUserInActiceAlert(getActivity(), response.body().getMessage());
                    } else {
                        Utils.toastMessage(getActivity(), response.body().getMessage());
                    }
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

    private void getSubCategory() {

        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.getSubCategory(postCategoryDetails, SessionHandler.getInstance().get(getActivity(), AppConstants.TOKEN_ID), SessionHandler.getInstance().get(getActivity(), AppConstants.Language)).enqueue(new Callback<POSTSubCategory>() {
            @Override
            public void onResponse(Call<POSTSubCategory> call, final Response<POSTSubCategory> response) {
                if (response.body().getCode().equals(200)) {
                    if (response.body().getData().size() > 0) {
                        subCategoryArray.add(sell_gigs_screen.getLbl_sub_category().getName());
                        for (int i = 0; i < response.body().getData().size(); i++) {
                            if (!subCategoryArray.contains(response.body().getData().get(i).getName())) {
                                subCategoryArray.add(response.body().getData().get(i).getName());
                            }
                        }
                        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                                android.R.layout.simple_spinner_item, subCategoryArray);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinSubCategory.setAdapter(adapter);
                        spinSubCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                spinSubCategory.clearFocus();
                                try {
                                    if (position != 0) {
                                        if (subCategoryArray.get(position).equalsIgnoreCase(response.body().getData().get(position - 1).getName())) {
                                            sSubCategoryId = response.body().getData().get(position - 1).getCid();
                                        }
                                    } else {
                                        sSubCategoryId = "";
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                    } else {
                        Utils.toastMessage(getActivity(), response.body().getMessage());
                    }
                } else {
                    Utils.toastMessage(getActivity(), response.body().getMessage());
                }
//                mCustomProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<POSTSubCategory> call, Throwable t) {
                Log.i("TAG", t.getMessage());
                mCustomProgressDialog.dismiss();
            }
        });
    }

    private void creategigs() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.createGigs(title, delivering_time, gig_price, gig_tags, gig_details, super_fast_delivery, super_fast_delivery_desc, super_fast_delivery_date,
                super_fast_charges, requirements, work_option, terms_conditions, extra_gigs, time_zone, category_ids, price_option, body, SessionHandler.getInstance().get(getActivity(), AppConstants.TOKEN_ID), SessionHandler.getInstance().get(getActivity(), AppConstants.Language))
                .enqueue(new Callback<POSTCreategigs>() {
                    @Override
                    public void onResponse(Call<POSTCreategigs> call, Response<POSTCreategigs> response) {
                        if (response.body().getCode().equals(200)) {
                            Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            mHandler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    ((MainActivity) getActivity()).callHome();
                                }
                            }, 3000);
                        } else if (response.body().getCode().equals(Integer.parseInt(AppConstants.INVALID_RESPONSE_CODE))) {
                            NetworkAlertDialog.networkAlertDialog(getActivity(), "", response.body().getMessage(), null, null);
                        } else {
                            Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        mCustomProgressDialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<POSTCreategigs> call, Throwable t) {
                        Log.i("TAG", t.getMessage());
                        mCustomProgressDialog.dismiss();
                        if (t instanceof UnknownHostException || t instanceof HttpException || t instanceof ConnectException || t instanceof SocketTimeoutException || t instanceof SocketException || t instanceof IOException) {
                            NetworkAlertDialog.networkAlertDialog(getActivity(), commonStrings.getLbl_network_err().getName(),
                                    commonStrings.getLbl_server_err().getName(), creategigsRunn, null);
                        }
                    }
                });
    }

    Runnable creategigsRunn = new Runnable() {
        @Override
        public void run() {
            //TODO: API call
            creategigs();
        }
    };

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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void askPermissions() {
        Dexter.withActivity(getActivity())
                .withPermissions(Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {

                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
//                        Toast.makeText(Splashscreen.this, "check permission1", Toast.LENGTH_SHORT).show();
//                        askPermissions();
                        token.continuePermissionRequest();
                    }
                })
                .onSameThread().check();
    }


    public void setLanguageLocale() {
        commonStrings = new Gson().fromJson(SessionHandler.getInstance().get(getActivity(), AppConstants.COMMONSTRINGS), POSTLanguageModel.Common_strings.class);
        sell_gigs_screen = new Gson().fromJson(SessionHandler.getInstance().get(getActivity(), AppConstants.SELLGIGS), POSTLanguageModel.Sell_gigs_screen.class);
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
        mCheckBox.setText(sell_gigs_screen.getLbl_terms_condition().getName());
        createGigs.setText(sell_gigs_screen.getBtn_create_gig().getName());
    }


}
