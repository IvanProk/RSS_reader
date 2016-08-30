package com.prok.ivan.rssapp.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.octo.android.robospice.SpiceManager;
import com.prok.ivan.rssapp.R;
import com.prok.ivan.rssapp.common.BaseFragment;
import com.prok.ivan.rssapp.common.NewsListAdapter;
import com.prok.ivan.rssapp.di.components.IMainActivityComponent;
import com.prok.ivan.rssapp.model.ItemNews;
import com.prok.ivan.rssapp.network.NetworkHelper;
import com.prok.ivan.rssapp.network.RSSService;
import com.prok.ivan.rssapp.presenter.ListFragmentPresenterImpl;


import java.util.List;

import javax.inject.Inject;

public class ListFragment extends BaseFragment implements IListFragmentView {

    @Inject
    ListFragmentPresenterImpl presenter;

    protected SpiceManager spiceManager = new SpiceManager(RSSService.class);

    private Activity activity;
    private ListView listView;
    private NewsListAdapter newsListAdapter;
    private View rootView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RelativeLayout errorMessage;

    public ListFragment() {
    }

    // ----- Lifecycle override method

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.getComponent(IMainActivityComponent.class).inject(this);
    }

    @Override
    public void onResume() {
        presenter.init(this);
        presenter.onResume(spiceManager);
        super.onResume();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.fragment_list, container, false);
            listView = (ListView) rootView.findViewById(R.id.talkListView);
            listView.setDividerHeight(0);

            errorMessage = (RelativeLayout) rootView.findViewById(R.id.errorMessage);
        }
        return rootView;
    }

    @Override
    public void onDestroyView() {
        if (rootView.getParent() != null) {
            ((ViewGroup) rootView.getParent()).removeView(rootView);
        }
        super.onDestroyView();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeToRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (NetworkHelper.getNetworkState(activity))
                    presenter.refresh(swipeRefreshLayout);
                else {
                    onNoConnection();
                    setRefreshing(false);
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (NetworkHelper.getNetworkState(activity))
                    presenter.onItemClick((ItemNews) listView.getAdapter().getItem(position));
                else
                    onNoConnection();
            }
        });
    }

    @Override
    public void onPause() {
        presenter.onPause();
        super.onPause();
    }

    // -----  IListFragmentView implement method

    @Override
    public void setNewsListAdapter(List<ItemNews> itemNewsList, int totalNews) {
        errorMessage.setVisibility(View.GONE);
        if (newsListAdapter == null) {
            newsListAdapter = new NewsListAdapter(activity, itemNewsList, totalNews);
            listView.setAdapter(newsListAdapter);
            newsListAdapter.setFooterButtonOnClick(new Runnable() {
                @Override
                public void run() {
                    listView.setSelection(0);
                }
            });

        } else {
            presenter.addListToAdapter(itemNewsList);
        }
    }

    @Override
    public void addListToAdapter(List<ItemNews> itemTalkList) {
        newsListAdapter.add(itemTalkList);
    }

    @Override
    public void showProgressDialog() {
        errorMessage.setVisibility(View.GONE);
        ProgressBar progressBar = (ProgressBar) activity.findViewById(R.id.mainActivityProgressBar);
        listView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressDialog() {

        listView.setVisibility(View.VISIBLE);
        ProgressBar progressBar = (ProgressBar) activity.findViewById(R.id.mainActivityProgressBar);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void replaceToDetailFragment(String url, String cardImageUrl) {
        FragmentManager fragmentManager = activity.getFragmentManager();
        DetailFragment detailFragment = DetailFragment.newInstance(url, cardImageUrl);
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, detailFragment)
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void startService() {
        if (!spiceManager.isStarted()) {
            spiceManager.start(activity);
        }
    }

    @Override
    public void stopService() {
        if (spiceManager.isStarted()) {
            spiceManager.shouldStop();
        }
    }

    @Override
    public void setRefreshing(boolean refresh) {
        swipeRefreshLayout.setRefreshing(refresh);
    }

    @Override
    public void onRequestFailure() {
        if (NetworkHelper.getNetworkState(activity))
            Toast.makeText(activity, "Ошибка сети", Toast.LENGTH_SHORT).show();
        else
            onNoConnection();
    }

    @Override
    public void onNoConnection() {
        errorMessage.setVisibility(View.VISIBLE);
        Toast.makeText(activity, "Необходимо подключение к интернету", Toast.LENGTH_SHORT).show();
    }


}
