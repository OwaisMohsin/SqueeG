package com.example.co.squeeg;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.co.squeeg.Model.user_type.UserType;
import com.example.co.squeeg.Model.user_type.UserTypeList;
import com.example.co.squeeg.network.ApiClient;
import com.example.co.squeeg.network.ApiInterface;
import com.example.co.squeeg.receiver.NetworkChangeReceiver;
import com.google.gson.Gson;

import com.example.co.squeeg.Model.POSTLanguageModel;
import com.example.co.squeeg.utils.AppConstants;
import com.example.co.squeeg.utils.SessionHandler;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Introscreen extends BaseActivity {

    private ViewPager viewPager;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private Button btnSkip, btnNext;
    private MyViewPagerAdapter myViewPagerAdapter;
    public POSTLanguageModel.Intro_screen introScreen = new POSTLanguageModel().new Intro_screen();
    private List<UserType> userTypeList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (SessionHandler.getInstance().getBoolean(Introscreen.this, AppConstants.IS_WELCOME_FIRST_TIME)) {
            launchHomeScreen();
        }
        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        setContentView(R.layout.activity_welcome);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        btnSkip = (Button) findViewById(R.id.btn_skip);
        btnNext = (Button) findViewById(R.id.btn_next);

        layouts = new int[]{
                R.layout.welcome_intro_one,
                R.layout.welcome_intro_two,
        };
        addBottomDots(0);

        changeStatusBarColor();
        setLanguageValues();
        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHomeScreen();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // checking for last page
                // if last page home screen will be launched
                int current = getItem(+1);
                if (current < layouts.length) {
                    // move to next screen
                    viewPager.setCurrentItem(current);
                } else {
                    launchHomeScreen();
                }
            }
        });
    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    private int getItem(int i) {
        return viewPager.getCurrentItem() + i;
    }

    private final ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);


            // changing the next button text 'NEXT' / 'GOT IT'
            if (position == layouts.length - 1) {
                // last page. make button text to GOT IT
                btnNext.setText(introScreen.getLbl_got_it().getName());
                btnSkip.setVisibility(View.GONE);
            } else {
                // still pages are left
                btnNext.setText(introScreen.getLbl_next().getName());
                btnSkip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view;
            TextView tvTitle, tvSubTitle;
            view = layoutInflater.inflate(layouts[position], container, false);
            tvTitle = (TextView) view.findViewById(R.id.tv_title);
            tvSubTitle = (TextView) view.findViewById(R.id.tv_subtitle);
//            if (position == 0) {
//                tvTitle.setText(introScreen.getLbl_showcase().getName());
//                tvSubTitle.setText(introScreen.getLbl_destination().getName());
//            } else {
////                view = layoutInflater.inflate(layouts[position], container, false);
////                tvTitle = (TextView) view.findViewById(R.id.tv_title);
////                tvSubTitle = (TextView) view.findViewById(R.id.tv_subtitle);
//                tvTitle.setText(introScreen.getLbl_get_rewarded().getName());
//                tvSubTitle.setText(introScreen.getLbl_earn_cash().getName());
//            }
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }

    private void launchHomeScreen() {
        getUserType();
    }

    private void getUserType() {
        if (NetworkChangeReceiver.isConnected()) {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            apiInterface.agetUserType().enqueue(new Callback<UserTypeList>() {
                @Override
                public void onResponse(Call<UserTypeList> call, Response<UserTypeList> response) {
                    if (response.body().getCode().equals(200)) {
                        userTypeList.clear();
                        userTypeList.addAll(response.body().getUserType());
                        setUserType();
                    } else {
                        showToast();
                    }
                }

                @Override
                public void onFailure(Call<UserTypeList> call, Throwable t) {

                }
            });
        } else {
            showToast();
        }
    }

    private void showToast() {
        Toast.makeText(this, getString(R.string.err_internet_connection), Toast.LENGTH_SHORT).show();
    }

    public void setUserType() {
//        userTypeList.clear();
        final String[] items = {"Buyer", "Seller"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        LayoutInflater inflater = this.getLayoutInflater();
        View titleView = inflater.inflate(R.layout.list_custom_alert_dialog_tiltle_user_type, null);
        builder.setCustomTitle(titleView);
        builder.setTitle(R.string.choose_user_type);
        builder.setAdapter(new LanguageAdapter(this, R.layout.list_item_language, userTypeList), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                String lang = userTypeList.get(item).getLang_value();
                SessionHandler.getInstance().saveBoolean(Introscreen.this, AppConstants.IS_WELCOME_FIRST_TIME, true);
                SessionHandler.getInstance().save(Introscreen.this, AppConstants.USER_TYPE, lang);
                startActivity(new Intent(Introscreen.this, MainActivity.class));
                finish();
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private class LanguageAdapter extends ArrayAdapter<UserType> {
        String[] items = new String[2];
        List<UserType> languageList = new ArrayList<>();

        LanguageAdapter(@NonNull Context context, int resource, List<UserType> items) {
            super(context, resource, items);
            this.languageList = items;
        }

        LanguageAdapter.ViewHolder holder;

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
                holder = new LanguageAdapter.ViewHolder();
                holder.icon = (ImageView) convertView.findViewById(R.id.iv_lang_icon);
                holder.title = (TextView) convertView.findViewById(R.id.tv_lang_txt);
                convertView.setTag(holder);
            } else {
                // view already defined, retrieve view holder
                holder = (LanguageAdapter.ViewHolder) convertView.getTag();
            }
            holder.title.setText(languageList.get(position).getLang_value());
//            if (position == 0)
//                holder.icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_lang_english));
//            else
//                holder.icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_lang_malay));

            return convertView;
        }
    }

    public void setLanguageValues() {
        introScreen = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.INTROSCREEN), POSTLanguageModel.Intro_screen.class);
        btnNext.setText(introScreen.getLbl_next().getName());
        btnSkip.setText(introScreen.getLbl_skip().getName());
    }
}
