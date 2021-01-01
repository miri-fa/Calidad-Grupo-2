package com.aplicacionps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class CaminoVuelta extends AppCompatActivity {

    // se crean las variables que van a almacenar los datos que se han pasado de la otra activity
    private int porcentajeActual;
    private boolean mascarilla;
    // AQUI SE RELACIONA LA CLASE CaminoVuelta.java CON SU XML activity_camino_vuelta.xml
    //Y TAMBIEN SE PONE LA PANTALLA EN HORIZONTAL AL INICIARLA
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camino_vuelta);

        //Cambiamos la orientación para que la pantalla se pueda ver en horizontal y que
        //se muestre a pantalla completa, sin barra de notificaciones
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // se obtiene el porcentaje actual
        String Dato = getIntent().getStringExtra("dato");
        porcentajeActual = Integer.parseInt(Dato);
    }

    public void vueltaAElegirCamino(View view){
        int valor = porcentajeActual;
        // el booleano mascarilla se pone en true ya que es obligado a volver a su casa para ponersela
        String mascarilla = Boolean.toString(true);
        String val = String.valueOf(valor);
        // se crea el intento de volver a elegir el camino con la mascarilla ya puesta y el numero de porcentaje se mantiene
        Intent vueltaAElegirCamino = new Intent(this, elegirCamino.class);
        // se pasan ambos datos y se inicia la actividad
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