package com.comsats.articular;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.sql.PreparedStatement;

public class DatabaseHelper extends SQLiteOpenHelper{
    Context context;
    public DatabaseHelper(Context context) {
        super(context,"Article_Database",null,1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            String query="CREATE TABLE article (id integer primary key autoincrement,author text," +
                                                "title text not null,description text,date_created text," +
                                                 "date_modified text,pic blob);";
            db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void saveData(){

        ContentValues contentValues=new ContentValues();
        contentValues.put("title","Shaper");
        contentValues.put("description","as");

        SQLiteDatabase db=getWritableDatabase();
        db.insert("article",null,contentValues);
        db.close();

    }


    public Bitmap retrieveData(){

        String qu = "select * from article where title=\"ta\"";
        SQLiteDatabase db=getReadableDatabase();
        Cursor cur = db.rawQuery(qu, null);

        if (cur.moveToFirst()){
            byte[] imgByte = cur.getBlob(6);
            cur.close();
            return BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
        }
        if (cur != null && !cur.isClosed()) {
            cur.close();
        }

        return null;


    }


    public void insertImg() {

        Bitmap img = BitmapFactory.decodeResource(context.getResources(), R.drawable.add);
        byte[] data = getBitmapAsByteArray(img); // this is a function

        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("pic",data);
        contentValues.put("title","ta");
        db.insert("article",null,contentValues);
        db.close();
        Toast.makeText(context, "Inserted SAuccesfully", Toast.LENGTH_SHORT).show();

    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }


}


