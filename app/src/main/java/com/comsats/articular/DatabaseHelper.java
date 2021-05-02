package com.comsats.articular;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

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


    public void insertData(ArrayList<String> data){

        ContentValues contentValues=new ContentValues();
        // pushing image.
        Bitmap img=null;
        try {
            img = MediaStore.Images.Media.getBitmap(context.getContentResolver(), Uri.parse(data.get(0)));
            contentValues.put("pic",getBitmapAsByteArray(img));
        } catch (IOException e) {
            contentValues.put("pic","");
        }

        contentValues.put("title",data.get(1));
        contentValues.put("author",data.get(2));
        contentValues.put("description",data.get(3));
        contentValues.put("date_created",data.get(4));
        contentValues.put("date_modified",data.get(5));

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


    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

}


