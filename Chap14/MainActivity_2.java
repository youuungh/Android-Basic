package com.example.pj0321_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btn;
    ProgressBar pb1, pb2;
    TextView tv1, tv2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb1 = findViewById(R.id.sb1);
        pb2 = findViewById(R.id.sb2);
        btn = findViewById(R.id.btn);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);

        btn.setOnClickListener(v -> {
            new Thread() {
                public void run() {
                    for (int i=pb1.getProgress(); i<100; i=i+2) {
                        runOnUiThread(() -> {
                            pb1.setProgress(pb1.getProgress()+2);
                            tv1.setText("1번 진행률 : " + pb1.getProgress() + "%");
                        });
                        SystemClock.sleep(100);
                    }
                }
            }.start();

            new Thread() {
                public void run() {
                    for (int i=pb2.getProgress(); i<100; i++) {
                        runOnUiThread(() -> {
                            pb2.setProgress(pb2.getProgress()+1);
                            tv2.setText("2번 진행률 : " + pb2.getProgress() + "%");
                        });
                        SystemClock.sleep(100);
                    }
                }
            }.start();
        });
    }
}