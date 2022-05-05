package com.pint.roombookerfinal.Utilizador;

import android.util.Log;

import com.pint.roombookerfinal.ApiClient;
import com.pint.roombookerfinal.ApiInterface;
import com.pint.roombookerfinal.Models.Utilizador;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContentLogin {
    private int id, id_tipo;
    private String email, password, confirmarPassword, nome_utilizador, nome_completo, data_nascimento;
    private boolean isEmailValid, isPasswordValid;
    private Utilizador model_utilizador;

    public void validarLogin(){
        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<List<Utilizador>> call = apiInterface.getUtilizadores();

        System.out.println("Email: " + email);
        System.out.println("Password: " + password);

        call.enqueue(new Callback<List<Utilizador>>() {
            @Override
            public void onResponse(Call<List<Utilizador>> call, Response<List<Utilizador>> response) {
                Log.e("Success", response.body().toString());
                List<Utilizador> utilizadoresList = response.body();
                for (Utilizador utilizador:utilizadoresList){
                    System.out.println(utilizador.getEmail() + " = " + email);
                    System.out.println(utilizador.getPalavraPasse() + " = " + password);
                    if (utilizador.getEmail() == email){
                        System.out.println("It's here!!!");
                        setEmailValid(true);
                        setPasswordValid(true);
                        model_utilizador.setIdUtilizador(utilizador.getIdUtilizador());
                        model_utilizador.setIdTipo(utilizador.getIdTipo());
                        model_utilizador.setEmail(utilizador.getEmail());
                        model_utilizador.setNomeCompleto(utilizador.getNomeUtilizador());
                        model_utilizador.setDataNascimento(utilizador.getDataNascimento());
                    }
                }
            }
            @Override
            public void onFailure(Call<List<Utilizador>> call, Throwable t) {
                Log.e("Failure", t.getLocalizedMessage());
            }
        });
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome_utilizador() {
        return nome_utilizador;
    }

    public void setNome_utilizador(String nome_utilizador) {
        this.nome_utilizador = nome_utilizador;
    }

    public String getNome_completo() {
        return nome_completo;
    }

    public void setNome_completo(String nome_completo) {
        this.nome_completo = nome_completo;
    }

    public String getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public boolean isEmailValid() {
        return isEmailValid;
    }

    public void setEmailValid(boolean emailValid) {
        isEmailValid = emailValid;
    }

    public boolean isPasswordValid() {
        return isPasswordValid;
    }

    public void setPasswordValid(boolean passwordValid) {
        isPasswordValid = passwordValid;
    }
}
