package com.example.tmdb.di.component;

import android.app.Application;

import com.example.tmdb.TMDBApplication;
import com.example.tmdb.data.DataManager;
import com.example.tmdb.di.module.AppModule;
import com.example.tmdb.utils.rx.SchedulerProvider;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(TMDBApplication app);

    DataManager getDataManager();

    SchedulerProvider getSchedulerProvider();

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}