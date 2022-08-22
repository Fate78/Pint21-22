package com.pint.roombookerapp2.Utilizador;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.auth0.android.jwt.JWT;
import com.pint.roombookerapp2.API.ApiClient;
import com.pint.roombookerapp2.API.ApiInterface;
import com.pint.roombookerapp2.MainActivity;
import com.pint.roombookerapp2.Models.AuthToken;
import com.pint.roombookerapp2.Models.Authenticate;
import com.pint.roombookerapp2.Models.Sala;
import com.pint.roombookerapp2.Models.Utilizador;
import com.pint.roombookerapp2.R;
import com.pint.roombookerapp2.SharedPrefManager;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    private SharedPrefManager sharedPrefManager;
    Context mCtx;

    EditText ed_login_input, ed_password_input;
    Button btn_login;
    String login_input, password;
    Boolean isLoggedout;
    ProgressBar progressBar;
    Spinner spinner;
    List<String> salasList;
    ArrayAdapter<String> catAdapter;
    Utilizador utilizador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mCtx = this;

        ed_login_input = findViewById(R.id.edtext_email);
        ed_password_input = findViewById(R.id.edtext_password);
        btn_login = findViewById(R.id.btn_login2);
        progressBar = findViewById(R.id.progressBarLogin);

        isLoggedout = new SharedPrefManager(this).isUserLoggedOut();

        if(!isLoggedout)
        {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
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
            String AuthToken = new SharedPrefManager(mCtx).getAuthToken();

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
                        new SharedPrefManager(LoginActivity.this).saveAuthToken(token);
                        JWT jwt = new JWT(token);
                        int idUtilizador = Integer.parseInt(jwt.getClaim("ID").asString());
                        String nomeUtilizador = jwt.getClaim("unique_name").asString();
                        String emailUtilizador = jwt.getClaim("EMAIL").asString();
                        utilizador = new Utilizador(idUtilizador, nomeUtilizador, emailUtilizador);

                        saveLoginDetails(utilizador.getIdUtilizador(), utilizador.getNomeUtilizador(), utilizador.getEmail());
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
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

            /*ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
            Call<Utilizador> call = apiInterface.getUtilizador(login_input, AuthToken);

            call.enqueue(new Callback<Utilizador>() {
                @Override
                public void onResponse(@NotNull Call<Utilizador> call, @NotNull Response<Utilizador> response) {

                    if (response.isSuccessful() && response.body() != null) {
                        Log.e("Success", response.body().toString());
                        Utilizador utilizador = response.body();
                        if (isEmailValid(login_input)) {
                            if (utilizador.getEmail().equals(login_input) && utilizador.getPalavraPasse().equals(password)) {
                                saveLoginDetails(utilizador.getIdUtilizador(), utilizador.getNomeUtilizador(), utilizador.getEmail(), utilizador.getPalavraPasse());
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(LoginActivity.this, "Email ou palavra passe errados!", Toast.LENGTH_SHORT).show();
                                btn_login.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.GONE);
                            }
                        }  else {
                            if (utilizador.getNomeUtilizador().equals(login_input) && utilizador.getPalavraPasse().equals(password)) {
                                saveLoginDetails(utilizador.getIdUtilizador(), utilizador.getNomeUtilizador(), utilizador.getEmail(), utilizador.getPalavraPasse());
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(LoginActivity.this, "Utilizador ou palavra passe errados!", Toast.LENGTH_SHORT).show();
                                btn_login.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    } else {
                        btn_login.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);
                    }

                }

                @Override
                public void onFailure(@NotNull Call<Utilizador> call, @NotNull Throwable t) {
                    Log.e("Failure", t.getLocalizedMessage());
                    btn_login.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }
            });*/
        }
    }

    private void saveLoginDetails(Integer userId, String username, String email){
        new SharedPrefManager(this).saveLoginDetails(userId, username, email);
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

    public void getSalas()
    {
        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<List<Sala>> call = apiInterface.getSalas();

        call.enqueue(new Callback<List<Sala>>() {
            @Override
            public void onResponse(@NonNull Call<List<Sala>> call, @NonNull Response<List<Sala>> response) {
                if (response.body() != null) {
                    Log.e("Success", response.body().toString());
                    List<Sala> salasList = response.body();
                    for (Sala sala : salasList){
                        Integer id_sala = sala.getIdSala();
                        Integer n_sala = sala.getnSala();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Sala>> call, @NonNull Throwable t) {
                Log.e("Failure", t.getLocalizedMessage());
            }
        });
    }
}