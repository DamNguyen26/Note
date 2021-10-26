package com.example.mynote;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbManager {
    private DBHelper dbHelper;
    private Context context;
    private SQLiteDatabase db;

    public dbManager(Context c){
        context = c;
    }
    public dbManager open() throws SQLException{
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        dbHelper.close();
    }
    public void insert(String name, String desc){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.subject, name);
        contentValues.put(DBHelper.desc, name);
        db.insert(DBHelper.tblName, null, contentValues);
    }
    public Cursor fetch(){
        String[] columns = new String[]{DBHelper.id, DBHelper.subject, DBHelper.desc};
        Cursor cursor = db.query(DBHelper.tblName, columns, null,null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public int update(long id, String name, String desc){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.subject, name);
        contentValues.put(DBHelper.desc, name);

        int i = db.update(DBHelper.tblName, contentValues, DBHelper.id + " = " + id, null);
        return i;
    }
    public void delete(long id){
        db.delete(DBHelper.tblName, DBHelper.id + " = " + id, null);
    }
}
