package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuPrincipal extends AppCompatActivity implements View.OnClickListener{

    public static final String user = "names";
    TextView txtview5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        Button CrearRuta = findViewById(R.id.button2);
        Button btnsalir = findViewById(R.id.btnsalir);
        CrearRuta.setOnClickListener(this);
        btnsalir.setOnClickListener(this);

        // asociamos el usuario
        txtview5 = (TextView) findViewById(R.id.txtview5);
        String user = getIntent().getStringExtra("names");
        txtview5.setText(user);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button2:
                Intent button2 = new Intent(MenuPrincipal.this, RegistarWayPoint.class);
                startActivity(button2);
                break;
            case R.id.btnsalir:
                finish();
                break;
        }


    }
}
