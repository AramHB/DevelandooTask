package com.example.develandootask.core.service.coreservice;

import android.support.annotation.NonNull;

import com.example.develandootask.core.service.response.model.RetrofitData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

import static com.example.develandootask.core.Constant.KEY_Accept;
import static com.example.develandootask.core.Constant.KEY_Cache_Control;
import static com.example.develandootask.core.Constant.KEY_Page;
import static com.example.develandootask.core.Constant.KEY_Post_Content_Type;
import static com.example.develandootask.core.Constant.KEY_Results;
import static com.example.develandootask.core.Constant.KEY_Seed;
import static com.example.develandootask.core.Constant.URL_DataByPageResultSeed;

public interface ApiInterface {

    /** To get Data By page, result and seed */
    @NonNull
    @Headers({KEY_Accept, KEY_Post_Content_Type, KEY_Cache_Control})
    @GET(URL_DataByPageResultSeed)
    Call<RetrofitData> getData(@Query(KEY_Page) String page, @Query(KEY_Results) String results, @Query(KEY_Seed) String seed);

}
