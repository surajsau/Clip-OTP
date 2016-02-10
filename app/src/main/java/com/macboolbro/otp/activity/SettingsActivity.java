package com.macboolbro.otp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.macboolbro.otp.IConstants;
import com.macboolbro.otp.R;
import com.macboolbro.otp.fragment.GuideFragment;
import com.macboolbro.otp.fragment.PrivacyFragment;

public class SettingsActivity extends AppCompatActivity implements IConstants {

    private String screenName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getDataFromIntent();

        openFragmentInContainer(runFragment());
    }

    private void getDataFromIntent() {
        screenName = getIntent().getStringExtra(SCREEN_TYPE);
    }

    private Fragment runFragment() {
        switch (screenName) {
            case SCREEN_DIY:
                return new GuideFragment();

            case SCREEN_PRIVACY:
                return new PrivacyFragment();
        }
        return null;
    }

    private void openFragmentInContainer(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.llContainer, fragment, screenName)
                .commit();
    }

}
