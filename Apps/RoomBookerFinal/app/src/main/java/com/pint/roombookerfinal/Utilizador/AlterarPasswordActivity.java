package com.pint.roombookerfinal.Utilizador;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
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

public class AlterarPasswordActivity extends AppCompatActivity {

    EditText password;
    Button btn_changePass;
    String input_password;
    String TokenType = "Bearer ";
    final MethodsInterface methodsInterface = new Methods();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_password);


    }

    public void alterarPassword(String password){
        String AuthToken = new SharedPrefManager(AlterarPasswordActivity.this).getAuthToken();
        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<Utilizador> call = apiInterface.changePassword(AuthToken, password, TokenType + AuthToken);

        call.enqueue(new Callback<Utilizador>() {
            @Override
            public void onResponse(@NonNull Call<Utilizador> call, @NonNull Response<Utilizador> response) {
                if(response.code()==401)
                {
                    methodsInterface.logout(AlterarPasswordActivity.this);
                }

                if (response.body() != null) {
                    Log.e("Success", response.body().toString());
                    Toast.makeText(AlterarPasswordActivity.this, "Password Alterada!", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(@NonNull Call<Utilizador> call, @NonNull Throwable t) {

            }
        });
    }
}