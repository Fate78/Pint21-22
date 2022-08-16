package com.pint.roombookerfinal.Utilizador;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.pint.roombookerfinal.API.ApiClient;
import com.pint.roombookerfinal.API.ApiInterface;
import com.pint.roombookerfinal.Methods;
import com.pint.roombookerfinal.MethodsInterface;
import com.pint.roombookerfinal.Models.Utilizador;
import com.pint.roombookerfinal.R;
import com.pint.roombookerfinal.SharedPrefManager;

import java.time.LocalDate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilActivity extends AppCompatActivity {

    EditText username, nome_completo, email, data_nascimento;
    String s_username, s_nome_completo, s_email, s_data_nascimento, api_data_nascimento, s_palavra_passe;
    boolean verificado, ativo;
    int id_user, id_tipo;
    final MethodsInterface methodsInterface = new Methods();
    final ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
    Button btn_guardar;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        progressBar = findViewById(R.id.progressBarPerfil);
        btn_guardar = findViewById(R.id.btn_save);
        progressBar.setVisibility(View.VISIBLE);
        btn_guardar.setVisibility(View.GONE);

        username = findViewById(R.id.ed_username_perfil);
        nome_completo = findViewById(R.id.ed_nome_perfil);
        email = findViewById(R.id.ed_email_perfil);
        data_nascimento = findViewById(R.id.ed_data_nascimento_perfil);
        s_username = new SharedPrefManager(this).getUsername();
        id_user = new SharedPrefManager(this).getUserId();
        username.setText(s_username);

        methodsInterface.disableSoftInputFromAppearing(data_nascimento);
        username.setEnabled(false);
        email.setEnabled(false);

        String AuthToken = new SharedPrefManager(this).getAuthToken();

        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<Utilizador> call = apiInterface.getUtilizador(s_username, AuthToken);

        call.enqueue(new Callback<Utilizador>() {
            @Override
            public void onResponse(@NonNull Call<Utilizador> call,
                                   @NonNull Response<Utilizador> response)
            {
                if (response.body() != null) {
                    Log.e("Success", response.body().toString());
                    Utilizador utilizador = response.body();

                    id_tipo = utilizador.getIdTipo();
                    s_nome_completo = utilizador.getNomeCompleto();
                    s_email = utilizador.getEmail();
                    api_data_nascimento = utilizador.getDataNascimento();
                    s_data_nascimento = methodsInterface.formatDateForUser(api_data_nascimento);
                    s_palavra_passe = utilizador.getPalavraPasse();
                    verificado = utilizador.getVerificado();
                    ativo = utilizador.getAtivo();

                    nome_completo.setText(s_nome_completo);
                    email.setText(s_email);
                    data_nascimento.setText(s_data_nascimento);

                    data_nascimento.setOnClickListener(v -> {
                        methodsInterface.popDatePicker(v, data_nascimento);
                    });

                    progressBar.setVisibility(View.GONE);
                    btn_guardar.setVisibility(View.VISIBLE);
                    btn_guardar.setOnClickListener(new View.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onClick(View v) {

                            progressBar.setVisibility(View.VISIBLE);
                            btn_guardar.setVisibility(View.GONE);

                            String input_date = methodsInterface.formatDateForAPI(data_nascimento.getText().toString());
                            LocalDate data_nascimento = methodsInterface.stringToDate(input_date);

                            if (nome_completo.getText().toString().isEmpty() || data_nascimento.compareTo(methodsInterface.getDateToday()) >= 0) {
                                Toast.makeText(v.getContext(), "Preencha todos os campos corretamente!", Toast.LENGTH_LONG).show();
                            }
                            else {
                                String new_nome_completo = nome_completo.getText().toString();
                                if (!s_nome_completo.equals(new_nome_completo) || !s_data_nascimento.equals(input_date)) {
                                    Utilizador utilizador = new Utilizador(id_user, id_tipo, s_username, new_nome_completo, s_palavra_passe, s_email, input_date, verificado, ativo);
                                    updateUtilizador(id_user, utilizador, v.getContext());
                                    Toast.makeText(v.getContext(), "Perfil Atualizado!", Toast.LENGTH_LONG).show();
                                }
                            }
                            progressBar.setVisibility(View.GONE);
                            btn_guardar.setVisibility(View.VISIBLE);
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

        String AuthToken = new SharedPrefManager(mCtx).getAuthToken();
        Call<Utilizador> updateUtilizador = apiInterface.updateUtilizador(id, utilizador, AuthToken);
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