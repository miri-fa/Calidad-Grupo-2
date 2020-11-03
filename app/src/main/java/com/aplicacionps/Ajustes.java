package com.aplicacionps;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

public class Ajustes extends AppCompatActivity {

    Switch audio;
    Boolean sonido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
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

    public void onClick(View view) {
        if (view.getId() == R.id.switch1){
            if (audio.isChecked()){
                sonido = true;
            }
            else {
                sonido = false;
            }
        }
    }

    public Boolean getSonido() {
        return sonido;
    }

    public void setSonido(Boolean sonido) {
        this.sonido = sonido;
    }
}