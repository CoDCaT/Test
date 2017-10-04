package com.google.developer.bugmaster.features.main_screen;

import android.content.Context;
import android.content.Intent;

import com.google.developer.bugmaster.base.BasePresenter;
import com.google.developer.bugmaster.data.DataManager;
import com.google.developer.bugmaster.data.Insect;
import com.google.developer.bugmaster.features.quiz_screen.QuizActivity;

import java.util.ArrayList;
import java.util.Random;

public class MainActivityPresenter<V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V>{

    private DataManager dataManager;

    public MainActivityPresenter(V mMvpView) {
        super(mMvpView);

        dataManager = DataManager.getInstance((Context) mMvpView);
    }

    @Override
    public void onViewInitialized(String sort) {
        getMvpView().showInsects(dataManager.getAllInsect(sort));
    }

    @Override
    public void onClickFabButton() {

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

        getMvpView().navigateToQuiz(insectArrayList, insectArrayList.get(rnd.nextInt(QuizActivity.ANSWER_COUNT)));

    }
}
