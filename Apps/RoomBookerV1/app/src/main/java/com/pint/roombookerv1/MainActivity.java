package com.pint.roombookerv1;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.pint.roombookerv1.Models.Salas;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView txt_result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_result = findViewById(R.id.txt_result);

        //Retrofit Builder
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://192.168.1.10:8082/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Instance for Interface
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        Call<Salas> callSalas = ApiInterface.getData();

        callSalas.enqueue(new Callback<Salas>() {
            @Override
            public void onResponse(Call<Salas> call, Response<Salas> response) {
                //Checking for Response
                if (response.code() != 200){
                    txt_result.setText("Check the connection");
                }

                String s_response = "";

                s_response = "nº sala: " + response.body().getnSala();

                txt_result.append(s_response);
            }

            @Override
            public void onFailure(Call<Salas> call, Throwable t) {

            }
        });
    }
}