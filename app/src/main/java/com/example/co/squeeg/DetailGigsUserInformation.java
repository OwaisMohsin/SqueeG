package com.example.co.squeeg;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;


import org.apache.http.HttpException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import com.example.co.squeeg.Model.GETProfession;
import com.example.co.squeeg.Model.POSTDetailGig;
import com.example.co.squeeg.Model.POSTLanguageModel;
import com.example.co.squeeg.Model.POSTSellerReviews;
import com.example.co.squeeg.adapter.SellerReviewsGigsAdpater;
import com.example.co.squeeg.dialog.ChatCommentBox;
import com.example.co.squeeg.fragment.DetailReviewGigsFragment;
import com.example.co.squeeg.network.ApiClient;
import com.example.co.squeeg.network.ApiInterface;
import com.example.co.squeeg.receiver.NetworkChangeReceiver;
import com.example.co.squeeg.utils.AppConstants;
import com.example.co.squeeg.utils.CustomProgressDialog;
import com.example.co.squeeg.utils.CustomTextView;
import com.example.co.squeeg.utils.NetworkAlertDialog;
import com.example.co.squeeg.utils.SessionHandler;
import com.example.co.squeeg.utils.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by user5 on 06-11-2017.
 */

public class DetailGigsUserInformation extends BaseActivity {


    private TabLayout tabLayout;
    /*private CustomViewPager viewPager;*/
    private LinearLayout inputGigsUserCount, inputGigsTotalViews, inputGigsUserCountry, inputGigsUserSpeaks;
    TextView toolTitle, username, gigsTitle, userprofession, gigsDesc, gigsNos, gigsCountry, gigsUserCount, gigsSpeaks;
    HashMap<String, String> postGigDetails = new HashMap<String, String>();
    private Gson gson;
    private GETProfession[] getProfession;
    private RatingBar gigsRating;
    private Button inputContact;
    private CircleImageView profileUserImage;
    private String gigs_id, gigsUser_id;
    Toolbar toolbar;
    ChatCommentBox mChatCommentBox;
    String seller_user_id = "";
    public String token;
    private ListView mDetailGigsReview;
    CustomProgressDialog mCustomProgressDialog;
    List<POSTSellerReviews.Review_detail> mData = new ArrayList<POSTSellerReviews.Review_detail>();
    private int total_page, page_num = 1;
    TextView noReviews;
    HashMap<String, String> postSellerReviews = new HashMap<String, String>();
    SellerReviewsGigsAdpater sellerReviewsGigsAdpater;
    CustomTextView ctvCountry, ctvSpeaks, ctvUserInfo, ctvReviews;
    public POSTLanguageModel.Detail_gigs detail_gigs = new POSTLanguageModel().new Detail_gigs();
    public POSTLanguageModel.Common_strings commonStrings = new POSTLanguageModel().new Common_strings();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_gigs_user_information);
        initLayouts();
        setLanguageValues();
        Utils.freeMemory();
        gson = new Gson();
        getProfession = gson.fromJson(SessionHandler.getInstance().get(DetailGigsUserInformation.this, AppConstants.PROFESSION), GETProfession[].class);
        gigs_id = getIntent().getStringExtra(AppConstants.GIGS_ID);
        gigsUser_id = getIntent().getStringExtra(AppConstants.GigUserID);
        //viewPager = (CustomViewPager) findViewById(R.id.CustomviewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        //tabLayout.setupWithViewPager(viewPager);

        if (SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID).equalsIgnoreCase(gigsUser_id)) {
            inputContact.setVisibility(View.GONE);
        }


        /*viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewPager.reMeasureCurrentPage(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });*/

        if (NetworkChangeReceiver.isConnected()) {
            if (SessionHandler.getInstance().get(DetailGigsUserInformation.this, AppConstants.TOKEN_ID) != null) {
                postGigDetails.put("userid", gigsUser_id);
                token = SessionHandler.getInstance().get(DetailGigsUserInformation.this, AppConstants.TOKEN_ID);
            } else {
                token = AppConstants.GUEST_TOKEN;
                postGigDetails.put("userid", "");
            }
            postGigDetails.put("gig_id", gigs_id);
            getUserDetails();
        } else {
            Utils.toastMessage(DetailGigsUserInformation.this, getString(R.string.err_internet_connection));
        }
    }

    private void setUpViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new DetailReviewGigsFragment(DetailGigsUserInformation.this, seller_user_id), "Reviews");
        viewPager.setAdapter(adapter);
    }

    private void getUserDetails() {
        mCustomProgressDialog.showDialog();
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.getDetailGigs(postGigDetails, token, SessionHandler.getInstance().get(this, AppConstants.Language)).enqueue(new Callback<POSTDetailGig>() {
            @Override
            public void onResponse(Call<POSTDetailGig> call, Response<POSTDetailGig> response) {
                if (response.body().getCode().equals(200)) {
                    if (response.body().getData().size() > 0) {
                        seller_user_id = response.body().getData().get(0).getGigs_details().getUser_id();
                        //setUpViewPager(viewPager);
                        POSTDetailGig.Gigs_details gigs_details = response.body().getData().get(0).getGigs_details();
                        Picasso.get().load(AppConstants.BASE_URL + gigs_details.getUser_thumb_image()).placeholder(R.drawable.ic_no_image_100).into(profileUserImage);
                        //profileUserImage.setImageURI(Uri.parse(Utils.BASEURL + gigs_details.getUser_thumb_image()));

                        username.setText(gigs_details.getFullname());
                        toolTitle.setText(gigs_details.getFullname());
                        if (getProfession != null) {
                            for (int i = 0; i < getProfession.length; i++) {
                                if (getProfession[i].getId().equalsIgnoreCase(gigs_details.getProfession())) {
                                    userprofession.setText(getProfession[i].getProfession_name());
                                }
                            }
                        }

                        if (gigs_details.getCountry() != null && !gigs_details.getCountry().isEmpty()) {
                            gigsCountry.setText(gigs_details.getCountry());
                        } else {
                            inputGigsUserCountry.setVisibility(View.GONE);
                        }
                        /*if (gigs_details.getTotal_views() != null && !gigs_details.getTotal_views().isEmpty()) {
                            gigsNos.setText(gigs_details.getTotal_views());
                        } else {
                            inputGigsTotalViews.setVisibility(View.GONE);
                        }
                        if (gigs_details.getGig_usercount() != null && !gigs_details.getGig_usercount().isEmpty()) {
                            gigsUserCount.setText(gigs_details.getGig_usercount());
                        } else {
                            inputGigsUserCount.setVisibility(View.GONE);
                        }*/
                        if (gigs_details.getLang_speaks() != null && !gigs_details.getLang_speaks().isEmpty()) {
                            gigsSpeaks.setText(gigs_details.getLang_speaks().substring(0, gigs_details.getLang_speaks().length() - 1));
                        } else {
                            inputGigsUserSpeaks.setVisibility(View.GONE);
                        }
                        gigsRating.setRating(Float.parseFloat(gigs_details.getGig_rating()));
                        getSellerReviews();

                    } else {
                        Utils.toastMessage(DetailGigsUserInformation.this, response.body().getMessage());
                    }

                } else if (response.body().getCode().equals(Integer.parseInt(AppConstants.INVALID_RESPONSE_CODE))) {
                    NetworkAlertDialog.networkAlertDialog(DetailGigsUserInformation.this, "", response.body().getMessage(), null, null);
                } else if (response.body().getCode().equals(AppConstants.InactiveStatusResponse)) {
                    Utils.createUserInActiceAlert(DetailGigsUserInformation.this, response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<POSTDetailGig> call, Throwable t) {
                Log.i("TAG", t.getMessage());
                mCustomProgressDialog.dismiss();
                if (t instanceof UnknownHostException || t instanceof HttpException || t instanceof ConnectException || t instanceof SocketTimeoutException || t instanceof SocketException || t instanceof IOException) {
                    //Toast.makeText(DetailGigsUserInformation.this, getString(R.string.err_server_msg), Toast.LENGTH_SHORT).show();
                    NetworkAlertDialog.networkAlertDialog(DetailGigsUserInformation.this, getResources().getString(R.string.err_network_error),
                            getResources().getString(R.string.err_try_again), detailGigsInfoRunn, null);
                }
            }
        });
    }

    Runnable detailGigsInfoRunn = new Runnable() {
        @Override
        public void run() {
            //TODO: API call
            getUserDetails();
        }
    };

    public void openChatCommentBox(View view) {
        mChatCommentBox = new ChatCommentBox(this, seller_user_id);
        mChatCommentBox.show(getSupportFragmentManager(), getResources().getString(R.string.txt_show_comment_box));
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    private void initLayouts() {
        toolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        setSupportActionBar(toolbar);
        toolTitle = (TextView) findViewById(R.id.tv_gigs_title);
        username = (TextView) findViewById(R.id.tv_gigs_username);
        userprofession = (TextView) findViewById(R.id.tv_gigs_profession);
        inputContact = (Button) findViewById(R.id.btn_order_now);
        gigsNos = (TextView) findViewById(R.id.gigs_nos_views);
        gigsCountry = (TextView) findViewById(R.id.country);
        gigsUserCount = (TextView) findViewById(R.id.gigs_user_count);
        gigsSpeaks = (TextView) findViewById(R.id.tv_user_speaks);
        gigsRating = (RatingBar) findViewById(R.id.rating_gigs);
        inputGigsUserCount = (LinearLayout) findViewById(R.id.ll_gigs_user_count);
        inputGigsTotalViews = (LinearLayout) findViewById(R.id.ll_gigs_total_views);
        inputGigsUserCountry = (LinearLayout) findViewById(R.id.ll_gigs_user_country);
        inputGigsUserSpeaks = (LinearLayout) findViewById(R.id.ll_gigs_user_speaks);
        profileUserImage = (CircleImageView) findViewById(R.id.iv_profile_image);
        mCustomProgressDialog = new CustomProgressDialog(this);
        mDetailGigsReview = (ListView) findViewById(R.id.lv_review_gigs);
        noReviews = (TextView) findViewById(R.id.tv_no_reviews);
        ctvSpeaks = (CustomTextView) findViewById(R.id.ctv_speaks);
        ctvCountry = (CustomTextView) findViewById(R.id.ctv_country);
        ctvUserInfo = (CustomTextView) findViewById(R.id.ctv_user_info);
        ctvReviews = (CustomTextView) findViewById(R.id.ctv_reviews);
        LinearLayoutManager recentPopularGigsLayoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    private void getSellerReviews() {
        if (NetworkChangeReceiver.isConnected()) {
            if (SessionHandler.getInstance().get(DetailGigsUserInformation.this, AppConstants.TOKEN_ID) != null) {
                postSellerReviews.put("user_id", seller_user_id);
                //postSellerReviews.put("user_id", SessionHandler.getInstance().get(mContext, AppConstants.GigUserID));

            } else {
                postGigDetails.put("userid", "");
            }
            postGigDetails.put("gig_id", gigs_id);
            postSellerReviews.put("device_type", AppConstants.DeviceType);
            postSellerReviews.put("page", String.valueOf(page_num));
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            apiInterface.getSellerReviews(postSellerReviews, SessionHandler.getInstance().get(DetailGigsUserInformation.this, AppConstants.TOKEN_ID), SessionHandler.getInstance().get(this, AppConstants.Language)).enqueue(new Callback<POSTSellerReviews>() {
                @Override
                public void onResponse(Call<POSTSellerReviews> call, Response<POSTSellerReviews> response) {
                    mCustomProgressDialog.dismiss();
                    if (response.body().getCode().equals(200)) {
                        if (response.body().getData().getReview_details().size() > 0) {
                            total_page = response.body().getData().getTotal_pages();
                            if (mData.size() > 0) {
                                mData.addAll(response.body().getData().getReview_details());
                                sellerReviewsGigsAdpater.notifyDataSetChanged();
                            } else {
                                mData.addAll(response.body().getData().getReview_details());
                                sellerReviewsGigsAdpater = new SellerReviewsGigsAdpater(DetailGigsUserInformation.this, mData);
                                mDetailGigsReview.setAdapter(sellerReviewsGigsAdpater);
                            }
                            mCustomProgressDialog.dismiss();
                            Utils.getListViewSize(mDetailGigsReview);
                        } else {
                            mDetailGigsReview.setVisibility(View.GONE);
                            noReviews.setVisibility(View.VISIBLE);
                            mCustomProgressDialog.dismiss();
                        }
                    } else if (response.body().getCode().equals(AppConstants.INVALID_RESPONSE_CODE)) {
                        mCustomProgressDialog.dismiss();
                        NetworkAlertDialog.networkAlertDialog(DetailGigsUserInformation.this, "", response.body().getMessage(), null, null);
                    } else if (response.body().getCode().equals(AppConstants.InactiveStatusResponse)) {
                        Utils.createUserInActiceAlert(DetailGigsUserInformation.this, response.body().getMessage());
                    }


                }

                @Override
                public void onFailure(Call<POSTSellerReviews> call, Throwable t) {
                    Log.i("TAG", t.getMessage());
                    mCustomProgressDialog.dismiss();
                    if (t instanceof UnknownHostException || t instanceof HttpException || t instanceof ConnectException || t instanceof SocketTimeoutException || t instanceof SocketException || t instanceof IOException) {
                        Toast.makeText(DetailGigsUserInformation.this, commonStrings.getLbl_server_prblm().getName(), Toast.LENGTH_SHORT).show();
                    }
                }
            });

        } else {
            Utils.toastMessage(DetailGigsUserInformation.this, commonStrings.getLbl_enable_internet().getName());
        }

    }

    public void setLanguageValues() {
        detail_gigs = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.DETAILGIGS), POSTLanguageModel.Detail_gigs.class);
        commonStrings = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.COMMONSTRINGS), POSTLanguageModel.Common_strings.class);
        ctvSpeaks.setText(detail_gigs.getLbl_speaks().getName());
        ctvReviews.setText(detail_gigs.getLbl_reviews().getName());
        ctvUserInfo.setText(detail_gigs.getLbl_user_info().getName());
        ctvSpeaks.setText(detail_gigs.getLbl_speaks().getName());
        ctvCountry.setText(detail_gigs.getLbl_country().getName());
        inputContact.setText(detail_gigs.getBtn_contact().getName());
    }
}
