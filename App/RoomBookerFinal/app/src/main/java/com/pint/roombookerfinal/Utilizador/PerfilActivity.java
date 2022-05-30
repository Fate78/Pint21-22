package com.pint.roombookerfinal.Utilizador;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.pint.roombookerfinal.API.ApiClient;
import com.pint.roombookerfinal.API.ApiInterface;
import com.pint.roombookerfinal.Models.Utilizador;
import com.pint.roombookerfinal.R;
import com.pint.roombookerfinal.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilActivity extends AppCompatActivity {

    EditText username, nome_completo, email, data_nascimento;
    String s_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        username = findViewById(R.id.edtext_username);
        nome_completo = findViewById(R.id.edtext_nome);
        email = findViewById(R.id.edtext_email_perfil);
        data_nascimento = findViewById(R.id.edtext_data_nascimento);
        s_username = new SharedPrefManager(this).getUsername();
        username.setText(s_username);

        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<Utilizador> call = apiInterface.getUtilizador(s_username);

        call.enqueue(new Callback<Utilizador>() {
            @Override
            public void onResponse(@NonNull Call<Utilizador> call,
                                   @NonNull Response<Utilizador> response)
            {
                if (response.body() != null) {
                    Log.e("Success", response.body().toString());
                    Utilizador utilizador = response.body();
                    nome_completo.setText(utilizador.getNomeCompleto());
                    email.setText(utilizador.getEmail());
                    data_nascimento.setText(formatDate(utilizador.getDataNascimento()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<Utilizador> call, @NonNull Throwable t) {
                Log.e("Failure", t.getLocalizedMessage());
            }
        });
    }

    private String formatDate(String date){
        date = date.split("T")[0];
        return date;
    }
}