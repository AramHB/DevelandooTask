package com.example.develandootask.app.activity;

import android.app.MediaRouteButton;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.widget.ProgressBar;

import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.develandootask.R;
import com.example.develandootask.app.view.CircleImageView;
import com.example.develandootask.app.view.GlideApp;
import com.example.develandootask.core.realm.model.RealmDataItem;

import static android.view.View.GONE;

public class ActivityDetail extends ActivityBase {

    private int itemId;
    private AppCompatTextView tv_name,
            tv_gender, tv_email, tv_phone, tv_cell, tv_nat, tv_street, tv_city, tv_state,
            tv_post, tv_uuid, tv_user, tv_pass, tv_salt, tv_md5, tv_sha1, tv_sha256,
            tv_dobDate, tv_dobAge, tv_regDate, tv_regAge, tv_idName, tv_idValue,
            tv_lat, tv_long, tv_offset, tv_desc;
    private RealmDataItem dataItem;
    private CircleImageView avatar;
    private ProgressBar avatarProgress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getExtras(getIntent());

        getItemData();

        initViews();

        setUpViews();

    }

    private void getItemData() {
        dataItem = getRealmHelper().getItem(itemId);
    }

    private void initViews() {
        avatar = findViewById(R.id.details_avatar);
        avatarProgress = findViewById(R.id.details_progress);
        tv_cell = findViewById(R.id.details_cell);
        tv_city = findViewById(R.id.details_loc_city);
        tv_dobAge = findViewById(R.id.details_dob_age);
        tv_dobDate = findViewById(R.id.details_dob_date);
        tv_email = findViewById(R.id.details_email);
        tv_gender = findViewById(R.id.details_gender);
        tv_idName = findViewById(R.id.details_id_name);
        tv_idValue = findViewById(R.id.details_id_value);
        tv_md5 = findViewById(R.id.details_login_md5);
        tv_name = findViewById(R.id.details_full_name);
        tv_nat = findViewById(R.id.details_nat);
        tv_pass = findViewById(R.id.details_login_pass);
        tv_phone = findViewById(R.id.details_phone);
        tv_post = findViewById(R.id.details_loc_postcode);
        tv_regAge = findViewById(R.id.details_reg_age);
        tv_regDate = findViewById(R.id.details_reg_date);
        tv_salt = findViewById(R.id.details_login_salt);
        tv_sha1 = findViewById(R.id.details_login_sha1);
        tv_sha256 = findViewById(R.id.details_login_sha256);
        tv_state = findViewById(R.id.details_loc_state);
        tv_street = findViewById(R.id.details_loc_street);
        tv_user = findViewById(R.id.details_login_user);
        tv_uuid = findViewById(R.id.details_login_uuid);
        tv_lat = findViewById(R.id.details_loc_coord_lat);
        tv_long = findViewById(R.id.details_loc_coord_lon);
        tv_offset = findViewById(R.id.details_loc_zone_offset);
        tv_desc = findViewById(R.id.details_loc_zone_desc);
    }

    private void setUpViews() {
        loadAvatar();
        String fullName = dataItem.getName().getTitle() + " " + dataItem.getName().getFirst() + " " + dataItem.getName().getLast();
        tv_name.setText(fullName);
        tv_cell.setText(String.format(getString(R.string.user_cell), dataItem.getCell()));
        tv_city.setText(String.format(getString(R.string.loc_city), dataItem.getLocation().getCity()));
        tv_dobAge.setText(String.format(getString(R.string.dob_age), dataItem.getDob().getAge()));
        tv_dobDate.setText(String.format(getString(R.string.dob_date), dataItem.getDob().getDate()));
        tv_email.setText(String.format(getString(R.string.user_email), dataItem.getEmail()));
        tv_gender.setText(String.format(getString(R.string.user_gender), dataItem.getGender()));
        tv_idName.setText(String.format(getString(R.string.id_name), dataItem.getRealmId().getName()));
        tv_idValue.setText(String.format(getString(R.string.id_value), dataItem.getRealmId().getValue()));
        tv_md5.setText(String.format(getString(R.string.log_md5), dataItem.getLogin().getMd5()));
        tv_nat.setText(String.format(getString(R.string.user_nat), dataItem.getNat()));
        tv_pass.setText(String.format(getString(R.string.log_pass), dataItem.getLogin().getPassword()));
        tv_phone.setText(String.format(getString(R.string.user_phone), dataItem.getPhone()));
        tv_post.setText(String.format(getString(R.string.loc_post), dataItem.getLocation().getPostcode()));
        tv_regAge.setText(String.format(getString(R.string.reg_age), dataItem.getRegistered().getAge()));
        tv_regDate.setText(String.format(getString(R.string.reg_date), dataItem.getRegistered().getDate()));
        tv_salt.setText(String.format(getString(R.string.log_salt), dataItem.getLogin().getSalt()));
        tv_sha1.setText(String.format(getString(R.string.log_sha1), dataItem.getLogin().getSha1()));
        tv_sha256.setText(String.format(getString(R.string.log_sha256), dataItem.getLogin().getSha256()));
        tv_state.setText(String.format(getString(R.string.loc_state), dataItem.getLocation().getState()));
        tv_street.setText(String.format(getString(R.string.loc_street), dataItem.getLocation().getStreet()));
        tv_user.setText(String.format(getString(R.string.log_user), dataItem.getLogin().getUsername()));
        tv_uuid.setText(String.format(getString(R.string.log_uuid), dataItem.getLogin().getUuid()));
        tv_lat.setText(String.format(getString(R.string.coord_lat), dataItem.getLocation().getCoordinates().getLatitude()));
        tv_long.setText(String.format(getString(R.string.coord_long), dataItem.getLocation().getCoordinates().getLongitude()));
        tv_offset.setText(String.format(getString(R.string.zone_off), dataItem.getLocation().getTimezone().getOffset()));
        tv_desc.setText(String.format(getString(R.string.zone_desc), dataItem.getLocation().getTimezone().getDescription()));
    }

    private void loadAvatar() {
        String logo = dataItem.getPicture().getMedium();
        GlideApp.with(this)
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

    private void getExtras(Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            itemId = bundle.getInt("itemId");
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
