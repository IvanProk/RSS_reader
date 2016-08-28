package com.prok.ivan.rssapp.di.modules;


import com.prok.ivan.rssapp.presenter.DetailFragmentPresenterImpl;
import com.prok.ivan.rssapp.presenter.ListFragmentPresenterImpl;
import com.prok.ivan.rssapp.presenter.MainActivityPresenterImpl;
import com.prok.ivan.rssapp.view.IMainActivityView;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    private IMainActivityView view;

    public MainActivityModule(IMainActivityView view) {
        this.view = view;
    }

    @Provides
    public IMainActivityView provideView() {
        return view;
    }

    @Provides
    public MainActivityPresenterImpl provideMainActivityPresenterImpl (IMainActivityView view){
        return  new MainActivityPresenterImpl(view);
    }

    @Provides
    public ListFragmentPresenterImpl provideListFragmentPresenterImpl() {
        return new ListFragmentPresenterImpl();
    }

    @Provides
    public DetailFragmentPresenterImpl provideDetailFragmentPresenterImpl() {
        return new DetailFragmentPresenterImpl();
    }
}
