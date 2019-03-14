package com.shailu.news.di.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;
import com.shailu.news.base.BaseApplication;
import com.shailu.news.di.module.ActivityBindingModule;
import com.shailu.news.di.module.ApplicationModule;
import com.shailu.news.di.module.ContextModule;
import com.shailu.news.di.module.MySharedPreferencesModule;

@Singleton
@Component(modules = {MySharedPreferencesModule.class, ContextModule.class, ApplicationModule.class, AndroidSupportInjectionModule.class, ActivityBindingModule.class})
public interface ApplicationComponent extends AndroidInjector<DaggerApplication> {

    void inject(BaseApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        ApplicationComponent build();
    }
}