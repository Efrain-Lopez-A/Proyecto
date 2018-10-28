package com.example.daniel.game;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class Principal extends AppCompatActivity implements ListView.OnItemClickListener {
    Saves[] save = {new Saves("Uziel","Partida1",true, 1, R.drawable.hombre),
            new Saves("Dany","Partida 2",true, 1,R.drawable.hombre),
            new Saves("Berenice","Partida 3",true, 1,R.drawable.mujer)};
    Button btnComenzar, btnNuevo, btnSalir;
    Intent inCambio;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_principal);
        btnComenzar = findViewById(R.id.btnComenzar);
        btnNuevo = findViewById(R.id.btnNuevo);
        btnSalir = findViewById(R.id.btnSalir);
        inCambio = new Intent(this,Secundaria.class);
    }

    public void OnClickSecun (View v){
        startActivity(inCambio);
    }

    public void OnClickSalir (View v){
        finish();
    }

    public void OnClickComenzar (View v){
        final Dialog dLista = new Dialog(this);
        dLista.setContentView(R.layout.lista_saves);
        lista = dLista.findViewById(R.id.list);
        lista.setAdapter(new ListaAdapter(this,R.layout.layout_lista,save));
        lista.setOnItemClickListener(this);
        dLista.create();
        dLista.show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent iPartida = new Intent(this,Game.class);
        iPartida.putExtra("nombre",save[position].nombre);
        startActivity(iPartida);
    }
}
