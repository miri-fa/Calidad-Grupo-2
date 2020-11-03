package com.aplicacionps;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;


public class elegirCamino extends AppCompatActivity {
private Personaje personaje;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elegir_camino);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }
    public void caminoAutobus(View view){
            Intent caminoAutobus = new Intent(this, autobusInterior.class);
            startActivity(caminoAutobus);
    }

    public void caminoAndando(View view){
        Intent caminoAndando = new Intent(this, irAndando.class);
        startActivity(caminoAndando);
    }

}