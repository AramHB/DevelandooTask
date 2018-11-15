package com.example.develandootask.core.realm.model;

import io.realm.RealmObject;

public class RealmTimezone extends RealmObject {

    private String offset;
    private String description;

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}