package com.prok.ivan.rssapp.di.modules;

import com.prok.ivan.rssapp.presenter.ListFragmentPresenterImpl;
import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class MainActivityModule_ProvideListFragmentPresenterImplFactory implements Factory<ListFragmentPresenterImpl> {
  private final MainActivityModule module;

  public MainActivityModule_ProvideListFragmentPresenterImplFactory(MainActivityModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public ListFragmentPresenterImpl get() {  
    ListFragmentPresenterImpl provided = module.provideListFragmentPresenterImpl();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<ListFragmentPresenterImpl> create(MainActivityModule module) {  
    return new MainActivityModule_ProvideListFragmentPresenterImplFactory(module);
  }
}

