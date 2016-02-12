package com.macboolbro.otp;

/**
 * Created by MacboolBro on 08/02/16.
 */
public interface IConstants {
    int NOTIFICATION_ID = 1;
    int CLIPBOARD_REQUEST = 0;
    String SMS_MESSAGE_NOTIFICATION_INTENT = "sms_message_notification_intent";
    String SMS_MESSAGE_SENDER = "sms_message_sender";
    String COPY_INTENT_FILTER = "filter_copy_intent";
    String CLIPBOARD_STRING = "clipboard_string";
    String CLIP_DATA = "clip_data";

    String SMS_BUNDLE = "pdus";
    int SMS_PERMISSIONS = 101;

    String OTP_STRING_REGEX = "is.[0-9]{4,8}\\.";
    String OTP_REGEX = "[0-9]+";

    String SCREEN_TYPE = "screen_type";
    String SCREEN_DIY = "screen_diy";
    String SCREEN_PRIVACY = "screen_privacy";

    String NOTIFICATION_ENABLED = "notification_enabled";
}
