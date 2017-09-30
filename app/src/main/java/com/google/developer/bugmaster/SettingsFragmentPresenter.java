package com.google.developer.bugmaster;

import android.content.Context;

import com.google.developer.bugmaster.base.BasePresenter;
import com.google.developer.bugmaster.reminders.AlarmReceiver;

public class SettingsFragmentPresenter<V extends SettingMvpView> extends BasePresenter<V> implements SettingMvpPresenter<V> {


    public SettingsFragmentPresenter(V mMvpView) {
        super(mMvpView);
    }

    @Override
    public void runService(Context context) {
        AlarmReceiver.scheduleAlarm(context);
    }
}
