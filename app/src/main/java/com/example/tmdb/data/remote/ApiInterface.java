package com.example.tmdb.data.remote;

import com.example.tmdb.BuildConfig;
import com.example.tmdb.data.model.Movie;
import com.example.tmdb.data.model.TrendingResponse;
import com.example.tmdb.utils.AppConstants;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {


    @GET("/3/trending/{media_type}/{time_window}")
    Observable<TrendingResponse> fetchTrendingResponse(@Path("media_type") String media_type,
                                                       @Path("time_window") String time_window, @Query("api_key") String apiKey);

    @GET("/3/movie/{movie_id}")
    Observable<Movie> fetchMovieFromTMDB(@Path("movie_id")int movie_id , @Query("api_key") String apiKey);

}
