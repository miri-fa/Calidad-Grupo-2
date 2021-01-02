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

public class superfuera extends AppCompatActivity {

    // se crea un progressbar para representar el pocentaje de contagio que lleva el personaje
    // se crean dos variables para almacenar los valores que se pasan de un activity a otro

    private ProgressBar progressbar;
    private int porcentajeActual;
    private boolean mascarilla;

    // AQUI SE RELACIONA LA CLASE superfuera.java CON SU XML activity_superfuera.xml

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superfuera);

        //Cambiamos la orientación para que la pantalla se pueda ver en horizontal y que
        //se muestre a pantalla completa, sin barra de notificaciones

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // se obtienen los datos como strings y luego se convierten en sus tipos correspondientes

        String Dato = getIntent().getStringExtra("dato");
        String Masc = getIntent().getStringExtra("masc");
        porcentajeActual = Integer.parseInt(Dato);
        mascarilla = Boolean.valueOf(Masc);

        // la barra se relaciona con el activity y se establece el porcentaje que se va a mostrar con el numero anteriormente obtenido

        progressbar= (ProgressBar)findViewById(R.id.barra1);
        progressbar.setProgress(porcentajeActual);

    }

    // EL BOTON TE LLEVA AL INTERIOR DEL SUPERMERCADO PARA INICIAR LA COMPRA
    // AL SER LA OPCIÓN INCORRECTA, SE AUMENTARÁ EN UN 10% EL PORCENTAJE DE CONTAGIO
    // Y NO DEJARÁ ENTRAR EN EL SUPERMERCADO
    public void entrar(View view){
        int valor= porcentajeActual + 10;
        String val= String.valueOf(valor);
        Intent entrar = new Intent(this, superdentro.class);
        Intent caminoVuelta = new Intent(this, CaminoVuelta.class);
        if (!mascarilla) {
            caminoVuelta.putExtra("dato", val);
            startActivity(caminoVuelta);
        } else {
            entrar.putExtra("dato", val);
            startActivity(entrar);
        }
    }

    // EL BOTON TE LLEVA AL INTERIOR DEL SUPERMERCADO PARA INICIAR LA COMPRA
    // AL SER LA OPCIÓN CORRECTA, EL PORCENTAJE DE CONTAGIO NO VARIARÁ
    // Y SE PODRÁ ENTRAR AL SUPERMERCADO
    public void esperar(View view){
        int valor= porcentajeActual;
        String val= String.valueOf(valor);
        Intent esperar = new Intent (this, superdentro.class);
        Intent caminoVuelta = new Intent(this, CaminoVuelta.class);
        if (!mascarilla) {
            caminoVuelta.putExtra("dato", val);
            startActivity(caminoVuelta);
        } else {
            esperar.putExtra("dato", val);
            startActivity(esperar);
        }
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