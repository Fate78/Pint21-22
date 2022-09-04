package com.pint.roombookerfinal.Utilizador;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.pint.roombookerfinal.API.ApiClient;
import com.pint.roombookerfinal.API.ApiInterface;
import com.pint.roombookerfinal.Methods;
import com.pint.roombookerfinal.MethodsInterface;
import com.pint.roombookerfinal.R;
import com.pint.roombookerfinal.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlterarPasswordActivity extends AppCompatActivity {

    EditText ed_newPassword;
    Button btn_changePass;
    String input_password;
    String TokenType = "Bearer ";
    final MethodsInterface methodsInterface = new Methods();
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_password);

        ed_newPassword = (EditText) findViewById(R.id.ed_newPassword);
        btn_changePass = findViewById(R.id.btn_changePass);
        progressBar = findViewById(R.id.progressBarChangePass);

        btn_changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                input_password = ed_newPassword.getText().toString();
                if(!input_password.isEmpty())
                    alterarPassword(input_password);
            }
        });

    }

    public void alterarPassword(String password){
        progressBar.setVisibility(View.VISIBLE);
        btn_changePass.setVisibility(View.GONE);

        String AuthToken = new SharedPrefManager(getApplicationContext()).getAuthToken();
        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<Void> call = apiInterface.changePassword(AuthToken, password, TokenType + AuthToken);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                if(response.code()==401)
                {
                    methodsInterface.logout(getApplicationContext());
                }

                if (response.code()==204) {
                    Log.e("Success", "");
                    Toast.makeText(getApplicationContext(), "Password Alterada Com Sucesso!", Toast.LENGTH_LONG).show();
                    finish();
                    methodsInterface.logoutWithFlag(getApplicationContext());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
                Log.e("Failure", t.getLocalizedMessage());
            }
        });
    }
}