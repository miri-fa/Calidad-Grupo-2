package com.aplicacionps;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

public class elegirCamino extends AppCompatActivity {

    private ProgressBar progressbar;
    private int porcentajeActual;
    private boolean mascarilla;

    // AQUI SE RELACIONA LA CLASE elegirCamino.java CON SU XML activity_elegir_camino.xml
    //Y TAMBIEN SE PONE LA PANTALLA EN HORIZONTAL AL INICIARLA
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elegir_camino);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        String Dato = getIntent().getStringExtra("dato");
        String Masc = getIntent().getStringExtra("masc");
        porcentajeActual = Integer.parseInt(Dato);
        progressbar= (ProgressBar)findViewById(R.id.barra1);
        progressbar.setProgress(porcentajeActual);
        mascarilla = Boolean.valueOf(Masc);
    }


    // EL BOTÓN TE LLEVA AL INTERIOR DEL AUTOBÚS
    public void caminoAutobus(View view) {
        int valor = porcentajeActual;
        String bool = Boolean.toString(mascarilla);
        String val = String.valueOf(valor);
        Intent caminoAutobus = new Intent(this, autobusInterior.class);
        Intent caminoVuelta = new Intent(this, CaminoVuelta.class);
        if (!mascarilla) {
            caminoVuelta.putExtra("dato", val);
            caminoVuelta.putExtra("masc", bool);
            startActivity(caminoVuelta);
        } else {
            caminoAutobus.putExtra("dato", val);
            caminoAutobus.putExtra("masc", bool);
            startActivity(caminoAutobus);
        }
    }

    // EL BOTÓN TE LLEVA A LA CALLE QUE LLEVA AL SUPERMERCADO
    public void caminoAndando(View view){
        int valor= porcentajeActual;
        String val= String.valueOf(valor);
        String bool = Boolean.toString(mascarilla);
        Intent caminoAndando = new Intent(this, irAndando.class);
        caminoAndando.putExtra("dato", val);
        caminoAndando.putExtra("masc", bool);
        startActivity(caminoAndando);
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