package com.example.pj0227_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {
    Button btnStart, btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("자동 사진 보기");

        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);

        final ViewFlipper vFlipper;
        vFlipper = findViewById(R.id.viewFlipper1);
        vFlipper.setFlipInterval(1000);

        btnStart.setOnClickListener(view -> {
            vFlipper.startFlipping();
        });

        btnStop.setOnClickListener(view -> {
            vFlipper.stopFlipping();
        });
    }
}