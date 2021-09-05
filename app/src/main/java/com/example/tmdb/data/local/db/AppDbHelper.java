package com.example.tmdb.data.local.db;

import com.example.tmdb.data.model.Movie;
import com.example.tmdb.data.model.Result;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

@Singleton
public class AppDbHelper implements DbHelper
{
    private final AppDatabase mAppDatabase;

    @Inject
    public AppDbHelper(AppDatabase appDatabase) {
        this.mAppDatabase = appDatabase;
    }

    @Override
    public Observable<Boolean> insert(Result result) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                 mAppDatabase.resultDao().insert(result);
                 return true;
            }
        });
    }

    @Override
    public Observable<Boolean> insertAll(List<Result> resultList) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mAppDatabase.resultDao().insert(resultList);
                return true;
            }
        });
    }

    @Override
    public Observable<List<Result>> fetchTrending() {
        return mAppDatabase.resultDao().fetchTrending();
    }

    @Override
    public Observable<Boolean> insert(Movie movie) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mAppDatabase.movieDao().insert(movie);
                return true;
            }
        });
    }

    @Override
    public Observable<Boolean> insert(List<Movie> movieList) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mAppDatabase.movieDao().insert(movieList);
                return true;
            }
        });
    }

    @Override
    public Observable<Movie> fetchMovieFromRoom(int movieId) {
        return mAppDatabase.movieDao().fetchMovieFromRoom(movieId);
    }
}
