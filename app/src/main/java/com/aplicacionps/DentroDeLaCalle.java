package com.aplicacionps;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DentroDeLaCalle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dentro_de_la_calle);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


    public void MeLaQuito(View view){
        Toast.makeText(this, "No disponible", Toast.LENGTH_SHORT).show();
    }
    public void NoMeLaQuito(View view){
        Intent NoMeLaQuito= new Intent(this, superfuera.class);
        startActivity(NoMeLaQuito);
    }

}
