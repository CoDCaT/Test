package com.google.developer.bugmaster.features.settings;


import com.google.developer.bugmaster.di.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class SettingsScreenModule {

    @Provides
    @ActivityScope
    SettingMvpView provideSettingMvpView(SettingsFragment settingsFragment){
        return settingsFragment;
    }

    @Provides
    @ActivityScope
    SettingsFragmentPresenter<SettingMvpView> provideSettingsFragmentPresenter(SettingMvpView settingMvpView){
        return new SettingsFragmentPresenter<>(settingMvpView);
    }

}
