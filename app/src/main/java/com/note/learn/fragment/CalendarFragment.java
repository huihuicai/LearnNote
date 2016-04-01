package com.note.learn.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.note.learn.R;
import com.note.learn.utils.ToolUtil;
import com.note.learn.view.CalendarView;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by wanghui on 2016/3/31.
 */
public class CalendarFragment extends Fragment implements View.OnClickListener {

    private GridView mGridView;
    private TextView mTvCalendarDate;
    private Calendar mCal = Calendar.getInstance();

    private CalendarAdapter mAdapter;
    private LayoutInflater mInflater;
    private int mYear;
    private int mMonth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mInflater = inflater;
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        initWidget(view);
        switchCalendar(false, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        switchCalendar(false, false);
    }

    private void initWidget(View v) {
        mTvCalendarDate = (TextView) v.findViewById(R.id.tv_calendar_date);
        mGridView = (GridView) v.findViewById(R.id.gv_calendar);
        mAdapter = new CalendarAdapter();
        mGridView.setAdapter(mAdapter);
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
        setCalendarData(mYear, mMonth);
    }

    public void setCalendarData(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        cal.set(year, month - 1, 1);
        int preMonth = (month - 1) > 0 ? (month - 1) : 12;
        int preYear = (month - 1) > 0 ? year : year - 1;
        int preMonthNum = ToolUtil.getDateNumByMonth(preYear, preMonth);
        int currentMonthNum = ToolUtil.getDateNumByMonth(year, month);

        int week = cal.get(Calendar.DAY_OF_WEEK);
        int firstPosition = week - 1;
        int minRow = (currentMonthNum + firstPosition) % 7;
        int row = (minRow == 0 ? 0 : 1) + (currentMonthNum + firstPosition) / 7;
        int firstNum = (firstPosition == 0) ? 1 : (preMonthNum - firstPosition + 1);
        boolean[] select = new boolean[currentMonthNum];
        for (int i = 0; i < currentMonthNum; i++) {
            if (i % 2 == 0) {
                select[i] = true;
            } else {
                select[i] = false;
            }
        }
        mAdapter.setData(row, firstNum, firstPosition, currentMonthNum, select);
    }

    private class CalendarAdapter extends BaseAdapter {
        private boolean[] mSelected;
        private int mRow, mFirstNum, mFirstPosition, mMaxDay;

        public void setData(int row, int firstNum, int firstPosition, int maxDay, boolean[] select) {
            mRow = row;
            mFirstNum = firstNum;
            mFirstPosition = firstPosition;
            mMaxDay = maxDay;
            mSelected = select;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return mRow * 7;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = mInflater.inflate(R.layout.view_calendar_item, parent, false);
                holder.mBgSelect = convertView.findViewById(R.id.bg_selected);
                holder.mIvSelect = convertView.findViewById(R.id.iv_check);
                holder.mTvCalDay = (TextView) convertView.findViewById(R.id.tv_calendar_day);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (position < mFirstPosition || position >= mMaxDay + mFirstPosition) {
                String text = position < mFirstPosition ? String.valueOf(mFirstNum + position) : String.valueOf(position - mMaxDay - mFirstPosition + 1);
                holder.mTvCalDay.setText(text);
                holder.mTvCalDay.setTextColor(getResources().getColor(R.color.color_gray));
            } else {
                holder.mTvCalDay.setText(String.valueOf(position - mFirstPosition + 1));
                holder.mTvCalDay.setTextColor(getResources().getColor(R.color.color_black));
            }

            if (mSelected != null) {
                if (position >= mFirstPosition && position < mMaxDay + mFirstPosition){
                    if (mSelected[position - mFirstPosition]) {
                        holder.mIvSelect.setVisibility(View.VISIBLE);
                        holder.mBgSelect.setVisibility(View.VISIBLE);
                        holder.mTvCalDay.setTextColor(Color.WHITE);
                    } else {
                        holder.mIvSelect.setVisibility(View.GONE);
                        holder.mBgSelect.setVisibility(View.GONE);
                        holder.mTvCalDay.setTextColor(getResources().getColor(R.color.color_black));
                    }
                }else{
                    holder.mIvSelect.setVisibility(View.GONE);
                    holder.mBgSelect.setVisibility(View.GONE);
                    holder.mTvCalDay.setTextColor(getResources().getColor(R.color.color_gray));
                }
            }

            return convertView;
        }

        class ViewHolder {
            TextView mTvCalDay;
            View mIvSelect,mBgSelect;
        }
    }

}
