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

package com.prok.ivan.rssapp.di.components;


import com.prok.ivan.rssapp.di.ActivityScope;
import com.prok.ivan.rssapp.di.modules.MainActivityModule;
import com.prok.ivan.rssapp.view.DetailFragment;
import com.prok.ivan.rssapp.view.ListFragment;
import com.prok.ivan.rssapp.view.MainActivity;
import com.prok.ivan.rssapp.view.ShowFragment;

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

    void inject(ShowFragment showFragment);
}