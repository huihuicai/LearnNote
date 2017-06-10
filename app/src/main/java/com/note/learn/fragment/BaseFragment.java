package com.note.learn.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ybm on 2017/6/10.
 */

public abstract class BaseFragment extends Fragment {

    protected LoadFragment mDialog;
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
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    public void showDialog() {
        if (mDialog == null) {
            mDialog = new LoadFragment();
        }
        FragmentManager fm = getChildFragmentManager();
        if (fm.findFragmentByTag(LoadFragment.TAG) != null) {
            mDialog = (LoadFragment) fm.findFragmentByTag(LoadFragment.TAG);
        }
        mDialog.setType(LoadFragment.TYPE_LOADING);
        if (!mDialog.isAdded()) {
            mDialog.show(getChildFragmentManager(), LoadFragment.TAG);
        }
    }

    public void cancelDialog() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

    protected abstract int getLayout();

    protected abstract void init();
}
