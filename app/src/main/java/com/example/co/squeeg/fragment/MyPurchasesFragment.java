package com.example.co.squeeg.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;

import java.util.List;

import com.example.co.squeeg.Model.POSTLanguageModel;
import com.example.co.squeeg.Model.POSTMyActivity;
import com.example.co.squeeg.R;
import com.example.co.squeeg.adapter.MyPurchaseAdapter;
import com.example.co.squeeg.dialog.PurchaseCancelDialogFragment;
import com.example.co.squeeg.dialog.PurchaseSeeFeedbackDialog;
import com.example.co.squeeg.dialog.SalesSeeFeedbackDialog;
import com.example.co.squeeg.interfaces.UpdateRequestData;
import com.example.co.squeeg.utils.AppConstants;
import com.example.co.squeeg.utils.SessionHandler;
import com.example.co.squeeg.utils.Utils;


public class MyPurchasesFragment extends Fragment implements UpdateRequestData {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    List<POSTMyActivity.My_purchase> myPurchaseArray;
    View purchaseFragment;

    RecyclerView recyclerViewPurchase;
    MyPurchaseAdapter aMyPurchaseAdapter;


    MyPurchasesFragment mMyPurchasesFragment;

    TextView mNoDataFound;

    public POSTLanguageModel.Common_strings commonStrings = new POSTLanguageModel().new Common_strings();
    public POSTLanguageModel.My_activity_screen my_activity_screen = new POSTLanguageModel().new My_activity_screen();

    public MyPurchasesFragment() {

    }

    public static MyPurchasesFragment newInstance(String param1, String param2) {
        MyPurchasesFragment fragment = new MyPurchasesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(getActivity());
        Utils.freeMemory();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        mMyPurchasesFragment = this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        purchaseFragment = inflater.inflate(R.layout.fragment_my_purchases, container, false);
        recyclerViewPurchase = (RecyclerView) purchaseFragment.findViewById(R.id.rv_purchase_recyclerview);
        mNoDataFound = (TextView) purchaseFragment.findViewById(R.id.no_data_found);
        setLanguageValues();
        if (getArguments() != null) {
            myPurchaseArray = getArguments().getParcelableArrayList(AppConstants.MY_PURARRAY_KEY);
            PurchaseCancelDialogFragment.callDialgFrag(mMyPurchasesFragment);
            PurchaseSeeFeedbackDialog.callDialgFrag(mMyPurchasesFragment);
            SalesSeeFeedbackDialog.callDialgFrag(mMyPurchasesFragment);
            if (myPurchaseArray != null)
                if (myPurchaseArray.size() > 0) {
                    mNoDataFound.setVisibility(View.GONE);
                    recyclerViewPurchase.setVisibility(View.VISIBLE);
                    aMyPurchaseAdapter = new MyPurchaseAdapter(getActivity(), myPurchaseArray, getChildFragmentManager());
                    LinearLayoutManager horizontalLayoutManagaer
                            = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                    recyclerViewPurchase.setLayoutManager(horizontalLayoutManagaer);
                    recyclerViewPurchase.setHasFixedSize(true);
                    recyclerViewPurchase.setItemAnimator(new DefaultItemAnimator());
                    recyclerViewPurchase.setAdapter(aMyPurchaseAdapter);
                } else {
                    mNoDataFound.setVisibility(View.VISIBLE);
                    recyclerViewPurchase.setVisibility(View.GONE);
                }
        } else {
            mNoDataFound.setVisibility(View.VISIBLE);
            recyclerViewPurchase.setVisibility(View.GONE);
        }


        return purchaseFragment;
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
    public void onResume() {
        super.onResume();


    }

    @Override
    public void onDetach() {
        super.onDetach();

        mListener = null;
    }

    public void UpdateFeedbackRequest(String order_id) {
        for (int position = 0; position < myPurchaseArray.size(); position++) {
            if (myPurchaseArray.get(position).getOrder_id().equalsIgnoreCase(order_id)) {
                POSTMyActivity.My_purchase newModifiedItem = myPurchaseArray.get(position);
                newModifiedItem.setFeedback(my_activity_screen.getLbl_see_feedback().getName());
                newModifiedItem.setFeedback_val(2);
                myPurchaseArray.set(position, newModifiedItem);
                aMyPurchaseAdapter.notifyItemChanged(position);
            }
        }
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void updateRequestData(String order_id) {
        for (int position = 0; position < myPurchaseArray.size(); position++) {
            if (myPurchaseArray.get(position).getOrder_id().equalsIgnoreCase(order_id)) {
                POSTMyActivity.My_purchase newModifiedItem = myPurchaseArray.get(position);
                newModifiedItem.setOrder_cancel_val(2);
                newModifiedItem.setOrder_cancel(my_activity_screen.getLbl_request_sent().getName());
                myPurchaseArray.set(position, newModifiedItem);
                aMyPurchaseAdapter.notifyItemChanged(position);
            }
        }
    }

    public void setLanguageValues() {
        commonStrings = new Gson().fromJson(SessionHandler.getInstance().get(getActivity(), AppConstants.COMMONSTRINGS), POSTLanguageModel.Common_strings.class);
        my_activity_screen = new Gson().fromJson(SessionHandler.getInstance().get(getActivity(), AppConstants.MYACTIVITY), POSTLanguageModel.My_activity_screen.class);
    }

}
