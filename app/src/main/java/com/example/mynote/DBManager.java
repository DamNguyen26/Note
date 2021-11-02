package com.example.mynote;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    private DBHelper dbHelper;
    private Context context;
    private SQLiteDatabase db;

    public DBManager(Context c){
        context = c;
    }
    public DBManager open() throws SQLException{
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        dbHelper.close();
    }
    public void insert(String noidung, String ngaytao){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.DESC, noidung);
        contentValues.put(DBHelper.DATE, ngaytao);
        db.insert(DBHelper.tblName, null, contentValues);
    }
    public Cursor fetch(){
        String[] columns = new String[]{DBHelper.ID, DBHelper.DESC, DBHelper.DATE};
        Cursor cursor = db.query(DBHelper.tblName, columns, null,null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public int update(long id, String noidung, String ngaytao){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.DESC, noidung);
        contentValues.put(DBHelper.DATE, ngaytao);

        int i = db.update(DBHelper.tblName, contentValues, DBHelper.ID + " = " + id, null);
        return i;
    }
    public void delete(long id){
        db.delete(DBHelper.tblName, DBHelper.ID + " = " + id, null);
    }
}
