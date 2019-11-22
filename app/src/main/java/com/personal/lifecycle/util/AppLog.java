package com.personal.lifecycle.util;

import static com.personal.lifecycle.constants.AppConstants.*;

import android.util.Log;

public class AppLog {
    public static void d(String TAG, String msg) {
        Log.d(TAG, buildMsg(msg, 4));
    }

    public static void i(String TAG, String msg) {
        Log.i(TAG, buildMsg(msg, 4));
    }

    public static void w(String TAG, String msg) {
        Log.w(TAG, buildMsg(msg, 4));
    }

    public static void e(String TAG, String msg) {
        Log.e(TAG, buildMsg(msg, 4));
    }

    public static void debugStackTrace(String msg) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String trace = "";
        for (int i = 3; i < stackTrace.length; i++) {
            trace += buildMsg(msg, i);
            trace += "\n";
        }
        Log.e(TAG, trace);
    }

    private static String buildMsg(String msg, int idx) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StringBuilder builder = new StringBuilder();
        StackTraceElement element = stackTrace[idx];
        builder.append(element.getFileName() + " ");
        builder.append(element.getMethodName()
                + "():" + element.getLineNumber() + " - ");
        builder.append(msg);
        return builder.toString();
    }
}
