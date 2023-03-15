package com.example.pj0314_3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edt1, edt2;
    Button btnExchange;
    TextView tvResult;
    static boolean flag = false;

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("원달러 환율 계산기");

        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);

        edt1.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        edt2.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

        btnExchange = findViewById(R.id.btnExchange);
        btnExchange.setOnClickListener(v -> {
            if (edt1.getText().toString().isEmpty() && edt2.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "환율을 입력하세요", Toast.LENGTH_SHORT).show();
            }
            else if (!edt1.getText().toString().isEmpty() && edt2.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "환전할 금액을 입력하세요", Toast.LENGTH_SHORT).show();
            }
            else if (!flag) {
                String ex = edt1.getText().toString();
                String value = edt2.getText().toString();

                // 달러 -> 원화
                double d_ex = Double.parseDouble(ex);
                double d_value = Double.parseDouble(value);
                double dollar = d_ex * d_value;

                tvResult = findViewById(R.id.tvResult);
                tvResult.setText(String.format("%,.2f", dollar) + " 원");
            }
            else {
                String ex = edt1.getText().toString();
                String value = edt2.getText().toString();

                // 원화 -> 달러
                double d_ex = Double.parseDouble(ex);
                double d_value = Double.parseDouble(value);
                double won = d_value / d_ex;

                tvResult = findViewById(R.id.tvResult);
                tvResult.setText(String.format("%,.2f", won) + " 달러");
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View focusView = getCurrentFocus();
        if (focusView != null) {
            Rect rect = new Rect();
            focusView.getGlobalVisibleRect(rect);

            int x = (int)ev.getX();
            int y = (int)ev.getY();

            if (!rect.contains(x, y)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (imm != null)
                    imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
                focusView.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                btnExchange.setText("달러를 원화로");
                flag = false;
                if (!edt1.getText().toString().equals("")) {
                    edt1.setText("");
                    edt2.setText("");
                    tvResult.setText("");
                }
                return true;
            case 2:
                btnExchange.setText("원화를 달러로");
                flag = true;
                if (!edt1.getText().toString().equals("")) {
                    edt1.setText("");
                    edt2.setText("");
                    tvResult.setText("");
                }
                return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 1, 0, "달러를 원화로");
        menu.add(0, 2, 0, "원화를 달러로");
        return true;
    }
}