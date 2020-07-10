package com.example.co.squeeg.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.gson.Gson;

import org.apache.http.HttpException;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.example.co.squeeg.CategoryList;
import com.example.co.squeeg.DetailGigs;
import com.example.co.squeeg.GigsLists;
import com.example.co.squeeg.Model.GETHomeGigs;
import com.example.co.squeeg.Model.POSTLanguageModel;
import com.example.co.squeeg.R;
import com.example.co.squeeg.SearchGigsList;
import com.example.co.squeeg.adapter.HorizontalPopularGigsAdapter;
import com.example.co.squeeg.adapter.HorizontalRecentPopularGigsAdapter;
import com.example.co.squeeg.adapter.HorizontalTopCategoryAdapter;
import com.example.co.squeeg.network.ApiClient;
import com.example.co.squeeg.network.ApiInterface;
import com.example.co.squeeg.receiver.NetworkChangeReceiver;
import com.example.co.squeeg.utils.AppConstants;
import com.example.co.squeeg.utils.CustomEditext;
import com.example.co.squeeg.utils.CustomProgressDialog;
import com.example.co.squeeg.utils.NetworkAlertDialog;
import com.example.co.squeeg.utils.SessionHandler;
import com.example.co.squeeg.utils.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.INPUT_METHOD_SERVICE;

public class HomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.sl_tv_top_category)
    TextView slTvTopCategory;
    @BindView(R.id.sl_tv_popular_gigs)
    TextView slTvPopularGigs;
    @BindView(R.id.sl_tv_latest_gigs)
    TextView slTvLatestGigs;
    Unbinder unbinder;
    @BindView(R.id.cet_search)
    CustomEditext cetSearch;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private ViewPager mViewPager;
    private View mView;
    private CustomProgressDialog mCustomProgressDialog;
    private final long DELAY_MS = 1000;//delay in milliseconds before task is to be executed
    private final long PERIOD_MS = 5000; // time in milliseconds between successive task executions.
    private int currentPage = 0;
    private Timer timer;
    private PopularGigsImageAdapter aPopularGigsImageAdapter;
    private RecyclerView horizontalCategories;
    private RecyclerView horizontalPopularGigs;
    private RecyclerView horizontalRecentGigs;
    private ShimmerFrameLayout mShimmerViewContainer;
    private TextView categoryMore;
    private TextView popularGigsMore;
    private TextView recentGigsMore, text_no_gigs_found, tvTopCategory, tvLatestGigs, tvPopularGigs;
    private String user_id = "";
    HorizontalPopularGigsAdapter aHorizontalPopularGigsAdapter;
    HorizontalRecentPopularGigsAdapter aHorizontalRecentPopularGigsAdapter;
    private RelativeLayout inputTopCategories, inputRecentGigs, inputPopularGigs;
    LinearLayout homeLayout;
    //    CardView inputTopCategory;
    private NestedScrollView homeGigs;
    boolean isLoading = false;

    private AdView mAdView;
    private ImageView input_noAdvertisment;
    public POSTLanguageModel.Home_screen homeScreen = new POSTLanguageModel().new Home_screen();
    public POSTLanguageModel.Common_strings commonStrings = new POSTLanguageModel().new Common_strings();

    public HomeFragment() {

    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        Utils.freeMemory();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_home, container, false);

        initLayouts(mView);
        unbinder = ButterKnife.bind(this, mView);
        setLanguageValues();
        mAdView.setVisibility(View.GONE);
        String android_id = Settings.Secure.getString(getContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        MobileAds.initialize(getActivity(), getResources().getString(R.string.admob_app_id));
        AdRequest request = new AdRequest.Builder()
                .build();
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                input_noAdvertisment.setVisibility(View.GONE);//:TODO
                mAdView.setVisibility(View.GONE);

            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                input_noAdvertisment.setVisibility(View.GONE);//:TODO
                mAdView.setVisibility(View.GONE);
            }
        });

        cetSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    try {
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
                    } catch (Exception e) {
                        // TODO: handle exception
                    }
                    if (!cetSearch.getText().toString().trim().isEmpty()) {
                        Intent callSearchActivity = new Intent(getActivity(), SearchGigsList.class);
                        callSearchActivity.putExtra("title", cetSearch.getText().toString().trim());
                        cetSearch.setText("");
                        startActivity(callSearchActivity);
                    }


                }
                return false;
            }
        });

        mAdView.loadAd(request);

        mCustomProgressDialog = new CustomProgressDialog(getActivity());
        if (SessionHandler.getInstance().get(getActivity(), AppConstants.TOKEN_ID) != null) {
            user_id = SessionHandler.getInstance().get(getActivity(), AppConstants.TOKEN_ID);
        } else {
            user_id = AppConstants.GUEST_TOKEN;
        }

        categoryMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CategoryList.class));
            }
        });

        popularGigsMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callGigsList = new Intent(getActivity(), GigsLists.class);
                callGigsList.putExtra(AppConstants.CAT_NAME, homeScreen.getLbl_popular_gigs().getName());
                startActivity(callGigsList);

            }
        });

        recentGigsMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callGigsList = new Intent(getActivity(), GigsLists.class);
                callGigsList.putExtra(AppConstants.CAT_NAME, homeScreen.getLbl_latest_gigs().getName());
                startActivity(callGigsList);
            }
        });


        return mView;
    }

    private void initLayouts(View mView) {
        mViewPager = (ViewPager) mView.findViewById(R.id.vp_popular_gigs);
        horizontalCategories = (RecyclerView) mView.findViewById(R.id.horizontal_categories);
        horizontalPopularGigs = (RecyclerView) mView.findViewById(R.id.horizontal_popular_gigs);
        horizontalRecentGigs = (RecyclerView) mView.findViewById(R.id.horizontal_recent_popular_gigs);
        categoryMore = (TextView) mView.findViewById(R.id.tv_category_more);
        popularGigsMore = (TextView) mView.findViewById(R.id.tv_popular_gigs_more);
        recentGigsMore = (TextView) mView.findViewById(R.id.tv_recent_gigs_more);
        homeLayout = (LinearLayout) mView.findViewById(R.id.ll_homeFragment);
        inputRecentGigs = (RelativeLayout) mView.findViewById(R.id.rl_recent_gigs);
        inputPopularGigs = (RelativeLayout) mView.findViewById(R.id.rl_popular_gigs);
        inputTopCategories = (RelativeLayout) mView.findViewById(R.id.rl_top_category);
        input_noAdvertisment = (ImageView) mView.findViewById(R.id.iv_no_ad);
        mAdView = (AdView) mView.findViewById(R.id.adView);
//        inputTopCategory = (CardView) mView.findViewById(R.id.cv_top_category);
        text_no_gigs_found = (TextView) mView.findViewById(R.id.tv_no_gigs_found);
        homeGigs = (NestedScrollView) mView.findViewById(R.id.nsv_home_gigs);
        mShimmerViewContainer = (ShimmerFrameLayout) mView.findViewById(R.id.shimmer_view_container);
        tvLatestGigs = (TextView) mView.findViewById(R.id.tv_latest_gigs);
        tvPopularGigs = (TextView) mView.findViewById(R.id.tv_popular_gigs);
        tvTopCategory = (TextView) mView.findViewById(R.id.tv_top_category);

        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        horizontalCategories.setLayoutManager(horizontalLayoutManagaer);
        LinearLayoutManager popularGigsLayoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        horizontalPopularGigs.setLayoutManager(popularGigsLayoutManager);
        LinearLayoutManager recentPopularGigsLayoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        horizontalRecentGigs.setLayoutManager(recentPopularGigsLayoutManager);
    }

    private void setViewpagerAutoSlide() {
        /*After setting the adapter use the timer */
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == (aPopularGigsImageAdapter.getCount() + 1) - 1) {
                    currentPage = 0;
                }
                mViewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled

            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);
    }


    private void GETHomegigs() {
        mShimmerViewContainer.startShimmerAnimation();
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.getHomeGigs(user_id, SessionHandler.getInstance().get(getActivity(), AppConstants.Language))
                .enqueue(new Callback<GETHomeGigs>() {
            @Override
            public void onResponse(Call<GETHomeGigs> call, Response<GETHomeGigs> response) {

                if (response.isSuccessful()) {
                    if (response.body().getCode().equals(200)) {
                        isLoading = false;
                        mShimmerViewContainer.stopShimmerAnimation();
                        mShimmerViewContainer.setVisibility(View.GONE);

                        homeGigs.setVisibility(View.VISIBLE);
//
                        //mCustomProgressDialog.dismiss();
                        if (response.body().getPrimary().size() > 0) {
                            for (GETHomeGigs.Primary primary : response.body().getPrimary()) {
                                AppConstants.BASE_URL = primary.getBase_url();
                                if (primary.getPopular_gigs_list().size() == 0 && primary.getRecent_gigs_list().size() == 0 && primary.getCategories().size() == 0) {
                                    homeGigs.setVisibility(View.GONE);
                                    text_no_gigs_found.setVisibility(View.VISIBLE);
                                } else {
                                    if (primary.getPopular_gigs_image().size() > 0) {
                                        if (getActivity() != null) {
                                            aPopularGigsImageAdapter = new PopularGigsImageAdapter(getActivity(), primary.getPopular_gigs_image());
                                            mViewPager.setAdapter(aPopularGigsImageAdapter);
//                                        setViewpagerAutoSlide();
                                        }
                                    }
                                    if (primary.getCategories().size() > 0) {
                                        HorizontalTopCategoryAdapter aHorizontalTopCategoryAdapter = new HorizontalTopCategoryAdapter(getActivity(), primary.getCategories());
                                        horizontalCategories.setAdapter(aHorizontalTopCategoryAdapter);
                                    } else {
//                                        inputTopCategory.setVisibility(View.GONE);
                                        inputTopCategories.setVisibility(View.GONE);
                                    }

                                    if (primary.getPopular_gigs_list().size() > 0) {
                                        aHorizontalPopularGigsAdapter = new HorizontalPopularGigsAdapter(getActivity(), primary.getPopular_gigs_list());
                                        horizontalPopularGigs.setAdapter(aHorizontalPopularGigsAdapter);
                                    } else {
                                        inputPopularGigs.setVisibility(View.GONE);
                                    }
                                    if (primary.getRecent_gigs_list().size() > 0) {
                                        aHorizontalRecentPopularGigsAdapter = new HorizontalRecentPopularGigsAdapter(getActivity(), primary.getRecent_gigs_list());
                                        horizontalRecentGigs.setAdapter(aHorizontalRecentPopularGigsAdapter);
                                    } else {
                                        inputRecentGigs.setVisibility(View.GONE);
                                    }
                                }
                            }

                        } else {
//                            Toast.makeText(getActivity(), "No primary data found...", Toast.LENGTH_SHORT).show();
                        }

                    } else if (response.body().getCode().equals(AppConstants.InactiveStatusResponse)) {
                        //mCustomProgressDialog.dismiss();
                        Utils.createUserInActiceAlert(getActivity(), response.body().getMessage());
                    } else if (response.body().getCode().equals(AppConstants.INVALID_RESPONSE_CODE)) {
                        //mCustomProgressDialog.dismiss();
                        NetworkAlertDialog.networkAlertDialog(getActivity(), "", response.body().getMessage(), null, null);
                    } else {
                        //mCustomProgressDialog.dismiss();
                        NetworkAlertDialog.networkAlertDialog(getActivity(), "",
                                response.body().getMessage(), null, null);
                    }
                } else {
                    //mCustomProgressDialog.dismiss();
                    Log.i("ERROR_HOME_FRAGMENT", response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<GETHomeGigs> call, Throwable t) {
                Log.i("TAG", t.getMessage());
                //mCustomProgressDialog.dismiss();
                try {
                    if (t instanceof UnknownHostException || t instanceof HttpException || t instanceof ConnectException || t instanceof SocketTimeoutException || t instanceof SocketException || t instanceof IOException) {
                        //Toast.makeText(getActivity(), getString(R.string.err_server_msg), Toast.LENGTH_SHORT).show();
                        NetworkAlertDialog.networkAlertDialog(getActivity(), commonStrings.getLbl_network_err().getName(),
                                commonStrings.getLbl_server_prblm().getName(), homegigsRunn, null);
                    }
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    Runnable homegigsRunn = new Runnable() {
        @Override
        public void run() {
            //TODO: API call
            //mCustomProgressDialog.showDialog();
            GETHomegigs();
        }
    };

    @Override
    public void onResume() {
        super.onResume();

        if (NetworkChangeReceiver.isConnected()) {
            if (!isLoading) {
                //mCustomProgressDialog.showDialog();
                GETHomegigs();
                isLoading = true;
            }
        } else {
            showToast();
        }
    }

    private void setViewpagerVisibliity() {
        mViewPager.setVisibility(View.GONE);
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
        Utils.setLanguageLocale(getActivity(), SessionHandler.getInstance().get(getActivity(), "locale"));
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private class PopularGigsImageAdapter extends PagerAdapter {
        final Context mContext;
        final List<GETHomeGigs.Popular_gigs_image> popular_gigs_image;
        final LayoutInflater mInflater;

        PopularGigsImageAdapter(Context context, List<GETHomeGigs.Popular_gigs_image> popular_gigs_image) {
            this.mContext = context;
            this.popular_gigs_image = popular_gigs_image;
            mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return popular_gigs_image.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {

            View innerImageLayer = mInflater.inflate(R.layout.adapter_popular_gigs_slider, null);
            final GETHomeGigs.Popular_gigs_image popular_gigs_imagelist = popular_gigs_image.get(position);

            ImageView gigsImages = (ImageView) innerImageLayer.findViewById(R.id.iv_popular_gigs_image);
            TextView textCount = (TextView) innerImageLayer.findViewById(R.id.tv_page_count);
            //Picasso.with(mContext).load(AppConstants.BASE_URL + popular_gigs_imagelist.getImage()).placeholder(R.drawable.no_image).into(gigsImages);
            textCount.setText((position + 1) + "/" + (getCount()));
            innerImageLayer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent callGigsDetails = new Intent(mContext, DetailGigs.class);
                    callGigsDetails.putExtra(AppConstants.USER_TYPE,"" );
                    callGigsDetails.putExtra(AppConstants.GIGS_ID, popular_gigs_imagelist.getId());
                    callGigsDetails.putExtra(AppConstants.GIGS_TITLE, popular_gigs_imagelist.getTitle());
                    mContext.startActivity(callGigsDetails);
                }
            });
            container.addView(innerImageLayer);
            return innerImageLayer;
        }
    }

    private void showToast() {
        Toast.makeText(getActivity(), commonStrings.getLbl_enable_internet().getName(), Toast.LENGTH_SHORT).show();
    }

    public void setLanguageValues() {
        commonStrings = new Gson().fromJson(SessionHandler.getInstance().get(getActivity(), AppConstants.COMMONSTRINGS), POSTLanguageModel.Common_strings.class);
        homeScreen = new Gson().fromJson(SessionHandler.getInstance().get(getActivity(), AppConstants.HOMESCREEN), POSTLanguageModel.Home_screen.class);
        slTvLatestGigs.setText(homeScreen.getLbl_latest_gigs().getName());
        slTvPopularGigs.setText(homeScreen.getLbl_popular_gigs().getName());
        slTvTopCategory.setText(homeScreen.getLbl_top_category().getName());
        tvLatestGigs.setText(homeScreen.getLbl_latest_gigs().getName());
        tvPopularGigs.setText(homeScreen.getLbl_popular_gigs().getName());
        tvTopCategory.setText(homeScreen.getLbl_top_category().getName());
        popularGigsMore.setText(homeScreen.getLbl_view_all().getName());
        recentGigsMore.setText(homeScreen.getLbl_view_all().getName());
        categoryMore.setText(homeScreen.getLbl_view_all().getName());
        cetSearch.setHint(homeScreen.getTxt_fld_search().getName());
    }
}