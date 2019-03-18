package com.shailu.jankari.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import javax.inject.Singleton;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import com.shailu.jankari.ui.list.TopHeadlinesViewModel;
import com.shailu.jankari.di.util.ViewModelKey;
import com.shailu.jankari.util.ViewModelFactory;

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



