package com.google.developer.bugmaster.features;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.developer.bugmaster.R;
import com.google.developer.bugmaster.SettingsActivity;
import com.google.developer.bugmaster.data.BugsDbHelper;
import com.google.developer.bugmaster.data.DataManager;
import com.google.developer.bugmaster.data.Insect;
import com.google.developer.bugmaster.data.MyAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainMvpView{

    private List<Insect> insects;
    private MainActivityPresenter<MainMvpView> mPresenter;
    private MyAdapter adapter;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.recycler_view) RecyclerView rvInsects;
    @BindView(R.id.fab) FloatingActionButton fab;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        attachPresenter();

        init();

    }

    @Override protected void onResume() {
        super.onResume();
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sort:
                //TODO: Implement the sort action
                return true;
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void attachPresenter() {
        mPresenter = new MainActivityPresenter<>(this);
    }

    private void init() {
        ButterKnife.bind(this);

        setToolbar();
        setFloatingActionButton();
        setRecyclerViewInsects();

        dbTest();

        mPresenter.onViewInitialized();
    }

    private void setRecyclerViewInsects() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new MyAdapter();
        rvInsects.setAdapter(adapter);
        rvInsects.setLayoutManager(layoutManager);
    }

    private void setFloatingActionButton() {
        fab.setOnClickListener(v -> Log.d("ff", "LOGTAG"));

    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
    }

    private List<Insect> getInsectData() {
        DataManager dataManager = DataManager.getInstance(this);
        insects = dataManager.getAllInsect(null);


//        if (c.moveToFirst()) {
//
//            int friendlyName = c.getColumnIndex("friendlyName");
//            int scientificName = c.getColumnIndex("scientificName");
//            int classification = c.getColumnIndex("classification");
//            int imageAsset = c.getColumnIndex("imageAsset");
//            int dangerLevel = c.getColumnIndex("dangerLevel");
//
//            do {
//
//                insects.add(new Insect(c.getString(friendlyName), c.getString(scientificName), c.getString(classification), c.getString(imageAsset), c.getInt(dangerLevel)));
//
//            } while (c.moveToNext());
//        } else {
//
//        }
//
//        c.close();

        return insects;
    }

    @Override public boolean isNetworkConnected() {
        return false;
    }

    @Override public void showLoading() {

    }

    @Override public void hideLoading() {

    }

    @Override public void onError(@NonNull String message) {

    }

    @Override public void showMessage(@NonNull String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override public void showInsects(List<Insect> allInsect) {
//        adapter.setInsects(allInsect);
//        adapter.notifyDataSetChanged();
    }

    private void dbTest(){

        BugsDbHelper bugsDbHelper = new BugsDbHelper(this);
        SQLiteDatabase db = bugsDbHelper.getWritableDatabase();

//        ContentValues cv = new ContentValues();
//        cv.put("friendlyName", "1");
//        cv.put("scientificName", "2");
//        cv.put("classification", "3");
//        cv.put("imageAsset", "4");
//        cv.put("dangerLevel", "5");
//
//        db.insert("bugs", null, cv);

        Cursor c = db.query("bugs", null, null, null, null, null, null);

        if (c.moveToFirst()) {
            insects = new ArrayList<>();
            int friendlyName = c.getColumnIndex("friendlyName");
            int scientificName = c.getColumnIndex("scientificName");
            int classification = c.getColumnIndex("classification");
            int imageAsset = c.getColumnIndex("imageAsset");
            int dangerLevel = c.getColumnIndex("dangerLevel");

            do {

                insects.add(new Insect(c.getString(friendlyName), c.getString(scientificName), c.getString(classification), c.getString(imageAsset), c.getInt(dangerLevel)));

            } while (c.moveToNext());
        } else {

        }

        if (insects != null) {
            adapter.setInsects(insects);
            adapter.notifyDataSetChanged();
        }

    }
}
