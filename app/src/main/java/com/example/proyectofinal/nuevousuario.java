package com.example.proyectofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class nuevousuario extends AppCompatActivity implements View.OnClickListener{

    Switch switch1;
    TextView textView4;

    private EditText TextUser;
    private EditText TextPassword1;
    private EditText TextPassword2;
    private Button btnRegistrar;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevousuario);

        //inicializamos el objeto firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        //variables para administrar el switch
        textView4 = (TextView) findViewById(R.id.textView4);
        switch1 = (Switch) findViewById(R.id.switch1);

        //variables para ingresar usuarios
        TextUser = (EditText) findViewById(R.id.TextUser);
        TextPassword1 = (EditText) findViewById(R.id.TextPassword1);
        TextPassword2 = (EditText) findViewById(R.id.TextPassword2);

        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.switch1){
            if (switch1.isChecked()){
                textView4.setText("Administrador");
            }else{
                textView4.setText("Operario");
            }
        }
        registrarUsuario();

    }
    private void registrarUsuario(){

        //Obtenemos el email y la contraseña desde las cajas de texto
        String usuario = TextUser.getText().toString().trim();
        String password1  = TextPassword1.getText().toString().trim();
        String password2  = TextPassword2.getText().toString().trim();

        //Verificamos que las cajas de texto no esten vacías
        if(TextUtils.isEmpty(usuario)){
            Toast.makeText(this,"Se debe ingresar un email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password1)){
            Toast.makeText(this,"Falta ingresar la contraseña",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password2)){
            Toast.makeText(this,"Falta repetir la contraseña",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password2)!=TextUtils.isEmpty(password1)){
            Toast.makeText(this,"Falta repetir la contraseña",Toast.LENGTH_LONG).show();
            return;
        }

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(usuario, password1)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){

                            Toast.makeText(nuevousuario.this,"Se ha registrado el usuario con el email: "+ TextUser.getText(),Toast.LENGTH_LONG).show();
                        }else{
                            //verificamos si existe colision de usuarios o existe algun problema
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(nuevousuario.this, "Ese usuario ya existe", Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(nuevousuario.this,"No se pudo registrar el usuario ",Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });

    }





}
