package com.aplicacionps;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

public class PantallaFinalTest {

    @Test
    public void testVaccine() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int year1 = Calendar.getInstance().get(Calendar.YEAR);
        int year2 = Calendar.getInstance().get(Calendar.YEAR);
        year = year + 2;
        assertEquals("Tu año estimada de vacunación es " + year, PantallaFinal.vaccine(20));
        year1 = year1 + 1;
        assertEquals("Tu año estimada de vacunación es " + year1, PantallaFinal.vaccine(55));

        assertEquals("Tu año estimada de vacunación es " + year2, PantallaFinal.vaccine(80));
    }
}