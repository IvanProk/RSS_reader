package com.prok.ivan.rssapp.presenter;

import com.prok.ivan.rssapp.view.IMainActivityView;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class MainActivityPresenterImpl_Factory implements Factory<MainActivityPresenterImpl> {
  private final Provider<IMainActivityView> viewProvider;

  public MainActivityPresenterImpl_Factory(Provider<IMainActivityView> viewProvider) {  
    assert viewProvider != null;
    this.viewProvider = viewProvider;
  }

  @Override
  public MainActivityPresenterImpl get() {  
    return new MainActivityPresenterImpl(viewProvider.get());
  }

  public static Factory<MainActivityPresenterImpl> create(Provider<IMainActivityView> viewProvider) {  
    return new MainActivityPresenterImpl_Factory(viewProvider);
  }
}

