package com.example.foodies5;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.foodies5.ui.home.HomeFragment;
import com.example.foodies5.ui.profile.profile_fragment;
import com.example.foodies5.ui.search.SearchFragment;
import com.example.foodies5.ui.videos_img.video_img_fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class home_screen_btmnav extends AppCompatActivity {

    BottomNavigationView navView;
    @SuppressLint({"RestrictedApi", "InflateParams"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view=getLayoutInflater().inflate(R.layout.bottom_nav_activity,null);
        setContentView(view);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,new HomeFragment()).commit();
        navView= findViewById(R.id.nav_view);
        getSupportFragmentManager().beginTransaction().add(new HomeFragment(),"");
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment temp=null;
                switch (item.getItemId())
                {
                    case R.id.navigation_home:
                        temp =new HomeFragment();break;
                    case R.id.navigation_search:
                        temp=new SearchFragment();break;
                    case R.id.navigation_profile:
                        temp=new profile_fragment();break;
                    case R.id.navigation_videos_img:
                        temp=new video_img_fragment();break;

                }
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,temp).commit();
                return true;
            }
        });


    }

}


// Passing each menu ID as a set of Ids because each
// menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_search, R.id.navigation_profile,
//                R.id.navigation_videos_img)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//
//        try {
////            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//
//        }catch (Exception e)
//        {
//            Log.d("home_screen_btmnav",e.toString());
//        }

//        NavigationUI.setupWithNavController(navView, navController);
