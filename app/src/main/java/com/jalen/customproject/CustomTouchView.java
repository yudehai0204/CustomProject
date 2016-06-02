package com.jalen.customproject;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by 于德海 on 2016/4/13.
 *
 * @version ${<VARIABLE_NAME>}
 * @decpter 实现随手指拖动而改变的View
 */
public class CustomTouchView extends View {
    public CustomTouchView(Context context) {
        this(context,null);
    }

    public CustomTouchView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomTouchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private int lastY,lastX;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int rawx= (int) event.getRawX();
        int rawy= (int) event.getRawY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX = rawx;
                lastY = rawy;
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetx = rawx-lastX;
                int offsety = rawy-lastY;

//                layout(getLeft()+offsetx,getTop()+offsety,getLeft()+offsetx+getWidth(),ge tTop()+offsety+getHeight());
                offsetLeftAndRight(offsetx);
                offsetTopAndBottom(offsety);
                lastX = rawx;
                lastY = rawy;
                break;
        }
        return true;
    }
}
