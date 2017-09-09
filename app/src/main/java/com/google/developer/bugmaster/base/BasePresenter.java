package com.google.developer.bugmaster.base;


public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private V mMvpView;

    public BasePresenter(V mMvpView) {
        this.mMvpView = mMvpView;
    }

    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void onDetach() {
        mMvpView = null;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public V getMvpView() {
        checkViewAttached();
        return mMvpView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.onAttach(MvpView) before" + " requesting data to the Presenter");
        }
    }
}
