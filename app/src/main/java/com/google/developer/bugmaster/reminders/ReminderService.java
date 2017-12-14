package com.google.developer.bugmaster.reminders;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.google.developer.bugmaster.App;
import com.google.developer.bugmaster.data.repository.IRepository;
import com.google.developer.bugmaster.features.quiz_screen.QuizActivity;
import com.google.developer.bugmaster.R;
import com.google.developer.bugmaster.data.Insect;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ReminderService extends IntentService {

    private IRepository repository;

    private static final String TAG = ReminderService.class.getSimpleName();
    private static final int NOTIFICATION_ID = 42;


    public ReminderService() {
        super(TAG);
    }

    @Override public void onCreate() {
        super.onCreate();
        repository = App.getApp().getAppRepository();
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "Quiz reminder event triggered");

        Random random = new Random();
        ArrayList<Insect> randomInsects = new ArrayList<>();

        List<Insect> insects = repository.getAllInsect();

        int insectCount = insects.size();
        Random rnd = new Random();
        Insect insect;
        int rndNum;
        for (int i = 0; i < QuizActivity.ANSWER_COUNT; i++) {
            rndNum = random.nextInt(insectCount);
            insect = insects.get(rndNum);
            randomInsects.add(insect);
        }

        //Present a notification to the user
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //Create action intent
        Intent action = new Intent(this, QuizActivity.class);
        //TODO: Add data elements to quiz launch

        //back---**
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(QuizActivity.class);
        stackBuilder.addNextIntent(action);
        //---

        //data----***
        action.putParcelableArrayListExtra(QuizActivity.EXTRA_INSECTS, randomInsects);
        action.putExtra(QuizActivity.EXTRA_ANSWER, randomInsects.get(random.nextInt(QuizActivity.ANSWER_COUNT - 1)));

        PendingIntent operation = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification note = new NotificationCompat.Builder(this)
                .setContentTitle(getString(R.string.notification_title))
                .setContentText(getString(R.string.notification_text))
                .setSmallIcon(R.drawable.ic_bug_empty)
                .setContentIntent(operation)
                .setAutoCancel(true)
                .build();

        manager.notify(NOTIFICATION_ID, note);

    }
}
