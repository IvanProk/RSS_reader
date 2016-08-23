/*
 * Copyright (2015) Alexey Mitutov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
