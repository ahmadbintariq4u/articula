package com.comsats.articular;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.comsats.articular.databinding.ActivityAddArticleBinding;

public class AddArticle extends AppCompatActivity {

    private ActivityAddArticleBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddArticleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_article_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this, "You click on " + item.getTitle(), Toast.LENGTH_SHORT).show();

        if (item.getTitle().equals("add")) {
            String title=binding.articleTitle.getText().toString();
            String description=binding.articleDescription.getText().toString();

            // Further add it into the Database.
        }


        return true;
    }
}