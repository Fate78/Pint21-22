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

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.pint.roombookerfinal.API.ApiClient;
import com.pint.roombookerfinal.API.ApiInterface;
import com.pint.roombookerfinal.Models.Utilizador;
import com.pint.roombookerfinal.NavigationUI.NavigationDrawerActivity;
import com.pint.roombookerfinal.R;
import com.pint.roombookerfinal.SharedPrefManager;

import org.jetbrains.annotations.NotNull;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private ContentLogin contentLogin;
    SharedPreferences sharedPreferences;
    private SharedPrefManager sharedPrefManager;
    Context mCtx;

    EditText ed_login_input, ed_password_input;
    Button btn_login;
    ProgressBar progressBar;
    Utilizador utilizador;
    String login_input, password;
    Boolean isLoggedout;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mCtx = this;

        contentLogin = new ContentLogin();
        ed_login_input = findViewById(R.id.edtext_email);
        ed_password_input = findViewById(R.id.edtext_password);
        btn_login = findViewById(R.id.btn_login);
        progressBar = findViewById(R.id.progressBar);
        isLoggedout = new SharedPrefManager(this).isUserLoggedOut();

        if(!isLoggedout)
        {
            Intent intent = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
            startActivity(intent);
        }
        btn_login.setOnClickListener(view -> {
            login_input = ed_login_input.getText().toString();
            password = getSha256(ed_password_input.getText().toString());
            validarLogin(login_input, password);
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
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
        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<Utilizador> call = apiInterface.getUtilizador(login_input);

        call.enqueue(new Callback<Utilizador>() {
            @Override
            public void onResponse(@NotNull Call<Utilizador> call, @NotNull Response<Utilizador> response) {
                btn_login.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                if (response.isSuccessful() && response.body()!=null) {
                    Log.e("Success", response.body().toString());
                    Utilizador utilizador = response.body();
                    if(isEmailValid(login_input))
                    {
                        if (utilizador.getEmail().equals(login_input) && utilizador.getPalavraPasse().equals(password)){
                            saveLoginDetails(utilizador.getIdUtilizador(), utilizador.getNomeUtilizador(), utilizador.getEmail(), utilizador.getPalavraPasse());
                            /*contentLogin.setUsernameValid(true);
                            contentLogin.setPasswordValid(true);
                            contentLogin.setId(utilizador.getIdUtilizador());
                            contentLogin.setEmail(utilizador.getEmail());
                            contentLogin.setNome_utilizador(utilizador.getNomeUtilizador());
                            contentLogin.setNome_completo(utilizador.getNomeCompleto());
                            contentLogin.setData_nascimento(utilizador.getDataNascimento());*/
                            Intent intent = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Email ou palavra passe errados!", Toast.LENGTH_SHORT).show();
                            btn_login.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                    else{
                        if (utilizador.getNomeUtilizador().equals(login_input) && utilizador.getPalavraPasse().equals(password)){
                            saveLoginDetails(utilizador.getIdUtilizador(), utilizador.getNomeUtilizador(), utilizador.getEmail(), utilizador.getPalavraPasse());
                            /*contentLogin.setUsernameValid(true);
                            contentLogin.setPasswordValid(true);
                            contentLogin.setId(utilizador.getIdUtilizador());
                            contentLogin.setEmail(utilizador.getEmail());
                            contentLogin.setNome_utilizador(utilizador.getNomeUtilizador());
                            contentLogin.setNome_completo(utilizador.getNomeCompleto());
                            contentLogin.setData_nascimento(utilizador.getDataNascimento());*/
                            Intent intent = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Utilizador ou palavra passe errados!", Toast.LENGTH_SHORT).show();
                            btn_login.setVisibility(View.VISIBLE);
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                }
                else{
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
        });
    }

    private void saveLoginDetails(Integer userId, String username, String email, String password){
        new SharedPrefManager(this).saveLoginDetails(userId, username, email, password);
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