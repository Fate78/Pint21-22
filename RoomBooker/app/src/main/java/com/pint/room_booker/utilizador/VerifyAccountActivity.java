package com.pint.room_booker.utilizador;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.pint.room_booker.R;

public class VerifyAccountActivity extends AppCompatActivity {
    private ContentLogin contentLogin;
    EditText password, confirmar_password;
    Button btn_alterar;

    TextInputLayout passwordError;
    boolean isPasswordValid;
    String pass, conf_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_account);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        contentLogin = new ContentLogin();
        password = (EditText) findViewById(R.id.ed_novapasse);
        confirmar_password = (EditText) findViewById(R.id.ed_confirmarpasse);
        btn_alterar = (Button) findViewById(R.id.btn_alterar);
        passwordError = (TextInputLayout) findViewById(R.id.error_password);

        btn_alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verify();
            }
        });
    }

    public void verify(){
        Bundle extras = getIntent().getExtras();
        this.contentLogin.setPassword(this.password.getText().toString());
        this.contentLogin.setConfirmarPassword(this.confirmar_password.getText().toString());
        this.contentLogin.setId(extras.getInt("id_utilizador"));

        if(checkPasswords()){
            this.contentLogin.changePasword();
        }

    }

    public boolean checkPasswords(){
        pass = password.getText().toString();
        conf_pass = confirmar_password.getText().toString();

        if (pass.isEmpty()) {
            passwordError.setError("Introduza uma password!");
            passwordError.setVisibility(View.VISIBLE);
            return false;
        }
        else{
            if (pass.equals(conf_pass)) {
                isPasswordValid = true;
                passwordError.setErrorEnabled(false);
                return true;
            } else {
                passwordError.setError("As passwords são diferentes!");
                passwordError.setVisibility(View.VISIBLE);
                isPasswordValid = false;
                return false;
            }
        }
    }
}