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

    private ProgressBar progressbar;
    private int porcentajeActual;
    private boolean mascarilla;

    // AQUI SE RELACIONA LA CLASE autobusInterior.java CON SU XML activity_autobus_interior.xml
    //Y TAMBIEN SE PONE LA PANTALLA EN HORIZONTAL AL INICIARLA
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autobus_interior);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        String Dato = getIntent().getStringExtra("dato");
        String Masc = getIntent().getStringExtra("masc");
        Toast.makeText(this, "No disponible " + Dato, Toast.LENGTH_SHORT).show();
        porcentajeActual = Integer.parseInt(Dato);
        progressbar= (ProgressBar)findViewById(R.id.barra1);
        progressbar.setProgress(porcentajeActual);
        mascarilla = Boolean.valueOf(Masc);
    }


    // EL BOTÓN HACE QUE SALGA UN MENSAJE EMERGENTE QUE PONE "NO DISPONIBLE"
    public void sitioSenhora(View view){
        String bool = Boolean.toString(mascarilla);
        int valor= porcentajeActual +10;
        String val= String.valueOf(valor);
        Intent sitioSenhora = new Intent (this, superfuera.class);
        sitioSenhora.putExtra("dato", val);
        sitioSenhora.putExtra("masc", bool);
        startActivity(sitioSenhora);
    }

    // EL BOTÓN TE LLEVA DIRECTAMENTE A LA ENTRADA DEL SUPER
    public void sitioSolo(View view){
        String bool = Boolean.toString(mascarilla);
        int valor= porcentajeActual;
        String val= String.valueOf(valor);
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