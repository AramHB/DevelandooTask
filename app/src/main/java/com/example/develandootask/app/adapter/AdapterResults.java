package com.example.develandootask.app.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.develandootask.app.view.ResultView;
import com.example.develandootask.core.realm.model.RealmDataItem;

import io.realm.RealmRecyclerViewAdapter;
import io.realm.RealmResults;

public class AdapterResults extends RealmRecyclerViewAdapter<RealmDataItem, AdapterResults.ResultHolder>
        implements ResultView.ResultActions {

    private final AdapterCallbacks callbacks;

    public interface AdapterCallbacks {
        void onItemClick(int position, int itemId);
    }

    private AdapterResults(Builder builder) {
        super(builder.list, true, true);
        this.callbacks = builder.callbacks;

        // Only set this if the model class has a primary key that is also a integer or long.
        // In that case, {@code getItemId(int)} must also be overridden to return the key.
        // See https://developer.android.com/reference/android/support/v7/widget/RecyclerView.Adapter.html#hasStableIds()
        // See https://developer.android.com/reference/android/support/v7/widget/RecyclerView.Adapter.html#getItemId(int)
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public ResultHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ResultView itemView = new ResultView.Builder(parent.getContext())
                .withItemActions(AdapterResults.this)
                .build();
        return new ResultHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultHolder holder, int position) {
        RealmDataItem item = getItem(position);
        holder.data = item;
        if (holder.data != null) {
            holder.view.bindView(position, item);
        }
    }

    @Override
    public long getItemId(int index) {
        //noinspection ConstantConditions
        return getItem(index).getItemId();
    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    @Override
    public void onItemClick(int position, int item) {
        callbacks.onItemClick(position, item);
    }

    public void updateList(RealmResults<RealmDataItem> list) {
        if (list != null) {
            updateData(list);
        }
    }

    public class ResultHolder extends RecyclerView.ViewHolder {
        ResultView view;
        public RealmDataItem data;

        ResultHolder(ResultView view) {
            super(view);
            this.view = view;
        }
    }

    public static class Builder {

        private final Context context;
        private AdapterCallbacks callbacks;
        private RealmResults<RealmDataItem> list;

        public Builder(Context context) {
            this.context = context;
        }

        @NonNull
        public Builder withDataItems(RealmResults<RealmDataItem> list) {
            this.list = list;
            return this;
        }

        @NonNull
        public Builder withCallbacks(AdapterCallbacks callbacks) {
            this.callbacks = callbacks;
            return this;
        }

        @NonNull
        public AdapterResults build() {
            return new AdapterResults(this);
        }
    }
}
