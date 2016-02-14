package com.macboolbro.otp.util;

/**
 * Created by MacboolBro on 08/02/16.
 */
public interface IConstants {
    int NOTIFICATION_ID = 1;
    int CLIPBOARD_REQUEST = 0;
    String SMS_MESSAGE_NOTIFICATION_INTENT = "sms_message_notification_intent";
    String SMS_MESSAGE_SENDER = "sms_message_sender";
    String SMS_TIME_STAMP = "sms_time_stamp";
    String COPY_INTENT_FILTER = "filter_copy_intent";
    String CLIPBOARD_STRING = "clipboard_string";
    String CLIP_DATA = "clip_data";

    String TWITTER_USERNAME = "theRoshogulla";
    String LINKEDIN_URL = "https://www.linkedin.com/in/suraj-sau-5826784a";
    String GITHUB_URL = "https://github.com/surajsau";

    String SMS_BUNDLE = "pdus";
    int SMS_PERMISSIONS = 101;

    String OTP_STRING_REGEX = "is.[0-9]{4,8}\\.";
    String OTP_REGEX = "[0-9]+";

    String SCREEN_TYPE = "screen_type";
    String SCREEN_DIY = "screen_diy";
    String SCREEN_PRIVACY = "screen_privacy";

    String PREF_NOTIFICATION_ENABLED = "notification_enabled";
    String PREF_SMS_MESSAGE = "sms_message";
    String PREF_SMS_OTP = "sms_otp";
    String PREF_SMS_SENDER = "sms_sender";
    String PREF_SMS_TIME = "sms_time";

    String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
}
