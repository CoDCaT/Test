package com.google.developer.bugmaster;

import com.google.developer.bugmaster.base.MvpPresenter;

public interface QuizMvpPresenter<V extends QuizMvpView> extends MvpPresenter<V> {
      void onViewInitialized(Intent intent);
}
