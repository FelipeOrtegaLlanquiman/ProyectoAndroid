package com.example.calendarbarber;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioGroup;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class RegisterUser extends AppCompatActivity {
    TextInputLayout til_name, til_mail, til_fechaNac, til_pass, til_rpass;
    Button btnRegister;
    RadioGroup rg_genero;
    int mDay,mMonth,mYear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        //referencia widgets
        til_name = findViewById(R.id.til_name);
        til_mail = findViewById(R.id.til_mail);
        til_fechaNac = findViewById(R.id.til_fechaNac);
        til_pass = findViewById(R.id.til_pass);
        til_rpass = findViewById(R.id.til_rpass);
        btnRegister = findViewById(R.id.btnRegister);
        rg_genero = findViewById(R.id.rg_genero);

        //evento onclick

        final Calendar calendar = Calendar.getInstance();
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        mMonth = calendar.get(Calendar.MONTH);
        mYear = calendar.get(Calendar.YEAR);


        til_fechaNac.getEditText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        if(month+1>=10){
                            if(day+1>10){
                                til_fechaNac.getEditText().setText(day+"/"+(month+1)+"/"+year);
                            }else{
                                til_fechaNac.getEditText().setText("0"+day+"/"+(month+1)+"/"+year);
                            }
                        }else{
                            if(day+1>10){
                                til_fechaNac.getEditText().setText(day+"/0"+(month+1)+"/"+year);
                            }else{
                                til_fechaNac.getEditText().setText("0"+day+"/0"+(month+1)+"/"+year);
                            }
                        }

                    }
                },mYear,mMonth, mDay);
            datePickerDialog.show();
            }
        });
    //accion del botom
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarDatos() == 0){
                    Intent intent = new Intent(RegisterUser.class,DashBoard.class);
                    startActivity(intent);
                }
            }
        });



}
    //validacion de campos
    private int validarDatos(){
        Validate validate = new Validate();
        int contador = 0;
        String name = til_name.getEditText().getText().toString();
        String mail = til_mail.getEditText().getText().toString();
        String date = til_fechaNac.getEditText().getText().toString();
        String pass = til_pass.getEditText().getText().toString();
        String rpass = til_rpass.getEditText().getText().toString();

        //validamos que nombre no este nulo
        if(validate.validarNulo(name)){
            //validamos formato del nombre
            if(validate.validarNombre(name)){
                til_name.setError(null);
            }else{
                til_name.setError(getString(R.string.error_name_wrongformat));
                contador++;
            }
        }else{

            til_name.setError(getString(R.string.error_name_wrongformat));
            contador++;
        }
        //validamos que la fechaNac no este nulo
        if(validate.validarNulo(date)){
            til_fechaNac.setError(null);
        }else{
            til_fechaNac.setError(getString(R.string.error_text_null));
            contador++;
        }
        //validamos que mail no este nulo
        if(validate.validarNulo(mail)){
            //validamos formato del mail
            if(validate.validarCorreo(mail)){
                til_mail.setError(null);
            }else{
                til_mail.setError(getString(R.string.error_mail_wrongformat));
                contador++;
            }
        }else{
            til_mail.setError(getString(R.string.error_text_null));
            contador++;
        }
        //validamos que PASS no este nulo
        if(validate.validarNulo(pass)){
            til_pass.setError(null);
        }else{
            til_pass.setError(getString(R.string.error_text_null));
            contador++;
        }
        //validamos que rPASS no este nulo
        if(validate.validarNulo(rpass)){
            til_rpass.setError(null);
        }else{
            til_rpass.setError(getString(R.string.error_text_null));
            contador++;
        }
        //validamos que la pass sea la misma
        if(rpass.equals(pass) && validate.validarNulo(pass) && validate.validarNulo(rpass)){
            til_rpass.setError(null);
            til_pass.setError(null);
        }else{
            if(validate.validarNulo(pass) && validate.validarNulo(rpass)){


            til_rpass.setError(getString(R.string.error_pass_wrongformat));
            til_pass.setError(getString(R.string.error_pass_wrongformat));
            contador++;
            }
        }
        return contador;
    }
}