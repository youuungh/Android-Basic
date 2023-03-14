package com.example.pj0314_2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("선택 알림 대화상자");

        iv = findViewById(R.id.imageView);
    }

    public void onClick(View view) {
        String[] animals = {"강아지", "고양이", "말", "토끼"};

        AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
        dlg.setTitle("좋아하는 동물은?");
        dlg.setItems(animals, (dialog, which) -> {
           switch (which) {
               case 0: iv.setImageResource(R.drawable.dog); break;
               case 1: iv.setImageResource(R.drawable.cat); break;
               case 2: iv.setImageResource(R.drawable.horse); break;
               case 3: iv.setImageResource(R.drawable.rabbit); break;
           }
        });
        dlg.setNegativeButton("닫기", null);
        dlg.show();
    }
}