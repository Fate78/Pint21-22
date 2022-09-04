package com.pint.roombookerfinal.Utilizador;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.auth0.android.jwt.JWT;
import com.pint.roombookerfinal.API.ApiClient;
import com.pint.roombookerfinal.API.ApiInterface;
import com.pint.roombookerfinal.Methods;
import com.pint.roombookerfinal.MethodsInterface;
import com.pint.roombookerfinal.Models.AuthToken;
import com.pint.roombookerfinal.Models.Authenticate;
import com.pint.roombookerfinal.Models.Utilizador;
import com.pint.roombookerfinal.NavigationUI.NavigationDrawerActivity;
import com.pint.roombookerfinal.R;
import com.pint.roombookerfinal.SharedPrefManager;

import java.util.Objects;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    private SharedPrefManager sharedPrefManager;
    MethodsInterface methodsInterface = new Methods();
    Context mCtx;

    EditText ed_login_input, ed_password_input;
    Button btn_login;
    ProgressBar progressBar;
    Utilizador utilizador;
    String login_input, password;
    Boolean isLoggedout;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mCtx = this;

        ed_login_input = findViewById(R.id.edtext_email);
        ed_password_input = findViewById(R.id.edtext_password);
        btn_login = findViewById(R.id.btn_login);
        progressBar = findViewById(R.id.progressBarLogin);
        isLoggedout = new SharedPrefManager(this).isUserLoggedOut();

        if(!isLoggedout)
        {
            Intent intent = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
            startActivity(intent);

        }
        btn_login.setOnClickListener(view -> {
            login_input = ed_login_input.getText().toString();
            password = ed_password_input.getText().toString();
            btn_login.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            validarLogin(login_input, password);
        });
    }

    public void validarLogin(String login_input, String password){
        if(login_input.isEmpty() || password.isEmpty()){
            Toast.makeText(LoginActivity.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            btn_login.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }
        else{
            Authenticate authenticate = new Authenticate(login_input, password);

            ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
            Call<AuthToken> call = apiInterface.authenticate(authenticate);

            call.enqueue(new Callback<AuthToken>() {
                @Override
                public void onResponse(@NonNull Call<AuthToken> call, @NonNull Response<AuthToken> response) {
                    if(response.code()==401)
                    {
                        Toast.makeText(LoginActivity.this, "Falha no login!", Toast.LENGTH_SHORT).show();
                        btn_login.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                    }

                    if (response.isSuccessful() && response.body() != null) {
                        Log.e("Success", response.body().toString());
                        AuthToken authToken = response.body();

                        String token = authToken.getToken();
                        saveAuthToken(token);
                        JWT jwt = new JWT(token);
                        int idUtilizador = Integer.parseInt(Objects.requireNonNull(jwt.getClaim("ID").asString()));
                        String nomeUtilizador = jwt.getClaim("unique_name").asString();
                        String emailUtilizador = jwt.getClaim("EMAIL").asString();
                        Boolean emailVerificado = jwt.getClaim("VERIF_EMAIL").asBoolean();
                        Boolean passwordVerificada = jwt.getClaim("VERIF_PASS").asBoolean();
                        utilizador = new Utilizador(idUtilizador, nomeUtilizador, emailUtilizador, emailVerificado, passwordVerificada);

                        saveLoginDetails(utilizador.getIdUtilizador(), utilizador.getNomeUtilizador(), utilizador.getEmail());

                        if(!utilizador.getEmailVerificado())
                        {
                            Intent intent = new Intent(getApplicationContext(), VerificarEmailActivity.class);
                            startActivity(intent);
                        }
                        else if(!utilizador.isPassword_verificada()){
                            Intent intent = new Intent(getApplicationContext(), AlterarPasswordActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Intent intent = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
                            startActivity(intent);
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<AuthToken> call, @NonNull Throwable t) {
                    Log.e("Failure", t.getLocalizedMessage());
                    btn_login.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }
            });
        }
    }

    private void saveLoginDetails(Integer userId, String username, String email){
        new SharedPrefManager(this).saveLoginDetails(userId, username, email);
    }

    private void saveAuthToken(String token){
        new SharedPrefManager(this).saveAuthToken(token);
    }

    public static boolean isEmailValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}