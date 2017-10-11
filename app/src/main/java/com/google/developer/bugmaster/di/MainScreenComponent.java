package com.google.developer.bugmaster.di;


import com.google.developer.bugmaster.features.main_screen.MainActivityPresenter;
import com.google.developer.bugmaster.features.main_screen.MainMvpView;

public interface MainScreenComponent {

    void inject(MainActivityPresenter<MainMvpView> presenter);

}
