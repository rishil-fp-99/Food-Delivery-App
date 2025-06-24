package com.example.foodies5.dataclasses;

import com.mikhaellopez.circularimageview.CircularImageView;

public class Data_to_be_searched {
    //name->name of the shop
    //img ->icon of the shop
    //when some clicks on it he will be able to it's details
    String details;
    String imgurl;
    String name;
    String web_url;

    Data_to_be_searched(){}
    public Data_to_be_searched(String details, String imgurl, String name,String web_url) {
        this.details = details;
        this.imgurl = imgurl;
        this.name = name;
        this.web_url=web_url;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

}
