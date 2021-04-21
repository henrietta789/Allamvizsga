package com.example.pixi2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int secondsDelayed = 1;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                Intent mainIntent = new Intent(MainActivity.this, ChooseActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, secondsDelayed * 1000);
    }
}