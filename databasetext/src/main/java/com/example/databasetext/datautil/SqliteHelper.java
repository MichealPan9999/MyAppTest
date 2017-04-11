package com.example.databasetext.datautil;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by panzq on 2017/4/11.
 */
public class SqliteHelper extends SQLiteOpenHelper {
    private static final String TAG = "panzq";
    //用来保存UserID、Access Token、Access Secret的表名
    public static final String TB_NAME = "users";

    public SqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " +
                TB_NAME + "(" +
                UserInfo.ID + " integer primary key," +
                UserInfo.USERID + " varchar," +
                UserInfo.TOKEN + " varchar," +
                UserInfo.TOKENSECRET + " varchar," +
                UserInfo.USERNAME + " varchar," +
                UserInfo.USERICON + " blob" +
                ")";
        db.execSQL(sql);
        Log.e(TAG, "onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS " + TB_NAME;
        db.execSQL(sql);
        onCreate(db);
        Log.e(TAG, "onUpgrade");
    }

    //更新列
    public void updateColumn(SQLiteDatabase db, String oldColumn, String newColumn, String typeColumn) {
        try {
            db.execSQL("ALTER TABLE " +
                    TB_NAME + " CHANGE " +
                    oldColumn + " " + newColumn +
                    " " + typeColumn
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
