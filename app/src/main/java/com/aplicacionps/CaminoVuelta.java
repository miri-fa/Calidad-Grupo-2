package com.aplicacionps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class CaminoVuelta extends AppCompatActivity {

    private int porcentajeActual;
    private boolean mascarilla;
    // AQUI SE RELACIONA LA CLASE CaminoVuelta.java CON SU XML activity_camino_vuelta.xml
    //Y TAMBIEN SE PONE LA PANTALLA EN HORIZONTAL AL INICIARLA
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camino_vuelta);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        String Dato = getIntent().getStringExtra("dato");
        porcentajeActual = Integer.parseInt(Dato);
    }

    public void vueltaAElegirCamino(View view){
        int valor = porcentajeActual;
        String mascarilla = Boolean.toString(true);
        String val = String.valueOf(valor);
        Intent vueltaAElegirCamino = new Intent(this, elegirCamino.class);
        vueltaAElegirCamino.putExtra("dato", val);
        vueltaAElegirCamino.putExtra("masc", mascarilla);
        startActivity(vueltaAElegirCamino);
    }

    // LOS SIGUIENTES MÉTODOS SIRVEN PARA PONER LA MUSICA QUE SE VA A ESCUCHAR A LO LARGO DE LA APLICACIÓN
    @Override
    public void onPause() {
        super.onPause();
        Intent i = new Intent(this, AudioService.class);
        i.putExtra("action", AudioService.PAUSE);
        startService(i);
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("save", MODE_PRIVATE);
        Boolean valordelboton = sharedPreferences.getBoolean("value", false);
        if (valordelboton != true) {
            Intent i = new Intent(this, AudioService.class);
            i.putExtra("action", AudioService.START);
            startService(i);
        }
    }

}