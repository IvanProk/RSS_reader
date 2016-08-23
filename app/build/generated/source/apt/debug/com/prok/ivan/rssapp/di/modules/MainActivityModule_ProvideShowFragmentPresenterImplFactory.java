package com.prok.ivan.rssapp.di.modules;

import com.prok.ivan.rssapp.presenter.ShowFragmentPresenterImpl;
import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class MainActivityModule_ProvideShowFragmentPresenterImplFactory implements Factory<ShowFragmentPresenterImpl> {
  private final MainActivityModule module;

  public MainActivityModule_ProvideShowFragmentPresenterImplFactory(MainActivityModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public ShowFragmentPresenterImpl get() {  
    ShowFragmentPresenterImpl provided = module.provideShowFragmentPresenterImpl();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<ShowFragmentPresenterImpl> create(MainActivityModule module) {  
    return new MainActivityModule_ProvideShowFragmentPresenterImplFactory(module);
  }
}

