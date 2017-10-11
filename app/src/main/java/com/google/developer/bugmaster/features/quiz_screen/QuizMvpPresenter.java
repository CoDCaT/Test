package com.google.developer.bugmaster.features.quiz_screen;

import android.content.Intent;
import android.view.MenuItem;

import com.google.developer.bugmaster.base.MvpPresenter;

public interface QuizMvpPresenter<V extends QuizMvpView> extends MvpPresenter<V> {
      void onViewInitialized(Intent intent);

      void onClickButtonMenu(MenuItem item);
}
