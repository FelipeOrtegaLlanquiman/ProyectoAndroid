package com.example.calendarbarber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
    private TextInputLayout til_pass, til_user;
    private Button btn_login, btn_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        til_user = findViewById(R.id.til_user);
        til_pass = findViewById(R.id.til_pass);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Validate validate = new Validate();
                String correo = til_user.getEditText().getText().toString();
                String pass = til_pass.getEditText().getText().toString();
                if(validarDatos() == 0){
                    Intent intent = new Intent(view.getContext(),DashBoard.class);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                }
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(),RegisterUser.class);
                startActivity(intent);
            }
        });

    }
    //validacion de campos
    private int validarDatos(){
        Validate validate = new Validate();
        int contador = 0;
        String mail = til_user.getEditText().getText().toString();
        String pass = til_pass.getEditText().getText().toString();

        if(validate.validarNulo(mail)){
            if(validate.validarCorreo(mail)){
                til_user.setError(null);
            }else{
                til_user.setError(getString(R.string.error_mail_wrongformat));
                contador++;
            }
        }else{

            til_user.setError(getString(R.string.error_mail_null));
            contador++;
        }
        if(validate.validarNulo(pass)){
            til_pass.setError(null);
        }else{
            til_pass.setError(getString(R.string.error_text_null));
            contador++;
        }
        return contador;
    }
}