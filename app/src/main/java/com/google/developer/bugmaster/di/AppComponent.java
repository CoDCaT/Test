package com.google.developer.bugmaster.di;


import android.app.Application;

import com.google.developer.bugmaster.App;
import com.google.developer.bugmaster.data.repository.AppModule;

import javax.inject.Singleton;
import dagger.BindsInstance;
import dagger.Component;

import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(
        modules = {
                ActivityBindingModule.class,
                AndroidSupportInjectionModule.class,
                AppModule.class
        }
)
public interface AppComponent extends AndroidInjector<App> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
