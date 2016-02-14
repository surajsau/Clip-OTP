package com.halfplatepoha.otp.util;

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

    String OTP = "otp";
    String PASSWORD = "password";
    String PASSCODE = "passcode";
    String PASSKEY = "passkey";
    String CODE = " code ";
    String KEY = " key ";
    String ONE_TIME_PASSWORD = "one time password";

    String ALL_POSSIBLE_PREFIX_SUFFIX = "(is|otp|password|key|code|CODE|KEY|OTP|PASSWORD)";

    // logic used...
    // ....KEYWORD XXXXXX KEYWORD.... or XXXXXX KEYWORD... or ....KEYWORD XXXXXX
    String OTP_STRING_REGEX = "("+ ALL_POSSIBLE_PREFIX_SUFFIX +".[0-9]{4,8}."+ ALL_POSSIBLE_PREFIX_SUFFIX +"?)|((^|)[0-9]{4,8}."+ ALL_POSSIBLE_PREFIX_SUFFIX +")";

    String OTP_REGEX = "[0-9]+";

    String PREF_NOTIFICATION_ENABLED = "notification_enabled";
    String PREF_SMS_MESSAGE = "sms_message";
    String PREF_SMS_OTP = "sms_otp";
    String PREF_SMS_SENDER = "sms_sender";
    String PREF_SMS_TIME = "sms_time";

    String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    String IS_FROM_MAIN = "is_from_main";
    String IS_APP_FIRST_TIME = "is_first_time";
}
