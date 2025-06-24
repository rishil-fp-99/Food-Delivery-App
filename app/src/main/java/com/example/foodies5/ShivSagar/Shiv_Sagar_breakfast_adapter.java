package com.example.foodies5.ShivSagar;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodies5.R;
import com.mikhaellopez.circularimageview.CircularImageView;

public class Shiv_Sagar_breakfast_adapter extends RecyclerView.ViewHolder {
    CircularImageView cimgurl;
    TextView item_name,item_price;

    public Shiv_Sagar_breakfast_adapter(@NonNull View itemView) {
        super(itemView);
    }

    public void set_breakfast_details(Context context,String imgurl,String i_name,String i_price)
    {
        cimgurl=itemView.findViewById(R.id.circularImageView2);
        item_name=itemView.findViewById(R.id.item_name);
        item_price=itemView.findViewById(R.id.item_price);

        Glide.with(context).load(imgurl).into(cimgurl);
        item_name.setText(i_name);
        item_price.setText(i_price);

    }

}
