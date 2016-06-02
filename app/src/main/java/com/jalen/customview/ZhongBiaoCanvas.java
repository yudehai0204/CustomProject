package com.jalen.customview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by 于德海 on 2016/5/31.
 *
 * @version ${VERSION}
 * @decpter
 */
public class ZhongBiaoCanvas  extends View{
    private Paint mPaintCircle,mPaintDegree,mPaintHour,mPaintMinu;//表盘，刻度
    private int mWidth,mHeight;//屏幕宽高
    public ZhongBiaoCanvas(Context context) {
        this(context,null);
    }

    public ZhongBiaoCanvas(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ZhongBiaoCanvas(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaintCircle = new Paint();
        mPaintCircle.setStyle(Paint.Style.STROKE);
        mPaintCircle.setStrokeWidth(5);
        mPaintCircle.setAntiAlias(true);

        mPaintDegree = new Paint();
        mPaintDegree.setStyle(Paint.Style.FILL);
        mPaintDegree.setStrokeWidth(3);

        mPaintHour = new Paint();
        mPaintHour.setStrokeWidth(30);

        mPaintMinu = new Paint();
        mPaintMinu.setStrokeWidth(20);

        DisplayMetrics outdisplay = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(outdisplay);
        mWidth = outdisplay.widthPixels;
        mHeight =outdisplay.heightPixels;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mWidth/2,mHeight/2,mWidth/2,mPaintCircle);
        for (int i=0;i<24;i++){
            if(i==0||i==6||i==12||i==18){
                mPaintDegree.setStrokeWidth(5);
                mPaintDegree.setTextSize(30);
                canvas.drawLine(mWidth/2,mHeight/2-mWidth/2,mWidth/2,mHeight/2-mWidth/2+60,mPaintDegree);
                canvas.drawText(Integer.toString(i),mWidth/2-mPaintDegree.measureText(Integer.toString(i))/2,mHeight/2-mWidth/2+90,mPaintDegree);
            }else {
                mPaintDegree.setStrokeWidth(3);
                mPaintDegree.setTextSize(15);
                canvas.drawLine(mWidth/2,mHeight/2-mWidth/2,mWidth/2,mHeight/2-mWidth/2+30,mPaintDegree);
                canvas.drawText(Integer.toString(i),mWidth/2-mPaintDegree.measureText(Integer.toString(i))/2,mHeight/2-mWidth/2+60,mPaintDegree);
            }
            canvas.rotate(15,mWidth/2,mHeight/2);
        }


        canvas.save();
        canvas.drawCircle(mWidth/2,mHeight/2,10,mPaintHour);
        canvas.translate(mWidth/2,mHeight/2);
        canvas.drawLine(0,0,100,100,mPaintHour);
        canvas.drawLine(0,0,200,200,mPaintMinu);
        canvas.restore();
    }
}
