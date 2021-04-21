package com.example.pixi2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;

public class ChooseActivity extends AppCompatActivity {

    ImageButton kamera, gallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        kamera = findViewById(R.id.kamera_imageButton);
        gallery = findViewById(R.id.gallery_imageButton);

        kamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int secondsDelayed = 1;
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        Intent mainIntent = new Intent(ChooseActivity.this, KameraActivity.class);
                        startActivity(mainIntent);
                        finish();
                    }
                }, secondsDelayed * 1000);
            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int secondsDelayed = 1;
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        Intent mainIntent = new Intent(ChooseActivity.this, FilterActivity.class);
                        startActivity(mainIntent);
                        finish();
                    }
                }, secondsDelayed * 1000);
            }
        });
    }
}