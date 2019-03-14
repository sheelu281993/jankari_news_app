package com.shailu.news.data.rest;


import android.util.Log;

import com.shailu.news.data.model.TopHeadlines;
import com.shailu.news.util.MySharedPreference;

import javax.inject.Inject;

import io.reactivex.Single;

public class TopHeadlinesRepository {

    private final TopHeadlinesService repoService;

    @Inject
    MySharedPreference sharedPreference;

    @Inject
    public TopHeadlinesRepository(TopHeadlinesService repoService) {
        this.repoService = repoService;
    }

    public Single<TopHeadlines> getTopHeadlines(String apiKey) {
        Log.v("test123shailendra", sharedPreference.getPrefCountryCodeName() );
        return repoService.getTopHeadlines(sharedPreference.getPrefCountryCodeName(), apiKey);
    }
}
