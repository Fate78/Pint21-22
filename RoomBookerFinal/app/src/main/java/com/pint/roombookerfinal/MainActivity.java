package com.pint.roombookerfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.pint.roombookerfinal.Models.Salas;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<List<Salas>> listaSalas = apiInterface.getSalas();
        System.out.println("++++++ Request ++++++");

        listaSalas.enqueue(new Callback<List<Salas>>() {
            @Override
            public void onResponse(Call<List<Salas>> call, Response<List<Salas>> response) {
                if(response.isSuccessful()){
                    Log.e("Success",response.body().toString());
                    System.out.println("++++++ on Response ++++++");
                }
                else{
                    System.out.println("++++++ Response not Successful ++++++");
                }
            }

            @Override
            public void onFailure(Call<List<Salas>> call, Throwable t) {
                Log.e("Failure", t.getLocalizedMessage());
            }
        });
    }
}