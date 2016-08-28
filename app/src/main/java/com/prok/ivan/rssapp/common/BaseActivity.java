package com.prok.ivan.rssapp.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.prok.ivan.rssapp.app.RSSApp;
import com.prok.ivan.rssapp.di.components.IRSSAppComponent;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent(RSSApp.get(this).getAppComponent());
    }

    protected abstract void setupComponent(IRSSAppComponent appComponent);

}
