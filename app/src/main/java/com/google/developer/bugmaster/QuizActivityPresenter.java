package com.google.developer.bugmaster;

import com.google.developer.bugmaster.base.BasePresenter;


public class QuizActivityPresenter<V extends QuizMvpView> extends BasePresenter<V> implements QuizMvpPresenter<V> {

    public QuizActivityPresenter(V mMvpView) {
        super(mMvpView);
    }
    
    @Override
    public void onViewInitialized(Intent intent) {
        
        List<Insect> insects = intent.getParcelableArrayListExtra(QuizActivity.EXTRA_INSECTS);
        Insect selected = getIntent().getParcelableExtra(QuizActivity.EXTRA_ANSWER);
        
        //Load answer strings
        ArrayList<String> options = new ArrayList<>();
        for (Insect item : insects) {
            options.add(item.scientificName);
        }
        
        String question = getString(R.string.question_text, selected.name);
        
        getMvpView().showQuestion(question, options, selected.getScientificName());
    }
}
