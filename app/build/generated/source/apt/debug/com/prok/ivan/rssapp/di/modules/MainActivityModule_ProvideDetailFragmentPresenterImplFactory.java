package com.prok.ivan.rssapp.di.modules;

import com.prok.ivan.rssapp.presenter.DetailFragmentPresenterImpl;
import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class MainActivityModule_ProvideDetailFragmentPresenterImplFactory implements Factory<DetailFragmentPresenterImpl> {
  private final MainActivityModule module;

  public MainActivityModule_ProvideDetailFragmentPresenterImplFactory(MainActivityModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public DetailFragmentPresenterImpl get() {  
    DetailFragmentPresenterImpl provided = module.provideDetailFragmentPresenterImpl();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<DetailFragmentPresenterImpl> create(MainActivityModule module) {  
    return new MainActivityModule_ProvideDetailFragmentPresenterImplFactory(module);
  }
}

