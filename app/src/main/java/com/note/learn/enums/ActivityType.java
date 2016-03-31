package com.note.learn.enums;

import android.support.v4.app.Fragment;

import com.note.learn.fragment.AboutFragment;
import com.note.learn.fragment.CalendarFragment;
import com.note.learn.fragment.CoursePlanFragment;
import com.note.learn.fragment.CourseRecordFragment;
import com.note.learn.fragment.FavoriteFragment;
import com.note.learn.fragment.LearnPlanFragment;
import com.note.learn.fragment.MineInformationFragment;
import com.note.learn.fragment.PracticePlanFragment;
import com.note.learn.fragment.PracticeRecordFragment;
import com.note.learn.fragment.SettingFragment;

/**
 * Created by wanghui on 2016/3/31.
 */
public enum ActivityType {
    LearnPlan("学习计划"), CoursePlan("课程计划"), PracticePlan("巩固计划"), CourseRecord("课程记录"), PracticeRecord("巩固练习记录"),
    MineInformation("个人信息"), MineSetting("设置"), MineFavorite("收藏"), MineAbout("关于"), CalendarFragment("日历");

    private String mValue;

    ActivityType(String value) {
        mValue = value;
    }

    public String getValue() {
        return mValue;
    }

    public static Fragment getFragmentInstance(ActivityType type) {
        switch (type) {
            case LearnPlan:
                return new LearnPlanFragment();
            case CoursePlan:
                return new CoursePlanFragment();
            case PracticePlan:
                return new PracticePlanFragment();
            case CourseRecord:
                return new CourseRecordFragment();
            case PracticeRecord:
                return new PracticeRecordFragment();
            case MineInformation:
                return new MineInformationFragment();
            case MineSetting:
                return new SettingFragment();
            case MineFavorite:
                return new FavoriteFragment();
            case MineAbout:
                return new AboutFragment();
            case CalendarFragment:
                return new CalendarFragment();
            default:
                throw new IllegalArgumentException("Can't find this fragment");
        }
    }
}
