package com.google.developer.bugmaster.features.details_insect;


import android.content.ClipData;
import android.content.Intent;
import android.view.MenuItem;

import com.google.developer.bugmaster.base.MvpPresenter;

public interface DetailsMvpPresenter<V extends DetailsMvpView> extends MvpPresenter<V> {

    void onViewInitialized(Intent intent);

    void onClickButtonMenu(MenuItem item);
}
