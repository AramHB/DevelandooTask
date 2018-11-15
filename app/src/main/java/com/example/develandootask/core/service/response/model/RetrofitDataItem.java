package com.example.develandootask.core.service.response.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RetrofitDataItem {

    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("name")
    @Expose
    private RetrofitName name;
    @SerializedName("location")
    @Expose
    private RetrofitLocation location;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("login")
    @Expose
    private RetrofitLogin login;
    @SerializedName("dob")
    @Expose
    private RetrofitDob dob;
    @SerializedName("registered")
    @Expose
    private RetrofitRegistered registered;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("cell")
    @Expose
    private String cell;
    @SerializedName("id")
    @Expose
    private RetrofitId id;
    @SerializedName("picture")
    @Expose
    private RetrofitPicture picture;
    @SerializedName("nat")
    @Expose
    private String nat;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public RetrofitName getName() {
        return name;
    }

    public void setName(RetrofitName name) {
        this.name = name;
    }

    public RetrofitLocation getLocation() {
        return location;
    }

    public void setLocation(RetrofitLocation location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RetrofitLogin getLogin() {
        return login;
    }

    public void setLogin(RetrofitLogin login) {
        this.login = login;
    }

    public RetrofitDob getDob() {
        return dob;
    }

    public void setDob(RetrofitDob dob) {
        this.dob = dob;
    }

    public RetrofitRegistered getRegistered() {
        return registered;
    }

    public void setRegistered(RetrofitRegistered registered) {
        this.registered = registered;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public RetrofitId getId() {
        return id;
    }

    public void setId(RetrofitId id) {
        this.id = id;
    }

    public RetrofitPicture getPicture() {
        return picture;
    }

    public void setPicture(RetrofitPicture picture) {
        this.picture = picture;
    }

    public String getNat() {
        return nat;
    }

    public void setNat(String nat) {
        this.nat = nat;
    }

}