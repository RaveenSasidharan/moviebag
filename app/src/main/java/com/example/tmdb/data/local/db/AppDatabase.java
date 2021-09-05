package com.example.tmdb.data.local.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.tmdb.data.local.db.converter.TMDBRoomTypeConverters;
import com.example.tmdb.data.local.db.dao.MovieDao;
import com.example.tmdb.data.local.db.dao.ResultDao;
import com.example.tmdb.data.model.Movie;
import com.example.tmdb.data.model.Result;

@Database(entities = {Movie.class, Result.class}, version = 1)
@TypeConverters(TMDBRoomTypeConverters.class)
public  abstract class AppDatabase extends RoomDatabase {

    public abstract ResultDao resultDao();

    public abstract MovieDao movieDao();
}
