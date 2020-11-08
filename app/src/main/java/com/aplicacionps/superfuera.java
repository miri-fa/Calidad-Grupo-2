package com.aplicacionps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

public class superfuera extends AppCompatActivity {

    // AQUI SE RELACIONA LA CLASE superfuera.java CON SU XML activity_superfuera.xml
    //Y TAMBIEN SE PONE LA PANTALLA EN HORIZONTAL AL INICIARLA
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superfuera);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    // EL BOTÓN HACE QUE SALGA UN MENSAJE EMERGENTE QUE PONE "NO DISPONIBLE"
    public void entrar(View view){
        Toast.makeText(this, "No disponible", Toast.LENGTH_SHORT).show();
        // Intent entrar = new Intent(this, superdentro.class);
        // startActivity(entrar);
    }

    // EL BOTON TE LLEVA AL INTERIOR DEL SUPERMERCADO PARA INICIAR LA COMPRA
    public void esperar(View view){
            Intent esperar = new Intent (this, superdentro.class);
            startActivity(esperar);
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