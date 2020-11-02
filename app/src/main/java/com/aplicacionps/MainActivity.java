package com.aplicacionps;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {


    MediaPlayer click;
    ImageButton boton_ajustes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        boton_ajustes = (ImageButton)findViewById(R.id.imageButton12);
        click = MediaPlayer.create(this, R.raw.sonido_click);

        boton_ajustes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                click.start();
            }
        });
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

}