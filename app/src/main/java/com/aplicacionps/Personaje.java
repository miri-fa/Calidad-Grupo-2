package com.aplicacionps;

public class Personaje {
    private double porcentajecontagio;
    private boolean mascarilla;

    public Personaje() {
        this.porcentajecontagio = 0.0;
        this.mascarilla = true;
    }

    public double getPorcentajecontagio() {
        return porcentajecontagio;
    }

    public void setPorcentajecontagio(double porcentajecontagio) {
        this.porcentajecontagio = porcentajecontagio;
    }

    public boolean isMascarilla() {
        return mascarilla;
    }

    public void setMascarilla(boolean mascarilla) {
        this.mascarilla = mascarilla;
    }

    public void incrementarPorcentaje(){
        this.porcentajecontagio += 0.2;
    }
}
