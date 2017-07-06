package com.note.learn.activity;

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

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by wanghui on 2016/3/30.
 */
public class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.tv_title)
    TextView tvTitle;

    private ActivityType mCurrentType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ButterKnife.bind(this);
        mCurrentType = (ActivityType) getIntent().getSerializableExtra(Constant.ACTIVITY_TYPE);
        findViewById(R.id.btn_back).setOnClickListener(this);
        ScreenUtil.initSystemBar(this);

        if (mCurrentType != null) {
            tvTitle.setText(mCurrentType.getValue());
            Fragment fragment = ActivityType.getFragmentInstance(mCurrentType);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fl_content, fragment);
            transaction.commitAllowingStateLoss();
            transaction.addToBackStack(null);
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
