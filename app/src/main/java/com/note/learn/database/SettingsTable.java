package com.note.learn.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.github.mikephil.charting.data.realm.base.RealmUtils;
import com.note.learn.bean.SettingBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanghui on 2016/4/19.
 */
public class SettingsTable {

    enum SettingType {
        restNotify, countTime, everydaySolidot, shieldAd
    }

    enum SettingStatus {
        close, open
    }

    private static final String TABLE_NAME = "table_setting";
    public static final String COLUMN_ID = "setting_id";
    public static final String COLUMN_SETTING_ID = "setting_id";
    public static final String COLUMN_SETTING_NAME = "setting_name";
    public static final String COLUMN_SETTING_STATUS = "setting_status";

    public static void createTable(SQLiteDatabase db) {
        StringBuilder builder = new StringBuilder();
        builder.append(DBUtil.SQL_CREATE_HEADER).append(TABLE_NAME).append(DBUtil.SQL_LEFT_BRACKET).append(COLUMN_ID).append(DBUtil.SQL_INTEGER_PRIMARY_KEY).append(DBUtil.SQL_COMMA_SYMBOL).append(COLUMN_SETTING_ID).append(DBUtil.SQL_INTEGER).append(DBUtil.SQL_NOT_NULL).append(DBUtil.SQL_COMMA_SYMBOL).append(COLUMN_SETTING_NAME).append(DBUtil.SQL_TEXT).append(DBUtil.SQL_NOT_NULL).append(DBUtil.SQL_COMMA_SYMBOL).append(COLUMN_SETTING_STATUS).append(DBUtil.SQL_INTEGER).append(DBUtil.SQL_NOT_NULL).append(DBUtil.SQL_RIGHT_BRACKET);
        db.execSQL(builder.toString());
    }

    public static boolean updateSettingStatus(Context context, SettingBean bean) {
        if (bean == null) {
            return false;
        }
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_SETTING_ID, bean.getmSettingId());
        cv.put(COLUMN_SETTING_NAME, bean.getmSettingName());
        cv.put(COLUMN_SETTING_STATUS, bean.getmSettingStatus());
        SQLiteDatabase db = DBHelper.getDateBase(context);
        long row = db.update(TABLE_NAME, cv, COLUMN_SETTING_ID + "=?", new String[]{String.valueOf(bean.getmSettingId())});
        if (row > 0) {
            return false;
        } else {
            row = db.insert(TABLE_NAME, null, cv);
            return row > 0;
        }
    }

    public static List<SettingBean> getSettingStatus(Context context) {
        SQLiteDatabase db = DBHelper.getDateBase(context);
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (c == null) {
            return null;
        }

        if (c.getCount() == 0) {
            c.close();
            return null;
        }

        List<SettingBean> allStatus = new ArrayList<>();
        SettingBean bean;
        int id = c.getColumnIndex(COLUMN_SETTING_ID);
        int name = c.getColumnIndex(COLUMN_SETTING_NAME);
        int status = c.getColumnIndex(COLUMN_SETTING_STATUS);
        while (c.moveToNext()) {
            bean = new SettingBean();
            bean.setmSettingId(c.getInt(id));
            bean.setmSettingName(c.getString(name));
            bean.setmSettingStatus(c.getInt(status));
            allStatus.add(bean);
        }
        if (!c.isClosed()) {
            c.close();
        }
        return allStatus;
    }
}
