package com.note.learn;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.note.learn.fragment.MainAnaslysisFragment;
import com.note.learn.fragment.MainMineFragment;
import com.note.learn.fragment.MainRecordFragment;
import com.note.learn.utils.ScreenUtil;

public class MainActivity extends AppCompatActivity implements
        RadioGroup.OnCheckedChangeListener {

    private FragmentTransaction mTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ScreenUtil.initSystemBar(this);
        initWidget();
    }

    private void initWidget() {
        ((RadioGroup) findViewById(R.id.tab_layout)).setOnCheckedChangeListener(this);
        MainRecordFragment fragment = new MainRecordFragment();
        mTransaction = getSupportFragmentManager().beginTransaction();
        mTransaction.replace(R.id.fl_content, fragment);
        mTransaction.commitAllowingStateLoss();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        Fragment fragment = null;
        switch (checkedId) {
            case R.id.rb_record:
                fragment = new MainRecordFragment();
                break;
            case R.id.rb_analysis:
                fragment = new MainAnaslysisFragment();
                break;
            case R.id.rb_mine:
                fragment = new MainMineFragment();
                break;
        }

        if (fragment != null) {
            mTransaction = getSupportFragmentManager().beginTransaction();
            mTransaction.replace(R.id.fl_content, fragment);
            mTransaction.commitAllowingStateLoss();
        }
    }
}
