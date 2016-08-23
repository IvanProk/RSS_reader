package com.prok.ivan.rssapp.view;

import com.prok.ivan.rssapp.common.BaseFragment;
import com.prok.ivan.rssapp.presenter.ShowFragmentPresenterImpl;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class ShowFragment_MembersInjector implements MembersInjector<ShowFragment> {
  private final MembersInjector<BaseFragment> supertypeInjector;
  private final Provider<ShowFragmentPresenterImpl> presenterProvider;

  public ShowFragment_MembersInjector(MembersInjector<BaseFragment> supertypeInjector, Provider<ShowFragmentPresenterImpl> presenterProvider) {  
    assert supertypeInjector != null;
    this.supertypeInjector = supertypeInjector;
    assert presenterProvider != null;
    this.presenterProvider = presenterProvider;
  }

  @Override
  public void injectMembers(ShowFragment instance) {  
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    supertypeInjector.injectMembers(instance);
    instance.presenter = presenterProvider.get();
  }

  public static MembersInjector<ShowFragment> create(MembersInjector<BaseFragment> supertypeInjector, Provider<ShowFragmentPresenterImpl> presenterProvider) {  
      return new ShowFragment_MembersInjector(supertypeInjector, presenterProvider);
  }
}

