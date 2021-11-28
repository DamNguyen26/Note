package com.example.mynote;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    static final String dbName = "MyDB";
    static final int dbVersion = 1;

    public DBHelper(Context context){
        super(context, dbName, null, dbVersion);
    }

    public static final String tblName = "notes";
    public static final String ID = "_id";
    public static final String IMPORTANT = "quantrong";
    public static final String DESC = "noidung";
    public static final String DATE = "ngaytao";

//    CREATE TABLE Notes ( id INTEGER PRIMARY KEY AUTOINCREMENT,quantrong INTEGER NOT NULL, title TEXT NOT NULL, description TEXT );
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTBL = "CREATE TABLE '" +tblName+ "' ( '" +ID+ "' INTEGER PRIMARY KEY AUTOINCREMENT , '" +IMPORTANT+ "' TEXT , '" +DESC+ "' TEXT NOT NULL , '" +DATE+ "' TEXT)";
        db.execSQL(createTBL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tblName);
        onCreate(db);
    }
}
