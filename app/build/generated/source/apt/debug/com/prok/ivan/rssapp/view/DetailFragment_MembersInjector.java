package com.prok.ivan.rssapp.view;

import com.prok.ivan.rssapp.common.BaseFragment;
import com.prok.ivan.rssapp.presenter.DetailFragmentPresenterImpl;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class DetailFragment_MembersInjector implements MembersInjector<DetailFragment> {
  private final MembersInjector<BaseFragment> supertypeInjector;
  private final Provider<DetailFragmentPresenterImpl> presenterProvider;

  public DetailFragment_MembersInjector(MembersInjector<BaseFragment> supertypeInjector, Provider<DetailFragmentPresenterImpl> presenterProvider) {  
    assert supertypeInjector != null;
    this.supertypeInjector = supertypeInjector;
    assert presenterProvider != null;
    this.presenterProvider = presenterProvider;
  }

  @Override
  public void injectMembers(DetailFragment instance) {  
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    supertypeInjector.injectMembers(instance);
    instance.presenter = presenterProvider.get();
  }

  public static MembersInjector<DetailFragment> create(MembersInjector<BaseFragment> supertypeInjector, Provider<DetailFragmentPresenterImpl> presenterProvider) {  
      return new DetailFragment_MembersInjector(supertypeInjector, presenterProvider);
  }
}

