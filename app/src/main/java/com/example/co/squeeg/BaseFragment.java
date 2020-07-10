package com.example.co.squeeg;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.co.squeeg.utils.SessionHandler;

import java.util.Locale;

/**
 * Created by Hari on 12-12-2018.
 */

public class BaseFragment extends Fragment {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        localeChanged();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private void localeChanged() {
        Configuration newConfig = new Configuration();
        if (SessionHandler.getInstance().get(getActivity(), "locale") != null) {
            newConfig.locale = new Locale(SessionHandler.getInstance().get(getActivity(), "locale"));
        } else {
            newConfig.locale = new Locale("en");
        }
        onConfigurationChanged(newConfig);
    }

}
