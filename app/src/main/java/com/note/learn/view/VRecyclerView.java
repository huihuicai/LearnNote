package com.note.learn.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * Created by ybm on 2017/7/6.
 */

public class VRecyclerView extends XRecyclerView {
    public VRecyclerView(Context context) {
        this(context, null);
    }

    public VRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        setLayoutManager(manager);
        setPullRefreshEnabled(true);
        setRefreshProgressStyle(ProgressStyle.CubeTransition);
        setLoadingMoreProgressStyle(ProgressStyle.BallScale);
    }

}
