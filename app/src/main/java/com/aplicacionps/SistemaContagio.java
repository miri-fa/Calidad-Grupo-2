package com.aplicacionps;

import android.text.style.ImageSpan;

/* El sistema de contagio se usa para inicializar y obtener el porcentaje de contagio que va a tener
el usuario en el primer escenario de la historia jugable, con el que posteriormente se irá trasladando
este valor a los siguientes escenarios de dicha historia jugable
 */


public class SistemaContagio {

    private int porcentaje;

    public SistemaContagio(int porcentaje) {
        this.porcentaje = porcentaje;
    }

    public int getPorcentaje(){
        return porcentaje;
    }

}
