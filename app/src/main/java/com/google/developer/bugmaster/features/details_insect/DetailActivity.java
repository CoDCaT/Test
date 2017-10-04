package com.google.developer.bugmaster.features.details_insect;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.developer.bugmaster.R;
import com.google.developer.bugmaster.data.Insect;

import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

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

        Intent intent = getIntent();
        Insect insect = intent.getParcelableExtra("insect");

        Bitmap bitmap = null;
        InputStream is;
        try {
            is = getAssets().open(insect.getImageAsset());
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        imgDetailBug.setImageBitmap(bitmap);
        txtFrandlyName.setText(insect.getName());
        txtName.setText(insect.getScientificName());
        String classification = String.format("Classification: %1$s", insect.getClassification());
        txtClassification.setText(classification);
        ratingDanger.setRating(insect.getDangerLevel());

    }

    private void setToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
