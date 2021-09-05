package com.example.tmdb.data.local.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.tmdb.data.model.Movie;

import java.util.List;

import io.reactivex.Observable;

@Dao
public interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Movie movie);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Movie> movieList);


    @Query("SELECT * FROM movie_table WHERE id = :movieId")
    Observable<Movie> fetchMovieFromRoom(int movieId);
}
