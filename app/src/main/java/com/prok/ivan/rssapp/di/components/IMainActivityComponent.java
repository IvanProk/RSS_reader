package com.prok.ivan.rssapp.di.components;


import com.prok.ivan.rssapp.di.ActivityScope;
import com.prok.ivan.rssapp.di.modules.MainActivityModule;
import com.prok.ivan.rssapp.view.DetailFragment;
import com.prok.ivan.rssapp.view.ListFragment;
import com.prok.ivan.rssapp.view.MainActivity;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = IRSSAppComponent.class,
        modules = MainActivityModule.class
)
public interface IMainActivityComponent {
    void inject(MainActivity activity);

    void inject(ListFragment talksListFragment);

    void inject(DetailFragment talkDetailFragment);
}