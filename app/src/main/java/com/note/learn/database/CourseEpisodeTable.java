package com.note.learn.database;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by wanghui on 2016/4/7.
 */
public class CourseEpisodeTable {

    private static final String TABLE_NAME = "table_course_episode";
    public static final String COLUMN_ID = "course_episode_id";
    public static final String COLUMN_COURSE_ID = "course_id";
    public static final String COLUMN_COURSE_NAME = "course_name";
    public static final String COLUMN_EPISODE_NAME = "course_episode_name";
    public static final String COLUMN_EPISODE_TIME = "course_episode_time";

    public static void createTable(SQLiteDatabase db) {
        StringBuilder builder = new StringBuilder();
        builder.append(DbUtil.SQL_CREATE_HEADER).append(TABLE_NAME).append(DbUtil.SQL_LEFT_BRACKET).append(COLUMN_ID).append(DbUtil.SQL_INTEGER_PRIMARY_KEY).append(DbUtil.SQL_COMMA_SYMBOL)
                .append(COLUMN_COURSE_ID).append(DbUtil.SQL_INTEGER).append(DbUtil.SQL_NOT_NULL).append(DbUtil.SQL_COMMA_SYMBOL).append(COLUMN_COURSE_NAME).append(DbUtil.SQL_TEXT).append(DbUtil.SQL_NOT_NULL).append(DbUtil.SQL_COMMA_SYMBOL)
                .append(COLUMN_EPISODE_NAME).append(DbUtil.SQL_TEXT).append(DbUtil.SQL_NOT_NULL).append(DbUtil.SQL_COMMA_SYMBOL)
                .append(COLUMN_EPISODE_TIME).append(DbUtil.SQL_INTEGER).append(DbUtil.SQL_NOT_NULL).append(DbUtil.SQL_RIGHT_BRACKET);
        db.execSQL(builder.toString());
    }
}
