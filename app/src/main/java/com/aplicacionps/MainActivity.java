package com.aplicacionps;

import android.content.Intent;
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
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }
    public void jugar(View view){
        Intent jugar= new Intent (this, Jugar.class);
        startActivity(jugar);
    }
    public void logros(View view){
        Intent logros = new Intent (this, Logros.class);
        startActivity(logros);
    }
    public void comoSeJuega(View view){
        Intent csj = new Intent (this, ComoJugar.class);
        startActivity(csj);
    }
    public void ajustes(View view){
        Intent sett = new Intent (this, Ajustes.class);
        startActivity(sett);
    }
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
        Intent i = new Intent(this, AudioService.class);
        i.putExtra("action", AudioService.START);
        startService(i);
    }

}