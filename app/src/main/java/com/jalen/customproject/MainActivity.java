package com.jalen.customproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private CustomTopBar topbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        topbar = (CustomTopBar) findViewById(R.id.topbar);
        topbar.setListener(new CustomTopBar.topBarClickListener() {
            @Override
            public void leftClick() {
                Toast.makeText(MainActivity.this,"LEFT",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rightClick() {
                Toast.makeText(MainActivity.this,"Right",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
