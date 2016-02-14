package com.halfplatepoha.otp.util;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by MacboolBro on 14/02/16.
 */
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
