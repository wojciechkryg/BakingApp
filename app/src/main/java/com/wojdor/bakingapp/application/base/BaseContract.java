package com.wojdor.bakingapp.application.base;

public interface BaseContract {

    interface View {

        void setupPresenter();
    }

    interface Presenter {

        void onAttachView();

        void onDetachView();
    }
}
