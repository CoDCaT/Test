package com.google.developer.bugmaster;


import com.google.developer.bugmaster.data.repository.AppRepository;
import com.google.developer.bugmaster.di.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class App extends DaggerApplication {

//    public static volatile AppRepository appRepository;

    @Inject AppRepository appRepository;

    private static App app;
    public static App getApp() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
//        if (appRepository == null) appRepository = new AppRepository(this);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().application(this).build();
    }

    public AppRepository getAppRepository() {
        return appRepository;
    }
}
