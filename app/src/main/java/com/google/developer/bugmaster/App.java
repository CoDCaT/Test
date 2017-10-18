package com.google.developer.bugmaster;


import android.app.Application;

import com.google.developer.bugmaster.data.repository.AppRepository;
import com.google.developer.bugmaster.di.AppComponent;

public class App extends Application {

    public static volatile AppRepository appRepository;

    @Override
    public void onCreate() {
        super.onCreate();

        if (appRepository == null) appRepository = new AppRepository(this);
    }



}
