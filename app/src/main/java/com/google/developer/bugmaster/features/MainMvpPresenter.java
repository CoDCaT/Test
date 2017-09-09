package com.google.developer.bugmaster.features;


import com.google.developer.bugmaster.base.MvpPresenter;

interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

    void onViewInitialized();
}
