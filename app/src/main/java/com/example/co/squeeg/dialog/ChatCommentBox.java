package com.example.co.squeeg.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import java.util.HashMap;

import com.example.co.squeeg.Model.POSTAcceptBuyRequest;
import com.example.co.squeeg.Model.POSTLanguageModel;
import com.example.co.squeeg.R;
import com.example.co.squeeg.network.ApiClient;
import com.example.co.squeeg.network.ApiInterface;
import com.example.co.squeeg.receiver.NetworkChangeReceiver;
import com.example.co.squeeg.utils.AppConstants;
import com.example.co.squeeg.utils.CustomProgressDialog;
import com.example.co.squeeg.utils.SessionHandler;
import com.example.co.squeeg.utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Prasad on 11/15/2017.
 */

public class ChatCommentBox extends DialogFragment {

    private EditText commentBox;
    private Button sendButton;
    Context mContext;
    private HashMap<String, String> postBuyerChat = new HashMap<String, String>();
    private String seller_user_id = "";
    CustomProgressDialog mCustomProgressDialog;
    public POSTLanguageModel.Common_strings commonStrings = new POSTLanguageModel().new Common_strings();
    public POSTLanguageModel.Detail_gigs detail_gigs = new POSTLanguageModel().new Detail_gigs();

    public ChatCommentBox() {

    }

    public ChatCommentBox(Context mContext, String seller_user_id) {
        this.mContext = mContext;
        this.seller_user_id = seller_user_id;
        mCustomProgressDialog = new CustomProgressDialog(mContext);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View chatCommentBox = inflater.inflate(R.layout.dialog_chat_comment_box, null);
        commentBox = chatCommentBox.findViewById(R.id.et_chat_comment_box);
        sendButton = chatCommentBox.findViewById(R.id.btn_send);
        setLanguageValues();
        commentBox.setHint(detail_gigs.getLbl_your_msg().getName());
        sendButton.setText(detail_gigs.getBtn_send().getName());
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (commentBox.getText().toString().isEmpty()) {
                    return;
                }
                postBuyerChat.put("sell_gigs_userid", seller_user_id);
                postBuyerChat.put("chat_message_content", commentBox.getText().toString());
                //postBuyerChat.put("user_id", SessionHandler.getInstance().get(mContext, AppConstants.TOKEN_ID));
                if (NetworkChangeReceiver.isConnected()) {
                    mCustomProgressDialog.showDialog();
                    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                    apiInterface.postMessage(postBuyerChat, SessionHandler.getInstance().get(mContext, AppConstants.TOKEN_ID), SessionHandler.getInstance().get(mContext, AppConstants.Language)).enqueue(new Callback<POSTAcceptBuyRequest>() {
                        @Override
                        public void onResponse(Call<POSTAcceptBuyRequest> call, Response<POSTAcceptBuyRequest> response) {
                            if (response.body() != null) {
                                if (response.body().getCode().equals("200")) {
                                    Utils.toastMessage(mContext, response.body().getMessage());
                                } else {
                                    Utils.toastMessage(mContext, response.body().getMessage());
                                }
                            }
                            mCustomProgressDialog.dismiss();
                            getDialog().dismiss();
                        }

                        @Override
                        public void onFailure(Call<POSTAcceptBuyRequest> call, Throwable t) {
                            Log.i("TAG", t.getMessage());
                            mCustomProgressDialog.dismiss();
                        }
                    });
                } else {
                    Utils.toastMessage(mContext, commonStrings.getLbl_enable_internet().getName());
                }

            }
        });
        return chatCommentBox;
    }

    public void setLanguageValues() {
        commonStrings = new Gson().fromJson(SessionHandler.getInstance().get(mContext, AppConstants.COMMONSTRINGS), POSTLanguageModel.Common_strings.class);
        detail_gigs = new Gson().fromJson(SessionHandler.getInstance().get(mContext, AppConstants.DETAILGIGS), POSTLanguageModel.Detail_gigs.class);
    }

}
