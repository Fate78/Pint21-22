package com.pint.room_booker.utilizador;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.pint.room_booker.MainActivity;
import com.pint.room_booker.R;
import com.pint.room_booker.SharedPreferencesHelper;
import com.pint.room_booker.utilizador.ContentLogin;

import org.json.JSONArray;

public class LoginActivity extends AppCompatActivity {
    private ContentLogin contentLogin;
    EditText email, password;
    Button login;
    TextInputLayout emailError, passError;

    boolean isEmailValid, isPasswordValid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        contentLogin = new ContentLogin();
        email = (EditText) findViewById(R.id.ed_email);
        password = (EditText) findViewById(R.id.ed_password);
        login = (Button) findViewById(R.id.btn_login);
        emailError = (TextInputLayout) findViewById(R.id.error_email);
        passError = (TextInputLayout) findViewById(R.id.error_password);
        
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate_login();
            }
        });
    }

    private void validate_login()
    {
        if(email.getText().toString().isEmpty())
        {
            emailError.setError("Introduza o seu e-mail");
            isEmailValid= false;
            emailError.setVisibility(View.VISIBLE);
        } else {

            isEmailValid= true;
            emailError.setErrorEnabled(false);
        }

        if(password.getText().toString().isEmpty())
        {
            passError.setError("Introduza a sua palavra-passe");
            isPasswordValid= false;
        } else {

            isPasswordValid= true;
            passError.setErrorEnabled(false);
            passError.setVisibility(View.VISIBLE);
        }

        if(isPasswordValid && isEmailValid)
        {
            this.contentLogin.setEmail_input(this.email.getText().toString());
            this.contentLogin.setPassword_input(this.password.getText().toString());
            this.contentLogin.loginEmail();

            if(contentLogin.loginEmail()) {
                this.contentLogin.loginPassword();
                if (contentLogin.loginPassword()) {
                    this.contentLogin.verificarAtivo();
                    if(contentLogin.verificarAtivo()){
                        //vai colocar o id na classe Shared Preferences
                        // vai bloquear a opção de voltar para a página de login
                        SharedPreferencesHelper.SessionStateSharedPreferences(LoginActivity.this, true);
                        finish();

                        Toast.makeText(getApplicationContext(), "Bem Vindo " + this.contentLogin.getNome_utilizador() + "!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("id_utilizador", this.contentLogin.getId());
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(), "A sua conta ainda não foi aprovada!", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getApplicationContext(), "Palavra Passe Errada!", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(getApplicationContext(), "Email errado!", Toast.LENGTH_SHORT).show();
            }
        }

    }
}