package com.prok.ivan.rssapp.di.components;

import com.prok.ivan.rssapp.app.RSSApp;
import com.prok.ivan.rssapp.di.modules.RSSAppModule;


import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                RSSAppModule.class
        }
)
public interface IRSSAppComponent {
    void inject(RSSApp app);
}
