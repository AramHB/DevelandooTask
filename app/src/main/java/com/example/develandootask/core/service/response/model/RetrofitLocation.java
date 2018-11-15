package com.example.develandootask.core.service.response.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RetrofitLocation {

    @SerializedName("street")
    @Expose
    private String street;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("postcode")
    @Expose
    private Integer postcode;
    @SerializedName("coordinates")
    @Expose
    private RetrofitCoordinates coordinates;
    @SerializedName("timezone")
    @Expose
    private RetrofitTimezone timezone;

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

    public RetrofitCoordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(RetrofitCoordinates coordinates) {
        this.coordinates = coordinates;
    }

    public RetrofitTimezone getTimezone() {
        return timezone;
    }

    public void setTimezone(RetrofitTimezone timezone) {
        this.timezone = timezone;
    }

}