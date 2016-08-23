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

package com.prok.ivan.rssapp.di.modules;


import com.prok.ivan.rssapp.presenter.DetailFragmentPresenterImpl;
import com.prok.ivan.rssapp.presenter.ListFragmentPresenterImpl;
import com.prok.ivan.rssapp.presenter.MainActivityPresenterImpl;
import com.prok.ivan.rssapp.presenter.ShowFragmentPresenterImpl;
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

    @Provides
    public ShowFragmentPresenterImpl provideShowFragmentPresenterImpl(){
        return new ShowFragmentPresenterImpl();
    }
}
