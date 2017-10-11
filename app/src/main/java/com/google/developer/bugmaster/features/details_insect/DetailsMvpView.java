package com.google.developer.bugmaster.features.details_insect;


import android.graphics.Bitmap;

import com.google.developer.bugmaster.base.MvpView;

public interface DetailsMvpView extends MvpView {

    void showDetails(Bitmap bitmap, String name, String scientificName, String classification, int dangerLevel);

    void closeScreen();
}
