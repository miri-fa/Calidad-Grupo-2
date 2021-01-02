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

    // se crea un progressbar para representar el pocentaje de contagio que lleva el personaje
    // se crean otra variable para almacenar el valor que se pasa de un activity a otro

    private ProgressBar progressbar;
    private int porcentajeActual;

    // AQUI SE RELACIONA LA CLASE superdentro.java CON SU XML activity_superdentro.xml

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superdentro);

        //Cambiamos la orientación para que la pantalla se pueda ver en horizontal y que
        //se muestre a pantalla completa, sin barra de notificaciones

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // se obtienen el dato como string y luego se convierte en sus tipo correspondiente

        String Dato = getIntent().getStringExtra("dato");
        porcentajeActual = Integer.parseInt(Dato);

        // la barra se relaciona con el activity y se establece el porcentaje que se va a mostrar con el numero anteriormente obtenido

        progressbar= (ProgressBar)findViewById(R.id.barra1);
        progressbar.setProgress(porcentajeActual);
    }

    //  EL BOTÓN TE LLEVA A LA COLA DEL SUPER AL HABER ACABADO LA COMPRA
    // AL SER LA OPCIÓN CORRECTA, EL PORCENTAJE DE CONTAGIO NO VARIARÁ
    public void guantesygel(View view){
        int valor= porcentajeActual;
        String val= String.valueOf(valor);
        Intent guantesygel = new Intent (this, colaparapagar.class);
        guantesygel.putExtra("dato", val);
        startActivity(guantesygel);
    }

    // EL BOTÓN TE LLEVA A LA COLA DEL SUPER AL HABER ACABADO LA COMPRA
    // AL SER LA OPCIÓN INCORRECTA, SE AUMENTARÁ EN UN 10% EL PORCENTAJE DE CONTAGIO
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