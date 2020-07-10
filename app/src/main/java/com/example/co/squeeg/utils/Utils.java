package com.example.co.squeeg.utils;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.co.squeeg.LoginScreen;
import com.example.co.squeeg.Model.POSTLanguageModel;
import com.example.co.squeeg.R;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Utils {
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 100;

    public static String getCurrentDate() {
        Date currentTime = Calendar.getInstance().getTime();
        String date = android.text.format.DateFormat.format("dd-MM-yyyy", currentTime).toString();
        return date;
    }

    public static String getCurrentTimeUsingDate() {

        Date date = new Date();

        String strDateFormat = "hh:mm:ss a";

        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);

        String formattedDate = dateFormat.format(date);

        return formattedDate;
    }

    public static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static void toastMessage(Context mContext, String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

    public static void getListViewSize(ListView myListView) {
        ListAdapter myListAdapter = myListView.getAdapter();
        if (myListAdapter == null) {
            //do nothing return null
            return;
        }
        //set listAdapter in loop for getting final size
        int totalHeight = 0;
        for (int size = 0; size < myListAdapter.getCount(); size++) {
            View listItem = myListAdapter.getView(size, null, myListView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        //setting listview item in adapter
        ViewGroup.LayoutParams params = myListView.getLayoutParams();
        params.height = totalHeight + (myListView.getDividerHeight() * (myListAdapter.getCount() - 1));
        myListView.setLayoutParams(params);
        // print height of adapter on log
        Log.i("height of listItem:", String.valueOf(totalHeight));
    }

    public static void getGridViewSize(GridView myListView) {
        ListAdapter myListAdapter = myListView.getAdapter();
        if (myListAdapter == null) {
            //do nothing return null
            return;
        }
        //set listAdapter in loop for getting final size
        int totalHeight = 0;
        for (int size = 0; size < myListAdapter.getCount(); size++) {
            View listItem = myListAdapter.getView(size, null, myListView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        //setting listview item in adapter
        ViewGroup.LayoutParams params = myListView.getLayoutParams();
        params.height = totalHeight;
        myListView.setLayoutParams(params);
        // print height of adapter on log
        Log.i("height of GridItem:", String.valueOf(totalHeight));
    }

    public static boolean checkPermission(final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((AppCompatActivity) context, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Permission necessary");
                    alertBuilder.setMessage("External storage permission is necessary");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                        }
                    });
                    AlertDialog alert = alertBuilder.create();
                    alert.show();
                } else {
                    ActivityCompat.requestPermissions((AppCompatActivity) context, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public static void showToast(Context mContext) {
        Toast.makeText(mContext, mContext.getString(R.string.err_internet_connection), Toast.LENGTH_SHORT).show();
    }

    public static void freeMemory() {
        System.runFinalization();
        Runtime.getRuntime().gc();
        System.gc();
    }


    public static void createUserInActiceAlert(final Context mContext, String msg) {
        final AppCompatActivity activity = (AppCompatActivity) mContext;

        AlertDialog.Builder userInactiveAlertBuilder = new AlertDialog.Builder(mContext);
        userInactiveAlertBuilder.setTitle("Warning!!!");
        userInactiveAlertBuilder.setCancelable(false);
        userInactiveAlertBuilder.setMessage("Sorry, Your account is inactive. Contact your administrator to activate it.");

        userInactiveAlertBuilder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SessionHandler.getInstance().remove(mContext, AppConstants.TOKEN_ID);
                SessionHandler.getInstance().remove(mContext, AppConstants.EMAIL_ID);
                SessionHandler.getInstance().remove(mContext, AppConstants.USER_NAME);
                SessionHandler.getInstance().remove(mContext, AppConstants.SellExtraGigsPrice);
                SessionHandler.getInstance().remove(mContext, AppConstants.SellGigsPrice);
                Intent callLogin = new Intent(mContext, LoginScreen.class);
                callLogin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                mContext.startActivity(callLogin);
                activity.finish();
                dialog.dismiss();
            }
        });

        userInactiveAlertBuilder.show();
    }

    public static void createUserInActiceLoginAlert(final Context mContext, String msg) {
        /* final Activity activity = (Activity) mContext;*/

        AlertDialog.Builder userInactiveAlertBuilder = new AlertDialog.Builder(mContext);
        userInactiveAlertBuilder.setTitle("Warning!!!");
        userInactiveAlertBuilder.setCancelable(false);
        userInactiveAlertBuilder.setMessage("Sorry, Your account is inactive. Contact your administrator to activate it.");

        userInactiveAlertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                /*SessionHandler.getInstance().remove(mContext, AppConstants.TOKEN_ID);
                SessionHandler.getInstance().remove(mContext, AppConstants.EMAIL_ID);
                SessionHandler.getInstance().remove(mContext, AppConstants.USER_NAME);
                SessionHandler.getInstance().remove(mContext, AppConstants.SellExtraGigsPrice);
                SessionHandler.getInstance().remove(mContext, AppConstants.SellGigsPrice);
                Intent callLogin = new Intent(mContext, Login.class);
                callLogin.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                mContext.startActivity(callLogin);
                activity.finish();*/
                dialog.dismiss();
            }
        });

        userInactiveAlertBuilder.show();
    }

    public static boolean checkCameraPermission(final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= android.os.Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((AppCompatActivity) context, Manifest.permission.CAMERA)) {
                    android.support.v7.app.AlertDialog.Builder alertBuilder = new android.support.v7.app.AlertDialog.Builder(context);
                    alertBuilder.setCancelable(true);
                    alertBuilder.setTitle("Permission necessary");
                    alertBuilder.setMessage("External storage permission is necessary");
                    alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CAMERA}, AppConstants.MY_PERMISSIONS_REQUEST_CAMERA);
                        }
                    });
                    android.support.v7.app.AlertDialog alert = alertBuilder.create();
                    alert.show();
                } else {
                    ActivityCompat.requestPermissions((AppCompatActivity) context, new String[]{Manifest.permission.CAMERA}, AppConstants.MY_PERMISSIONS_REQUEST_CAMERA);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public static void setLanguageLocale(Context mContext, String lang) {
        if (lang != null) {
            Locale locale = new Locale(lang);
            Resources res = mContext.getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = new Configuration();
            conf.locale = locale;
            conf.setLayoutDirection(locale);
            res.updateConfiguration(conf, null);
        }

    }

    public static void updateConfig(ContextThemeWrapper wrapper, String sLocale) {
        if (sLocale != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Configuration configuration = new Configuration();
            configuration.setLocale(new Locale(sLocale));
            wrapper.applyOverrideConfiguration(configuration);
        }
    }


    public static void updateConfig(Application app, Configuration configuration, String sLocale) {
        if (sLocale != null && Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            //Wrapping the configuration to avoid Activity endless loop
            Configuration config = new Configuration(configuration);
            // We must use the now-deprecated config.locale and res.updateConfiguration here,
            // because the replacements aren't available till API level 24 and 17 respectively.
            config.locale = new Locale(sLocale);
            Resources res = app.getBaseContext().getResources();
            res.updateConfiguration(config, res.getDisplayMetrics());
        }
    }

    public synchronized static void setLangInPref(POSTLanguageModel myRes, Context mContext) {
        POSTLanguageModel langData = myRes;
        if (langData != null && langData.getData() != null) {
            try {
                if (langData.getData().getLanguage().getLogin_screen() != null) {
                    String localeData = new Gson().toJson(langData.getData().getLanguage().getLogin_screen());
//                    PreferenceStorage.setKey(AppConstants.LOGIN, localeData);
                    SessionHandler.getInstance().save(mContext, AppConstants.LOGIN, localeData);
                }
            } catch (Exception e) {
            }

            try {
                if (langData.getData().getLanguage().getChange_password_screen() != null) {
                    String localeData = new Gson().toJson(langData.getData().getLanguage().getChange_password_screen());
                    SessionHandler.getInstance().save(mContext, AppConstants.CHANGEPASSWORD, localeData);
//                    PreferenceStorage.setKey(AppConstants.CHANGEPASSWORD, localeData);
                }
            } catch (Exception e) {
            }
            try {
                if (langData.getData().getLanguage().getCommon_strings() != null) {
                    String localeData = new Gson().toJson(langData.getData().getLanguage().getCommon_strings());
                    SessionHandler.getInstance().save(mContext, AppConstants.COMMONSTRINGS, localeData);
//                    PreferenceStorage.setKey(AppConstants.COMMONSTRINGS, localeData);
                }
            } catch (Exception e) {
            }

            try {
                if (langData.getData().getLanguage().getDetail_gigs() != null) {
                    String localeData = new Gson().toJson(langData.getData().getLanguage().getDetail_gigs());
                    SessionHandler.getInstance().save(mContext, AppConstants.DETAILGIGS, localeData);
//                    PreferenceStorage.setKey(AppConstants.DETAILGIGS, localeData);
                }
            } catch (Exception e) {
            }

            try {
                if (langData.getData().getLanguage().getForgot_password_screen() != null) {
                    String localeData = new Gson().toJson(langData.getData().getLanguage().getForgot_password_screen());
                    SessionHandler.getInstance().save(mContext, AppConstants.FORGOTPASSWORD, localeData);
//                    PreferenceStorage.setKey(AppConstants.FORGOTPASSWORD, localeData);
                }
            } catch (Exception e) {
            }
            try {
                if (langData.getData().getLanguage().getHome_screen() != null) {
                    String localeData = new Gson().toJson(langData.getData().getLanguage().getHome_screen());
                    SessionHandler.getInstance().save(mContext, AppConstants.HOMESCREEN, localeData);
//                    PreferenceStorage.setKey(AppConstants.HOMESCREEN, localeData);
                }
            } catch (Exception e) {
            }
            try {
                if (langData.getData().getLanguage().getIntro_screen() != null) {
                    String localeData = new Gson().toJson(langData.getData().getLanguage().getIntro_screen());
                    SessionHandler.getInstance().save(mContext, AppConstants.INTROSCREEN, localeData);
//                    PreferenceStorage.setKey(AppConstants.INTROSCREEN, localeData);
                }
            } catch (Exception e) {
            }
            try {
                if (langData.getData().getLanguage().getMy_activity_screen() != null) {
                    String localeData = new Gson().toJson(langData.getData().getLanguage().getMy_activity_screen());
                    SessionHandler.getInstance().save(mContext, AppConstants.MYACTIVITY, localeData);
//                    PreferenceStorage.setKey(AppConstants.MYACTIVITY, localeData);
                }
            } catch (Exception e) {
            }
            try {
                if (langData.getData().getLanguage().getNavigation_screen() != null) {
                    String localeData = new Gson().toJson(langData.getData().getLanguage().getNavigation_screen());
                    PreferenceStorage.setKey(AppConstants.NAVSCREEN, localeData);
                    SessionHandler.getInstance().save(mContext, AppConstants.NAVSCREEN, localeData);
                }
            } catch (Exception e) {
            }
            try {
                if (langData.getData().getLanguage().getRegister_screen() != null) {
                    String localeData = new Gson().toJson(langData.getData().getLanguage().getRegister_screen());
                    SessionHandler.getInstance().save(mContext, AppConstants.REGISTER, localeData);
//                    PreferenceStorage.setKey(AppConstants.REGISTER, localeData);
                }
            } catch (Exception e) {
            }
            try {
                if (langData.getData().getLanguage().getSearch_gigs_screen() != null) {
                    String localeData = new Gson().toJson(langData.getData().getLanguage().getSearch_gigs_screen());
                    SessionHandler.getInstance().save(mContext, AppConstants.SEARCHGIGS, localeData);
//                    PreferenceStorage.setKey(AppConstants.SEARCHGIGS, localeData);
                }
            } catch (Exception e) {
            }
            try {
                if (langData.getData().getLanguage().getSell_gigs_screen() != null) {
                    String localeData = new Gson().toJson(langData.getData().getLanguage().getSell_gigs_screen());
                    SessionHandler.getInstance().save(mContext, AppConstants.SELLGIGS, localeData);

//                    PreferenceStorage.setKey(AppConstants.SELLGIGS, localeData);
                }
            } catch (Exception e) {
            }
            try {
                if (langData.getData().getLanguage().getChange_password_screen() != null) {
                    String localeData = new Gson().toJson(langData.getData().getLanguage().getChange_password_screen());
                    SessionHandler.getInstance().save(mContext, AppConstants.CHANGEPASSWORD, localeData);
                    PreferenceStorage.setKey(AppConstants.CHANGEPASSWORD, localeData);
                }
            } catch (Exception e) {
            }
            try {
                if (langData.getData().getLanguage().getSettings_screen() != null) {
                    String localeData = new Gson().toJson(langData.getData().getLanguage().getSettings_screen());
                    SessionHandler.getInstance().save(mContext, AppConstants.SETTINGS, localeData);
//                    PreferenceStorage.setKey(AppConstants.SETTINGS, localeData);
                }
            } catch (Exception e) {
            }
            try {
                if (langData.getData().getLanguage().getStripe_payment_screen() != null) {
                    String localeData = new Gson().toJson(langData.getData().getLanguage().getStripe_payment_screen());
                    SessionHandler.getInstance().save(mContext, AppConstants.STRIPEPAYMENT, localeData);
//                    PreferenceStorage.setKey(AppConstants.STRIPEPAYMENT, localeData);
                }
            } catch (Exception e) {
            }
            try {
                if (langData.getData().getLanguage().getTabbar_screen() != null) {
                    String localeData = new Gson().toJson(langData.getData().getLanguage().getTabbar_screen());
                    SessionHandler.getInstance().save(mContext, AppConstants.TABBAR, localeData);
//                    PreferenceStorage.setKey(AppConstants.TABBAR, localeData);
                }
            } catch (Exception e) {
            }

            try {
                if (langData.getData().getLanguage().getCart_screen() != null) {
                    String localeData = new Gson().toJson(langData.getData().getLanguage().getCart_screen());
                    SessionHandler.getInstance().save(mContext, AppConstants.CARTSCREEN, localeData);
//                    PreferenceStorage.setKey(AppConstants.TABBAR, localeData);
                }
            } catch (Exception e) {
            }

        }
    }

    public static String cleanLangStr(Context mContext, String str1, int stringId) {
        String localeStr = mContext.getResources().getString(stringId);
        if (str1 != null && !str1.isEmpty()) {
            localeStr = str1;
        }
        return localeStr;
    }

}
