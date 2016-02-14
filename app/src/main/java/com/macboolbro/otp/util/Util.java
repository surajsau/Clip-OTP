package com.macboolbro.otp.util;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by MacboolBro on 09/02/16.
 */
public class Util implements IConstants {

    public static String otpFromMessage(String message) {
        String lowerCase = message.toLowerCase();
        boolean isOTPMessage = lowerCase.contains(OTP) ||
                lowerCase.contains(ONE_TIME_PASSWORD) ||
                lowerCase.contains(KEY) ||
                lowerCase.contains(CODE) ||
                lowerCase.contains(PASSCODE) ||
                lowerCase.contains(PASSKEY) ||
                lowerCase.contains(PASSWORD);

        if(isOTPMessage) {
            Pattern otpStringPattern = Pattern.compile(OTP_STRING_REGEX);
            Pattern otpPattern = Pattern.compile(OTP_REGEX);

            Matcher otpStringMatcher = otpStringPattern.matcher(message);
            if (otpStringMatcher.find()) {
                String otpString = otpStringMatcher.group(0);
                Matcher otpMatcher = otpPattern.matcher(otpString);

                if (otpMatcher.find()) {
                    String otp = otpMatcher.group(0);
                    return otp;
                }
            }
        }

        return null;
    }

    public static PendingIntent getClipboardPendingIntent(Context context, String clipText) {
        Intent clipboardIntent = new Intent(COPY_INTENT_FILTER);
        clipboardIntent.putExtra(CLIPBOARD_STRING, clipText);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, CLIPBOARD_REQUEST, clipboardIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        return pendingIntent;
    }

    public static String getTime(long timeStamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp);

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);

        return ((hour%12 != 0) ? (((hour%12) < 10 ? "0" : "") + (hour%12)) : "12")
                + ":"
                + ((minute < 10 ? "0" : "") + minute)
                + (hour < 12 ? "am" : "pm")
                + " "
                + day
                + " "
                + months[month];

    }

    public static String getValue(String s) {
        if(s != null && s.length() != 0)
            return s;
        else
            return "--";
    }
}
