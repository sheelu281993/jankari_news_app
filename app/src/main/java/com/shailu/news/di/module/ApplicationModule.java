package com.shailu.news.di.module;

import android.content.Context;
import android.util.Log;

import com.shailu.news.data.rest.TopHeadlinesService;
import com.shailu.news.di.util.NetworkUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
@Module(includes = ViewModelModule.class)
public class ApplicationModule {

    private static final String BASE_URL = "https://newsapi.org/";

    @Singleton
    @Provides
    static Retrofit provideRetrofit(Context context) {
        Interceptor REWRITE_RESPONSE_INTERCEPTOR_OFFLINE = chain -> {
            Request request = chain.request();
            if (!NetworkUtils.checkConnection(context)) {
                request = request.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached")
                        .build();
            }
            return chain.proceed(request);
        };

        Interceptor REWRITE_RESPONSE_INTERCEPTOR = chain -> {
            Response originalResponse = chain.proceed(chain.request());
            String cacheControl = originalResponse.header("Cache-Control");
            if (cacheControl == null || cacheControl.contains("no-store") || cacheControl.contains("no-cache") ||
                    cacheControl.contains("must-revalidate") || cacheControl.contains("max-age=0")) {
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, max-age=" + 5000)
                        .build();
            } else {
                return originalResponse;
            }
        };


        File httpCacheDirectory = new File(context.getCacheDir(), "offlineCache");
        //10 MB
        Cache cache = new Cache(httpCacheDirectory, 10 * 1024 * 1024);
        OkHttpClient client = new OkHttpClient().newBuilder()
                .cache(cache)
                .addNetworkInterceptor(REWRITE_RESPONSE_INTERCEPTOR)
                .addInterceptor(REWRITE_RESPONSE_INTERCEPTOR_OFFLINE)
                .build();

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    @Singleton
    @Provides
    static TopHeadlinesService provideRetrofitService(Retrofit retrofit) {
        return retrofit.create(TopHeadlinesService.class);
    }
}


