package com.example.tmdb;

import android.app.Application;

import com.example.tmdb.di.component.AppComponent;
import com.example.tmdb.di.component.DaggerAppComponent;

public class TMDBApplication  extends Application
{

    public AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .application(this)
                .build();

        appComponent.inject(this);


    }
}
