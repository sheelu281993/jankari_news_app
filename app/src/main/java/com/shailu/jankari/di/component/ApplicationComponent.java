package com.shailu.jankari.di.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;
import com.shailu.jankari.base.BaseApplication;
import com.shailu.jankari.di.module.ActivityBindingModule;
import com.shailu.jankari.di.module.ApplicationModule;
import com.shailu.jankari.di.module.ContextModule;
import com.shailu.jankari.di.module.MySharedPreferencesModule;

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