package com.aplicacionps;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DentroDeLaCalle extends AppCompatActivity {

    //Relacionamos la clase DentroDeLaCalle con su respectivo XML activity_dentro_de_la_calle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dentro_de_la_calle);

        //Cambiamos la orientación para que la pantalla se pueda ver en horizontal y que
        //se muestre a pantalla completa, sin barra de notificaciones

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    //Este método MeLaQuito corresponde con la parte lógica del botón 'Me la quito, necesito respirar' del XML activity_dentro_de_la_calle,
    //y por el momento, al no estar disponible la acción de incrementar el porcentaje de contagio de nuestro personaje,
    //nos saldrá por pantalla un toast con el mensaje 'No disponible'

    public void MeLaQuito(View view){
        Toast.makeText(this, "No disponible", Toast.LENGTH_SHORT).show();
    }

    //Este método NoMeLaQuito corresponde con la parte lógica del botón 'No me la quito' del XML activity_dentro_de_la_calle,
    //y lo que hará es que nos llevará al siguiente escenario que corresponde la clase superfuera

    public void NoMeLaQuito(View view){
        Intent NoMeLaQuito= new Intent(this, superfuera.class);
        startActivity(NoMeLaQuito);
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