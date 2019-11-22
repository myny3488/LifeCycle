package com.personal.lifecycle.util;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import com.personal.lifecycle.R;

public class AniUtil {
    public static void startCreateBtnAnimation(Context context, View view, boolean show, Animation.AnimationListener listener) {
        if (view == null) {
            return;
        }
        AnimationSet animSet = new AnimationSet(true);
        animSet.setInterpolator(new DecelerateInterpolator());
        animSet.setAnimationListener(listener);
        animSet.setDuration(300);

        float fromX = show ? 0f : 1f;
        float toX = show ? 1f : 0f;
        float fromY = show ? 0f : 1f;
        float toY = show ? 1f : 0f;
        float pivotX = 0.5f;
        float pivotY = 0.5f;
        animSet.addAnimation(new ScaleAnimation(fromX, toX, fromY, toY, pivotX, pivotY));

        float margin = context.getResources().getDimension(R.dimen.plus_btn_margin);
        int viewW = view.getWidth();
        int viewH = view.getHeight();
        fromX = show ? viewW / 2f : 0f;
        toX = show ? 0f : viewW / 2f;
        fromY = show ? viewH + margin : 0f;
        toY = show ? 0f : viewH + margin;
        animSet.addAnimation(new TranslateAnimation(fromX, toX, fromY, toY));

        view.startAnimation(animSet);
    }
}
