package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Fotografia extends AppCompatActivity implements View.OnClickListener{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotografia);

        Button next = findViewById(R.id.button4);
        Button finalruta = findViewById(R.id.button5);
        next.setOnClickListener(this);
        finalruta.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button4:
                Intent button4 = new Intent(Fotografia.this, RegistarWayPoint.class);
                startActivity(button4);
                break;
            case R.id.button5:
                Intent button5 = new Intent(Fotografia.this, MenuPrincipal.class);
                startActivity(button5);
                break;
        }

    }
}
