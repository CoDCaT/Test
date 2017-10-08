package com.google.developer.bugmaster.features.main_screen;


import com.google.developer.bugmaster.base.MvpView;
import com.google.developer.bugmaster.data.Insect;

import java.util.ArrayList;
import java.util.List;

public interface MainMvpView extends MvpView {

    void showInsects(List<Insect> allInsect);

    void navigateToQuiz(ArrayList<Insect> insectArrayList, Insect answerInsect);

    void navigateToSettings();
}
