package com.aplicacionps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

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
        Intent efectivo = new Intent (this, pantallafinal.class);
        startActivity(efectivo);
    }

    public void tarjeta(View view){
        Intent tarjeta = new Intent (this, pantallafinal.class);
        startActivity(tarjeta);
    }
}