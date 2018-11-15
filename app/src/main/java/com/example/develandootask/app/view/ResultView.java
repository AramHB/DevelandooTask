package com.example.develandootask.app.view;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.develandootask.R;
import com.example.develandootask.core.realm.model.RealmDataItem;

public class ResultView extends RelativeLayout {

    private Context context;
    private RealmDataItem data;
    private LinearLayout container;
    private CircleImageView avatar;
    private ResultActions itemActions;
    private ProgressBar avatarProgress;
    private AppCompatTextView resultFullName, resultStateCity, resultPhone;

    public interface ResultActions {
        void onItemClick(int position, int itemId);
    }

    private ResultView(Builder builder) {
        super(builder.context);

        this.itemActions = builder.actions;
        init(builder.context);
    }

    public ResultView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public ResultView(@NonNull Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ResultView(@NonNull Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public ResultView(@NonNull Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        initView();
    }

    private void initView() {

        inflate(context, R.layout.result_item, this);
        container = findViewById(R.id.result_container);
        avatar = findViewById(R.id.result_avatar);
        avatarProgress = findViewById(R.id.result_progress);
        resultFullName = findViewById(R.id.result_full_name);
        resultStateCity = findViewById(R.id.result_state_city);
        resultPhone = findViewById(R.id.result_phone);
    }

    public void bindView(int position, @NonNull RealmDataItem result) {
        this.data = result;
        initListeners(position);
        setUpItemData(result);
    }

    private void setUpItemData(RealmDataItem data) {
        loadNotIcon();

        if (data.isRead()) {
            container.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        } else {
            container.setBackgroundColor(ContextCompat.getColor(context, R.color.white_50));
        }

        String fullName =  data.getName().getFirst() + " " + data.getName().getLast();
        resultFullName.setText(fullName);

        String stateCity =  data.getLocation().getStreet() + " "
                + data.getLocation().getCity() + " "
                + data.getLocation().getState();
        resultStateCity.setText(stateCity);

        String phone = data.getPhone();
        resultPhone.setText(phone);

    }

    private void loadNotIcon() {
        String logo = data.getPicture().getMedium();
        GlideApp.with(context)
                .load(logo)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        avatarProgress.setVisibility(GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target,
                                                   com.bumptech.glide.load.DataSource dataSource, boolean isFirstResource) {
                        avatar.setImageDrawable(resource);
                        avatarProgress.setVisibility(GONE);
                        return false;
                    }
                })
                .into(avatar);
    }

    private void initListeners(int position) {

        setOnClickListener(v -> {
                itemActions.onItemClick(position, data.getItemId());
                if (!data.isRead()) {
                    resultFullName.setTypeface(resultFullName.getTypeface(), Typeface.NORMAL);
                    resultStateCity.setTypeface(resultStateCity.getTypeface(), Typeface.NORMAL);
                    resultPhone.setTypeface(resultPhone.getTypeface(), Typeface.NORMAL);
                }
        });
    }

    public static class Builder {

        private final Context context;
        private ResultView.ResultActions actions;

        public Builder(Context context) {
            this.context = context;
        }

        @NonNull
        public Builder withItemActions(ResultActions actions) {
            this.actions = actions;
            return this;
        }

        @NonNull
        public ResultView build() {
            return new ResultView(this);
        }
    }
}

