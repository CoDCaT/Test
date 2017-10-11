package com.google.developer.bugmaster.features.main_screen;

import android.content.Context;
import android.view.MenuItem;

import com.google.developer.bugmaster.R;
import com.google.developer.bugmaster.base.BasePresenter;
import com.google.developer.bugmaster.data.Insect;
import com.google.developer.bugmaster.data.specification.InsectsSqlSpecification;
import com.google.developer.bugmaster.data.repository.SqlRepository;
import com.google.developer.bugmaster.features.quiz_screen.QuizActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivityPresenter<V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V>{

    private SqlRepository repository;
    private List<Insect> insects;
    private Context context;

    MainActivityPresenter(V mMvpView) {
        super(mMvpView);
        this.context = (Context) mMvpView;
        repository = SqlRepository.getInstance(context);
    }

    @Override
    public void onViewInitialized() {
        insects = repository.query(new InsectsSqlSpecification());

        String sort = repository.readSharedSetting(context, "sort", "name");
        if(sort.equals("name")){
            Collections.sort(insects, (o1, o2) -> o1.getName().compareTo(o2.getName()));
        }else {
            Collections.sort(insects);
        }

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

        String sort = repository.readSharedSetting(context, "sort", "name");
        if(sort.equals("danger")){
            Collections.sort(insects, (o1, o2) -> o1.getName().compareTo(o2.getName()));
                repository.saveSharedSetting(context, "sort", "name");
        }else {
            Collections.sort(insects);
                repository.saveSharedSetting(context, "sort", "danger");
        }

        return insects;
    }
}
