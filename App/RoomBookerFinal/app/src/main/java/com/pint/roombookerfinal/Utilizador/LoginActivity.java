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

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.pint.roombookerfinal.ApiClient;
import com.pint.roombookerfinal.ApiInterface;
import com.pint.roombookerfinal.Models.Utilizador;
import com.pint.roombookerfinal.NavigationDrawerActivity;
import com.pint.roombookerfinal.R;
import com.pint.roombookerfinal.SharedPrefManager;

import org.jetbrains.annotations.NotNull;

import java.security.MessageDigest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private ContentLogin contentLogin;
    SharedPreferences sharedPreferences;
    private SharedPrefManager sharedPrefManager;
    Context mCtx;

    EditText username_input, password_input;
    Button btn_login;
    Utilizador utilizador;
    String username, password;
    Boolean isLoggedout;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mCtx = this;

        contentLogin = new ContentLogin();
        username_input = (EditText) findViewById(R.id.edtext_email);
        password_input = (EditText) findViewById(R.id.edtext_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        isLoggedout = new SharedPrefManager(this).isUserLoggedOut();

        if(!isLoggedout)
        {
            Intent intent = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
            startActivity(intent);
        }
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = username_input.getText().toString();
                password = getSha256(password_input.getText().toString());
                validarLogin(username, password);
                if(contentLogin.isUsernameValid() && contentLogin.isPasswordValid()){
                    System.out.println("Logged in: " + contentLogin.getNome_completo());
                    Intent intent = new Intent(getApplicationContext(), NavigationDrawerActivity.class);
                    startActivity(intent);
                }
                else{
                    System.out.println(contentLogin.isUsernameValid());
                    System.out.println(contentLogin.isPasswordValid());
                    System.out.println("Failed to Login");
                }
            }
        });
    }

    public static String getSha256(final String base) {
        try{
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(base.getBytes("UTF-8"));
            final StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                final String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public void validarLogin(String username, String password){
        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<Utilizador> call = apiInterface.getUtilizador(username);

        call.enqueue(new Callback<Utilizador>() {
            @Override
            public void onResponse(@NotNull Call<Utilizador> call, @NotNull Response<Utilizador> response) {
                if (response.body() != null) {
                    Log.e("Success", response.body().toString());
                    Utilizador utilizador = response.body();
                    if (utilizador.getNomeUtilizador().equals(username) && utilizador.getPalavraPasse().equals(password)){
                        saveLoginDetails(utilizador.getIdUtilizador(), username, utilizador.getEmail(), password);
                        contentLogin.setUsernameValid(true);
                        contentLogin.setPasswordValid(true);
                        contentLogin.setId(utilizador.getIdUtilizador());
                        contentLogin.setEmail(utilizador.getEmail());
                        contentLogin.setNome_utilizador(utilizador.getNomeUtilizador());
                        contentLogin.setNome_completo(utilizador.getNomeCompleto());
                        contentLogin.setData_nascimento(utilizador.getDataNascimento());
                    }
                }
            }
            @Override
            public void onFailure(@NotNull Call<Utilizador> call, @NotNull Throwable t) {
                Log.e("Failure", t.getLocalizedMessage());
            }
        });
    }

    private void saveLoginDetails(Integer userId, String username, String email, String password){
        new SharedPrefManager(this).saveLoginDetails(userId, username, email, password);
    }
}