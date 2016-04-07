package com.note.learn.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by wanghui on 2016/4/7.
 */
public class PunchCardTable implements Serializable {

    public static final String TABLE_NAME = "table_punch_card";
    public static final String COLUMN_ID = "punch_card_id";
    public static final String COLUMN_DATE = "punch_card_date";

    public static void createTable(SQLiteDatabase db) {
        StringBuilder builder = new StringBuilder();
        builder.append(DbUtil.SQL_CREATE_HEADER).append(TABLE_NAME).append(DbUtil.SQL_LEFT_BRACKET).append(COLUMN_ID).append(DbUtil.SQL_INTEGER_PRIMARY_KEY).append(DbUtil.SQL_COMMA_SYMBOL).append(COLUMN_DATE).append(DbUtil.SQL_TEXT).append(DbUtil.SQL_NOT_NULL).append(DbUtil.SQL_RIGHT_BRACKET);
        db.execSQL(builder.toString());
    }

    public static boolean insertPunchCard(Context context, String date) {
        if (TextUtils.isEmpty(date)) {
            return false;
        }
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_DATE, date);
        SQLiteDatabase db = DBHelper.getDateBase(context);
        long row = db.insert(TABLE_NAME, null, cv);
        return row > 0;
    }

    public static boolean queryPunchCard(Context context, String date) {
        if (TextUtils.isEmpty(date)) {
            return false;
        }
        SQLiteDatabase db = DBHelper.getDateBase(context);
        Cursor cursor = db.query(TABLE_NAME, null, COLUMN_DATE + "=?", new String[]{date}, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.close();
            return true;
        } else {
            return false;
        }
    }

    public static ArrayList<String> queryPunchCardList(Context context, String yearMonth) {
        if (TextUtils.isEmpty(yearMonth)) {
            return null;
        }
        String sql = "select * from " + TABLE_NAME + " while " + COLUMN_DATE + " like ?%";
        SQLiteDatabase db = DBHelper.getDateBase(context);
        Cursor c = db.rawQuery(sql, new String[]{yearMonth});
        if (c != null && c.getCount() > 0) {
            ArrayList<String> dateList = new ArrayList<>();
            int columnIndex = c.getColumnIndex(COLUMN_DATE);
            while (c.moveToNext()) {
                String date = c.getString(columnIndex);
                dateList.add(date);
            }
            return dateList;
        } else {
            return null;
        }
    }

}
