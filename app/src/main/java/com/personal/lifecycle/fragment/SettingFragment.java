package com.personal.lifecycle.fragment;

import static com.personal.lifecycle.constants.AppConstants.*;

import android.content.Context;
import android.view.View;

import com.personal.lifecycle.R;

public class SettingFragment extends BaseFragment {

    @Override
    public int getLayoutId() {
        return R.layout.setting_fragment_layout;
    }

    @Override
    public String getPageMode() {
        return TAB_SETTING;
    }

    @Override
    public CharSequence getTabTitle(Context context) {
        return context.getString(R.string.tab_title_setting);
    }

    @Override
    protected void initUI(View baseView) {

    }
}
