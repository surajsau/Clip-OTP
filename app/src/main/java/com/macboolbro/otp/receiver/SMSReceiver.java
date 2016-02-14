package com.macboolbro.otp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;

import com.macboolbro.otp.util.IConstants;
import com.macboolbro.otp.util.Util;
import com.macboolbro.otp.service.NotificationService;

/**
 * Created by MacboolBro on 08/02/16.
 */
public class SMSReceiver extends BroadcastReceiver implements IConstants {

    private static final String TAG = SMSReceiver.class.getSimpleName();
    private SmsMessage smsMessage;

    @Override
    public void onReceive(Context context, Intent intent) {

        //getting the most recent SmsMessage..
        if(intent != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                smsMessage = Telephony.Sms.Intents.getMessagesFromIntent(intent)[0];
            } else {
                Bundle bundle = intent.getExtras();

                if(bundle != null) {
                    Object[] pdus = (Object[])bundle.get(SMS_BUNDLE);
                    smsMessage = SmsMessage.createFromPdu((byte[])pdus[0]);
                }
            }
        }

        Log.d(TAG, "message: " + smsMessage.getMessageBody());

        Intent notificationIntent = new Intent(context, NotificationService.class);
        notificationIntent.putExtra(SMS_TIME_STAMP, Util.getTime(smsMessage.getTimestampMillis()));
        notificationIntent.putExtra(SMS_MESSAGE_SENDER, smsMessage.getDisplayOriginatingAddress());
        notificationIntent.putExtra(SMS_MESSAGE_NOTIFICATION_INTENT, smsMessage.getDisplayMessageBody());

        context.startService(notificationIntent);
    }

}
