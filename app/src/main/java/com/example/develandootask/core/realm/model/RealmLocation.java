package com.example.develandootask.core.realm.model;

import io.realm.RealmObject;

public class RealmLocation extends RealmObject {

    private String street;
    private String city;
    private String state;
    private Integer postcode;
    private RealmCoordinates coordinates;
    private RealmTimezone timezone;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getPostcode() {
        return postcode;
    }

    public void setPostcode(Integer postcode) {
        this.postcode = postcode;
    }

    public RealmCoordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(RealmCoordinates coordinates) {
        this.coordinates = coordinates;
    }

    public RealmTimezone getTimezone() {
        return timezone;
    }

    public void setTimezone(RealmTimezone timezone) {
        this.timezone = timezone;
    }

}