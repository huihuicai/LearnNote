package com.note.learn.database;

import android.content.ComponentName;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDiskIOException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wanghui on 2016/4/7.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String TAG = DBHelper.class.getSimpleName();
    private static final String DATEBASE_NAME = "LearnNote";
    private static final int mCurrentVersion = 1;
    private static volatile DBHelper mInstance;
    private static volatile SQLiteDatabase mDateBase;

    public DBHelper(Context context) {
        super(context, DATEBASE_NAME, null, mCurrentVersion);
    }

    public synchronized static SQLiteDatabase getDateBase(Context context) {
        synchronized (TAG) {
            if (mInstance == null || mDateBase == null) {
                if (mInstance == null) {
                    mInstance = new DBHelper(context);
                }
                try {
                    mDateBase = mInstance.getWritableDatabase();
                } catch (SQLiteDiskIOException e) {
                    e.printStackTrace();
                }
            }
            return mDateBase;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        PunchCardTable.createTable(db);
        CourseTable.createTable(db);
        CourseEpisodeTable.createTable(db);
        PracticeTable.createTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
