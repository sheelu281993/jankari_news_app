package com.shailu.jankari.data.rest;

import com.shailu.jankari.data.model.TopHeadlines;
import com.shailu.jankari.util.MySharedPreference;
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

    public Single<TopHeadlines> getTopHeadlines(String category) {
        String apiKey = "f5e5331f80fd461b928f93f5f7ef6f54";
        return repoService.getTopHeadlines(sharedPreference.getPrefCountryCodeName(), apiKey, category);
    }
}
