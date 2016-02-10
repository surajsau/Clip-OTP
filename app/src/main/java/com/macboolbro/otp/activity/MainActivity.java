package com.macboolbro.otp.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.macboolbro.otp.IConstants;
import com.macboolbro.otp.R;

public class MainActivity extends AppCompatActivity implements IConstants, View.OnClickListener {

    private FrameLayout flDIY;
    private FrameLayout flPrivacy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initResources();
        setupToolbar();

        checkForSmsPermissions();

        setOnClickListeners();
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initResources() {
        flDIY = (FrameLayout) findViewById(R.id.llDIY);
        flPrivacy = (FrameLayout) findViewById(R.id.llPrivacy);
    }

    private void setOnClickListeners() {
        flDIY.setOnClickListener(this);
        flPrivacy.setOnClickListener(this);
    }

    private void checkForSmsPermissions() {
        int smsPermissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS);

        if(smsPermissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS},
                    SMS_PERMISSIONS);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llDIY:
                startSettingsActivity(SCREEN_DIY);
                break;
            case R.id.llPrivacy:
                startSettingsActivity(SCREEN_PRIVACY);
                break;
        }
    }

    private void startSettingsActivity(String screenName) {
        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        settingsIntent.putExtra(SCREEN_TYPE, screenName);
        startActivity(settingsIntent);
    }
}