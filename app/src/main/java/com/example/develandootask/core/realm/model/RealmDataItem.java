package com.example.develandootask.core.realm.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class RealmDataItem extends RealmObject {

    @PrimaryKey
    private int itemId;
    private String gender;
    private RealmName name;
    private RealmLocation location;
    private String email;
    private RealmLogin login;
    private RealmDob dob;
    private RealmRegistered registered;
    private String phone;
    private String cell;
    private RealmId id;
    private RealmPicture picture;
    private String nat;

    private boolean isRead;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public RealmName getName() {
        return name;
    }

    public void setName(RealmName name) {
        this.name = name;
    }

    public RealmLocation getLocation() {
        return location;
    }

    public void setLocation(RealmLocation location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RealmLogin getLogin() {
        return login;
    }

    public void setLogin(RealmLogin login) {
        this.login = login;
    }

    public RealmDob getDob() {
        return dob;
    }

    public void setDob(RealmDob dob) {
        this.dob = dob;
    }

    public RealmRegistered getRegistered() {
        return registered;
    }

    public void setRegistered(RealmRegistered registered) {
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

    public RealmId getRealmId() {
        return id;
    }

    public void setRealmId(RealmId id) {
        this.id = id;
    }

    public RealmPicture getPicture() {
        return picture;
    }

    public void setPicture(RealmPicture picture) {
        this.picture = picture;
    }

    public String getNat() {
        return nat;
    }

    public void setNat(String nat) {
        this.nat = nat;
    }

    public long getId() {
        return itemId;
    }


    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

}