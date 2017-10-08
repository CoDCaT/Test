package com.google.developer.bugmaster.features.main_screen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.developer.bugmaster.features.quiz_screen.QuizActivity;
import com.google.developer.bugmaster.R;
import com.google.developer.bugmaster.features.settings.SettingsActivity;
import com.google.developer.bugmaster.data.BugsDbContract;
import com.google.developer.bugmaster.data.Insect;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainMvpView{

    private MainActivityPresenter<MainMvpView> mPresenter;
    private InsectListAdapter adapter;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.recycler_view) RecyclerView rvInsects;
    @BindView(R.id.fab) FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        attachPresenter();

        init();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sort:
                mPresenter.onClickSortButton();
                return true;
            case R.id.action_settings:
                mPresenter.onClickSettingMenu();
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

        String sortOrder = BugsDbContract.bugsEntry.COLUMN_NAME_FRIENDLY_NAME;
        String sortBy = BugsDbContract.bugsEntry.SORT_BY_ASC;
        mPresenter.onViewInitialized(sortOrder + sortBy);
    }

    private void setRecyclerViewInsects() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new InsectListAdapter();
        rvInsects.setAdapter(adapter);
        rvInsects.setLayoutManager(layoutManager);
    }

    private void setFloatingActionButton() {

        fab.setOnClickListener(v -> mPresenter.onClickFabButton());

    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
    }

    @Override public boolean isNetworkConnected() {
        return false;
    }

    @Override public void showLoading() {

    }

    @Override public void hideLoading() {

    }

    @Override public void onError(@NonNull String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override public void showMessage(@NonNull String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override public void showInsects(List<Insect> allInsect) {
        adapter.setInsects(allInsect);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void navigateToQuiz(ArrayList<Insect> insectArrayList, Insect answerInsect) {

        Intent intent = new Intent(this, QuizActivity.class);

        intent.putExtra(QuizActivity.EXTRA_INSECTS, insectArrayList);
        intent.putExtra(QuizActivity.EXTRA_ANSWER, answerInsect);
        startActivity(intent);

    }

    @Override
    public void navigateToSettings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

}
