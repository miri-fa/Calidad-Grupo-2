package com.aplicacionps;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Cambiamos la orientación para que la pantalla se pueda ver en horizontal y que
        //se muestre a pantalla completa, sin barra de notificaciones
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }
    //implementacion del boton jugar que lleva al menu de juego
    public void jugar(View view){
        Intent jugar= new Intent (this, Jugar.class);
        startActivity(jugar);
    }
    //implementacion del boton logros que lleva al menu de logros
    public void logros(View view){
        Intent logros = new Intent (this, Logros.class);
        startActivity(logros);
    }
    //implementacion del como se juga que lleva al menu de como se juega
    public void comoSeJuega(View view){
        Intent csj = new Intent (this, ComoJugar.class);
        startActivity(csj);
    }
    //implementacion del boton ajustes que lleva al menu de ajustes
    public void ajustes(View view){
        Intent sett = new Intent (this, Ajustes.class);
        startActivity(sett);
    }
    //Estos 2 últimos métodos sirven para implementar la música del juego dentro de esta clase y para las demás,
    //siendo la música constante y permanente durante el tiempo que te encuentres dentro del juego
    @Override
    public void onPause() {
        super.onPause();

        //IMPLEMENTACIÓN DEL SERVICIO DE AUDIO (INTERRUMPE EL SERVICIO CUANDO SE SALE DE ESTA ACTIVITY
        Intent i = new Intent(this, AudioService.class);
        i.putExtra("action", AudioService.PAUSE);
        startService(i);
    }

    @Override
    public void onResume() {
        super.onResume();

        //SE RECUPERA DE VALORES GUARDADOS EL VALOR DEL SWITCH SILENCIAR Y SE ALMACENA EN valorboton.
        SharedPreferences sharedPreferences = getSharedPreferences("save", MODE_PRIVATE);
        Boolean valordelboton = sharedPreferences.getBoolean("value", false);

        //DEPENDIENDO DEL CONTENIDO DE valorboton SE REANUDA EL SERVICIO DE AUDIO EN ESTA ACTIVITY
        if (valordelboton != true) {
            Intent i = new Intent(this, AudioService.class);
            i.putExtra("action", AudioService.START);
            startService(i);
        }
    }
}