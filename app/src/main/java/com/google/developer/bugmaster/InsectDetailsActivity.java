package com.google.developer.bugmaster;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.developer.bugmaster.data.Insect;
import com.google.developer.bugmaster.features.MainActivityPresenter;

import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.developer.bugmaster.R.id.txtFrandlyName;
import static com.google.developer.bugmaster.R.id.txtName;
import static com.google.developer.bugmaster.R2.id.imgDetailBug;
import static com.google.developer.bugmaster.R2.id.ratingDanger;
import static com.google.developer.bugmaster.R2.id.txtClassification;

public class InsectDetailsActivity extends AppCompatActivity implements DetailsMvpView {

    private DetailsActivityPresenter<DetailsMvpView> mPresenter;

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

        attachPresenter();

        init();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
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

    private void attachPresenter() {
        mPresenter = new DetailsActivityPresenter<>(this);
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
}
