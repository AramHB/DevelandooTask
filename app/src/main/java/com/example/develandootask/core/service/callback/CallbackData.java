package com.example.develandootask.core.service.callback;

import android.support.annotation.NonNull;

import com.example.develandootask.core.service.response.model.RetrofitData;

import retrofit2.Call;
import retrofit2.Response;

public interface CallbackData {
    void onSuccess(Call<RetrofitData> call, Response<RetrofitData> response);
    void onFailure(Call<RetrofitData> call, @NonNull Throwable t);
}
