package com.example.tmdb.di.module;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.ViewModelProvider;

import com.example.tmdb.ViewModelProviderFactory;
import com.example.tmdb.data.DataManager;
import com.example.tmdb.ui.base.BaseActivity;
import com.example.tmdb.ui.details.DetailsViewModel;
import com.example.tmdb.ui.home.HomeViewModel;
import com.example.tmdb.utils.rx.SchedulerProvider;

import java.util.function.Supplier;

import dagger.Module;
import dagger.Provides;


@Module
public class ActivityModule {
    private BaseActivity<?, ?> activity;

    public ActivityModule(BaseActivity<?, ?> activity) {
        this.activity = activity;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Provides
    HomeViewModel provideFeedViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        Supplier<HomeViewModel> supplier = () -> new HomeViewModel(dataManager, schedulerProvider);
        ViewModelProviderFactory<HomeViewModel> factory = new ViewModelProviderFactory<>(HomeViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(HomeViewModel.class);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Provides
    DetailsViewModel provideMainViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        Supplier<DetailsViewModel> supplier = () -> new DetailsViewModel(dataManager, schedulerProvider);
        ViewModelProviderFactory<DetailsViewModel> factory = new ViewModelProviderFactory<>(DetailsViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(DetailsViewModel.class);
    }


}