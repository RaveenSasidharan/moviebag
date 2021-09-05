package com.example.tmdb.data;

import com.example.tmdb.data.local.db.DbHelper;
import com.example.tmdb.data.local.prefs.PrefsHelper;
import com.example.tmdb.data.remote.ApiHelper;

public interface DataManager  extends DbHelper, PrefsHelper, ApiHelper {
}
