package com.example.foodies5.ui.videos_img;

import org.jetbrains.annotations.NotNull;

public class Video {

    String video_name,video_url;

    public Video() {
    }

    public Video(String video_name, String video_url) {
        this.video_name = video_name;
        this.video_url = video_url;
    }

    public String getVideo_name() {
        return video_name;
    }

    public void setVideo_name(String video_name) {
        this.video_name = video_name;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

}
