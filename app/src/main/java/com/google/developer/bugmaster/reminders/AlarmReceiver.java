package com.google.developer.bugmaster.reminders;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.developer.bugmaster.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AlarmReceiver extends BroadcastReceiver {
    private static final String TAG = AlarmReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        //Schedule alarm on BOOT_COMPLETED
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            scheduleAlarm(context);
        }
    }

    /* Schedule the alarm based on user preferences */
    public static void scheduleAlarm(Context context) {
        AlarmManager manager = AlarmManagerProvider.getAlarmManager(context);

        String keyReminder = context.getString(R.string.pref_key_reminder);
        String keyAlarm = context.getString(R.string.pref_key_alarm);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        boolean enabled = preferences.getBoolean(keyReminder, false);

        //Intent to trigger
        Intent intent = new Intent(context, ReminderService.class);
        PendingIntent operation = PendingIntent
                .getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);


        if (enabled) {
            //Gather the time preference
            Calendar startTime = Calendar.getInstance();

            try {
                String alarmPref = preferences.getString(keyAlarm, "12:00");
                SimpleDateFormat format = new SimpleDateFormat("HH:mm", Locale.getDefault());

                Calendar hour = Calendar.getInstance();
                hour.setTime(format.parse(alarmPref));

                startTime.set(Calendar.HOUR_OF_DAY, hour.get(Calendar.HOUR_OF_DAY));
                startTime.set(Calendar.MINUTE, hour.get(Calendar.MINUTE));
                startTime.set(Calendar.SECOND, hour.get(Calendar.SECOND));

            } catch (ParseException e) {
                Log.w(TAG, "Unable to determine alarm start time", e);
                return;
            }


            Log.d(TAG, "Scheduling quiz reminder alarm");
            manager.setRepeating(AlarmManager.RTC_WAKEUP, startTime.getTimeInMillis(), 24 * 60 * 60000, operation);
        } else {
            Log.d(TAG, "Disabling quiz reminder alarm");
            manager.cancel(operation);
        }
    }

}
