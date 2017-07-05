package com.note.learn.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ecloud.pulltozoomview.PullToZoomScrollViewEx;
import com.note.learn.R;
import com.note.learn.activity.BaseActivity;
import com.note.learn.enums.ActivityType;
import com.note.learn.utils.Constant;
import com.note.learn.utils.DialogUtil;
import com.note.learn.utils.ScreenUtil;

import java.util.Calendar;
import java.util.TimeZone;

import butterknife.BindView;

/**
 * Created by wanghui on 2016/3/30.
 */
public class MainRecordFragment extends BaseFragment implements View.OnClickListener{

    @BindView(R.id.pull_zoom_scroll)
    PullToZoomScrollViewEx pullZoomScroll;
    TextView tvHeaderCountDown;
    TextView tvCurrentDay;
    TextView tvCurrentMonth;
    TextView tvCurrentWeek;
    TextView tvSwipeCard;

    @Override
    protected int getLayout() {
        return R.layout.fragment_record;
    }

    @Override
    protected void init() {
        LinearLayout.LayoutParams localObject =
                new LinearLayout.LayoutParams(ScreenUtil.getScreenWidth(mContext), ScreenUtil.dip2px(mContext, 180f));
        pullZoomScroll.setHeaderLayoutParams(localObject);

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View header = inflater.inflate(R.layout.pull_zoom_header, null, false);
        View content = inflater.inflate(R.layout.pull_zoom_content, null, false);
        View zoomView = inflater.inflate(R.layout.pull_zoom_view, null, false);
        pullZoomScroll.setHeaderView(header);
        pullZoomScroll.setZoomView(zoomView);
        pullZoomScroll.setScrollContentView(content);

        tvHeaderCountDown = (TextView) header.findViewById(R.id.tv_header_count_down);
        tvCurrentMonth = (TextView) header.findViewById(R.id.tv_current_month);
        tvCurrentWeek = (TextView) header.findViewById(R.id.tv_current_week);
        tvCurrentDay = (TextView) header.findViewById(R.id.tv_current_day);
        tvSwipeCard = (TextView) header.findViewById(R.id.tv_swipe_card);

        header.findViewById(R.id.btn_calendar).setOnClickListener(this);
        header.findViewById(R.id.btn_share).setOnClickListener(this);
        header.findViewById(R.id.tv_swipe_card).setOnClickListener(this);
        content.findViewById(R.id.rl_learn_plans).setOnClickListener(this);
        content.findViewById(R.id.rl_course_plans).setOnClickListener(this);
        content.findViewById(R.id.rl_practice_plans).setOnClickListener(this);
        content.findViewById(R.id.rl_course_record).setOnClickListener(this);
        content.findViewById(R.id.rl_practice_record).setOnClickListener(this);

        initDate();
    }

    private void goActivity(ActivityType type) {
        Intent intent = new Intent(getActivity(), BaseActivity.class);
        intent.putExtra(Constant.ACTIVITY_TYPE, type);
        startActivity(intent);
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
        tvCurrentWeek.setText(way);
        tvCurrentDay.setText(day < 10 ? ("0" + day) : String.valueOf(day));
        tvCurrentMonth.setText(month < 10 ? ("0" + month + "月") : String.valueOf(month) + "月");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_calendar:
                goActivity(ActivityType.CalendarFragment);
                break;
            case R.id.btn_share:
                DialogUtil.shareDailog(getActivity(), new DialogUtil.OnDialogClickListener() {
                    @Override
                    public void onclick() {

                    }
                });
                break;
            case R.id.tv_swipe_card:
                tvSwipeCard.setText("今天已打卡");
                tvSwipeCard.setBackgroundResource(R.drawable.oval_white_border);
                break;
            case R.id.rl_learn_plans:
                goActivity(ActivityType.LearnPlan);
                break;
            case R.id.rl_course_plans:
                goActivity(ActivityType.CoursePlan);
                break;
            case R.id.rl_practice_plans:
                goActivity(ActivityType.PracticePlan);
                break;
            case R.id.rl_course_record:
                goActivity(ActivityType.CourseRecord);
                break;
            case R.id.rl_practice_record:
                goActivity(ActivityType.PracticeRecord);
                break;
        }
    }
}
