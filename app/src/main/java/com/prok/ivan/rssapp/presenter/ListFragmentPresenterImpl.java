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

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.octo.android.robospice.request.simple.SimpleTextRequest;
import com.prok.ivan.rssapp.model.ItemNews;
import com.prok.ivan.rssapp.model.saxrssreader.RssFeed;
import com.prok.ivan.rssapp.model.saxrssreader.RssReader;
import com.prok.ivan.rssapp.view.IListFragmentView;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ListFragmentPresenterImpl implements IListFragmentPresenter {

    int offset = 0;

    private static final String RSS_URL = "https://lenta.ru/rss";

    private IListFragmentView view;
    int totalNews;
    private SpiceManager spiceManager;

    @Inject
    public ListFragmentPresenterImpl() {
    }

    @Override
    public void init(IListFragmentView view) {
        this.view = view;
    }

    @Override
    public void onResume(SpiceManager spiceManager) {
        view.startService();
        this.spiceManager = spiceManager;
//        String url = RSS_URL + "&offset=" + String.valueOf(offset);
        String url = RSS_URL;
        sendRequest(url, spiceManager, false);
    }

    @Override
    public void onPause() {
        view.stopService();
    }

    @Override
    public void onLoadMore() {
//        // формируем новый URL в зависисмости от смещения
//        if (offset <= totalNews-30) {
//            offset+=30;
////            String url = RSS_URL + "&offset=" + String.valueOf(offset);
//            String url = RSS_URL;
//            sendRequest(url, spiceManager, true);
//
//        }
    }

    @Override
    public void onItemClick(ItemNews itemNews) {
        String url = itemNews.getLink();
        String cardImageUrl = itemNews.getEnclosureUrl();
        view.replaceToDetailFragment(url, cardImageUrl);
    }

    @Override
    public void addListToAdapter(List<ItemNews> itemNewsList) {
        view.addListToAdapter(itemNewsList);
    }


    private void sendRequest(String url, SpiceManager spiceManager, boolean isLoadMore) {
        SimpleTextRequest request = new SimpleTextRequest(url);
        // если это не догрузка в лист, то показываем основной прогрессбар в тулбаре
        if (!isLoadMore) {
            view.showProgressDialog();
        }
        spiceManager.execute(request, "xmlNews", DurationInMillis.ONE_HOUR, new RSSNewsRequestListener());

    }

    private final class RSSNewsRequestListener implements RequestListener<String> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            view.hideProgressDialog();
        }

        @Override
        public void onRequestSuccess(String result) {
            view.hideProgressDialog();
            RssFeed feed;
            ArrayList<ItemNews> itemNewsList = null;
            try {
                feed = RssReader.read(result);
                itemNewsList = feed.getItemNewses();
            } catch (SAXException | IOException e) {
                e.printStackTrace();
            }

            totalNews = itemNewsList.size();
            view.setNewsListAdapter((List) itemNewsList, totalNews);

        }


//        private List<ItemNews> getListFronJson(String jsonString) {
//            List<ItemNews> itemTalkList = new ArrayList<>();
//            try {
//                JSONObject jsonObject = new JSONObject(jsonString);
//                JSONArray talks = jsonObject.getJSONArray(Tags.TAG_TALKS);
//                for (int i = 0; i < talks.length(); i++) {
//                    JSONObject jsonTalkItem = talks.getJSONObject(i);
//                    JSONObject jsonTalk = jsonTalkItem.getJSONObject(Tags.TAG_TALK);
//                    int id = jsonTalk.getInt(Tags.TAG_ID);
//                    String name = jsonTalk.getString(Tags.TAG_NAME);
//                    String desc = jsonTalk.getString(Tags.TAG_DESC);
//                    String published = jsonTalk.getString(Tags.TAG_PUBLISHED);
//                    ItemNews itemTalk = new ItemNews();
////                    itemTalk.setId(id);
////                    itemTalk.setName(name);
////                    itemTalk.setDescription(desc);
////                    itemTalk.setPublished(published);
//                    itemTalkList.add(itemTalk);
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            return itemTalkList;
//        }
    }
}
