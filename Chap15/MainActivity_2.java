package com.example.pj0325;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    Button btnStart, btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(this, MusicService.class);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);

        btnStart.setOnClickListener(v -> {
            startService(intent);
            Toast.makeText(getApplicationContext(), "startService()", Toast.LENGTH_SHORT).show();
            finish();
        });

        btnStop.setOnClickListener(v -> {
            stopService(intent);
            Toast.makeText(getApplicationContext(), "stopService()", Toast.LENGTH_SHORT).show();
        });
    }
}