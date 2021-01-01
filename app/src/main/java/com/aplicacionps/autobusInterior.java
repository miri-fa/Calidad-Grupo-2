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

public class autobusInterior extends AppCompatActivity {
    // se crea un progressbar para representar el pocentaje de contagio que lleva el personaje
    // se crean dos variables para almacenar los valores que se pasan de un activity a otro
    private ProgressBar progressbar;
    private int porcentajeActual;
    private boolean mascarilla;

    // AQUI SE RELACIONA LA CLASE autobusInterior.java CON SU XML activity_autobus_interior.xml
    //Y TAMBIEN SE PONE LA PANTALLA EN HORIZONTAL AL INICIARLA
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autobus_interior);

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


    // EL BOTÓN HACE QUE SALGA UN MENSAJE EMERGENTE QUE PONE "NO DISPONIBLE"
    public void sitioSenhora(View view){
        // se obtiene el porcentaje actual y el booleano de la mascarilla y se actualiza al elegir una mala opcion
        String bool = Boolean.toString(mascarilla);
        int valor= porcentajeActual +10;
        String val= String.valueOf(valor);
        // se crea el nuevo activity, se pasan los datos anteriormente sacados y se inicializa
        Intent sitioSenhora = new Intent (this, superfuera.class);
        sitioSenhora.putExtra("dato", val);
        sitioSenhora.putExtra("masc", bool);
        startActivity(sitioSenhora);
    }

    // EL BOTÓN TE LLEVA DIRECTAMENTE A LA ENTRADA DEL SUPER
    public void sitioSolo(View view){
        // se obtiene el porcentaje actual y el booleano de la mascarilla
        String bool = Boolean.toString(mascarilla);
        int valor= porcentajeActual;
        String val= String.valueOf(valor);
        // se crea el nuevo activity, se pasan los datos anteriormente sacados y se inicializa
        Intent sitioSolo = new Intent (this, superfuera.class);
        sitioSolo.putExtra("dato", val);
        sitioSolo.putExtra("masc", bool);
        startActivity(sitioSolo);
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