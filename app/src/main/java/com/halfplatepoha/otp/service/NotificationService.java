package com.halfplatepoha.otp.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.halfplatepoha.otp.util.AppPreference;
import com.halfplatepoha.otp.util.IConstants;
import com.halfplatepoha.otp.R;
import com.halfplatepoha.otp.util.Util;
import com.halfplatepoha.otp.receiver.ClipboardReceiver;

/**
 * Created by MacboolBro on 09/02/16.
 */
public class NotificationService extends Service implements IConstants {

    private static final String TAG = NotificationService.class.getSimpleName();
    private ClipboardReceiver receiver;

    private AppPreference preference;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        preference = new AppPreference(this);

        receiver = new ClipboardReceiver();

        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        boolean isNotificationEnabled = preference.getBoolean(PREF_NOTIFICATION_ENABLED, false);



        if(intent != null) {
            String timeStamp = intent.getStringExtra(SMS_TIME_STAMP);
            String message = intent.getStringExtra(SMS_MESSAGE_NOTIFICATION_INTENT);
            String sender = intent.getStringExtra(SMS_MESSAGE_SENDER);

            //register Clipboard broadcast receiver..
            registerReceiver(receiver, new IntentFilter(COPY_INTENT_FILTER));

            if (message != null && Util.otpFromMessage(message) != null
                    && Util.otpFromMessage(message).length() != 0
                    && isNotificationEnabled) {

                //clear preferences to prevent OutOfMemoryException..
                preference.clearPreferences();
                preference.putBoolean(PREF_NOTIFICATION_ENABLED, isNotificationEnabled);

                preference.putString(PREF_SMS_OTP, Util.otpFromMessage(message));
                preference.putString(PREF_SMS_MESSAGE, message);
                preference.putString(PREF_SMS_SENDER, sender);
                preference.putString(PREF_SMS_TIME, timeStamp);
                showHeadsUpNotification(message, sender);

            }
        }

        return START_STICKY;
    }

    private void showHeadsUpNotification(String message, String sender) {
        Log.d(TAG, "sender: " + sender);
        Log.d(TAG, "otp: " + Util.otpFromMessage(message));

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setContentTitle(Util.otpFromMessage(message) + " - " + sender)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message.toString()))
                .setPriority(Notification.PRIORITY_HIGH)
                .addAction(R.drawable.icon_copy, "Copy to clipboard",
                        Util.getClipboardPendingIntent(this, Util.otpFromMessage(message)))
                .setSmallIcon(R.mipmap.ic_launcher);

        Intent push = new Intent();
        push.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent fullScreenIntent = PendingIntent.getActivity(this, 0, push, PendingIntent.FLAG_CANCEL_CURRENT);

        builder.setFullScreenIntent(fullScreenIntent, true);

        NotificationManager mgr = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        mgr.notify(NOTIFICATION_ID, builder.build());

    }
}
