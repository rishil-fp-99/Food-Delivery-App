package com.example.foodies5.dataclasses;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchViewAdapter extends RecyclerView.ViewHolder implements Filterable {

    public SearchViewAdapter(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public Filter getFilter() {
        return null;
    }
}
