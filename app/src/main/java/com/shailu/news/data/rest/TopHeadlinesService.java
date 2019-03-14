package com.shailu.news.data.rest;


import com.shailu.news.data.model.TopHeadlines;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TopHeadlinesService {

    @GET("/v2/top-headlines")
    Single<TopHeadlines> getTopHeadlines(@Query("country") String country, @Query("apiKey") String apiKey);

}
