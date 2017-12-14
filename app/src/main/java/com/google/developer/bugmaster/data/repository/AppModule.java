package com.google.developer.bugmaster.data.repository;


import android.app.Application;
import android.content.Context;

import com.google.developer.bugmaster.data.BugsDbHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application mApplication) {
        return mApplication;
    }

    @Provides
    @Singleton
    BugsDbHelper provideBugsDbHelper(Application context){
        return new BugsDbHelper(context);
    }

    @Provides
    AppRepository provideAppRepository(Context context, BugsDbHelper bugsDbHelper){
        return new AppRepository(context, bugsDbHelper);
    }

}
