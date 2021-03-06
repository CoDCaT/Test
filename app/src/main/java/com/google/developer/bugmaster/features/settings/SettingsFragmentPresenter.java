package com.google.developer.bugmaster.features.settings;

import android.content.Context;

import com.google.developer.bugmaster.base.BasePresenter;
import com.google.developer.bugmaster.reminders.AlarmReceiver;

import javax.inject.Inject;

public class SettingsFragmentPresenter<V extends SettingMvpView> extends BasePresenter<V> implements SettingMvpPresenter<V> {

    @Inject
    public SettingsFragmentPresenter(V mMvpView) {
        super(mMvpView);
    }

    @Override
    public void runService(Context context) {
        AlarmReceiver.scheduleAlarm(context);
    }
}
