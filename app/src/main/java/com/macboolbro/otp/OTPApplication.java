package com.macboolbro.otp;

import android.app.Application;

import com.macboolbro.otp.db.OTPDataSource;

/**
 * Created by MacboolBro on 12/02/16.
 */
public class OTPApplication extends Application {
    private OTPDataSource otpDataSource;

    @Override
    public void onCreate() {
        super.onCreate();
        otpDataSource = new OTPDataSource(this);
        otpDataSource.open();
    }
}
