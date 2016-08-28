package com.prok.ivan.rssapp.di.components;

import com.prok.ivan.rssapp.di.modules.MainActivityModule;
import com.prok.ivan.rssapp.di.modules.MainActivityModule_ProvideDetailFragmentPresenterImplFactory;
import com.prok.ivan.rssapp.di.modules.MainActivityModule_ProvideListFragmentPresenterImplFactory;
import com.prok.ivan.rssapp.di.modules.MainActivityModule_ProvideMainActivityPresenterImplFactory;
import com.prok.ivan.rssapp.di.modules.MainActivityModule_ProvideViewFactory;
import com.prok.ivan.rssapp.presenter.DetailFragmentPresenterImpl;
import com.prok.ivan.rssapp.presenter.ListFragmentPresenterImpl;
import com.prok.ivan.rssapp.presenter.MainActivityPresenterImpl;
import com.prok.ivan.rssapp.view.DetailFragment;
import com.prok.ivan.rssapp.view.DetailFragment_MembersInjector;
import com.prok.ivan.rssapp.view.IMainActivityView;
import com.prok.ivan.rssapp.view.ListFragment;
import com.prok.ivan.rssapp.view.ListFragment_MembersInjector;
import com.prok.ivan.rssapp.view.MainActivity;
import com.prok.ivan.rssapp.view.MainActivity_MembersInjector;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class DaggerIMainActivityComponent implements IMainActivityComponent {
  private Provider<IMainActivityView> provideViewProvider;
  private Provider<MainActivityPresenterImpl> provideMainActivityPresenterImplProvider;
  private MembersInjector<MainActivity> mainActivityMembersInjector;
  private Provider<ListFragmentPresenterImpl> provideListFragmentPresenterImplProvider;
  private MembersInjector<ListFragment> listFragmentMembersInjector;
  private Provider<DetailFragmentPresenterImpl> provideDetailFragmentPresenterImplProvider;
  private MembersInjector<DetailFragment> detailFragmentMembersInjector;

  private DaggerIMainActivityComponent(Builder builder) {  
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {  
    return new Builder();
  }

  private void initialize(final Builder builder) {  
    this.provideViewProvider = MainActivityModule_ProvideViewFactory.create(builder.mainActivityModule);
    this.provideMainActivityPresenterImplProvider = MainActivityModule_ProvideMainActivityPresenterImplFactory.create(builder.mainActivityModule, provideViewProvider);
    this.mainActivityMembersInjector = MainActivity_MembersInjector.create((MembersInjector) MembersInjectors.noOp(), provideMainActivityPresenterImplProvider);
    this.provideListFragmentPresenterImplProvider = MainActivityModule_ProvideListFragmentPresenterImplFactory.create(builder.mainActivityModule);
    this.listFragmentMembersInjector = ListFragment_MembersInjector.create((MembersInjector) MembersInjectors.noOp(), provideListFragmentPresenterImplProvider);
    this.provideDetailFragmentPresenterImplProvider = MainActivityModule_ProvideDetailFragmentPresenterImplFactory.create(builder.mainActivityModule);
    this.detailFragmentMembersInjector = DetailFragment_MembersInjector.create((MembersInjector) MembersInjectors.noOp(), provideDetailFragmentPresenterImplProvider);
  }

  @Override
  public void inject(MainActivity activity) {  
    mainActivityMembersInjector.injectMembers(activity);
  }

  @Override
  public void inject(ListFragment talksListFragment) {  
    listFragmentMembersInjector.injectMembers(talksListFragment);
  }

  @Override
  public void inject(DetailFragment talkDetailFragment) {  
    detailFragmentMembersInjector.injectMembers(talkDetailFragment);
  }

  public static final class Builder {
    private MainActivityModule mainActivityModule;
    private IRSSAppComponent iRSSAppComponent;
  
    private Builder() {  
    }
  
    public IMainActivityComponent build() {  
      if (mainActivityModule == null) {
        throw new IllegalStateException("mainActivityModule must be set");
      }
      if (iRSSAppComponent == null) {
        throw new IllegalStateException("iRSSAppComponent must be set");
      }
      return new DaggerIMainActivityComponent(this);
    }
  
    public Builder mainActivityModule(MainActivityModule mainActivityModule) {  
      if (mainActivityModule == null) {
        throw new NullPointerException("mainActivityModule");
      }
      this.mainActivityModule = mainActivityModule;
      return this;
    }
  
    public Builder iRSSAppComponent(IRSSAppComponent iRSSAppComponent) {  
      if (iRSSAppComponent == null) {
        throw new NullPointerException("iRSSAppComponent");
      }
      this.iRSSAppComponent = iRSSAppComponent;
      return this;
    }
  }
}

