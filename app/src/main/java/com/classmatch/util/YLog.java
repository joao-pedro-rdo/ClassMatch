package com.classmatch.util;

import android.util.Log;

public class YLog {

    private static final String TAG = "Y_LOG_MANAGER";

    // Debug
    public static void d(String codeClass, String method, String message) {
        Log.d(TAG, codeClass + "." + method + "(): " + message);
    }

    public static void d(String codeClass, String method, String message, String exceptionMessage) {
        Log.d(TAG, codeClass + "." + method + "(): " + message);
    }

}
