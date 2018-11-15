package com.example.develandootask.core.realm;

import android.content.Context;
import android.util.Log;

import com.example.develandootask.core.realm.model.RealmCoordinates;
import com.example.develandootask.core.realm.model.RealmData;
import com.example.develandootask.core.realm.model.RealmDataItem;
import com.example.develandootask.core.realm.model.RealmDob;
import com.example.develandootask.core.realm.model.RealmId;
import com.example.develandootask.core.realm.model.RealmInfo;
import com.example.develandootask.core.realm.model.RealmLocation;
import com.example.develandootask.core.realm.model.RealmLogin;
import com.example.develandootask.core.realm.model.RealmName;
import com.example.develandootask.core.realm.model.RealmPicture;
import com.example.develandootask.core.realm.model.RealmRegistered;
import com.example.develandootask.core.realm.model.RealmTimezone;
import com.example.develandootask.core.service.response.model.RetrofitCoordinates;
import com.example.develandootask.core.service.response.model.RetrofitData;
import com.example.develandootask.core.service.response.model.RetrofitDob;
import com.example.develandootask.core.service.response.model.RetrofitId;
import com.example.develandootask.core.service.response.model.RetrofitInfo;
import com.example.develandootask.core.service.response.model.RetrofitLocation;
import com.example.develandootask.core.service.response.model.RetrofitLogin;
import com.example.develandootask.core.service.response.model.RetrofitName;
import com.example.develandootask.core.service.response.model.RetrofitPicture;
import com.example.develandootask.core.service.response.model.RetrofitRegistered;
import com.example.develandootask.core.service.response.model.RetrofitDataItem;
import com.example.develandootask.core.service.response.model.RetrofitTimezone;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

import static com.example.develandootask.core.Constant.APP_LOG;

public class RealmHelper {

    private String className;

    public RealmHelper(Context context) {
        className = getClass().getSimpleName();
    }

    public RealmResults<RealmDataItem> getData() {
        Realm realm = null;
//        RealmData dataItem;
        RealmResults<RealmDataItem> dataItems;
        try {

            realm = Realm.getDefaultInstance();

//            dataItem = realm.where(RealmData.class).findFirst();
            dataItems = realm.where(RealmDataItem.class).findAll();
            Log.d(APP_LOG, className + " results = " + dataItems.toString());
            return dataItems;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            close(realm);
        }
        return null;
    }

    public void insertData(Context context, RetrofitData retrofitData) {

        Realm realm = null;
//        RealmData data = toRealmData(retrofitData);
        RealmList<RealmDataItem> dataItems = toRealmDataItem(retrofitData.getResults());
        try {

            realm = Realm.getDefaultInstance();

            realm.executeTransaction(realm1 -> {
                realm1.deleteAll();
                Realm.init(context);
                if (dataItems != null) {
                    realm1.insertOrUpdate(dataItems);
                } else {
                    RealmData results = realm1.where(RealmData.class).findFirst();
                    if (results != null) {
                        results.deleteFromRealm();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(realm);
        }
    }

    private RealmData toRealmData(RetrofitData retrofitData) {
        RealmData data = new RealmData();

        data.setResults(toRealmDataItem(retrofitData.getResults()));
        data.setInfo(toRealmInfo(retrofitData.getInfo()));

        return data;
    }

    private RealmList<RealmDataItem> toRealmDataItem(List<RetrofitDataItem> retrofitDataItems) {
        RealmList<RealmDataItem> results = new RealmList<>();

        for (int i = 0; i < retrofitDataItems.size(); i++) {
            RetrofitDataItem result = retrofitDataItems.get(i);

            RealmDataItem realmDataItem = new RealmDataItem();

            realmDataItem.setCell(result.getCell());
            realmDataItem.setDob(toRealmDob(result.getDob()));
            realmDataItem.setEmail(result.getEmail());
            realmDataItem.setGender(result.getGender());
            realmDataItem.setItemId(i + 1);
            realmDataItem.setLocation(toRealmLocation(result.getLocation()));
            realmDataItem.setLogin(toRealmLogin(result.getLogin()));
            realmDataItem.setName(toRealmName(result.getName()));
            realmDataItem.setNat(result.getNat());
            realmDataItem.setPhone(result.getPhone());
            realmDataItem.setPicture(toRealmPicture(result.getPicture()));
            realmDataItem.setRead(false);
            realmDataItem.setRealmId(toRealmId(result.getId()));
            realmDataItem.setRegistered(toRealmRegistered(result.getRegistered()));

            results.add(realmDataItem);
        }

        return results;
    }

    private RealmRegistered toRealmRegistered(RetrofitRegistered retrofitRegistered) {

        RealmRegistered registered = new RealmRegistered();

        registered.setAge(retrofitRegistered.getAge());
        registered.setDate(retrofitRegistered.getDate());

        return registered;
    }

    private RealmId toRealmId(RetrofitId retrofitId) {

        RealmId realmId = new RealmId();

        realmId.setName(retrofitId.getName());
        realmId.setValue(retrofitId.getValue());

        return realmId;
    }

    private RealmPicture toRealmPicture(RetrofitPicture retrofitPicture) {

        RealmPicture picture = new RealmPicture();

        picture.setLarge(retrofitPicture.getLarge());
        picture.setMedium(retrofitPicture.getMedium());
        picture.setThumbnail(retrofitPicture.getThumbnail());

        return picture;
    }

    private RealmName toRealmName(RetrofitName retrofitName) {

        RealmName name = new RealmName();

        name.setFirst(retrofitName.getFirst());
        name.setLast(retrofitName.getLast());
        name.setTitle(retrofitName.getTitle());

        return name;
    }

    private RealmLogin toRealmLogin(RetrofitLogin retrofitLogin) {

        RealmLogin login = new RealmLogin();

        login.setMd5(retrofitLogin.getMd5());
        login.setPassword(retrofitLogin.getPassword());
        login.setSalt(retrofitLogin.getSalt());
        login.setSha1(retrofitLogin.getSha1());
        login.setSha256(retrofitLogin.getSha256());
        login.setUsername(retrofitLogin.getUsername());
        login.setUuid(retrofitLogin.getUuid());

        return login;
    }

    private RealmLocation toRealmLocation(RetrofitLocation retrofitLocation) {
        RealmLocation location = new RealmLocation();

        location.setCity(retrofitLocation.getCity());
        location.setCoordinates(toRealmCoordinates(retrofitLocation.getCoordinates()));
        location.setPostcode(retrofitLocation.getPostcode());
        location.setState(retrofitLocation.getState());
        location.setStreet(retrofitLocation.getStreet());
        location.setTimezone(toRealmTimezone(retrofitLocation.getTimezone()));

        return location;
    }

    private RealmTimezone toRealmTimezone(RetrofitTimezone retrofitTimezone) {

        RealmTimezone timezone = new RealmTimezone();

        timezone.setDescription(retrofitTimezone.getDescription());
        timezone.setOffset(retrofitTimezone.getOffset());

        return timezone;
    }

    private RealmCoordinates toRealmCoordinates(RetrofitCoordinates retrofitCoordinates) {

        RealmCoordinates coordinates = new RealmCoordinates();

        coordinates.setLatitude(retrofitCoordinates.getLatitude());
        coordinates.setLongitude(retrofitCoordinates.getLongitude());

        return coordinates;
    }

    private RealmDob toRealmDob(RetrofitDob retrofitDob) {

        RealmDob realmDob = new RealmDob();

        realmDob.setAge(retrofitDob.getAge());
        realmDob.setDate(retrofitDob.getDate());

        return realmDob;
    }

    private RealmInfo toRealmInfo(RetrofitInfo retrofitInfo) {

        RealmInfo realmInfo = new RealmInfo();

        realmInfo.setPage(retrofitInfo.getPage());
        realmInfo.setResults(retrofitInfo.getResults());
        realmInfo.setSeed(retrofitInfo.getSeed());
        realmInfo.setVersion(retrofitInfo.getVersion());

        return realmInfo;
    }

    public void updateReadStatus(int itemId) {
        Realm realm = null;
        try {

            realm = Realm.getDefaultInstance();

            realm.executeTransaction(mRealm -> {

                RealmDataItem dataItem = mRealm.where(RealmDataItem.class).equalTo("itemId", itemId).findFirst();
                if (dataItem != null) {
                    dataItem.setRead(true);
                }

            });

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(realm);
        }
    }

    public void clear(Context context) {

        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(realm1 -> {
                realm1.deleteAll();
                Realm.init(context);
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(realm);
        }
    }

    public void close() {
        Realm realm = Realm.getDefaultInstance();
        realm.close();
    }

    private void close(Realm realm) {
        if (realm != null) {
            realm.close();
        }
    }

    public RealmDataItem getItem(int itemId) {
        Realm realm = null;
        RealmDataItem dataItem;

        try {

            realm = Realm.getDefaultInstance();
            dataItem = realm.where(RealmDataItem.class).equalTo("itemId", itemId).findFirst();

            return dataItem;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(realm);
        }

        return null;
    }
}
