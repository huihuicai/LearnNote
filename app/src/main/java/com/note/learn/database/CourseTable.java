package com.note.learn.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

/**
 * Created by wanghui on 2016/4/7.
 */
public class CourseTable {

    private static final String TABLE_NAME = "table_course";
    public static final String COLUMN_ID = "course_id";
    public static final String COLUMN_NAME = "course_name";

    public static void createTable(SQLiteDatabase db) {
        StringBuilder builder = new StringBuilder();
        builder.append(DbUtil.SQL_CREATE_HEADER).append(TABLE_NAME).append(DbUtil.SQL_LEFT_BRACKET).append(COLUMN_ID).append(DbUtil.SQL_INTEGER_PRIMARY_KEY).append(DbUtil.SQL_COMMA_SYMBOL).append(COLUMN_NAME).append(DbUtil.SQL_TEXT).append(DbUtil.SQL_NOT_NULL).append(DbUtil.SQL_RIGHT_BRACKET);
        db.execSQL(builder.toString());
    }

    public static boolean insertCourse(Context context, String name) {
        if (TextUtils.isEmpty(name)) {
            return false;
        }
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        SQLiteDatabase db = DBHelper.getDateBase(context);
        long row = db.update(TABLE_NAME, cv, COLUMN_NAME + "=?", new String[]{name});
        if (row > 0) {
            return false;
        } else {
            row = db.insert(TABLE_NAME, null, cv);
            return row > 0;
        }
    }
}
