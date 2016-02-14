package com.halfplatepoha.otp.activity;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.halfplatepoha.otp.util.AppPreference;
import com.halfplatepoha.otp.util.IConstants;
import com.halfplatepoha.otp.R;
import com.halfplatepoha.otp.util.Util;
import com.rey.material.widget.Switch;

public class MainActivity extends AppCompatActivity implements IConstants,
        View.OnClickListener, Switch.OnCheckedChangeListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private FrameLayout flPrivacy;
    private FrameLayout flAppTour;

    private Button btnOtp;
    private Switch swNotifications;
    private TextView tvHelper;
    private TextView tvSender;
    private TextView tvMessage;
    private TextView tvTimeStamp;
    private Button btnOtpCopy;
    private ImageButton btnTwitter;
    private ImageButton btnLinkedin;
    private ImageButton btnGithub;

    private AppPreference preference;
    private boolean isNotificationEnabled;
    private String otp;
    private String message;
    private String sender;
    private String timeStamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getDataFromPreference();

        initResources();
        setupToolbar();

        checkForSmsPermissions();

        setOnClickListeners();
    }

    private void getDataFromPreference() {
        preference = new AppPreference(this);

        isNotificationEnabled = preference.getBoolean(PREF_NOTIFICATION_ENABLED, false);
        timeStamp = preference.getString(PREF_SMS_TIME, "");
        sender = preference.getString(PREF_SMS_SENDER, "");
        message = preference.getString(PREF_SMS_MESSAGE, "");
        otp = preference.getString(PREF_SMS_OTP, "");
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(getString(R.string.app_name));
    }

    private void initResources() {
        flPrivacy = (FrameLayout) findViewById(R.id.llPrivacy);
        flAppTour = (FrameLayout) findViewById(R.id.llAppTour);

        btnOtp = (Button) findViewById(R.id.btnOtp);
        btnOtpCopy = (Button) findViewById(R.id.btnCopyOtp);
        swNotifications = (Switch) findViewById(R.id.swNotifications);

        btnGithub = (ImageButton) findViewById(R.id.btnGithub);
        btnLinkedin = (ImageButton) findViewById(R.id.btnLinkedin);
        btnTwitter = (ImageButton) findViewById(R.id.btnTwitter);

        swNotifications.setChecked(isNotificationEnabled);

        tvHelper = (TextView) findViewById(R.id.tvHelper);
        tvMessage = (TextView) findViewById(R.id.tvSmsBody);
        tvSender = (TextView) findViewById(R.id.tvSender);
        tvTimeStamp = (TextView) findViewById(R.id.tvTimeStamp);

        populateCard();
    }

    private void setOnClickListeners() {
        flPrivacy.setOnClickListener(this);
        flAppTour.setOnClickListener(this);

        btnOtp.setOnClickListener(this);
        btnOtpCopy.setOnClickListener(this);
        btnTwitter.setOnClickListener(this);
        btnLinkedin.setOnClickListener(this);
        btnGithub.setOnClickListener(this);

        swNotifications.setOnCheckedChangeListener(this);
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
            case R.id.llPrivacy:
                startInstructionsActivity();
                break;

            case R.id.llAppTour:
                startIntroductionActivity();
                break;

            case R.id.btnCopyOtp:
            case R.id.btnOtp: {
                copyToClipboard();
            }
            break;

            case R.id.btnGithub:
                startGithub();
                break;

            case R.id.btnLinkedin:
                startLinkedIn();
                break;

            case R.id.btnTwitter:
                startTwitter();
                break;
        }
    }

    private void startInstructionsActivity() {
        Intent settingsIntent = new Intent(this, InstructionsActivity.class);
        startActivity(settingsIntent);
    }

    private void startIntroductionActivity() {
        Intent settingsIntent = new Intent(this, IntroductionActivity.class);
        settingsIntent.putExtra(IS_FROM_MAIN, true);
        startActivity(settingsIntent);
    }

    @Override
    public void onCheckedChanged(Switch view, boolean checked) {
        setHelperText(checked);
        preference.putBoolean(PREF_NOTIFICATION_ENABLED, checked);
    }

    @Override
    protected void onPause() {
        preference.putBoolean(PREF_NOTIFICATION_ENABLED, swNotifications.isChecked());
        super.onPause();
    }

    @Override
    protected void onResume() {
        isNotificationEnabled = preference.getBoolean(PREF_NOTIFICATION_ENABLED, false);
        swNotifications.setChecked(isNotificationEnabled);
        super.onResume();
    }

    private void setHelperText(boolean isNotificationEnabled) {
        if(isNotificationEnabled)
            tvHelper.setText("Turn off notification");
        else
            tvHelper.setText("Turn on notification");
    }

    private void copyToClipboard() {
        String otp = btnOtp.getText().toString();

        if(!otp.equals(getString(R.string.na))) {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText(IConstants.CLIP_DATA, otp);
            clipboard.setPrimaryClip(clip);

            Toast.makeText(this, "OTP has been copied", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "You've no OTP to copy", Toast.LENGTH_SHORT).show();
        }
    }

    private void startTwitter() {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=" + TWITTER_USERNAME)));
        }catch (Exception e) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/" + TWITTER_USERNAME)));
        }
    }

    private void startLinkedIn() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(LINKEDIN_URL)));
    }

    private void startGithub() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(GITHUB_URL)));
    }

    private void populateCard() {
        btnOtp.setText(Util.getValue(otp));
        tvSender.setText(Util.getValue(sender));
        tvMessage.setText(Util.getValue(message));
        tvTimeStamp.setText(Util.getValue(timeStamp));
    }
}