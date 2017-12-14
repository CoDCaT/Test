package com.google.developer.bugmaster.features.main_screen;


import android.view.MenuItem;

import com.google.developer.bugmaster.base.MvpPresenter;

interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {

    void onViewInitialized();

    void onClickFabButton();

    void onClickButtonMenu(MenuItem item);


}
