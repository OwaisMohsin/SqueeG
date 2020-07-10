package com.example.co.squeeg;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.co.squeeg.utils.NetworkAlertDialog;
import com.google.gson.Gson;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.wang.avi.AVLoadingIndicatorView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.example.co.squeeg.Model.GETCountry;
import com.example.co.squeeg.Model.GETLanguageList;
import com.example.co.squeeg.Model.GETProfession;
import com.example.co.squeeg.Model.POSTLanguageModel;
import com.example.co.squeeg.dialogBuilder.Effectstype;
import com.example.co.squeeg.dialogBuilder.NiftyDialogBuilder;
import com.example.co.squeeg.network.ApiClient;
import com.example.co.squeeg.network.ApiInterface;
import com.example.co.squeeg.receiver.NetworkChangeReceiver;
import com.example.co.squeeg.utils.AppConstants;
import com.example.co.squeeg.utils.LocaleUtils;
import com.example.co.squeeg.utils.SessionHandler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.co.squeeg.utils.Utils.setLangInPref;

public class SplashScreen extends AppCompatActivity implements NetworkChangeReceiver.ConnectivityReceiverListener {
    @BindView(R.id.spinner)
    Spinner spinner;
    private Gson gson;
    private AVLoadingIndicatorView aviLoadingView;
    Handler handler;
    Locale myLocale;
    boolean isGranted;
    String language = "en";
    ConstraintLayout clSplashScreen;
    List<GETLanguageList.Datum> languageData = new ArrayList<GETLanguageList.Datum>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ButterKnife.bind(this);
        clSplashScreen = (ConstraintLayout) findViewById(R.id.cl_splash);
        TimeZone tz = TimeZone.getDefault();
        SessionHandler.getInstance().save(SplashScreen.this, AppConstants.TIMEZONE_ID, tz.getID());
        aviLoadingView = (AVLoadingIndicatorView) findViewById(R.id.avi_progress_bar);
//        aviLoadingView = (AVLoadingIndicatorView) findViewById(R.id.avi_progress_bar);
        gson = new Gson();

        generateKeyHashFromCode();
        aviLoadingView.setVisibility(View.INVISIBLE);
        if (!SessionHandler.getInstance().getBoolean(this, AppConstants.LANGUAGE_SET)) {
            askPermissions();
        } else {
            LocaleUtils.setLocale(new Locale(SessionHandler.getInstance().get(this, "locale")));
            LocaleUtils.updateConfigActivity(this, getBaseContext().getResources().getConfiguration());
            aviLoadingView.setVisibility(View.VISIBLE);
            loadData();
        }

//        loadLanguageData();

//        askPermissions();
    }

    private void loadData() {
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                aviLoadingView.show();
                if (SessionHandler.getInstance().get(SplashScreen.this, AppConstants.COUNTRY_JSON) != null) {

                    GETCountry[] getCountryLists = gson.fromJson(SessionHandler.getInstance().get(SplashScreen.this, AppConstants.COUNTRY_JSON), GETCountry[].class);

                    if (!(getCountryLists.length > 0)) {
                        return;
                    }
                    if (!SessionHandler.getInstance().getBoolean(SplashScreen.this, AppConstants.IS_WELCOME_FIRST_TIME)) {
                        startActivity(new Intent(SplashScreen.this, Introscreen.class));
                        finish();
                    } else if (SessionHandler.getInstance().getBoolean(SplashScreen.this, AppConstants.IS_WELCOME_FIRST_TIME) &&
                            SessionHandler.getInstance().get(SplashScreen.this, AppConstants.TOKEN_ID) != null) {
                        startActivity(new Intent(SplashScreen.this, MainActivity.class));
                        finish();
                    } else {
                        startActivity(new Intent(SplashScreen.this, LoginScreen.class));
                        finish();
                    }
                    aviLoadingView.hide();

                } else {
                    if (NetworkChangeReceiver.isConnected()) {
//                        aviLoadingView.show();
                        getCountryAPI();
                    } else {
                        showToast();
                    }
                }
            }
        }, 3000);
    }

    private void getCountryAPI() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.getCountry(AppConstants.GUEST_TOKEN, SessionHandler.getInstance().get(this, AppConstants.Language)).enqueue(new Callback<List<GETCountry>>() {
            @Override
            public void onResponse(Call<List<GETCountry>> call, Response<List<GETCountry>> response) {
                if (response.isSuccessful()) {
                    if (response.body().size() > 0) {
                        String json = gson.toJson(response.body());
                        SessionHandler.getInstance().save(SplashScreen.this, AppConstants.COUNTRY_JSON, json);
                        getProfession();
                    } else {
//                        Toast.makeText(Splashscreen.this, "some error occurred..", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<GETCountry>> call, Throwable t) {
                Log.i("TAG", t.getMessage());
            }
        });
    }

    private void getProfession() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.getProfession(AppConstants.GUEST_TOKEN, SessionHandler.getInstance().get(this, AppConstants.Language)).enqueue(new Callback<List<GETProfession>>() {
            @Override
            public void onResponse(Call<List<GETProfession>> call, Response<List<GETProfession>> response) {
                if (response.isSuccessful()) {
                    if (response.body().size() > 0) {
                        String json = gson.toJson(response.body());
                        SessionHandler.getInstance().save(SplashScreen.this, AppConstants.PROFESSION, json);
                        startActivity(new Intent(SplashScreen.this, Introscreen.class));
                        finish();
                    } else {
                        startActivity(new Intent(SplashScreen.this, Introscreen.class));
                        finish();
                    }
                } else {
                    Log.i("ERROR_PROFESSSION", response.errorBody().toString());
                }
                aviLoadingView.hide();
            }

            @Override
            public void onFailure(Call<List<GETProfession>> call, Throwable t) {
                Log.i("TAG", t.getMessage());
                startActivity(new Intent(SplashScreen.this, Introscreen.class));
                finish();
                aviLoadingView.hide();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // register connection status listener
        MyApplication.getInstance().setConnectivityListener(this);
    }

    private void showToast() {
        Toast.makeText(this, getString(R.string.err_internet_connection), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (isConnected) {
            if (SessionHandler.getInstance().get(SplashScreen.this, AppConstants.COUNTRY_JSON) != null) {
                GETCountry[] getCountryLists = gson.fromJson(SessionHandler.getInstance().get(SplashScreen.this, AppConstants.COUNTRY_JSON), GETCountry[].class);

                if (!(getCountryLists.length > 0)) {
                    return;
                }
                startActivity(new Intent(this, Introscreen.class));
                finish();
            } else {
                aviLoadingView.show();
                getCountryAPI();
            }
        } else {
            showToast();
        }

    }


    private void generateKeyHashFromCode() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.co.squeeg",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException ignored) {

        } catch (NoSuchAlgorithmException ignored) {

        }
    }


    public void setLanguageSettings() {
        final String[] items = {"English", "Arabic"};
//        final String[] items = {getResources().getString(R.string.txt_english)};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        LayoutInflater inflater = this.getLayoutInflater();
        View titleView = inflater.inflate(R.layout.list_custom_alert_dialog_tiltle, null);
        builder.setCustomTitle(titleView);
        builder.setAdapter(new LanguageAdapter(this, R.layout.list_item_language, languageData), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                String lang = languageData.get(item).getLanguage_value();
//                switch (item) {
//                    case 0:
//                        lang = "en";
//                        break;
//                    case 1:
//                        lang = "ar";
//                        break;
//                }
                dialog.dismiss();
                setLocale(lang);
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void setLocale(String localeName) {
        SessionHandler.getInstance().save(SplashScreen.this, "locale", localeName);
        SessionHandler.getInstance().save(SplashScreen.this, "localechanged", "true");
        AppConstants.localeName = localeName;
        LocaleUtils.setLocale(new Locale(localeName));
        LocaleUtils.updateConfigActivity(this, getBaseContext().getResources().getConfiguration());
        aviLoadingView.setVisibility(View.VISIBLE);
        getLanguageData(localeName);
    }

    private class LanguageAdapter extends ArrayAdapter<GETLanguageList.Datum> {
        String[] items = new String[2];
        List<GETLanguageList.Datum> languageList = new ArrayList<>();

        LanguageAdapter(@NonNull Context context, int resource, List<GETLanguageList.Datum> items) {
            super(context, resource, items);
            this.languageList = items;
        }

        ViewHolder holder;

        class ViewHolder {
            ImageView icon;
            TextView title;
        }

        @Override
        public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }


        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {
            final LayoutInflater inflater = (LayoutInflater) getApplicationContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.list_item_language, null);
                holder = new ViewHolder();
                holder.icon = (ImageView) convertView.findViewById(R.id.iv_lang_icon);
                holder.title = (TextView) convertView.findViewById(R.id.tv_lang_txt);
                convertView.setTag(holder);
            } else {
                // view already defined, retrieve view holder
                holder = (ViewHolder) convertView.getTag();
            }
            holder.title.setText(languageList.get(position).getLanguage());
//            if (position == 0)
//                holder.icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_lang_english));
//            else
//                holder.icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_lang_malay));

            return convertView;
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
                        getLanguageList();
//                        loadLanguageData();
//                        setLanguageSettings();
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

    public void getLanguageData(final String localeName) {
        if (NetworkChangeReceiver.isConnected()) {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            apiInterface.languageData(localeName).enqueue(new Callback<POSTLanguageModel>() {
                @Override
                public void onResponse(Call<POSTLanguageModel> call, Response<POSTLanguageModel> response) {
                    SessionHandler.getInstance().saveBoolean(SplashScreen.this, AppConstants.LANGUAGE_SET, true);
                    SessionHandler.getInstance().save(SplashScreen.this, AppConstants.Language, localeName);
                    setLangInPref(response.body(), SplashScreen.this);
                    loadData();
                }

                @Override
                public void onFailure(Call<POSTLanguageModel> call, Throwable t) {

                }
            });
        }
    }

    public void loadLanguageData() {
        final NiftyDialogBuilder dialogBuilder = NiftyDialogBuilder.getInstance(this);
        dialogBuilder
                .withTitle("Choose Language")
                .withTitleColor("#FFFFFF")                                  //def
                .withDividerColor("#11000000")                              //def
                .withMessageColor(getResources().getColor(R.color.colorPrimary))                              //def  | withMessageColor(int resid)
                .withDialogColor("#FFFFFF")                               //def  | withDialogColor(int resid)                               //def
                .isCancelableOnTouchOutside(false)                           //def    | isCancelable(true)
                .withDuration(700)                                          //def
                .withEffect(Effectstype.SlideBottom)
                .withButton1Text("English")                                      //def gone
                .withButton2Text("Arabic")//def gone
                .setButton1Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogBuilder.dismiss();
                        setLocale("en");
//                        Toast.makeText(v.getContext(), "English", Toast.LENGTH_SHORT).show();
                    }
                })
                .setButton2Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogBuilder.dismiss();
                        setLocale("ar");
//                        Toast.makeText(v.getContext(), "Arabic", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }

    public void getLanguageList() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.getLanguageList().enqueue(new Callback<GETLanguageList>() {
            @Override
            public void onResponse(Call<GETLanguageList> call, Response<GETLanguageList> response) {
                if (response.isSuccessful()) {
                    if (response.body().getCode().equals(200)) {
                        languageData.addAll(response.body().getData());
                        setLanguageSettings();
                    }
                } else {
                    NetworkAlertDialog.networkAlertDialog(SplashScreen.this, "Error", "Server not responding", null, "cancel");
                }
            }

            @Override
            public void onFailure(Call<GETLanguageList> call, Throwable t) {

            }
        });
    }
}