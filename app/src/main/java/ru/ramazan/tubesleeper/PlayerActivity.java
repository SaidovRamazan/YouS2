package ru.ramazan.tubesleeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.media2.SessionPlayer;

import android.app.Notification;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.session.MediaSession;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;

import com.google.android.exoplayer2.ExoPlayer;
//import androidx.media2.MediaItem;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.ui.StyledPlayerView;

public class PlayerActivity extends AppCompatActivity {


    //PlaybackService playbackService = new PlaybackService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_activity);



    }

    @Override
    protected void onStart() {
        super.onStart();


        ExoPlayer player = new ExoPlayer.Builder(this).build();
        //playbackService.onCreate();
        //SessionPlayer sessionPlayer = playbackService.onGetSession().getPlayer();
        //ExoPlayer player = (ExoPlayer) sessionPlayer;
        StyledPlayerView playerView = findViewById(R.id.player_view);
        playerView.setPlayer(player);

        // Build the media item.
        MediaItem mediaItem = MediaItem.fromUri(VideoLink.videoLink);
// Set the media item to be played.
        player.setMediaItem(mediaItem);
// Prepare the player.
        player.prepare();
// Start the playback.
        player.play();


    }
}