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

public class colaparapagar extends AppCompatActivity {

    private ProgressBar progressbar;
    private int porcentajeActual;

    // AQUI SE RELACIONA LA CLASE colaparapagar.java CON SU XML activity_colaparapagar.xml
    //Y TAMBIEN SE PONE LA PANTALLA EN HORIZONTAL AL INICIARLA
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colaparapagar);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        String Dato = getIntent().getStringExtra("dato");
        porcentajeActual = Integer.parseInt(Dato);
        progressbar= (ProgressBar)findViewById(R.id.barra1);
        progressbar.setProgress(porcentajeActual);

    }

    // EL BOTÓN HACE QUE SALGA UN MENSAJE EMERGENTE QUE PONE "NO DISPONIBLE"
    public void efectivo(View view){
        int valor= porcentajeActual + 10;
        String val= String.valueOf(valor);
        Intent efectivo = new Intent (this, pantallafinal.class);
        efectivo.putExtra("dato", val);
        startActivity(efectivo);
    }

    // EL BOTÓN TE LLEVA A LA PANTALLA FINAL AL HABER ACABADO EL RECORRIDO
    public void tarjeta(View view){
        int valor= porcentajeActual;
        String val= String.valueOf(valor);
        Intent tarjeta = new Intent (this, pantallafinal.class);
        tarjeta.putExtra("dato", val);
        startActivity(tarjeta);
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