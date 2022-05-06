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
import com.pint.roombookerfinal.MainActivity;
import com.pint.roombookerfinal.Models.Utilizador;
import com.pint.roombookerfinal.R;

import org.jetbrains.annotations.NotNull;

import java.security.MessageDigest;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private ContentLogin contentLogin;
    SharedPreferences sharedPreferences;
    Context mCtx;

    EditText email, password;
    Button btn_login;
    Utilizador utilizador;
    String email_input, password_input;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mCtx = this;

        contentLogin = new ContentLogin();
        email = (EditText) findViewById(R.id.edtext_email);
        password = (EditText) findViewById(R.id.edtext_password);
        btn_login = (Button) findViewById(R.id.btn_login);

        sharedPreferences = mCtx.getSharedPreferences("ProfileSharedPref", Context.MODE_PRIVATE);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                email_input = email.getText().toString();
                password_input = getSha256(password.getText().toString());
                validarLogin(email_input, password_input);
                if(contentLogin.isEmailValid() && contentLogin.isPasswordValid()){
                    System.out.println("Logged in: " + contentLogin.getNome_completo());
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.putExtra("id_utilizador", contentLogin.getId());
                    startActivity(intent);
                }
                else{
                    System.out.println(contentLogin.isEmailValid());
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

    public void validarLogin(String email, String password){
        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<List<Utilizador>> call = apiInterface.getUtilizadores();

        call.enqueue(new Callback<List<Utilizador>>() {
            @Override
            public void onResponse(@NotNull Call<List<Utilizador>> call, @NotNull Response<List<Utilizador>> response) {
                Log.e("Success", response.body().toString());
                List<Utilizador> utilizadoresList = response.body();
                System.out.println("++++In Response++++");
                for (Utilizador utilizador:utilizadoresList){
                    if (utilizador.getEmail().equals(email) && utilizador.getPalavraPasse().equals(password)){
                        SharedPreferences.Editor profile = sharedPreferences.edit();
                        profile.putFloat("id_utilizador", utilizador.getIdUtilizador());
                        profile.apply();
                        contentLogin.setId(utilizador.getIdUtilizador());
                        contentLogin.setEmail(utilizador.getEmail());
                        contentLogin.setNome_utilizador(utilizador.getNomeUtilizador());
                        contentLogin.setNome_completo(utilizador.getNomeCompleto());
                        contentLogin.setData_nascimento(utilizador.getDataNascimento());

                        contentLogin.setEmailValid(true);
                        contentLogin.setPasswordValid(true);
                    }
                }
            }
            @Override
            public void onFailure(@NotNull Call<List<Utilizador>> call, @NotNull Throwable t) {
                Log.e("Failure", t.getLocalizedMessage());
            }
        });
    }
}