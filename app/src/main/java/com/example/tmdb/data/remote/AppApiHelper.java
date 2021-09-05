package com.example.tmdb.data.remote;

import com.example.tmdb.BuildConfig;
import com.example.tmdb.data.model.Movie;
import com.example.tmdb.data.model.TrendingResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class AppApiHelper implements ApiHelper{

    private static Retrofit mRetrofit;

    private  Object clientLocker = new Object();

    @Inject
    public AppApiHelper()
    {

    }


    public  Retrofit getRetrofitInstance() {

        GsonBuilder builder;
        builder = new GsonBuilder();
        // Register an adapter to manage the date types as long values
        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        });
        Gson gson = builder.create();


        if (mRetrofit == null) {
            synchronized (clientLocker) {
                if (mRetrofit != null)
                    return mRetrofit;

                mRetrofit = new Retrofit.Builder()
                        .baseUrl("https://api.themoviedb.org/")
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .client(getRequestHeader())
                        .build();

            }
        }



        return mRetrofit;
    }


    private static OkHttpClient getRequestHeader() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient httpClient;

        if (BuildConfig.DEBUG)
            httpClient = new OkHttpClient.Builder()
                    .connectTimeout(5, TimeUnit.MINUTES)
                    .writeTimeout(5, TimeUnit.MINUTES)
                    .readTimeout(5, TimeUnit.MINUTES)
                    .addInterceptor(interceptor)
                    .build();
        else
            httpClient = new OkHttpClient.Builder()
                    .connectTimeout(5, TimeUnit.MINUTES)
                    .writeTimeout(5, TimeUnit.MINUTES)
                    .readTimeout(5, TimeUnit.MINUTES)
                    .build();



        return httpClient;
    }


    @Override
    public Observable<TrendingResponse> fetchTrendingResponse(String media_type, String time_window) {
        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);
        return apiInterface.fetchTrendingResponse(media_type, time_window, BuildConfig.API_KEY);
    }

    @Override
    public Observable<Movie> fetchMovieFromTMDB(int movie_id) {
        ApiInterface apiInterface = getRetrofitInstance().create(ApiInterface.class);

        return apiInterface.fetchMovieFromTMDB(movie_id, BuildConfig.API_KEY);
    }
}
