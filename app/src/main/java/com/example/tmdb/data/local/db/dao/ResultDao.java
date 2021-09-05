package com.example.tmdb.data.local.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.tmdb.data.model.Movie;
import com.example.tmdb.data.model.Result;

import java.util.List;

import io.reactivex.Observable;

@Dao
public interface ResultDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Result result);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Result> resultList);


    @Query("SELECT * FROM trending_table")
    Observable<List<Result>> fetchTrending();
}
