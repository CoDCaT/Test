package com.google.developer.bugmaster.features.quiz;

import android.content.Context;
import android.content.Intent;

import com.google.developer.bugmaster.R;
import com.google.developer.bugmaster.base.BasePresenter;
import com.google.developer.bugmaster.data.Insect;

import java.util.ArrayList;
import java.util.List;


public class QuizActivityPresenter<V extends QuizMvpView> extends BasePresenter<V> implements QuizMvpPresenter<V> {

    Context context;

    public QuizActivityPresenter(V mMvpView) {
        super(mMvpView);
        this.context = (Context) mMvpView;
    }
    
    @Override
    public void onViewInitialized(Intent intent) {
        
        List<Insect> insects = intent.getParcelableArrayListExtra(QuizActivity.EXTRA_INSECTS);
        Insect selected = intent.getParcelableExtra(QuizActivity.EXTRA_ANSWER);
        
        //Load answer strings
        ArrayList<String> options = new ArrayList<>();
        for (Insect item : insects) {
            options.add(item.scientificName);
        }
        
        String question = context.getString(R.string.question_text, selected.name);
        
        getMvpView().showQuestion(question, options, selected.getScientificName());
    }
}
