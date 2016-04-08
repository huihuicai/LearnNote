package com.note.learn.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.note.learn.R;
import com.note.learn.utils.ToolUtil;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by wanghui on 2016/3/31.
 */
public class CalendarView extends FrameLayout implements View.OnClickListener {

    private GridView mGridView;
    private TextView mTvCalendarDate;
    private ImageButton mBtnLeft, mBtnRight;
    private CalendarAdapter mAdapter;
    private Calendar mCal = Calendar.getInstance();
    private int mYear, mMonth;
    private MonthType mCurrentType = MonthType.current;

    private OnDataChangeListener mListener;

    public enum MonthType {
        before, current, after
    }

    public CalendarView(Context context) {
        super(context);
        initWidget(context);
        initData(context);
    }

    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWidget(context);
        initData(context);
    }

    private void initWidget(Context context) {
        this.setFocusable(true);
        View view = LayoutInflater.from(context).inflate(R.layout.view_calendar_layout, this, true);
        mTvCalendarDate = (TextView) view.findViewById(R.id.tv_calendar_date);
        mGridView = (GridView) view.findViewById(R.id.gv_calendar);
        mBtnLeft = (ImageButton) view.findViewById(R.id.btn_left_arrow);
        mBtnRight = (ImageButton) view.findViewById(R.id.btn_right_arrow);
        mBtnLeft.setOnClickListener(this);
        mBtnRight.setOnClickListener(this);

    }

    private void initData(Context context) {
        mAdapter = new CalendarAdapter(context);
        mGridView.setAdapter(mAdapter);
        switchCalendar();
        boolean[] select = new boolean[30];
        for (int i = 0; i < 30; i++) {
            if (i % 2 == 0) {
                select[i] = true;
            } else {
                select[i] = false;
            }
        }
        setCalendarData(mYear, mMonth, select);
    }

    @Override
    public void onClick(View v) {
        if (v == mBtnLeft) {
            mCurrentType = MonthType.before;
            switchCalendar();
        } else if (v == mBtnRight) {
            mCurrentType = MonthType.after;
            switchCalendar();
        }

        if (mListener != null) {
            mListener.dataChange(mYear, mMonth);
        }
    }

    private void switchCalendar() {
        int[] data;
        switch (mCurrentType) {
            case before:
                data = ToolUtil.getPreMonth(mYear, mMonth);
                mYear = data[0];
                mMonth = data[1];
                break;
            case current:
                mYear = mCal.get(Calendar.YEAR);
                mMonth = mCal.get(Calendar.MONTH) + 1;
                break;
            case after:
                data = ToolUtil.getNextMonth(mYear, mMonth);
                mYear = data[0];
                mMonth = data[1];
                break;
        }
    }

    public void setCalendarData(int year, int month, boolean[] select) {
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
        if (select == null || select.length != currentMonthNum) {
            select = null;
        }
        String mon = month > 9 ? String.valueOf(month) : "0" + month;
        mTvCalendarDate.setText(year + "年" + mon + "月");
        mAdapter.setData(row, firstNum, firstPosition, currentMonthNum, select);
    }


    private class CalendarAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        private boolean[] mSelected;
        private int mRow, mFirstNum, mFirstPosition, mMaxDay;

        public CalendarAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
        }

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
            if (position < mFirstPosition || position > mMaxDay + mFirstPosition) {
                String text = position < mFirstPosition ? String.valueOf(mFirstNum + position) : String.valueOf(mMaxDay + mFirstPosition);
                holder.mTvCalDay.setText(text);
                holder.mTvCalDay.setTextColor(getResources().getColor(R.color.color_gray));
            } else {
                holder.mTvCalDay.setText(String.valueOf(position - mFirstPosition + 1));
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
                if (position >= mFirstPosition && position < mMaxDay + mFirstPosition) {
                    if (mSelected[position - mFirstPosition]) {
                        holder.mIvSelect.setVisibility(View.VISIBLE);
                        holder.mBgSelect.setVisibility(View.VISIBLE);
                        holder.mTvCalDay.setTextColor(Color.WHITE);
                    } else {
                        holder.mIvSelect.setVisibility(View.GONE);
                        holder.mBgSelect.setVisibility(View.GONE);
                        holder.mTvCalDay.setTextColor(getResources().getColor(R.color.color_black));
                    }
                } else {
                    holder.mIvSelect.setVisibility(View.GONE);
                    holder.mBgSelect.setVisibility(View.GONE);
                    holder.mTvCalDay.setTextColor(getResources().getColor(R.color.color_gray));
                }
            } else {
                if (position >= mFirstPosition && position < mMaxDay + mFirstPosition) {
                    holder.mIvSelect.setVisibility(View.GONE);
                    holder.mBgSelect.setVisibility(View.GONE);
                    holder.mTvCalDay.setTextColor(getResources().getColor(R.color.color_black));
                } else {
                    holder.mIvSelect.setVisibility(View.GONE);
                    holder.mBgSelect.setVisibility(View.GONE);
                    holder.mTvCalDay.setTextColor(getResources().getColor(R.color.color_gray));
                }
            }

            return convertView;
        }

        class ViewHolder {
            TextView mTvCalDay;
            View mIvSelect, mBgSelect;
        }
    }

    public void setOnDataChangeListener(OnDataChangeListener listener) {
        mListener = listener;
    }

    public interface OnDataChangeListener {
        void dataChange(int year, int month);
    }
}
