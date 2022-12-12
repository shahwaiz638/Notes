package com.shahwaiz.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
    public DBHelper( Context context) {
        super(context, "NoteDetail.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create table noteDetail(id integer,title text, description text,Currtime integer,updatedTime integer, uri text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("Drop table if exists NoteDetail ");
    }

    public void deleteAll()
    {
        SQLiteDatabase DB=this.getWritableDatabase();
        //SQLiteDatabase db = this.getWritableDatabase();
        // db.delete(TABLE_NAME,null,null);
        //db.execSQL("delete * from"+ TABLE_NAME);
        DB.execSQL("delete from "+ "noteDetail");
        DB.close();
    }

//    public void Truncate() {
//        long result=DB.delete("noteDetail","id",new String[] {null});
//
//    }

    public boolean insertData(int id,String title,String description,long Currtime,String uri)
    {
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("id",id);
        contentValues.put("title",title);
        contentValues.put("description",description);
        contentValues.put("Currtime",Currtime);
        contentValues.put("updatedTime",Currtime);
        contentValues.put("uri",uri);

        long result=DB.insert("noteDetail",null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public boolean UpdateData(int id,String oldtitle,String newtitle,String description,String uri) {
        long updated = System.currentTimeMillis();
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        Cursor cursor = DB.rawQuery("select * from noteDetail where title = ?", new String[]{oldtitle});

        if (cursor.getCount() > 0) {

            if (cursor.moveToFirst()) {
                do {
                    contentValues.put("Currtime", cursor.getString(4));
                    if (newtitle.length()<=0) {
                        newtitle = cursor.getString(2);
                    }
                    else
                    {
                        contentValues.put("title", newtitle);
                    }
                    if (description.length()<=0) {
                        description = cursor.getString(2);
                    }
                    else
                    {
                        contentValues.put("description", description);
                    }
                    if (uri.length()<=0) {
                        uri = cursor.getString(5);
                    }
                    else
                    {
                        contentValues.put("uri", uri);
                    }

                } while (cursor.moveToNext());
                contentValues.put("id", id);


                //contentValues.put("Currtime",cursor.getString(4));
                contentValues.put("updatedTime", updated);

                long result = DB.update("noteDetail", contentValues, "title=?", new String[]{oldtitle});
                if (result == -1)
                    return false;
                else
                    return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public boolean deleteData(int id,String title,String description,String time)
    {
        SQLiteDatabase DB=this.getWritableDatabase();

        Cursor cursor=DB.rawQuery("select * from noteDetail where title = ?",new String[] {title});

        if(cursor.getCount()>0)
        {
            long result=DB.delete("noteDetail","title",new String[] {title});
            if(result==-1)
                return false;
            else
                return true;
        }
        else
        {
            return false;
        }
    }

    public Cursor getData(int sort)
    {
        Cursor cursor = null;
        SQLiteDatabase DB=this.getWritableDatabase();

        if(sort==1)
        {
            cursor=DB.rawQuery("SELECT * FROM noteDetail ORDER BY title  DESC", new String[] {});
        }
        else if(sort==2)
        {
            cursor=DB.rawQuery("SELECT * FROM noteDetail ORDER BY Currtime  DESC", new String[] {});
        }
        else if(sort==3)
        {
            cursor=DB.rawQuery("SELECT * FROM noteDetail ORDER BY Currtime  ASC", new String[] {});
        }
        else if(sort==4)
        {
            cursor=DB.rawQuery("SELECT * FROM noteDetail ORDER BY updatedTime  DESC", new String[] {});
        }
        else if(sort==5)
        {
            cursor=DB.rawQuery("SELECT * FROM noteDetail ORDER BY updatedTime  ASC", new String[] {});
        }
        return cursor;
    }

    public Cursor searchData(String title)
    {
        SQLiteDatabase DB=this.getWritableDatabase();

        Cursor cursor=DB.rawQuery("select * from noteDetail where title=?",new String[] {title});
        return cursor;
    }

    public boolean deleteData(String title)
    {
        SQLiteDatabase DB=this.getWritableDatabase();

        //Cursor cursor=DB.rawQuery("delete from noteDetail where title=?",new String[] {title});

        long result=DB.delete("noteDetail","title" + "=?",new String[] {title});

        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
