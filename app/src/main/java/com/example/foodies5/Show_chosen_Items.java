package com.example.foodies5;

import java.io.Serializable;

public class Show_chosen_Items  {

    String show_item_name,show_item_count,show_item_price;

    public Show_chosen_Items() {
    }

    public String getShow_item_name() {
        return show_item_name;
    }

    public void setShow_item_name(String show_item_name) {
        this.show_item_name = show_item_name;
    }

    public String getShow_item_count() {
        return show_item_count;
    }

    public void setShow_item_count(String show_item_count) {
        this.show_item_count = show_item_count;
    }

    public String getShow_item_price() {
        return show_item_price;
    }

    public void setShow_item_price(String show_item_price) {
        this.show_item_price = show_item_price;
    }
}
