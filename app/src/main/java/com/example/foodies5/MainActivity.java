package com.example.foodies5;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodies5.User_details.MyUserData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView user_sign_in;
    TextView tuname,tpwd,temail,tphone,taddress;
    Button create_acc;
    EditText uname,pwd,email,ph_no,address;
    String u1,pwd1,email1,ph_no1,address1;
   public DatabaseReference dregister,d_child_count;
    private static long usercounter;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dregister= FirebaseDatabase.getInstance().getReference("Users");
        d_child_count=FirebaseDatabase.getInstance().getReference("Users");
        Log.d("MainActivity", Arrays.toString(databaseList()));
        register();
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public  void register()
    {
        //Create custom dialog ui
        View custom_view=getLayoutInflater().inflate(R.layout.custom_dialog_user_verification,null);
        user_sign_in=custom_view.findViewById(R.id.direct_user_sign_in);

            user_sign_in.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, Login_Activity.class);
                    startActivity(intent);
                }
            });

        uname=custom_view.findViewById(R.id.username);
        pwd=custom_view.findViewById(R.id.pwd);
        email=custom_view.findViewById(R.id.pemail);
        ph_no=custom_view.findViewById(R.id.mobile_number);
        address=custom_view.findViewById(R.id.address);
        create_acc=custom_view.findViewById(R.id.create_acc);
        tuname=custom_view.findViewById(R.id.validate_uname);
        tpwd=custom_view.findViewById(R.id.validate_pwd);
        temail=custom_view.findViewById(R.id.validate_email);
        tphone=custom_view.findViewById(R.id.validate_phone_num);
        taddress=custom_view.findViewById(R.id.validate_address);

      //  create_acc.setEnabled(true);


        create_acc.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                add_to_firebase();

            }
        });
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setView(custom_view);
        AlertDialog alertDialog=builder.create();
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();
    }

    @SuppressLint("SetTextI18n")
    public void add_to_firebase()
    {
        MyUserData myUserData=new MyUserData();
        u1=uname.getText().toString();
        pwd1=pwd.getText().toString();
        email1=email.getText().toString();
        ph_no1=ph_no.getText().toString();
        address1=address.getText().toString();

        myUserData.setUsername(u1);
        myUserData.setPassword(pwd1);
        myUserData.setEmail(email1);
        myUserData.setPhone_num(ph_no1);
        myUserData.setAddress(address1);


        if(u1.isEmpty())
        {
            tuname.setText("Empty Username");
            tuname.setTextColor(Color.RED);
            usercounter=0;
        }
        else{
            tuname.setVisibility(View.GONE);
        }
         if(pwd1.isEmpty()){
            tpwd.setText("Empty password");
            tpwd.setTextColor(Color.RED);
            usercounter=0;
        }
         else
         {
            tpwd.setVisibility(View.GONE);
         }
         if(email1.isEmpty()){
            temail.setText("Empty email address");
            temail.setTextColor(Color.RED);
             usercounter=0;
        }
         else {
             temail.setVisibility(View.GONE);
         }
         if(ph_no1.isEmpty()){
            tphone.setText("Empty phone number");
            tphone.setTextColor(Color.RED);
             usercounter=0;
        }else {
             tphone.setVisibility(View.GONE);
         }
         if(address1.isEmpty()){
            taddress.setText("Empty address");
            taddress.setTextColor(Color.RED);
             usercounter=0;
        }
         else {
             taddress.setVisibility(View.GONE);
         }
        if(u1.length()>0 && pwd1.length()>0 && email1.length()>0 && ph_no1.length()>0 && address1.length()>0) {
//            usercounter++;

            dregister.child("").push().setValue(myUserData).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(MainActivity.this, "Account created successfully"+"usercounter"+usercounter, Toast.LENGTH_SHORT).show();
                    uname.setText("");
                    pwd.setText("");
                    email.setText("");
                    ph_no.setText("");
                    address.setText("");
//                    Intent intent = new Intent(MainActivity.this, Login_Activity.class);
//                    startActivity(intent);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(MainActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        }


    }



}