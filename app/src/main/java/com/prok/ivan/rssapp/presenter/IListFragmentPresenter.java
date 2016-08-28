package com.prok.ivan.rssapp.presenter;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import com.octo.android.robospice.SpiceManager;
import com.prok.ivan.rssapp.common.BaseFragmentPresenter;
import com.prok.ivan.rssapp.model.ItemNews;
import com.prok.ivan.rssapp.view.IListFragmentView;
import java.util.List;

public interface IListFragmentPresenter extends BaseFragmentPresenter<IListFragmentView> {
    void onResume(SpiceManager spiceManager);
    void onPause();
    void onLoadMore();
    void onItemClick(ItemNews itemNews);
    void addListToAdapter(List<ItemNews> ItemNewsList);
    void refresh(SwipeRefreshLayout swipeRefreshLayout);
    boolean getNetworkState(Context context);
}
