package com.prok.ivan.rssapp.di.modules;

import android.app.Application;
import dagger.internal.Factory;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class RSSAppModule_ProvideApplicationFactory implements Factory<Application> {
  private final RSSAppModule module;

  public RSSAppModule_ProvideApplicationFactory(RSSAppModule module) {  
    assert module != null;
    this.module = module;
  }

  @Override
  public Application get() {  
    Application provided = module.provideApplication();
    if (provided == null) {
      throw new NullPointerException("Cannot return null from a non-@Nullable @Provides method");
    }
    return provided;
  }

  public static Factory<Application> create(RSSAppModule module) {  
    return new RSSAppModule_ProvideApplicationFactory(module);
  }
}

