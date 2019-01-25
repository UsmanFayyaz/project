package com.example.abdulbasit.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class sqlite extends SQLiteOpenHelper {

    public static final String databaseName = "alarm.db";
    public static final String tableName = "data";
    public static final String col1 = "id";
    public static final String col2 = "title";
    public static final String col3 = "date";
    public static final String col4 = "time";


    public sqlite(Context context) {
        super(context, databaseName, null, 1);
        SQLiteDatabase db = this.getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQLString = "create table " + tableName +
                "("
                + col1 + " integer primary key autoincrement, "
                + col2 + " Text, "
                + col3 + " Text, "
                + col4 + " Text" +
                ")";
        db.execSQL(SQLString);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + tableName);
    }

    public boolean insertData(String title, String date, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(col2, title);
        content.put(col3, date);
        content.put(col4, time);

        long result = db.insert(tableName, null, content);

        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean updateData(String id, String title, String date, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(col1, id);
        content.put(col2, title);
        content.put(col3, date);
        content.put(col4, time);
        db.update(tableName, content, "id = ?", new String[]{id});
        return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from " + tableName, null);
    }

    public Integer deleteData(String title) {                                                          //Integar is a class not a datatype
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(tableName, "title = ?", new String[]{title});
    }
}
