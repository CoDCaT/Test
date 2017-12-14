package com.google.developer.bugmaster.features.main_screen;

import android.app.Application;
import android.view.MenuItem;

import com.google.developer.bugmaster.App;
import com.google.developer.bugmaster.R;
import com.google.developer.bugmaster.base.BasePresenter;
import com.google.developer.bugmaster.data.Insect;
import com.google.developer.bugmaster.data.repository.IRepository;
import com.google.developer.bugmaster.features.quiz_screen.QuizActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

public class MainActivityPresenter<V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V>{

    private IRepository repository;
    private List<Insect> insects;

    @Inject
    MainActivityPresenter(V mMvpView) {
        super(mMvpView);
        repository = App.getApp().getAppRepository();
    }

    @Override
    public void onViewInitialized() {
        insects = repository.getAllInsect();

        String sort = repository.readFromPreference("sort");

        if(sort.equals("name")) Collections.sort(insects, (o1, o2) -> o1.getName().compareTo(o2.getName()));
        else Collections.sort(insects);

        getMvpView().showInsects(insects);
    }

    @Override
    public void onClickFabButton() {

        //TODO: create Mapper!!!
        ArrayList<Insect> insects = (ArrayList<Insect>) repository.getCurrentInsect();

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
    public void onClickButtonMenu(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_sort:
                sortListInsect(insects);
                getMvpView().showInsects(insects);
                break;
            case R.id.action_settings:
                getMvpView().navigateToSettings();
                break;
        }

    }

    private List<Insect> sortListInsect(List<Insect> insects){

        String sort = repository.readFromPreference("sort");
        if(sort.equals("danger")){
            Collections.sort(insects, (o1, o2) -> o1.getName().compareTo(o2.getName()));
                repository.saveToPreference("name");
        }else {
            Collections.sort(insects);
                repository.saveToPreference("danger");
        }

        return insects;
    }
}
