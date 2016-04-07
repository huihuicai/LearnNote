package com.note.learn.database;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by wanghui on 2016/4/7.
 */
public class PracticeTable {

    private static final String TABLE_NAME = "table_practice";
    public static final String COLUMN_ID = "practice_id";
    public static final String COLUMN_NAME = "practice_name";
    public static final String COLUMN_TIME = "practice_time";

    public static void createTable(SQLiteDatabase db) {
        StringBuilder builder = new StringBuilder();
        builder.append(DbUtil.SQL_CREATE_HEADER).append(TABLE_NAME).append(DbUtil.SQL_LEFT_BRACKET).append(COLUMN_ID).append(DbUtil.SQL_INTEGER_PRIMARY_KEY).append(DbUtil.SQL_COMMA_SYMBOL).append(COLUMN_NAME).append(DbUtil.SQL_TEXT).append(DbUtil.SQL_NOT_NULL).append(DbUtil.SQL_COMMA_SYMBOL).append(COLUMN_TIME).append(DbUtil.SQL_TEXT).append(DbUtil.SQL_NOT_NULL).append(DbUtil.SQL_RIGHT_BRACKET);
        db.execSQL(builder.toString());
    }
}
