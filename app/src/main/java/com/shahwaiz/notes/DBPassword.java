package com.shahwaiz.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBPassword extends SQLiteOpenHelper {
    public DBPassword (Context context) {
        super(context, "password.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create table password(title text, code text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("Drop table if exists password");
    }

    public boolean setPassword(String title,String password) {
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("title",title);
        contentValues.put("code",password);

        long result=DB.insert("password",null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public boolean checkPassword(String title,String password) {
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("title",title);
        contentValues.put("password",password);

        Cursor cursor = DB.rawQuery("select * from password where title = ? and code=?", new String[]{title,password});
        if(cursor.getCount()==1)
        {
            return true;
        }
        else
        {
            return false;
        }

    }





}
