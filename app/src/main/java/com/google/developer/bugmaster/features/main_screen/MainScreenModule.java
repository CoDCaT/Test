package com.google.developer.bugmaster.features.main_screen;


import com.google.developer.bugmaster.di.ActivityScope;


import dagger.Module;
import dagger.Provides;

@Module
public class MainScreenModule {

    @Provides
    @ActivityScope
    MainMvpView provideMainMvpView(MainActivity mainActivity){
        return mainActivity;
    }

    @Provides
    @ActivityScope
    MainActivityPresenter<MainMvpView> provideMainActivityPresenter (MainMvpView mainMvpView){
        return new MainActivityPresenter<>(mainMvpView);
    }

}
