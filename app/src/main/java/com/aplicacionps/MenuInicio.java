package com.aplicacionps;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;

import androidx.navigation.Navigation;

import android.widget.Button;
import android.widget.ImageButton;

import static android.content.Context.MODE_PRIVATE;

public class MenuInicio extends Fragment {

    boolean boton_pulsado = true;

    public MenuInicio() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menuinicio, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageButton btnjugar = view.findViewById(R.id.btnjugar);
        ImageButton btncomojugar = view.findViewById(R.id.btncomojugar);
        ImageButton btndesafios = view.findViewById(R.id.btndesafios);
        ImageButton btnajustes = view.findViewById(R.id.btnajustes);
        ImageButton btnyoutube = view.findViewById(R.id.btnytutorial);
        ImageButton idioma = view.findViewById(R.id.idioma_button);
        //ImageButton btnIdioma = view.findViewById(R.id.idioma_button);
        //Implementacion de boton que lleva de un fragmento a otro fragment
        btnjugar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Navigation.findNavController(v).navigate(R.id.menuJuego);
            }
        });
        btncomojugar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Navigation.findNavController(v).navigate(R.id.tutorial0);
            }
        });
        btndesafios.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Navigation.findNavController(v).navigate(R.id.menuDesafios);
            }
        });
        btnajustes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                    Intent ajustes= new Intent (getActivity(),Ajustes.class);
                ajustes.putExtra("datos","mas datos");
                startActivity(ajustes);
            }
        });
        btnyoutube.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){

                String link = "https://www.youtube.com/";
                Intent viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse(link));
                startActivity(viewIntent);
            }
        });

        idioma.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v) {

                if (boton_pulsado) {
                    boton_pulsado = false;
                    idioma.setBackgroundResource(R.drawable.banderabritanica3);

                } else {
                    boton_pulsado = true;
                    idioma.setBackgroundResource(R.drawable.bandera_espana);
                }
            }
        });


    }

}