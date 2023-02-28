package com.example.pj0228;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout baseLayout;
    Button btn1, btn2, btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("컨텍스트 메뉴");

        baseLayout = findViewById(R.id.baseLayout);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);

        registerForContextMenu(btn2);
        registerForContextMenu(btn3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater mInflater = getMenuInflater();
        mInflater.inflate(R.menu.menu1, menu);

//        // 코드만으로 메뉴 구성
//        menu.add(0, 1, 0, "배경색(빨강)");
//        menu.add(0, 2, 0, "배경색(초록)");
//        menu.add(0, 3, 0, "배경색(파랑)");
//
//        SubMenu sMenu = menu.addSubMenu("버튼 변경");
//        sMenu.add(0, 4, 0, "버튼 45도 회전");
//        sMenu.add(0, 5, 0, "버튼 2배 확대");

        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v == btn2) {
            menu.setHeaderTitle("배경색 변경");

            menu.add(0, 1, 0, "배경색(빨강)");
            menu.add(0, 2, 0, "배경색(초록)");
            menu.add(0, 3, 0, "배경색(파랑)");
            menu.add(0, 4, 0, "배경색(하양)");
        }
        if (v == btn3) {
            menu.add(0, 5, 0, "버튼 원래대로(회전)");
            menu.add(0, 6, 0, "버튼 원래대로(스케일)");
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemRed:
                baseLayout.setBackgroundColor(Color.RED);
                return true;
            case R.id.itemGreen:
                baseLayout.setBackgroundColor(Color.GREEN);
                return true;
            case R.id.itemBlue:
                baseLayout.setBackgroundColor(Color.BLUE);
                return true;
            case R.id.subRotate:
                btn1.setRotation(45);
                return true;
            case R.id.subSize:
                btn1.setScaleX(2);
                return true;
        }
        return false;
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                baseLayout.setBackgroundColor(Color.RED);
                return true;
            case 2:
                baseLayout.setBackgroundColor(Color.GREEN);
                return true;
            case 3:
                baseLayout.setBackgroundColor(Color.BLUE);
                return true;
            case 4:
                baseLayout.setBackgroundColor(Color.WHITE);
                return true;
            case 5:
                btn1.setRotation(0);
                return true;
            case 6:
                btn1.setScaleX(1);
                return true;
        }
        return false;
    }
}