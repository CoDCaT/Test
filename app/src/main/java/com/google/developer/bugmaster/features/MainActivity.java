package com.google.developer.bugmaster.features;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
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

import com.google.developer.bugmaster.QuizActivity;
import com.google.developer.bugmaster.R;
import com.google.developer.bugmaster.SettingsActivity;
import com.google.developer.bugmaster.data.BugsDbContract;
import com.google.developer.bugmaster.data.DataManager;
import com.google.developer.bugmaster.data.Insect;
import com.google.developer.bugmaster.data.MyAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainMvpView{

    private List<Insect> insects;
    private MainActivityPresenter<MainMvpView> mPresenter;
    private MyAdapter adapter;
    private String sortOrder = BugsDbContract.bugsEntry.COLUMN_NAME_FRIENDLY_NAME;
    private String sortBy = BugsDbContract.bugsEntry.SORT_BY_ASC;

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
                if (sortOrder.equals(BugsDbContract.bugsEntry.COLUMN_NAME_FRIENDLY_NAME)) {
                    sortOrder = BugsDbContract.bugsEntry.COLUMN_NAME_DANGER_LEVEL;
                    sortBy = BugsDbContract.bugsEntry.SORT_BY_DESC;
                }else {
                    sortOrder = BugsDbContract.bugsEntry.COLUMN_NAME_FRIENDLY_NAME;
                    sortBy = BugsDbContract.bugsEntry.SORT_BY_ASC;
                }

                mPresenter.onViewInitialized(sortOrder + sortBy);

                return true;
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //init------------------------------------------------****

    private void attachPresenter() {
        mPresenter = new MainActivityPresenter<>(this);
    }

    private void init() {
        ButterKnife.bind(this);

        setToolbar();
        setFloatingActionButton();
        setRecyclerViewInsects();

        mPresenter.onViewInitialized(sortOrder + sortBy);
    }

    private void setRecyclerViewInsects() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new MyAdapter();
        rvInsects.setAdapter(adapter);
        rvInsects.setLayoutManager(layoutManager);
    }

    private void setFloatingActionButton() {

        fab.setOnClickListener(v -> {

            ArrayList<Insect> insects = (ArrayList<Insect>) adapter.getInsects();

            int insectCount = insects.size();
            Random rnd = new Random();
            int rndNum;
            ArrayList<Insect> insectArrayList = new ArrayList<>(QuizActivity.ANSWER_COUNT);
            Insect insect;
            for(int i = 0; i < QuizActivity.ANSWER_COUNT; i++) {
                rndNum = rnd.nextInt(insectCount);
                insect = insects.get(rndNum);
                insectArrayList.add(insect);
            }

            Intent intent = new Intent(this, QuizActivity.class);

            intent.putExtra(QuizActivity.EXTRA_INSECTS, insectArrayList);
            intent.putExtra(QuizActivity.EXTRA_ANSWER, insectArrayList.get(rnd.nextInt(QuizActivity.ANSWER_COUNT)));
            startActivity(intent);
        });

    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
    }


    //MVP ---------------------------------------------------****

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
        adapter.setInsects(allInsect);
        adapter.notifyDataSetChanged();
    }

}
