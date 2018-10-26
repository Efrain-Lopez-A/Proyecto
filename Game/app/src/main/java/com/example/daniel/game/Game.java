package com.example.daniel.game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Game extends AppCompatActivity {
    TextView txtNombre;
    Intent iSecun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        txtNombre = findViewById(R.id.txtNombre);
        iSecun = getIntent();
        txtNombre.setText(iSecun.getStringExtra("nombre"));
    }
}
