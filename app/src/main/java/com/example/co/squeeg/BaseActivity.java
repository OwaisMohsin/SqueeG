package com.example.co.squeeg;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.Toast;

import java.util.Locale;

import com.example.co.squeeg.utils.SessionHandler;

public abstract class BaseActivity extends AppCompatActivity {

    Locale locale;
//    public POSTLanguageModel.Change_password_screen changePasswordScreen = new POSTLanguageModel().new Change_password_screen();
//    public POSTLanguageModel.Common_strings commonStrings = new POSTLanguageModel().new Common_strings();
//    public POSTLanguageModel.Detail_gigs detailGigs = new POSTLanguageModel().new Detail_gigs();
//    public POSTLanguageModel.Forgot_password_screen forgotPasswordScreen = new POSTLanguageModel().new Forgot_password_screen();
//    public POSTLanguageModel.Home_screen homeScreen = new POSTLanguageModel().new Home_screen();
//    public POSTLanguageModel.Intro_screen introScreen = new POSTLanguageModel().new Intro_screen();
////    public POSTLanguageModel.Login_screen loginScreen = new POSTLanguageModel().new Login_screen();
//    public POSTLanguageModel.My_activity_screen myActivityScreen = new POSTLanguageModel().new My_activity_screen();
//    public POSTLanguageModel.Navigation_screen navigationScreen = new POSTLanguageModel().new Navigation_screen();
//    public POSTLanguageModel.Register_screen registerScreen = new POSTLanguageModel().new Register_screen();
//    public POSTLanguageModel.Search_gigs_screen searchGigsScreen = new POSTLanguageModel().new Search_gigs_screen();
//    public POSTLanguageModel.Sell_gigs_screen sellGigsScreen = new POSTLanguageModel().new Sell_gigs_screen();
//    public POSTLanguageModel.Settings_screen settingsScreen = new POSTLanguageModel().new Settings_screen();
//    public POSTLanguageModel.Stripe_payment_screen stripePaymentScreen = new POSTLanguageModel().new Stripe_payment_screen();
//    public POSTLanguageModel.Tabbar_screen tabbarScreen = new POSTLanguageModel().new Tabbar_screen();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public BaseActivity() {
//        LocaleUtils.updateConfig(this);
//        setLanguageData();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private void localeChanged() {
        if (SessionHandler.getInstance().get(this, "locale") != null) {
            locale = new Locale(SessionHandler.getInstance().get(this, "locale"));
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = new Configuration();
            Locale.setDefault(locale);
            conf.locale = locale;
            conf.setLayoutDirection(locale);
            getBaseContext().getResources().updateConfiguration(conf, null);
            onConfigurationChanged(conf);
            Toast.makeText(this, String.valueOf(locale), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
//
    }

    public void setLanguageData() {
//        String lgLogin = SessionHandler.getInstance().get(this, AppConstants.LOGIN);
//        loginScreen = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.LOGIN), POSTLanguageModel.Login_screen.class);
//        changePasswordScreen = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.CHANGEPASSWORD), POSTLanguageModel.Change_password_screen.class);
//        commonStrings = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.COMMONSTRINGS), POSTLanguageModel.Common_strings.class);
//        detailGigs = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.DETAILGIGS), POSTLanguageModel.Detail_gigs.class);
//        forgotPasswordScreen = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.FORGOTPASSWORD), POSTLanguageModel.Forgot_password_screen.class);
//        homeScreen = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.HOMESCREEN), POSTLanguageModel.Home_screen.class);
//        introScreen = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.INTROSCREEN), POSTLanguageModel.Intro_screen.class);
//        myActivityScreen = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.MYACTIVITY), POSTLanguageModel.My_activity_screen.class);
//        navigationScreen = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.NAVSCREEN), POSTLanguageModel.Navigation_screen.class);
//        registerScreen = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.REGISTER), POSTLanguageModel.Register_screen.class);
//        searchGigsScreen = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.SEARCHGIGS), POSTLanguageModel.Search_gigs_screen.class);
//        sellGigsScreen = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.SELLGIGS), POSTLanguageModel.Sell_gigs_screen.class);
//        settingsScreen = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.SETTINGS), POSTLanguageModel.Settings_screen.class);
//        stripePaymentScreen = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.STRIPEPAYMENT), POSTLanguageModel.Stripe_payment_screen.class);
//        tabbarScreen = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.TABBAR), POSTLanguageModel.Tabbar_screen.class);
    }
}