package com.example.tmdb.data.local.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.tmdb.di.PreferenceInfo;

import javax.inject.Inject;

public class AppPreferenceHelper implements PrefsHelper{


    private final SharedPreferences mPrefs;

    @Inject
    public AppPreferenceHelper(Context context, @PreferenceInfo String prefFileName) {
        mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

}
