package com.example.foodies5.ui.videos_img;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.foodies5.R;
import com.example.foodies5.dataclasses.Sweets;
import com.example.foodies5.dataclasses.SweetsAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


public class video_img_fragment extends Fragment {
    View root;
   static DataSnapshot snapshot;
    CarouselView north_carousel,south_carousel,dessert_carousel,drinks_carousel;
    DatabaseReference north_dishes,south_dishes,sweet_dishes,drinks;
   HashMap<String,String> north=new HashMap<>();
   List<String> north_list =new ArrayList<>();
    List<String> south_list =new ArrayList<>();
    List<String> sweets_list =new ArrayList<>();
    List<String> drinks_list =new ArrayList<>();


    //    RecyclerView recNorthView,recSweetsView;
//    FirebaseRecyclerOptions<Sweets> sweetsOptions;
//    FirebaseRecyclerAdapter<Sweets,SweetsAdapter> sweetsFirebaseAdapter;
    private static final String TAG = "video_img_fragment";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         root= inflater.inflate(R.layout.fragment_video_img, container, false);




        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        listen_north_dishes();
        listen_south_dishes();
        listen_sweets();
        listen_drinks();


    }

    public void listen_north_dishes()
    {
       // Firebase recycler options will start listening for the events
        north_dishes= FirebaseDatabase.getInstance().getReference("Restaurant_Cusines").child("North_Dishes");
        north_carousel=root.findViewById(R.id.north_carousel);
        north_dishes.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                video_img_fragment.snapshot=snapshot;
                for(DataSnapshot dataSnapshot:snapshot.getChildren()) {

                        north.put(dataSnapshot.getKey(), Objects.requireNonNull(dataSnapshot.getValue()).toString());
                        for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                        {
                            north_list.add(Objects.requireNonNull(Objects.requireNonNull(dataSnapshot1.getValue()).toString()));

                        }

                    }
                Log.d(TAG, north_list.toString());
                //                Log.d(TAG, Objects.requireNonNull(north.get("video_name")));

                north_carousel.setImageListener(new ImageListener() {
                    @Override
                    public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageURI(Uri.parse(north_list.get(position)));
                        Glide.with(requireContext()).load(north_list.get(position)).into(imageView);
                    }
                });
                north_carousel.setPageCount(north_list.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    public  void listen_south_dishes()
    {
        south_carousel=root.findViewById(R.id.south_carousel);
        south_dishes= FirebaseDatabase.getInstance().getReference("Restaurant_Cusines").child("South_Dishes");

        south_dishes.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                video_img_fragment.snapshot=snapshot;
                for(DataSnapshot dataSnapshot:snapshot.getChildren()) {

                    north.put(dataSnapshot.getKey(), Objects.requireNonNull(dataSnapshot.getValue()).toString());
                    for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                    {
                        south_list.add(Objects.requireNonNull(Objects.requireNonNull(dataSnapshot1.getValue()).toString()));

                    }

                }
                south_carousel.setImageListener(new ImageListener() {
                    @Override
                    public void setImageForPosition(int position, ImageView imageView) {
                        Glide.with(requireContext()).load(south_list.get(position)).into(imageView);
                    }
                });
                south_carousel.setPageCount(south_list.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    public void listen_sweets()
    {
        sweet_dishes=FirebaseDatabase.getInstance().getReference("Restaurant_Cusines").child("Sweets");
        dessert_carousel=root.findViewById(R.id.desserts_carousel);

        sweet_dishes.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                video_img_fragment.snapshot=snapshot;
                for(DataSnapshot dataSnapshot:snapshot.getChildren()) {

                    north.put(dataSnapshot.getKey(), Objects.requireNonNull(dataSnapshot.getValue()).toString());
                    for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                    {
                        sweets_list.add(Objects.requireNonNull(Objects.requireNonNull(dataSnapshot1.getValue()).toString()));

                    }

                }
                dessert_carousel.setImageListener(new ImageListener() {
                    @Override
                    public void setImageForPosition(int position, ImageView imageView) {
                        Glide.with(requireContext()).load(sweets_list.get(position)).into(imageView);
                    }
                });
                dessert_carousel.setPageCount(sweets_list.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
    public void listen_drinks()
    {
        drinks=FirebaseDatabase.getInstance().getReference("Restaurant_Cusines").child("Drinks");
        drinks_carousel=root.findViewById(R.id.drinks_carousel);

        drinks.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                video_img_fragment.snapshot=snapshot;
                for(DataSnapshot dataSnapshot:snapshot.getChildren()) {
                    north.put(dataSnapshot.getKey(), Objects.requireNonNull(dataSnapshot.getValue()).toString());
                    for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                    {
                        drinks_list.add(Objects.requireNonNull(Objects.requireNonNull(dataSnapshot1.getValue()).toString()));
                    }
                }
               drinks_carousel.setImageListener(new ImageListener() {
                    @Override
                    public void setImageForPosition(int position, ImageView imageView) {
                        Glide.with(requireContext()).load(drinks_list.get(position)).into(imageView);
                    }
                });
                drinks_carousel.setPageCount(drinks_list.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }



}