package com.example.tmdb.di.module;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.example.tmdb.BuildConfig;
import com.example.tmdb.data.AppDataManager;
import com.example.tmdb.data.DataManager;
import com.example.tmdb.data.local.db.AppDatabase;
import com.example.tmdb.data.local.db.AppDbHelper;
import com.example.tmdb.data.local.db.DbHelper;
import com.example.tmdb.data.local.prefs.AppPreferenceHelper;
import com.example.tmdb.data.local.prefs.PrefsHelper;
import com.example.tmdb.data.remote.ApiHelper;
import com.example.tmdb.data.remote.AppApiHelper;
import com.example.tmdb.di.ApiInfo;
import com.example.tmdb.di.DatabaseInfo;
import com.example.tmdb.di.PreferenceInfo;
import com.example.tmdb.utils.AppConstants;
import com.example.tmdb.utils.rx.AppSchedulerProvider;
import com.example.tmdb.utils.rx.SchedulerProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @ApiInfo
    String provideApiKey() {
        return BuildConfig.API_KEY;
    }

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@DatabaseInfo String dbName, Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, dbName).fallbackToDestructiveMigration()
                .build();
    }



    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    PrefsHelper providePreferencesHelper(AppPreferenceHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }



    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

}
