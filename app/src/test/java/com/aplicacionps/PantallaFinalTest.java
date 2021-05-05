package com.aplicacionps;

import org.junit.Test;
import android.content.Context;
import android.widget.TextView;



import androidx.test.core.app.ApplicationProvider;


import java.util.Calendar;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


public class PantallaFinalTest extends junit.framework.TestCase {
    private static final String fakeString = "Tu año estimado de vacunación es ";
    private int year = Calendar.getInstance().get(Calendar.YEAR);
    private Context context = ApplicationProvider.getApplicationContext();
    private TextView Mensaje;
    @Test
    public void vaccineTest() {
        PantallaFinal pantallaFinal = new PantallaFinal();
        int PorcentajeActual = 0;
        int aux = year+2;
        String stringToCompare = fakeString + aux;
        pantallaFinal.vaccine(PorcentajeActual)
        onView(withText(stringToCompare)).check(matches(isDisplayed()));
        onView();



        PorcentajeActual = 40;
        aux = year+1;
        stringToCompare = fakeString + aux;
        assertEquals(stringToCompare, Mensaje.getText());

        PorcentajeActual = 60;
        stringToCompare = fakeString + year;
        assertEquals(stringToCompare, Mensaje.getText());
        }



    }
}