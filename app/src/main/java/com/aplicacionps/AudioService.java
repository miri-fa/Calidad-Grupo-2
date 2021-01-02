package com.aplicacionps;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

//SERVICIO QUE IMPLEMENTAN TODAS LAS ACTIVITY QUE POSEAN MÚSICA DE FONDO
public class AudioService extends Service {

    //DISITNTAS ACCIONES QUE SE PUEDEN REALIZAR. EN ORDEN SON: DISMINUIR VOLUMEN, AUMENTAR VOLUMEN, EMPEZAR MÚSICA, PARAR MÚSICA
    static final int DECREASE = 1, INCREASE= 2, START = 3, PAUSE = 4;
    Boolean shouldPause = false;
    MediaPlayer loop;

    //LA MÚSICA SE EJECUTA EN BUCLE.
    private void startLoop(){

        //SI AUN NO SE HA EJECUTADO EL BUCLE, SE INSERTA LA CANCIÓN EN loop (musica_fondo_final EN CARPETA raw)
        if(loop == null){
            loop = MediaPlayer.create(this, R.raw.musica_fondo_final);
        }

        //SI YA SE HABIA ASIGNADO UNA CACNCIÓN Y TERMINA, SE REPRODUCE DESDE LE PRINCIPIO
        if(!loop.isPlaying()){
            loop.setLooping(true);
            loop.start();
        }
    }

    //FUNCIÓN QUE DISMINUYE EL VOLUMNE DE LA MÚSICA
    private void decrease(){
        loop.setVolume(0.2f, 0.2f);
    }

    //FUNCIÓN QUE AUMENTA EL VOLUMEN DE LA MÚSICA
    private void increase(){
        loop.setVolume(1.0f, 1.0f);
    }

    //FUNCIÓN QUE EMPIEZA LA REPRODUCCIÓN DE LA MÚSICA
    private void start(){
        startLoop();
        shouldPause = false;
    }

    //FUNCIÓN QUE PAUSA LA MÚSICA
    private void stop(){
        shouldPause = true;

        //SE PAUSA EL BUCLE PARA PODER REANUDARLO CUANDO SE VUELVA A LA ACTIVITY (SENSACIÓN DE CONTINUIDAD EN LA MÚSICA)
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        if(shouldPause&&loop!=null) {
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

            //DEPENDIENDO EL VALOR QUE SE INTRODUCA A LA FUNCIÓN SE EJECUTA UNA FUNCIÓN U OTRA
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
        //SE LIBERA LA MEMORIA QUE OCUPASE LA CANCIÓN AL DESTRUIR ESTE SERVICIO (CIERRE TOTAL DE LA APLICACIÓN)
        if (loop != null) loop.release();
    }

    //FUNCIÓN POR DEFECTO QUE TIENE QUE APARECER EN TODOS LOS Service
    @Override
    public IBinder onBind(Intent intent){
        return null;
    }
}

//LAS SIGUIENTES TRES LÍNEAS SE INSERTAN EN EL PROCEDIMEINTO onPause() DE TODA ACTIVITY QUE REPRODUZCA MÚSICA DE FONDO
/*Intent i = new Intent(this, AudioService.class);
  i.putExtra("action", AudioService.PAUSE);
  startService(i);*/

//LAS SIGUIENTES DOS LINEAS SE INSERTAN EN EL PROCEDIMIENTO onResume() DE TODA ACTIVITY QUE REPRODUZCA MÚSICA DE FONDO
/*SharedPreferences sharedPreferences = getSharedPreferences("save", MODE_PRIVATE);
  Boolean valordelboton = sharedPreferences.getBoolean("value", false);*/