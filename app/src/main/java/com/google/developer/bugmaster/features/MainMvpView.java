package com.google.developer.bugmaster.features;


import com.google.developer.bugmaster.base.MvpView;
import com.google.developer.bugmaster.data.Insect;

import java.util.List;

public interface MainMvpView extends MvpView {

    void showInsects(List<Insect> allInsect);

}
