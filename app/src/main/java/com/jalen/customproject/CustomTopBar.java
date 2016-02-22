package com.jalen.customproject;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Jalen on 2016/2/22.
 */
public class CustomTopBar extends ViewGroup {
    private String mLeftText,mRightText,mTitleText;
    private int mLeftTextColor,mRightTextColor,mTitleTextColor;
    private float mTitleSize;
    private Drawable mLeftBackGround,mRightBackGround;
    private Button mLeftButton,mRightButton;
    private TextView mTitleTextView;
    private Context mContext;

    private ViewGroup.LayoutParams mLeftParmas,mRightParmas,mTitleParmas;
    public CustomTopBar(Context context) {
        this(context, null);
    }

    public CustomTopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.TopBar);
        mLeftTextColor = a.getColor(R.styleable.TopBar_leftTextcolor, Color.BLACK);
        mLeftText = a.getString(R.styleable.TopBar_leftText);
        mLeftBackGround = a.getDrawable(R.styleable.TopBar_leftBackground);

        mRightTextColor = a.getColor(R.styleable.TopBar_rightTextcolor, Color.BLACK);
        mRightText = a.getString(R.styleable.TopBar_rightText);
        mRightBackGround = a.getDrawable(R.styleable.TopBar_rightBackground);

        mTitleText = a.getString(R.styleable.TopBar_titleText);
        mTitleSize =a.getDimension(R.styleable.TopBar_titlesize, 10f);
        mTitleTextColor = a.getColor(R.styleable.TopBar_titlecolor, Color.BLACK);

        a.recycle();


        initViews();


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);



    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        int cr,cb,cl,ct;
        cb = b;
        cl=0;
        ct=0;
        cr=r;
        for(int i=0;i<count;i++){
            View child = getChildAt(i);
            int width = child.getMeasuredWidth();
            switch (i){
                case 0://左
                    cl=ct=0;
                    break;
                case 1://右
                    cl = r-width;
                    break;
                case 2://中

                    cl= r/2-width/2;
                    break;
            }
            child.layout(cl,ct,cl+width,b);
        }
    }

    private void initViews() {
        mLeftButton = new Button(mContext);
        mRightButton = new Button(mContext);
        mTitleTextView = new TextView(mContext);
        mLeftButton.setVisibility(View.VISIBLE);
        mRightButton.setVisibility(View.VISIBLE);
        mLeftButton.setText(mLeftText);
        mLeftButton.setTextColor(mLeftTextColor);
        mLeftButton.setBackground(mLeftBackGround);
        mLeftParmas = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
        mLeftButton.setLayoutParams(mLeftParmas);
        mLeftButton.setGravity(Gravity.CENTER);
        addView(mLeftButton);

        mRightButton.setText(mRightText);
        mRightButton.setTextColor(mRightTextColor);
        mRightButton.setBackground(mRightBackGround);
        mRightParmas = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
        mRightButton.setLayoutParams(mRightParmas);
        mRightButton.setGravity(Gravity.CENTER);
        addView(mRightButton);

        mTitleTextView.setText(mTitleText);
        mTitleTextView.setTextColor(mTitleTextColor);
        mTitleTextView.setTextSize(mTitleSize);
        mTitleTextView.setGravity(Gravity.CENTER);
        mTitleParmas = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.MATCH_PARENT);
        mTitleTextView.setLayoutParams(mTitleParmas);
        addView(mTitleTextView);

        mLeftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.leftClick();
                }
            }
        });
        mRightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null){
                    mListener.rightClick();
                }
            }
        });
    }



    public CustomTopBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    private topBarClickListener mListener;

    public void setListener(topBarClickListener listener){
        mListener = listener;
    }
    public interface  topBarClickListener{
        void leftClick();
        void rightClick();

    }
}
