package com.personal.lifecycle.fragment;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.personal.lifecycle.app.ActInf;
import com.personal.lifecycle.fragment.BaseFragment;

import java.util.List;

public class FragmentPagerAdaper extends FragmentPagerAdapter {
    private ActInf mInf;

    public FragmentPagerAdaper(@NonNull FragmentManager fm, ActInf inf) {
        super(fm);
        mInf = inf;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (mInf.getFragmentList() != null) {
            return mInf.getFragmentList().get(position);
        } else {
            return null;
        }
    }

    @Override
    public int getCount() {
        return mInf.getFragmentList() != null ? mInf.getFragmentList().size() : 0;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        List<BaseFragment> list = mInf.getFragmentList();
        if (list == null || position >= list.size()) {
            return "";
        }
        return list.get(position).getTabTitle();
    }
}
