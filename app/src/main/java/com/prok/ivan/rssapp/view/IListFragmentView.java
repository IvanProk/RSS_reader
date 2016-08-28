package com.prok.ivan.rssapp.view;

import com.prok.ivan.rssapp.model.ItemNews;
import java.util.List;

public interface IListFragmentView {
    void setNewsListAdapter(List<ItemNews> itemNewsList, int totalNews);
    void addListToAdapter(List<ItemNews> itemNewsList);
    void showProgressDialog();
    void hideProgressDialog();
    void replaceToDetailFragment(String url, String cardImageUrl);
    void startService();
    void stopService();
    void setRefreshing(boolean refresh);
    void onRequestFailure();
    void onNoConnection();
}
