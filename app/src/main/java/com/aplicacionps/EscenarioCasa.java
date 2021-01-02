package com.aplicacionps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class EscenarioCasa extends AppCompatActivity {
    // se crea un nuevo sistema de contagio con porcentaje igual a 0 y se crea un progressbar que será utilizado después
    SistemaContagio si= new SistemaContagio(0);
    private ProgressBar progressbar;

    //Relacionamos la clase EscenarioCasa con su respectivo XML activity_entrada_casa
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrada_casa);

        //Cambiamos la orientación para que la pantalla se pueda ver en horizontal y que
        //se muestre a pantalla completa, sin barra de notificaciones
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // se relaciona la progressbar creada con la progressbar del activity y se inicializa el porcentaje que muestra
        progressbar= (ProgressBar)findViewById(R.id.barra1);
        progressbar.setProgress(si.getPorcentaje());

    }

    //Este método mascarilla corresponde con la parte lógica del botón 'coger mascarilla y gel' del XML activity_entrada_casa,
    //y lo que hará es que nos llevará al siguiente escenario que corresponde la clase elegirCamino
    public void mascarilla(View view){
        // se obtiene el porcentaje y el booleano de la mascarilla de pone a true
        int valor = si.getPorcentaje();
        String bool = Boolean.toString(true);
        String val = String.valueOf(valor);
        Intent mascarilla = new Intent (this, elegirCamino.class);
        // se pasan los datos anteriormente sacados al siguiente activity y se inicializa
        mascarilla.putExtra("dato", val);
        mascarilla.putExtra("masc", bool);
        startActivity(mascarilla);
    }

    //Este método noMascarilla corresponde con la parte lógica del botón 'no coger nada' del XML activity_entrada_casa,
    //y por el momento, al no estar disponible la acción de incrementar el porcentaje de contagio de nuestro personaje,
    //nos saldrá por pantalla un toast con el mensaje 'No disponible'
    public void noMascarilla(View view){
        // se obtiene el porcentaje y el booleano de la mascarilla de pone a false
        int valor = si.getPorcentaje() + 10;
        String bool = Boolean.toString(false);
        String val = String.valueOf(valor);
        Intent noMascarilla = new Intent (this, elegirCamino.class);
        // se pasan los datos anteriormente sacados al siguiente activity y se inicializa
        noMascarilla.putExtra("dato", val);
        noMascarilla.putExtra("masc", bool);
        startActivity(noMascarilla);
    }

    //Estos 2 últimos métodos sirven para implementar la música del juego dentro de esta clase y para las demás,
    //siendo la música constante y permanente durante el tiempo que te encuentres dentro del juego
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