package com.note.learn.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.note.learn.R;

/**
 * Created by wanghui on 2016/3/31.
 */
public class FavoriteFragment extends Fragment {

    private TabLayout mTabLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite,container,false);
        mTabLayout = (TabLayout) view.findViewById(R.id.tabs);
        mTabLayout.setLayoutMode(TabLayout.MODE_FIXED);
        mTabLayout.addTab(mTabLayout.newTab().setText("详情"));
        mTabLayout.addTab(mTabLayout.newTab().setText("资源"));
        mTabLayout.addTab(mTabLayout.newTab().setText("评论"));
        return view;
    }
}
