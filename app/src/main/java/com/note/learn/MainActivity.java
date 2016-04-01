package com.note.learn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ecloud.pulltozoomview.PullToZoomScrollViewEx;
import com.note.learn.activity.BaseActivity;
import com.note.learn.enums.ActivityType;
import com.note.learn.fragment.MainAnaslysisFragment;
import com.note.learn.fragment.MainMineFragment;
import com.note.learn.fragment.MainRecordFragment;
import com.note.learn.utils.Constant;
import com.note.learn.utils.DialogUtil;
import com.note.learn.utils.ScreenUtil;

import java.util.Calendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private TextView mTvCountDown, mTvCurrentDay, mTvCurrentMonth, mTvCurrentWeek, mTvWipeCard;
    private FragmentTransaction mTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();
        initDate();
    }

    private void initWidget() {
        ((RadioGroup) findViewById(R.id.tab_layout)).setOnCheckedChangeListener(this);
        PullToZoomScrollViewEx scrollViewEx = (PullToZoomScrollViewEx) findViewById(R.id.pull_zoom_scroll);
        LinearLayout.LayoutParams localObject = new LinearLayout.LayoutParams(ScreenUtil.getScreenWidth(this), ScreenUtil.dip2px(this, 200f));
        scrollViewEx.setHeaderLayoutParams(localObject);

        View header = LayoutInflater.from(this).inflate(R.layout.pull_zoom_header, null, false);
        View content = LayoutInflater.from(this).inflate(R.layout.pull_zoom_content, null, false);
        View zoomView = LayoutInflater.from(this).inflate(R.layout.pull_zoom_view, null, false);
        scrollViewEx.setHeaderView(header);
        scrollViewEx.setZoomView(zoomView);
        scrollViewEx.setScrollContentView(content);

        MainRecordFragment fragment = new MainRecordFragment();
        mTransaction = getSupportFragmentManager().beginTransaction();
        mTransaction.replace(R.id.content_layout, fragment);
        mTransaction.commitAllowingStateLoss();

        mTvCountDown = (TextView) header.findViewById(R.id.tv_header_count_down);
        mTvCurrentDay = (TextView) header.findViewById(R.id.tv_current_day);
        mTvCurrentMonth = (TextView) header.findViewById(R.id.tv_current_month);
        mTvCurrentWeek = (TextView) header.findViewById(R.id.tv_current_week);

        header.findViewById(R.id.btn_calendar).setOnClickListener(this);
        header.findViewById(R.id.btn_share).setOnClickListener(this);
        header.findViewById(R.id.tv_swipe_card).setOnClickListener(this);
    }

    private void initDate() {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        int week = c.get(Calendar.DAY_OF_WEEK);
        String way = "星期天";
        switch (week) {
            case 1:
                way = "星期天";
                break;
            case 2:
                way = "星期一";
                break;
            case 3:
                way = "星期二";
                break;
            case 4:
                way = "星期三";
                break;
            case 5:
                way = "星期四";
                break;
            case 6:
                way = "星期五";
                break;
            case 7:
                way = "星期六";
                break;
        }
        mTvCurrentWeek.setText(way);
        mTvCurrentDay.setText(day < 10 ? ("0" + day) : String.valueOf(day));
        mTvCurrentMonth.setText(month < 10 ? ("0" + month + "月") : String.valueOf(month) + "月");

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
            mTransaction.replace(R.id.content_layout, fragment);
            mTransaction.commitAllowingStateLoss();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_calendar:
                Intent intent = new Intent(MainActivity.this, BaseActivity.class);
                intent.putExtra(Constant.ACTIVITY_TYPE, ActivityType.CalendarFragment);
                startActivity(intent);
                break;
            case R.id.btn_share:
                DialogUtil.shareDailog(MainActivity.this, new DialogUtil.OnDialogClickListener() {
                    @Override
                    public void onclick() {

                    }
                });
                break;
            case R.id.tv_swipe_card:
                break;
        }
    }
}
