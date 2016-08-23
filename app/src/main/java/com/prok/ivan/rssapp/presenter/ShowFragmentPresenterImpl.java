package com.prok.ivan.rssapp.presenter;


import com.prok.ivan.rssapp.view.IShowFragmentView;

import javax.inject.Inject;

public class ShowFragmentPresenterImpl implements IShowFragmentPresenter {

    private IShowFragmentView view;

    @Inject
    public ShowFragmentPresenterImpl(){
    }

    @Override
    public void init(IShowFragmentView view) {
        this.view = view;
    }
}
