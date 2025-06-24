package com.example.foodies5.ui.profile;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontFamily;
import android.graphics.fonts.FontStyle;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.foodies5.R;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.tooltip.OnClickListener;
import com.tooltip.OnDismissListener;
import com.tooltip.Tooltip;

import java.util.Objects;

public class profile_fragment extends Fragment {

    private ProfileViewModel notificationsViewModel;
    private Tooltip tooltip;
    static  int clickcount=0;
   public CircularImageView circularImageView;
    @SuppressLint({"ClickableViewAccessibility", "CutPasteId"})
    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_profile, container, false);
        /*notificationsViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);

        //final TextView textView = root.findViewById(R.id.text_notifications);
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
           //     textView.setText(s);
            }
        });*/
        circularImageView=root.findViewById(R.id.add_photo);
        circularImageView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View v) {
                clickcount=clickcount+1;

                if(clickcount>1)
                    tooltip.dismiss();
                tooltip=new Tooltip.Builder(circularImageView)
                        .setText("Click to add profile photo").setTextColor(Color.CYAN)
                        .setCornerRadius(9f).setGravity(Gravity.BOTTOM).setDismissOnClick(true)
                        .setBackgroundColor(Color.MAGENTA).setMaxWidth(180).show();
                tooltip.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(@NonNull Tooltip tooltip) {
                        if (root.getContext().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                                ||root.getContext().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                        ) {
                            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                        } else
                            startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), 2);
                        tooltip.dismiss();
                    }
                });
            }
        });

        return root;
}

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==2)
        {
            Bitmap bitmap;

                if(data!=null) {
                    try {
                        bitmap = (Bitmap) data.getExtras().get("data");
                        circularImageView.setImageBitmap(bitmap);
                    }
                    catch (NullPointerException ne)
                    {
                        Toast.makeText(getContext(), ""+ne.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
