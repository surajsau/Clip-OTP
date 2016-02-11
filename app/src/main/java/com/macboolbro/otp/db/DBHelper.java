package com.macboolbro.otp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by MacboolBro on 11/02/16.
 */
public class DBHelper extends SQLiteOpenHelper implements IDbConstants {

    private static DBHelper mInstance;

    private static final String TAG = DBHelper.class.getSimpleName();

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_OTP_NOTIFICATIONS + "("
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_OTP + " TEXT NOT NULL, "
            + KEY_MESSAGE + " TEXT, "
            + KEY_SENDER + " TEXT "
            + ")";

    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " +  TABLE_OTP_NOTIFICATIONS;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, CREATE_TABLE);

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, DROP_TABLE);

        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public static synchronized DBHelper getInstance(Context context) {
        if(mInstance == null)
            mInstance = new DBHelper(context);
        return mInstance;
    }
}
