package com.example.pj0314;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("커스텀 다이얼로그");
    }

    public void onClick(View view) {
        Dialog loginDialog = new Dialog(MainActivity.this);
        loginDialog.setContentView(R.layout.dialog);

        Button login = loginDialog.findViewById(R.id.login);
        Button cancel = loginDialog.findViewById(R.id.cancel);

        final EditText username = loginDialog.findViewById(R.id.username);
        final EditText password = loginDialog.findViewById(R.id.password);

        login.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "로그인 성공: 아이디=" + username.getText().toString().trim()
                    + ", 비밀번호=" + password.getText().toString().trim(), Toast.LENGTH_SHORT).show();
            loginDialog.dismiss();
        });

        cancel.setOnClickListener(v -> {
            loginDialog.dismiss();
        });
        loginDialog.show();
    }
}