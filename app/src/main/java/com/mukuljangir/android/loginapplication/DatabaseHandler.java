package com.mukuljangir.android.loginapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UserDatabase";
    private static final String TABLE_NAME = "User";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COLUMN_EMAIL + " TEXT,"
            + COLUMN_PASSWORD + " TEXT" + ")";

    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DatabaseHandler(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
     db.execSQL(CREATE_USER_TABLE);

    }
    //upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
     db.execSQL(DROP_USER_TABLE,null);
     db.close();
    }

    //inserting data to database
    public boolean addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        //ContentValues used to storing data for some time
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL,user.getEmail());
        values.put(COLUMN_PASSWORD,user.getPassword());
        //put data to ContentValues class and insert to database
         long insertResult = db.insert(TABLE_NAME,null,values);
         if(insertResult == 1) {
             return false;
         }else  {
             return true;
         }
    }

   //Updating database
    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        //putting value to ContentValues class
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL,user.getEmail());
        values.put(COLUMN_PASSWORD,user.getPassword());
        //updating
        db.update(TABLE_NAME,values, COLUMN_ID + " =?",new String[] {String.valueOf(user.getId())});
        db.close();
    }
    //check user if exists or not
    public boolean checkEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,new String[] {COLUMN_ID},COLUMN_EMAIL + " = ?",new String[]{email},null,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        if(count > 0) {
            return true;
        }else {
            return false;
        }

    }
    //check password if exists or not
    public boolean checkPassword(String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,new String[] {COLUMN_PASSWORD},COLUMN_PASSWORD + " = ?",new String[]{password},null,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        if(count > 0) {
            return true;
        }else {
            return false;
        }

    }

}
