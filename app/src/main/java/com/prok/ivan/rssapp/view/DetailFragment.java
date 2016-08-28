package com.prok.ivan.rssapp.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
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

    private static final String BUNDLE_IMAGE_URL = "bundleImgUrl";
    public static final String BUNDLE_LINK = "bundleImageUrl";

    private Activity activity;
    private String link;
    private String cardImageUrl;

    private ImageView imageView;
    private TextView nameTextView;
    private TextView descTextView;
    private ImageButton btnBack;
    private Button btnToSite;
    private ScrollView scrollView;

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
            if (getArguments().containsKey(BUNDLE_IMAGE_URL))
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
        presenter.onResume(spiceManager, link, cardImageUrl);
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
        btnBack = (ImageButton) view.findViewById(R.id.btnBack);
        btnToSite = (Button) view.findViewById(R.id.btnToSite);
        scrollView = (ScrollView) view.findViewById(R.id.scrollView);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("RSS", "onClick: ");
                presenter.backButtonOnClick();
            }
        });

        btnToSite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.toSiteButtonOnClick();
            }
        });
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
        nameTextView.setText(name);
        descTextView.setText(desc);
    }

    @Override
    public void showProgressDialog() {
        ProgressBar progressBar = (ProgressBar) activity.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        scrollView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgressDialog() {
        ProgressBar progressBar = (ProgressBar) activity.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);
        scrollView.setVisibility(View.VISIBLE);
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
    public void close() {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.popBackStack();
    }

    @Override
    public void goToSite() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(this.link));
        activity.startActivity(intent);
    }


}
