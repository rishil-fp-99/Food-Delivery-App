package com.example.foodies5.ui.videos_img;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodies5.R;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerTracker;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class Video_Adapter_one extends RecyclerView.ViewHolder{

    SimpleExoPlayer simpleExoPlayer;
    PlayerView playerView;
    TextView textView;
    VideoView videoView;
    YouTubePlayerView youTubePlayerView;

    public Video_Adapter_one(@NonNull View itemView) {
        super(itemView);
    }
    public void setVideoDetails(Context context,String video_name,String video_url)
    {
        textView=itemView.findViewById(R.id.text_cuisines_video_name);
        textView.setText(video_name);
        playerView=itemView.findViewById(R.id.exo_north);
        try{
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter.Builder(context).build();
            TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
            simpleExoPlayer = (SimpleExoPlayer) ExoPlayerFactory.newSimpleInstance(context);
            simpleExoPlayer.setRepeatMode(Player.REPEAT_MODE_ONE);
            Uri video = Uri.parse(video_url);
            DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("video");
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            final MediaSource mediaSource = new ExtractorMediaSource(video, dataSourceFactory, extractorsFactory, null, null);
            playerView.setPlayer(simpleExoPlayer);
            simpleExoPlayer.prepare(mediaSource);
            simpleExoPlayer.setPlayWhenReady(false);
        }catch (Exception e)
        {
            Toast.makeText(context, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public void setVideoView(Context context, String video_name, final String video_url)
    {
        youTubePlayerView=itemView.findViewById(R.id.exo_north);
        final YouTubePlayerTracker youTubePlayerTracker=new YouTubePlayerTracker();

        youTubePlayerView.addYouTubePlayerListener(new YouTubePlayerListener() {
            @Override
            public void onReady(@NotNull YouTubePlayer youTubePlayer) {

            }

            @Override
            public void onStateChange(@NotNull YouTubePlayer youTubePlayer, @NotNull PlayerConstants.PlayerState playerState) {

            }

            @Override
            public void onPlaybackQualityChange(@NotNull YouTubePlayer youTubePlayer, @NotNull PlayerConstants.PlaybackQuality playbackQuality) {

            }

            @Override
            public void onPlaybackRateChange(@NotNull YouTubePlayer youTubePlayer, @NotNull PlayerConstants.PlaybackRate playbackRate) {

            }

            @Override
            public void onError(@NotNull YouTubePlayer youTubePlayer, @NotNull PlayerConstants.PlayerError playerError) {

            }

            @Override
            public void onCurrentSecond(@NotNull YouTubePlayer youTubePlayer, float v) {

            }

            @Override
            public void onVideoDuration(@NotNull YouTubePlayer youTubePlayer, float v) {

            }

            @Override
            public void onVideoLoadedFraction(@NotNull YouTubePlayer youTubePlayer, float v) {

            }

            @Override
            public void onVideoId(@NotNull YouTubePlayer youTubePlayer, @NotNull String s) {
             youTubePlayer.loadVideo(video_url,0);
            }

            @Override
            public void onApiChange(@NotNull YouTubePlayer youTubePlayer) {

            }
        });
        textView=itemView.findViewById(R.id.text_cuisines_video_name);
        textView.setText(video_name);



//        videoView=itemView.findViewById(R.id.exo_north);
//        videoView.setVideoPath(video_url);
//        MediaController mediaController=new MediaController(context);
//        mediaController.setAnchorView(videoView);
//        videoView.setMediaController(mediaController);
    }

}
