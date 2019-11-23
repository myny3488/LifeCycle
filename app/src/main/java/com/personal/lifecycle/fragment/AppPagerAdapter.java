package com.personal.lifecycle.fragment;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.personal.lifecycle.app.ActInf;

import java.util.ArrayList;

public class AppPagerAdapter extends FragmentPagerAdapter {
    private ActInf mInf;
    private ArrayList<BaseFragment> mList = new ArrayList<>();

    public AppPagerAdapter(@NonNull FragmentManager fm, ActInf inf) {
        super(fm);
        mInf = inf;
    }

    public AppPagerAdapter addFragment(BaseFragment fragment) {
        mList.add(fragment);
        return this;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mList.get(position).getTabTitle(mInf.getAppCtx());
    }

    public void onCreateBtnClicked(View view) {
        mList.get(1).onCreateBtnClicked(view);
    }
}
