package com.google.developer.bugmaster.features.details_insect;

import com.google.developer.bugmaster.di.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class DetailScreenModule {

    @Provides
    @ActivityScope
    DetailsMvpView provideDetailsMvpView (InsectDetailsActivity insectDetailsActivity){
        return insectDetailsActivity;
    }

    @Provides
    @ActivityScope
    DetailsActivityPresenter<DetailsMvpView> provideDetailsActivityPresenter(DetailsMvpView detailsMvpView){
        return new DetailsActivityPresenter<>(detailsMvpView);
    }

}
