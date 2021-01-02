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

    // se crea un progressbar para representar el pocentaje de contagio que lleva el personaje
    // se crean otra variable para almacenar el valor que se pasa de un activity a otro

    private ProgressBar progressbar;
    private int porcentajeActual;

    // AQUI SE RELACIONA LA CLASE colaparapagar.java CON SU XML activity_colaparapagar.xml

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colaparapagar);

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

    // EL BOTÓN TE LLEVA A LA PANTALLA FINAL AL HABER ACABADO EL RECORRIDO
    // AL SER LA OPCIÓN INCORRECTA, SE AUMENTARÁ EN UN 10% EL PORCENTAJE DE CONTAGIO

    public void efectivo(View view){
        int valor= porcentajeActual + 10;
        String val= String.valueOf(valor);
        Intent efectivo = new Intent (this, pantallafinal.class);
        efectivo.putExtra("dato", val);
        startActivity(efectivo);
    }

    // EL BOTÓN TE LLEVA A LA PANTALLA FINAL AL HABER ACABADO EL RECORRIDO
    // AL SER LA OPCIÓN CORRECTA, EL PORCENTAJE DE CONTAGIO NO VARIARÁ

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