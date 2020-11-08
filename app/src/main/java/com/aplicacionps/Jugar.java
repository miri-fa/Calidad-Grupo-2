package com.aplicacionps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;


public class Jugar extends AppCompatActivity {

    //Relacionamos la clase Jugar con su respectivo XML activity_jugar
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugar);

        //Cambiamos la orientación para que la pantalla se pueda ver en horizontal y que
        //se muestre a pantalla completa, sin barra de notificaciones

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    @Override
    protected void onStart() {
        super.onStart();
        // La actividad está a punto de hacerse visible.
    }

    //Este método supermercado corresponde con la parte lógica del botón 'supermercado' del XML activity_jugar,
    //y lo que hará es que nos llevará al siguiente escenario que corresponde la clase EscenarioCasa
    public void supermercado(View view){
        Intent supermercado = new Intent (this, EscenarioCasa.class);
        startActivity(supermercado);
    }

    //Este método instituto corresponde con la parte lógica del botón 'Instituto' del XML activity_jugar,
    //y por el momento, al no estar disponible la historia jugable Instituto nos saldrá por pantalla
    //un toast con el mensaje 'No disponible'
    public void instituto(View view){
        Toast.makeText(this, "No disponible", Toast.LENGTH_SHORT).show();
    }

    //Este método volver corresponde con la parte lógica del botón 'flecha_atras' del XML activity_jugar,
    //y lo que hará es que nos llevará a la pantalla de inicio, correspondiente a la clase MainActivity

    public void volver(View view){
        Intent volver= new Intent (this, MainActivity.class);
        startActivity(volver);

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
    @Override
    protected void onStop() {
        super.onStop();
        // La actividad ya no es visible (ahora está "detenida")
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // La actividad está a punto de ser destruida.
    }
}