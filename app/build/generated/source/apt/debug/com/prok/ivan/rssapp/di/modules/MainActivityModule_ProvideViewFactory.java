package com.prok.ivan.rssapp.di.modules;

import com.prok.ivan.rssapp.view.IMainActivityView;
import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class MainActivityModule_ProvideViewFactory implements Factory<IMainActivityView> {
  private final MainActivityModule module;

  public MainActivityModule_ProvideViewFactory(MainActivityModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public IMainActivityView get() {  
    IMainActivityView provided = module.provideView();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<IMainActivityView> create(MainActivityModule module) {  
    return new MainActivityModule_ProvideViewFactory(module);
  }
}

