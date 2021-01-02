package com.aplicacionps;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MenuJuego extends Fragment {

    public MenuJuego() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState
        );
    }
    @Override
    //Relacionamos la clase MenuJuego con su respectivo XML fragment_menu_juego
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu_juego, container, false);
    }

    //implemnetacion de los diferentes botones que hay en la pantalla de MenuJuego
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImageView btnHistoriaSupermercado = view.findViewById(R.id.btnHistoriaSupermercado);
        ImageButton btnVolver = view.findViewById(R.id.btnVolver);
        //implementacion de boton que lleva de un fragmento a una activity
        btnHistoriaSupermercado.setOnClickListener(new View.OnClickListener(){
            @Override
            //implementacion botón que nos llevará al hacer click en supermercado a la salida desde nuestro escenariocasa
            public void onClick(View v){
                Intent jugar= new Intent (getActivity(),EscenarioCasa.class);
                jugar.putExtra("datos","mas datos");
                startActivity(jugar);
            }
        });
        //implementacion de boton que lleva de un fragmento a otro fragment
        btnVolver.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Navigation.findNavController(v).navigate(R.id.menuInicio);
            }
        });
    }
}