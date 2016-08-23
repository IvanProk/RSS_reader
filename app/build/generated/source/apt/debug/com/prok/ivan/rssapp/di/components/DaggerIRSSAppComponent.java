package com.prok.ivan.rssapp.di.components;

import com.prok.ivan.rssapp.app.RSSApp;
import com.prok.ivan.rssapp.di.modules.RSSAppModule;
import dagger.internal.MembersInjectors;
import javax.annotation.Generated;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class DaggerIRSSAppComponent implements IRSSAppComponent {
  private DaggerIRSSAppComponent(Builder builder) {  
    assert builder != null;
    initialize(builder);
  }

  public static Builder builder() {  
    return new Builder();
  }

  private void initialize(final Builder builder) {  
  }

  @Override
  public void inject(RSSApp app) {  
    MembersInjectors.noOp().injectMembers(app);
  }

  public static final class Builder {
    private RSSAppModule rSSAppModule;
  
    private Builder() {  
    }
  
    public IRSSAppComponent build() {  
      if (rSSAppModule == null) {
        throw new IllegalStateException("rSSAppModule must be set");
      }
      return new DaggerIRSSAppComponent(this);
    }
  
    public Builder rSSAppModule(RSSAppModule rSSAppModule) {  
      if (rSSAppModule == null) {
        throw new NullPointerException("rSSAppModule");
      }
      this.rSSAppModule = rSSAppModule;
      return this;
    }
  }
}

