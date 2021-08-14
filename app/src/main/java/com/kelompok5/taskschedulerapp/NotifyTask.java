package com.kelompok5.taskschedulerapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class NotifyTask extends BroadcastReceiver {

    public static String NOTIFICATION_ID = "notification-id";
    public static String NOTIFICATION = "notification";

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = intent.getParcelableExtra(NOTIFICATION);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(Hal_Dashboard.NOTIFICATION_CHANNEL_ID,
                    "Pengingat", importance);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(notificationChannel);
        }
        int id = intent.getIntExtra(NOTIFICATION_ID, 0);
        if (notificationManager != null) {
            notificationManager.notify(id, notification);
        }

    }



}

