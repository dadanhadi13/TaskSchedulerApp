package com.kelompok5.taskschedulerapp;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.graphics.ColorSpace;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdapterData extends BaseAdapter {

    private Context context;
    private ArrayList<Modeldata> arrayList;

    public AdapterData(Context context, ArrayList<Modeldata> arrayList){
        super();
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount(){
        return this.arrayList.size();
    }

    @Override
    public Object getItem(int position){
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @SuppressLint({"ViewHolder","inflateParams"})
    @Override
    public View getView(int position, View convertView, final ViewGroup parent){
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert layoutInflater != null;
        convertView = layoutInflater.inflate(R.layout.data_itemlist, null);
        TextView titletextView = convertView.findViewById(R.id.titleList);
        TextView dateTextView = convertView.findViewById(R.id.dateList);
        TextView timeTextView = convertView.findViewById(R.id.timeList);
        RadioButton dellTask = convertView.findViewById(R.id.Rbutton);
        dellTask.setTag(position);

        dellTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int pos = (int) v.getTag();
                deleteItem(pos);
            }
        });

        Modeldata modelData = arrayList.get(position);
        titletextView.setText(modelData.getTitle());
        dateTextView.setText(modelData.getDate());
        timeTextView.setText(modelData.getTime());
        return convertView;
    }

    private void deleteItem(int position) {
        deleteItemFromDb(arrayList.get(position).getId());
        arrayList.remove(position);
        notifyDataSetChanged();
    }

    private void deleteItemFromDb(int id) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        try {
            databaseHelper.deleteData(id);
            toastMsg("Tugas berhasil diselesaikan");
        } catch (Exception e) {
            e.printStackTrace();
            toastMsg("Ada kesalahan saat menyelesaikan task");
        }
    }

    private void toastMsg(String msg) {
        Toast t = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        t.setGravity(Gravity.CENTER,0,0);
        t.show();
    }

}