package com.example.co.squeeg;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.co.squeeg.Model.GETCountry;
import com.example.co.squeeg.Model.GETProfession;
import com.example.co.squeeg.Model.GETState;
import com.example.co.squeeg.Model.POSTLanguageModel;
import com.example.co.squeeg.Model.POSTUpdateProfile;
import com.example.co.squeeg.Model.POSTViewProfile;
import com.example.co.squeeg.network.ApiClient;
import com.example.co.squeeg.network.ApiInterface;
import com.example.co.squeeg.receiver.NetworkChangeReceiver;
import com.example.co.squeeg.utils.AppConstants;
import com.example.co.squeeg.utils.CustomProgressDialog;
import com.example.co.squeeg.utils.MultiSpinner;
import com.example.co.squeeg.utils.NetworkAlertDialog;
import com.example.co.squeeg.utils.SessionHandler;
import com.example.co.squeeg.utils.Utils;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.apache.http.HttpException;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateProfile extends BaseActivity {
    private final AlertDialog alert = null;
    private Spinner spinCountry;
    private Spinner spinState;
    private Spinner spinProfession;
    private MultiSpinner spinSpeaks;
    private Gson gson;
    private GETCountry[] getCountryLists;
    private GETProfession[] getProfession;
    private final List<String> addCountryLists = new ArrayList<String>();
    private final List<String> addProfessionLists = new ArrayList<String>();
    private final List<String> addStateLists = new ArrayList<String>();
    private final List<String> addlanguageLists = new ArrayList<String>();
    private String country = "";
    private String state = "";
    private String profession = "";
    private String languages = "";
    private String state_name = "";
    private EditText editPhone;
    private EditText editName;
    private EditText editAddress;
    private EditText editCity;
    private EditText editZipCode;
    private EditText editSuggestions;
    private EditText editEmail;
    private CustomProgressDialog mCustomProgressDialog;
    private HashMap<String, String> getProfiles = new HashMap<String, String>();
    private int VIEW_TYPE = 0;
    private TextInputLayout inputLayoutPhone, inputLayoutCity, inputLayoutZipcode, inputLayoutAddress, inputLayoutSuggestion, inputLayoutName;
    public static StringBuilder stringBuilder;
    Toolbar mToolbar;
    RequestBody user_contact = null, user_zip = null, user_city = null, user_addr = null, user_desc = null, country_id = null, state_id = null, professions = null, user_name = null, language = null;
    MultipartBody.Part body;
    RequestBody requestFile;
    private CircleImageView ivImage;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    Bitmap thumbnail;
    private String userChoosenTask, user_id = "";
    ByteArrayOutputStream bytes;
    String encodeImage;
    String data = "";
    Handler handler;
    public POSTLanguageModel.Register_screen registerScreen = new POSTLanguageModel().new Register_screen();
    public POSTLanguageModel.Search_gigs_screen searchGigsScreen = new POSTLanguageModel().new Search_gigs_screen();
    public POSTLanguageModel.Common_strings commonStrings = new POSTLanguageModel().new Common_strings();

    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutUsername;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutRPassword;
    private TextInputLayout textInputLayoutZipcode;
    private Button buttonRegister;
    private TextView tvToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);
        gson = new Gson();
        mCustomProgressDialog = new CustomProgressDialog(this);
        Utils.checkCameraPermission(this);
        Utils.freeMemory();
        ButterKnife.bind(this);
        initLayouts();
        setLanguageValues();
        getProfession();


        /*editName.addTextChangedListener(new RegisterTextWatcher(editName));
        editPhone.addTextChangedListener(new RegisterTextWatcher(editPhone));
        editAddress.addTextChangedListener(new RegisterTextWatcher(editAddress));
        editCity.addTextChangedListener(new RegisterTextWatcher(editCity));
        editZipCode.addTextChangedListener(new RegisterTextWatcher(editZipCode));
        editSuggestions.addTextChangedListener(new RegisterTextWatcher(editSuggestions));*/


        try {
            JSONArray obj = new JSONArray(loadJSONFromAsset());
            if (obj.length() > 0) {
                for (int i = 0; i < obj.length(); i++) {
                    addlanguageLists.add(obj.getJSONObject(i).get("language").toString());
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        if (SessionHandler.getInstance().get(UpdateProfile.this, AppConstants.COUNTRY_JSON) != null) {

            getCountryLists = gson.fromJson(SessionHandler.getInstance().get(UpdateProfile.this, AppConstants.COUNTRY_JSON), GETCountry[].class);


            /*if (country == null && country.isEmpty()) {
            }*/

            addCountryLists.add(registerScreen.getLbl_country().getName());
            for (GETCountry getCountryList : getCountryLists) {
                addCountryLists.add(getCountryList.getCountry());
            }


            if (addCountryLists.size() > 0) {
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(UpdateProfile.this,
                        android.R.layout.simple_spinner_item, addCountryLists);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinCountry.setAdapter(adapter);


                spinCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        spinState.clearFocus();
                        /*if (!country.isEmpty() && VIEW_TYPE == 0) {
                            if (position > 0 && getCountryLists.length > 0) {
                                country = getCountryLists[position - 1].getId();
                                getStateAPI();
                            } else if (position == 0) {
                                spinState.setAdapter(new ArrayAdapter<String>(UpdateProfile.this,
                                        android.R.layout.simple_spinner_item, new ArrayList<String>(addStateLists)));
                            }
                            VIEW_TYPE = 1;
                        } else {
                            country = getCountryLists[position].getId();
                            getStateAPI();
                        }*/
                        if (position > 0 && getCountryLists.length > 0) {
                            country = getCountryLists[position - 1].getId();
                            getStateAPI();
                        } else if (position == 0) {
                            addStateLists.clear();
                            addStateLists.add(registerScreen.getLbl_state().getName());
                            spinState.setAdapter(new ArrayAdapter<String>(UpdateProfile.this,
                                    android.R.layout.simple_spinner_item, new ArrayList<String>(addStateLists)));
                            country = "";
                            state = "";
                        }
                        VIEW_TYPE = 1;
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }


        }


        if (addlanguageLists.size() > 0) {
            spinSpeaks.setItems(addlanguageLists, registerScreen.getLbl_select_lang().getName(), new MultiSpinner.MultiSpinnerListener() {
                @Override
                public void onItemsSelected(boolean[] selected) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < selected.length; i++) {
                        if (selected[i]) {
                            languages = sb.append(addlanguageLists.get(i)).append(",").toString();
                            data = "";
                        }
                    }
                }
            });
        }

        if (NetworkChangeReceiver.isConnected()) {
            mCustomProgressDialog.showDialog();
            getProfiles.put("user_id", SessionHandler.getInstance().get(UpdateProfile.this, AppConstants.TOKEN_ID));

            postProfile();
            /*handler = new Handler();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    postProfile();
                }
            }, 3000);*/


        } else {
            showToast();
        }
    }


    private void getProfession() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.getProfession(AppConstants.GUEST_TOKEN, SessionHandler.getInstance().get(this, AppConstants.Language)).enqueue(new Callback<List<GETProfession>>() {
            @Override
            public void onResponse(Call<List<GETProfession>> call, Response<List<GETProfession>> response) {
                if (response.isSuccessful()) {
                    if (response.body().size() > 0) {
                        String json = gson.toJson(response.body());
                        SessionHandler.getInstance().save(UpdateProfile.this, AppConstants.PROFESSION, json);
                        getProfession = gson.fromJson(SessionHandler.getInstance().get(UpdateProfile.this, AppConstants.PROFESSION), GETProfession[].class);
                        if (getProfession != null) {
                            addProfessionLists.add(registerScreen.getLbl_select_category().getName());
                            for (GETProfession getProfessionList : getProfession) {
                                addProfessionLists.add(getProfessionList.getProfession_name());
                            }
                        }
                        if (addProfessionLists.size() > 0) {
                            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(UpdateProfile.this,
                                    android.R.layout.simple_spinner_item, addProfessionLists);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinProfession.setAdapter(adapter);


                            spinProfession.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    if (position == 0) {
                                        profession = "";
                                    } else {
                                        profession = getProfession[position + 1].getId();
                                    }
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });
                        }

                    }
                } else {
                    Log.i("ERROR_PROFESSSION", response.errorBody().toString());
                }

            }

            @Override
            public void onFailure(Call<List<GETProfession>> call, Throwable t) {
                Log.i("TAG", t.getMessage());

            }
        });
    }


    private synchronized void postProfile() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.postViewProfile(getProfiles, SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID), SessionHandler.getInstance().get(this, AppConstants.Language)).enqueue(new Callback<POSTViewProfile>() {
            @Override
            public void onResponse(Call<POSTViewProfile> call, Response<POSTViewProfile> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode().equals(200)) {
                        mCustomProgressDialog.dismiss();
                        editPhone.setText(response.body().getData().get(0).getContact());
                        editAddress.setText(response.body().getData().get(0).getAddress());
                        editSuggestions.setText(response.body().getData().get(0).getDescription());
                        editZipCode.setText(response.body().getData().get(0).getZipcode());
                        editName.setText(response.body().getData().get(0).getFullname());
                        editEmail.setText(response.body().getData().get(0).getEmail());
                        editCity.setText(response.body().getData().get(0).getCity());
                        String profileImage = "";
                        if (!response.body().getData().get(0).getUser_profile_image().isEmpty()) {
                            if (response.body().getData().get(0).getUser_profile_image().contains("https")) {
                                profileImage = response.body().getData().get(0).getUser_profile_image();
                            } else {
                                profileImage = AppConstants.ImageBaseURL + response.body().getData().get(0).getUser_profile_image();
                            }
                        }
                        if (!profileImage.isEmpty()) {
                            Picasso.get().load(profileImage).placeholder(R.drawable.ic_no_image_100).into(ivImage);
                            //ivImage.setImageURI(Uri.parse(AppConstants.ImageBaseURL + response.body().getData().get(0).getUser_profile_image()));
                            SessionHandler.getInstance().save(UpdateProfile.this, AppConstants.USER_IMAGE, profileImage);
                        }

                        for (int i = 0; i < getCountryLists.length; i++) {
                            if (getCountryLists[i].getId().equalsIgnoreCase(
                                    response.body().getData().get(0).getCountry()
                            )) {
                                spinCountry.setSelection(i + 1);
                                country = getCountryLists[i + 1].getId();
                            }
                        }

                        state = response.body().getData().get(0).getState();
                        state_name = response.body().getData().get(0).getState_name();
                        stringBuilder = new StringBuilder();
                        if (stringBuilder.length() < 0) {
                            languages = "";
                        } else {
                            data = response.body().getData().get(0).getLang_speaks();

                            if (!data.isEmpty()) {
                                String[] items = data.split(",");
                                for (int i = 0; i < items.length; i++) {
                                    stringBuilder.append(items[i]);
                                    languages = stringBuilder.toString();
                                    if (i < items.length - 1)
                                        stringBuilder.append(",").toString();
                                }

                                spinSpeaks.setUpdateItems(addlanguageLists, stringBuilder.toString(), new MultiSpinner.MultiSpinnerListener() {
                                    @Override
                                    public void onItemsSelected(boolean[] selected) {
                                        StringBuilder sb = new StringBuilder();
                                        for (int i = 0; i < selected.length; i++) {
                                            if (selected[i]) {
                                                languages = sb.append(addlanguageLists.get(i)).append(",").toString();
                                                data = "";
                                            } else {
                                                if (sb.toString().isEmpty()) {
                                                    languages = "";
                                                }
                                            }
                                        }
                                    }
                                });
                            } else {
                                spinSpeaks.setItems(addlanguageLists, registerScreen.getLbl_select_lang().getName(), new MultiSpinner.MultiSpinnerListener() {
                                    @Override
                                    public void onItemsSelected(boolean[] selected) {
                                        StringBuilder sb = new StringBuilder();
                                        for (int i = 0; i < selected.length; i++) {
                                            if (selected[i]) {
                                                languages = sb.append(addlanguageLists.get(i)).append(",").toString();
                                            }
                                        }
                                    }
                                });
                            }
                        }

                        if (getProfession != null) {
                            for (int i = 0; i < getProfession.length; i++) {
                                if (getProfession[i].getId().equalsIgnoreCase(
                                        response.body().getData().get(0).getProfession()
                                )) {
                                    spinProfession.setSelection(i - 1);
                                    profession = getProfession[i - 1].getId();
                                }
                            }
                        }
                    } else if (response.body().getCode().equals(Integer.parseInt(AppConstants.INVALID_RESPONSE_CODE))) {
//                    mCustomProgressDialog.dismiss();
                        NetworkAlertDialog.networkAlertDialog(UpdateProfile.this, "", response.body().getMessage(), null, null);
                    } else if (response.body().getCode().equals(AppConstants.InactiveStatusResponse)) {
                        Utils.createUserInActiceAlert(UpdateProfile.this, response.body().getMessage());
                    } else {
                        Toast.makeText(UpdateProfile.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    mCustomProgressDialog.dismiss();
                } else {
                    Log.i("ERROR_UPDATE_PROFILE", response.errorBody().toString());
                }
                mCustomProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<POSTViewProfile> call, Throwable t) {
                mCustomProgressDialog.dismiss();
                Log.i("TAG", t.getMessage());
                if (t instanceof UnknownHostException || t instanceof HttpException || t instanceof ConnectException || t instanceof SocketTimeoutException || t instanceof SocketException) {
                    Toast.makeText(UpdateProfile.this, commonStrings.getLbl_server_prblm().getName(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getStateAPI() {
        if (NetworkChangeReceiver.isConnected()) {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            apiInterface.getState(country, AppConstants.GUEST_TOKEN, SessionHandler.getInstance().get(this, AppConstants.Language)).enqueue(new Callback<List<GETState>>() {
                @Override
                public void onResponse(Call<List<GETState>> call, final Response<List<GETState>> response) {
                    if (response.isSuccessful()) {
                        if (response.body().size() > 0) {
                            addStateLists.clear();
                            addStateLists.add(registerScreen.getLbl_state().getName());
                            /*if (country == null && country.isEmpty()) {

                            }*/

                            for (GETState getState : response.body()) {
                                addStateLists.add(getState.getState_name());
                            }

                            if (addStateLists.size() > 0 && response.body().size() > 0) {
                                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(UpdateProfile.this,
                                        android.R.layout.simple_spinner_item, addStateLists);
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinState.setAdapter(adapter);

                                if (state_name != null && state_name.length() > 0) {
                                    for (int i = 0; i < addStateLists.size(); i++) {
                                        if (state_name.equalsIgnoreCase(addStateLists.get(i))) {
                                            spinState.setSelection(i);
                                        }
                                    }
                                }

                                spinState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        if (position > 0 && addStateLists.size() > 0) {
                                            state = response.body().get(position - 1).getState_id();
                                        } else if (position == 0) {
                                            state = "";
                                        }
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {


                                    }
                                });


                            }


                        }
                    }

                }

                @Override
                public void onFailure(Call<List<GETState>> call, Throwable t) {
                    Log.i("TAG", t.getMessage());
                    mCustomProgressDialog.dismiss();
                }
            });
        } else {
            Toast.makeText(this, commonStrings.getLbl_enable_internet().getName(), Toast.LENGTH_SHORT).show();
        }


    }


    private void initLayouts() {

        mToolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        setSupportActionBar(mToolbar);
        buttonRegister = (Button) findViewById(R.id.button_register);
        textInputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
//        textInputLayoutUsername = (TextInputLayout) findViewById(R.id.input_layout_username);
//        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
//        textInputLayoutRPassword = (TextInputLayout) findViewById(R.id.input_layout_rpassword);
        spinCountry = (Spinner) findViewById(R.id.spinner_counrty);
        spinState = (Spinner) findViewById(R.id.spinner_state);
        spinProfession = (Spinner) findViewById(R.id.spinner_profession);
        spinSpeaks = (MultiSpinner) findViewById(R.id.spinner_speaks);

        editPhone = (EditText) findViewById(R.id.input_phone);
        editAddress = (EditText) findViewById(R.id.input_address_line);
        editCity = (EditText) findViewById(R.id.input_city);
        editSuggestions = (EditText) findViewById(R.id.input_suggestion_about_you);
        editZipCode = (EditText) findViewById(R.id.input_zip_code);
        editName = (EditText) findViewById(R.id.input_name);
        editEmail = (EditText) findViewById(R.id.input_email);

        inputLayoutPhone = (TextInputLayout) findViewById(R.id.input_layout_phno);
        inputLayoutCity = (TextInputLayout) findViewById(R.id.input_layout_city);
        inputLayoutZipcode = (TextInputLayout) findViewById(R.id.input_layout_zip_code);
        inputLayoutAddress = (TextInputLayout) findViewById(R.id.input_layout_address_line);
        inputLayoutSuggestion = (TextInputLayout) findViewById(R.id.input_layout_suggestion_about_you);
        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name);
        ivImage = (CircleImageView) findViewById(R.id.input_profile_picture);
        tvToolbar = (TextView) findViewById(R.id.tv_toolbar);

        editAddress.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View view, MotionEvent event) {

                if (view.getId() == R.id.input_address_line) {
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
        editSuggestions.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View view, MotionEvent event) {

                if (view.getId() == R.id.input_suggestion_about_you) {
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

    private String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("language.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void UpdatePicture(View view) {
        selectImage();
       /* CropImage.activity(null)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setScaleType(CropImageView.ScaleType.CENTER_CROP)
                .setActivityTitle("Select Image")
                .setAspectRatio(2, 2)
                .setFixAspectRatio(true)
                .setAutoZoomEnabled(false)
                .setMaxCropResultSize(200, 200)
                .setCropShape(CropImageView.CropShape.OVAL)
                .start(UpdateProfile.this);*/

    }

    private boolean validateName() {
        if (editName.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError(registerScreen.getTxt_fld_fullname().getValidation1());
            requestFocus(editName);
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    }


    private boolean validateCity() {
        if (editCity.getText().toString().trim().isEmpty()) {
            inputLayoutCity.setError(registerScreen.getTxt_fld_city().getValidation1());
            requestFocus(editCity);
            return false;
        } else if (!editCity.getText().toString().matches(AppConstants.cityMatch)) {
            inputLayoutCity.setError(registerScreen.getTxt_fld_city().getValidation2());
            requestFocus(editCity);
            return false;
        } else {
            inputLayoutCity.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePhone() {
        if (editPhone.getText().toString().trim().isEmpty()) {
            inputLayoutPhone.setError(registerScreen.getTxt_fld_ph_num().getValidation1());
            requestFocus(editPhone);
            return false;
        } else {
            inputLayoutPhone.setErrorEnabled(false);
        }

        return true;
    }


    private boolean validateZipCode() {
        if (editZipCode.getText().toString().trim().isEmpty()) {
            inputLayoutZipcode.setError(registerScreen.getLbl_fld_zipcode().getValidation1());
            requestFocus(editZipCode);
            return false;
        } else {
            inputLayoutZipcode.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateAddress() {
        if (editAddress.getText().toString().trim().isEmpty()) {
            inputLayoutAddress.setError(registerScreen.getTxt_fld_address_line().getValidation1());
            requestFocus(editAddress);
            return false;
        } else {
            inputLayoutAddress.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateSuggestion() {
        if (editSuggestions.getText().toString().trim().isEmpty()) {
            inputLayoutSuggestion.setError(registerScreen.getTxt_fld_suggestions().getValidation1());
            requestFocus(editSuggestions);
            return false;
        } else {
            inputLayoutSuggestion.setErrorEnabled(false);
        }

        return true;
    }

    public void upadteProfile(View view) {

        if (editName.getText().toString().isEmpty()) {
            editName.setError(registerScreen.getTxt_fld_fullname().getValidation1());
            requestFocus(editName);
        }   /*else if (!validatePhone()) {
            return;
        } else if (!validateAddress()) {
            return;
        } *//*else if (languages.isEmpty()) {
            Toast.makeText(this, "Please Choose Language", Toast.LENGTH_SHORT).show();
            return;
        } *//* else if (country.isEmpty()) {
            Toast.makeText(this, "Please Choose Country", Toast.LENGTH_SHORT).show();
            return;
        } else if (state.isEmpty()) {
            Toast.makeText(this, "Please Choose State", Toast.LENGTH_SHORT).show();
            return;
        } else if (!validateCity()) {
            return;
        } else if (!validateZipCode()) {
            return;
        } else if (!validateSuggestion()) {
            return;
        } */ else {
            mCustomProgressDialog.showDialog();
            //user_id = RequestBody.create(MediaType.parse("text/plain"), SessionHandler.getInstance().get(UpdateProfile.this, AppConstants.TOKEN_ID));
            user_contact = RequestBody.create(MediaType.parse("text/plain"), editPhone.getText().toString());
            user_zip = RequestBody.create(MediaType.parse("text/plain"), editZipCode.getText().toString());
            user_city = RequestBody.create(MediaType.parse("text/plain"), editCity.getText().toString());
            user_addr = RequestBody.create(MediaType.parse("text/plain"), editAddress.getText().toString());
            user_desc = RequestBody.create(MediaType.parse("text/plain"), editSuggestions.getText().toString());
            country_id = RequestBody.create(MediaType.parse("text/plain"), country);
            state_id = RequestBody.create(MediaType.parse("text/plain"), state);
            professions = RequestBody.create(MediaType.parse("text/plain"), profession);
            user_name = RequestBody.create(MediaType.parse("text/plain"), editName.getText().toString());
            language = RequestBody.create(MediaType.parse("text/plain"), languages);
           /* if (!languages.isEmpty()) {
                language = RequestBody.create(MediaType.parse("text/plain"), languages);
            } else {
                language = RequestBody.create(MediaType.parse("text/plain"), data);
            }*/
            postUpdateProfile();
        }
    }

    private void postUpdateProfile() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.postUpdateProfile(user_contact, user_zip, user_city, user_addr, user_desc, country_id, state_id, professions, user_name, language, body, SessionHandler.getInstance().get(UpdateProfile.this, AppConstants.TOKEN_ID), SessionHandler.getInstance().get(this, AppConstants.Language))
                .enqueue(new Callback<POSTUpdateProfile>() {
                    @Override
                    public void onResponse(Call<POSTUpdateProfile> call, Response<POSTUpdateProfile> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getCode().equals(200)) {
                                Toast.makeText(UpdateProfile.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                POSTUpdateProfile.Data data = response.body().getData();
                                SessionHandler.getInstance().save(UpdateProfile.this, AppConstants.USER_NAME, data.getUsername());
                                SessionHandler.getInstance().save(UpdateProfile.this, AppConstants.EMAIL_ID, data.getEmail());
                                SessionHandler.getInstance().save(UpdateProfile.this, AppConstants.USER_IMAGE, data.getUserProfileImage());
                                finish();
                            } else if (response.body().getCode().equals(AppConstants.InactiveStatusResponse)) {
                                Utils.createUserInActiceAlert(UpdateProfile.this, response.body().getMessage());
                            } else {
                                Toast.makeText(UpdateProfile.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Log.i("ERROR_UPDATE_PROFILE", response.errorBody().toString());
                        }
                        mCustomProgressDialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<POSTUpdateProfile> call, Throwable t) {
                        Log.i("TAG", t.getMessage());
                        mCustomProgressDialog.dismiss();
                        if (t instanceof UnknownHostException || t instanceof HttpException || t instanceof ConnectException || t instanceof SocketTimeoutException || t instanceof SocketException || t instanceof IOException) {
                            Toast.makeText(UpdateProfile.this, commonStrings.getLbl_server_prblm().getName(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void showToast() {
        Toast.makeText(this, commonStrings.getLbl_enable_internet().getName(), Toast.LENGTH_SHORT).show();
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

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


    private void selectImage() {
        final CharSequence[] items = {commonStrings.getTxt_take_photo().getName(), commonStrings.getTxt_choose_from_gallery().getName(),
                commonStrings.getLbl_cancel().getName()};
        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateProfile.this);
        builder.setTitle(commonStrings.getLbl_add_photo().getName());
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                boolean result = Utils.checkPermission(UpdateProfile.this);
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
            thumbnail = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
        } catch (IOException e) {
            e.printStackTrace();
        }
        bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        Uri selectedImage = data.getData();

        encodeImage = Base64.encodeToString(bytes.toByteArray(), Base64.DEFAULT);

        requestFile = RequestBody.create(MediaType.parse("image/jpeg"), bytes.toByteArray());
        body = MultipartBody.Part.createFormData("profile_img", "image.jpg", requestFile);
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
        body = MultipartBody.Part.createFormData("profile_img", "image.jpg", requestFile);
        ivImage.setImageBitmap(thumbnail);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE) {
                onSelectFromGalleryResult(data);
            } else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private class RegisterTextWatcher implements TextWatcher {
        private View view;

        private RegisterTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (view.getId() == R.id.input_name) {
                if (!editName.getText().toString().isEmpty())
                    validateName();
            } else if (view.getId() == R.id.input_phone) {
                if (!editPhone.getText().toString().isEmpty())
                    validatePhone();
            } else if (view.getId() == R.id.input_address_line) {
                if (!editAddress.getText().toString().isEmpty())
                    validateAddress();
            } else if (view.getId() == R.id.input_city) {
                if (!editCity.getText().toString().isEmpty())
                    validateCity();
            } else if (view.getId() == R.id.input_zip_code) {
                if (!editZipCode.getText().toString().isEmpty())
                    validateZipCode();
            } else if (view.getId() == R.id.input_suggestion_about_you) {
                if (!editSuggestions.getText().toString().isEmpty())
                    validateSuggestion();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    public void setLanguageValues() {
        commonStrings = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.COMMONSTRINGS), POSTLanguageModel.Common_strings.class);
        searchGigsScreen = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.SEARCHGIGS), POSTLanguageModel.Search_gigs_screen.class);
        registerScreen = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.REGISTER), POSTLanguageModel.Register_screen.class);
        inputLayoutName.setHint(registerScreen.getTxt_fld_fullname().getName());
        textInputLayoutEmail.setHint(registerScreen.getTxt_fld_email().getName());
        inputLayoutPhone.setHint(registerScreen.getTxt_fld_ph_num().getName());
        inputLayoutAddress.setHint(registerScreen.getTxt_fld_address_line().getName());
        inputLayoutCity.setHint(registerScreen.getTxt_fld_city().getName());
//        textInputLayoutUsername.setHint(registerScreen.getTxt_fld_username().getName());
        inputLayoutZipcode.setHint(registerScreen.getLbl_fld_zipcode().getName());
        buttonRegister.setHint(registerScreen.getBtn_submit().getName());
        mToolbar.setTitle(registerScreen.getBtn_register().getName());
        //loginInfo.setText(registerScreen.getLbl_already_mem().getName());
        tvToolbar.setText(registerScreen.getLbl_update_profile().getName());
    }
}
