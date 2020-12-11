package com.aplicacionps;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class irAndando extends AppCompatActivity {

    private ProgressBar progressbar;
    private int porcentajeActual;
    private boolean mascarilla;

    //Relacionamos la clase irAndando con su respectivo XML activity_ir_andando

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ir_andando);

        //Cambiamos la orientación para que la pantalla se pueda ver en horizontal y que
        //se muestre a pantalla completa, sin barra de notificaciones

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        String Dato = getIntent().getStringExtra("dato");
        String Masc = getIntent().getStringExtra("masc");
        porcentajeActual = Integer.parseInt(Dato);
        progressbar= (ProgressBar)findViewById(R.id.barra1);
        progressbar.setProgress(porcentajeActual);
        mascarilla = Boolean.valueOf(Masc);
    }

    //Este método CalleSinGente corresponde con la parte lógica del botón 'calle vacía, pero tardo más' del XML activity_ir_andando,
    //y lo que hará es que nos llevará al siguiente escenario que corresponde la clase DentroDeLaCalle

    public void CalleSinGente(View view){
        String bool = Boolean.toString(mascarilla);
        int valor= porcentajeActual;
        String val= String.valueOf(valor);
        Intent CalleSinGente= new Intent(this, DentroDeLaCalle.class);
        CalleSinGente.putExtra("dato", val);
        CalleSinGente.putExtra("masc", bool);
        startActivity(CalleSinGente);
    }

    //Este método CalleConGente corresponde con la parte lógica del botón 'calle con gente' del XML activity_ir_andando,
    //y por el momento, al no estar disponible la acción de incrementar el porcentaje de contagio de nuestro personaje,
    //nos saldrá por pantalla un toast con el mensaje 'No disponible'

    public void CalleConGente(View view){
        String bool = Boolean.toString(mascarilla);
        int valor= porcentajeActual +10;
        String val= String.valueOf(valor);
        Intent CalleConGente = new Intent (this, DentroDeLaCalle.class);
        CalleConGente.putExtra("dato", val);
        CalleConGente.putExtra("masc", bool);
        startActivity(CalleConGente);
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
