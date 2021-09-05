package com.example.tmdb.ui.details;

import com.example.tmdb.data.model.Movie;

public interface IDetailsNavigator {

    void showError(Throwable throwable);

    void showMovieDetails(Movie movie);
}
