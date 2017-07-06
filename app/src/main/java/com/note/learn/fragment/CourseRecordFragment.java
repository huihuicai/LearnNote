package com.note.learn.fragment;

import com.note.learn.R;
import com.note.learn.view.VRecyclerView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wanghui on 2016/3/31.
 */
public class CourseRecordFragment extends BaseFragment {

    @BindView(R.id.rv_learn_plan)
    VRecyclerView rvLearnPlan;

    @Override
    protected int getLayout() {
        return R.layout.fragment_plan_or_record;
    }

    @Override
    protected void init() {

    }

    @OnClick(R.id.btn_add)
    public void onViewClicked() {
    }
}
