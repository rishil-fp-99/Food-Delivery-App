package com.example.foodies5.ui.home;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodies5.Google_Maps;
import com.example.foodies5.Order_Food_one;
import com.example.foodies5.R;
import com.example.foodies5.SetMyLocation;
import com.example.foodies5.top_restaurants_details;
import com.example.foodies5.ui.home.recent_locations.recent_locations_adapter;
import com.example.foodies5.ui.home.recent_locations.recent_locations_model;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;
import com.tooltip.Tooltip;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class HomeFragment extends Fragment {



    RadioButton user_loc,set_loc;
    ImageView choose_delivery_address;
    Tooltip tooltip;
    EditText delivery_address;
    DatabaseReference databaseReference,dref_recent_location;
    RecyclerView home_recycler_view,recent_locations_recview;
    ArrayList<FoodOrderRest> al=new ArrayList<>();
    private static final int count_click=0;
    private HomeViewModel homeViewModel;
    CarouselView fast_food,top_restaurants;
    CardView anim_card_view;
    FirebaseRecyclerOptions<recent_locations_model> recent_options;
    FirebaseRecyclerAdapter<recent_locations_model, recent_locations_adapter> recent_firebseadapter;
//    Animation fadeIn,fadeOut;
//    AnimationSet animation;
    View root;
    BottomSheetDialog bottomSheetDialog;

    
    private int[] fast_food_images={
           R.drawable.burger,R.drawable.pizza_animate,R.drawable.sandwitch,R.drawable.meals,
            R.drawable.chat,R.drawable.drinks
    };

    private String[] names_of_fast_food={
      "Burger","Pizza","Sandwitch","Meals","Chat","Drinks"
    };
    private int[] top_res_images={
            R.drawable.macdonals,R.drawable.smokins_img,R.drawable.subway,
            R.drawable.naturals,R.drawable.fasos,R.drawable.burger_king,
            R.drawable.kfc
    };

    private String[] names_of_top_restaurants={
            "Macdonalds","Smokin Joe's","Subway","Naturals","Fasos","Burger King","KFC"
    };


    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
      root = inflater.inflate(R.layout.fragment_home, container, false);
        delivery_address=root.findViewById(R.id.select_delivery_address);
        dref_recent_location=FirebaseDatabase.getInstance().getReference("Recent Locations");

              anim_card_view=root.findViewById(R.id.animate_card_view);


//        fadeIn = new AlphaAnimation(0, 1);
//        fadeIn.setDuration(1500);
//
////        fadeOut = new AlphaAnimation(1, 0);
////        fadeOut.setStartOffset(1000);
////        fadeOut.setDuration(1500);
//
//        animation = new AnimationSet(true);
//        animation.addAnimation(fadeIn);
//       // animation.addAnimation(fadeOut);


//        google_maps=new Google_Maps();

        set_carosuel_view_for_top_restaurants();
        set_carousel_views_for_fast_food();

        choose_delivery_address=root.findViewById(R.id.choose_delivery_address);
        choose_delivery_address.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("InflateParams")
            @Override
            public void onClick(View v) {

//               if(count_click==0) {
//                   count_click=1;
//                   tooltip = new Tooltip.Builder(choose_delivery_address)
//                           .setText("Select your address").setTextColor(Color.BLUE)
//                           .setCornerRadius(9f).setGravity(Gravity.BOTTOM).setDismissOnClick(true)
//                           .setBackgroundColor(Color.YELLOW).setMaxWidth(180).show();
//
//                   tooltip.setOnClickListener(new OnClickListener() {
//                       @Override
//                       public void onClick(@NonNull Tooltip tooltip) {
//                           Intent intent = new Intent(getContext(), Google_Maps.class);
//                           startActivity(intent);
//                       }
//                   });
//               }
//              else
//               {
//
//                   tooltip.dismiss();
//                   count_click=0;
//               }




                user_loc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked)
                        {
                            Intent intent=new Intent(requireActivity(),Google_Maps.class);
                            startActivity(intent);

                            bottomSheetDialog.dismiss();
                        }
                    }
                });

                set_loc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked)
                        {
                            startActivity(new Intent(requireActivity(), SetMyLocation.class));
                            bottomSheetDialog.dismiss();
                        }
                    }
                });

                bottomSheetDialog.show();

            }
        });

        try {
            if (requireActivity().getIntent().getExtras() != null) {
                Bundle bun = requireActivity().getIntent().getExtras();
                if (bun != null) {
//                String value = (String) Objects.requireNonNull(bun).get("delivery_address");
                    int check_intent = (int) Objects.requireNonNull(bun).get("CHECK_INTENT");
                    //                delivery_address.setText(value);

                    if (check_intent == 0) {
                        String value = (String) Objects.requireNonNull(bun).get("delivery_address");
                        delivery_address.setText(value);
                        add_recent_locations_to_firebase();


                    } else if (check_intent == 1) {
                        String value = (String) Objects.requireNonNull(bun).get("set_loc");
                        delivery_address.setText(value);
                        add_recent_locations_to_firebase();

                    }

                }

            }
        }catch (Exception e)
        {
        }
        databaseReference= FirebaseDatabase.getInstance().getReference("Restaurant_Cusines").child("Display_Restaurants");
        home_recycler_view=root.findViewById(R.id.home_recycler_view);
        home_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
      //  home_recycler_view.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,true));
        return root;

    }

    @Override
    public void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                al.add(snapshot.getValue(FoodOrderRest.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        FirebaseRecyclerOptions<FoodOrderRest> menu_options=new FirebaseRecyclerOptions.Builder<FoodOrderRest>()
                .setQuery(databaseReference,FoodOrderRest.class).build();

        FirebaseRecyclerAdapter<FoodOrderRest,FoodOrderAdapter> firebaseRecyclerAdapter=new
                FirebaseRecyclerAdapter<FoodOrderRest, FoodOrderAdapter>(menu_options) {
                    @Override
                    protected void onBindViewHolder(@NonNull FoodOrderAdapter holder, int position, @NonNull FoodOrderRest model) {
                        holder.setFoodDetails(getContext(),model.getRest_name(),model.getRest_img_url());
                    }

                    @NonNull
                    @Override
                    public FoodOrderAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.home_recycler_view,parent,false);
                        Button view_button=view.findViewById(R.id.view_details_add_cart);
                        final TextView textView=view.findViewById(R.id.food_text);
                        view_button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                             String hotel_name=textView.getText().toString();
                             Intent intent=new Intent(requireActivity(), Order_Food_one.class);
                             intent.putExtra("hotel_name",hotel_name);
                             startActivity(intent);
                            }
                        });

                        return new FoodOrderAdapter(view);
                    }
        };
        firebaseRecyclerAdapter.startListening();
        firebaseRecyclerAdapter.notifyDataSetChanged();
        home_recycler_view.setAdapter(firebaseRecyclerAdapter);



        //adding recent locations

        if(dref_recent_location!=null) {
            recent_options = new FirebaseRecyclerOptions.Builder<recent_locations_model>()
                    .setQuery(dref_recent_location, recent_locations_model.class).build();


            recent_firebseadapter = new FirebaseRecyclerAdapter<recent_locations_model, recent_locations_adapter>(recent_options) {
                @Override
                protected void onBindViewHolder(@NonNull recent_locations_adapter holder, int position, @NonNull recent_locations_model model) {
                    holder.setRecentLocationDetails(root.getContext(), model.getRecent_location());
                }

                @NonNull
                @Override
                public recent_locations_adapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                    return new recent_locations_adapter(LayoutInflater.from(parent.getContext()).inflate(R.layout.recent_location_inflate_file, parent, false));
                }
            };

            bottomSheetDialog=new BottomSheetDialog(requireContext());
            bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog);
            bottomSheetDialog.setCanceledOnTouchOutside(true);
            user_loc=bottomSheetDialog.findViewById(R.id.continue_user_location);
            set_loc=bottomSheetDialog.findViewById(R.id.pick_my_location);
            recent_locations_recview=bottomSheetDialog.findViewById(R.id.recview_recent_locations);
            recent_firebseadapter.startListening();
            recent_locations_recview.setLayoutManager(new LinearLayoutManager(getContext()));
            recent_locations_recview.setAdapter(recent_firebseadapter);

        }
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        return super.onContextItemSelected(item);
    }



    public void set_carousel_views_for_fast_food()
    {
        fast_food=root.findViewById(R.id.home_fragment_fast_food_carousel);
        fast_food.setPageCount(fast_food_images.length);
        fast_food.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(fast_food_images[position]);
            }
        });

    }
    public  void set_carosuel_view_for_top_restaurants()
    {
        top_restaurants=root.findViewById(R.id.home_fragment_top_restaurants_carousel);
        top_restaurants.setPageCount(top_res_images.length);
        top_restaurants.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(top_res_images[position]);
            }
        });

        top_restaurants.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getContext(), ""+names_of_top_restaurants[position], Toast.LENGTH_SHORT).show();
                if(names_of_top_restaurants[position].equals("Macdonalds"))
                {

                }
                else if(names_of_top_restaurants[position].equals("Smokin Joe's"))
                {

                }
                else if(names_of_top_restaurants[position].equals("Subway"))
                {


                    setAnim_card_view();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent=new Intent(requireActivity(), top_restaurants_details.class);
                            startActivity(intent);

                         }
                    },1010);

                }
                else if(names_of_top_restaurants[position].equals("Naturals"))
                {

                }
                else if(names_of_top_restaurants[position].equals("Fasos"))
                {

                }
                else if(names_of_top_restaurants[position].equals("Burger King"))
                {

                }
                else{


                }


            }
        });

/*
   private String[] names_of_top_restaurants={
            "Macdonalds","Smokin Joe's","Subway","Naturals","Fasos","Burger King","KFC"
    };

 */

    }
    public void setAnim_card_view()
    {

        anim_card_view.setVisibility(View.VISIBLE);
        final ObjectAnimator left_to_right=ObjectAnimator.ofFloat(anim_card_view,"translationX",15f);
        left_to_right.setDuration(1000);
        left_to_right.start();
        left_to_right.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        anim_card_view.setVisibility(View.INVISIBLE);

                    }
                },1200);

            }
        });


    }

    public void add_recent_locations_to_firebase()
    {
        final recent_locations_model recent_locations_model=new recent_locations_model();

        if(!delivery_address.getText().toString().equals(""))
        {

            dref_recent_location.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists())
                    {
                        boolean check=true;
                        String my_delivery_address=delivery_address.getText().toString();
                        Iterator<DataSnapshot> dataSnapshotIterator=snapshot.getChildren().iterator();

                        while (dataSnapshotIterator.hasNext())
                        {
                            DataSnapshot child=dataSnapshotIterator.next();
                            String temp_delivery_address=child.child("recent_location").getValue(String.class);

                            if(my_delivery_address.equals(temp_delivery_address))
                            {
                                check=false;
                                break;
                            }
                        }
                        if(check)
                        {
                            recent_locations_model.setRecent_location(delivery_address.getText().toString());
                            dref_recent_location.push().setValue(recent_locations_model).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(root.getContext(), "Data inserted successfully", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(root.getContext(), "Error while inserting data", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }


                    }
                    else{
                        recent_locations_model.setRecent_location(delivery_address.getText().toString());
                        dref_recent_location.push().setValue(recent_locations_model).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(root.getContext(), "Data inserted successfully", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(root.getContext(), "Error while inserting data", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });





        }
    }




}