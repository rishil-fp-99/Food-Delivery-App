
package com.example.foodies5.dataclasses;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodies5.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;
import java.util.HashMap;

public class SweetsAdapter extends RecyclerView.ViewHolder {
    private static final String TAG = "SweetsAdapter";
    ImageView sweets_imgview;
    TextView sweets_name;
    public SweetsAdapter(@NonNull View itemView) {
        super(itemView);

    }
    public void setSweetDetails(Context context,String sweet_name,String sweet_img_url)
    {
        sweets_name=itemView.findViewById(R.id.sweets_name);
        sweets_imgview=itemView.findViewById(R.id.sweets_imgview);
        sweets_name.setText(sweet_name);
        Glide.with(context).load(sweet_img_url).into(sweets_imgview);
    }

//
    }






















































































































//    public ArrayList<Sweets> sweetsArrayList;
//
//    public SweetsAdapter(ArrayList<Sweets> sweetsArrayList) {
//        this.sweetsArrayList = sweetsArrayList;
//    }
//
//    @NonNull
//    @Override
//    public SweetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return new SweetViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.sweets, parent, false));
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull SweetViewHolder holder, int position) {
//        holder.sweets_name.setText(sweetsArrayList.get(position).getSweet_name());
//        Glide.with(holder.sweets_img.getContext()).load(sweetsArrayList.get(position).getImage_url()).into(holder.sweets_img);
//    }
//
//    @Override
//    public int getItemCount() {
//        return sweetsArrayList.size();
//    }
//
//
//    public static class SweetViewHolder extends RecyclerView.ViewHolder {
//
//    ImageView sweets_img;
//    TextView sweets_name;
//
//        public SweetViewHolder(@NonNull View itemView) {
//            super(itemView);
//            sweets_name=itemView.findViewById(R.id.sweets_name);
//            sweets_img=itemView.findViewById(R.id.sweets_imgview);
//        }

