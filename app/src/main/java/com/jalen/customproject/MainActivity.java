package com.jalen.customproject;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.jalen.activitys.ColorMatrixActivity;
import com.jalen.activitys.ZhongBiaoActivity;

public class MainActivity extends AppCompatActivity {
private CustomTopBar topbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
//        topbar = (CustomTopBar) findViewById(R.id.topbar);
//        topbar.setListener(new CustomTopBar.topBarClickListener() {
//            @Override
//            public void leftClick() {
//                Toast.makeText(MainActivity.this,"LEFT",Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void rightClick() {
//                Toast.makeText(MainActivity.this,"Right",Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        int i = getNums(10);

    }

    public void Zhongbiao(View view){
        startActivity(new Intent(this, ZhongBiaoActivity.class));
    }
    public void goColor(View view){
        startActivity(new Intent(this, ColorMatrixActivity.class));
    }

    private int getNums(int price){
        int hpj = price/2;
        int pg= price/2;
        int p =price/2;
        int i =0;
        while (p>=2||pg>=4){
            i++;
            Log.d("PJ","第"+i+"次  瓶："+p+" 瓶盖：  "+pg+" 喝啤酒： "+hpj);
            int pp = p/2;
            p = p%2+pp;
            hpj+=pp;
            pg +=pp;
            int ppg = pg/4;
            pg=pg%4+ppg;
            p +=ppg;
            hpj+=ppg;
        }
        Log.d("PJ","喝啤酒："+hpj);
    Toast.makeText(this,""+hpj,Toast.LENGTH_SHORT).show();
        return  hpj;
    }
}
