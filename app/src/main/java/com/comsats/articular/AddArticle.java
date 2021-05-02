package com.comsats.articular;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.comsats.articular.databinding.ActivityAddArticleBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AddArticle extends AppCompatActivity {

    private ActivityAddArticleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddArticleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.articlePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 1);
            }
        });
    }

    public String imageURI = null;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            //TODO: action
            if (data != null) {
                binding.articlePic.setImageURI(data.getData());
                imageURI = data.getData().toString();
            }
        } else {
            imageURI = null;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_article_menu, menu);
        return true;
    }


    public ArrayList<String> articleData;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Toast.makeText(this, "You click on " + item.getTitle(), Toast.LENGTH_SHORT).show();

        articleData = new ArrayList<String>();

        if (item.getTitle().equals("add")) {


            articleData.add(imageURI);  //0
            articleData.add(binding.articleTitle.getText().toString()); //1
            String author = binding.articleAuthor.getText().toString();

            // 2
            if (!author.equals(""))
                articleData.add(author);
            else
                articleData.add("Unknown.");

            articleData.add(binding.articleDescription.getText().toString()); //3
            String currentDateTime = new SimpleDateFormat("h:mm a dd/MM/yyyy", Locale.getDefault()).format(new Date());
            articleData.add(currentDateTime); //4
            articleData.add(currentDateTime); // new modified date.  //5

            // Further add it into the Database.
        }

        return true;
    }
}