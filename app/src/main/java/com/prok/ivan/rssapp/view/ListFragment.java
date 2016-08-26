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

package com.prok.ivan.rssapp.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.octo.android.robospice.SpiceManager;
import com.prok.ivan.rssapp.R;
import com.prok.ivan.rssapp.common.BaseFragment;
import com.prok.ivan.rssapp.common.EndlessScrollListener;
import com.prok.ivan.rssapp.common.NewsListAdapter;
import com.prok.ivan.rssapp.di.components.IMainActivityComponent;
import com.prok.ivan.rssapp.model.ItemNews;
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
        super.onResume();
        presenter.init(this);
        presenter.onResume(spiceManager);

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
        listView.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public void onLoadMore() {
                presenter.onLoadMore();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {//TODO
//                showDescriptionOfSelectedItem(view);
                presenter.onItemClick((ItemNews) listView.getAdapter().getItem(position));
                Log.d("RSS", "onItemClick: position: " + position + " id: " + id);
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
    public void showDescriptionOfSelectedItem(View item) {
        TextView tvDescription = (TextView)item.findViewById(R.id.news_description);
        if (tvDescription.getVisibility() == View.GONE)
            tvDescription.setVisibility(View.VISIBLE);
        else
            tvDescription.setVisibility(View.GONE);
    }

    @Override
    public void setNewsListAdapter(List<ItemNews> itemNewsList, int totalNews) {
        if (newsListAdapter == null) {
            newsListAdapter = new NewsListAdapter(activity, itemNewsList, totalNews);
            listView.setAdapter(newsListAdapter);
            newsListAdapter.setFooterButtonOnClick(new Runnable() {
                @Override
                public void run() {
                    listView.scrollTo(0,0);
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
//        ProgressBar progressBar = (ProgressBar) activity.findViewById(R.id.progress_bar);
//        progressBar.setVisibility(View.VISIBLE);
        ProgressFragmentView progressView = new ProgressFragmentView(activity);
        progressView.showProgressView(activity);
    }

    @Override
    public void hideProgressDialog() {
//        ProgressBar progressBar = (ProgressBar) activity.findViewById(R.id.progress_bar);
//        progressBar.setVisibility(View.INVISIBLE);
        ProgressFragmentView progressView = new ProgressFragmentView(activity);
        progressView.hideProgressView();
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
}
