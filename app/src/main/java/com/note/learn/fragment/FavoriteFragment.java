package com.note.learn.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.note.learn.R;
import com.note.learn.view.CalendarView;

import java.util.Calendar;

/**
 * Created by wanghui on 2016/3/31.
 */
public class FavoriteFragment extends Fragment {

    private CalendarView mCalendarView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        mCalendarView = (CalendarView) view.findViewById(R.id.view_calendar);
        mCalendarView.setOnDataChangeListener(new CalendarView.OnDataChangeListener() {
            @Override
            public void dataChange(int year, int month) {
                mCalendarView.setCalendarData(year,month,null);
            }
        });
        return view;
    }


}
