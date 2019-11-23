package com.personal.lifecycle.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import static com.personal.lifecycle.constants.AppConstants.*;

import com.personal.lifecycle.R;
import com.personal.lifecycle.app.ActInf;

public class StatFragment extends BaseFragment {

    @Override
    public int getLayoutId() {
        return R.layout.stat_fragment_layout;
    }

    @Override
    public String getPageMode() {
        return TAB_SETTING;
    }

    @Override
    public CharSequence getTabTitle(Context context) {
        return context.getString(R.string.tab_title_stat);
    }

    @Override
    protected void initUI(View baseView) {

    }
}
