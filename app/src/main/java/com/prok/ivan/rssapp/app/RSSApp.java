package com.prok.ivan.rssapp.app;

import android.app.Application;
import android.content.Context;

import com.prok.ivan.rssapp.di.components.DaggerIRSSAppComponent;
import com.prok.ivan.rssapp.di.components.IRSSAppComponent;
import com.prok.ivan.rssapp.di.modules.RSSAppModule;

public class RSSApp extends Application {

    private IRSSAppComponent appComponent;

    public static RSSApp get(Context context) {
        return (RSSApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        buildGraphAndInject();
    }

    public IRSSAppComponent getAppComponent() {
        return appComponent;
    }

    public void buildGraphAndInject() {
        appComponent = DaggerIRSSAppComponent.builder()
                .rSSAppModule(new RSSAppModule(this))
                .build();
        appComponent.inject(this);
    }
}
