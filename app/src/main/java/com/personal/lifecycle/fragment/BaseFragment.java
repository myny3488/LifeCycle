package com.personal.lifecycle.fragment;

import static com.personal.lifecycle.constants.AppConstants.*;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.personal.lifecycle.app.ActInf;
import com.personal.lifecycle.util.AppLog;

public abstract class BaseFragment extends Fragment {
    protected ActInf mInf;
    private View mBaseView;

    abstract public int getLayoutId();

    abstract public String getPageMode();

    abstract public String getTabTitle();

    abstract protected void initUI(View baseView);

    public void init() {
    }

    public BaseFragment(ActInf inf) {
        mInf = inf;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AppLog.d(TAG, "onCreateView = " + getPageMode());
        mBaseView = inflater.inflate(getLayoutId(), null);
        mInf.postOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mBaseView == null) {
                    AppLog.e(TAG, "base view is null, return");
                    return;
                }
                initUI(mBaseView);
            }
        });
        return mBaseView;
    }

    public void onCreateBtnClicked(View view) {
    }
}
