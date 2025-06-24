package com.example.foodies5.dataclasses;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Sweets {
    String sweet_name;



    String sweet_url;

    public Sweets() {
    }

    public String getSweet_name() {
        return sweet_name;
    }

    public void setSweet_name(String sweet_name) {
        this.sweet_name = sweet_name;
    }
    public String getSweet_url() {
        return sweet_url;
    }

    public void setSweet_url(String sweet_url) {
        this.sweet_url = sweet_url;
    }


}


