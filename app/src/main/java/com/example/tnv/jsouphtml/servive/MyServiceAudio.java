package com.example.tnv.jsouphtml.servive;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MyServiceAudio extends Service {

    public MediaPlayer mediaPlayer;
    public MyServiceAudio() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //mediaPlayer = MediaPlayer.create(getApplicationContext(),)
    }
}
