package com.pint.room_booker.utilizador;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.pint.room_booker.R;

public class PerfilActivity extends AppCompatActivity {
    private ContentLogin contentLogin;
    EditText username, nome_completo, email, data_nascimento;
    Button btn_guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        contentLogin = new ContentLogin();
        username = (EditText) findViewById(R.id.perfil_username);
        nome_completo = (EditText) findViewById(R.id.perfil_name);
        email = (EditText) findViewById(R.id.perfil_email);
        data_nascimento = (EditText) findViewById(R.id.perfil_nascimento);
        btn_guardar = (Button) findViewById(R.id.btn_guardar);

        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}