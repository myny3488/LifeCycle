package com.personal.lifecycle.app;

import static com.personal.lifecycle.constants.AppConstants.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;

import com.google.android.material.tabs.TabLayout;
import com.personal.lifecycle.R;
import com.personal.lifecycle.constants.ST;
import com.personal.lifecycle.fragment.AppPagerAdapter;
import com.personal.lifecycle.fragment.ListFragment;
import com.personal.lifecycle.fragment.SettingFragment;
import com.personal.lifecycle.fragment.StatFragment;
import com.personal.lifecycle.util.AniUtil;
import com.personal.lifecycle.util.AppLog;
import com.personal.lifecycle.util.SharedPreferenceUtil;

public class MainActivity extends AppCompatActivity implements ActInf, ViewPager.OnPageChangeListener {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private AppPagerAdapter mAdaper;
    private int mPosition;

    private View mCreateBtn;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "LifeCycle : onCreate()");
        setContentView(R.layout.activity_main);

        mTabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_pager);
        mAdaper = new AppPagerAdapter(getSupportFragmentManager(), this)
                .addFragment(new SettingFragment())
                .addFragment(new ListFragment())
                .addFragment(new StatFragment());

        mViewPager.addOnPageChangeListener(this);
        mViewPager.setAdapter(mAdaper);
        mTabLayout.setupWithViewPager(mViewPager);

        mPosition = TAB_DEFAULT_POSITION;
        mViewPager.setCurrentItem(mPosition, false);

        mCreateBtn = findViewById(R.id.create_event_button);
        mCreateBtn.setVisibility(View.VISIBLE);
        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppLog.d(TAG, "onCreateBtnClicked");
                if (mAdaper != null) {
                    mAdaper.onCreateBtnClicked(view);
                }
            }
        });
        Toolbar toolbar = findViewById(R.id.app_toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "LifeCycle : onNewIntent()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "LifeCycle : onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "LifeCycle : onResume()");
        float density = getResources().getDisplayMetrics().density;
        Log.d(TAG, "LifeCycle : verify display, density = " + density);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "LifeCycle : onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "LifeCycle : onStop()");
        ST.FUNC.clearAll();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "LifeCycle : onDestroy()");
        mViewPager.removeOnPageChangeListener(this);
        mTabLayout = null;
        mViewPager = null;
        mAdaper = null;
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public MainActivity getActivity() {
        return this;
    }

    @Override
    public Context getAppCtx() {
        return getApplicationContext();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        AppLog.d(TAG, "onPageSelected = " + position);
        mPosition = position;
        SharedPreferenceUtil.setTabPosition(this, mPosition);
        final boolean show = mPosition == TAB_DEFAULT_POSITION;
        AniUtil.startCreateBtnAnimation(this, mCreateBtn, show, new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (animation != null) {
                    animation.cancel();
                }
                if (mCreateBtn != null) {
                    mCreateBtn.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void postOnUiThread(Runnable runnable) {
        mHandler.post(runnable);
    }

    @Override
    public void postOnUiThread(Runnable runnable, int delay) {
        mHandler.postDelayed(runnable, delay);
    }
}
