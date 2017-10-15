package com.google.developer.bugmaster;


import android.app.Application;

import com.google.developer.bugmaster.di.AppComponent;
import com.google.developer.bugmaster.di.AppModule;
import com.google.developer.bugmaster.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class App extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        getAppComponent();
    }

    public AppComponent getAppComponent() {
        if (appComponent == null){
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .build();
        }
        return appComponent;
    }

//    @Override
//    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
//        AppComponent appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
//        appComponent.inject(this);
//        return appComponent;
//    }
}
