package com.example.abdulbasit.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class custom_list extends ArrayAdapter<structure> {

    public custom_list(Context context, ArrayList<structure> data) {
        super(context, R.layout.custom_list, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater myInflater = LayoutInflater.from(getContext());
        View customView = myInflater.inflate(R.layout.custom_list, parent, false);

        structure st = getItem(position);

        TextView desc = (TextView) customView.findViewById(R.id.content);
        TextView date = (TextView) customView.findViewById(R.id.date);
        TextView time = (TextView) customView.findViewById(R.id.time);

        desc.setText(st.getDescription());
        date.setText(st.getDate());
        time.setText(st.getTime());

        return customView;
    }
}
