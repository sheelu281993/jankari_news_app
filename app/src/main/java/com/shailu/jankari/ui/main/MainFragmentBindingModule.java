package com.shailu.jankari.ui.main;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import com.shailu.jankari.ui.list.ListFragment;

@Module
public abstract class MainFragmentBindingModule {

    @ContributesAndroidInjector
    abstract ListFragment provideListFragment();

}
