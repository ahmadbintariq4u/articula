package com.comsats.articular;

import android.app.AlertDialog;
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

public class DatabaseHelper extends SQLiteOpenHelper {
    public Context context;

    public DatabaseHelper(Context context) {
        super(context, "Article_Database", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE article (id integer primary key autoincrement,author text," +
                "title text not null,description text,date_created text," +
                "date_modified text,pic blob);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int naewVersion) {

    }


    public void insertData(ArrayList<String> data) {

        try {
            ContentValues contentValues = new ContentValues();
            // pushing image.
            Bitmap img = null;
            try {
                img = MediaStore.Images.Media.getBitmap(context.getContentResolver(), Uri.parse(data.get(0)));
                contentValues.put("pic", getBitmapAsByteArray(img));

            } catch (Exception e) {
                img = BitmapFactory.decodeResource(context.getResources(), R.drawable.questionmark);
                contentValues.put("pic", getBitmapAsByteArray(img));
            }

            contentValues.put("title", data.get(1));
            contentValues.put("author", data.get(2));
            contentValues.put("description", data.get(3));
            contentValues.put("date_created", data.get(4));
            contentValues.put("date_modified", data.get(5));

            SQLiteDatabase db = getWritableDatabase();
            db.insert("article", null, contentValues);
            db.close();

            Toast.makeText(context, "Data inserted Successfully", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            // Toast.makeText(context, ""+e.getMessage()+" "+e.getStackTrace()[0]+"\n"+e.getStackTrace()[0], Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public ArrayList<String>[] recievedData = null;

    public ArrayList<String>[] retrieveData() {

        recievedData = null;

        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("select * from article", null);
        recievedData = new ArrayList[cursor.getCount()];
        while (cursor.moveToNext()) {

            recievedData[cursor.getPosition()] = new ArrayList<String>();
            recievedData[cursor.getPosition()].add(cursor.getString(1));  // author
            recievedData[cursor.getPosition()].add(cursor.getString(2));  // title
            recievedData[cursor.getPosition()].add(cursor.getString(3)); // description
            recievedData[cursor.getPosition()].add(cursor.getString(4)); // date_created
            recievedData[cursor.getPosition()].add(cursor.getString(5)); // date_modified
            if (cursor.getBlob(6) != null)
                recievedData[cursor.getPosition()].add(new String(cursor.getBlob(6))); // pic
            else
                recievedData[cursor.getPosition()].add(null); // pic
        }

        return recievedData;
    }


    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

}


