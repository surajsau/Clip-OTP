package com.macboolbro.otp.db;

/**
 * Created by MacboolBro on 11/02/16.
 */
public interface IDbConstants {
    String DATABASE_NAME = "otp.db";
    int DATABASE_VERSION = 1;

    String TABLE_OTP_NOTIFICATIONS = "table_otp_notifications";
    String _ID = "_id";
    String KEY_OTP = "key_otp";
    String KEY_MESSAGE = "key_message";
    String KEY_SENDER = "key_sender";

    int _ID_COL = 0;
    int OTP_COL = 1;
    int MESSAGE_COL = 2;
    int SENDER_COL = 3;
}
