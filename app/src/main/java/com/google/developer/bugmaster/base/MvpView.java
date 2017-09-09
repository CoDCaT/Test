package com.google.developer.bugmaster.base;

import android.support.annotation.NonNull;
import android.view.View;

public interface MvpView {

    boolean isNetworkConnected();

    void showLoading();

    void hideLoading();

    void onError(@NonNull String message);

    void showMessage(@NonNull String message);

}
