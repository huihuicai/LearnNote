package com.note.learn.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.note.learn.R;

/**
 * Created by wanghui on 2016/3/31.
 */
public class SettingFragment extends Fragment implements View.OnClickListener {

    private TextView mTvResetNOtify, mTvCountTime, mTvEveryday, mTvCloseAd, mTvCountDown;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        initWidget(view);
        return view;
    }

    private void initWidget(View v) {
        mTvResetNOtify = (TextView) v.findViewById(R.id.tv_reset_status);
        mTvCountTime = (TextView) v.findViewById(R.id.tv_count_time_status);
        mTvEveryday = (TextView) v.findViewById(R.id.tv_everyday_status);
        mTvCloseAd = (TextView) v.findViewById(R.id.tv_close_ad_status);
        mTvCountDown = (TextView) v.findViewById(R.id.tv_count_down_status);

        v.findViewById(R.id.rl_reset_notify).setOnClickListener(this);
        v.findViewById(R.id.rl_count_time).setOnClickListener(this);
        v.findViewById(R.id.rl_everyday).setOnClickListener(this);
        v.findViewById(R.id.rl_close_ad).setOnClickListener(this);
        v.findViewById(R.id.rl_count_down).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rl_reset_notify:
                break;
            case R.id.rl_count_time:
                break;
            case R.id.rl_everyday:
                break;
            case R.id.rl_close_ad:
                break;
            case R.id.rl_count_down:
                break;
        }
    }
}
