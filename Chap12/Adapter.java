package com.example.pj0317;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {
    LayoutInflater inflater;
    ArrayList<Item> items;
    Context context;
    int layout;

    Adapter(Context context, int layout, ArrayList<Item> items) {
        this.context = context;
        this.layout = layout;
        this.items = items;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = inflater.inflate(layout, parent, false);

        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvTel = convertView.findViewById(R.id.tvTel);
        tvName.setText(items.get(position).getName());
        tvTel.setText(items.get(position).getTel());

        LinearLayout item_layout = convertView.findViewById(R.id.layout_item);
        item_layout.setOnLongClickListener(v -> {
            items.remove(position);
            this.notifyDataSetChanged();
            Toast.makeText(context, "삭제됨", Toast.LENGTH_SHORT).show();

            return false;
        });

        return convertView;
    }
}
