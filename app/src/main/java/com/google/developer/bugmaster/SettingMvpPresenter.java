package com.google.developer.bugmaster;


import android.content.Context;

import com.google.developer.bugmaster.base.MvpPresenter;

public interface SettingMvpPresenter<V extends SettingMvpView> extends MvpPresenter<V> {

    void runService(Context context);

}