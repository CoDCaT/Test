package com.google.developer.bugmaster.features.quiz_screen;

import com.google.developer.bugmaster.di.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class QuizScreenModule {

    @Provides
    @ActivityScope
    QuizMvpView provideQuizMvpView(QuizActivity quizActivity){
        return quizActivity;
    }

    @Provides
    @ActivityScope
    QuizActivityPresenter<QuizMvpView> provideQuizActivityPresenter(QuizMvpView quizMvpView){
        return new QuizActivityPresenter<>(quizMvpView);
    }

}
