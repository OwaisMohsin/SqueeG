package com.example.co.squeeg.utils;

import android.app.Application;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextThemeWrapper;

import java.util.Locale;

public class LocaleUtils {

    private static Locale sLocale;

    public static void setLocale(Locale locale) {
        sLocale = locale;
        if (sLocale != null) {
            Locale.setDefault(sLocale);
        }
    }

    public static void updateConfig(ContextThemeWrapper wrapper) {
        if (sLocale != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Configuration configuration = new Configuration();
            configuration.setLayoutDirection(sLocale);
            configuration.setLocale(sLocale);
            wrapper.applyOverrideConfiguration(configuration);
        }
    }

    public static void updateConfig(Application app, Configuration configuration) {
        if (sLocale != null) {
            //Wrapping the configuration to avoid Activity endless loop
            Configuration config = new Configuration(configuration);
            config.locale = sLocale;
            config.setLayoutDirection(sLocale);
            Resources res = app.getBaseContext().getResources();
            res.updateConfiguration(config, null);
        }
    }

    public static void updateConfigActivity(AppCompatActivity app, Configuration configuration) {
        if (sLocale != null) {
            //Wrapping the configuration to avoid Activity endless loop
            Configuration config = new Configuration(configuration);
            config.locale = sLocale;
            config.setLayoutDirection(sLocale);
            Resources res = app.getBaseContext().getResources();
            res.updateConfiguration(config, null);
        }
    }
}