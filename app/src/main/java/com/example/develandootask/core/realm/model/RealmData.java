package com.example.develandootask.core.realm.model;

import io.realm.RealmList;
import io.realm.RealmObject;

public class RealmData extends RealmObject {

    private RealmList<RealmDataItem> results = null;
    private RealmInfo info;

    public RealmList<RealmDataItem> getResults() {
        return results;
    }

    public void setResults(RealmList<RealmDataItem> results) {
        this.results = results;
    }

    public RealmInfo getInfo() {
        return info;
    }

    public void setInfo(RealmInfo info) {
        this.info = info;
    }

}