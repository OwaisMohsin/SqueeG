package com.example.co.squeeg;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.onesignal.OSNotification;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import com.example.co.squeeg.utils.LocaleUtils;
import io.fabric.sdk.android.Fabric;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

import com.example.co.squeeg.receiver.NetworkChangeReceiver;
import com.example.co.squeeg.utils.AppConstants;
import com.example.co.squeeg.utils.NotificationUtils;
import com.example.co.squeeg.utils.SessionHandler;

public class MyApplication extends Application {

    private static MyApplication mInstance;
    public static final String EXTRA_KEY_UPDATE = "EXTRA_UPDATE";
    public static final String MESSAGE = "MESSAGE";
    public static final String ACTION_MyUpdate = "com.example.co.squeeg.UPDATE";
    private NotificationUtils notificationUtils;
    private static final String TAG = "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        Fresco.initialize(this);

        if (SessionHandler.getInstance().get(this, "locale") != null) {
            LocaleUtils.setLocale(new Locale(SessionHandler.getInstance().get(this, "locale")));
            LocaleUtils.updateConfig(this, getBaseContext().getResources().getConfiguration());
        }
//        else {
//            LocaleUtils.setLocale(new Locale("en"));
//            LocaleUtils.updateConfig(this, getBaseContext().getResources().getConfiguration());
//        }

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .setNotificationOpenedHandler(new OneSignal.NotificationOpenedHandler() {
                    @Override
                    public void notificationOpened(OSNotificationOpenResult result) {
                        if (SessionHandler.getInstance().get(getApplicationContext(), AppConstants.TOKEN_ID) != null) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        } else {
                            Intent intent = new Intent(getApplicationContext(), LoginScreen.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        }
                    }
                })
                .setNotificationReceivedHandler(new OneSignal.NotificationReceivedHandler() {
                    @Override
                    public void notificationReceived(OSNotification notification) {
                        JSONObject data = notification.payload.additionalData;
                        // Check if message contains a notification payload.
                        if (data != null) {
                            try {
                                Intent intentUpdate = new Intent();
                                intentUpdate.setAction(ACTION_MyUpdate);
                                intentUpdate.addCategory(Intent.CATEGORY_DEFAULT);
                                intentUpdate.putExtra(EXTRA_KEY_UPDATE, data.toString());
                                intentUpdate.putExtra(MESSAGE, notification.payload.body);
                                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intentUpdate);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }
                        /*if (data != null) {
                            handleNotification(notification.payload.body, data);
                            handleDataMessage(data);
                        }*/
                    }
                })
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
        OneSignal.setSubscription(true);
        JSONObject tags = new JSONObject();
        try {
            if (SessionHandler.getInstance().get(getApplicationContext(), AppConstants.TOKEN_ID) != null) {
                tags.put("user_id", SessionHandler.getInstance().get(getApplicationContext(), AppConstants.TOKEN_ID));
                OneSignal.sendTags(tags);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        saveOnesignalId();
        mInstance = this;
    }

    private void handleNotification(String message, JSONObject data) {
        // play notification sound
        NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
        notificationUtils.playNotificationSound();
        if (data != null) {
            try {
                Intent intentUpdate = new Intent();
                intentUpdate.setAction(ACTION_MyUpdate);
                intentUpdate.addCategory(Intent.CATEGORY_DEFAULT);
                intentUpdate.putExtra(EXTRA_KEY_UPDATE, data.toString());
                intentUpdate.putExtra(MESSAGE, data.getString("body"));
                sendBroadcast(intentUpdate);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleDataMessage(JSONObject json) {
        Log.e(TAG, "push json: " + json.toString());

        try {
            JSONObject data = json.getJSONObject("data");

            String title = data.getString("title");
            String message = data.getString("message");
            //boolean isBackground = data.getBoolean("is_background");
            String imageUrl = data.getString("image");
            String timestamp = data.getString("timestamp");
            JSONObject payload = data.getJSONObject("payload");

            Log.e(TAG, "title: " + title);
            Log.e(TAG, "message: " + message);
            //Log.e(TAG, "isBackground: " + isBackground);
            Log.e(TAG, "payload: " + payload.toString());
            Log.e(TAG, "imageUrl: " + imageUrl);
            Log.e(TAG, "timestamp: " + timestamp);

            if (!NotificationUtils.isAppIsInBackground(getApplicationContext())) {
                // app is in foreground, broadcast the push message
                Intent pushNotification = new Intent(AppConstants.PUSH_NOTIFICATION);
                pushNotification.putExtra("message", message);
                LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);

                // play notification sound
                NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
                notificationUtils.playNotificationSound();
            } else {
                // app is in background, show the notification in notification tray
                Intent resultIntent = new Intent(getApplicationContext(), MainActivity.class);
                resultIntent.putExtra("message", message);

                // check for image attachment
                if (TextUtils.isEmpty(imageUrl)) {
                    showNotificationMessage(getApplicationContext(), title, message, timestamp, resultIntent);
                } else {
                    // image is present, show notification with image
                    showNotificationMessageWithBigImage(getApplicationContext(), title, message, timestamp, resultIntent, imageUrl);
                }
            }
        } catch (JSONException e) {
            //Log.e(TAG, "Json Exception: " + e.getMessage());
        } catch (Exception e) {
            //Log.e(TAG, "Exception: " + e.getMessage());
        }
    }

    /**
     * Showing notification with text only
     */
    private void showNotificationMessage(Context context, String title, String message, String timeStamp, Intent intent) {
        notificationUtils = new NotificationUtils(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, timeStamp, intent);
    }

    /**
     * Showing notification with text and image
     */
    private void showNotificationMessageWithBigImage(Context context, String title, String message, String timeStamp, Intent intent, String imageUrl) {
        notificationUtils = new NotificationUtils(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, timeStamp, intent, imageUrl);
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(NetworkChangeReceiver.ConnectivityReceiverListener listener) {
        NetworkChangeReceiver.connectivityReceiverListener = listener;
    }

    private void saveOnesignalId() {
        OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler() {
            @Override
            public void idsAvailable(final String userId, String registrationId) {

                Log.i("onesignaal", "userId : " + userId);
                Log.i("onesignaal", "registrationId : " + registrationId);
                SessionHandler.getInstance().save(getApplicationContext(), AppConstants.NOTIFICATION_IDS, userId);
            }
        });
    }
}