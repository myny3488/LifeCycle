package com.personal.lifecycle.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.personal.lifecycle.R;
import com.personal.lifecycle.components.LifeEvent;
import com.personal.lifecycle.util.AppLog;

import java.util.List;

import static com.personal.lifecycle.constants.AppConstants.TAG;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private FragmentInf mInf;
    private List<LifeEvent> mList;

    public RecyclerViewAdapter(FragmentInf inf) {
        mInf = inf;
    }

    public RecyclerViewAdapter set(List<LifeEvent> list) {
        AppLog.d(TAG, "set = " + list.size());
        mList = list;
        return this;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AppLog.d(TAG, "onCreateViewHolder");
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.recycler_content_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AppLog.d(TAG, "onBindViewHolder, position = " + position);
        if (holder == null || mList == null) {
            return;
        }
        if (position >= mList.size()) {
            return;
        }
        holder.mTextView.setText(mList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.text_view);
        }
    }
}
