package com.aplicacionps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class pantallafinal extends AppCompatActivity {

    // AQUI SE RELACIONA LA CLASE pantallafinal.java CON SU XML activity_pantallafinal.xml
    //Y TAMBIEN SE PONE LA PANTALLA EN HORIZONTAL AL INICIARLA
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantallafinal);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    //AL PULSAR EL BOTON DE VOLVER A JUGAR, EL JUEGO TE LLEVA A LA CASA DEL JUGADOR PARA INICIAR DE NUEVO EL CAMINO
    public void volverajugar(View view){
        Intent volverajugar = new Intent (this, EscenarioCasa.class);
        startActivity(volverajugar);
    }

    //AL PULSAR EL BOTÓN DE MENÚ, TE LLEVA A LA PANTALLA PRINCIPAL DEL JUEGO
    public void menuprincipal(View view){
        Intent menuprincipal = new Intent (this, MainActivity.class);
        startActivity(menuprincipal);
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