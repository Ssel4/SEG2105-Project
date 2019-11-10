package com.example.walkinclinicsservicesapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "DB_version_1.db";

    public static final String TABLE_NAME = "Account_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "EMAIL";
    public static final String COL_4 = "ROLE";
    public static final String COL_5 = " PASSWORD";
    public static final String DB_CREATE_ACCOUNT ="CREATE TABLE " + TABLE_NAME + "("+ COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "+COL_2+" TEXT, "+COL_3+" TEXT, "+COL_4+" TEXT, "+COL_5+" TEXT"+")";


    public static final String TABLE_SERVICE = "Services";
    public static final String SERVICE_COL_1 = "ID";
    public static final String SERVICE_COL_2 = "SERVICE";
    public static final String SERVICE_COL_3 = "ROLE";
    public static final String DB_CREATE_SERVICE="CREATE TABLE " + TABLE_SERVICE + "("+ SERVICE_COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "+SERVICE_COL_2+" TEXT, "+SERVICE_COL_3+" TEXT "+")";

        //String CREATE_PRODUCTS_TABLE = "CREATE TABLE " + TABLE_PRODUCTS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_PRODUCTNAME + " TEXT," + COLUMN_SKU + " INTEGER" + ")";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE_ACCOUNT);
        db.execSQL(DB_CREATE_SERVICE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_SERVICE);
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

        sqLiteDatabase.close();

        if(alpha==-1){
            return false;
        }
        return true;
    }


    public boolean emailExist(String email){

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
//EMAIL like '%"+email+"%'";
        String query = "Select * FROM "+TABLE_NAME+" WHERE EMAIL like '%"+email+"%'";
        Cursor result = sqLiteDatabase.rawQuery(query,null);

        if( result.getCount() > 0){
            sqLiteDatabase.close();
            return true;
        }
        sqLiteDatabase.close();
        return false;
    }


    public boolean logInAccount(String name,String email, String role, String password){

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "Select * FROM "+TABLE_NAME+ " WHERE NAME like '%"+name+"%' AND EMAIL like '%"+email+"%' AND ROLE like '%"+role+"%'";
        Cursor result= sqLiteDatabase.rawQuery(query,null);

        boolean correctName=false;
        boolean correctEmail=false;
        boolean correctPassword=false;
        boolean correctRole=false;

        if(result.getCount()>0 ){
            while(result.moveToNext()){

                if(result.getString(1).equalsIgnoreCase(name) ){
                    correctName=true;
                }
                if(result.getString(2).equalsIgnoreCase(email)){
                    correctEmail = true;
                }
                if(result.getString(3).equalsIgnoreCase(role)){
                    correctPassword=true;
                }
                if(result.getString(4).equalsIgnoreCase( password)){
                    correctRole=true;
                }
            }
        }
        sqLiteDatabase.close();
        return (correctName && correctEmail && correctPassword && correctRole );
    }



    public boolean createService (String serviceName, String role){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(SERVICE_COL_2,serviceName);
        contentValues.put(SERVICE_COL_3,role);
        long alpha = db.insert(TABLE_SERVICE,null,contentValues);
        db.close();
        if(alpha==-1){
            return false;
        }
        return true;
    }

    public List<Service> getAllServices(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "Select * FROM "+TABLE_SERVICE;
        Cursor result = db.rawQuery(query,null);
        List<Service> services= new ArrayList<>();

        if(result.getCount()>0){
            while(result.moveToNext()){
                Service tmp = new Service(result.getString(1),result.getString(2));
                services.add(tmp);
            }
        }
        db.close();
        return services;
    }

    public void deleteService(String serviceName , String role){

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * FROM "+TABLE_SERVICE+" WHERE "+SERVICE_COL_2+" =\""+serviceName+"\" AND "+SERVICE_COL_3+" =\""+role+"\" ";
        Cursor result = db.rawQuery(query,null);
        if(result.moveToFirst()){
            String idString = result.getString(0);
            db.delete(TABLE_SERVICE,SERVICE_COL_1+" = "+idString,null);
            }
        db.close();
    }

    public void updateService(String serviceName1 , String role1, String serviceName2 , String role2){

        SQLiteDatabase db = this.getWritableDatabase();
        String query1 = "Select * FROM "+TABLE_SERVICE+" WHERE "+SERVICE_COL_2+" =\""+serviceName1+"\" AND "+SERVICE_COL_3+" =\""+role1+"\"";
        Cursor result = db.rawQuery(query1,null);

        if(result.moveToFirst()){
            String idString = result.getString(0);
            ContentValues cv = new ContentValues();
            cv.put(SERVICE_COL_2,serviceName2);
            cv.put(SERVICE_COL_3,role2);
            db.update(TABLE_SERVICE, cv,"ID="+idString ,null);
        }

        db.close();
    }

}

