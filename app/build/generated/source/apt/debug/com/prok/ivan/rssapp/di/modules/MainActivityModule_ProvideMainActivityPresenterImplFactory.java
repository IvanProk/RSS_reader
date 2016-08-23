package com.prok.ivan.rssapp.di.modules;

import com.prok.ivan.rssapp.presenter.MainActivityPresenterImpl;
import com.prok.ivan.rssapp.view.IMainActivityView;
import dagger.internal.Factory;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class MainActivityModule_ProvideMainActivityPresenterImplFactory implements Factory<MainActivityPresenterImpl> {
  private final MainActivityModule module;
  private final Provider<IMainActivityView> viewProvider;

  public MainActivityModule_ProvideMainActivityPresenterImplFactory(MainActivityModule module, Provider<IMainActivityView> viewProvider) {  
    assert module != null;
    this.module = module;
    assert viewProvider != null;
    this.viewProvider = viewProvider;
  }

  @Override
  public MainActivityPresenterImpl get() {  
    MainActivityPresenterImpl provided = module.provideMainActivityPresenterImpl(viewProvider.get());
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<MainActivityPresenterImpl> create(MainActivityModule module, Provider<IMainActivityView> viewProvider) {  
    return new MainActivityModule_ProvideMainActivityPresenterImplFactory(module, viewProvider);
  }
}

