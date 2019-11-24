package com.personal.lifecycle.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.personal.lifecycle.constants.AppConstants.*;

import com.personal.lifecycle.R;
import com.personal.lifecycle.app.ActInf;
import com.personal.lifecycle.components.LifeEvent;
import com.personal.lifecycle.util.AppLog;
import com.personal.lifecycle.util.SharedPreferenceUtil;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends BaseFragment implements FragmentInf {
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private ArrayList<LifeEvent> mEventList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.list_fragment_layout;
    }

    @Override
    public String getPageMode() {
        return TAB_LIST;
    }

    @Override
    public CharSequence getTabTitle(Context context) {
        return context.getString(R.string.tab_title_list);
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferenceUtil.setEventList(getContext(), makeEventFlatten());
    }

    private String makeEventFlatten() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < mEventList.size(); i++) {
            builder.append(mEventList.get(i).flatten());
            if (i < mEventList.size() - 1) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    @Override
    public void init() {
        super.init();
        String list = SharedPreferenceUtil.getEventList(getActivity());
        if (list != null && !"".equals(list)) {
            String[] eventList = list.split("\n");
            for (int i = 0; i < eventList.length; i++) {
                String[] unflattend = eventList[i].split(";");
                LifeEvent event = new LifeEvent.Builder(unflattend[0], null, null)
                        .build();
                mEventList.add(event);
            }
        }
    }

    @Override
    protected void initUI(View baseView) {
        mRecyclerView = baseView.findViewById(R.id.list_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setHasFixedSize(false);
        mAdapter = new RecyclerViewAdapter(this).set(mEventList);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void startEventCreateActivity() {
        Intent intent = new Intent(ACTION_EVENT_CREATE);
        intent.setPackage(APP_PACKAGE);
        startActivityForResult(intent, REQUEST_CODE_EVENT_CREATE);
    }

    @Override
    public List<LifeEvent> getEventList() {
        return mEventList;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        AppLog.d(TAG, "onActivityResult = " + requestCode);
        switch (requestCode) {
            case REQUEST_CODE_EVENT_CREATE:
                if (resultCode == Activity.RESULT_OK) {
                    postOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String title = "test" + mEventList.size();
                            LifeEvent event = new LifeEvent.Builder(title, null, null)
                                    .build();
                            mEventList.add(event);
                            mAdapter.set(mEventList).notifyItemInserted(mEventList.size() - 1);
                        }
                    });
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onCreateBtnClicked(View view) {
        startEventCreateActivity();
    }
}
