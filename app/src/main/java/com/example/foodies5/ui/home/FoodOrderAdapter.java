package com.example.foodies5.ui.home;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodies5.R;

public class FoodOrderAdapter extends RecyclerView.ViewHolder {
    ImageView food_img;
    TextView food_text;

    public FoodOrderAdapter(@NonNull View itemView) {
        super(itemView);
        food_img=itemView.findViewById(R.id.food_img);
        food_text=itemView.findViewById(R.id.food_text);
    }

    public void setFoodDetails(Context context,String rest_name,String rest_img_url)
    {
        Glide.with(context).load(rest_img_url).into(food_img);
        food_text.setText(rest_name);
    }
}
