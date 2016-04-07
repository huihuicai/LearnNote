package com.note.learn.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;

import com.note.learn.bean.CourseBean;

import java.util.ArrayList;

/**
 * Created by wanghui on 2016/4/7.
 */
public class CourseTable {

    private static final String TABLE_NAME = "table_course";
    public static final String COLUMN_ID = "course_id";
    public static final String COLUMN_NAME = "course_name";
    public static final String COLUMN_CHAPTER_NUM = "course_chapter_num";

    public static void createTable(SQLiteDatabase db) {
        StringBuilder builder = new StringBuilder();
        builder.append(DBUtil.SQL_CREATE_HEADER).append(TABLE_NAME).append(DBUtil.SQL_LEFT_BRACKET).append(COLUMN_ID).append(DBUtil.SQL_INTEGER_PRIMARY_KEY).append(DBUtil.SQL_COMMA_SYMBOL).append(COLUMN_NAME).append(DBUtil.SQL_TEXT).append(DBUtil.SQL_NOT_NULL).append(COLUMN_CHAPTER_NUM).append(DBUtil.SQL_INTEGER).append(DBUtil.SQL_DEFAULT).append(DBUtil.SQL_COMMA_SYMBOL).append(DBUtil.SQL_RIGHT_BRACKET);
        db.execSQL(builder.toString());
    }

    public static boolean insertCourse(Context context, CourseBean course) {
        if (course == null) {
            return false;
        }
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, course.getmCourseName());
        cv.put(COLUMN_CHAPTER_NUM, course.getmCourseChapterNum());
        SQLiteDatabase db = DBHelper.getDateBase(context);
        long row = db.update(TABLE_NAME, cv, COLUMN_NAME + "=?", new String[]{course.getmCourseName()});
        if (row > 0) {
            return false;
        } else {
            row = db.insert(TABLE_NAME, null, cv);
            return row > 0;
        }
    }

    public static ArrayList<CourseBean> getAllCourse(Context context) {
        SQLiteDatabase db = DBHelper.getDateBase(context);
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            ArrayList<CourseBean> courses = new ArrayList<>();
            int idIndex = cursor.getColumnIndex(COLUMN_ID);
            int nameIndex = cursor.getColumnIndex(COLUMN_NAME);
            int chapterNumIndex = cursor.getColumnIndex(COLUMN_CHAPTER_NUM);
            while (cursor.moveToNext()) {
                CourseBean item = new CourseBean();
                item.setmCourseId(cursor.getInt(idIndex));
                item.setmCourseName(cursor.getString(nameIndex));
                item.setmCourseChapterNum(cursor.getInt(chapterNumIndex));
                courses.add(item);
            }
            cursor.close();
            return courses;
        } else {
            return null;
        }
    }
}
