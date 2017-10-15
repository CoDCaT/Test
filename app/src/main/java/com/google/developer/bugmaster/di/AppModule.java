package com.google.developer.bugmaster.di;

import android.content.Context;
import com.google.developer.bugmaster.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final App app;

    public AppModule(App app) {
        this.app = app;
    }


    @Provides @Singleton
    public Context provideContext() {
        return app;
    }

}
