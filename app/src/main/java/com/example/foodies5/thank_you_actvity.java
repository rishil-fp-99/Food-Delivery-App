package com.example.foodies5;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RatingBar;
import android.widget.TextView;

public class thank_you_actvity extends AppCompatActivity {

    RatingBar ratingBar;
    TextView popup_text_view;
    Animation bottom_to_top;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you_actvity);
        ratingBar=findViewById(R.id.rating_bar);
        popup_text_view=findViewById(R.id.thank_you);

        ratingBar.setStepSize(1);
        ratingBar.setNumStars(5);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
              popup_text_view.setVisibility(View.VISIBLE);
                bottom_to_top= AnimationUtils.loadAnimation(thank_you_actvity.this,R.anim.bottom_to_top);
                popup_text_view.setAnimation(bottom_to_top);
              bottom_to_top.start();
            }
        });

    }
}