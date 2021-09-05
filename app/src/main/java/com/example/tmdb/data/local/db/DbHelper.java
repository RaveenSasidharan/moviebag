package com.example.tmdb.data.local.db;

import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.tmdb.data.model.Movie;
import com.example.tmdb.data.model.Result;

import java.util.List;

import io.reactivex.Observable;

public interface DbHelper {

    Observable<Boolean> insert(Result result);

    Observable<Boolean> insertAll(List<Result> resultList);

    Observable<List<Result>> fetchTrending();

    Observable<Boolean> insert(Movie movie);

    Observable<Boolean> insert(List<Movie> movieList);

    Observable<Movie> fetchMovieFromRoom(int movieId);
}
