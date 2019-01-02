package com.example.abdulbasit.project;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

public class alarm extends BroadcastReceiver {
//    MediaPlayer mediaPlayer;
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"Wake up",Toast.LENGTH_LONG).show();
//        mediaPlayer=MediaPlayer.create(this,R.raw.la);
//        mediaPlayer.start();
    }
}
