package com.comsats.articular;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.comsats.articular.databinding.ActivityHomeBinding;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class HomeActivity extends AppCompatActivity {

    public ActivityHomeBinding binding;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        db=new DatabaseHelper(this);
        //db.insertImg();

        // set the listener to the FAB.
        binding.homeFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this, AddArticle.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        //Toast.makeText(this, "Item selected: "+item.getTitle(), Toast.LENGTH_SHORT).show();
        //Bitmap image=db.retrieveData();
        //binding.image.setImageBitmap(image);
        Toast.makeText(this, "123", Toast.LENGTH_SHORT).show();

        Intent i = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i,1);

        return true;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            //TODO: action


                binding.image.setImageURI(data.getData());


        }
        }



}
