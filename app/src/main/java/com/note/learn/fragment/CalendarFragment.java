package com.note.learn.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.note.learn.R;
import com.note.learn.utils.ToolUtil;
import com.note.learn.view.CalendarView;

import java.util.Calendar;
import java.util.TooManyListenersException;

/**
 * Created by wanghui on 2016/3/31.
 */
public class CalendarFragment extends Fragment implements View.OnClickListener {

    private TextView mTvCalendarDate;
    private CalendarView mViewCaldendar;
    private Calendar mCal = Calendar.getInstance();
    private int mYear;
    private int mMonth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar_layout, container, false);
        initWidget(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        switchCalendar(false, false);
    }

    private void initWidget(View v) {
        mTvCalendarDate = (TextView) v.findViewById(R.id.tv_calendar_date);
        mViewCaldendar = (CalendarView) v.findViewById(R.id.calendar_view);
        v.findViewById(R.id.iv_left_arrow).setOnClickListener(this);
        v.findViewById(R.id.iv_right_arrow).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_left_arrow:
                switchCalendar(true, false);
                break;
            case R.id.iv_right_arrow:
                switchCalendar(false, true);
                break;
        }
    }


    private void switchCalendar(boolean isPre, boolean isNext) {
        if (isNext && !isPre) {
            int[] data = ToolUtil.getNextMonth(mYear, mMonth);
            mYear = data[0];
            mMonth = data[1];
        } else if (!isNext && isPre) {
            int[] data = ToolUtil.getPreMonth(mYear, mMonth);
            mYear = data[0];
            mMonth = data[1];
        } else {
            mYear = mCal.get(Calendar.YEAR);
            mMonth = mCal.get(Calendar.MONTH) + 1;
        }
        String month = mMonth > 9 ? String.valueOf(mMonth) : "0" + mMonth;
        mTvCalendarDate.setText(mYear + "年" + month + "月");
        mViewCaldendar.setCalendarData(mYear, mMonth, null);
    }
}
