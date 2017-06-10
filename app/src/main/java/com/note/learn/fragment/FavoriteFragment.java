package com.note.learn.fragment;

import android.support.design.widget.TabLayout;

import com.note.learn.R;

import butterknife.BindView;

/**
 * Created by wanghui on 2016/3/31.
 */
public class FavoriteFragment extends BaseFragment {

    @BindView(R.id.tabs)
    TabLayout tabs;

    @Override
    protected int getLayout() {
        return R.layout.fragment_favorite;
    }

    @Override
    protected void init() {
        tabs.addTab(tabs.newTab().setText("详情"));
        tabs.addTab(tabs.newTab().setText("资源"));
        tabs.addTab(tabs.newTab().setText("评论"));
    }

}
