package com.example.foodies5.ui.home;

public class FoodOrderRest  {
    String rest_img_url;
    String rest_name;
    public FoodOrderRest() {
    }
    public FoodOrderRest(String rest_img_url, String rest_name) {
        this.rest_img_url = rest_img_url;
        this.rest_name = rest_name;
    }

    public String getRest_img_url() {
        return rest_img_url;
    }

    public void setRest_img_url(String rest_img_url) {
        this.rest_img_url = rest_img_url;
    }

    public String getRest_name() {
        return rest_name;
    }

    public void setRest_name(String rest_name) {
        this.rest_name = rest_name;
    }



}
