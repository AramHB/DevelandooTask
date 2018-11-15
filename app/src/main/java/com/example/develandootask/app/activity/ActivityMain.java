package com.example.develandootask.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.develandootask.R;
import com.example.develandootask.app.adapter.AdapterResults;
import com.example.develandootask.core.realm.model.RealmDataItem;
import com.example.develandootask.core.service.callback.CallbackData;
import com.example.develandootask.core.service.request.RequestGetData;
import com.example.develandootask.core.service.response.model.RetrofitData;

import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Response;

import static com.example.develandootask.core.Constant.APP_LOG;

public class ActivityMain extends ActivityBase implements AdapterResults.AdapterCallbacks {

    private RecyclerView recyclerView;
    private AppCompatEditText etPage, etResult, etSeed/*, unavailableData*/;
    private LinearLayoutManager manager;
    private AdapterResults adapterResults;
    private LinearLayout loadingView;
    private String className;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        className = getClass().getSimpleName();

        initViews();
        setUpToolbar();
        setUpTexts();
        setUpDataList();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.refresh:
                refreshData();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onItemClick(int position, int itemId) {

        getRealmHelper().updateReadStatus(itemId);

        adapterResults.notifyItemChanged(position, getRealmHelper().getItem(itemId));

        Intent intent = new Intent(ActivityMain.this, ActivityDetail.class);
        intent.putExtra("itemId", itemId);

        startActivity(intent);
    }

    private void initViews() {
        etPage = findViewById(R.id.et_page);
        etResult = findViewById(R.id.et_results);
        etSeed = findViewById(R.id.et_seed);
        recyclerView = findViewById(R.id.rv_results);
        loadingView = findViewById(R.id.loading);

        manager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, manager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    private void setUpToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.app_name);
        }
    }

    private void setUpTexts() {
        etPage.setText(R.string.default_page);
        etResult.setText(R.string.default_results);
        etSeed.setText(R.string.default_seed);
    }

    private void setUpDataList() {

        RealmResults<RealmDataItem> results = getRealmHelper().getData();

        if (results == null || results.isEmpty()) {
            getDataFromServer();
        }

        adapterResults = new AdapterResults.Builder(this).withDataItems(results).withCallbacks(this).build();
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapterResults);
        recyclerView.setHasFixedSize(true);
        getRealmHelper().close();
    }

    private void getDataFromServer() {

        showLoading();

        Editable page = etPage.getText();
        Editable results = etResult.getText();
        Editable seed = etSeed.getText();

        // in case if credentials are NULL
        String pageText = (page != null && !page.toString().isEmpty()) ? page.toString() : getString(R.string.default_page);
        String resultsText = (results != null && !results.toString().isEmpty()) ? results.toString() : getString(R.string.default_results);
        String seedText = (seed != null && !seed.toString().isEmpty()) ? seed.toString() : getString(R.string.default_seed);


        new RequestGetData.Builder(this)
                .setPage(pageText).setResults(resultsText).setSeed(seedText)
                .setListener(new CallbackData() {
                    @Override
                    public void onSuccess(Call<RetrofitData> call, Response<RetrofitData> response) {
                        if (response.isSuccessful()) {
                            getRealmHelper().insertData(ActivityMain.this, response.body());
                            Log.d(APP_LOG, className + " onSuccess = " + response.body());

                            updateDataList();
                        }
                        hideLoading();
                    }

                    @Override
                    public void onFailure(Call<RetrofitData> call, @NonNull Throwable t) {
                        Log.d(APP_LOG, className + " onFailure = " + t.getMessage());
                        hideLoading();
                    }
                }).build().sendRequest();
    }

    private void updateDataList() {
        RealmResults<RealmDataItem> items = getRealmHelper().getData();
        adapterResults.updateList(items);

    }

    private void showLoading() {
        loadingView.setVisibility(View.VISIBLE);
    }

    private void hideLoading() {
        loadingView.setVisibility(View.GONE);
    }

    private void refreshData() {
        getRealmHelper().clear(getApplicationContext());
        getDataFromServer();
    }
}
