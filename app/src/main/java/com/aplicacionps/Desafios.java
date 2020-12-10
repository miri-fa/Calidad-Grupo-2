package com.aplicacionps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class Desafios extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desafios);
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

    //Metodo para poder volver a la pantalla inicio
    public void volver(View view){
        Intent volver= new Intent (this, MainActivity.class);
        startActivity(volver);
    }

    public void irdesafiobronce01(View view) {
        Intent bronce01= new Intent (this, Bronce_01_PrimeraHistoria.class);
        startActivity(bronce01);
    }

    public void irdesafiobronce02(View view) {
        Intent bronce02= new Intent (this, Bronce_02_ConductorDePrimera.class);
        startActivity(bronce02);
    }
    public void irdesafioplata01(View view) {
        Intent plata01= new Intent (this, Plata_01_SindromeIndianaJones.class);
        startActivity(plata01);
    }
    public void irdesafioplata02(View view) {
        Intent plata02= new Intent (this, Plata_02_HoustonTenemosUnProblema.class);
        startActivity(plata02);
    }

    public void irdesafiooro01(View view) {
        Intent oro01= new Intent (this, Oro_01_CaminoDelNinja.class);
        startActivity(oro01);
    }
    public void irdesafiooro02(View view) {
        Intent oro02= new Intent (this, Oro_02_Alumno_Excelente.class);
        startActivity(oro02);
    }

    //Los siguientes 2 métodos sirven para implementar la música del juego dentro de esta clase y para las demás,
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