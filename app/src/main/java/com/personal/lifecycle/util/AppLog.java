package com.personal.lifecycle.util;

import static com.personal.lifecycle.constants.AppConstants.*;

import android.util.Log;

public class AppLog {
    public static boolean sLogOn = true;
    private static final boolean LOG_TAG_EXCEPTION = true;
    private static final int REAL_METHOD_POS = 2;


    private static String prefix() {
        StackTraceElement[] ste = new Throwable().getStackTrace();
        StackTraceElement realMethod = ste[REAL_METHOD_POS];

        String thread = "Other";
        return "[" + realMethod.getFileName() + ":"
                + realMethod.getLineNumber() + ":"
                + realMethod.getMethodName() + "()-" + "[Thread:" + thread + "] ";
    }

    public static void setLogOn(boolean on) {
        sLogOn = on;
    }

    public static boolean getLogOn() {
        return sLogOn;
    }

    public static void d(String tag, String msg) {
        if (sLogOn) {
            Log.d(tag, prefix() + msg);
        }
    }

    public static void i(String tag, String msg) {
        if (sLogOn) {
            Log.i(tag, prefix() + msg);
        }
    }

    public static void e(String tag, String msg) {
        // if (LOG_ON) {
        Log.e(tag, prefix() + msg);
        // }
    }

    public static void v(String tag, String msg) {
        if (sLogOn) {
            Log.v(tag, prefix() + msg);
        }
    }

    public static void w(String tag, String msg) {
        if (sLogOn) {
            Log.w(tag, prefix() + msg);
        }
    }

    public static void d(String tag, String msg, Throwable tr) {
        if (sLogOn) {
            Log.d(tag, prefix() + msg, tr);
        }
    }

    public static void i(String tag, String msg, Throwable tr) {
        if (sLogOn) {
            Log.i(tag, prefix() + msg, tr);
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (sLogOn) {
            Log.e(tag, prefix() + msg, tr);
        }
    }

    public static void v(String tag, String msg, Throwable tr) {
        if (sLogOn) {
            Log.v(tag, prefix() + msg, tr);
        }
    }

    public static void w(String tag, String msg, Throwable tr) {
        if (sLogOn) {
            Log.w(tag, prefix() + msg, tr);
        }
    }

    public static boolean isTagExceptionLogOn() {
        return LOG_TAG_EXCEPTION;
    }

    public static void debugStackTrace(String from) {
        Log.e(TAG, "[Debug] Printing stack trace : from - " + from);
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();
        for (int i = 3; i < elements.length; i++) {
            StackTraceElement s = elements[i];
            Log.e(TAG, "[Debug] \tat " + from + s.getClassName() + "." +
                    s.getMethodName() + "(" +
                    s.getFileName() + ":" +
                    s.getLineNumber() + ")");
        }
    }
}
