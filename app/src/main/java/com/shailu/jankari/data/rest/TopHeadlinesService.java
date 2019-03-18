package com.shailu.jankari.data.rest;


import com.shailu.jankari.data.model.TopHeadlines;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TopHeadlinesService {

    @GET("/v2/top-headlines")
    Single<TopHeadlines> getTopHeadlines(@Query("country") String country,
                                         @Query("apiKey") String apiKey,
                                         @Query("category") String category);

}
