package com.pint.roombookerfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.pint.roombookerfinal.Models.Salas;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv_salas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<List<Salas>> call = apiInterface.getSalas();
        System.out.println("++++++ Request ++++++");

        call.enqueue(new Callback<List<Salas>>() {
            @Override
            public void onResponse(Call<List<Salas>> call, Response<List<Salas>> response) {
                System.out.println(response);
                List<Salas> salasList = response.body();
                if(response.isSuccessful()){
                    Log.e("Success",response.body().toString());
                    System.out.println("++++++ on Response ++++++");
                    for (Salas salas:salasList) {
                        String content = "";
                        content += "Sala Nº " + salas.getnSala() + "\n";
                        System.out.println(content);
                    }
                    recyclerView.setAdapter(new RecyclerViewAdapter(MainActivity.this, salasList) );
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