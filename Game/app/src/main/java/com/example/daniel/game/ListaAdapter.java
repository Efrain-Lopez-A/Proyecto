package com.example.daniel.game;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListaAdapter extends ArrayAdapter<Saves> {
    Context cContexto;
    int iLayout;
    Saves[] saves;

    public ListaAdapter(@NonNull Context context, int resource, @NonNull Saves[] objects) {
        super(context, resource, objects);
        cContexto = context;
        iLayout = resource;
        saves = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ImageView imgVw;
        TextView txtNombreLista, txtNivel, txtNumPartida;

        View vFila = convertView;
        if(vFila == null){
            LayoutInflater liVista = ((Activity)cContexto).getLayoutInflater();
            vFila = liVista.inflate(iLayout,parent,false);
        }
        imgVw = vFila.findViewById(R.id.imgViewSave);
        txtNombreLista = vFila.findViewById(R.id.txtNombreLista);
        txtNivel = vFila.findViewById(R.id.txtNivel1);
        txtNumPartida = vFila.findViewById(R.id.txtNumPartida);

        Saves sActual = saves[position];
        imgVw.setImageResource(sActual.imagen);
        txtNumPartida.setText("Partida #" + (position + 1));
        txtNombreLista.setText(sActual.nombre);
        txtNivel.setText("Nivel: " + sActual.nivel);
        return vFila;


    }
}
