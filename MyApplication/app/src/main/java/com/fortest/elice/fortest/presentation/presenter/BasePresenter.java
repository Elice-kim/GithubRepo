package com.fortest.elice.fortest.presentation.presenter;

import rx.Subscription;


public class BasePresenter<VIEW> {

    protected Subscription subscription;

    public VIEW view;

    public void setView(VIEW view) {
        this.view = view;
    }

}
