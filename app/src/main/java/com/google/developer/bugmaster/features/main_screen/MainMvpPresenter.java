package com.google.developer.bugmaster.features.main_screen;


import com.google.developer.bugmaster.base.MvpPresenter;

interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

    void onViewInitialized(String sort);

    void onClickFabButton();
}
