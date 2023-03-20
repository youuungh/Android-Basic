package com.example.pj0320;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    myDBHelper myHelper;
    EditText edtName, edtNum, edtNameResult, edtNumResult;
    Button btnInit, btnInsert, btnUpdate, btnDelete, btnSelect;
    SQLiteDatabase sqlDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("아이돌 그룹 관리 DB");

        edtName = findViewById(R.id.edtName);
        edtNum = findViewById(R.id.edtNum);
        edtNameResult = findViewById(R.id.edtNameResult);
        edtNumResult = findViewById(R.id.edtNumResult);

        btnInit = findViewById(R.id.btnInit);
        btnInsert = findViewById(R.id.btnInsert);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnSelect = findViewById(R.id.btnSelect);

        myHelper = new myDBHelper(this);

        edtName.setInputType(InputType.TYPE_CLASS_TEXT);
        edtNum.setInputType(InputType.TYPE_CLASS_NUMBER);

        btnInit.setOnClickListener(v -> {
            sqlDB = myHelper.getWritableDatabase();
            myHelper.onUpgrade(sqlDB, 1, 2);
            sqlDB.close();
        });

        btnInsert.setOnClickListener(v -> {
            if (edtName.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "이름을 입력하세요", Toast.LENGTH_SHORT).show();
            } else if (edtNum.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "인원을 입력하세요", Toast.LENGTH_SHORT).show();
            } else {
                sqlDB = myHelper.getWritableDatabase();
                sqlDB.execSQL("INSERT INTO groupTBL VALUES ('"
                        + edtName.getText().toString() + "' , "
                        + edtNum.getText().toString() + ");");
                sqlDB.close();
                edtName.setText("");
                edtNum.setText("");
                Toast.makeText(getApplicationContext(), "입력됨", Toast.LENGTH_SHORT).show();
                btnSelect.callOnClick();
            }
        });

        btnUpdate.setOnClickListener(v -> {
            if (edtName.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "이름을 입력하세요", Toast.LENGTH_SHORT).show();
            } else if (edtNum.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "인원을 입력하세요", Toast.LENGTH_SHORT).show();
            } else {
                sqlDB = myHelper.getWritableDatabase();
                if (edtName.getText().toString() != "") {
                    sqlDB.execSQL("UPDATE groupTBL SET gNumber =" + edtNum.getText() + " WHERE gName = '"
                            + edtName.getText().toString() + "';");
                }
                sqlDB.close();
                edtName.setText("");
                edtNum.setText("");
                Toast.makeText(getApplicationContext(), "수정됨", Toast.LENGTH_SHORT).show();
                btnSelect.callOnClick();
            }
        });

        btnDelete.setOnClickListener(v -> {
            if (edtName.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "이름을 입력하세요", Toast.LENGTH_SHORT).show();
            } else {
                sqlDB = myHelper.getWritableDatabase();
                if (edtName.getText().toString() != "") {
                    sqlDB.execSQL("DELETE FROM groupTBL WHERE gName = '" + edtName.getText().toString() + "';");
                }
                sqlDB.close();
                edtName.setText("");
                edtNum.setText("");
                Toast.makeText(getApplicationContext(), "삭제됨", Toast.LENGTH_SHORT).show();
                btnSelect.callOnClick();
            }
        });

        btnSelect.setOnClickListener(v -> {
            sqlDB = myHelper.getReadableDatabase();
            Cursor cursor;
            cursor = sqlDB.rawQuery("SELECT * FROM groupTBL;", null);

            String strNames = "그룹이름" + "\r\n" + "--------------" + "\r\n";
            String strNums = "인원" + "\r\n" + "-------" + "\r\n";

            while (cursor.moveToNext()) {
                strNames += cursor.getString(0) + "\r\n";
                strNums += cursor.getString(1) + "\r\n";
            }

            edtNameResult.setText(strNames);
            edtNumResult.setText(strNums);

            cursor.close();
            sqlDB.close();
        });
    }

    public class myDBHelper extends SQLiteOpenHelper {
        public myDBHelper(Context context) {
            super(context, "groupDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE groupTBL(gName CHAR(20) PRIMARY KEY, gNumber INTEGER);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS groupTBL");
            onCreate(db);
        }
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
}