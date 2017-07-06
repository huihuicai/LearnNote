package com.note.learn.view;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.ScrollView;

/**
 * Created by ybm on 2017/7/3.
 */

/**
 * 手势：下拉的时候，move的距离按照阻尼系数缩放
 * 松手：属性动画的方式进行回归弹
 */
public class PullScrollView extends ScrollView {

    private final float MAX_SCALE = 1.5f;
    private final float MIN_SCALE = 1.0f;
    private final float BASE_DUMP = 0.3f;

    private View mScaleView;
    private float mTouchSlop;
    private int mScaleViewHeight;
    private ViewGroup.LayoutParams mParams;
    private float mScale, mLastX, mLastY, mMoveDelta, mDumpFactor;
    private boolean mIsDrag = false;
    private ScaleRunnable mScaleRunnable;

    public PullScrollView(Context context) {
        this(context, null);
    }

    public PullScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        ViewConfiguration config = ViewConfiguration.get(context);
        mTouchSlop = config.getScaledTouchSlop();
        mScaleRunnable = new ScaleRunnable();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() < 1 || getChildCount() > 1) {
            throw new IllegalArgumentException("This view must contain a child");
        }
        if (getChildAt(0) instanceof ViewGroup) {
            mScaleView = ((ViewGroup) getChildAt(0)).getChildAt(0);
        } else {
            throw new IllegalArgumentException("This view must contain child");
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mScaleView != null && mScaleViewHeight <= 0) {
            mScaleViewHeight = mScaleView.getMeasuredHeight();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mIsDrag = false;
                mLastX = event.getX();
                mLastY = event.getY();
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(event.getY() - mLastY) > (Math.abs(event.getX() - mLastX))
                        && Math.abs(event.getY() - mLastY) > mTouchSlop) {
                    mIsDrag = true;
                }
                break;
        }
        return mIsDrag;
    }


    /**
     * 1.每次滑动一点距离，利用requestLayout重新来放置
     * 2.松手之后通过一个动画的方式来缩放
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (mIsDrag) {
                    mLastY = ev.getY();
                    return true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (!mScaleRunnable.mIsFinished) {
                    mScaleRunnable.endScale();
                }
                mMoveDelta = ev.getY() - mLastY;
                mDumpFactor = BASE_DUMP + mDumpInterpolator.getInterpolation(mScaleView.getBottom() / (1.5f * mScaleViewHeight));
                mParams = mScaleView.getLayoutParams();
                mScale = (mScaleView.getBottom() + mMoveDelta * mDumpFactor) / mScaleViewHeight;
                if (mScale > MIN_SCALE) {
                    mScale = mScale > MAX_SCALE ? MAX_SCALE : mScale;
                    mParams.height = Math.round(mScaleViewHeight * mScale);
                    mScaleView.setLayoutParams(mParams);
                }
                mLastY = ev.getY();
                break;
            case MotionEvent.ACTION_UP:
                if (mScale >= MIN_SCALE && mIsDrag) {
                    mScaleRunnable.startScale(100, mScale);
                    mScale = MIN_SCALE;
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 松开手之后，不断的进行缩放
     */
    private class ScaleRunnable implements Runnable {

        private boolean mIsFinished;
        private int mDuration;
        private long mStartTime;
        private float mStartScale;

        public void startScale(int duration, float scale) {
            mIsFinished = false;
            mDuration = duration;
            mStartScale = scale;
            mStartTime = SystemClock.currentThreadTimeMillis();
            post(this);
        }

        public void endScale() {
            mIsFinished = true;
            removeCallbacks(this);
        }

        @Override
        public void run() {
            if (mStartScale <= MIN_SCALE || mIsFinished) {
                return;
            }
            //不停的去缩小scale的比例，然后重新layout,scale-[max~1]
            float rote = mInterpolator.getInterpolation(1.0f * (SystemClock.currentThreadTimeMillis() - mStartTime) / mDuration);
            float scale = mStartScale - (mStartScale - MIN_SCALE) * rote;
            if (scale < MIN_SCALE) {
                scale = MIN_SCALE;
            }
            if (scale == MIN_SCALE) {
                mIsFinished = true;
            }
            mParams = mScaleView.getLayoutParams();
            mParams.height = (int) (mScaleViewHeight * scale);
            mScaleView.setLayoutParams(mParams);
            post(this);
        }
    }

    /**
     * 比例插值器
     */
    private Interpolator mInterpolator = new Interpolator() {
        @Override
        public float getInterpolation(float input) {
            return (float) (Math.pow(input - MIN_SCALE, 5) + 1f);
        }
    };
    /**
     * 阻尼插值器
     */
    private Interpolator mDumpInterpolator = new Interpolator() {
        @Override
        public float getInterpolation(float input) {
            return (float) Math.pow(1 - input, 3);
        }
    };
}
