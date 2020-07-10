package com.example.co.squeeg;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.co.squeeg.Model.GETCountry;
import com.example.co.squeeg.Model.GETState;
import com.example.co.squeeg.Model.GETTermsAndConditions;
import com.example.co.squeeg.Model.POSTLanguageModel;
import com.example.co.squeeg.Model.POSTRegister;
import com.example.co.squeeg.network.ApiClient;
import com.example.co.squeeg.network.ApiInterface;
import com.example.co.squeeg.receiver.NetworkChangeReceiver;
import com.example.co.squeeg.utils.AppConstants;
import com.example.co.squeeg.utils.CustomProgressDialog;
import com.example.co.squeeg.utils.CustomTextView;
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
import java.io.FileOutputStream;
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
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterScreen extends BaseActivity {

    @BindView(R.id.login_info)
    CustomTextView loginInfo;
    @BindView(R.id.textt_login)
    CustomTextView texttLogin;
    @BindView(R.id.ctv_terms_conditions)
    CustomTextView ctvTermsConditions;
    @BindView(R.id.tb_register)
    CustomTextView tbRegister;
    private Button buttonRegister;
    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutUsername;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutRPassword;
    private EditText editName;
    private EditText editEmail;
    private EditText editUserName;
    private EditText editPassword;
    private EditText editRPassword;
    private Spinner spinCountry;
    private Spinner spinState;
    private TextView tvCountry, tvState;
    private String country = "";
    private String state = "";
    private Gson gson;
    private final List<String> addCountryLists = new ArrayList<String>();
    private final List<String> addStateLists = new ArrayList<String>();
    private List<GETState> stateData;
    private GETCountry[] getCountryLists;
    private final HashMap<String, String> registerData = new HashMap<String, String>();
    private CustomProgressDialog mCustomProgressDialog;
    private Toolbar mToolbar;
    CheckBox mCheckBox;
    private int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    Bitmap thumbnail;
    private String userChoosenTask;
    FileOutputStream fo;
    ByteArrayOutputStream bytes;
    String encodeImage;
    MultipartBody.Part body;
    RequestBody requestFile;
    private ImageView ivImage;
    boolean result;
    String termsandconditions;
    RequestBody email = null, username = null, password = null, states = null, countries = null, fullname = null, user_timezone = null, user_image = null;
    public POSTLanguageModel.Register_screen registerScreen = new POSTLanguageModel().new Register_screen();
    public POSTLanguageModel.Search_gigs_screen searchGigsScreen = new POSTLanguageModel().new Search_gigs_screen();
    public POSTLanguageModel.Common_strings commonStrings = new POSTLanguageModel().new Common_strings();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        Utils.freeMemory();
        gson = new Gson();
        mCustomProgressDialog = new CustomProgressDialog(this);
        mToolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        setSupportActionBar(mToolbar);
        initLayouts();
        int currentAPIVersion = Build.VERSION.SDK_INT;
//        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
//            if (checkSelfPermission(Manifest.permission.CAMERA)
//                    != PackageManager.PERMISSION_GRANTED) {
//
//                requestPermissions(new String[]{Manifest.permission.CAMERA},
//                        REQUEST_CAMERA);
//            }
//        }

        askPermissions();

        editName.addTextChangedListener(new RegisterTextWatcher(editName));
        editEmail.addTextChangedListener(new RegisterTextWatcher(editEmail));
        editUserName.addTextChangedListener(new RegisterTextWatcher(editUserName));
        editPassword.addTextChangedListener(new RegisterTextWatcher(editPassword));
        editRPassword.addTextChangedListener(new RegisterTextWatcher(editRPassword));

        if (SessionHandler.getInstance().get(RegisterScreen.this, AppConstants.COUNTRY_JSON) != null) {

            getCountryLists = gson.fromJson(SessionHandler.getInstance().get(RegisterScreen.this, AppConstants.COUNTRY_JSON), GETCountry[].class);
            addCountryLists.add(searchGigsScreen.getLbl_select_country().getName());
            addStateLists.add(searchGigsScreen.getLbl_select_state().getName());
            for (GETCountry getCountryList : getCountryLists) {
                addCountryLists.add(getCountryList.getCountry());
            }
            if (addCountryLists.size() > 0) {
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegisterScreen.this,
                        android.R.layout.simple_spinner_item, addCountryLists);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinCountry.setAdapter(adapter);

                spinCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        editName.clearFocus();
                        editEmail.clearFocus();
                        editUserName.clearFocus();
                        editPassword.clearFocus();
                        editRPassword.clearFocus();
                        spinState.clearFocus();

                        if (position > 0 && getCountryLists.length > 0) {
                            country = getCountryLists[position - 1].getId();
                            if (!country.isEmpty())
                                getStateAPI();
                        } else if (position == 0) {
                            spinState.setAdapter(new ArrayAdapter<String>(RegisterScreen.this,
                                    android.R.layout.simple_spinner_item, new ArrayList<String>(addStateLists)));
                            country = "";
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

    private void getTermsandConditions() {
        mCustomProgressDialog.showDialog();
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.getTermsandConditions(AppConstants.GUEST_TOKEN, SessionHandler.getInstance().get(this, AppConstants.Language)).enqueue(new Callback<GETTermsAndConditions>() {
            @Override
            public void onResponse(Call<GETTermsAndConditions> call, Response<GETTermsAndConditions> response) {
                if (response.body().getCode().equals(200)) {
                    mCustomProgressDialog.dismiss();
                    showTermsAndConditions(response.body().getData().getUser_terms_and_conditions());
                } else {
                    mCustomProgressDialog.dismiss();
                    Toast.makeText(RegisterScreen.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GETTermsAndConditions> call, Throwable t) {
                mCustomProgressDialog.dismiss();
            }
        });
    }

    public void showTermsAndConditions(String user_terms_and_conditions) {

        final Dialog dialog = new Dialog(RegisterScreen.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.show_termsandconditions_dialog);
        // set the custom dialog components - text, image and button
        final TextView inputTermsandConditions = (TextView) dialog.findViewById(R.id.tv_termsandConditions);
        inputTermsandConditions.setText(Html.fromHtml(user_terms_and_conditions));
        dialog.show();
    }

    private void getStateAPI() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.getState(country, AppConstants.GUEST_TOKEN, SessionHandler.getInstance().get(this, AppConstants.Language)).enqueue(new Callback<List<GETState>>() {
            @Override
            public void onResponse(Call<List<GETState>> call, final Response<List<GETState>> response) {
                if (response.isSuccessful()) {
                    if (response.body().size() > 0) {
                        stateData = response.body();
                        addStateLists.clear();
                        addStateLists.add(searchGigsScreen.getLbl_select_state().getName());
                        for (GETState getState : response.body()) {
                            addStateLists.add(getState.getState_name());
                        }
                        if (addStateLists.size() > 0 && stateData.size() > 0) {
                            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(RegisterScreen.this,
                                    android.R.layout.simple_spinner_item, addStateLists);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spinState.setAdapter(adapter);

                            spinState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    if (position > 0 && stateData.size() > 0) {
                                        state = stateData.get(position - 1).getState_id();
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
            }
        });
    }

    private void initLayouts() {
        buttonRegister = (Button) findViewById(R.id.button_register);
        textInputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        textInputLayoutUsername = (TextInputLayout) findViewById(R.id.input_layout_username);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        textInputLayoutRPassword = (TextInputLayout) findViewById(R.id.input_layout_rpassword);
        editName = (EditText) findViewById(R.id.input_name);
        editEmail = (EditText) findViewById(R.id.input_email);
        editUserName = (EditText) findViewById(R.id.input_username);
        editPassword = (EditText) findViewById(R.id.input_password);
        editRPassword = (EditText) findViewById(R.id.input_rpassword);

        spinCountry = (Spinner) findViewById(R.id.spinner_counrty);
        spinState = (Spinner) findViewById(R.id.spinner_state);

        mCheckBox = (CheckBox) findViewById(R.id.cb_agree_conditions);
        ivImage = (ImageView) findViewById(R.id.input_profile_picture);
        tvCountry = (TextView) findViewById(R.id.tv_country);
        tvState = (TextView) findViewById(R.id.tv_state);
        setLanguageValues();
    }


    public void registerHere(View view) {

        if (!validateName()) {
            return;
        }
        if (!validateEmail()) {
            return;
        }
        if (!validateUsername()) {
            return;
        }
        if (!validatePassword()) {
            return;
        }
        if (!validateRPassword()) {
            return;
        }
        if (!validateCountrySpin()) {
            return;
        }
        if (!validateStateSpin()) {
            return;
        }

        /*if (state == null && state.isEmpty()) {
            Toast.makeText(this, "Please select Country", Toast.LENGTH_SHORT).show();
            return;
        }*/

        if (!mCheckBox.isChecked()) {
            Toast.makeText(this, registerScreen.getLbl_terms_and_conditions().getName(), Toast.LENGTH_SHORT).show();
            return;
        }

        email = RequestBody.create(MediaType.parse("text/plain"), editEmail.getText().toString());
        username = RequestBody.create(MediaType.parse("text/plain"), editUserName.getText().toString());
        password = RequestBody.create(MediaType.parse("text/plain"), editPassword.getText().toString());
        states = RequestBody.create(MediaType.parse("text/plain"), state);
        countries = RequestBody.create(MediaType.parse("text/plain"), country);
        fullname = RequestBody.create(MediaType.parse("text/plain"), editName.getText().toString());
        user_timezone = RequestBody.create(MediaType.parse("text/plain"), SessionHandler.getInstance().get(RegisterScreen.this, AppConstants.TIMEZONE_ID));

        if (NetworkChangeReceiver.isConnected()) {
            mCustomProgressDialog.showDialog();
            postRegistration();
        } else {
            showToast();
        }
    }

    private void postRegistration() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.postRegister(email, username, password, states, countries, fullname, user_timezone, body, AppConstants.GUEST_TOKEN, SessionHandler.getInstance().get(this, AppConstants.Language)).enqueue(new Callback<POSTRegister>() {
            @Override
            public void onResponse(Call<POSTRegister> call, Response<POSTRegister> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode().equals(200)) {
                        Toast.makeText(RegisterScreen.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(RegisterScreen.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    try {
                        Log.i("ERROR_REGISTER:", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


                mCustomProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<POSTRegister> call, Throwable t) {
                Log.i("TAG", t.getMessage());
                mCustomProgressDialog.dismiss();
                if (t instanceof UnknownHostException || t instanceof HttpException || t instanceof ConnectException || t instanceof SocketTimeoutException || t instanceof SocketException) {
                    Toast.makeText(RegisterScreen.this, commonStrings.getLbl_server_err().getName(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void showToast() {
        Toast.makeText(this, commonStrings.getLbl_enable_internet().getName(), Toast.LENGTH_SHORT).show();
    }

    private boolean validateName() {
        if (editName.getText().toString().trim().isEmpty()) {
            textInputLayoutName.setError(registerScreen.getTxt_fld_fullname().getValidation1());
            requestFocus(editName);
            return false;
        } else {
            textInputLayoutName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateUsername() {
        if (editUserName.getText().toString().trim().isEmpty()) {
            textInputLayoutUsername.setError(registerScreen.getTxt_fld_username().getValidation1());
            requestFocus(editUserName);
            return false;
        } else {
            textInputLayoutUsername.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateEmail() {
        String email = editEmail.getText().toString().trim();

        if (email.isEmpty() || !Utils.isValidEmail(email)) {
            textInputLayoutEmail.setError(registerScreen.getTxt_fld_email().getValidation1());
            requestFocus(editEmail);
            return false;
        } else {
            textInputLayoutEmail.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validatePassword() {
        if (editPassword.getText().toString().trim().isEmpty()) {
            textInputLayoutPassword.setError(registerScreen.getTxt_fld_pwd().getValidation1());
            requestFocus(editPassword);
            return false;
        } else if (editPassword.getText().toString().length() < 8) {
            textInputLayoutPassword.setError(registerScreen.getTxt_fld_pwd().getValidation2());
            requestFocus(editPassword);
            return false;
        } else if (!editPassword.getText().toString().matches(AppConstants.passwordMatch)) {
            textInputLayoutPassword.setError(registerScreen.getTxt_fld_pwd().getValidation2());
            requestFocus(editPassword);
            return false;
        } else {
            textInputLayoutPassword.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateRPassword() {
        if (editRPassword.getText().toString().trim().isEmpty()) {
            textInputLayoutRPassword.setError(registerScreen.getTxt_fld_rpwd().getValidation1());
            requestFocus(editRPassword);
            return false;
        } else if (!editRPassword.getText().toString().matches(AppConstants.passwordMatch)) {
            textInputLayoutRPassword.setError(registerScreen.getTxt_fld_rpwd().getValidation2());
            requestFocus(editRPassword);
            return false;
        } else if (!editRPassword.getText().toString().trim().equalsIgnoreCase(editPassword.getText().toString().trim())) {
            textInputLayoutRPassword.setError(registerScreen.getTxt_fld_rpwd().getValidation3());
            requestFocus(editRPassword);
            return false;
        } else {
            textInputLayoutRPassword.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateCountrySpin() {
        if (country.isEmpty()) {
            Toast.makeText(this, registerScreen.getLbl_country().getValidation1(), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean validateStateSpin() {
        if (state.isEmpty()) {
            Toast.makeText(this, registerScreen.getLbl_state().getValidation1(), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void SelectPicture(View view) {
        selectImage();
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
                validateName();
            } else if (view.getId() == R.id.input_email) {
                validateEmail();
            } else if (view.getId() == R.id.input_username) {
                validateUsername();
            } else if (view.getId() == R.id.input_password) {
                validatePassword();
            } else if (view.getId() == R.id.input_rpassword) {
                validateRPassword();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }


    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public void loginHere(View view) {
        startActivity(new Intent(this, LoginScreen.class));
    }

    public void termsOfConditions(View view) {

        getTermsandConditions();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);

    }


    private void selectImage() {
        final CharSequence[] items = {commonStrings.getTxt_take_photo().getName(), commonStrings.getTxt_choose_from_gallery().getName(),
                commonStrings.getLbl_cancel().getName()};
        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterScreen.this);
        builder.setTitle(commonStrings.getLbl_add_photo().getName());
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                result = Utils.checkPermission(RegisterScreen.this);
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
                    result = Utils.checkPermission(RegisterScreen.this);
                }
                break;
        }
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


        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
        SessionHandler.getInstance().save(RegisterScreen.this, "BitmapPath", String.valueOf(picturePath));
        ivImage.setImageBitmap(thumbnail);
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


    public void askPermissions() {
        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {

                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })
                .onSameThread().check();
    }

    public void setLanguageValues() {
        commonStrings = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.COMMONSTRINGS), POSTLanguageModel.Common_strings.class);
        searchGigsScreen = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.SEARCHGIGS), POSTLanguageModel.Search_gigs_screen.class);
        registerScreen = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.REGISTER), POSTLanguageModel.Register_screen.class);
        textInputLayoutName.setHint(registerScreen.getTxt_fld_fullname().getName());
        textInputLayoutEmail.setHint(registerScreen.getTxt_fld_email().getName());
        textInputLayoutUsername.setHint(registerScreen.getTxt_fld_username().getName());
        textInputLayoutPassword.setHint(registerScreen.getTxt_fld_pwd().getName());
        textInputLayoutRPassword.setHint(registerScreen.getTxt_fld_rpwd().getName());
        buttonRegister.setText(registerScreen.getBtn_register().getName());
        mToolbar.setTitle(registerScreen.getBtn_register().getName());
        loginInfo.setText(registerScreen.getLbl_already_mem().getName());
        texttLogin.setText(registerScreen.getLbl_login().getName());
        mCheckBox.setText(registerScreen.getLbl_terms_and_conditions().getName());
        tvState.setText(registerScreen.getLbl_state().getName());
        tvCountry.setText(registerScreen.getLbl_country().getName());
        ctvTermsConditions.setText(registerScreen.getLbl_terms_and_conditions().getName());
        tbRegister.setText(registerScreen.getBtn_register().getName());
    }

}
