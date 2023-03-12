package com.example.pj0312_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    Button btnPrev, btnNext;
    MyPictureView myPictureView;
    TextView tvNum;
    int curNum = 1;
    File[] imageFiles;
    String imageFname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("간단 이미지 뷰어");
        ActivityCompat.requestPermissions(this,
                new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, MODE_PRIVATE);

        btnPrev = findViewById(R.id.btnPrev);
        btnNext = findViewById(R.id.btnNext);
        tvNum = findViewById(R.id.tvNum);
        myPictureView = findViewById(R.id.myPictureView1);

        imageFiles = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/Pictures").listFiles();
        imageFname = imageFiles[1].toString();
        myPictureView.imagePath = imageFname;
        tvNum.setText("1" + "/" + (imageFiles.length-1));

        btnPrev.setOnClickListener(view -> {
            if (curNum <= 1) {
                curNum = imageFiles.length-1;
            } else {
                curNum--;
            }
            imageFname = imageFiles[curNum].toString();
            myPictureView.imagePath = imageFname;
            myPictureView.invalidate();
            tvNum.setText((curNum) + "/" + (imageFiles.length-1));
        });

        btnNext.setOnClickListener(view -> {
            if (curNum >= imageFiles.length-1) {
                curNum = 1;
            } else {
                curNum++;
            }
            imageFname = imageFiles[curNum].toString();
            myPictureView.imagePath = imageFname;
            myPictureView.invalidate();;
            tvNum.setText((curNum) + "/" + (imageFiles.length-1));
        });
    }
}