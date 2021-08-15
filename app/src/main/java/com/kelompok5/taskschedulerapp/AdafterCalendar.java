package com.kelompok5.taskschedulerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdafterCalendar extends BaseAdapter {

    private final Context context;
    private final ArrayList<Modeldata> arrayList;

    public AdafterCalendar(Context context, ArrayList<Modeldata> arrayList){
        super();
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        convertView = layoutInflater.inflate(R.layout.data_calendar, null);
        TextView titletextView = convertView.findViewById(R.id.txtTitle);
        TextView dateTextView = convertView.findViewById(R.id.tgl);

        Modeldata modelData = arrayList.get(position);
        titletextView.setText(modelData.getTitle());
        dateTextView.setText(modelData.getDate());
        return convertView;
    }
}
