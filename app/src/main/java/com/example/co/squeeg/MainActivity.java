package com.example.co.squeeg;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.co.squeeg.Model.POSTLanguageModel;
import com.example.co.squeeg.fragment.BuyFragment;
import com.example.co.squeeg.fragment.BuyerRequestFragment;
import com.example.co.squeeg.fragment.ChatFragment;
import com.example.co.squeeg.fragment.HomeFragment;
import com.example.co.squeeg.fragment.SellFragment;
import com.example.co.squeeg.fragment.BuyerRequestsToSellerFragment;
import com.example.co.squeeg.fragment.SettingsFragment;
import com.example.co.squeeg.interfaces.callnavigationHme;
import com.example.co.squeeg.utils.AppConstants;
import com.example.co.squeeg.utils.SessionHandler;
import com.example.co.squeeg.utils.Utils;
import com.google.gson.Gson;
import com.onesignal.OneSignal;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, callnavigationHme {

    @Override
    public void callHome() {
        FragmentTransaction fragmentTransaction;
        Fragment fragment = null;
        fragment = new HomeFragment();
        removeHomeHighlight();
        if (fragment != null) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_frame_layout, fragment);
            fragmentTransaction.commit();
        }
    }

    private ImageView ivHome, ivExplore, ivOffer, ivRequest, ivBuy, ivChat, ivSettings;
    private CircleImageView iv_user_image;
    private TextView txtHome, txtExplore, txtOffer, txtRequest, txtBuy, txtChat, txtSettings, txt_username;
    private LinearLayout llHome;
    private LinearLayout llExplore;
    private LinearLayout llOffer;
    private LinearLayout llRequest;
    private LinearLayout llBuy;
    private LinearLayout llChat;
    private LinearLayout llSettings;
    String username;
    private Menu drawerMenu;
    public POSTLanguageModel.Common_strings commonStrings = new POSTLanguageModel().new Common_strings();
    public POSTLanguageModel.Tabbar_screen tabbarScreen = new POSTLanguageModel().new Tabbar_screen();
    public POSTLanguageModel.Navigation_screen navigationScreen = new POSTLanguageModel().new Navigation_screen();
    private String userType;
    private View vCart,
            vExplore,
            vOffer,
            vRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        Utils.setLanguageLocale(this, SessionHandler.getInstance().get(this, "locale"));
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Utils.freeMemory();

        // if savedInstanceState is null we do some cleanup
        if (savedInstanceState != null) {
            // cleanup any existing fragments in case we are in detailed mode
            getSupportFragmentManager().executePendingTransactions();
            Fragment fragmentById = getSupportFragmentManager().
                    findFragmentById(R.id.fragment_frame_layout);
            if (fragmentById != null) {
                getSupportFragmentManager().beginTransaction()
                        .remove(fragmentById).commit();
            }
        }
        initLayouts();
        setLanguageValues();

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationDrawerSetContent();
        hideItem();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                /*if (SessionHandler.getInstance().get(MainActivity.this, AppConstants.TOKEN_ID) != null && SessionHandler.getInstance().get(MainActivity.this, AppConstants.USER_IMAGE) != null) {
                    username = SessionHandler.getInstance().get(MainActivity.this, AppConstants.USER_NAME);
                    txt_username.setText("Hi " + username.substring(0, 1).toUpperCase() + username.substring(1));
                    *//*Picasso.with(MainActivity.this)
                            .load(AppConstants.ImageBaseURL + SessionHandler.getInstance().get(MainActivity.this, AppConstants.USER_IMAGE))
                            .error(R.drawable.ic_no_image_100)
                            .placeholder(R.drawable.ic_no_image_100)
                            .into(iv_user_image);*//*
                    //iv_user_image.setImageURI(Uri.parse(AppConstants.ImageBaseURL + SessionHandler.getInstance().get(MainActivity.this, AppConstants.USER_IMAGE)));
                } else {
                    txt_username.setText("Hi Guest");
                }*/
                drawer.invalidate();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        drawer.setDrawerListener(toggle);
        toggle.syncState();
        //setDefaultIcons();
        setLanguageValues();
        removeHomeHighlight();
        hideViews();

        if (getIntent() != null && getIntent().getStringExtra("From") != null && getIntent().getStringExtra("From").equalsIgnoreCase("MainPage")) {
            int loadFragment = getIntent().getIntExtra("LoadFragment", 0);
            Fragment fragment = null;
            switch (loadFragment) {
                case 0:
                default:
                    fragment = new HomeFragment();
                    removeHomeHighlight();
                    break;
                case 1://TODO:
                    this.getSupportActionBar().setTitle(commonStrings.getTitle_sell_services().getName());
                    fragment = new SellFragment();
                    removeExploreHighlight();
                    break;
                case 2:
                    this.getSupportActionBar().setTitle(commonStrings.getTitle_buy_services().getName());
                    fragment = new BuyFragment();
                    removeBuyHighlight();
                    break;
                case 3:
                    this.getSupportActionBar().setTitle(commonStrings.getTitle_chat().getName());
                    fragment = new ChatFragment();
                    removeChatHighlight();
                    break;
                case 4:
                    this.getSupportActionBar().setTitle(commonStrings.getTitle_settings().getName());
                    fragment = new SettingsFragment();
                    removeSettingsHighlight();
                    break;
                case 5:
                    this.getSupportActionBar().setTitle(commonStrings.getTitle_buyer_request().getName());
                    fragment = new BuyerRequestFragment();
                    removeRequestHighlight();
                    break;
                case 6:
                    this.getSupportActionBar().setTitle(commonStrings.getTitle_seller_offer().getName());
                    fragment = new BuyerRequestsToSellerFragment();
                    removeOfferHighlight();
                    break;
            }
            if (fragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_frame_layout, fragment).commit();
            }

        } else if (getIntent() != null && getIntent().getStringExtra("From") != null && getIntent().getStringExtra("From").equalsIgnoreCase("NavPage")) {
            callNavIntent(getIntent().getIntExtra("LoadNav", 0));
        } else {
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.fragment_frame_layout, new HomeFragment()).commit();
//            removeHomeHighlight();
            llHome.performClick();
        }
    }

    private void NavigationDrawerSetContent() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);
        iv_user_image = (CircleImageView) header.findViewById(R.id.iv_userimage);
        txt_username = (TextView) header.findViewById(R.id.input_username);

        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_camera).setTitle(navigationScreen.getLbl_favourites().getName());
        menu.findItem(R.id.nav_gallery).setTitle(navigationScreen.getLbl_last_visited_gigs().getName());
        menu.findItem(R.id.nav_search).setTitle(navigationScreen.getLbl_search_gigs().getName());
        menu.findItem(R.id.nav_myactivity).setTitle(navigationScreen.getLbl_my_activity().getName());
        menu.findItem(R.id.nav_myGigs).setTitle(navigationScreen.getLbl_my_gigs().getName());

        if (SessionHandler.getInstance().get(MainActivity.this, AppConstants.TOKEN_ID) != null && SessionHandler.getInstance().get(MainActivity.this, AppConstants.USER_IMAGE) != null) {
            username = SessionHandler.getInstance().get(MainActivity.this, AppConstants.USER_NAME);
            txt_username.setText(commonStrings.getLbl_fld_hi().getName() + " " + username.substring(0, 1).toUpperCase() + username.substring(1));
            Picasso.get()
                    .load(AppConstants.ImageBaseURL + SessionHandler.getInstance().get(MainActivity.this, AppConstants.USER_IMAGE))
                    .error(R.drawable.ic_no_image_100)
                    .placeholder(R.drawable.ic_no_image_100)
                    .into(iv_user_image);
            //iv_user_image.setImageURI(Uri.parse(AppConstants.ImageBaseURL + SessionHandler.getInstance().get(MainActivity.this, AppConstants.USER_IMAGE)));
        } else {
            txt_username.setText(commonStrings.getLbl_guest().getName());
            txt_username.setText(commonStrings.getLbl_guest().getName());
        }
    }

    private void hideItem() {
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
        if (userType.equalsIgnoreCase("Buyer")) {
            nav_Menu.findItem(R.id.nav_myGigs).setVisible(false);
        } else
            nav_Menu.findItem(R.id.nav_myRequests).setVisible(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Utils.setLanguageLocale(this, SessionHandler.getInstance().get(this, "locale"));
        OneSignal.setInFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification);
        NavigationDrawerSetContent();
        hideItem();
    }

    private void setDefaultIcons() {
        txtExplore.setTextColor(getResources().getColor(android.R.color.black));
        ivExplore.setBackgroundResource(R.drawable.ic_explore_black_24dp);
        txtOffer.setTextColor(getResources().getColor(android.R.color.black));
        ivOffer.setBackgroundResource(R.drawable.ic_explore_black_24dp);
        txtRequest.setTextColor(getResources().getColor(android.R.color.black));
        ivRequest.setBackgroundResource(R.drawable.ic_explore_black_24dp);
        txtBuy.setTextColor(getResources().getColor(android.R.color.black));
        ivBuy.setBackgroundResource(R.drawable.ic_add_shopping_cart_black_24dp);
        txtChat.setTextColor(getResources().getColor(android.R.color.black));
        ivChat.setBackgroundResource(R.drawable.ic_chat_black_24dp);
        txtSettings.setTextColor(getResources().getColor(android.R.color.black));
        ivSettings.setBackgroundResource(R.drawable.ic_settings_black_24dp);
        txtHome.setTextColor(getResources().getColor(R.color.colorAppTheme));
        ivHome.setBackgroundResource(R.drawable.ic_home_purple_24dp);
    }

    private void initLayouts() {
        ivHome = (ImageView) findViewById(R.id.iv_home);
        ivExplore = (ImageView) findViewById(R.id.iv_explore);
        ivOffer = (ImageView) findViewById(R.id.iv_offer);
        ivRequest = (ImageView) findViewById(R.id.iv_request);
        ivBuy = (ImageView) findViewById(R.id.iv_cart);
        ivChat = (ImageView) findViewById(R.id.iv_chat);
        ivSettings = (ImageView) findViewById(R.id.iv_settings);

        txtHome = (TextView) findViewById(R.id.txt_home);
        txtExplore = (TextView) findViewById(R.id.txt_explore);
        txtOffer = (TextView) findViewById(R.id.txt_offer);
        txtRequest = (TextView) findViewById(R.id.txt_request);
        txtBuy = (TextView) findViewById(R.id.txt_cart);
        txtChat = (TextView) findViewById(R.id.txt_chat);
        txtSettings = (TextView) findViewById(R.id.txt_settings);

        llHome = (LinearLayout) findViewById(R.id.ll_home);
        llExplore = (LinearLayout) findViewById(R.id.ll_explore);
        llOffer = (LinearLayout) findViewById(R.id.ll_offer);
        llRequest = (LinearLayout) findViewById(R.id.ll_request);
        llBuy = (LinearLayout) findViewById(R.id.ll_buy);
        llChat = (LinearLayout) findViewById(R.id.ll_chat);
        llSettings = (LinearLayout) findViewById(R.id.ll_settings);

        vCart = findViewById(R.id.v_cart);
        vExplore = findViewById(R.id.v_explore);
        vOffer = findViewById(R.id.v_offer);
        vRequest = findViewById(R.id.v_request);

        llHome.setOnClickListener(this);
        llExplore.setOnClickListener(this);
        llOffer.setOnClickListener(this);
        llRequest.setOnClickListener(this);
        llBuy.setOnClickListener(this);
        llChat.setOnClickListener(this);
        llSettings.setOnClickListener(this);
    }

    private void hideViews() {
        if (userType.equalsIgnoreCase("Buyer")) {
            llExplore.setVisibility(View.GONE);
            vExplore.setVisibility(View.GONE);
            llOffer.setVisibility(View.GONE);
            vOffer.setVisibility(View.GONE);
        } else if (userType.equalsIgnoreCase("Seller")) {
            llBuy.setVisibility(View.GONE);
            vCart.setVisibility(View.GONE);
            llRequest.setVisibility(View.GONE);
            vRequest.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
//        this.drawerMenu = menu;
//        drawerMenu.findItem(R.id.nav_camera).setTitle(navigationScreen.getLbl_favourites().getName());
//        drawerMenu.findItem(R.id.nav_gallery).setTitle(navigationScreen.getLbl_last_visited_gigs().getName());
//        drawerMenu.findItem(R.id.nav_search).setTitle(navigationScreen.getLbl_search_gigs().getName());
//        drawerMenu.findItem(R.id.nav_myactivity).setTitle(navigationScreen.getLbl_my_activity().getName());
//        drawerMenu.findItem(R.id.nav_myGigs).setTitle(navigationScreen.getLbl_my_gigs().getName());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            if (SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID) != null) {
                startActivity(new Intent(this, Favourites.class));
            } else {
                Intent loginIntent = new Intent(this, LoginScreen.class);
                loginIntent.putExtra("From", "NavPage");
                loginIntent.putExtra("LoadNav", 1);
                startActivity(loginIntent);
                finish();
            }
        } else if (id == R.id.nav_gallery) {
            if (SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID) != null) {
                startActivity(new Intent(this, LastVisitedGigs.class));
            } else {
                Intent loginIntent = new Intent(this, LoginScreen.class);
                loginIntent.putExtra("From", "NavPage");
                loginIntent.putExtra("LoadNav", 2);
                startActivity(loginIntent);
                finish();

            }
        } else if (id == R.id.nav_search) {
            if (SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID) != null) {
                startActivity(new Intent(this, SearchGigs.class));
            } else {
                Intent loginIntent = new Intent(this, LoginScreen.class);
                loginIntent.putExtra("From", "NavPage");
                loginIntent.putExtra("LoadNav", 3);
                startActivity(loginIntent);
                finish();
            }
        } else if (id == R.id.nav_myactivity) {
            if (SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID) != null) {
                startActivity(new Intent(this, MyActivity.class));
            } else {
                Intent loginIntent = new Intent(this, LoginScreen.class);
                loginIntent.putExtra("From", "NavPage");
                loginIntent.putExtra("LoadNav", 4);
                startActivity(loginIntent);
                finish();
            }
        } else if (id == R.id.nav_myGigs) {
            if (SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID) != null) {
                startActivity(new Intent(this, MyGigs.class));
            } else {
                Intent loginIntent = new Intent(this, LoginScreen.class);
                loginIntent.putExtra("From", "NavPage");
                loginIntent.putExtra("LoadNav", 5);
                startActivity(loginIntent);
                finish();
            }
        } else if (id == R.id.nav_myRequests) {
            if (SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID) != null) {
                startActivity(new Intent(this, BuyerRequestsToBuyerScreen.class));
            } else {
                Intent loginIntent = new Intent(this, LoginScreen.class);
                loginIntent.putExtra("From", "NavPage");
                loginIntent.putExtra("LoadNav", 6);
                startActivity(loginIntent);
                finish();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void callNavIntent(int value) {
        switch (value) {
            case 1:
                startActivity(new Intent(this, Favourites.class));
                break;
            case 2:
                startActivity(new Intent(this, LastVisitedGigs.class));
                break;
            case 3:
                startActivity(new Intent(this, SearchGigs.class));
                break;
            case 4:
                startActivity(new Intent(this, MyActivity.class));
                break;
            case 5:
                startActivity(new Intent(this, MyGigs.class));
                break;
            case 6:
                startActivity(new Intent(this, BuyerRequestsToBuyerScreen.class));
                break;
        }
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction;
        Fragment fragment = null;
        //LoadFragment : 0 - Home, 1- Sell, 2- Buy,3-Chat,4-Settings
        switch (v.getId()) {
            case R.id.ll_home:
                removeHomeHighlight();
                fragment = new HomeFragment();
                this.getSupportActionBar().setTitle(commonStrings.getTitle_home().getName());
                break;
            case R.id.ll_explore:
                if (SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID) != null) {
                    this.getSupportActionBar().setTitle(commonStrings.getTitle_sell_services().getName());
                    fragment = new SellFragment();
                } else {
                    //NetworkAlertDialog.networkAlertDialog(MainActivity.this, "", "Please Login to continue...", null, null);
                    Intent loginIntent = new Intent(this, LoginScreen.class);
                    loginIntent.putExtra("From", "MainPage");
                    loginIntent.putExtra("LoadFragment", 1);
                    startActivity(loginIntent);
                    finish();
                }
                removeExploreHighlight();
                break;
            case R.id.ll_buy:
                if (SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID) != null) {
                    this.getSupportActionBar().setTitle(commonStrings.getTitle_buy_services().getName());
                    fragment = new BuyFragment();
                } else {
                    Intent loginIntent = new Intent(this, LoginScreen.class);
                    loginIntent.putExtra("From", "MainPage");
                    loginIntent.putExtra("LoadFragment", 2);
                    startActivity(loginIntent);
                    finish();
                }
                removeBuyHighlight();
                break;
            case R.id.ll_chat:
                if (SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID) != null) {
                    this.getSupportActionBar().setTitle(commonStrings.getTitle_chat().getName());
                    fragment = new ChatFragment();
                } else {
                    Intent loginIntent = new Intent(this, LoginScreen.class);
                    loginIntent.putExtra("From", "MainPage");
                    loginIntent.putExtra("LoadFragment", 3);
                    startActivity(loginIntent);
                    finish();
                }
                removeChatHighlight();
                break;
            case R.id.ll_settings:
                if (SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID) != null) {
                    this.getSupportActionBar().setTitle(commonStrings.getTitle_settings().getName());
                    fragment = new SettingsFragment();
                } else {
                    Intent loginIntent = new Intent(this, LoginScreen.class);
                    loginIntent.putExtra("From", "MainPage");
                    loginIntent.putExtra("LoadFragment", 4);
                    startActivity(loginIntent);
                    finish();
                }
                removeSettingsHighlight();
                break;
            case R.id.ll_request:
                if (SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID) != null) {
                    this.getSupportActionBar().setTitle(commonStrings.getTitle_buyer_request().getName());
                    fragment = new BuyerRequestFragment();
                } else {
                    //NetworkAlertDialog.networkAlertDialog(MainActivity.this, "", "Please Login to continue...", null, null);
                    Intent loginIntent = new Intent(this, LoginScreen.class);
                    loginIntent.putExtra("From", "MainPage");
                    loginIntent.putExtra("LoadFragment", 5);
                    startActivity(loginIntent);
                    finish();
                }
                removeRequestHighlight();
                break;
            case R.id.ll_offer:
                if (SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID) != null) {
                    this.getSupportActionBar().setTitle(commonStrings.getTitle_seller_offer().getName());
                    fragment = new BuyerRequestsToSellerFragment();
                } else {
                    //NetworkAlertDialog.networkAlertDialog(MainActivity.this, "", "Please Login to continue...", null, null);
                    Intent loginIntent = new Intent(this, LoginScreen.class);
                    loginIntent.putExtra("From", "MainPage");
                    loginIntent.putExtra("LoadFragment", 6);
                    startActivity(loginIntent);
                    finish();
                }
                removeOfferHighlight();
                break;
        }
        if (fragment != null) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_frame_layout, fragment);
            fragmentTransaction.commit();
        }
    }

    private void removeHomeHighlight() {
//        this.getSupportActionBar().setTitle(getString(R.string.title_home));
        txtHome.setTextColor(getResources().getColor(R.color.colorAppTheme));
        ivHome.setBackgroundResource(R.drawable.ic_home_purple_24dp);
        txtExplore.setTextColor(getResources().getColor(android.R.color.black));
        ivExplore.setBackgroundResource(R.drawable.ic_explore_black_24dp);
        txtOffer.setTextColor(getResources().getColor(android.R.color.black));
        ivOffer.setBackgroundResource(R.drawable.offer);
        txtRequest.setTextColor(getResources().getColor(android.R.color.black));
        ivRequest.setBackgroundResource(R.drawable.request);
        txtBuy.setTextColor(getResources().getColor(android.R.color.black));
        ivBuy.setBackgroundResource(R.drawable.ic_add_shopping_cart_black_24dp);
        txtChat.setTextColor(getResources().getColor(android.R.color.black));
        ivChat.setBackgroundResource(R.drawable.ic_chat_black_24dp);
        txtSettings.setTextColor(getResources().getColor(android.R.color.black));
        ivSettings.setBackgroundResource(R.drawable.ic_settings_black_24dp);


//        if(SessionHandler.getInstance().get(this,"locale").equalsIgnoreCase("ar")){
//            this.getSupportActionBar().setTitle("الصفحة الرئيسية");
//            txtHome.setText("الصفحة الرئيسية");
//            txtExplore.setText("يبيع");
//            txtBuy.setText("يشترى");
//            txtChat.setText("دردشة");
//            txtSettings.setText("الإعدادات");
//        }else {
//            this.getSupportActionBar().setTitle(getString(R.string.title_home));
//            txtHome.setText(getResources().getString(R.string.bottom_menu_home));
//            txtExplore.setText(getResources().getString(R.string.bottom_menu_sell));
//            txtBuy.setText(getResources().getString(R.string.bottom_menu_buy));
//            txtChat.setText(getResources().getString(R.string.bottom_menu_chat));
//            txtSettings.setText(getResources().getString(R.string.bottom_menu_settings));
//        }
        this.getSupportActionBar().setTitle(commonStrings.getTitle_home().getName());
        txtHome.setText(commonStrings.getTitle_home().getName());
        txtExplore.setText(tabbarScreen.getLbl_sell().getName());
        txtOffer.setText(tabbarScreen.getLbl_offer().getName());
        txtRequest.setText(tabbarScreen.getLbl_request().getName());
        txtBuy.setText(tabbarScreen.getLbl_buy().getName());
        txtChat.setText(commonStrings.getTitle_chat().getName());
        txtSettings.setText(commonStrings.getTitle_settings().getName());


        ivHome.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAppThemeSecondary)));
        ivExplore.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        ivOffer.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        ivRequest.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        ivBuy.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        ivChat.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        ivSettings.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
    }

    private void removeExploreHighlight() {
        txtExplore.setTextColor(getResources().getColor(R.color.colorAppTheme));
        ivExplore.setBackgroundResource(R.drawable.ic_explore_purple_24dp);
        txtOffer.setTextColor(getResources().getColor(android.R.color.black));
        ivOffer.setBackgroundResource(R.drawable.offer);
        txtRequest.setTextColor(getResources().getColor(android.R.color.black));
        ivRequest.setBackgroundResource(R.drawable.request);
        txtHome.setTextColor(getResources().getColor(android.R.color.black));
        ivHome.setBackgroundResource(R.drawable.ic_home_black_24dp);
        txtBuy.setTextColor(getResources().getColor(android.R.color.black));
        ivBuy.setBackgroundResource(R.drawable.ic_add_shopping_cart_black_24dp);
        txtChat.setTextColor(getResources().getColor(android.R.color.black));
        ivChat.setBackgroundResource(R.drawable.ic_chat_black_24dp);
        txtSettings.setTextColor(getResources().getColor(android.R.color.black));
        ivSettings.setBackgroundResource(R.drawable.ic_settings_black_24dp);

        this.getSupportActionBar().setTitle(commonStrings.getTitle_sell_services().getName());
        ivHome.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        ivExplore.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAppThemeSecondary)));
        ivOffer.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        ivRequest.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        ivBuy.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        ivChat.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        ivSettings.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));

        txtHome.setText(commonStrings.getTitle_home().getName());
        txtExplore.setText(tabbarScreen.getLbl_sell().getName());
        txtOffer.setText(tabbarScreen.getLbl_offer().getName());
        txtRequest.setText(tabbarScreen.getLbl_request().getName());
        txtBuy.setText(tabbarScreen.getLbl_buy().getName());
        txtChat.setText(commonStrings.getTitle_chat().getName());
        txtSettings.setText(commonStrings.getTitle_settings().getName());
    }

    private void removeBuyHighlight() {

        txtBuy.setTextColor(getResources().getColor(R.color.colorAppTheme));
        ivBuy.setBackgroundResource(R.drawable.ic_add_shopping_cart_purple_24dp);
        txtExplore.setTextColor(getResources().getColor(android.R.color.black));
        ivExplore.setBackgroundResource(R.drawable.ic_explore_black_24dp);
        txtOffer.setTextColor(getResources().getColor(android.R.color.black));
        ivOffer.setBackgroundResource(R.drawable.offer);
        txtRequest.setTextColor(getResources().getColor(android.R.color.black));
        ivRequest.setBackgroundResource(R.drawable.request);
        txtHome.setTextColor(getResources().getColor(android.R.color.black));
        ivHome.setBackgroundResource(R.drawable.ic_home_black_24dp);
        txtChat.setTextColor(getResources().getColor(android.R.color.black));
        ivChat.setBackgroundResource(R.drawable.ic_chat_black_24dp);
        txtSettings.setTextColor(getResources().getColor(android.R.color.black));
        ivSettings.setBackgroundResource(R.drawable.ic_settings_black_24dp);

        ivHome.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        ivExplore.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        ivOffer.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        ivRequest.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        ivBuy.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAppThemeSecondary)));
        ivChat.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        ivSettings.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));

        this.getSupportActionBar().setTitle(commonStrings.getTitle_buy_services().getName());
        txtHome.setText(commonStrings.getTitle_home().getName());
        txtExplore.setText(tabbarScreen.getLbl_sell().getName());
        txtOffer.setText(tabbarScreen.getLbl_offer().getName());
        txtRequest.setText(tabbarScreen.getLbl_request().getName());
        txtBuy.setText(tabbarScreen.getLbl_buy().getName());
        txtChat.setText(commonStrings.getTitle_chat().getName());
        txtSettings.setText(commonStrings.getTitle_settings().getName());
    }

    private void removeOfferHighlight() {

        txtOffer.setTextColor(getResources().getColor(R.color.colorAppTheme));
        ivOffer.setBackgroundResource(R.drawable.offer);
        txtExplore.setTextColor(getResources().getColor(android.R.color.black));
        ivExplore.setBackgroundResource(R.drawable.ic_explore_black_24dp);
        txtSettings.setTextColor(getResources().getColor(android.R.color.black));
        ivSettings.setBackgroundResource(R.drawable.ic_settings_black_24dp);
        txtRequest.setTextColor(getResources().getColor(android.R.color.black));
        ivRequest.setBackgroundResource(R.drawable.request);
        txtBuy.setTextColor(getResources().getColor(android.R.color.black));
        ivBuy.setBackgroundResource(R.drawable.ic_add_shopping_cart_black_24dp);
        txtHome.setTextColor(getResources().getColor(android.R.color.black));
        ivHome.setBackgroundResource(R.drawable.ic_home_black_24dp);
        txtChat.setTextColor(getResources().getColor(android.R.color.black));
        ivChat.setBackgroundResource(R.drawable.ic_chat_black_24dp);

        ivHome.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        ivExplore.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        ivSettings.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        ivRequest.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        ivBuy.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        ivChat.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        ivOffer.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAppThemeSecondary)));

        this.getSupportActionBar().setTitle(commonStrings.getTitle_seller_offer().getName());
        txtHome.setText(commonStrings.getTitle_home().getName());
        txtExplore.setText(tabbarScreen.getLbl_sell().getName());
        txtOffer.setText(tabbarScreen.getLbl_offer().getName());
        txtRequest.setText(tabbarScreen.getLbl_request().getName());
        txtBuy.setText(tabbarScreen.getLbl_buy().getName());
        txtChat.setText(commonStrings.getTitle_chat().getName());
        txtSettings.setText(commonStrings.getTitle_settings().getName());
    }

    private void removeRequestHighlight() {

        txtRequest.setTextColor(getResources().getColor(R.color.colorAppTheme));
        ivRequest.setBackgroundResource(R.drawable.offer);
        txtExplore.setTextColor(getResources().getColor(android.R.color.black));
        ivExplore.setBackgroundResource(R.drawable.ic_explore_black_24dp);
        txtSettings.setTextColor(getResources().getColor(android.R.color.black));
        ivSettings.setBackgroundResource(R.drawable.ic_settings_black_24dp);
        txtOffer.setTextColor(getResources().getColor(android.R.color.black));
        ivRequest.setBackgroundResource(R.drawable.request);
        txtBuy.setTextColor(getResources().getColor(android.R.color.black));
        ivBuy.setBackgroundResource(R.drawable.ic_add_shopping_cart_black_24dp);
        txtHome.setTextColor(getResources().getColor(android.R.color.black));
        ivHome.setBackgroundResource(R.drawable.ic_home_black_24dp);
        txtChat.setTextColor(getResources().getColor(android.R.color.black));
        ivChat.setBackgroundResource(R.drawable.ic_chat_black_24dp);

        ivHome.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        ivExplore.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        ivSettings.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        ivOffer.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        ivBuy.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        ivChat.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        ivRequest.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAppThemeSecondary)));

        this.getSupportActionBar().setTitle(commonStrings.getTitle_buyer_request().getName());
        txtHome.setText(commonStrings.getTitle_home().getName());
        txtExplore.setText(tabbarScreen.getLbl_sell().getName());
        txtOffer.setText(tabbarScreen.getLbl_offer().getName());
        txtRequest.setText(tabbarScreen.getLbl_request().getName());
        txtBuy.setText(tabbarScreen.getLbl_buy().getName());
        txtChat.setText(commonStrings.getTitle_chat().getName());
        txtSettings.setText(commonStrings.getTitle_settings().getName());
    }

    private void removeChatHighlight() {

        txtChat.setTextColor(getResources().getColor(R.color.colorAppTheme));
        ivChat.setBackgroundResource(R.drawable.ic_chat_purple_24dp);
        txtExplore.setTextColor(getResources().getColor(android.R.color.black));
        ivExplore.setBackgroundResource(R.drawable.ic_explore_black_24dp);
        txtOffer.setTextColor(getResources().getColor(android.R.color.black));
        ivOffer.setBackgroundResource(R.drawable.offer);
        txtRequest.setTextColor(getResources().getColor(android.R.color.black));
        ivRequest.setBackgroundResource(R.drawable.request);
        txtBuy.setTextColor(getResources().getColor(android.R.color.black));
        ivBuy.setBackgroundResource(R.drawable.ic_add_shopping_cart_black_24dp);
        txtHome.setTextColor(getResources().getColor(android.R.color.black));
        ivHome.setBackgroundResource(R.drawable.ic_home_black_24dp);
        txtSettings.setTextColor(getResources().getColor(android.R.color.black));
        ivSettings.setBackgroundResource(R.drawable.ic_settings_black_24dp);

        ivHome.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        ivExplore.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        ivOffer.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        ivRequest.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        ivBuy.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        ivChat.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAppThemeSecondary)));
        ivSettings.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));

        this.getSupportActionBar().setTitle(commonStrings.getTitle_chat().getName());
        txtHome.setText(commonStrings.getTitle_home().getName());
        txtOffer.setText(tabbarScreen.getLbl_offer().getName());
        txtRequest.setText(tabbarScreen.getLbl_request().getName());
        txtExplore.setText(tabbarScreen.getLbl_sell().getName());
        txtBuy.setText(tabbarScreen.getLbl_buy().getName());
        txtChat.setText(commonStrings.getTitle_chat().getName());
        txtSettings.setText(commonStrings.getTitle_settings().getName());
    }

    private void removeSettingsHighlight() {

        txtSettings.setTextColor(getResources().getColor(R.color.colorAppTheme));
        ivSettings.setBackgroundResource(R.drawable.ic_settings_purple_24dp);
        txtExplore.setTextColor(getResources().getColor(android.R.color.black));
        ivExplore.setBackgroundResource(R.drawable.ic_explore_black_24dp);
        txtOffer.setTextColor(getResources().getColor(android.R.color.black));
        ivOffer.setBackgroundResource(R.drawable.offer);
        txtRequest.setTextColor(getResources().getColor(android.R.color.black));
        ivRequest.setBackgroundResource(R.drawable.request);
        txtBuy.setTextColor(getResources().getColor(android.R.color.black));
        ivBuy.setBackgroundResource(R.drawable.ic_add_shopping_cart_black_24dp);
        txtHome.setTextColor(getResources().getColor(android.R.color.black));
        ivHome.setBackgroundResource(R.drawable.ic_home_black_24dp);
        txtChat.setTextColor(getResources().getColor(android.R.color.black));
        ivChat.setBackgroundResource(R.drawable.ic_chat_black_24dp);

        ivHome.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        ivExplore.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        ivOffer.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        ivRequest.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        ivBuy.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        ivChat.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(android.R.color.black)));
        ivSettings.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAppThemeSecondary)));

        this.getSupportActionBar().setTitle(commonStrings.getTitle_settings().getName());
        txtHome.setText(commonStrings.getTitle_home().getName());
        txtExplore.setText(tabbarScreen.getLbl_sell().getName());
        txtOffer.setText(tabbarScreen.getLbl_offer().getName());
        txtRequest.setText(tabbarScreen.getLbl_request().getName());
        txtBuy.setText(tabbarScreen.getLbl_buy().getName());
        txtChat.setText(commonStrings.getTitle_chat().getName());
        txtSettings.setText(commonStrings.getTitle_settings().getName());
    }

    public void setLanguageValues() {
        commonStrings = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.COMMONSTRINGS), POSTLanguageModel.Common_strings.class);
        tabbarScreen = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.TABBAR), POSTLanguageModel.Tabbar_screen.class);
        navigationScreen = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.NAVSCREEN), POSTLanguageModel.Navigation_screen.class);
        txtHome.setText(commonStrings.getTitle_home().getName());
        txtExplore.setText(tabbarScreen.getLbl_sell().getName());
        txtBuy.setText(tabbarScreen.getLbl_buy().getName());
        txtChat.setText(commonStrings.getTitle_chat().getName());
        txtSettings.setText(commonStrings.getTitle_settings().getName());
        userType = SessionHandler.getInstance().get(MainActivity.this, AppConstants.USER_TYPE);
    }
}