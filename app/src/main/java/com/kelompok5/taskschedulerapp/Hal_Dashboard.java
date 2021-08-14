package com.kelompok5.taskschedulerapp;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormatSymbols;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class Hal_Dashboard extends Fragment {

    public static final String NOTIFICATION_CHANNEL_ID = "10001";
    private final static String default_notification_channel_id = "default";
    private DatabaseHelper dbHelper;
    private ListView lsView;
    private static final String TAG = "Hal_Dashboard";
    private FloatingActionButton btnAdd;
    private final AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.3F);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);

        dbHelper = new DatabaseHelper(getActivity());
        lsView = rootView.findViewById(R.id.Rview);
        btnAdd = rootView.findViewById(R.id.button_add);

        populateListView();
        hideBtn();
        onBtnAddclick();

        return rootView;
    }

    private void scheduleNotif(Notification notification, long delay){
        Intent notifIntent = new Intent(getActivity(), NotifyTask.class);
        notifIntent.putExtra(NotifyTask.NOTIFICATION_ID, 1);
        notifIntent.putExtra(NotifyTask.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), 0,
                notifIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getLayoutInflater().getContext().getSystemService(Context.ALARM_SERVICE);
        if (alarmManager != null){
            alarmManager.set(AlarmManager.RTC_WAKEUP, delay, pendingIntent);
        }
    }


    private Notification getNotif(String content){
        Intent intent = new Intent(getActivity(), NavBottom.class);
        intent.putExtra("fragmen_dashboard", "dashboard");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getLayoutInflater().getContext(), default_notification_channel_id);
        builder.setContentTitle("Pengingat");
        builder.setContentText(content);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setSmallIcon(R.drawable.ic_judul);
        builder.setDefaults(Notification.DEFAULT_LIGHTS | Notification.DEFAULT_SOUND);
        builder.setChannelId(NOTIFICATION_CHANNEL_ID);
        builder.setPriority(NotificationCompat.PRIORITY_HIGH);
        return builder.build();
    }


    private void insertDataToDb(String title, String date, String time){
        boolean insertData = dbHelper.InsertData(title, date, time);
        if (insertData){
            try {
                populateListView();
                toastMsg("Task Di Tambahkan");
            } catch (Exception e){
                e.printStackTrace();
            }
        } else
            toastMsg("Opp terjadi kesalahan!");
    }

    private void toastMsg(String msg) {
        Toast t = Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT);
        t.setGravity(Gravity.CENTER,0,0);
        t.show();
    }

    private void populateListView(){
        try{
            ArrayList<Modeldata> items = dbHelper.getAllData();
            AdapterData itemsAdopter = new AdapterData(getActivity(), items);
            lsView.setAdapter(itemsAdopter);
            itemsAdopter.notifyDataSetChanged();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    private void hideBtn() {
        lsView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_IDLE) {
                    btnAdd.show();
                } else {
                    btnAdd.hide();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });
    }

    private void onBtnAddclick() {
        try {
            btnAdd.setOnClickListener(v -> {
                v.startAnimation(buttonClick);
                showAddDialog();
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAddDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getLayoutInflater().getContext(), R.style.MydialogTheme);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.activity_add, null);
        dialogBuilder.setView(dialogView);

        final EditText tugas = dialogView.findViewById(R.id.title);
        final TextView tanggal = dialogView.findViewById(R.id.date);
        final TextView waktu = dialogView.findViewById(R.id.time);
        final Button tambah = dialogView.findViewById(R.id.addTask);
        final ImageView close = dialogView.findViewById(R.id.btnclose);

        AlertDialog show = dialogBuilder.show();

        final long date = System.currentTimeMillis();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat dateSdf = new SimpleDateFormat("d MMMM");
        String dateString = dateSdf.format(date);
        tanggal.setText((dateString));
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat timeSdf = new SimpleDateFormat("HH : mm ");
        String timeString = timeSdf.format(date);
        waktu.setText(timeString);

        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());

        tanggal.setOnClickListener(v -> {
            @SuppressLint("SetTextI18n")
            final DatePickerDialog datePickerDialog = new DatePickerDialog(getLayoutInflater().getContext(),
                    (view, year, monthOfYear, dayOfMonth) -> {
                        String newMonth = getMonth(monthOfYear + 1);
                        tanggal.setText(dayOfMonth + " " + newMonth);
                        cal.set(Calendar.YEAR, year);
                        cal.set(Calendar.MONTH, monthOfYear);
                        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
            datePickerDialog.getDatePicker().setMinDate(date);
        });

        waktu.setOnClickListener(v -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(getLayoutInflater().getContext(),
                    (view, hourOfDay, minute) -> {
                        String time;
                        @SuppressLint("DefaultLocale") String minTime = String.format("%02d", minute);
                        @SuppressLint("DefaultLocale") String minHours = String.format("%02d", hourOfDay);
                        if (hourOfDay < 0 || hourOfDay >= 12) {
                            if (hourOfDay != 12) {
                                hourOfDay = hourOfDay - 12;
                            }
                        }
                        time = minHours + " : " + minTime;

                        waktu.setText(time);
                        cal.set(Calendar.HOUR, hourOfDay);
                        cal.set(Calendar.MINUTE, minute);
                        cal.set(Calendar.SECOND, 0);
                        Log.d(TAG, "onTimeSet: Time has been set successfully");
                    }, cal.get(Calendar.HOUR), cal.get(Calendar.MINUTE), true);
            timePickerDialog.show();
        });

      tambah.setOnClickListener(v -> {
           String title = tugas.getText().toString();
           String date1 = tanggal.getText().toString();
           String time = waktu.getText().toString();
           if (title.length() != 0) {
               try {
                   insertDataToDb(title, date1, time);
                   scheduleNotif(getNotif(title), cal.getTimeInMillis());
               } catch (Exception e) {
                   e.printStackTrace();
               } show.dismiss();
           } else {
               toastMsg("Title tidak boleh kosong");
           }
       });

      close.setOnClickListener(v -> {
          toastMsg("keluar");
          show.dismiss();
      });

    }

    private String getMonth(int month){
        return new DateFormatSymbols().getMonths()[month-1];
    }
}
