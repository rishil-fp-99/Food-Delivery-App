package com.example.foodies5.dataclasses;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.foodies5.R;
import com.example.foodies5.ShowWebActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//implement filterbale when there is no databse like option
public class DataSearchAdapter extends RecyclerView.Adapter< DataSearchAdapter.Inner_Search_Data> implements Filterable {

    HashMap<String,String> map_urls;
    FragmentActivity fragmentActivity;
    public ArrayList<Data_to_be_searched> restaurants=new ArrayList<>();
    ArrayList<String> arrayList;
    Context context;

    public DataSearchAdapter(Context context, ArrayList<Data_to_be_searched> restaurants, FragmentActivity fragmentActivity) {
        this.context = context;
        this.restaurants = restaurants;
        this.fragmentActivity=fragmentActivity;
    }

    //Creating a backup as well as main feed array
//    public ArrayList<Data_to_be_searched> rest_backup;

    public ArrayList<Data_to_be_searched> getRest() {
        return restaurants;
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    @NonNull
    @Override
    public Inner_Search_Data onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.mysearchdata,parent,false);
        return new Inner_Search_Data(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final Inner_Search_Data holder, final int position) {
        holder.name.setText(restaurants.get(position).getName());
        holder.details.setText("Click to view Details");
        Glide.with(holder.circularImageView.getContext()).load(restaurants.get(position).getImgurl()).into(holder.circularImageView);
        map_urls=new HashMap<>();
        arrayList=new ArrayList<>();
        holder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "Holder Name:"+holder.name.getText().toString()+"\nUrl"+restaurants.get(position).getWeb_url(), Toast.LENGTH_SHORT).show();
                arrayList.add(0,holder.name.getText().toString());
                arrayList.add(1,restaurants.get(position).getWeb_url());
                Intent intent=new Intent(fragmentActivity.getApplicationContext(), ShowWebActivity.class);
                intent.putExtra("Web Url",restaurants.get(position).getWeb_url());
                fragmentActivity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }
//    public void set_to_list_name(String name)
//    {
//        rest.add(name);
//        Log.d("DataSearchAdapter",rest.toString());
//    }


     static class Inner_Search_Data extends RecyclerView.ViewHolder{

        CircularImageView circularImageView;
        TextView name,details;

        public Inner_Search_Data(@NonNull View itemView) {
            super(itemView);
            circularImageView=itemView.findViewById(R.id.Img_Icon);
            name=itemView.findViewById(R.id.res_name);
            details=itemView.findViewById(R.id.view_details);
        }
    }
//      static class Give_Intent extends AppCompatActivity
//    {
//
//        @Override
//        public void startActivity(Intent intent) {
//            super.startActivity(intent);
//        }
//    }
}
