package com.google.developer.bugmaster.features;

import android.content.Context;

import com.google.developer.bugmaster.base.BasePresenter;
import com.google.developer.bugmaster.data.DataManager;

public class MainActivityPresenter<V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V>{

    private DataManager dataManager;

    public MainActivityPresenter(V mMvpView) {
        super(mMvpView);

        dataManager = DataManager.getInstance((Context) mMvpView);
    }

    @Override public void onViewInitialized() {
//        getMvpView().showInsects(dataManager.getAllInsect(null));
    }
}
