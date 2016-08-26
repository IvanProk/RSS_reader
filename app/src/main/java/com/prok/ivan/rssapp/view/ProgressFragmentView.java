package com.prok.ivan.rssapp.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prok.ivan.rssapp.R;
import com.prok.ivan.rssapp.common.BaseFragment;

/**
 * Created by Ivan Prokofyev on 26.08.16.
 */
public class ProgressFragmentView extends View {
    private View view;
    private Activity activity;
    private ViewGroup.LayoutParams layoutParams;

    public ProgressFragmentView(Context context) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.progress_bar, null,  false);
        layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }


    public void showProgressView(Activity activity) {
        activity.addContentView(this, layoutParams);
    }


    public void hideProgressView() {
        this.setVisibility(View.INVISIBLE);
    }

}
