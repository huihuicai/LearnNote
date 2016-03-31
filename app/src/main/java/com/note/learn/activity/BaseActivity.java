package com.note.learn.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.note.learn.R;
import com.note.learn.enums.ActivityType;
import com.note.learn.utils.Constant;
import com.note.learn.utils.ScreenUtil;

/**
 * Created by wanghui on 2016/3/30.
 */
public class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTvTitle;
    private ActivityType mCurrentType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenUtil.setSystemBarTint(this, R.color.color_red, Color.RED);
        setContentView(R.layout.activity_base);
        mCurrentType = (ActivityType) getIntent().getSerializableExtra(Constant.ACTIVITY_TYPE);
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        findViewById(R.id.btn_back).setOnClickListener(this);

        if (mCurrentType != null) {
            mTvTitle.setText(mCurrentType.getValue());
            Fragment fragment = ActivityType.getFragmentInstance(mCurrentType);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fl_content, fragment);
            transaction.commitAllowingStateLoss();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
        }
    }
}
