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

    // AQUI SE RELACIONA LA CLASE elegirCamino.java CON SU XML activity_elegir_camino.xml
    //Y TAMBIEN SE PONE LA PANTALLA EN HORIZONTAL AL INICIARLA
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elegir_camino);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        String Dato = getIntent().getStringExtra("dato");
        Toast.makeText(this, "No disponible " + Dato, Toast.LENGTH_SHORT).show();
        porcentajeActual = Integer.parseInt(Dato);
        progressbar= (ProgressBar)findViewById(R.id.barra1);
        progressbar.setProgress(porcentajeActual);
    }


    // EL BOTÓN TE LLEVA AL INTERIOR DEL AUTOBÚS
    public void caminoAutobus(View view){
        int valor= porcentajeActual;
        String val= String.valueOf(valor);
        Intent caminoAutobus = new Intent (this, autobusInterior.class);
        caminoAutobus.putExtra("dato", val);
        startActivity(caminoAutobus);
    }

    // EL BOTÓN TE LLEVA A LA CALLE QUE LLEVA AL SUPERMERCADO
    public void caminoAndando(View view){
        int valor= porcentajeActual;
        String val= String.valueOf(valor);
        Intent caminoAndando = new Intent(this, irAndando.class);
        caminoAndando.putExtra("dato", val);
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