package com.aplicacionps;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Cambiamos la orientación para que la pantalla se pueda ver en horizontal y que
        //se muestre a pantalla completa, sin barra de notificaciones
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }
    //Estos 2 últimos métodos sirven para implementar la música del juego dentro de esta clase y para las demás,
    //siendo la música constante y permanente durante el tiempo que te encuentres dentro del juego
    @Override
    public void onPause() {
        super.onPause();

        //IMPLEMENTACIÓN DEL SERVICIO DE AUDIO (INTERRUMPE EL SERVICIO CUANDO SE SALE DE ESTA ACTIVITY
        Intent i = new Intent(this, AudioService.class);
        i.putExtra("action", AudioService.PAUSE);
        startService(i);
    }

    @Override
    public void onResume() {
        super.onResume();

        //SE RECUPERA DE VALORES GUARDADOS EL VALOR DEL SWITCH SILENCIAR Y SE ALMACENA EN valorboton.
        SharedPreferences sharedPreferences = getSharedPreferences("save", MODE_PRIVATE);
        Boolean valordelboton = sharedPreferences.getBoolean("value", false);

        //DEPENDIENDO DEL CONTENIDO DE valorboton SE REANUDA EL SERVICIO DE AUDIO EN ESTA ACTIVITY
        if (valordelboton != true) {
            Intent i = new Intent(this, AudioService.class);
            i.putExtra("action", AudioService.START);
            startService(i);
        }
    }
}