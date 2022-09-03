package com.pint.roombookerfinal.Utilizador;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.auth0.android.jwt.JWT;
import com.pint.roombookerfinal.API.ApiClient;
import com.pint.roombookerfinal.API.ApiInterface;
import com.pint.roombookerfinal.Methods;
import com.pint.roombookerfinal.MethodsInterface;
import com.pint.roombookerfinal.R;
import com.pint.roombookerfinal.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerificarEmailActivity extends AppCompatActivity {

    EditText ed_code;
    String code;
    Button btn_confirmar, btn_resend;
    String TokenType = "Bearer ";
    ProgressBar progressBar;
    final MethodsInterface methodsInterface = new Methods();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificar_email);

        ed_code = findViewById(R.id.ed_codigo);
        btn_confirmar = findViewById(R.id.btn_confirmar);
        btn_resend = findViewById(R.id.btn_resend);
        progressBar = findViewById(R.id.progressBarVerifyEmail);

        if(ed_code!=null){
            btn_confirmar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    code = ed_code.getText().toString();
                    verifyCode(code);
                }
            });
        }

        btn_resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resendCode();
            }
        });
    }

    public void verifyCode(String code){
        progressBar.setVisibility(View.VISIBLE);
        btn_resend.setVisibility(View.GONE);
        btn_confirmar.setVisibility(View.GONE);

        String email = new SharedPrefManager(getApplicationContext()).getEmail();
        String AuthToken = new SharedPrefManager(getApplicationContext()).getAuthToken();
        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<Void> call = apiInterface.verifyEmailCode(code, email,TokenType + AuthToken);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if(response.code()==401)
                {
                    methodsInterface.logout(getApplicationContext());
                }
                if(response.isSuccessful())
                {
                    Log.e("Success", "");
                    Toast.makeText(getApplicationContext(), "Email Verificado!", Toast.LENGTH_LONG).show();

                    String token = new SharedPrefManager(VerificarEmailActivity.this).getAuthToken();
                    JWT jwt = new JWT(token);
                    Boolean passwordVerificada = jwt.getClaim("VERIF_PASS").asBoolean();

                    Intent intent;
                    if(Boolean.TRUE.equals(passwordVerificada))
                    {
                        methodsInterface.logout(getApplicationContext());
                    }
                    else
                    {
                        intent = new Intent(getApplicationContext(), AlterarPasswordActivity.class);
                        startActivity(intent);
                    }

                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Log.e("Failure", t.getLocalizedMessage());
            }
        });
    }

    public void resendCode(){
        String email = new SharedPrefManager(getApplicationContext()).getEmail();
        String AuthToken = new SharedPrefManager(getApplicationContext()).getAuthToken();
        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<Void> call = apiInterface.sendEmailVerification(email,TokenType + AuthToken);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if(response.code()==401)
                {
                    methodsInterface.logout(getApplicationContext());
                }

                Log.e("Success", "");
                Toast.makeText(getApplicationContext(), "Email Enviado!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Log.e("Failure", t.getLocalizedMessage());
            }
        });
    }
}