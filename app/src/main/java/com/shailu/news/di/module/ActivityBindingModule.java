package com.shailu.news.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

import com.shailu.news.ui.WebViewActivity;
import com.shailu.news.ui.main.MainActivity;
import com.shailu.news.ui.main.MainFragmentBindingModule;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = {MainFragmentBindingModule.class})
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector()
    abstract WebViewActivity bindWebViewActivity();
}
