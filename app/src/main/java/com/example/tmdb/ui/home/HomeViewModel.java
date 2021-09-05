package com.example.tmdb.ui.home;

import com.example.tmdb.data.DataManager;
import com.example.tmdb.ui.base.BaseViewModel;
import com.example.tmdb.utils.AppConstants;
import com.example.tmdb.utils.rx.SchedulerProvider;

public class HomeViewModel extends BaseViewModel<IHomeNavigator>
{

    public HomeViewModel(DataManager dataManager, SchedulerProvider schedulerProvider)
    {
        super(dataManager, schedulerProvider);
    }


    public void getTrendingMoviesFromServer()
    {
        getCompositeDisposable().add(getDataManager().fetchTrendingResponse(AppConstants.DEFAULT_MEDIA_TYPE,
                 AppConstants.DEFAULT_TIME_WINDOW)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                        getNavigator().showTrendingMovies(response.getResults());
                }, throwable -> {

                }));
    }

    public void getTrendingMoviesFromRoom()
    {
        getCompositeDisposable().add(getDataManager().fetchTrending()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(response -> {
                    getNavigator().showTrendingMovies(response);

                }, throwable -> {

                }));
    }
}
