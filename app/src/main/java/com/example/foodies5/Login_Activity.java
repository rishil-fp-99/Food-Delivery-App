package com.example.foodies5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.TooltipCompat;

import android.app.AlertDialog;
import android.content.Intent;

import android.graphics.Color;
import android.os.Bundle;
import android.os.UserManager;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodies5.User_details.MyUserData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tooltip.Tooltip;


import java.util.ArrayList;

import java.util.List;
import java.util.Objects;

public class Login_Activity extends AppCompatActivity {

    TextView direct_sign_up;
    Button login;
    String uname;
    String pwd;
    EditText luname,lpwd;
    CheckBox show_password;
    DatabaseReference dlogin;
    public  static int check_click=0;
    public static int check_sit=0;
    Tooltip tuanme,tpwd;
   List<String> auth=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        login();
    }
    public void login()
    {

        final View loginview=getLayoutInflater().inflate(R.layout.login_xml,null);
        direct_sign_up=loginview.findViewById(R.id.direct_sign_up);
        login=loginview.findViewById(R.id.loginbtn);
        luname=loginview.findViewById(R.id.luname);
        lpwd=loginview.findViewById(R.id.lpwd);
        dlogin= FirebaseDatabase.getInstance().getReference("Users");
        show_password=loginview.findViewById(R.id.show_password);
        show_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    lpwd.setTransformationMethod(new HideReturnsTransformationMethod());
                }
                else
                    lpwd.setTransformationMethod(new PasswordTransformationMethod());
            }
        });




        direct_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login_Activity.this,MainActivity.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (luname.getText().toString().equals("")) {
                    tuanme=new Tooltip.Builder(luname).setText("Empty username").setTextColor(Color.CYAN)
                            .setCornerRadius(9f).setGravity(Gravity.BOTTOM).setDismissOnClick(true)
                            .setBackgroundColor(Color.MAGENTA).setMaxWidth(180).show();
                    check_sit = 1;
                }
                if (lpwd.getText().toString().equals("")) {
                    tpwd=new Tooltip.Builder(lpwd).setText("Empty password").setTextColor(Color.CYAN)
                            .setCornerRadius(9f).setGravity(Gravity.BOTTOM).setDismissOnClick(true)
                            .setBackgroundColor(Color.MAGENTA).setMaxWidth(180).show();
                    check_sit = 1;
                }
                //login code

                dlogin.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                                if (Objects.equals(dataSnapshot1.getKey(), "email") || Objects.equals(dataSnapshot1.getKey(), "password")) {
                                    auth.add(dataSnapshot1.getValue(String.class));
                                }
                            }

                        }
                 //      Toast.makeText(Login_Activity.this, "" + auth.get(0), Toast.LENGTH_SHORT).show();
                        Log.d("Login_Activity", String.valueOf("Auth uname"+auth.contains(luname.getText().toString())));
                        Log.d("Login_Activity",String.valueOf("Auth password"+auth.contains(lpwd.getText().toString())));
                        if(auth.contains(luname.getText().toString())&& auth.contains(lpwd.getText().toString()))
                        {
                            Toast.makeText(Login_Activity.this, "Login successful", Toast.LENGTH_SHORT).show();
                            call_intent();
                        }
                        else{
                            Toast.makeText(Login_Activity.this, "Retry username or password", Toast.LENGTH_SHORT).show();
                        }

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(Login_Activity.this, "Error:"+error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }

        });
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setView(loginview);
        AlertDialog alertDialog=builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();

    }
    public void call_intent()
    {
     startActivity(new Intent(this,home_screen_btmnav.class));
    }

}