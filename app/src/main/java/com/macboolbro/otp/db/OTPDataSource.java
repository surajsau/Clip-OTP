package com.macboolbro.otp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.macboolbro.otp.Util;

import java.util.ArrayList;

/**
 * Created by MacboolBro on 11/02/16.
 */
public class OTPDataSource implements IDbConstants {

    private static final String TAG = OTPDataSource.class.getSimpleName();

    private DBHelper dbHelper;
    private SQLiteDatabase db;

    private String[] allColums = {_ID, KEY_OTP, KEY_MESSAGE, KEY_SENDER};

    public OTPDataSource(Context context) {
        dbHelper = DBHelper.getInstance(context);
    }

    public void open() {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public OTPModel addOTPModel(String message, String sender) {
        ContentValues values = new ContentValues();
        values.put(KEY_OTP, Util.otpFromMessage(message));
        values.put(KEY_MESSAGE, message);
        values.put(KEY_SENDER, sender);

        long insertId = db.insert(TABLE_OTP_NOTIFICATIONS, null, values);

        Cursor cursor = getCursorFromId(insertId);
        cursor.moveToFirst();

        OTPModel model = cursorToModel(cursor);

        Log.d(TAG, "added: " + model.getMessage());
        return model;
    }

    public int getNumberOfRows() {
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_OTP_NOTIFICATIONS + ";", null);

        cursor.moveToFirst();

        Log.d(TAG, "number of rows: " + cursor.getInt(0));
        return cursor.getInt(0);
    }

    public void deleteAll() {
        db.execSQL("DELETE * FROM " + TABLE_OTP_NOTIFICATIONS);

        Log.d(TAG, "delete all");
    }

    public void deleteOTPModel(OTPModel model) {
        long id = model.getId();
        db.delete(TABLE_OTP_NOTIFICATIONS, _ID + " = " + id, null);

        Log.d(TAG, "delete model id: " + id + ", Message: " + model.getMessage());
    }

    public Cursor getCursorFromId(long id) {
        Cursor cursor = db.query(TABLE_OTP_NOTIFICATIONS, allColums,
                _ID + " = " + id, null, null, null, null);

        return cursor;
    }

    public ArrayList<OTPModel> getAllOTPModels() {
        ArrayList<OTPModel> models = new ArrayList<>();

        Cursor cursor = db.query(TABLE_OTP_NOTIFICATIONS, allColums, null, null, null, null, null, null);

        cursor.moveToFirst();

        while(!cursor.isAfterLast()) {
            OTPModel model = cursorToModel(cursor);
            models.add(model);

            cursor.moveToNext();
        }

        cursor.close();
        return models;
    }

    private OTPModel cursorToModel(Cursor cursor) {
        OTPModel model = new OTPModel();
        model.setId(cursor.getLong(_ID_COL));
        model.setOtp(cursor.getString(OTP_COL));
        model.setMessage(cursor.getString(MESSAGE_COL));
        model.setSender(cursor.getString(SENDER_COL));

        return model;
    }
}
