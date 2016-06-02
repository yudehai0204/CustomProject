package com.jalen.customproject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Jalen on 2016/2/24.
 */
public class CustomMediaRect extends View {

   private int mRectnums=8;
    private Paint mPaint;
    private int mItemWidth;
    private LinearGradient mLinearGradient;

    public CustomMediaRect(Context context) {
        this(context, null);
    }

    public CustomMediaRect(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomMediaRect(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    private void initViews() {
        mPaint = new Paint();
        mPaint.setColor(Color.GRAY);
        mPaint.setAntiAlias(true);
    }


    public  void setmRectnums(int nums){
        if(nums!=0){
            this.mRectnums=nums;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int measuresize = Math.min(width,height);
        mItemWidth = measuresize/(mRectnums+1);
        setMeasuredDimension(measuresize,measuresize);
    }

    private int mWidth,mHeight;
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mLinearGradient = new LinearGradient(0,0,getMeasuredHeight()/(mRectnums+1),getMeasuredHeight(),new int []{Color.GREEN,Color.RED},null, Shader.TileMode.CLAMP);
        mPaint.setShader(mLinearGradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i=0;i<mRectnums;i++){
            float height = (float) (getMeasuredHeight()*Math.random());
            canvas.drawRect(mItemWidth * i + (mItemWidth / (mRectnums + 1)) * (i + 1), height, mItemWidth * (i + 1) + (mItemWidth / (mRectnums + 1)) * (i + 1), getMeasuredHeight(), mPaint);
        }
        postInvalidateDelayed(300);
    }
}
