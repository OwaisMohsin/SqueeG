package com.example.co.squeeg;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.onesignal.OneSignal;
import com.squareup.picasso.Picasso;

import org.apache.http.HttpException;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import com.example.co.squeeg.Model.POSTChatHistory;
import com.example.co.squeeg.Model.POSTLanguageModel;
import com.example.co.squeeg.Model.POSTUserChat;
import com.example.co.squeeg.adapter.ChatRoomAdapter;
import com.example.co.squeeg.network.ApiClient;
import com.example.co.squeeg.network.ApiInterface;
import com.example.co.squeeg.receiver.NetworkChangeReceiver;
import com.example.co.squeeg.utils.AppConstants;
import com.example.co.squeeg.utils.CustomButton;
import com.example.co.squeeg.utils.CustomProgressDialog;
import com.example.co.squeeg.utils.NetworkAlertDialog;
import com.example.co.squeeg.utils.SessionHandler;
import com.example.co.squeeg.utils.Utils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Prasad on 11/14/2017.
 */

public class ChatRoomActivity extends BaseActivity {

    public static RecyclerView recyclerViewChatRoom;
    String user_id = "", chat_id = "", chatter_name = "", user_image = "", user_status = "";
    static CustomProgressDialog mCustomProgressDialog;
    CustomButton btnSend;
    private HashMap<String, String> postChatDetails = new HashMap<String, String>();
    public static ChatRoomAdapter aChatRoomAdapter;
    private EditText sendMessage;
    private TextView messengerName;
    Toolbar toolbar;
    private HashMap<String, String> postChatmessage = new HashMap<String, String>();
    static List<POSTChatHistory.Chat_detail> chatArray = new ArrayList<POSTChatHistory.Chat_detail>();
    String notification = "", message = "";
    String body;
    JSONObject notificationData;
    private BroadcastReceiver myBroadcastReceiver;
    private CircleImageView ivImage;
    int page_num = 1, total_pages;
    LinearLayoutManager popularGigsLayoutManager;
    int visibleItemCount, totalItemCount, pastVisiblesItems;
    boolean isLoading = false;
    private LinearLayout sendmsglayout;
    LocalBroadcastManager localBroadcastManager;
    public POSTLanguageModel.Detail_gigs detail_gigs = new POSTLanguageModel().new Detail_gigs();
    public POSTLanguageModel.Common_strings commonStrings = new POSTLanguageModel().new Common_strings();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_room);
        OneSignal.setInFocusDisplaying(OneSignal.OSInFocusDisplayOption.None);
        Utils.freeMemory();
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        sendMessage = (EditText) findViewById(R.id.et_send_message);
        messengerName = (TextView) findViewById(R.id.tv_chat_msger_name);
        ivImage = (CircleImageView) findViewById(R.id.input_profile_picture);
        recyclerViewChatRoom = (RecyclerView) findViewById(R.id.rv_chat_room);
        sendmsglayout = (LinearLayout) findViewById(R.id.ll_send_msg);
        btnSend = (CustomButton) findViewById(R.id.btn_send);
        mCustomProgressDialog = new CustomProgressDialog(this);

        setLanguageaValues();

        user_id = getIntent().getStringExtra("user_id");
        chat_id = getIntent().getStringExtra("chat_id");
        chatter_name = getIntent().getStringExtra("chat_name");
        user_image = getIntent().getStringExtra("user_image");
        notification = getIntent().getStringExtra("notification");
        body = getIntent().getStringExtra("receivedResult");
        message = getIntent().getStringExtra("message");
        user_status = getIntent().getStringExtra("user_status");
        if (user_status != null) {
            if (user_status.equalsIgnoreCase("1")) {
                sendmsglayout.setVisibility(View.GONE);
            }
        }
        UpdateMessage();
        Picasso.get().load(AppConstants.BASE_URL + user_image).placeholder(R.drawable.ic_no_image_100).into(ivImage);
        //ivImage.setImageURI(Uri.parse(AppConstants.BASE_URL + user_image));

        toolbar = (Toolbar) findViewById(R.id.tb_toolbar);
        setSupportActionBar(toolbar);

        messengerName.setText(chatter_name);

        chatArray.clear();
        getChatResponse();

        recyclerViewChatRoom.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(final RecyclerView recyclerView, int dx, int dy) {
                if (page_num <= total_pages) {
                    if (dy < 0) {
                        visibleItemCount = popularGigsLayoutManager.getChildCount();
                        totalItemCount = popularGigsLayoutManager.getItemCount();
                        pastVisiblesItems = popularGigsLayoutManager.findFirstVisibleItemPosition();
                        int displayedPosition = popularGigsLayoutManager.findFirstCompletelyVisibleItemPosition();
                        if (!isLoading && displayedPosition == 0) {
                            Log.v("...", " Reached Last Item");
                            page_num++;
                            getChatResponse();
                            isLoading = true;
                        }
                    }

                }
            }
        });

    }

    private void getChatResponse() {
        if (NetworkChangeReceiver.isConnected()) {

            if (SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID) != null && user_id != null) {
                //postChatDetails.put("from_user_id", SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID));
                postChatDetails.put("to_user_id", user_id);
                postChatDetails.put("page", String.valueOf(page_num));
            }
            mCustomProgressDialog.showDialog();
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            apiInterface.getChatHistory(postChatDetails, SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID), SessionHandler.getInstance().get(this, AppConstants.Language)).enqueue(new Callback<POSTChatHistory>() {
                @Override
                public void onResponse(Call<POSTChatHistory> call, Response<POSTChatHistory> response) {
                    mCustomProgressDialog.dismiss();
                    if (response.body() != null)
                        if (response.body().getCode().equals(200)) {
                            isLoading = false;
                            total_pages = response.body().getData().getTotal_records();
                            if (response.body().getData().getChat_details().size() > 0) {
                                if (chatArray.size() > 0) {
                                    chatArray.addAll(0, response.body().getData().getChat_details());
                                    aChatRoomAdapter.notifyDataSetChanged();
                                } else {
                                    chatArray.addAll(response.body().getData().getChat_details());
                                    aChatRoomAdapter = new ChatRoomAdapter(ChatRoomActivity.this, chatArray);
                                    popularGigsLayoutManager
                                            = new LinearLayoutManager(ChatRoomActivity.this, LinearLayoutManager.VERTICAL, false);
                                    recyclerViewChatRoom.setLayoutManager(popularGigsLayoutManager);
                                    recyclerViewChatRoom.setAdapter(aChatRoomAdapter);
                                    scrollToBottom();
                                }
                                isLoading = false;
                            } else {
                                Log.d("Message Response", "No message available");

                            }


                        } else if (response.body().getCode().equals(AppConstants.INVALID_RESPONSE_CODE)) {
                            mCustomProgressDialog.dismiss();
                            NetworkAlertDialog.networkAlertDialog(ChatRoomActivity.this, "", response.body().getMessage(), null, null);
                        } else if (response.body().getCode().equals(AppConstants.InactiveStatusResponse)) {
                            Utils.createUserInActiceAlert(ChatRoomActivity.this, response.body().getMessage());
                        }


                }

                @Override
                public void onFailure(Call<POSTChatHistory> call, Throwable t) {
                    Log.i("TAG", t.getMessage());
                    mCustomProgressDialog.dismiss();
                    if (t instanceof UnknownHostException || t instanceof HttpException || t instanceof ConnectException || t instanceof SocketTimeoutException || t instanceof SocketException) {
                        //Toast.makeText(ChatRoomActivity.this, getString(R.string.err_server_msg), Toast.LENGTH_SHORT).show();
                        NetworkAlertDialog.networkAlertDialog(ChatRoomActivity.this, commonStrings.getLbl_network_err().getName(),
                                commonStrings.getLbl_server_err().getName(), chatRoomRunn, null);
                    }
                }
            });


        } else {
            Toast.makeText(this, commonStrings.getLbl_enable_internet().getName(), Toast.LENGTH_SHORT).show();
        }
    }

    public static void scrollToBottom() {
        if (aChatRoomAdapter != null) {
            aChatRoomAdapter.notifyDataSetChanged();
            if (aChatRoomAdapter.getItemCount() > 1) {
                recyclerViewChatRoom.getLayoutManager().scrollToPosition(aChatRoomAdapter.getItemCount() - 1);
            }
        }
    }


    public void sendMesageBtn(View view) {
        String message = sendMessage.getText().toString().trim();
        if (message.isEmpty()) {
            return;
        }
        //postChatmessage.put("from_user_id", SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID));
        postChatmessage.put("to_user_id", user_id);
        postChatmessage.put("message", message);

        if (NetworkChangeReceiver.isConnected()) {
            mCustomProgressDialog.showDialog();
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            apiInterface.postUserChat(postChatmessage, SessionHandler.getInstance().get(this, AppConstants.TOKEN_ID), SessionHandler.getInstance().get(this, AppConstants.Language)).enqueue(new Callback<POSTUserChat>() {
                @Override
                public void onResponse(Call<POSTUserChat> call, Response<POSTUserChat> response) {
                    if (response.body() != null) {
                        if (response.body().getCode().equals(200)) {
                            mCustomProgressDialog.dismiss();
                            POSTChatHistory.Chat_detail mChat_detail = new POSTChatHistory.Chat_detail();
                            mChat_detail.setChat_from(response.body().getData().get(0).getChat_from());
                            mChat_detail.setChat_to(response.body().getData().get(0).getChat_to());
                            mChat_detail.setChat_time(response.body().getData().get(0).getChat_time());
                            mChat_detail.setContent(response.body().getData().get(0).getContent());
                            aChatRoomAdapter.addData(mChat_detail);
                            aChatRoomAdapter.notifyDataSetChanged();
                            scrollToBottom();
                            sendMessage.setText("");
                        } else if (response.body().getCode().equals(AppConstants.INVALID_RESPONSE_CODE)) {
                            mCustomProgressDialog.dismiss();
                            NetworkAlertDialog.networkAlertDialog(ChatRoomActivity.this, "", response.body().getMessage(), null, null);
                        } else {
                            mCustomProgressDialog.dismiss();
                            Toast.makeText(ChatRoomActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<POSTUserChat> call, Throwable t) {
                    Log.i("TAG", t.getMessage());
                    mCustomProgressDialog.dismiss();
                    if (t instanceof UnknownHostException || t instanceof HttpException || t instanceof ConnectException || t instanceof SocketException || t instanceof SocketTimeoutException) {
                        Toast.makeText(ChatRoomActivity.this, commonStrings.getLbl_server_prblm().getName(), Toast.LENGTH_SHORT).show();

                    }
                }
            });

        } else {
            Utils.toastMessage(this, commonStrings.getLbl_enable_internet().getName());
        }
    }

    Runnable chatRoomRunn = new Runnable() {
        @Override
        public void run() {
            //TODO: API call
            getChatResponse();
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            OneSignal.setInFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter(MyApplication.ACTION_MyUpdate);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        localBroadcastManager.registerReceiver(myBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (myBroadcastReceiver != null) {
            localBroadcastManager.unregisterReceiver(myBroadcastReceiver);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    public void UpdateMessage() {
        myBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String result = intent.getStringExtra(MyApplication.EXTRA_KEY_UPDATE);
                String body = intent.getStringExtra(MyApplication.MESSAGE);
                if (!result.isEmpty()) {
                    try {
                        JSONObject mJSONObject = new JSONObject(result);
                        if (mJSONObject.get("from_user_id").toString().equalsIgnoreCase(user_id)) {
                            POSTChatHistory.Chat_detail mChat_detail = new POSTChatHistory.Chat_detail();
                            mChat_detail.setChat_from(mJSONObject.get("from_user_id").toString());
                            mChat_detail.setChat_to(mJSONObject.get("to_user_id").toString());
                            mChat_detail.setChat_time(mJSONObject.get("chat_utc_time").toString());
                            mChat_detail.setContent(body);
                            chatArray.add(mChat_detail);
                            aChatRoomAdapter.notifyDataSetChanged();
                            recyclerViewChatRoom.getLayoutManager().scrollToPosition(aChatRoomAdapter.getItemCount() - 1);
                        } else {
                            OneSignal.setInFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification);
                        }

                        Log.i("TAG JSONRESULT ---->", mJSONObject.toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        };
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        OneSignal.setInFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification);
    }

    public void setLanguageaValues() {
        detail_gigs = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.DETAILGIGS), POSTLanguageModel.Detail_gigs.class);
        commonStrings = new Gson().fromJson(SessionHandler.getInstance().get(this, AppConstants.COMMONSTRINGS), POSTLanguageModel.Common_strings.class);
        sendMessage.setHint(detail_gigs.getTxt_fld_enter_msg().getName());
        btnSend.setText(detail_gigs.getBtn_send().getName());
    }
}
