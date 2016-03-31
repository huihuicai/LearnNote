package com.note.learn.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.note.learn.R;
import com.note.learn.utils.ToolUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Random;
import java.util.TimeZone;

/**
 * Created by wanghui on 2016/3/30.
 */
public class MainAnaslysisFragment extends Fragment {

    private LineChart mLineChart;
    private PieChart mPieChart;

    private String[] mCourseType = {"财务管理", "经济学", "审计", "税法"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_anaslysis, container, false);
        mLineChart = (LineChart) view.findViewById(R.id.chart_learn_time);
        mPieChart = (PieChart) view.findViewById(R.id.chart_learn_ratio);

        mLineChart.setDrawGridBackground(false);
        mLineChart.setDescription("变化趋势图");
        mLineChart.setScaleEnabled(true);
        mLineChart.setPinchZoom(false);

        mPieChart.setUsePercentValues(true);
        mPieChart.setDescription("课程学时所占比例");
        mPieChart.setExtraOffsets(5, 10, 5, 5);
        mPieChart.setDragDecelerationFrictionCoef(0.95f);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initChartData();
        mLineChart.invalidate();
        mPieChart.invalidate();
    }

    private void initChartData() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        int currentMonth = cal.get(Calendar.MONTH) + 1;
        int currentDay = cal.get(Calendar.DAY_OF_MONTH);
        int preMonth = (currentMonth - 1) > 0 ? (currentMonth - 1) : 12;
        int preYear = (currentMonth - 1) > 0 ? cal.get(Calendar.YEAR) : cal.get(Calendar.YEAR) - 1;
        int preMonthDayNum = ToolUtil.getDateNumByMonth(preYear, preMonth);
        int loopDay = 0;
        ArrayList<String> xLineVals = new ArrayList<>();
        ArrayList<Entry> yLineVals = new ArrayList<>();
        for (int i = 0, temp = currentDay; i < 30; i++) {
            temp--;
            if (temp == 0) {
                continue;
            } else if (temp < 0) {
                loopDay = preMonthDayNum + temp;
                xLineVals.add(preMonth + "/" + loopDay);
            } else {
                loopDay = temp;
                xLineVals.add(currentMonth + "/" + loopDay);
            }
        }
        Collections.reverse(xLineVals);

        for (int i = 0; i < xLineVals.size(); i++) {
            float value = new Random().nextInt(5) + 1;
            yLineVals.add(new Entry(value, i));
        }
        LineDataSet set = new LineDataSet(yLineVals, "学习时间");
        set.setLineWidth(2.0f);
        set.setCircleSize(4f);
        set.setValueTextSize(10);
        set.disableDashedLine();
        LineData mLineChartData = new LineData(xLineVals, set);
        mLineChart.setData(mLineChartData);

        ArrayList<String> xPieVals = new ArrayList<>();
        ArrayList<Entry> yPieVals = new ArrayList<>();
        ArrayList<Integer> colors = new ArrayList<>();


        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);
        for (int i = 0, len = mCourseType.length; i < len; i++) {
            xPieVals.add(mCourseType[i % len]);
        }
        for (int i = 0; i < mCourseType.length; i++) {
            yPieVals.add(new Entry((float) (Math.random() * 20) / 5, i));
        }
        PieDataSet pieSet = new PieDataSet(yPieVals, "");
        pieSet.setSliceSpace(2f);
        pieSet.setSelectionShift(5f);
        pieSet.setColors(colors);

        PieData mPieChartData = new PieData(xPieVals, pieSet);
        mPieChartData.setValueFormatter(new PercentFormatter());
        mPieChartData.setValueTextSize(12f);
        mPieChartData.setValueTextColor(Color.WHITE);
        mPieChart.setData(mPieChartData);
    }

}
