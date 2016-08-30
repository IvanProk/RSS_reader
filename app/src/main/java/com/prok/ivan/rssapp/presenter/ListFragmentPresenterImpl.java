package com.prok.ivan.rssapp.presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.widget.SwipeRefreshLayout;

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
        sendRequest(RSS_URL, spiceManager, false);
    }

    @Override
    public void onPause() {
        view.stopService();
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

    @Override
    public void refresh(SwipeRefreshLayout swipeRefreshLayout) {
        view.setRefreshing(true);
        sendRequest(RSS_URL, spiceManager, false);
    }



    private void sendRequest(String url, SpiceManager spiceManager, boolean isLoadMore) {
        SimpleTextRequest request = new SimpleTextRequest(url);
        if (!isLoadMore) {
            view.showProgressDialog();
        }
        spiceManager.execute(request, "xmlNews", DurationInMillis.ONE_HOUR, new RSSNewsRequestListener());

    }

    private final class RSSNewsRequestListener implements RequestListener<String> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            view.setRefreshing(false);
            view.hideProgressDialog();
            view.onRequestFailure();
        }

        @Override
        public void onRequestSuccess(String result) {
            view.setRefreshing(false);
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
    }
}
