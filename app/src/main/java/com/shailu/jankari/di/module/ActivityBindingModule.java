package com.shailu.jankari.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

import com.shailu.jankari.ui.WebViewActivity;
import com.shailu.jankari.ui.main.MainActivity;
import com.shailu.jankari.ui.main.MainFragmentBindingModule;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = {MainFragmentBindingModule.class})
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector()
    abstract WebViewActivity bindWebViewActivity();
}
