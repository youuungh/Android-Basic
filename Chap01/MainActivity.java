package com.example.pj0711;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editUrl;
    Button btn1, btn2;
    RadioGroup rGroup1;
    RadioButton radioArray[] = new RadioButton[2];
    ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("PJ0711");

        editUrl = findViewById(R.id.editUrl);
        btn1 = findViewById(R.id.btnShowMsg);
        btn2 = findViewById(R.id.btnOpenUrl);
        rGroup1 = findViewById(R.id.rGroup1);
        radioArray[0] = findViewById(R.id.rdoQ);
        radioArray[1] = findViewById(R.id.rdoR);
        imgView = findViewById(R.id.imgView);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), editUrl.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(editUrl.getText().toString()));
                startActivity(mIntent);
            }
        });

        final int draw[] = { R.drawable.q10, R.drawable.r };

        for (int i = 0; i < radioArray.length; i++) {
            final int index;
            index = i;
            radioArray[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgView.setImageResource(draw[index]);
                }
            });
        }

    }
}
