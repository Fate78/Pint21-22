package com.pint.roombookerfinal.Utilizador;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.pint.roombookerfinal.API.ApiClient;
import com.pint.roombookerfinal.API.ApiInterface;
import com.pint.roombookerfinal.Methods;
import com.pint.roombookerfinal.MethodsInterface;
import com.pint.roombookerfinal.Models.Utilizador;
import com.pint.roombookerfinal.R;
import com.pint.roombookerfinal.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilActivity extends AppCompatActivity {

    EditText username, nome_completo, email, data_nascimento;
    String s_username, s_nome_completo, s_email, s_data_nascimento;
    int id_user;
    final MethodsInterface methodsInterface = new Methods();
    final ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
    Button btn_guardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        username = findViewById(R.id.ed_username_perfil);
        nome_completo = findViewById(R.id.ed_nome_perfil);
        email = findViewById(R.id.ed_email_perfil);
        data_nascimento = findViewById(R.id.ed_data_nascimento_perfil);
        s_username = new SharedPrefManager(this).getUsername();
        id_user = new SharedPrefManager(this).getUserId();
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
                    s_nome_completo = utilizador.getNomeCompleto();
                    s_email = utilizador.getEmail();
                    s_data_nascimento = methodsInterface.formatDateForUser(utilizador.getDataNascimento());

                    nome_completo.setText(s_nome_completo);
                    email.setText(s_email);
                    data_nascimento.setText(s_data_nascimento);

                    btn_guardar = findViewById(R.id.btn_save);
                    btn_guardar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            String new_nome_completo = username.getText().toString();
                            String new_email = nome_completo.getText().toString();
                            String new_data_nascimento = methodsInterface.formatDateForAPI(data_nascimento.getText().toString());

                            if(!s_nome_completo.equals(new_nome_completo) || !s_email.equals(new_email) || !s_data_nascimento.equals(new_data_nascimento))
                            {
                                /*Utilizador utilizador = new Utilizador(new_nome_completo, new_email, new_data_nascimento);
                                updateUtilizador(id_user, utilizador, v.getContext());*/
                            }
                        }
                    });
                }
            }

            @Override
            public void onFailure(@NonNull Call<Utilizador> call, @NonNull Throwable t) {
                Log.e("Failure", t.getLocalizedMessage());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private String formatDate(String date){
        date = date.split("T")[0];
        return date;
    }

    public void updateUtilizador(int id, Utilizador utilizador, Context mCtx) {
        Call<Utilizador> updateUtilizador = apiInterface.updateUtilizador(id, utilizador);
        updateUtilizador.enqueue(new Callback<Utilizador>() {
            @Override
            public void onResponse(@NonNull Call<Utilizador> call, @NonNull Response<Utilizador> response) {
                Utilizador responseUtilizador = response.body();
                if (responseUtilizador != null) {
                    Toast.makeText(mCtx, "Perfil Atualizado",
                            Toast.LENGTH_LONG).show();
                } else {
                    System.out.println(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Utilizador> call, @NonNull Throwable t) {
                Log.e("Failure", t.getLocalizedMessage());
            }
        });
    }
}