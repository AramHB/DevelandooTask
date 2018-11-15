package com.example.develandootask.app.activity;

import android.support.v7.app.AppCompatActivity;

import com.example.develandootask.core.App;
import com.example.develandootask.core.realm.RealmHelper;

public class ActivityBase extends AppCompatActivity {


    protected RealmHelper getRealmHelper() {
        RealmHelper realmHelper;
        realmHelper = App.getInstance().getRealmHelper();
        return realmHelper;
    }
}
