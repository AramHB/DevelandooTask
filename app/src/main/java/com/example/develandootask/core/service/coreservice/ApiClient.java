package com.example.develandootask.core.service.coreservice;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.example.develandootask.core.Constant.URL_API_HOST;

public class ApiClient {

    private static ApiClient apiClient = null;

    private static Retrofit retrofit = null;

    /* This method using SingleTone safe Thread design Pattern to keep instance */
    public static <T> T createRequest(@NonNull Class<T> tClass, @NonNull Context context) {
        if (apiClient == null) {
            synchronized (ApiClient.class) {
                if (apiClient == null) {
                    apiClient = new ApiClient();
                }
            }
        }
        return apiClient.getRetrofitInstance(context).create(tClass);
    }

    /* Create Retrofit Client*/
    private Retrofit getRetrofitInstance(@NonNull Context context) {
        if (retrofit == null) {
            retrofit = builder(context);
        }
        return retrofit;
    }

    /* Builder configs of Retrofit*/
    private Retrofit builder(@NonNull Context context) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return new Retrofit.Builder()
                .baseUrl(URL_API_HOST)
                .client(getHttpClient(context))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(new ToStringConverterFactory())
                .build();
    }

    private Cache ensureCache(Context context) {
        File httpCacheDirectory = new File(context.getCacheDir(), "responses");
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        return new Cache(httpCacheDirectory, cacheSize);
    }

    /* Config of OkHttpClient Lib  for core of  Retrofit*/
    private OkHttpClient getHttpClient(@NonNull Context context) {
        return new OkHttpClient.Builder()
                .cache(ensureCache(context)) // 10 MB
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60 / 2, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();
    }

}
