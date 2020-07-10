package com.example.co.squeeg.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.co.squeeg.MainActivity;
import com.example.co.squeeg.Model.GETCategory;
import com.example.co.squeeg.Model.POSTCreategigs;
import com.example.co.squeeg.Model.POSTLanguageModel;
import com.example.co.squeeg.Model.POSTSubCategory;
import com.example.co.squeeg.Model.POSTSubscriptionModel;
import com.example.co.squeeg.R;
import com.example.co.squeeg.network.ApiClient;
import com.example.co.squeeg.network.ApiInterface;
import com.example.co.squeeg.receiver.NetworkChangeReceiver;
import com.example.co.squeeg.utils.AppConstants;
import com.example.co.squeeg.utils.CustomProgressDialog;
import com.example.co.squeeg.utils.NetworkAlertDialog;
import com.example.co.squeeg.utils.SessionHandler;
import com.example.co.squeeg.utils.Utils;
import com.google.gson.Gson;

import org.apache.http.HttpException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuyerRequestFragment extends Fragment {

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private View inflateGigsLayouts;
    private Context mContext;

    private String[] arrayDeliveryDays;

    public POSTLanguageModel.Common_strings commonStrings = new POSTLanguageModel().new Common_strings();
    public POSTLanguageModel.Sell_gigs_screen sell_gigs_screen = new POSTLanguageModel().new Sell_gigs_screen();
    private ArrayList<POSTSubscriptionModel.Subscription> subscriptions = new ArrayList<>();

    private HashMap<String, String> postCategoryDetails = new HashMap<String, String>();

    ArrayList<String> subCategoryArray = new ArrayList<String>();
    ArrayList<String> categoryArray = new ArrayList<String>();

    Handler mHandler;

    private LinearLayout llSubCategory;
    private CustomProgressDialog mCustomProgressDialog;

    private EditText etDescription,
            etBudget;
    private Spinner spCategory,
            spSubCategory;
    private TextView tvDeliveryDays;
    private Button btnSubmit;

    private String sDescription = null,
            sDeliveryDays = null,
            sBudget = null,
            sCategoryId = "",
            sSubCategoryId = "";

    private RequestBody USERID;
    RequestBody description;
    RequestBody category_id;
    RequestBody delivery_time;
    RequestBody budget;
    RequestBody date;
    RequestBody time;

    public BuyerRequestFragment() {
        // Required empty public constructor
    }

    public static BuyerRequestFragment newInstance(String param1, String param2) {
        BuyerRequestFragment fragment = new BuyerRequestFragment();
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
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        inflateGigsLayouts = inflater.inflate(R.layout.buyer_request_fragment, container, false);
        mContext = getActivity();
        mCustomProgressDialog = new CustomProgressDialog(mContext);

        arrayDeliveryDays = getResources().getStringArray(R.array.days);
        mHandler = new Handler();
        initView();

        setLanguageLocale();

        if (NetworkChangeReceiver.isConnected()) {
            mCustomProgressDialog.showDialog();
            getCategoryList();
        } else {
            Utils.toastMessage(getActivity(), commonStrings.getLbl_enable_internet().getName());
        }

        tvDeliveryDays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showStateChoiceDialog((TextView) view);
            }
        });

        if (tvDeliveryDays.getText().toString().trim().isEmpty())
            handleFastExtrasFocus(false);
        else
            handleFastExtrasFocus(true);

        etDescription.addTextChangedListener(new TextWatcher() {

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

                if (!s.toString().isEmpty()) {
                    if (s.length() != 0) {
                        handleFastExtrasFocus(true);
                    } else {
                        handleFastExtrasFocus(false);
                    }
                } else {
                    handleFastExtrasFocus(false);
                }
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                submitRequest();
            }
        });

        return inflateGigsLayouts;
    }

    private void submitRequest() {

        getDateFromView();

        if (validateRequest())
            return;

        if (NetworkChangeReceiver.isConnected()) {
            mCustomProgressDialog.showDialog();
            putRequestData();
        } else {
            Utils.toastMessage(getActivity(), commonStrings.getLbl_enable_internet().getName());
        }
    }

    private void putRequestData() {
        USERID = RequestBody.create(MediaType.parse("text/plain"), SessionHandler.getInstance().get(getActivity(), AppConstants.TOKEN_ID));
        description = RequestBody.create(MediaType.parse("text/plain"), sDescription);
        if (llSubCategory.getVisibility() != View.VISIBLE)
            category_id = RequestBody.create(MediaType.parse("text/plain"), sCategoryId);
        else
            category_id = RequestBody.create(MediaType.parse("text/plain"), sSubCategoryId);

        delivery_time = RequestBody.create(MediaType.parse("text/plain"), sDeliveryDays);
        budget = RequestBody.create(MediaType.parse("text/plain"), sBudget);
        date = RequestBody.create(MediaType.parse("text/plain"), Utils.getCurrentDate());
        time = RequestBody.create(MediaType.parse("text/plain"), Utils.getCurrentTimeUsingDate());

        createRequest();
    }

    private void createRequest() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.createBuyerRequest(USERID, description, category_id, delivery_time, budget, date, time,
                SessionHandler.getInstance().get(getActivity(), AppConstants.TOKEN_ID),
                SessionHandler.getInstance().get(getActivity(), AppConstants.Language))
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
            createRequest();
        }
    };

    private boolean validateRequest() {

        if (sDescription.isEmpty() || sDescription.length() > 300) {
            Utils.toastMessage(mContext, "Enter description Max 300 Chars");
            return true;
        }

        if (sCategoryId.isEmpty()) {
            Utils.toastMessage(mContext, "Select Category");
            return true;
        }

        if (llSubCategory.getVisibility() == View.VISIBLE && sSubCategoryId.isEmpty()) {
            sSubCategoryId = "";
            Utils.toastMessage(mContext, "Select Sub Category");
            return true;
        }

        if (sDeliveryDays.isEmpty()) {
            Utils.toastMessage(mContext, "Select Delivery Time");
            return true;
        }

        if (sBudget.isEmpty() || Float.parseFloat(sBudget) < 5.0) {
            Utils.toastMessage(mContext, "Enter Budget Min. $5");
            return true;
        }
        return false;
    }

    private void handleFastExtrasFocus(boolean type) {
        tvDeliveryDays.setFocusable(type);
        tvDeliveryDays.setFocusableInTouchMode(type);
        tvDeliveryDays.setClickable(type);
        etBudget.setFocusable(type);
        etBudget.setFocusableInTouchMode(type);
        etBudget.setClickable(type);
    }

    public void setLanguageLocale() {
        commonStrings = new Gson().fromJson(SessionHandler.getInstance().get(getActivity(), AppConstants.COMMONSTRINGS), POSTLanguageModel.Common_strings.class);
        sell_gigs_screen = new Gson().fromJson(SessionHandler.getInstance().get(getActivity(), AppConstants.SELLGIGS), POSTLanguageModel.Sell_gigs_screen.class);
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

    private void initView() {
        llSubCategory = (LinearLayout) inflateGigsLayouts.findViewById(R.id.ll_sub_category);

        etDescription = (EditText) inflateGigsLayouts.findViewById(R.id.et_description);
        etBudget = (EditText) inflateGigsLayouts.findViewById(R.id.et_budget);
        spCategory = (Spinner) inflateGigsLayouts.findViewById(R.id.s_category);
        spSubCategory = (Spinner) inflateGigsLayouts.findViewById(R.id.s_sub_category);
        tvDeliveryDays = (TextView) inflateGigsLayouts.findViewById(R.id.tv_delivery_days);

        btnSubmit = (Button) inflateGigsLayouts.findViewById(R.id.btn_submit);
    }

    private void getDateFromView() {
        sDescription = etDescription.getText().toString().trim();
        sDeliveryDays = tvDeliveryDays.getText().toString().trim();
        sBudget = etBudget.getText().toString();
    }

    private void getCategoryList() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.getCategories(SessionHandler.getInstance().get(getActivity(), AppConstants.TOKEN_ID), SessionHandler.getInstance().get(getActivity(), AppConstants.Language)).enqueue(new Callback<GETCategory>() {
            @Override
            public void onResponse(Call<GETCategory> call, final Response<GETCategory> response) {

                if (response.body().getCode().equals(200)) {
                    if (response.body().getPrimary().size() > 0) {
                        categoryArray.add(sell_gigs_screen.getLbl_select_category().getName());
                        for (int i = 0; i < response.body().getPrimary().size(); i++) {
                            if (!categoryArray.contains(response.body().getPrimary().get(i).getName())) {
                                categoryArray.add(response.body().getPrimary().get(i).getName());
                            }
                        }
                        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                                android.R.layout.simple_spinner_item, categoryArray);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spCategory.setAdapter(adapter);

                        spCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                spCategory.clearFocus();
                                if (position == 0) {
                                    llSubCategory.setVisibility(View.GONE);
                                    sCategoryId = "";
                                    sSubCategoryId = "";
                                } else {
                                    llSubCategory.setVisibility(View.VISIBLE);
                                    if (categoryArray.get(position).equalsIgnoreCase(response.body().getPrimary().get(position - 1).getName())) {
                                        sCategoryId = response.body().getPrimary().get(position - 1).getCid();
                                        sSubCategoryId = "";
                                        if (!sCategoryId.isEmpty()) {
                                            if (!sCategoryId.isEmpty()) {
                                                postCategoryDetails.put("category_id", sCategoryId);
                                            }
                                            if (SessionHandler.getInstance().get(getActivity(), AppConstants.TOKEN_ID) != null) {
                                                //postCategoryDetails.put("user_id", SessionHandler.getInstance().get(getActivity(), AppConstants.TOKEN_ID));
                                            }
                                            postCategoryDetails.put("services", "All");
                                            if (response.body().getPrimary().get(position - 1).getSubcategory().equalsIgnoreCase("1")) {
                                                llSubCategory.setVisibility(View.VISIBLE);
                                                if (subCategoryArray.size() > 0) {
                                                    subCategoryArray.clear();
                                                }
                                                mCustomProgressDialog.showDialog();
                                                getSubCategory();
                                            } else {
                                                llSubCategory.setVisibility(View.GONE);
                                            }

                                        }
                                    }
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });
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
                        spSubCategory.setAdapter(adapter);

                        spSubCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                spSubCategory.clearFocus();
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
                mCustomProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<POSTSubCategory> call, Throwable t) {
                Log.i("TAG", t.getMessage());
                mCustomProgressDialog.dismiss();
            }
        });

    }

    private void showStateChoiceDialog(final TextView tv) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setCancelable(true);
        builder.setSingleChoiceItems(arrayDeliveryDays, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                tv.setTextColor(Color.BLACK);
                tv.setText(arrayDeliveryDays[which]);
            }
        });
        builder.show();
    }
}