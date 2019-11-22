package com.personal.lifecycle.components;

import com.personal.lifecycle.util.AppLog;
import com.personal.lifecycle.util.Utils;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.personal.lifecycle.constants.AppConstants.*;

public class LifeEvent implements Serializable {
    private String mTitle;
    private Date mDate;

    public String flatten() {
        StringBuilder builder = new StringBuilder();
        builder.append(mTitle);
        builder.append(";");
        builder.append(Utils.dateToString(mDate, DATE_FORMAT));
        return builder.toString();
    }

    public LifeEvent unflatten(String list) {
        if (list == null || "".equals(list)) {
            return null;
        }
        String[] splited = list.split(";");
        mTitle = splited[0];
        mDate = Utils.stringToDate(splited[1], DATE_FORMAT);
        return this;
    }

    public LifeEvent setTitle(String title) {
        mTitle = title;
        return this;
    }

    public String getTitle() {
        return mTitle;
    }

    public LifeEvent setDate(Date date) {
        mDate = date;
        return this;
    }
}
