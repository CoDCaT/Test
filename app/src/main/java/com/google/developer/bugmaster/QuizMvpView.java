package com.google.developer.bugmaster;

import com.google.developer.bugmaster.base.MvpView;

public interface QuizMvpView extends MvpView {
    void showQuestion(String question, ArrayList<String> options, String scientificName);
}
