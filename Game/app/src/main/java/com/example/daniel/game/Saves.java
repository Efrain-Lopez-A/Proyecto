package com.example.daniel.game;

public class Saves {

    String nombre,partida;
    boolean genero;
    int nivel,imagen;

    public Saves(String nom,String par, boolean ge, int nv, int img){
        partida = par;
        nombre = nom;
        genero = ge;
        nivel = nv;
        imagen = img;
    }
}
