package com.google.developer.bugmaster.di;


import com.google.developer.bugmaster.features.details_insect.DetailScreenModule;
import com.google.developer.bugmaster.features.details_insect.InsectDetailsActivity;
import com.google.developer.bugmaster.features.main_screen.MainActivity;
import com.google.developer.bugmaster.features.main_screen.MainScreenModule;
import com.google.developer.bugmaster.features.quiz_screen.QuizActivity;
import com.google.developer.bugmaster.features.quiz_screen.QuizScreenModule;
import com.google.developer.bugmaster.features.settings.SettingsFragment;
import com.google.developer.bugmaster.features.settings.SettingsScreenModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = MainScreenModule.class)
    abstract MainActivity mainActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = DetailScreenModule.class)
    abstract InsectDetailsActivity insectDetailsActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = QuizScreenModule.class)
    abstract QuizActivity quizActivityInjector();

    @ActivityScope
    @ContributesAndroidInjector(modules = SettingsScreenModule.class)
    abstract SettingsFragment settingsFragment();

}
