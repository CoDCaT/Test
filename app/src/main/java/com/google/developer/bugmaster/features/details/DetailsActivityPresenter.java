package com.google.developer.bugmaster.features.details;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.developer.bugmaster.base.BasePresenter;
import com.google.developer.bugmaster.data.Insect;

import java.io.IOException;
import java.io.InputStream;

public class DetailsActivityPresenter<V extends DetailsMvpView> extends BasePresenter<V> implements DetailsMvpPresenter<V> {

    Context context;

    public DetailsActivityPresenter(V mMvpView) {
        super(mMvpView);
        this.context = (Context) mMvpView;
    }

    @Override
    public void onViewInitialized(Intent intent) {

        Insect insect = intent.getParcelableExtra("insect");

        Bitmap bitmap = null;
        InputStream is;
        try {
            is = context.getAssets().open(insect.getImageAsset());
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String classification = String.format("Classification: %1$s", insect.getClassification());
        getMvpView().showDetails(bitmap, insect.getName(), insect.getScientificName(), classification, insect.getDangerLevel());
    }
}
