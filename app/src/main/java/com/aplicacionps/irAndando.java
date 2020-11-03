package com.aplicacionps;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class irAndando extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ir_andando);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void CalleSinGente(View view){
        Intent CalleSinGente= new Intent(this, DentroDeLaCalle.class);
        startActivity(CalleSinGente);

    }

    public void CalleConGente(View view){
        Toast.makeText(this, "No disponible", Toast.LENGTH_SHORT).show();
    }

}
