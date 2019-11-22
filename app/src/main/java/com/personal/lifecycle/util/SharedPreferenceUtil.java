package com.personal.lifecycle.util;

import static com.personal.lifecycle.constants.AppConstants.*;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.ArrayList;

public class SharedPreferenceUtil {
    private static SharedPreferences getSharedPreference(Context context) {
        return context.getSharedPreferences("LifeCycle", Context.MODE_PRIVATE);
    }

    public static void setTabPosition(Context context, int position) {
        SharedPreferences pref = getSharedPreference(context);
        Editor editor = pref.edit();
        editor.putInt("tab_position", position);
        editor.apply();
    }

    public static int getTabPosition(Context context) {
        SharedPreferences pref = getSharedPreference(context);
        return pref.getInt("tab_position", 0);
    }

    public static void setEventList(Context context, String list) {
        AppLog.d(TAG, "setEventList = " + list);
        SharedPreferences pref = getSharedPreference(context);
        Editor editor = pref.edit();
        editor.putString(getEventKey(), list);
        editor.apply();
    }

    public static String getEventList(Context context) {
        SharedPreferences pref = getSharedPreference(context);
        return pref.getString(getEventKey(), null);
    }

    private static String getEventKey() {
        return "event_list";
    }
}
