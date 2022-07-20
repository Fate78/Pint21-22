package com.pint.roombookerapp2.Utilizador;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pint.roombookerapp2.MainActivity;
import com.pint.roombookerapp2.Models.Utilizador;
import com.pint.roombookerapp2.SharedPrefManager;
import com.pint.roombookerapp2.API.ApiClient;
import com.pint.roombookerapp2.API.ApiInterface;
import com.pint.roombookerapp2.R;

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
    Utilizador utilizador;
    String login_input, password;
    Boolean isLoggedout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mCtx = this;

        contentLogin = new ContentLogin();
        ed_login_input = findViewById(R.id.edtext_email);
        ed_password_input = findViewById(R.id.edtext_password);
        btn_login = findViewById(R.id.btn_login);
        isLoggedout = new SharedPrefManager(this).isUserLoggedOut();

        if(!isLoggedout)
        {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
        btn_login.setOnClickListener(view -> {
            login_input = ed_login_input.getText().toString();
            password = getSha256(ed_password_input.getText().toString());
            validarLogin(login_input, password);
            if(contentLogin.isUsernameValid() && contentLogin.isPasswordValid()){
                System.out.println("Logged in: " + contentLogin.getNome_completo());
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
            else{
                System.out.println(contentLogin.isUsernameValid());
                System.out.println(contentLogin.isPasswordValid());
                Toast.makeText(LoginActivity.this, "Failed to Login \nPlease try again", Toast.LENGTH_SHORT).show();
            }
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
        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<Utilizador> call = apiInterface.getUtilizador(login_input);

        call.enqueue(new Callback<Utilizador>() {
            @Override
            public void onResponse(@NotNull Call<Utilizador> call, @NotNull Response<Utilizador> response) {
                if (response.body() != null) {
                    Log.e("Success", response.body().toString());
                    Utilizador utilizador = response.body();
                    if(isEmailValid(login_input))
                    {
                        if (utilizador.getEmail().equals(login_input) && utilizador.getPalavraPasse().equals(password)){
                            saveLoginDetails(utilizador.getIdUtilizador(), utilizador.getNomeUtilizador(), utilizador.getEmail(), utilizador.getPalavraPasse());
                            contentLogin.setUsernameValid(true);
                            contentLogin.setPasswordValid(true);
                            contentLogin.setId(utilizador.getIdUtilizador());
                            contentLogin.setEmail(utilizador.getEmail());
                            contentLogin.setNome_utilizador(utilizador.getNomeUtilizador());
                            contentLogin.setNome_completo(utilizador.getNomeCompleto());
                            contentLogin.setData_nascimento(utilizador.getDataNascimento());
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Email not found!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        if (utilizador.getNomeUtilizador().equals(login_input) && utilizador.getPalavraPasse().equals(password)){
                            saveLoginDetails(utilizador.getIdUtilizador(), utilizador.getNomeUtilizador(), utilizador.getEmail(), utilizador.getPalavraPasse());
                            contentLogin.setUsernameValid(true);
                            contentLogin.setPasswordValid(true);
                            contentLogin.setId(utilizador.getIdUtilizador());
                            contentLogin.setEmail(utilizador.getEmail());
                            contentLogin.setNome_utilizador(utilizador.getNomeUtilizador());
                            contentLogin.setNome_completo(utilizador.getNomeCompleto());
                            contentLogin.setData_nascimento(utilizador.getDataNascimento());
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "User not found!", Toast.LENGTH_SHORT).show();
                        }
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