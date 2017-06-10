package com.note.learn.fragment;

import android.content.Intent;
import android.view.View;

import com.note.learn.R;
import com.note.learn.activity.BaseActivity;
import com.note.learn.enums.ActivityType;
import com.note.learn.utils.Constant;

import butterknife.OnClick;

/**
 * Created by wanghui on 2016/3/30.
 */
public class MainMineFragment extends BaseFragment {

    @Override
    protected int getLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void init() {

    }

    @OnClick({R.id.rl_information, R.id.rl_setting, R.id.rl_favorite, R.id.rl_about})
    public void onViewClicked(View view) {
        Intent intent = new Intent(getActivity(), BaseActivity.class);
        switch (view.getId()) {
            case R.id.rl_information:
                intent.putExtra(Constant.ACTIVITY_TYPE, ActivityType.MineInformation);
                break;
            case R.id.rl_setting:
                intent.putExtra(Constant.ACTIVITY_TYPE, ActivityType.MineSetting);
                break;
            case R.id.rl_favorite:
                intent.putExtra(Constant.ACTIVITY_TYPE, ActivityType.MineFavorite);
                break;
            case R.id.rl_about:
                intent.putExtra(Constant.ACTIVITY_TYPE, ActivityType.MineAbout);
                break;
        }
        startActivity(intent);
    }
}
