package com.macboolbro.otp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.macboolbro.otp.R;
import com.macboolbro.otp.util.FadeTransformer;

/**
 * Created by MacboolBro on 13/02/16.
 */
public class IntroductionActivity extends AppIntro {

    @Override
    public void init(Bundle savedInstanceState) {

        addFragment("Easy notifications",
                "Now when you receive an SMS for OTP or passcode, a popup comes up. We\'ve made it so easy for you",
                R.drawable.notification);

        addFragment("Copy & Paste",
                "Click on \'Copy to clipboard\' and long press on the text field to \'Paste\'",
                R.drawable.copy_paste);

        addFragment("No more switching apps",
                "You don\'t have to switch between browser and messaging apps when entering a passcode or OTP",
                R.drawable.switching);

        addFragment("No internet required",
                "This app doesn't require internet at all. ",
                R.drawable.wifi);

        addFragment("Absolutely secure",
                "We don\'t steal any information whatsoever. Contact me if you\'ve trust issues",
                R.drawable.security);

        addFragment("Easy life!",
                "From now on we\'ll do the hard work for you. So, sit back and relax. Just wait for our popup!",
                R.drawable.enjoy);

        setCustomTransformer(new FadeTransformer());
    }

    @Override
    public void onSkipPressed() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onNextPressed() {

    }

    @Override
    public void onDonePressed() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onSlideChanged() {

    }

    private void addFragment(String title, String description, int bgIcon) {
        addSlide(AppIntroFragment.newInstance(title, description, bgIcon, ContextCompat.getColor(this, android.R.color.white),
                ContextCompat.getColor(this, R.color.colorPrimaryDark), ContextCompat.getColor(this, R.color.colorPrimaryDark)));
    }
}
