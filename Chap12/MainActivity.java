package com.example.pj0317;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ClipData;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener {
    ArrayList<Item> items = new ArrayList<>();
    EditText edtName, edtTel;
    Button btnAdd;
    ListView listView;
    Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단 전화번호부");

        adapter = new Adapter(this, R.layout.listview_item, items);
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        edtName = findViewById(R.id.edtName);
        edtTel = findViewById(R.id.edtTel);
        edtName.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        edtTel.setInputType(InputType.TYPE_CLASS_PHONE);

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (edtName.getText().toString().isEmpty() && edtTel.getText().toString().isEmpty()) {
            Toast.makeText(this, "이름을 입력하세요", Toast.LENGTH_SHORT).show();
        } else if (!edtName.getText().toString().isEmpty() && edtTel.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "전화번호를 입력하세요", Toast.LENGTH_SHORT).show();
        } else if (v.getId() == R.id.btnAdd) {
            Item item = new Item(edtName.getText().toString(), edtTel.getText().toString());
            items.add(item);
            adapter.notifyDataSetChanged();
            edtName.setText("");
            edtTel.setText("");
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