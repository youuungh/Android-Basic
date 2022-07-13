package com.example.pj0713;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText edit1, edit2;
    Button btnChk;
    TextView tvResult;
    String num1, num2, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("PJ0713 : 비만도 계산기");

        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);
        btnChk = findViewById(R.id.btnChk);
        tvResult = findViewById(R.id.tvResult);

        btnChk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calBmi();
            }
        });

    }

    private void calBmi() {
        num1 = edit1.getText().toString();
        num2 = edit2.getText().toString();
        float h = Float.parseFloat(num1) / 100;
        float w = Float.parseFloat(num2);
        float b = w / (h * h);

        if (b < 18.5)
            result = "저체중입니다.";
        else if (b >= 18.5 && b < 23)
            result = "정상입니다.";
        else if (b >= 23 && b < 25)
            result = "과제충입니다.";
        else if (b >= 25 && b < 30)
            result = "비만입니다.";
        else
            result = "고도비만입니다.";

        float bmi = Float.parseFloat(String.format("%.2f", b));
        tvResult.setText("귀하의 BMI = " + bmi + "이며, " + result);
    }
}
