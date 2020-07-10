package com.example.co.squeeg.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.co.squeeg.ChangePassword;
import com.example.co.squeeg.HelpandSupport;
import com.example.co.squeeg.LoginScreen;
import com.example.co.squeeg.MainActivity;
import com.example.co.squeeg.Model.GETLanguageList;
import com.example.co.squeeg.Model.GETLogoutResponse;
import com.example.co.squeeg.Model.POSTLanguageModel;
import com.example.co.squeeg.PaypalSettings;
import com.example.co.squeeg.R;
import com.example.co.squeeg.StripeSettings;
import com.example.co.squeeg.UpdateProfile;
import com.example.co.squeeg.network.ApiClient;
import com.example.co.squeeg.network.ApiInterface;
import com.example.co.squeeg.receiver.NetworkChangeReceiver;
import com.example.co.squeeg.utils.AppConstants;
import com.example.co.squeeg.utils.CustomProgressDialog;
import com.example.co.squeeg.utils.LocaleUtils;
import com.example.co.squeeg.utils.SessionHandler;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.co.squeeg.utils.Utils.setLangInPref;

public class SettingsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private View settingsView;
    private ListView listSettings;
    private SettingsAdapter aSettingsAdapter;
    public TextView tv_stripe, tv_paypal;
    CustomProgressDialog mCustomProgressDialog;
    public POSTLanguageModel.Common_strings commonStrings = new POSTLanguageModel().new Common_strings();
    public POSTLanguageModel.Settings_screen settingsScreen = new POSTLanguageModel().new Settings_screen();
    String[] settings = new String[6];
    List<GETLanguageList.Datum> languageData = new ArrayList<GETLanguageList.Datum>();

    public SettingsFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
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
        mCustomProgressDialog = new CustomProgressDialog(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        settingsView = inflater.inflate(R.layout.fragment_settings, container, false);

//        String[] settings = getResources().getStringArray(R.array.settings_data);
        setLanguageValues();
        listSettings = (ListView) settingsView.findViewById(R.id.lv_settings);

        if (SessionHandler.getInstance().get(getActivity(), AppConstants.TOKEN_ID) == null) {
            settings[5] = getResources().getString(R.string.text_login);

            aSettingsAdapter = new SettingsAdapter(getActivity(), settings);
        } else {
            settings[5] = settingsScreen.getLbl_logout().getName();
            aSettingsAdapter = new SettingsAdapter(getActivity(), settings);
        }
        listSettings.setAdapter(aSettingsAdapter);


        return settingsView;
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private class SettingsAdapter extends BaseAdapter {
        AppCompatActivity activity;
        final Context mContext;
        final String[] settings;
        final LayoutInflater mInflater;
        CustomProgressDialog mCustomProgressDialog;

        public SettingsAdapter(Context mContext, String[] settings) {
            this.mContext = mContext;
            this.settings = settings;
            mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return settings.length;
        }

        @Override
        public String getItem(int position) {
            return settings[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final ViewHolder mHolder;
            if (convertView == null) {
                mHolder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.adapter_settings, null);
                mHolder.txtSettingsItems = (TextView) convertView.findViewById(R.id.tv_settings_items);
                mCustomProgressDialog = new CustomProgressDialog(mContext);
                convertView.setTag(mHolder);
            } else {
                mHolder = (ViewHolder) convertView.getTag();
            }

            mHolder.txtSettingsItems.setText(getItem(position));

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (position) {
                        case 0:
                            if (SessionHandler.getInstance().get(mContext, AppConstants.TOKEN_ID) != null) {
                                mContext.startActivity(new Intent(mContext, ChangePassword.class));
                            } else {
                                startActivity(new Intent(mContext, LoginScreen.class));
                                ((MainActivity) mContext).finish();
                            }

                            break;
                        case 1:
                            if (SessionHandler.getInstance().get(mContext, AppConstants.TOKEN_ID) != null) {
                                mContext.startActivity(new Intent(mContext, UpdateProfile.class));
                            } else {
                                startActivity(new Intent(mContext, LoginScreen.class));
                                ((MainActivity) mContext).finish();
                            }

                            break;
                        case 2:
                            if (SessionHandler.getInstance().get(mContext, AppConstants.TOKEN_ID) != null) {
//                                mContext.startActivity(new Intent(mContext, PaypalSettings.class));
                                //mContext.startActivity(new Intent(mContext, PaymentSettings.class));
                                showPaymentDialog();
                            } else {
                                startActivity(new Intent(mContext, LoginScreen.class));
                                ((MainActivity) mContext).finish();
                            }
                            break;
                        case 3:

                            getLanguageList();
                            break;
                        case 4:
                            Intent callFaq = new Intent(mContext, HelpandSupport.class);
                            callFaq.putExtra("id", "1");
                            mContext.startActivity(callFaq);
                            break;
                        case 5:
                            if (SessionHandler.getInstance().get(mContext, AppConstants.TOKEN_ID) != null) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                builder.setMessage(settingsScreen.getTxt_logout_info().getName())
                                        .setCancelable(false)
                                        .setPositiveButton(settingsScreen.getLbl_yes().getName(), new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {

                                                if (NetworkChangeReceiver.isConnected()) {
                                                    mCustomProgressDialog.showDialog();
                                                    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                                                    apiInterface.getLogoutResponse(SessionHandler.getInstance().get(mContext, AppConstants.TOKEN_ID), SessionHandler.getInstance().get(mContext, AppConstants.Language)).enqueue(new Callback<GETLogoutResponse>() {
                                                        @Override
                                                        public void onResponse(Call<GETLogoutResponse> call, Response<GETLogoutResponse> response) {
                                                            if (response.body().getCode().equals(200)) {
                                                                mCustomProgressDialog.dismiss();
                                                                removePrefs(mContext);
                                                                notifyDataSetChanged();
                                                                mContext.startActivity(new Intent(mContext, LoginScreen.class));
                                                                ((MainActivity) mContext).finish();
                                                            } else if (response.body().getCode().equals(Integer.parseInt(AppConstants.INVALID_RESPONSE_CODE))) {
                                                                removePrefs(mContext);
                                                                notifyDataSetChanged();
                                                                mContext.startActivity(new Intent(mContext, LoginScreen.class));
                                                                ((MainActivity) mContext).finish();
                                                                //NetworkAlertDialog.networkAlertDialog(getActivity(), "", response.body().getMessage(), null, null);
                                                                mCustomProgressDialog.dismiss();

                                                            } else {
                                                                mCustomProgressDialog.dismiss();
                                                                Toast.makeText(mContext, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                                            }
                                                        }

                                                        @Override
                                                        public void onFailure(Call<GETLogoutResponse> call, Throwable t) {
                                                            mCustomProgressDialog.dismiss();
                                                        }
                                                    });
                                                }


                                            }
                                        })
                                        .setNegativeButton(settingsScreen.getLbl_no().getName(), new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        });
                                AlertDialog alert = builder.create();
                                alert.show();
                            } else {
                                mContext.startActivity(new Intent(mContext, LoginScreen.class));
                                ((MainActivity) mContext).finish();
                            }
                            break;
                    }
                }
            });
            return convertView;
        }

        private void showPaymentDialog() {
            final Dialog rootView = new Dialog(mContext);
            rootView.requestWindowFeature(Window.FEATURE_NO_TITLE);
            rootView.setCancelable(true);
            rootView.setContentView(R.layout.activity_payement_settings);
            tv_paypal = (TextView) rootView.findViewById(R.id.tv_paypal);
            tv_stripe = (TextView) rootView.findViewById(R.id.tv_stripe);
            tv_paypal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mContext.startActivity(new Intent(mContext, PaypalSettings.class));
                    rootView.dismiss();
                }
            });
            tv_stripe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mContext.startActivity(new Intent(mContext, StripeSettings.class));
                    rootView.dismiss();
                }
            });
            rootView.show();
        }

        private void removePrefs(Context mContext) {
            SessionHandler.getInstance().remove(mContext, AppConstants.TOKEN_ID);
            SessionHandler.getInstance().remove(mContext, AppConstants.EMAIL_ID);
            SessionHandler.getInstance().remove(mContext, AppConstants.USER_NAME);
            SessionHandler.getInstance().remove(getActivity(), AppConstants.SellExtraGigsPrice);
            SessionHandler.getInstance().remove(getActivity(), AppConstants.SellGigsPrice);
        }

        class ViewHolder {
            TextView txtSettingsItems;
        }
    }

    public void languageLocale() {
        final String[] items = {"English", "Arabic"};
//        final String[] items = {getResources().getString(R.string.txt_english)};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        LayoutInflater inflater = this.getLayoutInflater();
        View titleView = inflater.inflate(R.layout.list_custom_alert_dialog_tiltle, null);
        builder.setCustomTitle(titleView);
        builder.setAdapter(new LanguageAdapter(getActivity(), R.layout.list_item_language, languageData), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                String lang = "";
//                switch (item) {
//                    case 0:
//                        if (SessionHandler.getInstance().get(getActivity(), "locale").equalsIgnoreCase("en")) {
//                            return;
//                        } else {
//                            lang = "en";
//                            setLocale(lang);
//                        }
//
//                        break;
//                    case 1:
//                        if (SessionHandler.getInstance().get(getActivity(), "locale").equalsIgnoreCase("ar")) {
//                            return;
//                        } else {
//                            lang = "ar";
//                            setLocale(lang);
//                        }
//                        break;
//                }

                if (SessionHandler.getInstance().get(getActivity(), "locale").equalsIgnoreCase(languageData.get(item).getLanguage_value())) {
                    return;
                } else {
                    lang = languageData.get(item).getLanguage_value();
                    setLocale(lang);
                }
                dialog.dismiss();

            }
        });
        builder.show();
    }

    private class LanguageAdapter extends ArrayAdapter<GETLanguageList.Datum> {
        String[] items = new String[2];
        List<GETLanguageList.Datum> languageList = new ArrayList<>();

        public LanguageAdapter(@NonNull Context context, int resource, List<GETLanguageList.Datum> items) {
            super(context, resource, items);
            this.languageList = items;
            /*items[0] = AppUtils.cleanLangStr(context, commonData.getLg7_english(), R.string.txt_english);
            items[1] = AppUtils.cleanLangStr(context, commonData.getLg7_malay(), R.string.txt_malay);*/
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
            final LayoutInflater inflater = (LayoutInflater) getActivity()
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

            return convertView;
        }
    }

    public void setLocale(String localeName) {
//        myLocale = new Locale(localeName);
        SessionHandler.getInstance().save(getActivity(), "locale", localeName);
//        Resources res = getResources();
//        DisplayMetrics dm = res.getDisplayMetrics();
//        Configuration conf = new Configuration();
//        Locale.setDefault(myLocale);
//        conf.locale = myLocale;
//        conf.setLayoutDirection(new Locale(localeName));
//        getBaseContext().getResources().updateConfiguration(conf, null);
//        onConfigurationChanged(conf);
        getLanguageData(localeName);
    }

    public void getLanguageData(final String localeName) {
        if (NetworkChangeReceiver.isConnected()) {
            mCustomProgressDialog.showDialog();
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            apiInterface.languageData(localeName).enqueue(new Callback<POSTLanguageModel>() {
                @Override
                public void onResponse(Call<POSTLanguageModel> call, Response<POSTLanguageModel> response) {
                    mCustomProgressDialog.dismiss();
                    SessionHandler.getInstance().saveBoolean(getActivity(), AppConstants.LANGUAGE_SET, true);
                    SessionHandler.getInstance().save(getActivity(), AppConstants.Language, localeName);
                    setLangInPref(response.body(), getActivity());
                    SessionHandler.getInstance().save(getActivity(), "localechanged", "true");
                    AppConstants.localeName = localeName;
                    LocaleUtils.setLocale(new Locale(localeName));
                    LocaleUtils.updateConfigActivity((AppCompatActivity) getActivity(), getActivity().getBaseContext().getResources().getConfiguration());
                    Intent callMainAct = new Intent(getActivity(), MainActivity.class);
                    callMainAct.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    getActivity().startActivity(callMainAct);
                    getActivity().finish();
                }

                @Override
                public void onFailure(Call<POSTLanguageModel> call, Throwable t) {
                    mCustomProgressDialog.dismiss();
                }
            });
        }
    }

    public void setLanguageValues() {
        commonStrings = new Gson().fromJson(SessionHandler.getInstance().get(getActivity(), AppConstants.COMMONSTRINGS), POSTLanguageModel.Common_strings.class);
        settingsScreen = new Gson().fromJson(SessionHandler.getInstance().get(getActivity(), AppConstants.SETTINGS), POSTLanguageModel.Settings_screen.class);
        settings[0] = settingsScreen.getLbl_change_pwd().getName();
        settings[1] = settingsScreen.getLbl_edit_profile().getName();
        settings[2] = settingsScreen.getLbl_wallet().getName();
        settings[3] = settingsScreen.getLbl_change_language().getName();
        settings[4] = settingsScreen.getLbl_help_and_support().getName();
        settings[5] = settingsScreen.getLbl_logout().getName();
    }


    public void getLanguageList() {
        if (NetworkChangeReceiver.isConnected()) {
            mCustomProgressDialog.showDialog();
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            apiInterface.getLanguageList().enqueue(new Callback<GETLanguageList>() {
                @Override
                public void onResponse(Call<GETLanguageList> call, Response<GETLanguageList> response) {
                    mCustomProgressDialog.dismiss();
                    if (response.body().getCode().equals(200)) {
                        languageData = new ArrayList<>();
                        languageData.addAll(response.body().getData());
                        languageLocale();
                    }
                }

                @Override
                public void onFailure(Call<GETLanguageList> call, Throwable t) {
                    mCustomProgressDialog.dismiss();
                }
            });
        }

    }
}