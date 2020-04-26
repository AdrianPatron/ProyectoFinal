package com.example.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RegistarWayPoint extends AppCompatActivity implements View.OnClickListener{

    public static final String user = "names";
    TextView textView9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar_way_point);

        Button next = findViewById(R.id.button3);
        next.setOnClickListener(this);

        textView9 = (TextView) findViewById(R.id.textView9);
        String user = getIntent().getStringExtra("names");
        textView9.setText(user);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button3:
                Intent button3 = new Intent(RegistarWayPoint.this, Fotografia.class);
                startActivity(button3);
                break;
        }


    }
}
