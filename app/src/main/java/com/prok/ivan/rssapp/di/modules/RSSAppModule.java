package com.prok.ivan.rssapp.di.modules;

import android.app.Application;

import com.prok.ivan.rssapp.app.RSSApp;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RSSAppModule {

    private final RSSApp app;

    public RSSAppModule(RSSApp app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public Application provideApplication() {
        return app;
    }
}
