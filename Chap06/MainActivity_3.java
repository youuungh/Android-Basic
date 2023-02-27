package com.example.pj0227_3;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvColor, tvSize;
    RadioButton rdoRed, rdoGreen, rdoBlue;
    Button btnMinus, btnPlus;
    float textSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvColor = findViewById(R.id.tvColor);
        tvSize = findViewById(R.id.tvSize);

        rdoRed = findViewById(R.id.rdoRed);
        rdoGreen = findViewById(R.id.rdoGreen);
        rdoBlue = findViewById(R.id.rdoBlue);

        btnMinus = findViewById(R.id.btnMinus);
        btnPlus = findViewById(R.id.btnPlus);

        rdoRed.setOnClickListener(this);
        rdoGreen.setOnClickListener(this);
        rdoBlue.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnPlus.setOnClickListener(this);

        TabHost tabHost = findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tabSpecColor = tabHost.newTabSpec("COLOR").setIndicator("색상");
        tabSpecColor.setContent(R.id.tabColor);
        tabHost.addTab(tabSpecColor);

        TabHost.TabSpec tabSpecSize = tabHost.newTabSpec("SIZE").setIndicator("크기");
        tabSpecSize.setContent(R.id.tabSize);
        tabHost.addTab(tabSpecSize);

        tabHost.setCurrentTab(0);
    }

    @Override
    public void onClick(View view) {
        textSize = tvSize.getTextSize();

        switch (view.getId()) {
            case R.id.rdoRed:
                tvColor.setTextColor(Color.RED);
                break;
            case R.id.rdoGreen:
                tvColor.setTextColor(Color.GREEN);
                break;
            case R.id.rdoBlue:
                tvColor.setTextColor(Color.BLUE);
                break;
            case R.id.btnMinus:
                tvSize.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize-10);
                break;
            case R.id.btnPlus:
                tvSize.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize+10);
                break;
        }
    }
}