package com.example.develandootask.core.service.request;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.develandootask.core.service.callback.CallbackData;
import com.example.develandootask.core.service.coreservice.ApiClient;
import com.example.develandootask.core.service.coreservice.ApiInterface;
import com.example.develandootask.core.service.response.model.RetrofitData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestGetData implements Callback<RetrofitData> {

    private final String page;
    private final String results;
    private final String seed;
    private final Context context;
    private final CallbackData listener;

    private RequestGetData(Builder builder) {
        this.page = builder.page;
        this.results = builder.results;
        this.seed = builder.seed;
        this.context = builder.context;
        this.listener = builder.listener;
    }


    public void sendRequest() {
        Call<RetrofitData> userCall = ApiClient.createRequest(ApiInterface.class, context)
                .getData(page, results, seed);
        userCall.enqueue(this);
    }

    @Override
    public void onResponse(@NonNull Call<RetrofitData> call, @NonNull Response<RetrofitData> response) {
        listener.onSuccess(call, response);
    }

    @Override
    public void onFailure(@NonNull Call<RetrofitData> call, @NonNull Throwable t) {
        listener.onFailure(call, t);
    }

    public static class Builder {
        private String page;
        private String results;
        private String seed;
        private Context context;
        private CallbackData listener;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setPage(String page) {
            this.page = page;
            return this;
        }

        public Builder setResults(String results) {
            this.results = results;
            return this;
        }

        public Builder setSeed(String seed) {
            this.seed = seed;
            return this;
        }

        @NonNull
        public Builder setListener(CallbackData listener) {
            this.listener = listener;
            return this;
        }

        @NonNull
        public RequestGetData build() {
            return new RequestGetData(this);
        }
    }
}
