package com.example.pj0316;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    Button btnOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단 인텐트");

        final RadioButton rdoSecond = findViewById(R.id.rdoSecond);

        btnOpen = findViewById(R.id.btnOpen);
        btnOpen.setOnClickListener(v -> {
            Intent intent;

            if (rdoSecond.isChecked())
                intent = new Intent(getApplicationContext(), SecondActivity.class);
            else
                intent = new Intent(getApplicationContext(), ThirdActivity.class);
            startActivity(intent);
        });
    }
}