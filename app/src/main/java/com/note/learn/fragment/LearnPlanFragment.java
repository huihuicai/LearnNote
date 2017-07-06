package com.note.learn.fragment;


import android.content.Context;

import com.note.learn.R;
import com.note.learn.adapter.BaseAdapter;
import com.note.learn.adapter.BaseViewHolder;
import com.note.learn.bean.BaseBean;
import com.note.learn.view.VRecyclerView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wanghui on 2016/3/31.
 */
public class LearnPlanFragment extends BaseFragment {

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

    private class PlanAdapter extends BaseAdapter<BaseBean> {

        public PlanAdapter(Context context) {
            super(context);
        }

        @Override
        protected int getViewHolderLayout() {
            return R.layout.item_learn_plan;
        }

        @Override
        protected void bindData(BaseViewHolder holder, BaseBean data) {

        }
    }
}
