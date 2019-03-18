package com.shailu.jankari.di.module;

import android.content.Context;

import com.shailu.jankari.util.MySharedPreference;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Singleton
@Module
public class MySharedPreferencesModule {

    @Provides
    public MySharedPreference provideMySharedPreferences(Context context) {
        return new MySharedPreference(context);
    }
}
