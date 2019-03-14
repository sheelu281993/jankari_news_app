package com.shailu.news.ui.main;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import com.shailu.news.ui.list.ListFragment;

@Module
public abstract class MainFragmentBindingModule {

    @ContributesAndroidInjector
    abstract ListFragment provideListFragment();

}
