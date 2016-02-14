package com.halfplatepoha.otp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.halfplatepoha.otp.R;
import com.halfplatepoha.otp.util.AppPreference;
import com.halfplatepoha.otp.util.IConstants;

/**
 * Created by MacboolBro on 14/02/16.
 */
public class SplashActivity extends AppCompatActivity {

    private boolean isAppFirstTime;
    private AppPreference preference;

    private static final int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preference = new AppPreference(this);

        setContentView(R.layout.activity_splash);

        isAppFirstTime = preference.getBoolean(IConstants.IS_APP_FIRST_TIME, true);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!isAppFirstTime) {
                    startMainActivity();
                }
                else {
                    startIntroductionActivity();
                    preference.putBoolean(IConstants.PREF_NOTIFICATION_ENABLED, true);
                    preference.putBoolean(IConstants.IS_APP_FIRST_TIME, false);
                }
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    private void startIntroductionActivity() {
        Intent introIntent = new Intent(this, IntroductionActivity.class);
        introIntent.putExtra(IConstants.IS_FROM_MAIN, false);
        startActivity(introIntent);
    }

    private void startMainActivity() {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
    }
}
