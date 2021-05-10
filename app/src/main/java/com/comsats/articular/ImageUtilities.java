package com.comsats.articular;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ImageUtilities {

    public static Bitmap compessBitmap(Context context, Uri uri, int IMAGE_SIZE) throws IOException {

        int quality = 100;
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);

        int byteArrayLength = Integer.MAX_VALUE;
        ByteArrayOutputStream bos = null;

        while((byteArrayLength/1000)>= IMAGE_SIZE){
            bos = new ByteArrayOutputStream();

            bitmap.compress(Bitmap.CompressFormat.WEBP,
                    quality,
                    bos);

            byteArrayLength = bos.toByteArray().length;
            quality-=10;

            Log.w("TAG","Image Size is now: "  + byteArrayLength+"");
        }
        try {
            byte[]bosBytes = bos.toByteArray();
            return BitmapFactory.decodeByteArray(bosBytes, 0, bosBytes.length);
        }catch (NullPointerException e){
            return bitmap;
        }
    }


    public static String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] arr=baos.toByteArray();
        String result= Base64.encodeToString(arr, Base64.DEFAULT);
        return result;
    }

    public static Bitmap StringToBitMap(String image){
        try{
            byte [] encodeByte=Base64.decode(image,Base64.DEFAULT);
            Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }catch(Exception e){
            e.getMessage();
            return null;
        }
    }




}
