package com.example.tmdb.data;

import android.content.Context;

import com.example.tmdb.data.local.db.DbHelper;
import com.example.tmdb.data.local.prefs.PrefsHelper;
import com.example.tmdb.data.model.Movie;
import com.example.tmdb.data.model.Result;
import com.example.tmdb.data.model.TrendingResponse;
import com.example.tmdb.data.remote.ApiHelper;
import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class AppDataManager implements DataManager{
    private static final String TAG = "AppDataManager";

    private final ApiHelper mApiHelper;

    private final Context mContext;

    private final DbHelper mDbHelper;

    private final Gson mGson;

    private final PrefsHelper mPreferencesHelper;

    @Inject
    public AppDataManager(Context context, DbHelper dbHelper, PrefsHelper preferencesHelper, ApiHelper apiHelper, Gson gson) {
        mContext = context;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
        mApiHelper = apiHelper;
        mGson = gson;
    }

    @Override
    public Observable<Boolean> insert(Result result) {
        return mDbHelper.insert(result);
    }

    @Override
    public Observable<Boolean> insertAll(List<Result> resultList) {
        return mDbHelper.insertAll(resultList);
    }

    @Override
    public Observable<List<Result>> fetchTrending() {
        return mDbHelper.fetchTrending();
    }

    @Override
    public Observable<Boolean> insert(Movie movie) {
        return mDbHelper.insert(movie);
    }

    @Override
    public Observable<Boolean> insert(List<Movie> movieList) {
        return mDbHelper.insert(movieList);
    }

    @Override
    public Observable<Movie> fetchMovieFromRoom(int movieId) {
        return mDbHelper.fetchMovieFromRoom(movieId);
    }

    @Override
    public Observable<TrendingResponse> fetchTrendingResponse(String media_type, String time_window) {
        return mApiHelper.fetchTrendingResponse(media_type, time_window);
    }

    @Override
    public Observable<Movie> fetchMovieFromTMDB(int movie_id) {
        return mApiHelper.fetchMovieFromTMDB(movie_id);
    }
}

