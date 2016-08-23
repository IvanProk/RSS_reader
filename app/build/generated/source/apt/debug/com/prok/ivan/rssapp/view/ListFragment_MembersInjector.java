package com.prok.ivan.rssapp.view;

import com.prok.ivan.rssapp.common.BaseFragment;
import com.prok.ivan.rssapp.presenter.ListFragmentPresenterImpl;
import dagger.MembersInjector;
import javax.annotation.Generated;
import javax.inject.Provider;

@Generated("dagger.internal.codegen.ComponentProcessor")
public final class ListFragment_MembersInjector implements MembersInjector<ListFragment> {
  private final MembersInjector<BaseFragment> supertypeInjector;
  private final Provider<ListFragmentPresenterImpl> presenterProvider;

  public ListFragment_MembersInjector(MembersInjector<BaseFragment> supertypeInjector, Provider<ListFragmentPresenterImpl> presenterProvider) {  
    assert supertypeInjector != null;
    this.supertypeInjector = supertypeInjector;
    assert presenterProvider != null;
    this.presenterProvider = presenterProvider;
  }

  @Override
  public void injectMembers(ListFragment instance) {  
    if (instance == null) {
      throw new NullPointerException("Cannot inject members into a null reference");
    }
    supertypeInjector.injectMembers(instance);
    instance.presenter = presenterProvider.get();
  }

  public static MembersInjector<ListFragment> create(MembersInjector<BaseFragment> supertypeInjector, Provider<ListFragmentPresenterImpl> presenterProvider) {  
      return new ListFragment_MembersInjector(supertypeInjector, presenterProvider);
  }
}

