package com.pint.roombookerfinal.Utilizador;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.pint.roombookerfinal.R;

public class PerfilActivity extends AppCompatActivity {

    private ContentPerfil contentPerfil;
    private ContentLogin contentLogin;
    SharedPreferences sharedPreferences;
    Context mCtx;

    EditText username, nome_completo, email, data_nascimento;
    Button btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        contentLogin = new ContentLogin();
        contentPerfil = new ContentPerfil();
        username = (EditText) findViewById(R.id.edtext_username);
        nome_completo = (EditText) findViewById(R.id.edtext_nome);
        email = (EditText) findViewById(R.id.edtext_email_perfil);
        data_nascimento = (EditText) findViewById(R.id.edtext_data_nascimento);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username.setText(contentLogin.getNome_utilizador());
                nome_completo.setText(contentLogin.getNome_completo());
                email.setText(contentLogin.getEmail());
                data_nascimento.setText(contentLogin.getData_nascimento());
            }
        });
    }
}