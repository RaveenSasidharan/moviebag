package com.example.tmdb.ui.details;

import com.example.tmdb.data.DataManager;
import com.example.tmdb.ui.base.BaseViewModel;
import com.example.tmdb.utils.AppConstants;
import com.example.tmdb.utils.rx.SchedulerProvider;

public class DetailsViewModel extends BaseViewModel<IDetailsNavigator> {
    public DetailsViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }


    public void getMovieFromServer(int movieID)
    {
        getCompositeDisposable().add(getDataManager().fetchMovieFromTMDB(movieID)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    getNavigator().showMovieDetails(response);
                }, throwable -> {
                    getNavigator().showError(throwable);
                }));
    }

    public void getMovieFromRoom(int movieID)
    {
        getCompositeDisposable().add(getDataManager().fetchMovieFromRoom(movieID)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    getNavigator().showMovieDetails(response);

                }, throwable -> {
                    getNavigator().showError(throwable);
                }));
    }
}
