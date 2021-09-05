package com.example.tmdb.di.component;

import com.example.tmdb.di.module.ActivityModule;
import com.example.tmdb.di.scope.ActivityScope;
import com.example.tmdb.ui.details.MovieDetailsActivity;
import com.example.tmdb.ui.home.HomeActivity;

import dagger.Component;

@ActivityScope
@Component(modules = ActivityModule.class, dependencies = AppComponent.class)
public interface ActivityComponent {

    public void inject(HomeActivity homeActivity);
    public void inject(MovieDetailsActivity movieDetailsActivity);

}
