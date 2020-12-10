package com.aplicacionps;

import android.text.style.ImageSpan;

public class SistemaContagio {

    private int porcentaje;

    public SistemaContagio(int porcentaje) {
        this.porcentaje = porcentaje;
    }

    public int getPorcentaje(){
        return porcentaje;
    }

}
