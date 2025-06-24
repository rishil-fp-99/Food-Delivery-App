package com.example.foodies5.ui.home.recent_locations;

import java.io.Serializable;

public class recent_locations_model implements Serializable {
    String recent_location;
    public recent_locations_model() {
    }

    public String getRecent_location() {
        return recent_location;
    }

    public void setRecent_location(String recent_location) {
        this.recent_location = recent_location;
    }

}
