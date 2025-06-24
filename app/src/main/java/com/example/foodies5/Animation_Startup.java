package com.example.foodies5;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;


public class Animation_Startup extends AppCompatActivity {

  ///This activity will help us in object animator.
    ImageView food_delivery_anim;
    MediaPlayer mediaPlayer;
    ProgressBar pb;
    ObjectAnimator objectAnimator;
    static int count=0;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation__startup);

    }

  }