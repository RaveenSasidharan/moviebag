package com.example.tmdb.data.remote;

import com.example.tmdb.data.model.Movie;
import com.example.tmdb.data.model.TrendingResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiHelper {

    Observable<TrendingResponse> fetchTrendingResponse(@Path("media_type") String media_type,
                                                       @Path("time_window") String time_window);

    Observable<Movie> fetchMovieFromTMDB(@Path("movie_id")int movie_id);
}
