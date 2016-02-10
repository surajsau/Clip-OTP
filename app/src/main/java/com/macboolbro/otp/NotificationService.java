package com.macboolbro.otp;

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

import com.macboolbro.otp.receiver.ClipboardReceiver;

/**
 * Created by MacboolBro on 09/02/16.
 */
public class NotificationService extends Service implements IConstants {

    private static final String TAG = NotificationService.class.getSimpleName();
    private ClipboardReceiver receiver;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        receiver = new ClipboardReceiver();
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String message = intent.getStringExtra(SMS_MESSAGE_NOTIFICATION_INTENT);

        //register Clipboard broadcast receiver..
        registerReceiver(receiver, new IntentFilter(COPY_INTENT_FILTER));

        if(message != null &&
                Util.otpFromMessage(message) != null &&
                Util.otpFromMessage(message).length() != 0)
            showHeadsUpNotification(Util.otpFromMessage(message), message);

        return START_STICKY;
    }

    private void showHeadsUpNotification(String otp, String message) {
        Log.d(TAG, "message: " + message);
        Log.d(TAG, "otp: " + otp);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setContentTitle(otp)
                .setContentText(message)
                .setPriority(Notification.PRIORITY_HIGH)
                .addAction(R.drawable.icon_copy, "Copy to clipboard",
                        Util.getClipboardPendingIntent(this, otp))
                .setSmallIcon(android.R.drawable.ic_dialog_map);

        Intent push = new Intent();
        push.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent fullScreenIntent = PendingIntent.getActivity(this, 0, push, PendingIntent.FLAG_CANCEL_CURRENT);

        builder.setFullScreenIntent(fullScreenIntent, true);

        NotificationManager mgr = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        mgr.notify(NOTIFICATION_ID, builder.build());

    }
}
