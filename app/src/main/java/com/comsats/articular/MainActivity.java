package com.comsats.articular;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.hanks.htextview.base.HTextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        ImageView image=findViewById(R.id.splash_person);
        image.setImageResource(R.drawable.ic_splash_person);

        HTextView text=findViewById(R.id.splash_text);
        text.animateText("A Place Where You Write Your Article.");


        HTextView text2=findViewById(R.id.splash_title);
        text2.animateText("Articular");
        text2.setLinkTextColor(Color.parseColor("#ffffff"));


       // Toasty.error(this, "This is an error toast.", Toast.LENGTH_SHORT, true).show();

    }
} 