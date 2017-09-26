package com.google.developer.bugmaster;

import com.google.developer.bugmaster.base.BasePresenter;


public class QuizActivityPresenter<V extends QuizMvpView> extends BasePresenter<V> {

    public QuizActivityPresenter(V mMvpView) {
        super(mMvpView);
    }
}
