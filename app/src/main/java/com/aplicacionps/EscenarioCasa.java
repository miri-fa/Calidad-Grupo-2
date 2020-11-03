package com.aplicacionps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

public class EscenarioCasa extends AppCompatActivity {
private Personaje personaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrada_casa);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void mascarilla(View view){
        Intent mascarilla = new Intent (this, elegirCamino.class);
        startActivity(mascarilla);
    }
    //Falta crear una clase personaje en la que al elegir el camino malo incremente el porcentaje de contagio
    public void noMascarilla(View view){
        Toast.makeText(this, "No disponible", Toast.LENGTH_SHORT).show();
    }

}