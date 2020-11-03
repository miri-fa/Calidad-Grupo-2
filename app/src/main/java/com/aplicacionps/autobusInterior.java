package com.aplicacionps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class autobusInterior extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autobus_interior);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void sitioSenhora(View view){
        Intent sitioSenhora = new Intent (this, superfuera.class);
        startActivity(sitioSenhora);
    }

    public void sitioSolo(View view){
        Intent sitioSolo = new Intent (this, superfuera.class);
        startActivity(sitioSolo);
    }
}