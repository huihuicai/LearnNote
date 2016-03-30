package com.note.learn.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.note.learn.R;
import com.note.learn.activity.CoursePlanActivity;
import com.note.learn.activity.CourseRecordActivity;
import com.note.learn.activity.LearnPlanActivity;
import com.note.learn.activity.PracticePlanActivity;
import com.note.learn.activity.PracticeRecordActivity;

/**
 * Created by wanghui on 2016/3/30.
 */
public class RecordFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_record, container, false);
        initWidget(view);
        return view;
    }

    private void initWidget(View v) {
        v.findViewById(R.id.rl_learn_plans).setOnClickListener(this);
        v.findViewById(R.id.rl_course_plans).setOnClickListener(this);
        v.findViewById(R.id.rl__practice_plans).setOnClickListener(this);
        v.findViewById(R.id.rl_course_record).setOnClickListener(this);
        v.findViewById(R.id.rl__practice_record).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.rl_learn_plans:
                intent = new Intent(getActivity(), LearnPlanActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_course_plans:
                intent = new Intent(getActivity(), CoursePlanActivity.class);
                startActivity(intent);
                break;
            case R.id.rl__practice_plans:
                intent = new Intent(getActivity(), PracticePlanActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_course_record:
                intent = new Intent(getActivity(), CourseRecordActivity.class);
                startActivity(intent);
                break;
            case R.id.rl__practice_record:
                intent = new Intent(getActivity(), PracticeRecordActivity.class);
                startActivity(intent);
                break;
        }
    }
}
