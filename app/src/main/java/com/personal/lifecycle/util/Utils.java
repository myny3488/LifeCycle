package com.personal.lifecycle.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.personal.lifecycle.constants.AppConstants.TAG;

public class Utils {
    public static String dateToString(Date date, String format) {
        if (date == null || format == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    public static Date stringToDate(String date, String format) {
        if (date == null || format == null) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.parse(date);
        } catch (ParseException e) {
            AppLog.e(TAG, "parse ERROR!!!!");
            e.printStackTrace();
        }
        return null;
    }
}