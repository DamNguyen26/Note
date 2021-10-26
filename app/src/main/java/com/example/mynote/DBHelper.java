package com.example.mynote;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String tblName = "Notes";
    static final String dbName = "MyDB";
    static final int dbVersion = 1;

    public static final String id = "id";
    public static final String subject = "subject";
    public static final String desc = "description";

    private static final String createTBL = "create table " +tblName+ "(" +id+ " integer primary key autoincrement, " +subject+ " text not null, " +desc+ "text);";

    public DBHelper(Context context){
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTBL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " +tblName);
        onCreate(db);
    }
}
