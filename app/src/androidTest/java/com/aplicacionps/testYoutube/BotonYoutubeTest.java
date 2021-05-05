package com.aplicacionps.testYoutube;


import android.content.Context;
import io.cucumber.junit.Cucumber;

import org.junit.runner.RunWith;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;



import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;



import static androidx.test.InstrumentationRegistry.getContext;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


@RunWith(Cucumber.class)
public class BotonYoutubeTest extends junit.framework.TestCase {

    Context context;

    public void setUp() throws Exception {
        super.setUp();

        context = getContext();

        assertNotNull(context);

    }


    @Given("^que estoy en el menú principal $")
    public void que_estoy_en_el_men_principal() throws Throwable {
            // Context of the app under test.
            Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            assertEquals("com.aplicacionps", appContext.getPackageName());
    }



    @When("^hago click sobre el botón de youtube $")
    public void hago_click_sobre_el_botn_de_youtube() throws Throwable {
        onView(withText("botonyt")).perform(click());
        onView(ViewMatchers.withText("botonyt"))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }




    @Then("^soy redirigido al vídeo $")
    public void soy_redirigido_al_vdeo() throws Throwable {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("https://www.youtube.com/", "False");
    }
}
