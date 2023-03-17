package com.example.pj0317_2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;

public class GridAdapter extends BaseAdapter {
    Context context;

    GridAdapter(Context context) {
        this.context = context;
    }

    Integer[] posterID = { R.drawable.mov01, R.drawable.mov02,
            R.drawable.mov03, R.drawable.mov04, R.drawable.mov05,
            R.drawable.mov06, R.drawable.mov07, R.drawable.mov08,
            R.drawable.mov09, R.drawable.mov10, R.drawable.mov11,
            R.drawable.mov12, R.drawable.mov13, R.drawable.mov14,
            R.drawable.mov15, R.drawable.mov16, R.drawable.mov17,
            R.drawable.mov18, R.drawable.mov19, R.drawable.mov20,
            R.drawable.mov21, R.drawable.mov22, R.drawable.mov23,
            R.drawable.mov24, R.drawable.mov25, R.drawable.mov26,
            R.drawable.mov27, R.drawable.mov28, R.drawable.mov29,
            R.drawable.mov30 };

    String[] posterTitle = { "토이스토리4", "호빗3", "제이슨 본", "반지의 제왕 3","정직한 후보",
            "나쁜 녀석들", "겨울왕국 2", "알라딘", "극한직업", "스파이더맨",
            "레옹", "주먹왕 랄프2", "타짜", "걸캅스","도굴",
            "어벤져스", "엑시트", "캡틴 마블", "봉오동 전투", "분노의 질주",
            "아바타", "힘을내요 미스터리", "포드v페라리", "쥬만지","대부",
            "국가대표", "토이스토리3", "마당을 나온 암탉", "죽은 시인의 사회", "서유쌍기"};

    @Override
    public int getCount() {
        return posterID.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imgView = new ImageView(context);
        imgView.setLayoutParams(new ViewGroup.LayoutParams(200, 300));
        imgView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imgView.setPadding(5, 5, 5, 5);
        imgView.setImageResource(posterID[position]);

        final int pos = position;
        imgView.setOnClickListener(v -> {
            View dialogView = View.inflate(context, R.layout.dialog, null);

            ImageView ivPoster = dialogView.findViewById(R.id.ivPoster);
            ivPoster.setImageResource(posterID[pos]);

            AlertDialog.Builder dlg = new AlertDialog.Builder(context);
            dlg.setTitle(posterTitle[pos]);
            dlg.setView(dialogView);
            dlg.setNegativeButton("닫기", null);
            dlg.show();
        });

        return imgView;
    }
}
