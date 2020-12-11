package com.aplicacionps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class pantallafinal extends AppCompatActivity {

    private int porcentajeActual;
    private TextView mensaje;
    private TextView porciento;


    // AQUI SE RELACIONA LA CLASE pantallafinal.java CON SU XML activity_pantallafinal.xml
    //Y TAMBIEN SE PONE LA PANTALLA EN HORIZONTAL AL INICIARLA
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantallafinal);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        String Dato = getIntent().getStringExtra("dato");
        porcentajeActual = Integer.parseInt(Dato);
        porciento = (TextView) findViewById(R.id.porciento);
        porciento.setText("PORCENTAJE = " + Dato);
        mensaje = (TextView) findViewById(R.id.mensajeFinal);
        int numAleatorio = (int) (Math.random() * 100);
        if (porcentajeActual == 0) {
            mensaje.setText("¡ENHORABUENA! No te has contagiado ya que tienes un 0 por ciento de probabilidades. Sigue así.");
        } else if (porcentajeActual == 100) {
            mensaje.setText("Te has contagiado. Ten más cuidado ya que puedes enfermar a los que más quieres.");
        } else {
            if (numAleatorio <= porcentajeActual) {
                if (porcentajeActual <= 30) {
                    mensaje.setText("Mala suerte, te has contagiado. Incluso con poco porcentaje te puedes contagiar. Ten más cuidado la proxima vez");
                } else if ((porcentajeActual <= 60) && (porcentajeActual > 30)) {
                    mensaje.setText("Mala suerte, te ha contagiado. Ten más cuidado la proxima vez");
                } else {
                    mensaje.setText("Te has contagiado. Tienes que tener más cuidado si no quieres que te vuelva a pasar");
                }
            } else {
                if (porcentajeActual <= 30) {
                    mensaje.setText("No te has contagiado aunque habian pocas posibilidades. Intentalo de nuevo para bajarlas.");
                } else if ((porcentajeActual <= 60) && (porcentajeActual > 30)) {
                    mensaje.setText("Has tenido suerte y no te has contagiado. Puedes mejorar este resultado. Intentálo de nuevo");
                } else {
                    mensaje.setText("Has tenido muchiiiisima suerte. Pero puede que algún día no la tengas y lo pilles. Ten cuidado.");
                }
            }
        }
    }


    //AL PULSAR EL BOTON DE VOLVER A JUGAR, EL JUEGO TE LLEVA A LA CASA DEL JUGADOR PARA INICIAR DE NUEVO EL CAMINO
    public void volverajugar(View view){
        Intent volverajugar = new Intent (this, EscenarioCasa.class);
        startActivity(volverajugar);
    }

    //AL PULSAR EL BOTÓN DE MENÚ, TE LLEVA A LA PANTALLA PRINCIPAL DEL JUEGO
    public void menuprincipal(View view){
        Intent menuprincipal = new Intent (this, MainActivity.class);
        startActivity(menuprincipal);
    }

    // LOS SIGUIENTES MÉTODOS SIRVEN PARA PONER LA MUSICA QUE SE VA A ESCUCHAR A LO LARGO DE LA APLICACIÓN
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