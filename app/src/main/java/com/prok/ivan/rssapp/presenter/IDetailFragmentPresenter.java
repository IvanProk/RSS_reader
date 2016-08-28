package com.prok.ivan.rssapp.presenter;

import com.octo.android.robospice.SpiceManager;
import com.prok.ivan.rssapp.common.BaseFragmentPresenter;
import com.prok.ivan.rssapp.view.IDetailFragmentView;


public interface IDetailFragmentPresenter extends BaseFragmentPresenter<IDetailFragmentView> {
    void onResume(SpiceManager spiceManager, String link, String spareImageUrl);
    void onPause();
    void backButtonOnClick();
}
