package com.google.developer.bugmaster.features.details_insect;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.developer.bugmaster.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public class InsectDetailsActivity extends DaggerAppCompatActivity implements DetailsMvpView {

    @Inject DetailsActivityPresenter<DetailsMvpView> mPresenter;

    @BindView(R.id.imgDetailBug) ImageView imgDetailBug;
    @BindView(R.id.txtFrandlyName) TextView txtFrandlyName;
    @BindView(R.id.txtName) TextView txtName;
    @BindView(R.id.txtClassification) TextView txtClassification;
    @BindView(R.id.ratingDanger) RatingBar ratingDanger;
    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        init();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        mPresenter.onClickButtonMenu(item);
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        ButterKnife.bind(this);

        setToolbar();
        setViews();

    }

    private void setViews() {
        mPresenter.onViewInitialized(getIntent());
    }

    private void setToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean isNetworkConnected() {
        return false;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onError(@NonNull String message) {

    }

    @Override
    public void showMessage(@NonNull String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDetails(Bitmap bitmap, String name, String scientificName, String classification, int dangerLevel) {

        imgDetailBug.setImageBitmap(bitmap);
        txtFrandlyName.setText(name);
        txtName.setText(scientificName);
        txtClassification.setText(classification);
        ratingDanger.setRating(dangerLevel);

    }

    @Override
    public void closeScreen() {
        finish();
    }
}
