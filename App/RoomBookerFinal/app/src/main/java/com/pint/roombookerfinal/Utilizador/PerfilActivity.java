package com.pint.roombookerfinal.Utilizador;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.pint.roombookerfinal.ApiClient;
import com.pint.roombookerfinal.ApiInterface;
import com.pint.roombookerfinal.Models.Utilizador;
import com.pint.roombookerfinal.R;
import com.pint.roombookerfinal.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilActivity extends AppCompatActivity {

    private ContentPerfil contentPerfil;
    private ContentLogin contentLogin;
    private SharedPrefManager sharedPrefManager;
    Context mCtx;

    EditText username, nome_completo, email, data_nascimento;
    Button btn_save;
    Integer id_utilizador;
    String s_username;

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
        s_username = new SharedPrefManager(this).getUsername();
        username.setText(s_username);

        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<Utilizador> call = apiInterface.getUtilizador(s_username);

        call.enqueue(new Callback<Utilizador>() {
            @Override
            public void onResponse(Call<Utilizador> call, Response<Utilizador> response) {
                Log.e("Success", response.body().toString());
                Utilizador utilizador = response.body();
                nome_completo.setText(utilizador.getNomeCompleto());
                email.setText(utilizador.getEmail());
                data_nascimento.setText(formatDate(utilizador.getDataNascimento()));

            }

            @Override
            public void onFailure(Call<Utilizador> call, Throwable t) {
                Log.e("Failure", t.getLocalizedMessage());
            }
        });
    }

    private String formatDate(String date){
        date = date.split("T")[0];
        return date;
    }
}