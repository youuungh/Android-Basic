package com.example.pj0919;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvCal, tvResult;
    private String curr, res;
    private Button btnAdd, btnSub, btnMul, btnDiv, btnResult;
    private boolean dotCheck, operatorCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("탁상용 계산기");

        tvCal = findViewById(R.id.tvCal);
        tvResult = findViewById(R.id.tvResult);

        curr = "";
        res = "";
        dotCheck = false;
        operatorCheck = false;

        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);
        btnResult = findViewById(R.id.btnResult);

        btnAdd.setOnClickListener(nListener);
        btnSub.setOnClickListener(nListener);
        btnMul.setOnClickListener(nListener);
        btnDiv.setOnClickListener(nListener);
        btnResult.setOnClickListener(nListener);
    }

    View.OnClickListener nListener = new View.OnClickListener() {
        @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
        @Override
        public void onClick(View view) {
            if (curr.isEmpty())
                Toast.makeText(getApplicationContext(), "숫자를 먼저 입력하세요", Toast.LENGTH_SHORT).show();
            else {
                switch (view.getId()) {
                    case R.id.btnAdd:
                        dotCheck = false;
                        if(!operatorCheck) {
                            curr = curr + " + ";
                            operatorCheck = true;
                            tvResult.setText(curr);
                        }
                        break;
                    case R.id.btnSub:
                        dotCheck = false;
                        if(!operatorCheck) {
                            curr = curr + " - ";
                            operatorCheck = true;
                            tvResult.setText(curr);
                        }
                        break;
                    case R.id.btnMul:
                        dotCheck = false;
                        if(!operatorCheck) {
                            curr = curr + " * ";
                            operatorCheck = true;
                            tvResult.setText(curr);
                        }
                        break;
                    case R.id.btnDiv:
                        dotCheck = false;
                        if(!operatorCheck) {
                            curr = curr + " / ";
                            operatorCheck = true;
                            tvResult.setText(curr);
                        }
                        break;
                    case R.id.btnResult:
                        if (operatorCheck && !curr.substring(curr.length()-1).equals(" ")) {
                            String[] tokens = curr.split(" ");

                            switch (tokens[1].charAt(0)) {
                                case '+':
                                    res = Double.toString(Double.parseDouble(tokens[0]) + Double.parseDouble(tokens[2]));
                                    break;
                                case '-':
                                    res = Double.toString(Double.parseDouble(tokens[0]) - Double.parseDouble(tokens[2]));
                                    break;
                                case '*':
                                    res = Double.toString(Double.parseDouble(tokens[0]) * Double.parseDouble(tokens[2]));
                                    break;
                                case '/':
                                    res = Double.toString(Double.parseDouble(tokens[0]) / Double.parseDouble(tokens[2]));
                                    break;
                            }
                            tvCal.setText(curr);
                            tvResult.setText(res);
                        }
                        break;
                }
            }
        }
    };

    @SuppressLint({"SetTextI18n", "NonConstantResourceId"})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn0: curr = curr + "0"; tvResult.setText(curr); break;
            case R.id.btn1: curr = curr + "1"; tvResult.setText(curr); break;
            case R.id.btn2: curr = curr + "2"; tvResult.setText(curr); break;
            case R.id.btn3: curr = curr + "3"; tvResult.setText(curr); break;
            case R.id.btn4: curr = curr + "4"; tvResult.setText(curr); break;
            case R.id.btn5: curr = curr + "5"; tvResult.setText(curr); break;
            case R.id.btn6: curr = curr + "6"; tvResult.setText(curr); break;
            case R.id.btn7: curr = curr + "7"; tvResult.setText(curr); break;
            case R.id.btn8: curr = curr + "8"; tvResult.setText(curr); break;
            case R.id.btn9: curr = curr + "9"; tvResult.setText(curr); break;
            case R.id.btnDot:
                if (curr.isEmpty()) {
                    curr = "0.";
                    dotCheck = true;
                    tvResult.setText(curr);
                }
                else if (!dotCheck) {
                    curr = curr + ".";
                    dotCheck = true;
                    tvResult.setText(curr);
                }
                break;
            case R.id.btnClear:
                curr = "";
                res = "";
                dotCheck = false;
                operatorCheck = false;
                tvCal.setText(curr);
                tvResult.setText(res);
                break;
        }
    }
}