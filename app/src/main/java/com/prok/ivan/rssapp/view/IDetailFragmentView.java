package com.prok.ivan.rssapp.view;

public interface IDetailFragmentView {
    void updateViews(String url, String name, String desc);
    void showProgressDialog();
    void hideProgressDialog();
    void startService();
    void stopService();
    void close();
    void goToSite();
}
