package com.jalen.customproject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by Jalen on 2016/2/23.
 */
public class CustomRadian extends View{
    private Paint mArcPaint,mCirClePaint,mTextPaint;
    public CustomRadian(Context context) {
        this(context, null);
    }

    public CustomRadian(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomRadian(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mArcPaint = new Paint();
        mArcPaint.setColor(Color.GREEN);
        mArcPaint.setAntiAlias(true);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mCirClePaint = new Paint();
        mCirClePaint.setColor(Color.BLUE);
        mTextPaint = new TextPaint();
        int textsize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,12,getResources().getDisplayMetrics());
        mTextPaint.setTextSize(textsize);

    }

    private int mWidth;
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        mWidth = Math.min(height, width);
        setMeasuredDimension(mWidth,mWidth);
    }

    private RectF mRect;
    private String str = "Hello World";
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float width = mArcPaint.getStrokeWidth()/2;
        canvas.drawCircle(mWidth/2,mWidth/2,mWidth/3,mCirClePaint);
        mRect = new RectF(0+width,0+width,mWidth-width,mWidth-width);
        canvas.drawArc(mRect, 1, 90, true, mArcPaint);

        Rect bounds = new Rect();
        mTextPaint.getTextBounds(str,0,str.length(),bounds);
        float tvwidth = bounds.width();
        int tvheight = bounds.height();
        canvas.drawText(str, 0, str.length(), mWidth / 2-tvwidth/2, mWidth / 2+tvheight/2, mTextPaint);
    }
}
