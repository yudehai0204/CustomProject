package com.jalen.customproject;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by 于德海 on 2016/3/28.
 *
 * @decpter
 */
public class CustomViewGroup extends ViewGroup {

    private Scroller mScroller;//补助滚动类
    private boolean isScrolling;//是否正在滚动
    private int mScreenHeight;
    private  Context mContext;
    public CustomViewGroup(Context context) {
        this(context,null);
    }

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initViews();
    }

    /***
     *
     */
    private void initViews() {
        Point point = new Point();
        ((Activity)mContext).getWindowManager().getDefaultDisplay().getSize(point);
        mScreenHeight = point.y;
        mScroller = new Scroller(mContext);
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();
        for (int i=0;i<count;i++){
            View child = getChildAt(i);
            measureChildren(widthMeasureSpec,heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int child = getChildCount();
        MarginLayoutParams mlp = (MarginLayoutParams) getLayoutParams();
        mlp.height = mScreenHeight*child;
        setLayoutParams(mlp);
        for (int i =0;i<child;i++){
            View  childView = getChildAt(i);
            if(childView.getVisibility()!=View.GONE){
                childView.layout(l,i*mScreenHeight,r,(i+1)*mScreenHeight);
            }
        }
    }

    private int mLasty;
    private int mStart,mEnd;//七点,终点
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) event.getY();
        Log.d("yu","y:"+y+" getScrollY: "+getScrollY()+" ScrollFinsh: "+ mScroller.isFinished()+" getHeight()-mScreenHeight: "+(getHeight()-mScreenHeight));

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mLasty = y;
                mStart = getScrollY();
                break;
            case MotionEvent.ACTION_MOVE:
                if(!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                int dy = mLasty-y;
                Log.d("yu",dy+"");
                if(getScrollY()<0){
                    dy = 0;
                    scrollTo(0, 0);
                }
                if(getScrollY()>getHeight()-mScreenHeight){
                    dy=0;
                    scrollTo(0, getHeight() - mScreenHeight);
                }
                scrollBy(0,dy);
                mLasty = y;
                break;
            case MotionEvent.ACTION_UP:
                mEnd = getScrollY();
                int dScrollY = mEnd-mStart;//记录滑动距离
                if(dScrollY>0){
                    if(dScrollY<mScreenHeight/3){
                        mScroller.startScroll(0,getScrollY(),0,-dScrollY);
                    }else {
                        mScroller.startScroll(0,getScrollY(),0,mScreenHeight-dScrollY);
                    }

                }else{
                    if(-dScrollY<mScreenHeight/3){
                        mScroller.startScroll(0,getScrollY(),0,-dScrollY);
                    }else{
                        mScroller.startScroll(0,getScrollY(),0,-mScreenHeight-dScrollY);
                    }
                }
                break;
        }
        postInvalidate();
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()){
            scrollTo(0,mScroller.getCurrY());
            postInvalidate();
        }
    }
}
