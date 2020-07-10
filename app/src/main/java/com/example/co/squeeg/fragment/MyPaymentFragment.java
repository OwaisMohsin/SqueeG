package com.example.co.squeeg.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import com.example.co.squeeg.Model.POSTLanguageModel;
import com.example.co.squeeg.Model.POSTMyActivity;
import com.example.co.squeeg.MyApplication;
import com.example.co.squeeg.R;
import com.example.co.squeeg.adapter.MyPaymentAdapter;
import com.example.co.squeeg.utils.AppConstants;
import com.example.co.squeeg.utils.SessionHandler;
import com.example.co.squeeg.utils.Utils;

public class MyPaymentFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private View myPaymentViews;
    RecyclerView recyclerViewPurchase;
    //    SwipeRefreshLayout mSwipeRefreshLayout;
    MyPaymentAdapter aMyPaymentAdapter;
    TextView currentAmount, mNoDataFound;
    private BroadcastReceiver myBroadcastReceiver;
    Context mContext;
    public POSTLanguageModel.Common_strings commonStrings = new POSTLanguageModel().new Common_strings();
    public POSTLanguageModel.My_activity_screen my_activity_screen = new POSTLanguageModel().new My_activity_screen();

    public MyPaymentFragment() {

    }

    public static MyPaymentFragment newInstance(String param1, String param2) {
        MyPaymentFragment fragment = new MyPaymentFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myPaymentViews = inflater.inflate(R.layout.fragment_my_payment, container, false);
        setLanguageValues();
        recyclerViewPurchase = (RecyclerView) myPaymentViews.findViewById(R.id.rv_purchase_recyclerview);
        currentAmount = (TextView) myPaymentViews.findViewById(R.id.tv_current_bal);
        mNoDataFound = (TextView) myPaymentViews.findViewById(R.id.no_data_found);

        PaymentUpdate();
        if (getArguments() != null) {
            List<POSTMyActivity.My_payment> my_payment_array = getArguments().getParcelableArrayList(AppConstants.MY_PAYMENTARRAY_KEY);
            if (my_payment_array != null) {
                if (my_payment_array.size() > 0) {
                    mNoDataFound.setVisibility(View.GONE);
                    recyclerViewPurchase.setVisibility(View.VISIBLE);
                    currentAmount.setVisibility(View.VISIBLE);
                    currentAmount.setText(my_activity_screen.getLbl_current_balance().getName() + AppConstants.DOLLAR_SIGN + getArguments().getString(AppConstants.WALLET_BALANCE));
                    aMyPaymentAdapter = new MyPaymentAdapter(getActivity(), my_payment_array, currentAmount, getArguments().getString(AppConstants.WALLET_BALANCE), getChildFragmentManager());
                    LinearLayoutManager horizontalLayoutManagaer
                            = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                    recyclerViewPurchase.setLayoutManager(horizontalLayoutManagaer);
                    recyclerViewPurchase.setHasFixedSize(true);
                    recyclerViewPurchase.setItemAnimator(new DefaultItemAnimator());
                    recyclerViewPurchase.setAdapter(aMyPaymentAdapter);
                } else {
                    mNoDataFound.setVisibility(View.VISIBLE);
                    recyclerViewPurchase.setVisibility(View.GONE);
                    currentAmount.setVisibility(View.GONE);

                }
            }

        } else {
            mNoDataFound.setVisibility(View.VISIBLE);
            recyclerViewPurchase.setVisibility(View.GONE);
            currentAmount.setVisibility(View.GONE);
        }

        return myPaymentViews;
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
        mContext = context;
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        if (myBroadcastReceiver != null) {
            mContext.unregisterReceiver(myBroadcastReceiver);
        }
    }

    public void PaymentUpdate() {
        myBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String result = intent.getStringExtra(MyApplication.EXTRA_KEY_UPDATE);
                if (!result.isEmpty()) {

                    try {
                        JSONObject mJSONObject = new JSONObject(result);
                        currentAmount.setText(my_activity_screen.getLbl_current_balance().getName()  + mJSONObject.getString("balance"));
                        Log.i("TAG JSONRESULT ---->", mJSONObject.toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        };
        IntentFilter intentFilter = new IntentFilter(MyApplication.ACTION_MyUpdate);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        mContext.registerReceiver(myBroadcastReceiver, intentFilter);

    }


    @Override
    public void onResume() {
        super.onResume();
        PaymentUpdate();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    public void setLanguageValues() {
        commonStrings = new Gson().fromJson(SessionHandler.getInstance().get(mContext, AppConstants.COMMONSTRINGS), POSTLanguageModel.Common_strings.class);
        my_activity_screen = new Gson().fromJson(SessionHandler.getInstance().get(mContext, AppConstants.MYACTIVITY), POSTLanguageModel.My_activity_screen.class);
    }

}
