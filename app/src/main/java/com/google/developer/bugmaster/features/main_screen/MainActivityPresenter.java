package com.google.developer.bugmaster.features.main_screen;

import android.content.Context;

import com.google.developer.bugmaster.base.BasePresenter;
import com.google.developer.bugmaster.data.Insect;
import com.google.developer.bugmaster.data.InsectsSqlSpecification;
import com.google.developer.bugmaster.data.SqlRepository;
import com.google.developer.bugmaster.features.quiz_screen.QuizActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class MainActivityPresenter<V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V>{

    private SqlRepository repository;
    private List<Insect> insects;

    public MainActivityPresenter(V mMvpView) {
        super(mMvpView);
        repository = SqlRepository.getInstance((Context) mMvpView);
    }

    @Override
    public void onViewInitialized(String sort) {
        insects = repository.query(new InsectsSqlSpecification());
        getMvpView().showInsects(insects);
    }

    @Override
    public void onClickFabButton() {

        //TODO: create Mapper!!!
        ArrayList<Insect> insects = (ArrayList<Insect>) repository.query(new InsectsSqlSpecification());

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

    @Override
    public void onClickSettingMenu() {
        getMvpView().navigateToSettings();
    }

    @Override
    public void onClickSortButton() {
        //TODO: fix it!
        Collections.sort(insects, (o1, o2) -> o1.getScientificName().compareTo(o2.getScientificName()));
        getMvpView().showInsects(insects);
    }
}
