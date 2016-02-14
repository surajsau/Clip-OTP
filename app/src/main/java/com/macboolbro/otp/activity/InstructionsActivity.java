package com.macboolbro.otp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.macboolbro.otp.R;

/**
 * Created by MacboolBro on 13/02/16.
 */
public class InstructionsActivity extends AppIntro {

    @Override
    public void init(@Nullable Bundle savedInstanceState) {
        addFragment("SMS having OTP arrives",
                "You\'re in the payment portal where you need to enter the OTP sent to your mobile. And it has arrived..",
                R.drawable.step_one);

        addFragment("Popup comes up",
                "Then a popup comes up where you need to click on \'Copy to clipboard\'. A toast is shown confirming the action..",
                R.drawable.step_two);

        addFragment("Long press on OTP field",
                "Where you need to enter the OTP/passcode, long press until \'Paste\' baloon shows up..",
                R.drawable.step_three);

        addFragment("Click paste and voila!",
                "Click the \'Paste\' baloon and voila! You didn\'t even have to open the messaging app for the latest OTP!",
                R.drawable.step_four);

        setCustomTransformer(new FadeTransformer());
    }

    @Override
    public void onSkipPressed() {

    }

    @Override
    public void onNextPressed() {

    }

    @Override
    public void onDonePressed() {

    }

    @Override
    public void onSlideChanged() {

    }

    private void addFragment(String title, String description, int bgIcon) {
        addSlide(AppIntroFragment.newInstance(title, description, bgIcon, ContextCompat.getColor(this, android.R.color.white),
                ContextCompat.getColor(this, R.color.colorPrimaryDark), ContextCompat.getColor(this, R.color.colorPrimaryDark)));
    }

    public class FadeTransformer implements ViewPager.PageTransformer {

        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        @Override
        public void transformPage(View view, float position) {

            //http://stackoverflow.com/questions/25096069/viewpager-animation-fade-in-out-instead-of-slide
            view.setTranslationX(view.getWidth() * -position);

            if(position <= -1.0F || position >= 1.0F) {
                view.setAlpha(0.0F);
            } else if( position == 0.0F ) {
                view.setAlpha(1.0F);
            } else {
                // position is between -1.0F & 0.0F OR 0.0F & 1.0F
                view.setAlpha(1.0F - Math.abs(position));
            }

        }

    }
}
