package com.note.learn.fragment;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.note.learn.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ybm on 2017/6/10.
 */

public class LoadFragment extends DialogFragment {

    public final static String TAG = LoadFragment.class.getSimpleName();
    public final static int TYPE_SUCCESS = 0;
    public final static int TYPE_FAILED = 1;
    public final static int TYPE_LOADING = 2;
    public final static int TYPE_OTHER = 3;

    @BindView(R.id.iv_tip)
    ImageView ivTip;
    @BindView(R.id.tv_tip)
    TextView tvTip;

    private Unbinder unbinder;
    protected AnimationDrawable mDrawable;
    private int mLoadType = TYPE_SUCCESS;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, R.style.dialog_fragment_style);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_load, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        switch (mLoadType) {
            case TYPE_SUCCESS:
                ivTip.setImageResource(R.drawable.icon_tip_success);
                tvTip.setText("加载成功");
                mDrawable = null;
                break;
            case TYPE_FAILED:
                ivTip.setImageResource(R.drawable.icon_tip_failed);
                tvTip.setText("加载失败");
                mDrawable = null;
                break;
            case TYPE_LOADING:
                ivTip.setImageResource(R.drawable.anim_loading);
                tvTip.setText("正在加载");
                mDrawable = (AnimationDrawable) ivTip.getDrawable();
                break;
            case TYPE_OTHER:
                ivTip.setImageResource(R.drawable.icon_tip_notnet);
                tvTip.setText("其他错误");
                mDrawable = null;
                break;
        }
        if (mDrawable != null) {
            mDrawable.start();
        }
    }

    public void setType(int type) {
        mLoadType = type;
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (mDrawable != null) {
            mDrawable.stop();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
