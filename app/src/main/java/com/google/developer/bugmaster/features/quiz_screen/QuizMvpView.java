package com.google.developer.bugmaster.features.quiz_screen;

import com.google.developer.bugmaster.base.MvpView;

import java.util.ArrayList;

public interface QuizMvpView extends MvpView {
    void showQuestion(String question, ArrayList<String> options, String scientificName);

    void closeScreen();
}
