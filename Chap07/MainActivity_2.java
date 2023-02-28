package com.example.pj0228_2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edtName, edtEmail, dlgEdtName, dlgEdtEmail;
    Button button;
    View dialogView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("사용자 정보 입력");

        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        dlgEdtName = findViewById(R.id.dlgEdtName);
        dlgEdtEmail = findViewById(R.id.dlgEdtEmail);

        button = findViewById(R.id.button1);
        button.setOnClickListener(view -> {
            dialogView = View.inflate(MainActivity.this, R.layout.dialog1, null);
            AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
            dlg.setTitle("사용자 정보 입력");
            dlg.setView(dialogView);

            dlgEdtName = dialogView.findViewById(R.id.dlgEdtName);
            dlgEdtEmail = dialogView.findViewById(R.id.dlgEdtEmail);
            dlgEdtName.setText(edtName.getText().toString());
            dlgEdtEmail.setText(edtEmail.getText().toString());

            dlg.setPositiveButton("확인", (dialogInterface, i) -> {
                edtName.setText(dlgEdtName.getText().toString());
                edtEmail.setText(dlgEdtEmail.getText().toString());
            });
            dlg.setNegativeButton("취소", (dialogInterface, i) -> {
                Toast.makeText(MainActivity.this, "취소했습니다", Toast.LENGTH_SHORT).show();
            });

            dlg.show();
        });
    }
}