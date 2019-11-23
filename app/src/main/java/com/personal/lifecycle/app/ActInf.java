package com.personal.lifecycle.app;

import android.content.Context;

import com.personal.lifecycle.fragment.BaseFragment;

import java.io.Serializable;
import java.util.ArrayList;

public interface ActInf {
    public MainActivity getActivity();

    public Context getAppCtx();

    public void postOnUiThread(Runnable runnable);

    public void postOnUiThread(Runnable runnable, int delay);
}
