package com.google.developer.bugmaster;


import android.content.Intent;

import com.google.developer.bugmaster.base.MvpPresenter;

public interface DetailsMvpPresenter<V extends DetailsMvpView> extends MvpPresenter<V> {

    void onViewInitialized(Intent intent);

}
