package com.aplicacionps;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;

public class AudioService extends Service {
    static final int DECREASE = 1, INCREASE= 2, START = 3, PAUSE = 4;
    Boolean shouldPause = false;
    MediaPlayer loop;




    private void startLoop(){

        if(loop == null){
            loop = MediaPlayer.create(this, R.raw.musica_fondo_final);
        }
        if(!loop.isPlaying()){
            loop.setLooping(true);
            loop.start();
        }
    }
    private void decrease(){
        loop.setVolume(0.2f, 0.2f);
    }
    private void increase(){
        loop.setVolume(1.0f, 1.0f);
    }
    private void start(){
        startLoop();
        shouldPause = false;
    }
    private void stop(){
        shouldPause = true;
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        if(shouldPause) {
                            loop.pause();
                        }
                    }
                }, 100);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(getClass().getSimpleName(), "Creating service");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Log.i(getClass().getSimpleName(), "Intent received");

        try {
            int actionDefault = 0;
            int action = actionDefault;

            if(intent != null){
                if(intent.hasExtra("action")){
                    action = intent.getIntExtra("action", actionDefault);
                }
            }

            switch (action) {
                case INCREASE:
                    increase();
                    break;
                case DECREASE:
                    decrease();
                    break;
                case START:
                    start();
                    break;
                case PAUSE:
                    stop();
                    break;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (loop != null) loop.release();
    }

    @Override
    public IBinder onBind(Intent intent){
        return null;
    }
}
