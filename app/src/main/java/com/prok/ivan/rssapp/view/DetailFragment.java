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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.octo.android.robospice.SpiceManager;
import com.prok.ivan.rssapp.R;
import com.prok.ivan.rssapp.common.BaseFragment;
import com.prok.ivan.rssapp.di.components.IMainActivityComponent;
import com.prok.ivan.rssapp.network.ImageService;
import com.prok.ivan.rssapp.network.RSSService;
import com.prok.ivan.rssapp.presenter.DetailFragmentPresenterImpl;


import javax.inject.Inject;

public class DetailFragment extends BaseFragment implements IDetailFragmentView {

    @Inject
    DetailFragmentPresenterImpl presenter;

    protected SpiceManager spiceManager = new SpiceManager(RSSService.class);

    private static final String BUNDLE_IMAGE_URL = "bunleImgUrl";
    public static final String BUNDLE_ID = "bundleID";
    public static final String BUNDLE_LINK = "bundleImageUrl";
    public static final String BUNDLE_TITLE = "bundleTitle";

    private Activity activity;
    private String link;
    private String cardImageUrl;

    private ImageView imageView;
    private TextView nameTextView;
    private TextView descTextView;

    public DetailFragment() {
    }

    public static DetailFragment newInstance(String link, String cardImageUrl) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_LINK, link);
        bundle.putString(BUNDLE_IMAGE_URL, cardImageUrl);
        detailFragment.setArguments(bundle);
        return detailFragment;
    }

    // ----- Lifecycle override method

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            if (getArguments().containsKey(BUNDLE_LINK))
                this.link = getArguments().getString(BUNDLE_LINK);
             else if(getArguments().containsKey(BUNDLE_IMAGE_URL))
                 this.cardImageUrl = getArguments().getString(BUNDLE_IMAGE_URL);
        } else {
            throw new IllegalArgumentException("Must be created through newInstance(int id)");
        }
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
        presenter.onResume(spiceManager, link);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        imageView = (ImageView) view.findViewById(R.id.detailImageView);
        nameTextView = (TextView) view.findViewById(R.id.detailNameTextView);
        descTextView = (TextView) view.findViewById(R.id.detaliDescTextView);

    }

    @Override
    public void onPause() {
        presenter.onPause();
        super.onPause();
    }

    // -----  IDetailFragmentView implement method

    @Override
    public void updateViews(String url, String name, String desc) {
        String imageUrl;
        if (url != null)
            imageUrl = url;
        else
            imageUrl = cardImageUrl;
        ImageService.getInstance(activity).downloadImage(imageUrl, imageView);
//        Picasso.with(activity)
//                .load(imageUrl)
//                .placeholder(R.drawable.picasso_loading_animation)
//                .into(imageView);
        nameTextView.setText(name);
        descTextView.setText(desc);
    }

    @Override
    public void showProgressDialog() {
        ProgressBar progressBar = (ProgressBar) activity.findViewById(R.id.toolbar_progress_bar);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressDialog() {
        ProgressBar progressBar = (ProgressBar) activity.findViewById(R.id.toolbar_progress_bar);
        progressBar.setVisibility(View.INVISIBLE);
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
    public void replaceToShowFragment(String mediaURL) {
        FragmentManager fragmentManager = getFragmentManager();
        ShowFragment showFragment = ShowFragment.newInstance(mediaURL);
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, showFragment, ShowFragment.TAG)
                .addToBackStack(null)
                .commit();
    }
}
