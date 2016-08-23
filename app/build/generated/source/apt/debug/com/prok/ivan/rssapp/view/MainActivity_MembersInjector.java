package com.prok.ivan.rssapp.view;

import com.prok.ivan.rssapp.common.BaseActivity;
import com.prok.ivan.rssapp.presenter.MainActivityPresenterImpl;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class MainActivity_MembersInjector implements MembersInjector<MainActivity> {
  private final MembersInjector<BaseActivity> supertypeInjector;
  private final Provider<MainActivityPresenterImpl> presenterProvider;

  public MainActivity_MembersInjector(MembersInjector<BaseActivity> supertypeInjector, Provider<MainActivityPresenterImpl> presenterProvider) {  
    assert supertypeInjector != null;
    this.supertypeInjector = supertypeInjector;
    assert presenterProvider != null;
    this.presenterProvider = presenterProvider;
  }

  @Override
  public void injectMembers(MainActivity instance) {  
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    supertypeInjector.injectMembers(instance);
    instance.presenter = presenterProvider.get();
  }

  public static MembersInjector<MainActivity> create(MembersInjector<BaseActivity> supertypeInjector, Provider<MainActivityPresenterImpl> presenterProvider) {  
      return new MainActivity_MembersInjector(supertypeInjector, presenterProvider);
  }
}

