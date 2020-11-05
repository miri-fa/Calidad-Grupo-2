package com.aplicacionps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

public class colaparapagar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colaparapagar);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }
    //clase personaje...+%
    public void efectivo(View view){
        Toast.makeText(this, "No disponible", Toast.LENGTH_SHORT).show();
        //Intent efectivo = new Intent (this, pantallafinal.class);
        //startActivity(efectivo);
    }

    public void tarjeta(View view){
        Intent tarjeta = new Intent (this, pantallafinal.class);
        startActivity(tarjeta);
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
        SharedPreferences sharedPreferences = getSharedPreferences("save", MODE_PRIVATE);
        Boolean valordelboton = sharedPreferences.getBoolean("value", false);
        if (valordelboton != true) {
            Intent i = new Intent(this, AudioService.class);
            i.putExtra("action", AudioService.START);
            startService(i);
        }
    }
}