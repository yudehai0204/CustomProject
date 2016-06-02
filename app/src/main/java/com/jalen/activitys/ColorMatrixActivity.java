package com.jalen.activitys;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.jalen.customproject.R;

/**
 * Created by 于德海 on 2016/6/1.
 *
 * @version ${VERSION}
 * @decpter
 */
public class ColorMatrixActivity extends Activity implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {
    private ImageView img;
    private SeekBar mSeekbar_lue,mSeekBar_solu,mSeekbar_lum;
    private Bitmap bm;
    private Button btn;
    private float [] f1={0.33F ,0.59F ,0.11F ,0f ,0f,
            0.33F ,0.59F ,0.11F ,0f, 0f,
            0.33F ,0.59F ,0.11F,  0f, 0f,
            0f,        0f,       0f,       1f,  0f
    },f2={-1f , 0f , 0f, 1f ,1f,
            0f,-1f ,  0f  ,1f,   1f,
            0f  , 0f,  -1f ,1f,   1f,
            0f , 0f ,  0f ,1f,   0f},
    f3={
            0.394F ,0.769F ,0.189F ,0F,0F,
            0.349F ,0.6856F ,0.168F ,0F, 0F,
            0.272F ,0.534F ,0.131F, 0F, 0F,
            0F,        0F,       0F,       1F, 0F
    },
    f4={
            1.5F, 1.5F, 1.5F, 0F, -1F,
            1.5F, 1.5F, 1.5F, 0F, -1F,
            1.5F, 1.5F, 1.5F, 0F, -1F,
            1.5F, 1.5F, 1.5F, 1F, 0F
    },
    f5={
            1.438F, -0.122F, -0.016F, 0F, -0.03F,
            -0.062F, 1.378F,-0.016F, 0F,0.05F,
            -0.062F, -0.122F, 1.483F,0F,-0.02F,
            0F,       0F,       0F,     1F,  0F
    }

            ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_color);
        img = (ImageView) findViewById(R.id.img);
        Drawable draw = getResources().getDrawable(R.mipmap.bg);
        bm = ((BitmapDrawable)draw).getBitmap();
        mSeekbar_lue = (SeekBar) findViewById(R.id.seekbar_hue);
        mSeekBar_solu = (SeekBar) findViewById(R.id.seekbar_saturation);
        mSeekbar_lum = (SeekBar) findViewById(R.id.seekbar_lum);
        mSeekbar_lue.setProgress(127);
        mSeekBar_solu.setProgress(127);
        mSeekbar_lum.setProgress(127);
        mSeekbar_lue.setOnSeekBarChangeListener(this);
        mSeekBar_solu.setOnSeekBarChangeListener(this);
        mSeekbar_lum.setOnSeekBarChangeListener(this);
        btn = (Button) findViewById(R.id.btn1);
        btn.setOnClickListener(this);
        btn = (Button) findViewById(R.id.btn2);
        btn.setOnClickListener(this);
        btn = (Button) findViewById(R.id.btn3);
        btn.setOnClickListener(this);
        btn = (Button) findViewById(R.id.btn4);
        btn.setOnClickListener(this);
        btn = (Button) findViewById(R.id.btn5);
        btn.setOnClickListener(this);
    }
    private float hue,sokution,lum;

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Toast.makeText(this,""+progress,Toast.LENGTH_SHORT).show();
        switch (seekBar.getId()){
            case R.id.seekbar_hue:
                hue = progress*1.0F/127;
                break;
            case R.id.seekbar_saturation:
                sokution = progress*1.0F/127;
                break;
            case R.id.seekbar_lum:
                lum = progress*1.0F/127;
                break;
        }
        changeBitmap(hue,sokution,lum);
    }



    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
    public void ColorMatrixSet(Bitmap bm,float [] f){
        Bitmap bmp = Bitmap.createBitmap(bm.getWidth(),bm.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();
        ColorMatrix hueMatrix = new ColorMatrix();
        hueMatrix.set(f);
        paint.setColorFilter(new ColorMatrixColorFilter(hueMatrix));
        canvas.drawBitmap(bm,0,0,paint);
        img.setImageBitmap(bmp);
    }
    private void changeBitmap(float hue, float sokution, float lum) {
        Bitmap bmp = Bitmap.createBitmap(bm.getWidth(),bm.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint();
        ColorMatrix hueMatrix = new ColorMatrix();
        hueMatrix.setRotate(0,hue);
        hueMatrix.setRotate(1,hue);
        hueMatrix.setRotate(2,hue)
        ;
        ColorMatrix sMatrix = new ColorMatrix();
        sMatrix.setSaturation(sokution);

        ColorMatrix lumMatrix = new ColorMatrix();
        lumMatrix.setScale(lum,lum,lum,1);
//        lumMatrix.set(new float[]{1,100,0,0,0,0,1,0,0,10,0,0,1,0,0,0,10,0,1,0});


        ColorMatrix imageMatrix = new ColorMatrix();
        imageMatrix.postConcat(hueMatrix);
        imageMatrix.postConcat(sMatrix);
        imageMatrix.postConcat(lumMatrix);
        paint.setColorFilter(new ColorMatrixColorFilter(imageMatrix));
        canvas.drawBitmap(bm,0,0,paint);
        img.setImageBitmap(bmp);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn1:
                ColorMatrixSet(bm,f1);
                break;
            case R.id.btn2:
                ColorMatrixSet(bm,f2);
                break;
            case R.id.btn3:
                ColorMatrixSet(bm,f3);
                break;
            case R.id.btn4:
                ColorMatrixSet(bm,f4);
                break;
            case R.id.btn5:
                ColorMatrixSet(bm,f5);
                break;
        }

    }
}
