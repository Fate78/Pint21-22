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

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
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
            /*String token = new SharedPrefManager(this).getAuthToken();
            JWT jwt = new JWT(token);
            long tokenExpiration = jwt.getClaim("exp").asLong();

            LocalDateTime dateTimeNow = LocalDateTime.now();
            ZoneId zoneId = ZoneId.of("Europe/Lisbon");
            ZoneOffset zoneOffset = zoneId.getRules().getOffset(dateTimeNow);
            long secEpochNow = dateTimeNow.toEpochSecond(zoneOffset);
            //If token is not expired
            if(tokenExpiration>secEpochNow) {
                Intent intent = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
                startActivity(intent);
            }
            else{
                methodsInterface.logout(mCtx);
            }*/
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

    public static String getSha256(final String base) {
        try{
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(base.getBytes(StandardCharsets.UTF_8));
            final StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                final String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
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
                    if (response.isSuccessful() && response.body() != null) {
                        Log.e("Success", response.body().toString());
                        AuthToken authToken = response.body();

                        String token = authToken.getToken();
                        saveAuthToken(token);
                        JWT jwt = new JWT(token);
                        int idUtilizador = Integer.parseInt(Objects.requireNonNull(jwt.getClaim("ID").asString()));
                        String nomeUtilizador = jwt.getClaim("unique_name").asString();
                        String emailUtilizador = jwt.getClaim("EMAIL").asString();
                        utilizador = new Utilizador(idUtilizador, nomeUtilizador, emailUtilizador);

                        saveLoginDetails(utilizador.getIdUtilizador(), utilizador.getNomeUtilizador(), utilizador.getEmail());
                        Intent intent = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
                        startActivity(intent);
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