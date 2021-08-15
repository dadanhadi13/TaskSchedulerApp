package com.kelompok5.taskschedulerapp;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ListView;

import java.util.ArrayList;

public class Hal_Tanggal extends Fragment {

    private DatabaseHelper dbHelper;
    private ListView lsView;
    private static final String TAG = "Hal_Tanggal";


    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_tanggal, container, false);

        dbHelper = new DatabaseHelper(getActivity());
        lsView = rootView.findViewById(R.id.Rview);
        CalendarView calenderView = rootView.findViewById(R.id.cdView);

        calenderView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String date = year + "/" + month + "/" + dayOfMonth;
            Log.d(TAG, "onSelectedDayChange: mm/dd/yyyy:" + date);
        });


        populateListView();

        return rootView;
      }

    private void populateListView(){
        try{
            ArrayList<Modeldata> items = dbHelper.getAllData();
            AdafterCalendar itemsAdopter = new AdafterCalendar(getActivity(), items);
            lsView.setAdapter(itemsAdopter);
            itemsAdopter.notifyDataSetChanged();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

}