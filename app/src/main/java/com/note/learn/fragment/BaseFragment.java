package com.note.learn.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ybm on 2017/6/10.
 */

public abstract class BaseFragment extends Fragment {

    protected Context mContext;
    private Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView;
        if (getLayout() > 0) {
            contentView = inflater.inflate(getLayout(), container, false);
            unbinder = ButterKnife.bind(this, contentView);
        } else {
            contentView = super.onCreateView(inflater, container, savedInstanceState);
        }
        init();
        return contentView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    protected abstract int getLayout();

    protected abstract void init();
}
