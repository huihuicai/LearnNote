package com.note.learn;

import android.graphics.SweepGradient;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.ecloud.pulltozoomview.PullToZoomScrollViewEx;
import com.note.learn.utils.ScreenUtil;

import java.util.Calendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private FrameLayout mContentLayout;
    private TextView mTvCountDown, mTvCurrentDay, mTvCurrentMonth, mTvCurrentWeek, mTvWipeCard;

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


        mContentLayout = (FrameLayout) content.findViewById(R.id.content_layout);
        mTvCountDown = (TextView) header.findViewById(R.id.tv_header_count_down);
        mTvCurrentDay = (TextView) header.findViewById(R.id.tv_current_day);
        mTvCurrentMonth = (TextView) header.findViewById(R.id.tv_current_month);
        mTvCurrentWeek = (TextView) header.findViewById(R.id.tv_current_week);
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
        mTvCurrentMonth.setText(month < 10 ? ("0" + month+"月") : String.valueOf(month)+"月");

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_record:
                break;
            case R.id.rb_analysis:
                break;
            case R.id.rb_mine:
                break;
        }
    }
}
