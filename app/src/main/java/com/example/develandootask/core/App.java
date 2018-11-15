package com.example.develandootask.core;

import android.app.Application;

import com.example.develandootask.core.realm.RealmHelper;

import io.realm.Realm;
import io.realm.RealmConfiguration;

import static com.example.develandootask.core.Constant.REALM_NAME;

public class App extends Application {

    private static App app;
    private RealmHelper realmHelper;

    public static App getInstance() {

        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(REALM_NAME)
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(1)
                .build();


        Realm.setDefaultConfiguration(realmConfiguration);
        realmHelper = new RealmHelper(this);
    }

    public RealmHelper getRealmHelper() {
        return realmHelper;
    }
}
