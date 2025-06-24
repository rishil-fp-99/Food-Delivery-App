package com.example.foodies5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    ImageView man_logo;
    TextView app_name,quote;
    Animation top,right_to_left,left_to_right;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //helps to remove the status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        man_logo=findViewById(R.id.man_logo);
        app_name=findViewById(R.id.app_name);
        quote=findViewById(R.id.quote);
        top= AnimationUtils.loadAnimation(this,R.anim.top_anim);
        left_to_right=AnimationUtils.loadAnimation(this,R.anim.left);
        right_to_left=AnimationUtils.loadAnimation(this,R.anim.right);

        man_logo.setAnimation(top);
        app_name.setAnimation(left_to_right);
        quote.setAnimation(right_to_left);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this,MainActivity.class));
                finish();
            }
        },5500);

    }
}