package com.note.learn.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.note.learn.R;
import com.note.learn.activity.AboutActivity;
import com.note.learn.activity.FavoriteActivity;
import com.note.learn.activity.SettingActivity;

/**
 * Created by wanghui on 2016/3/30.
 */
public class MineFragment extends Fragment implements View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine,container,false);
        initWidget(view);
        return view;
    }

    private void initWidget(View v){
        v.findViewById(R.id.rl_setting).setOnClickListener(this);
        v.findViewById(R.id.rl__favorite).setOnClickListener(this);
        v.findViewById(R.id.rl__about).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.rl__about:
                intent = new Intent(getActivity(), AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.rl__favorite:
                intent = new Intent(getActivity(), FavoriteActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_setting:
                intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;
        }
    }
}
