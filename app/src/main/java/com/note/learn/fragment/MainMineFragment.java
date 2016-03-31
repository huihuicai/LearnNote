package com.note.learn.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.note.learn.R;
import com.note.learn.activity.BaseActivity;
import com.note.learn.enums.ActivityType;
import com.note.learn.utils.Constant;

/**
 * Created by wanghui on 2016/3/30.
 */
public class MainMineFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        initWidget(view);
        return view;
    }

    private void initWidget(View v) {
        v.findViewById(R.id.rl_information).setOnClickListener(this);
        v.findViewById(R.id.rl_setting).setOnClickListener(this);
        v.findViewById(R.id.rl__favorite).setOnClickListener(this);
        v.findViewById(R.id.rl__about).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), BaseActivity.class);
        switch (v.getId()) {
            case R.id.rl_information:
                intent.putExtra(Constant.ACTIVITY_TYPE, ActivityType.MineInformation);
                startActivity(intent);
                break;
            case R.id.rl__about:
                intent.putExtra(Constant.ACTIVITY_TYPE, ActivityType.MineAbout);
                startActivity(intent);
                break;
            case R.id.rl__favorite:
                intent.putExtra(Constant.ACTIVITY_TYPE, ActivityType.MineFavorite);
                startActivity(intent);
                break;
            case R.id.rl_setting:
                intent.putExtra(Constant.ACTIVITY_TYPE, ActivityType.MineSetting);
                startActivity(intent);
                break;
        }
    }
}
