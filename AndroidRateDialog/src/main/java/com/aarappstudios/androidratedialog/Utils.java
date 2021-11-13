package com.aarappstudios.androidratedialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

final class Utils {




    private Utils() {
    }

    static boolean underHoneyComb() {
        return false;
    }

    static boolean isLollipop() {
        return Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP || Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP_MR1;
    }


    static AlertDialog.Builder getDialogBuilder(Context context) {

            return new AlertDialog.Builder(context);
        }


}
