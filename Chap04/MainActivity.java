package com.example.pj0914;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edit1, edit2;
    Button btnAdd, btnSub, btnMul, btnDiv;
    TextView tvResult;
    String num1, num2;
    Integer result;

    Button[] numButtons = new Button[10];
    Integer[] numBtnIDs = {R.id.btnNum0, R.id.btnNum1, R.id.btnNum2, R.id.btnNum3,
                            R.id.btnNum4, R.id.btnNum5, R.id.btnNum6, R.id.btnNum7,
                            R.id.btnNum8, R.id.btnNum9};
    int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("테이블레이아웃 계산기");

        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);

        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);

        tvResult = findViewById(R.id.tvResult);

        btnAdd.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                result = Integer.parseInt(num1) + Integer.parseInt(num2);
                tvResult.setText("계산 결과 : " + result.toString());
                return false;
            }
        });

        btnSub.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                result = Integer.parseInt(num1) - Integer.parseInt(num2);
                tvResult.setText("계산 결과 : " + result.toString());
                return false;
            }
        });

        btnMul.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                result = Integer.parseInt(num1) * Integer.parseInt(num2);
                tvResult.setText("계산 결과 : " + result.toString());
                return false;
            }
        });

        btnDiv.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                num1 = edit1.getText().toString();
                num2 = edit2.getText().toString();
                result = Integer.parseInt(num1) / Integer.parseInt(num2);
                tvResult.setText("계산 결과 : " + result.toString());
                return false;
            }
        });

        for (i = 0; i < numBtnIDs.length; i++) {
            numButtons[i] = findViewById(numBtnIDs[i]);
        }

        for (i = 0; i < numBtnIDs.length; i++) {
            final int index;
            index = i;

            numButtons[index].setOnClickListener(v -> {
                if (edit1.isFocused()) {
                    num1 = edit1.getText().toString() + numButtons[index].getText().toString();
                    edit1.setText(num1);
                } else if (edit2.isFocused()) {
                    num2 = edit2.getText().toString() + numButtons[index].getText().toString();
                    edit2.setText(num2);
                } else {
                    Toast.makeText(getApplicationContext(), "먼저 에디트텍스트를 선택하세요",
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}