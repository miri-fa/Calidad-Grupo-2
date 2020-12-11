package com.aplicacionps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

public class superdentro extends AppCompatActivity {

    private ProgressBar progressbar;
    private int porcentajeActual;

    // AQUI SE RELACIONA LA CLASE superdentro.java CON SU XML activity_superdentro.xml
    //Y TAMBIEN SE PONE LA PANTALLA EN HORIZONTAL AL INICIARLA
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superdentro);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        String Dato = getIntent().getStringExtra("dato");

        porcentajeActual = Integer.parseInt(Dato);
        progressbar= (ProgressBar)findViewById(R.id.barra1);
        progressbar.setProgress(porcentajeActual);
    }

    //  EL BOTÓN TE LLEVA A LA COLA DEL SUPER AL HABER ACABADO LA COMPRA
    public void guantesygel(View view){
        int valor= porcentajeActual;
        String val= String.valueOf(valor);
        Intent guantesygel = new Intent (this, colaparapagar.class);
        guantesygel.putExtra("dato", val);
        startActivity(guantesygel);
    }

    // EL BOTÓN HACE QUE SALGA UN MENSAJE EMERGENTE QUE PONE "NO DISPONIBLE"
    public void nada(View view){
        int valor= porcentajeActual + 10;
        String val= String.valueOf(valor);
        Intent nada = new Intent (this, colaparapagar.class);
        nada.putExtra("dato", val);
        startActivity(nada);
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