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
    // se crea una variable para almacenar el porcentaje final obtenido y 2 textview para mostrar el porcentaje y el mensaje final
    private int porcentajeActual;
    private TextView mensaje;
    private TextView porciento;


    // AQUI SE RELACIONA LA CLASE pantallafinal.java CON SU XML activity_pantallafinal.xml
    //Y TAMBIEN SE PONE LA PANTALLA EN HORIZONTAL AL INICIARLA
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantallafinal);
        //Cambiamos la orientación para que la pantalla se pueda ver en horizontal y que
        //se muestre a pantalla completa, sin barra de notificaciones
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // se obtiene el porcentaje de la actividad anterior
        String Dato = getIntent().getStringExtra("dato");
        porcentajeActual = Integer.parseInt(Dato);
        // se relaciona el TextView "porciento" con el del activity y muestra el porcentaje final obtenido
        porciento = (TextView) findViewById(R.id.porciento);
        porciento.setText("PORCENTAJE = " + Dato);
        // se relaciona el TextView "mensaje" con el del activity
        mensaje = (TextView) findViewById(R.id.mensajeFinal);
        // se obtiene un numero aleatorio que dependiendo del porcentaje final hará que aparezca un mensaje diferente cada vez
        int numAleatorio = (int) (Math.random() * 100);
        // si el porcentaje final obtenido es 0 no se puede contagiar
        if (porcentajeActual == 0) {
            mensaje.setText("¡ENHORABUENA! No te has contagiado ya que tienes un 0 por ciento de probabilidades. Sigue así.");
        //si el porcentaje final es 100 o mas se contagiará si o si
        } else if (porcentajeActual <= 100) {
            mensaje.setText("Te has contagiado. Ten más cuidado ya que puedes enfermar a los que más quieres.");
        } else {
            // si el numero aleatorio obtenido es menor o igual que el porcentaje obtenido, se contagiará
            if (numAleatorio <= porcentajeActual) {
                // dependiendo del porcentaje que se haya obtenido al final aparecerá un mensaje diferente cada vez
                if (porcentajeActual <= 30) {
                    mensaje.setText("Mala suerte, te has contagiado. Incluso con poco porcentaje te puedes contagiar. Ten más cuidado la proxima vez");
                } else if ((porcentajeActual <= 60) && (porcentajeActual > 30)) {
                    mensaje.setText("Mala suerte, te ha contagiado. Ten más cuidado la proxima vez");
                } else {
                    mensaje.setText("Te has contagiado. Tienes que tener más cuidado si no quieres que te vuelva a pasar");
                }
            //por el contrario, si es mayor no se contagiará
            } else {
                // dependiendo del porcentaje que se haya obtenido al final aparecerá un mensaje diferente cada vez
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