package com.example.proyectofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class Login extends AppCompatActivity implements View.OnClickListener{

    private EditText edtusuario;
    private EditText edtcontraseña;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login = findViewById(R.id.btnlogin);
        ImageButton nuevousuario = findViewById(R.id.imgbtnnewuser);
        login.setOnClickListener(this);
        nuevousuario.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();

        edtusuario = (EditText) findViewById(R.id.txtview5);
        edtcontraseña = (EditText) findViewById(R.id.edtcontraseña);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnlogin:
            //Implementar funcion registrar usuario
                loguearUsuario();
                break;
            case R.id.imgbtnnewuser:
                Intent imgbtnnewuser = new Intent(Login.this, nuevousuario.class);
                startActivity(imgbtnnewuser);
                break;
        }

    }

    private void loguearUsuario() {
        //obtenemos el email y la contraseña desde las cajas de texto
        final String usuario = edtusuario.getText().toString().trim();
        String contraseña  = edtcontraseña.getText().toString().trim();

        //Verificamos que las cajas de texto no esten vacías
        if(TextUtils.isEmpty(usuario)){
            Toast.makeText(this,"Se debe ingresar un email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(contraseña)){
            Toast.makeText(this,"Falta ingresar la contraseña",Toast.LENGTH_LONG).show();
            return;
        }


        //loguear usuario existente
        firebaseAuth.signInWithEmailAndPassword(usuario,contraseña)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //verificando el funcionamiento
                        if(task.isSuccessful()){
                            //capturando el nombre de usuario antes del @
                            int pos = usuario.indexOf("@");
                            String user = usuario.substring(0,pos);
                            Toast.makeText(Login.this,"Bienvenido!!: "+ edtusuario.getText(),Toast.LENGTH_LONG).show();
                            Intent button = new Intent(Login.this, MenuPrincipal.class);
                            button.putExtra(MenuPrincipal.user, user);
                            button.putExtra(RegistarWayPoint.user, user);
                            startActivity(button);


                        }else{
                            //verificamos si existe colision de usuarios o existe algun problema
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(Login.this, "Ese usuario ya existe", Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(Login.this,"No se pudo registrar el usuario ",Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });


    }
}
