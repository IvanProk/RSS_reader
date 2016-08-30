
package com.prok.ivan.rssapp.view;

import android.app.FragmentManager;
import android.os.Bundle;

import com.prok.ivan.rssapp.R;
import com.prok.ivan.rssapp.common.BaseActivity;
import com.prok.ivan.rssapp.di.IHasComponent;
import com.prok.ivan.rssapp.di.components.DaggerIMainActivityComponent;
import com.prok.ivan.rssapp.di.components.IMainActivityComponent;
import com.prok.ivan.rssapp.di.components.IRSSAppComponent;
import com.prok.ivan.rssapp.di.modules.MainActivityModule;
import com.prok.ivan.rssapp.presenter.MainActivityPresenterImpl;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements IMainActivityView, IHasComponent<IMainActivityComponent> {

    @Inject
    MainActivityPresenterImpl presenter;

    private IMainActivityComponent mainActivityComponent;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getFragmentManager();
        ListFragment listFragment = (ListFragment)fragmentManager.findFragmentByTag("ListFragment");
        if (listFragment == null){
            listFragment = new ListFragment();
        }
        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, listFragment)
                    .commit();
        }
    }

    @Override
    public void onBackPressed(){
        if (fragmentManager.getBackStackEntryCount() > 0) {
            presenter.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }
    @Override
    protected void setupComponent(IRSSAppComponent appComponent) {
        mainActivityComponent = DaggerIMainActivityComponent.builder()
                .iRSSAppComponent(appComponent)
                .mainActivityModule(new MainActivityModule(this))
                .build();
        mainActivityComponent.inject(this);
    }

    @Override
    public IMainActivityComponent getComponent() {
        return mainActivityComponent;
    }

    // -----  IMainActivityView implement method

    @Override
    public void popFragmentFromStack() {
        fragmentManager.popBackStack();
    }
}
