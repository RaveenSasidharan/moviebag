package com.example.tmdb.ui.home;

import com.example.tmdb.data.model.Result;

import java.util.List;

public interface IHomeNavigator {

    void showTrendingMovies(List<Result> resultList);

    void updateTrendingMovies(List<Result> resultList);

    void showError(Throwable throwable);
}
