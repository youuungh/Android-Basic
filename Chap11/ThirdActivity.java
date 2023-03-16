package com.example.pj0316;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ThirdActivity extends AppCompatActivity {
    Button btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        setTitle("Third 액티비티");

        btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(v -> {
            finish();
        });
    }
}