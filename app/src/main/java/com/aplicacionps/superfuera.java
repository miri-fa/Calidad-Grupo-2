package com.aplicacionps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class superfuera extends AppCompatActivity {
    //Se crea un ProgressBar para representar el pocentaje de contagio que lleva el personaje
    private ProgressBar ProgressBar;
    //Se crean dos variables para almacenar los valores que se pasan de un activity a otro
    private int PorcentajeActual;
    private boolean Mascarilla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Relacionaremos la clase superfuera.java con su XML activity_superfuera.xml
        setContentView(R.layout.activity_superfuera);
        //Cambiamos la orientación para que la pantalla se pueda ver en horizontal y que se muestre a
        // pantalla completa, sin barra de notificaciones
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //Se obtienen los datos como strings y luego se convierten en sus tipos correspondientes
        String Dato = getIntent().getStringExtra("dato");
        String Masc = getIntent().getStringExtra("masc");
        PorcentajeActual = Integer.parseInt(Dato);
        Mascarilla = Boolean.valueOf(Masc);
        //La barra se relaciona con el activity y se establece el porcentaje que se va a mostrar con
        // el numero anteriormente obtenido
        ProgressBar = (ProgressBar)findViewById(R.id.barra1);
        ProgressBar.setProgress(PorcentajeActual);
    }

    //El boton te lleva al interior del supermercado para iniciar la compra.Al ser opcion incorrecta,
    //se aumentará en un 10% el porcentaje de contagio y no dejará entrar en el supermercado
    public void entrar(View view){
        int valor= PorcentajeActual + 10;
        String val= String.valueOf(valor);
        Intent entrar = new Intent(this, SuperDentro.class);
        Intent caminoVuelta = new Intent(this, CaminoVuelta.class);
        if (!Mascarilla) {
            caminoVuelta.putExtra("dato", val);
            startActivity(caminoVuelta);
        } else {
            entrar.putExtra("dato", val);
            startActivity(entrar);
        }
    }

    //El boton te lleva al interior del supermercado para iniciar la compra. Al ser opcion correcta,
    //el porcentaje de contagio no variará y se podrá entrar al supermercado
    public void esperar(View view){
        int valor= PorcentajeActual;
        String val= String.valueOf(valor);
        Intent esperar = new Intent (this, SuperDentro.class);
        Intent caminoVuelta = new Intent(this, CaminoVuelta.class);
        if (!Mascarilla) {
            caminoVuelta.putExtra("dato", val);
            startActivity(caminoVuelta);
        } else {
            esperar.putExtra("dato", val);
            startActivity(esperar);
        }
    }

    //Los siguientes metodos sirven para poner musica que se va a escuchar en la aplicacion
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