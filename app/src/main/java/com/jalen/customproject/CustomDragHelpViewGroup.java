package com.jalen.customproject;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by 于德海 on 2016/4/13.
 *
 * @version ${<VARIABLE_NAME>}
 * @decpter
 */
public class CustomDragHelpViewGroup extends FrameLayout{
    private ViewDragHelper mViewDragHelper;
    private View mViewmenu,mMainView;
    private int mWidth;
    private ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            return mMainView==child;
        }

        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            return left;
        }

        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return 0;
        }

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            if(mMainView.getLeft()<500){
                mViewDragHelper.smoothSlideViewTo(mMainView,0,0);
                ViewCompat.postInvalidateOnAnimation(CustomDragHelpViewGroup.this);
            }else {
                mViewDragHelper.smoothSlideViewTo(mMainView,300,0);
                ViewCompat.postInvalidateOnAnimation(CustomDragHelpViewGroup.this);
            }
        }
    };
    public CustomDragHelpViewGroup(Context context) {
        this(context,null);
    }

    public CustomDragHelpViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomDragHelpViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mViewDragHelper = ViewDragHelper.create(this, callback);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = mViewmenu.getMeasuredWidth();

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mViewmenu = getChildAt(0);
        mMainView = getChildAt(1);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mViewDragHelper.continueSettling(true)){
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
}
