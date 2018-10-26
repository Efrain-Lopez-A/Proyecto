package com.example.daniel.game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Secundaria extends AppCompatActivity {
    Button btnDatos;
    EditText edtNombre;
    Intent inDatos;
    Boolean bGenero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundaria);
        btnDatos = findViewById(R.id.btnDatos);
        edtNombre = findViewById(R.id.edtNombre);
        inDatos = new Intent(this, Game.class);//falta la pagina

    }

    public void OnClickDatos (View v){
        String sCade = edtNombre.getText().toString();
        inDatos.putExtra("nombre",sCade);
        inDatos.putExtra("genero",bGenero);
        startActivity(inDatos);
        finish();
    }
    public void OnClickHombre (View v){
        bGenero = true;
        Toast.makeText(this, "Seleccionaste al macho", Toast.LENGTH_SHORT).show();
    }

    public void OnClickMujer (View v){
        bGenero = false;
        Toast.makeText(this, "Seleccionaste a laa hembra", Toast.LENGTH_SHORT).show();
    }
}

