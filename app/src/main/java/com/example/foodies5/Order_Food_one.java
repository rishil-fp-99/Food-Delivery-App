package com.example.foodies5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.foodies5.ShivSagar.Shiv_Sagar_breakfast;
import com.example.foodies5.ShivSagar.Shiv_Sagar_breakfast_adapter;
import com.example.foodies5.ui.home.FoodOrderRest;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

public class Order_Food_one extends AppCompatActivity {

    DatabaseReference dref,dref_lunch,dref_snacks,dref_dinner,dref_vidharbha,dref_js,dref_sharma_bhelpuri,dref_gajalee;
    CardView shiv_sagarbkfast,shiv_sagarlunch,shiv_sagarsnacks,shiv_sagardinner;
    View view;
    RecyclerView food_menu_recview,vidarbha_recview,js_recview,sharma_bhel_puri_recview,gajalee_recview;
    CarouselView carouselView,js_carousel,carousel_sharma_bhel_puri,carousel_gajalee;
    private static final String TAG = "Order_Food_one";
    public static int counter_click_gajalee=0;
    Show_chosen_Items show_chosen_items=new Show_chosen_Items();
    FirebaseRecyclerAdapter<Show_chosen_Items,ShowItemBottomAdapter> itemadpater;
    DatabaseReference display_items;
    public  ElegantNumberButton myelegantNumberButton;

    public static   String append;


//   BottomSheetDialog show_myitems=new BottomSheetDialog(Order_Food_one.this);

    private int[] images =new int[]{
    R.drawable.vidarbha_vadapav_status,R.drawable.vidharbha_vadapav,R.drawable.samosa,R.drawable.onion_bhaji,R.drawable.potato_bhaji
    };
    private int[] js_images =new int[]
            {
                R.drawable.chilli_cheese_fries,R.drawable.chicken_magic_fries,R.drawable.chicken_popcorn_fries,
                    R.drawable.bbq_cheese_fries,R.drawable.nachos_fries,R.drawable.peri_peri_fries
            };

    private int[] sharma_bhelpuri_images=new int[]
            {
              R.drawable.bhel_puri,R.drawable.dahi_aloo_papdi_chaat,R.drawable.ragda_pattice,R.drawable.mung_bhel,R.drawable.sev_puri,
                    R.drawable.samosa_chaat
            };

    private int[] gajalee_images=new int[]
            {
              R.drawable.lobster_curry,R.drawable.chicken_tikka_masala,R.drawable.tandoori_pomfret,R.drawable.egg_curry,R.drawable.fish_kebab
            };


    @SuppressLint("InflateParams")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        food_menu_recview=findViewById(R.id.hotel_recview);
        dref=FirebaseDatabase.getInstance().getReference("Shiv_Sagar_breakfast");
        dref_lunch=FirebaseDatabase.getInstance().getReference("Shiv_Sagar_lunch");
        dref_snacks=FirebaseDatabase.getInstance().getReference("Shiv_Sagar_Snacks");
        dref_dinner=FirebaseDatabase.getInstance().getReference("Shiv_Sagar_Dinner");
        dref_vidharbha=FirebaseDatabase.getInstance().getReference("Vidarbha Vadapav");
        dref_js=FirebaseDatabase.getInstance().getReference("J's");
        dref_sharma_bhelpuri=FirebaseDatabase.getInstance().getReference("Sharma Bhel Puri");
        dref_gajalee=FirebaseDatabase.getInstance().getReference("Gajalee");
        display_items=FirebaseDatabase.getInstance().getReference("Show Items");
        try {
           Bundle bundle= getIntent().getExtras();
           String hotel_value= (String) Objects.requireNonNull(bundle).get("hotel_name");
            Toast.makeText(this, ""+hotel_value, Toast.LENGTH_SHORT).show();

           if(Objects.equals(hotel_value, "Shiv Sagar"))
           {
               Log.d("Order_Food_one","executed!");
               view=getLayoutInflater().inflate(R.layout.shiv_sagar_menu,null,false);
               setContentView(view);
               //breakfast

               shiv_sagarbkfast=view.findViewById(R.id.breakfast);
                shiv_sagarbkfast.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        FirebaseRecyclerOptions<Shiv_Sagar_breakfast> options=new FirebaseRecyclerOptions.Builder<Shiv_Sagar_breakfast>()
                                .setQuery(dref,Shiv_Sagar_breakfast.class).build();


                        FirebaseRecyclerAdapter<Shiv_Sagar_breakfast, Shiv_Sagar_breakfast_adapter> firebaseRecyclerAdapter=new
                                FirebaseRecyclerAdapter<Shiv_Sagar_breakfast, Shiv_Sagar_breakfast_adapter>(options) {
                                    @Override
                                    protected void onBindViewHolder(@NonNull Shiv_Sagar_breakfast_adapter holder, int position, @NonNull Shiv_Sagar_breakfast model) {
                                        holder.set_breakfast_details(Order_Food_one.this,model.getImgurl(),model.getItem_name(),model.getItem_price());
                                    }

                                    @NonNull
                                    @Override
                                    public Shiv_Sagar_breakfast_adapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                                        return new Shiv_Sagar_breakfast_adapter(LayoutInflater.from(Order_Food_one.this).inflate(R.layout.layout_breakfast_menu,parent,false));
                                    }
                                };
                        food_menu_recview=findViewById(R.id.hotel_recview);
                        food_menu_recview.setLayoutManager(new LinearLayoutManager(Order_Food_one.this));
                        firebaseRecyclerAdapter.startListening();
                        food_menu_recview.setAdapter(firebaseRecyclerAdapter);

                    }
                });
                //lunch
               shiv_sagarlunch=view.findViewById(R.id.lunch);
               shiv_sagarlunch.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       FirebaseRecyclerOptions<Shiv_Sagar_breakfast> options=new FirebaseRecyclerOptions.Builder<Shiv_Sagar_breakfast>()
                               .setQuery(dref_lunch,Shiv_Sagar_breakfast.class).build();


                       FirebaseRecyclerAdapter<Shiv_Sagar_breakfast, Shiv_Sagar_breakfast_adapter> firebaseRecyclerAdapter=new
                               FirebaseRecyclerAdapter<Shiv_Sagar_breakfast, Shiv_Sagar_breakfast_adapter>(options) {
                                   @Override
                                   protected void onBindViewHolder(@NonNull Shiv_Sagar_breakfast_adapter holder, int position, @NonNull Shiv_Sagar_breakfast model) {
                                       holder.set_breakfast_details(Order_Food_one.this,model.getImgurl(),model.getItem_name(),model.getItem_price());
                                   }

                                   @NonNull
                                   @Override
                                   public Shiv_Sagar_breakfast_adapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                                       return new Shiv_Sagar_breakfast_adapter(LayoutInflater.from(Order_Food_one.this).inflate(R.layout.layout_breakfast_menu,parent,false));
                                   }
                               };
                       food_menu_recview=findViewById(R.id.hotel_recview);
                       food_menu_recview.setLayoutManager(new LinearLayoutManager(Order_Food_one.this));
                       firebaseRecyclerAdapter.startListening();
                       food_menu_recview.setAdapter(firebaseRecyclerAdapter);

                   }
               });
               //snacks,dinner
               shiv_sagarsnacks=view.findViewById(R.id.snacks);
               shiv_sagarsnacks.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       FirebaseRecyclerOptions<Shiv_Sagar_breakfast> options=new FirebaseRecyclerOptions.Builder<Shiv_Sagar_breakfast>()
                               .setQuery(dref_snacks,Shiv_Sagar_breakfast.class).build();


                       FirebaseRecyclerAdapter<Shiv_Sagar_breakfast, Shiv_Sagar_breakfast_adapter> firebaseRecyclerAdapter=new
                               FirebaseRecyclerAdapter<Shiv_Sagar_breakfast, Shiv_Sagar_breakfast_adapter>(options) {
                                   @Override
                                   protected void onBindViewHolder(@NonNull Shiv_Sagar_breakfast_adapter holder, int position, @NonNull Shiv_Sagar_breakfast model) {
                                       holder.set_breakfast_details(Order_Food_one.this,model.getImgurl(),model.getItem_name(),model.getItem_price());
                                   }

                                   @NonNull
                                   @Override
                                   public Shiv_Sagar_breakfast_adapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                                       return new Shiv_Sagar_breakfast_adapter(LayoutInflater.from(Order_Food_one.this).inflate(R.layout.layout_breakfast_menu,parent,false));
                                   }
                               };
                       food_menu_recview=findViewById(R.id.hotel_recview);
                       food_menu_recview.setLayoutManager(new LinearLayoutManager(Order_Food_one.this));
                       firebaseRecyclerAdapter.startListening();
                       food_menu_recview.setAdapter(firebaseRecyclerAdapter);
                   }
               });

               shiv_sagardinner=view.findViewById(R.id.dinner);
               shiv_sagardinner.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       FirebaseRecyclerOptions<Shiv_Sagar_breakfast> options=new FirebaseRecyclerOptions.Builder<Shiv_Sagar_breakfast>()
                               .setQuery(dref_dinner,Shiv_Sagar_breakfast.class).build();


                       FirebaseRecyclerAdapter<Shiv_Sagar_breakfast, Shiv_Sagar_breakfast_adapter> firebaseRecyclerAdapter=new
                               FirebaseRecyclerAdapter<Shiv_Sagar_breakfast, Shiv_Sagar_breakfast_adapter>(options) {
                                   @Override
                                   protected void onBindViewHolder(@NonNull Shiv_Sagar_breakfast_adapter holder, int position, @NonNull Shiv_Sagar_breakfast model) {
                                       holder.set_breakfast_details(Order_Food_one.this,model.getImgurl(),model.getItem_name(),model.getItem_price());
                                   }

                                   @NonNull
                                   @Override
                                   public Shiv_Sagar_breakfast_adapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                                       return new Shiv_Sagar_breakfast_adapter(LayoutInflater.from(Order_Food_one.this).inflate(R.layout.layout_breakfast_menu,parent,false));
                                   }
                               };
                       food_menu_recview=findViewById(R.id.hotel_recview);
                       food_menu_recview.setLayoutManager(new LinearLayoutManager(Order_Food_one.this));
                       firebaseRecyclerAdapter.startListening();
                       food_menu_recview.setAdapter(firebaseRecyclerAdapter);
                   }
               });

           }
           else if(Objects.equals(hotel_value, "Rama Krishna"))
           {
                view=getLayoutInflater().inflate(R.layout.rama_krishna_menu,null,false);
               setContentView(view);
           }
           else if(Objects.equals(hotel_value, "Vidarbha VadaPav"))
           {
                view=getLayoutInflater().inflate(R.layout.vidarbhav_vadapav_menu,null,false);
                setContentView(view);
                setVidarbhaVadapavDetails(view);
           }
           else if(Objects.equals(hotel_value, "J's"))
           {
               view=getLayoutInflater().inflate(R.layout.js_details,null,false);
               setContentView(view);
               setJsDetails(view);
           }
           else if(Objects.equals(hotel_value, "Sharma Bhel Puri"))
           {
                view=getLayoutInflater().inflate(R.layout.sharma_bhel_puri,null,false);
                setContentView(view);
                set_Sharma_Bhel_puri_details(view);
           }
           else if(Objects.equals(hotel_value, "Gajalee"))
           {
               view=getLayoutInflater().inflate(R.layout.gajalee,null,false);
               setContentView(view);
               set_gajalee_details(view);
           }
        }catch (Exception e)
        {
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            Log.d("Order_Food_one", Objects.requireNonNull(e.getMessage()));
        }
    }


    private void setVidarbhaVadapavDetails(View view)
    {
        TextView menu_vidarbha=view.findViewById(R.id.menu_vidarbha);
        carouselView=view.findViewById(R.id.vidarbha_carousel_view);
        carouselView.setPageCount(images.length);
        carouselView.setAnimateOnBoundary(true);

        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(images[position]);
            }
        });

        FirebaseRecyclerOptions<Shiv_Sagar_breakfast> options=new FirebaseRecyclerOptions.Builder<Shiv_Sagar_breakfast>()
                .setQuery(dref_vidharbha,Shiv_Sagar_breakfast.class).build();


        FirebaseRecyclerAdapter<Shiv_Sagar_breakfast, Shiv_Sagar_breakfast_adapter> firebaseRecyclerAdapter=new
                FirebaseRecyclerAdapter<Shiv_Sagar_breakfast, Shiv_Sagar_breakfast_adapter>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull Shiv_Sagar_breakfast_adapter holder, int position, @NonNull Shiv_Sagar_breakfast model) {
                        holder.set_breakfast_details(Order_Food_one.this,model.getImgurl(),model.getItem_name(),model.getItem_price());
                    }

                    @NonNull
                    @Override
                    public Shiv_Sagar_breakfast_adapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        return new Shiv_Sagar_breakfast_adapter(LayoutInflater.from(Order_Food_one.this).inflate(R.layout.layout_breakfast_menu,parent,false));
                    }
                };
        vidarbha_recview=view.findViewById(R.id.vidarbha_recview);
        vidarbha_recview.setLayoutManager(new LinearLayoutManager(Order_Food_one.this));
        firebaseRecyclerAdapter.startListening();
        vidarbha_recview.setAdapter(firebaseRecyclerAdapter);

    }
    private void setJsDetails(View view) {
        js_carousel=view.findViewById(R.id.js_carousel);
        js_carousel.setPageCount(js_images.length);
        js_carousel.setAnimateOnBoundary(true);
        js_carousel.setSnap(true);
        js_carousel.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(js_images[position]);
            }
        });

        FirebaseRecyclerOptions<Shiv_Sagar_breakfast> options=new FirebaseRecyclerOptions.Builder<Shiv_Sagar_breakfast>()
                .setQuery(dref_js,Shiv_Sagar_breakfast.class).build();


        FirebaseRecyclerAdapter<Shiv_Sagar_breakfast, Shiv_Sagar_breakfast_adapter> firebaseRecyclerAdapter=new
                FirebaseRecyclerAdapter<Shiv_Sagar_breakfast, Shiv_Sagar_breakfast_adapter>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull Shiv_Sagar_breakfast_adapter holder, int position, @NonNull Shiv_Sagar_breakfast model) {
                        holder.set_breakfast_details(Order_Food_one.this,model.getImgurl(),model.getItem_name(),model.getItem_price());
                    }

                    @NonNull
                    @Override
                    public Shiv_Sagar_breakfast_adapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        return new Shiv_Sagar_breakfast_adapter(LayoutInflater.from(Order_Food_one.this).inflate(R.layout.layout_breakfast_menu,parent,false));
                    }
                };
        js_recview=view.findViewById(R.id.js_recview);
        js_recview.setLayoutManager(new LinearLayoutManager(Order_Food_one.this));
        firebaseRecyclerAdapter.startListening();
        js_recview.setAdapter(firebaseRecyclerAdapter);


    }

    public void set_Sharma_Bhel_puri_details(View view)
    {
        carousel_sharma_bhel_puri=view.findViewById(R.id.carousel_sharma_bhel_puri);
        carousel_sharma_bhel_puri.setPageCount(sharma_bhelpuri_images.length);
        carousel_sharma_bhel_puri.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                    imageView.setImageResource(sharma_bhelpuri_images[position]);
            }
        });

        FirebaseRecyclerOptions<Shiv_Sagar_breakfast> options=new FirebaseRecyclerOptions.Builder<Shiv_Sagar_breakfast>()
                .setQuery(dref_sharma_bhelpuri,Shiv_Sagar_breakfast.class).build();


        FirebaseRecyclerAdapter<Shiv_Sagar_breakfast, Shiv_Sagar_breakfast_adapter> firebaseRecyclerAdapter=new
                FirebaseRecyclerAdapter<Shiv_Sagar_breakfast, Shiv_Sagar_breakfast_adapter>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull Shiv_Sagar_breakfast_adapter holder, int position, @NonNull Shiv_Sagar_breakfast model) {
                        holder.set_breakfast_details(Order_Food_one.this,model.getImgurl(),model.getItem_name(),model.getItem_price());
                    }

                    @NonNull
                    @Override
                    public Shiv_Sagar_breakfast_adapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        return new Shiv_Sagar_breakfast_adapter(LayoutInflater.from(Order_Food_one.this).inflate(R.layout.layout_breakfast_menu,parent,false));
                    }
                };
        sharma_bhel_puri_recview=view.findViewById(R.id.sharma_recview);
        sharma_bhel_puri_recview.setLayoutManager(new LinearLayoutManager(Order_Food_one.this));
        firebaseRecyclerAdapter.startListening();
        sharma_bhel_puri_recview.setAdapter(firebaseRecyclerAdapter);

    }
    public void set_gajalee_details(final View view)
    {
        TextView tv1=view.findViewById(R.id.gajalee_tv);


        carousel_gajalee=view.findViewById(R.id.carousel_gajalee);
        carousel_gajalee.setPageCount(gajalee_images.length);
        carousel_gajalee.setAnimateOnBoundary(true);
        carousel_gajalee.setSnap(true);
        carousel_gajalee.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(gajalee_images[position]);
            }
        });


        FirebaseRecyclerOptions<Shiv_Sagar_breakfast> options=new FirebaseRecyclerOptions.Builder<Shiv_Sagar_breakfast>()
                .setQuery(dref_gajalee,Shiv_Sagar_breakfast.class).build();


        final FirebaseRecyclerAdapter<Shiv_Sagar_breakfast, Shiv_Sagar_breakfast_adapter> firebaseRecyclerAdapter=new
                FirebaseRecyclerAdapter<Shiv_Sagar_breakfast, Shiv_Sagar_breakfast_adapter>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull Shiv_Sagar_breakfast_adapter holder, int position, @NonNull Shiv_Sagar_breakfast model) {
                        holder.set_breakfast_details(Order_Food_one.this,model.getImgurl(),model.getItem_name(),model.getItem_price());
                    }

                    @NonNull
                    @Override
                    public Shiv_Sagar_breakfast_adapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View myview=LayoutInflater.from(Order_Food_one.this).inflate(R.layout.layout_breakfast_menu,parent,false);
                       final Button add_items=myview.findViewById(R.id.button_add);
                       final TextView item_name=myview.findViewById(R.id.item_name);
                       final TextView item_price=myview.findViewById(R.id.item_price);
                       final ElegantNumberButton enb=myview.findViewById(R.id.elegantNumberButton);
                        final CardView my_items=view.findViewById(R.id.display_shortcut_items);
                        final TextView set_item_count=view.findViewById(R.id.no_of_items);//
                        final TextView total_price=view.findViewById(R.id.total_price);//
                        Button view_cart=view.findViewById(R.id.view_cart);
                        final Button clear_gajalee_item=view.findViewById(R.id.clear_cart);
                        final StringBuffer sb=new StringBuffer();
                       final Vector<String > user_choice_vector=new Vector<>();

                       setEnb(enb);

                        //We have collected the id's of item name ,item price,elegant butn.Now we have to add to gajalee.xml file.
                     //   final int[] counter_click = {0};
                        final int[] new_ct = {0};
                        final int[] temp = {0};
                        final List<Integer> t_price=new ArrayList<>();
                        t_price.add(0);


                        enb.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
                            @Override
                            public void onValueChange(ElegantNumberButton view, final int oldValue, final int newValue) {

                                try {

                                    if (oldValue < newValue) {
                                        if (set_item_count.getText().toString().equals("")) {
                                            new_ct[0] = Integer.parseInt("0") + newValue;
                                        } else {
                                            new_ct[0] = Integer.parseInt(set_item_count.getText().toString()) + newValue;
                                        }
                                    }
                                    if (oldValue > newValue) {

                                        set_item_count.setText(String.valueOf(Integer.parseInt(set_item_count.getText().toString()) - Integer.parseInt(String.valueOf(1))));
                                        total_price.setText(String.valueOf(Integer.parseInt(total_price.getText().toString())-Integer.parseInt(item_price.getText().toString().substring(3))));
                                        if (Integer.parseInt(set_item_count.getText().toString()) < 0)
                                        {
                                            set_item_count.setText("");
                                        }
                                        if(Integer.parseInt(total_price.getText().toString())<0)
                                        {
                                            total_price.setText("");
                                        }

                                        display_items.removeValue();

                                    }

                                }catch (Exception e)
                                { }

                            }
                        });

                        add_items.setOnClickListener(new View.OnClickListener() {
                            @SuppressLint("RestrictedApi")
                            @Override
                            public void onClick(View v) {
                                counter_click_gajalee++;
                                if(counter_click_gajalee>0)
                                {
                                    Log.d(TAG, "Counter click "+ counter_click_gajalee);
                                    my_items.setVisibility(View.VISIBLE);
                                    if(!enb.getNumber().equals("0")) {
                                        set_item_count.setText(String.valueOf(new_ct[0]));
                                    }
                                    else{
                                        set_item_count.setText("0");
                                    }
                                    Log.d(TAG,"Item_name:"+item_name.getText().toString()+"\nItem Price"+item_price.getText().toString());
                                    //Toast.makeText(Order_Food_one.this, ""+"Item_name:"+item_name.getText().toString()+"\nItem Price"+item_price.getText().toString(), Toast.LENGTH_SHORT).show();

                                    //Here we will add the price to total_price
                                    Log.d(TAG,"before"+t_price.get(0).toString()) ;
                                    t_price.add(0,Integer.parseInt(item_price.getText().toString().substring(3)));
                                     Log.d(TAG,"After"+ t_price.get(0).toString());

                                    Log.d(TAG,"enb.getNumber()"+enb.getNumber());
                                    if(!total_price.getText().toString().equals("")) {
                                        Log.d(TAG,"Pre value:"+String.valueOf(Integer.parseInt(total_price.getText().toString()) + new_ct[0] * Integer.parseInt(String.valueOf(t_price.get(0)))));
                                        total_price.setText(String.valueOf(Integer.parseInt(total_price.getText().toString()) + Integer.parseInt(enb.getNumber())* Integer.parseInt(String.valueOf(t_price.get(0)))));
                                        //Log.d(TAG,"New ct value"+ Arrays.toString(new_ct)+"\nt_price.get(0)"+t_price.get(0));
                                      //  Toast.makeText(Order_Food_one.this, ""+"New ct value"+ Arrays.toString(new_ct)+"\nt_price.get(0)"+t_price.get(0), Toast.LENGTH_SHORT).show();

                                    }
                                    else {
                                        total_price.setText(String.valueOf(Integer.parseInt(enb.getNumber())*Integer.parseInt(item_price.getText().toString().substring(3))));
                                    }

                                    if(enb.getNumber().equals("0"))
                                    {
                                        t_price.add(0,0);
                                        total_price.setText("");
                                    }
                                    user_choice_vector.add(0,item_name.getText().toString());
                                    user_choice_vector.add(1,item_price.getText().toString());
                                    user_choice_vector.add(2,"Items:"+enb.getNumber());
                                    Log.d(TAG,"Vector"+user_choice_vector.get(0)+user_choice_vector.get(1)+user_choice_vector.get(2));
                                    Log.d(TAG,user_choice_vector.toString());

                                    show_chosen_items.setShow_item_name(item_name.getText().toString());
                                    show_chosen_items.setShow_item_count(enb.getNumber());
                                    show_chosen_items.setShow_item_price(item_price.getText().toString().substring(3));


                                    DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();

                                        display_items.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if(snapshot.exists())
                                                {
                                                    boolean check=true;
                                                    Toast.makeText(Order_Food_one.this, ""+"entered into if", Toast.LENGTH_SHORT).show();
                                                    Log.d(TAG,snapshot.toString());
                                                    String show_item_count=enb.getNumber();
                                                    String show_item_name=item_name.getText().toString();
                                                    String show_item_price=item_price.getText().toString().substring(3);
                                                    Iterator<DataSnapshot> dataSnapshotIterator=snapshot.getChildren().iterator();
                                                    while (dataSnapshotIterator.hasNext())
                                                    {
                                                        DataSnapshot child=dataSnapshotIterator.next();
                                                        String sic=child.child("show_item_count").getValue(String.class);
                                                        String sin=child.child("show_item_name").getValue(String.class);
                                                        String sip=child.child("show_item_price").getValue(String.class);
                                                        Log.d(TAG,"sic+sin+sip"+sic+sin+sip);

                                                        if(show_item_count.equals(sic) && show_item_name.equals(sin) && show_item_price.equals(sip))
                                                        {
                                                            check =false;
                                                            Log.d(TAG, String.valueOf(check));
                                                            break;
                                                        }

                                                    }
                                                    if(check)
                                                    {

                                                        display_items.child("").push().setValue(show_chosen_items).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                            @Override
                                                            public void onSuccess(Void aVoid) {
                                                                Toast.makeText(Order_Food_one.this, "Data inserted", Toast.LENGTH_SHORT).show();
                                                            }
                                                        }).addOnFailureListener(new OnFailureListener() {
                                                            @Override
                                                            public void onFailure(@NonNull Exception e) {
                                                            }
                                                        });
                                                    }


                                                }
                                                else {
                                                    Toast.makeText(Order_Food_one.this, ""+"entered into else", Toast.LENGTH_SHORT).show();
                                                    display_items.child("").push().setValue(show_chosen_items).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Toast.makeText(Order_Food_one.this, "Data inserted", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                        }
                                                    });
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                                Toast.makeText(Order_Food_one.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });

//                                    }
//                                    else {
//                                        Toast.makeText(Order_Food_one.this, ""+"entered into else", Toast.LENGTH_SHORT).show();
//
//                                        display_items.child("").push().setValue(show_chosen_items).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                            @Override
//                                            public void onSuccess(Void aVoid) {
//                                                Toast.makeText(Order_Food_one.this, "Data inserted", Toast.LENGTH_SHORT).show();
//                                            }
//                                        }).addOnFailureListener(new OnFailureListener() {
//                                            @Override
//                                            public void onFailure(@NonNull Exception e) {
//                                            }
//                                        });
//                                    }


                                    clear_gajalee_item.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            set_item_count.setText("");
                                            total_price.setText("");
                                            my_items.setVisibility(View.GONE);
                                            //3.
                                            enb.setNumber("0");
                                            display_items.removeValue();

                                        }
                                    });

                                }

                            }
                        });


                        view_cart.setOnClickListener(new View.OnClickListener() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onClick(View v) {
                                try {
                                    @SuppressLint("InflateParams") View bottom_view = getLayoutInflater().inflate(R.layout.bottom_sheet_dialog_cart, null, false);

                                    TextView set_total_price, set_total_price_including_gst;
                                    RecyclerView recyclerView = bottom_view.findViewById(R.id.show_item_recview);
                                    final CheckBox select_payment_method=bottom_view.findViewById(R.id.select_payment_method);
                                    recyclerView.setHasFixedSize(false);
                                    recyclerView.setLayoutManager(new LinearLayoutManager(Order_Food_one.this));

                                    recyclerView.setAdapter(itemadpater);
                                    BottomSheetDialog final_cart = new BottomSheetDialog(Order_Food_one.this);
                                     Button  place_order=bottom_view.findViewById(R.id.place_order);

                                    final_cart.setContentView(bottom_view);
                                    final_cart.setCanceledOnTouchOutside(true);

                                    String s = "Rs";
                                    set_total_price = final_cart.findViewById(R.id.bottom_total_price);

                                    set_total_price_including_gst = final_cart.findViewById(R.id.bottom_total_price_including_gst);

                                    Objects.requireNonNull(set_total_price).setText(s + " " + total_price.getText().toString());

                                    double temp_total_price_ = Integer.parseInt(total_price.getText().toString());
                                    temp_total_price_ = temp_total_price_ + (0.05 + 0.05) * temp_total_price_;

                                    Objects.requireNonNull(set_total_price_including_gst).setText(s + " " + temp_total_price_);

                                   select_payment_method.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                        @Override
                                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                            //show a dialog
                                           AlertDialog.Builder custom_payment_dialog=new AlertDialog.Builder(Order_Food_one.this);
                                            custom_payment_dialog.setView(R.layout.custom_payment_dialog);
                                            custom_payment_dialog.setTitle(R.string.Select_payment_method);
                                            final StringBuilder stringBuilder=new StringBuilder();


                                            custom_payment_dialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {

                                                    if(append.equals(""))
                                                    {
                                                        append="Select payment method";
                                                    }
                                                    select_payment_method.setText(append);
                                                    dialog.dismiss();

                                                }
                                            }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    select_payment_method.setChecked(false);
                                                    select_payment_method.setText("SELECT PAYMENT METHOD");
                                                    dialog.dismiss();

                                                }
                                            });
                                            AlertDialog alertDialog=custom_payment_dialog.create();
                                            alertDialog.setCanceledOnTouchOutside(false);
                                            alertDialog.show();


                                            final RadioButton credit=alertDialog.findViewById(R.id.credit);
                                            final RadioButton debit=alertDialog.findViewById(R.id.debit);
                                            final RadioButton cod=alertDialog.findViewById(R.id.cod);
                                            final RadioButton e_wallet=alertDialog.findViewById(R.id.e_wallet);

                                            credit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                @Override
                                                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                    if(isChecked) {
//                                                        stringBuilder.append(credit.getText().toString());
                                                        append=credit.getText().toString();

                                                        Toast.makeText(Order_Food_one.this, "" + credit.getText().toString(), Toast.LENGTH_SHORT).show();
                                                        Log.d(TAG,credit.getText().toString());
                                                    }
                                                }
                                            });
                                            debit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                @Override
                                                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                    if(isChecked) {
                                                       append=debit.getText().toString();

                                                        Toast.makeText(Order_Food_one.this, "" + debit.getText().toString(), Toast.LENGTH_SHORT).show();
                                                        Log.d(TAG,debit.getText().toString());

                                                    }

                                                    }
                                            });
                                            cod.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                @Override
                                                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                    if(isChecked) {
                                                       append=cod.getText().toString();
                                                        getStringBuilder(stringBuilder);
                                                        Toast.makeText(Order_Food_one.this, "" + cod.getText().toString(), Toast.LENGTH_SHORT).show();
                                                        Log.d(TAG,cod.getText().toString());
                                                    }
                                                    }
                                            });
                                            e_wallet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                @Override
                                                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                                                    if(isChecked) {
                                                     append=e_wallet.getText().toString();
                                                        getStringBuilder(stringBuilder);
                                                        Toast.makeText(Order_Food_one.this, "" + e_wallet.getText().toString(), Toast.LENGTH_SHORT).show();
                                                        Log.d(TAG,e_wallet.getText().toString());
                                                    }
                                                    }
                                            });



                                        }
                                    });

                                   place_order.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           if(!(select_payment_method.isChecked()))
                                           {
                                               Toast.makeText(Order_Food_one.this, "Select a payment method", Toast.LENGTH_SHORT).show();
                                           }
                                        else {
                                               startActivity(new Intent(Order_Food_one.this, thank_you_actvity.class));
                                           }

                                        }
                                   });

                                    final_cart.show();
                                }catch (Exception e){ }


                            }
                        });


                        return new Shiv_Sagar_breakfast_adapter(myview);

                    }
                };

        gajalee_recview=view.findViewById(R.id.gajalee_recview);
        gajalee_recview.setLayoutManager(new LinearLayoutManager(Order_Food_one.this));
        firebaseRecyclerAdapter.startListening();
        gajalee_recview.setAdapter(firebaseRecyclerAdapter);



    }

    private StringBuilder getStringBuilder(StringBuilder stringBuilder) {
        return stringBuilder;
    }

    private void setEnb(ElegantNumberButton enb) {
        this.myelegantNumberButton=enb;
    }


    @Override
    protected void onStart() {
        super.onStart();


      //1.
        display_items.removeValue();

        final FirebaseRecyclerOptions<Show_chosen_Items> myItemoptions=new FirebaseRecyclerOptions.Builder<Show_chosen_Items>()
                .setQuery(display_items,Show_chosen_Items.class).build();

        itemadpater=new FirebaseRecyclerAdapter<Show_chosen_Items, ShowItemBottomAdapter>(myItemoptions) {
            @Override
            protected void onBindViewHolder(@NonNull ShowItemBottomAdapter holder, int position, @NonNull Show_chosen_Items model) {
                holder.set_user_ordered_food_details( Order_Food_one.this,model.getShow_item_name(),model.getShow_item_count(),model.getShow_item_price());
            }

            @NonNull
            @Override
            public ShowItemBottomAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View myview=LayoutInflater.from(parent.getContext()).inflate(R.layout.show_items_on_bottom_sheet_dialog,parent,false);
                final CardView cardView=myview.findViewById(R.id.card_show_items_on_bottom);
                Button clear_bottom_sheet= myview.findViewById(R.id.clear_bottom);

                clear_bottom_sheet.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cardView.setVisibility(View.GONE);
                    //    2.
//                    display_items.removeValue();

                    }
                });

                
                return new ShowItemBottomAdapter(myview);
            }
        };
        itemadpater.startListening();


    }
}