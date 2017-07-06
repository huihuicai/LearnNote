package com.note.learn.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ybm on 2017/7/6.
 */

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<T> mData = new ArrayList<>();
    private int mResLayout;

    public BaseAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mResLayout = getViewHolderLayout();
    }

    public BaseAdapter(Context context, List<T> data) {
        this(context);
        if (data != null) {
            mData.addAll(data);
        }
    }

    public void setData(List<T> data) {
        mData.clear();
        if (data != null) {
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }

    public void setMoreData(List<T> data) {
        if (data != null) {
            mData.addAll(data);
            notifyDataSetChanged();
        }
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = mInflater.inflate(mResLayout, viewGroup, false);
        return new BaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder baseViewHolder, int position) {
        if (mData != null && getItemCount() > position) {
            bindData(baseViewHolder, mData.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    protected abstract int getViewHolderLayout();

    protected abstract void bindData(BaseViewHolder holder, T data);

}
