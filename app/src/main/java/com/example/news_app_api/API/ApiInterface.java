package com.example.news_app_api.API;

import com.example.news_app_api.Models.NewsApiResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("top-headlines")
    Call<NewsApiResponse> getNews(
            @Query("apiKey") String apiKey,
            @Query("category") String category
    );
}