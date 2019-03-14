package com.shailu.news.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import javax.inject.Singleton;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import com.shailu.news.ui.list.TopHeadlinesViewModel;
import com.shailu.news.di.util.ViewModelKey;
import com.shailu.news.util.ViewModelFactory;

@Singleton
@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(TopHeadlinesViewModel.class)
    abstract ViewModel bindTopHeadlinesViewModel(TopHeadlinesViewModel topHeadlinesViewModel);


    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}



