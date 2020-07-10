package com.example.co.squeeg.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

import com.example.co.squeeg.LoginScreen;

/**
 * Created by Hari on 08-05-2018.
 */

public class NetworkAlertDialog extends AlertDialog {

    Context mContext;
    String title;
    String msg;
    Runnable okRunnable;
    Runnable cancelRunnable;

    public NetworkAlertDialog(@NonNull Context context) {
        super(context);
    }

    public static void networkAlertDialog(final Context mContext, String title, String msg, final Runnable okRunnable, final String cancelRunnable) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(mContext);
        alertBuilder.setCancelable(false);
        alertBuilder.setTitle(title);
        alertBuilder.setMessage(msg);
        if (okRunnable != null) {
            alertBuilder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    okRunnable.run();
                    dialog.dismiss();
                }
            });
        } else if (cancelRunnable != null) {
            alertBuilder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    if (mContext instanceof Activity) {
                        ((Activity) mContext).finish();
                    }
                    dialog.dismiss();
                }
            });
        } else {
            alertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Intent callLogin = new Intent(mContext, LoginScreen.class);
                    mContext.startActivity(callLogin);
                    SessionHandler.getInstance().remove(mContext, AppConstants.TOKEN_ID);
                    if (mContext instanceof Activity) {
                        ((Activity) mContext).finish();
                    }
                    dialog.dismiss();
                }
            });
        }
        AlertDialog alert = alertBuilder.create();
        alert.show();
    }
}
