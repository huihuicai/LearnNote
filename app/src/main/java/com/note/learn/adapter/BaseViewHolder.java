package com.note.learn.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by ybm on 2017/7/6.
 */

public class BaseViewHolder extends RecyclerView.ViewHolder {

    private View rootView;

    public BaseViewHolder(View itemView) {
        super(itemView);
        rootView = itemView;
    }

    public View getRootView() {
        return rootView;
    }

    public TextView getTextView(int id) {
        return (TextView) rootView.findViewById(id);
    }

    public ImageView getImageView(int id) {
        return (ImageView) rootView.findViewById(id);
    }

    public View getView(int id) {
        return rootView.findViewById(id);
    }
}
