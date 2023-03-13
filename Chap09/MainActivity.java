package com.example.pj0313;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button[] buttons = new Button[9];
    Integer[] buttons_id = {
            R.id.btn1, R.id.btn2, R.id.btn3,
            R.id.btn4, R.id.btn5, R.id.btn6,
            R.id.btn7, R.id.btn8, R.id.btn9
    };
    TextView tvEmpty;
    static int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i=0; i<9; i++) {
            buttons[i] = findViewById(buttons_id[i]);
        }

        for (int i=0; i<buttons.length; i++) {
            final int index = i;
            buttons[index].setOnClickListener(v -> {
                buttons[index].setVisibility(View.INVISIBLE);
                count++;

                if (count == 9) {
                    tvEmpty = findViewById(R.id.tvEmpty);
                    tvEmpty.setVisibility(View.VISIBLE);
                }
            });
        }

    }
}