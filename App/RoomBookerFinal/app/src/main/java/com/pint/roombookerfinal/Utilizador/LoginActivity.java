package com.pint.roombookerfinal.Utilizador;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.pint.roombookerfinal.Models.Utilizador;
import com.pint.roombookerfinal.R;

import java.security.MessageDigest;

public class LoginActivity extends AppCompatActivity {
    private ContentLogin contentLogin;
    EditText email, password;
    Button btn_login;
    boolean isEmailValid, isPasswordValid;
    Utilizador utilizador;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        contentLogin = new ContentLogin();
        email = (EditText) findViewById(R.id.edtext_email);
        password = (EditText) findViewById(R.id.edtext_password);
        btn_login = (Button) findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                contentLogin.setEmail(email.getText().toString());
                contentLogin.setPassword(getSha256(password.getText().toString()));
                contentLogin.validarLogin();
                if(contentLogin.isEmailValid() && contentLogin.isPasswordValid()){
                    System.out.println("Logged in: " + utilizador.getNomeCompleto());
                }
                else{
                    System.out.println("Failed to Login");
                }
            }
        });
    }

    private void validateLogin(){
        if (email.getText().toString().isEmpty()){isEmailValid = false;}
        else{isEmailValid = true;}

        if (password.getText().toString().isEmpty()){isPasswordValid = false;}
        else {isPasswordValid = true;}

        if (isEmailValid && isPasswordValid){

        }
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
}