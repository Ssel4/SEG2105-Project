package com.example.walkinclinicsservicesapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Accounts.db";
    public static final String TABLE_NAME = "Account_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "EMAIL";
    public static final String COL_4 = "ROLE";
    public static final String COL_5 = " PASSWORD";

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table  "+TABLE_NAME+" (ID INT PRIMARY KEY AUTOINCREMENT, NAME TEXT, EMAIL TEXT, ROLE TEXT, PASSWORD TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public boolean createAccount(String name, String email, String role, String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, email);
        contentValues.put(COL_4, role);
        contentValues.put(COL_5, password);
        long alpha = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        if(alpha==-1){
            return false;
        }
        return true;
    }

    public Cursor getData(String word,String columnName){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor result= sqLiteDatabase.rawQuery("Select * FROM "+TABLE_NAME + " WHERE "+columnName+" LIKE '%"+word+"%'",null);
        return result;
    }

    public Cursor getSpecificAccountData(String word,String email, String role){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor result= sqLiteDatabase.rawQuery("Select * FROM "+TABLE_NAME + " WHERE NAME LIKE '%"+word+"%' AND EMAIL LIKE '%"+email+"%' AND ROLE LIKE '%"+role+"%'",null);
        return result;
    }


}
