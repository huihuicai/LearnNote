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
import android.widget.TextView;

import com.note.learn.R;
import com.note.learn.utils.ToolUtil;

import java.util.Calendar;

/**
 * Created by wanghui on 2016/3/31.
 */
public class CalendarView extends FrameLayout {

    private LayoutInflater mInflater;
    private GridView mGridView;
    private CalendarAdapter mAdapter;

    public CalendarView(Context context) {
        super(context);
        initWidget(context);
    }

    public CalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWidget(context);
    }

    private void initWidget(Context context) {
        mInflater = LayoutInflater.from(context);
        View view = mInflater.inflate(R.layout.view_calendar_layout, null);
        mGridView = (GridView) view.findViewById(R.id.gv_calendar);
        mAdapter = new CalendarAdapter();
        mGridView.setAdapter(mAdapter);
    }

    public void setCalendarData(int year, int month, boolean[] select) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, 1);
        int preMonth = (month - 1) > 0 ? (month - 1) : 12;
        int preYear = (month - 1) > 0 ? year : year - 1;
        int preMonthNum = ToolUtil.getDateNumByMonth(preYear, preMonth);
        int currentMonthNum = ToolUtil.getDateNumByMonth(year, month);

        int week = cal.get(Calendar.DAY_OF_WEEK);
        int firstPosition = week - 1;
        int minRow = (currentMonthNum + firstPosition) % 7;
        int row = (minRow == 0 ? 0 : 1) + (currentMonthNum + firstPosition) / 7;
        int firstNum = preMonthNum - firstPosition;
        Log.e("setData","row="+row+",firstNum="+firstNum+",firstPosition="+firstPosition+",currentMonthNum="+currentMonthNum);
        mAdapter.setData(row, firstNum, firstPosition, currentMonthNum,select);
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

            if (mSelected != null) {
                if (mSelected[position - mFirstPosition]) {
                    holder.mIvSelect.setVisibility(View.VISIBLE);
                    convertView.setBackgroundResource(R.drawable.circle_red_shape);
                } else {
                    holder.mIvSelect.setVisibility(View.GONE);
                    convertView.setBackgroundColor(Color.TRANSPARENT);
                }
            }

            return convertView;
        }

        class ViewHolder {
            TextView mTvCalDay;
            View mIvSelect;
        }
    }
}
