package com.jalen.customproject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Jalen on 2016/2/19.
 */
public class CustomTextView extends TextView {
    private LinearGradient mLinearGradient;
    private Matrix mGradientMatrix;
    private int mViewWidth;
    private Paint mPaint,mPaint1,mPaint2;
    public CustomTextView(Context context) {
        this(context, null);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(mViewWidth==0){
            mViewWidth=getMeasuredWidth();
            if(mViewWidth>0){
                mPaint = getPaint();
                mLinearGradient = new LinearGradient(0,0,mViewWidth,0,new int []{Color.BLUE,Color.RED,Color.GREEN},null, Shader.TileMode.REPEAT);
                mPaint.setShader(mLinearGradient);
                mGradientMatrix = new Matrix();
            }
        }

    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint1 = new Paint();
        mPaint1.setColor(getResources().getColor(android.R.color.holo_blue_dark));
        mPaint1.setAntiAlias(true);
        mPaint1.setStrokeWidth(20f);
        mPaint1.setStyle(Paint.Style.STROKE);
        mPaint2 = new Paint();
        mPaint2.setColor(Color.GREEN);
        mPaint2.setStrokeWidth(20f);
        mPaint2.setStyle(Paint.Style.STROKE);



    }


    private int mTranslate;
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(),mPaint1);
        canvas.drawRect(20, 20, getMeasuredWidth() - 20, getMeasuredHeight() - 20, mPaint2);
        canvas.save();
        super.onDraw(canvas);
        canvas.restore();
        if(mGradientMatrix!=null){
            mTranslate+= mViewWidth/5;
            if(mTranslate>2*mViewWidth){
                mTranslate = -mViewWidth;
            }
            mGradientMatrix.setTranslate(mTranslate,0);

            mLinearGradient.setLocalMatrix(mGradientMatrix);
            postInvalidateDelayed(100);
        }
    }
}
