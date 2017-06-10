package com.note.learn.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.note.learn.R;
import com.note.learn.activity.BaseActivity;
import com.note.learn.enums.ActivityType;
import com.note.learn.utils.Constant;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by wanghui on 2016/3/30.
 */
public class MainRecordFragment extends BaseFragment {

    @Override
    protected int getLayout() {
        return R.layout.fragment_record;
    }

    @Override
    protected void init() {

    }

    @OnClick({R.id.rl_learn_plans, R.id.rl_course_plans, R.id.rl_practice_plans, R.id.rl_course_record, R.id.rl_practice_record})
    public void onViewClicked(View view) {
        Intent intent = new Intent(getActivity(), BaseActivity.class);
        switch (view.getId()) {
            case R.id.rl_learn_plans:
                intent.putExtra(Constant.ACTIVITY_TYPE, ActivityType.LearnPlan);
                break;
            case R.id.rl_course_plans:
                intent.putExtra(Constant.ACTIVITY_TYPE, ActivityType.CoursePlan);
                break;
            case R.id.rl_practice_plans:
                intent.putExtra(Constant.ACTIVITY_TYPE, ActivityType.PracticePlan);
                break;
            case R.id.rl_course_record:
                intent.putExtra(Constant.ACTIVITY_TYPE, ActivityType.CourseRecord);
                break;
            case R.id.rl_practice_record:
                intent.putExtra(Constant.ACTIVITY_TYPE, ActivityType.PracticeRecord);
                break;
        }
        startActivity(intent);
    }
}
