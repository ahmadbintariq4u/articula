package com.comsats.articular;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.dynamicanimation.animation.DynamicAnimation;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.comsats.articular.databinding.ActivityMainBinding;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.hanks.htextview.base.AnimationListener;
import com.hanks.htextview.base.HTextView;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);  // avoid from theme overriding

        // set the full screen for splash screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        binding.splashPerson.setImageResource(R.drawable.ic_splash_person);

        // set the animation to the main_title
        YoYo.with(Techniques.Wobble)
                .duration(1000)
                .repeat(3)
                .playOn(binding.splashTitleMain);

        // set the animation to the slogan.
        HTextView text=findViewById(R.id.splash_text);
        text.animateText("A Place Where You Write Your Article.");
        text.setAnimationListener(new AnimationListener() {
            @Override
            public void onAnimationEnd(HTextView hTextView) {
                //Toast.makeText(MainActivity.this, "Animation end", Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(MainActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }

        });

    }
} 