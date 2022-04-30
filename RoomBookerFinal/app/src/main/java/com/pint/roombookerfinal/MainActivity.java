package com.pint.roombookerfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.pint.roombookerfinal.Models.Salas;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView txt_result;
    Button btn_get;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_result = findViewById(R.id.txt_result);
        btn_get = findViewById(R.id.btn_get);

        btn_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callRetrofit();
            }
        });
        }

    public void callRetrofit(){

        //Retrofit Builder
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://localhost:7063")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        Call<Salas> callSalas = apiInterface.getData();

        if (callSalas != null) {
            callSalas.enqueue(new Callback<Salas>() {
                @Override
                public void onResponse(Call<Salas> call, Response<Salas> response) {
                    //Checking for Response
                    if (response.code() != 200){
                        txt_result.setText("Check the connection");
                        return;
                    }

                    String s_response = "";

                    s_response = "nº sala: " + response.body().getnSala();

                    txt_result.append("\n" + s_response);
                }

                @Override
                public void onFailure(Call<Salas> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_LONG).show();
                }
            });
        }
        else{
            txt_result.setText("It's fucking null, bitch!");
        }
    }
}