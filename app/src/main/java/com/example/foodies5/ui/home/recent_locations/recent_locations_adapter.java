package com.example.foodies5.ui.home.recent_locations;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodies5.R;

public class recent_locations_adapter extends RecyclerView.ViewHolder {
    EditText recent_edit_text;

    public recent_locations_adapter(@NonNull View itemView) {
        super(itemView);
    }

    public void setRecentLocationDetails(Context context, String recent_location) {

    recent_edit_text=itemView.findViewById(R.id.edit_text_put_recent_loc);
    recent_edit_text.setText(recent_location);

    }
}
