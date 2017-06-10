package com.note.learn.fragment;

import android.view.View;
import android.widget.TextView;

import com.note.learn.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wanghui on 2016/3/31.
 */
public class SettingFragment extends BaseFragment {

    @BindView(R.id.tv_reset_status)
    TextView tvResetStatus;
    @BindView(R.id.tv_count_time_status)
    TextView tvCountTimeStatus;
    @BindView(R.id.tv_everyday_status)
    TextView tvEverydayStatus;
    @BindView(R.id.tv_close_ad_status)
    TextView tvCloseAdStatus;
    @BindView(R.id.tv_count_down_status)
    TextView tvCountDownStatus;

    @Override
    protected int getLayout() {
        return R.layout.fragment_setting;
    }

    @Override
    protected void init() {

    }

    @OnClick({R.id.rl_reset_notify, R.id.rl_count_time, R.id.rl_everyday, R.id.rl_close_ad, R.id.rl_count_down})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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
