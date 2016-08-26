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

package com.prok.ivan.rssapp.presenter;

import android.util.Log;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.octo.android.robospice.request.simple.SimpleTextRequest;
import com.prok.ivan.rssapp.view.IDetailFragmentView;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import javax.inject.Inject;

public class DetailFragmentPresenterImpl implements IDetailFragmentPresenter {

    private IDetailFragmentView view;
    private String mediaURL;


    @Inject
    public DetailFragmentPresenterImpl() {
    }

    @Override
    public void init(IDetailFragmentView view) {
        this.view = view;
    }

    @Override
    public void onResume(SpiceManager spiceManager, String link) {
        view.startService();
        sendRequest(link, spiceManager);
    }

    @Override
    public void onPause() {
        view.stopService();
    }

    @Override
    public void backButtonOnClick() {
        Log.d("RSS", "backButtonOnClick: ");view.close();}


    private void sendRequest(String url, SpiceManager spiceManager) {
        SimpleTextRequest request = new SimpleTextRequest(url);
        spiceManager.execute(request, "htmlNews" + url, DurationInMillis.ONE_DAY, new RSSFeedRequestListener());
        view.showProgressDialog();
    }


    // ----- Inner Class for RequestListener

    private final class RSSFeedRequestListener implements RequestListener<String> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            view.hideProgressDialog();
        }

        @Override
        public void onRequestSuccess(String result) {
            String imageUrl;
            String name;
            String desc = "";

            view.hideProgressDialog();
            Log.d("RSS", "onRequestSuccess: result is: " + result);
            Document document = Jsoup.parse(result);
            imageUrl = document.body().getElementsByClass("g-picture").attr("src");
            name = document.body().getElementsByTag("h1").first().text();
            Elements topic = document.body().getElementsByClass("b-text");
            for (Element element : topic.select("p"))
                desc += element.text() + "\n\n";
            view.hideProgressDialog();
            Log.d("RSS", "onRequestSuccess: imgUrl -- " + imageUrl +
                    "\n name -- " + name +
                    "\n description -- " + desc);
            view.updateViews(imageUrl, name, desc);
        }
    }
}

