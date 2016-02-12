package com.macboolbro.otp.activity;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.macboolbro.otp.AppPreference;
import com.macboolbro.otp.IConstants;
import com.macboolbro.otp.R;
import com.macboolbro.otp.adapter.OTPRecyclerAdapter;
import com.rey.material.widget.Switch;

public class MainActivity extends AppCompatActivity implements IConstants,
        View.OnClickListener, Switch.OnCheckedChangeListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private FrameLayout flDIY;
    private FrameLayout flPrivacy;

    private Button btnOtp;
    private Switch swNotifications;
    private TextView tvHelper;
    private TextView tvSender;
    private TextView tvMessage;
    private Button btnOtpCopy;

    private AppPreference preference;
    private boolean isNotificationEnabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initResources();
        setupToolbar();

        checkForSmsPermissions();

        setOnClickListeners();
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("OTP");
    }

    private void initResources() {
        preference = new AppPreference(this);
        isNotificationEnabled = preference.getBoolean(PREF_NOTIFICATION_ENABLED, false);

        flDIY = (FrameLayout) findViewById(R.id.llDIY);
        flPrivacy = (FrameLayout) findViewById(R.id.llPrivacy);

        btnOtp = (Button) findViewById(R.id.btnOtp);
        btnOtpCopy = (Button) findViewById(R.id.btnCopyOtp);
        swNotifications = (Switch) findViewById(R.id.swNotifications);

        swNotifications.setChecked(isNotificationEnabled);

        tvHelper = (TextView) findViewById(R.id.tvHelper);
        tvMessage = (TextView) findViewById(R.id.tvSmsBody);
        tvSender = (TextView) findViewById(R.id.tvSender);

    }

    private void setOnClickListeners() {
        flDIY.setOnClickListener(this);
        flPrivacy.setOnClickListener(this);
    }

    private void checkForSmsPermissions() {
        int smsPermissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS);

        if(smsPermissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS},
                    SMS_PERMISSIONS);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llDIY:
                startSmsListingActivity();
                break;
            case R.id.llPrivacy:
                startActivity(new Intent(this, SmsListingActivity.class));
                break;

            case R.id.btnCopyOtp:
            case R.id.btnOtp: {

            }
            break;
        }
    }

    private void startSmsListingActivity() {
        Intent settingsIntent = new Intent(this, SmsListingActivity.class);
        startActivity(settingsIntent);
    }

    @Override
    public void onCheckedChanged(Switch view, boolean checked) {
        setHelperText(checked);
        preference.putBoolean(IConstants.PREF_NOTIFICATION_ENABLED, checked);
    }

    private void setHelperText(boolean isNotificationEnabled) {
        if(isNotificationEnabled)
            tvHelper.setText("Turn off notification");
        else
            tvHelper.setText("Turn on notification");
    }

    private void copyToClipboard() {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText(IConstants.CLIP_DATA, btnOtp.getText().toString());
        clipboard.setPrimaryClip(clip);

        Toast.makeText(this, "OTP has been copied", Toast.LENGTH_SHORT).show();
    }
}