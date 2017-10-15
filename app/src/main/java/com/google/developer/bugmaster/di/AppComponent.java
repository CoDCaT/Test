package com.google.developer.bugmaster.di;


import com.google.developer.bugmaster.features.details_insect.DetailsActivityPresenter;
import com.google.developer.bugmaster.features.details_insect.DetailsMvpView;
import com.google.developer.bugmaster.features.main_screen.MainActivityPresenter;
import com.google.developer.bugmaster.features.main_screen.MainMvpView;
import com.google.developer.bugmaster.features.quiz_screen.QuizActivityPresenter;
import com.google.developer.bugmaster.features.quiz_screen.QuizMvpView;
import com.google.developer.bugmaster.features.settings.SettingMvpView;
import com.google.developer.bugmaster.features.settings.SettingsFragmentPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                AppModule.class,
        }
)

public interface AppComponent {

    void inject(MainActivityPresenter<MainMvpView> presenter);
    void inject(DetailsActivityPresenter<DetailsMvpView> presenter);
    void inject(QuizActivityPresenter<QuizMvpView> presenter);
    void inject(SettingsFragmentPresenter<SettingMvpView> presenter);

}
