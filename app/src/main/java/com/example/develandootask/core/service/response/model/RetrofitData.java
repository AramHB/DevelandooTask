package com.example.develandootask.core.service.response.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RetrofitData {

    @SerializedName("results")
    @Expose
    private List<RetrofitDataItem> results = null;
    @SerializedName("info")
    @Expose
    private RetrofitInfo info;

    public List<RetrofitDataItem> getResults() {
        return results;
    }

    public void setResults(List<RetrofitDataItem> results) {
        this.results = results;
    }

    public RetrofitInfo getInfo() {
        return info;
    }

    public void setInfo(RetrofitInfo info) {
        this.info = info;
    }

}