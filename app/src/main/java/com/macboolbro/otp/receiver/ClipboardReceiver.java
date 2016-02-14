package com.macboolbro.otp.receiver;

import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.macboolbro.otp.IConstants;

/**
 * Created by MacboolBro on 08/02/16.
 */
public class ClipboardReceiver extends BroadcastReceiver implements IConstants {

    private static final String TAG = ClipboardReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra(CLIPBOARD_STRING);

        Log.d(TAG, "message: " + message);
        Toast.makeText(context, "OTP has been copied", Toast.LENGTH_SHORT).show();

        ClipboardManager mgr = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(CLIP_DATA, message);
        mgr.setPrimaryClip(clip);
    }
}
