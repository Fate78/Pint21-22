package com.pint.roombookerfinal.Sala;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.pint.roombookerfinal.ApiClient;
import com.pint.roombookerfinal.ApiInterface;
import com.pint.roombookerfinal.Models.Salas;
import com.pint.roombookerfinal.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalaActivity extends AppCompatActivity {
    private int salaId;
    Context mCtx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sala);

        salaId = getIntent().getIntExtra("IdSala",0);
        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<Salas> call = apiInterface.getSala(salaId);

        call.enqueue(new Callback<Salas>() {
            @Override
            public void onResponse(Call<Salas> call, Response<Salas> response) {
                Log.e("Success",response.body().toString());
                Salas sala = response.body();
                System.out.println("++++++ on Response ++++++");
                String content = "";
                content += "Sala nº " + sala.getnSala();
                content += "\nLotação: " + sala.getLotacaoMax();
                content += "\nTempo Limpeza: " + sala.getTempoMinLimp();
                content += "\nAtiva: " + sala.getAtivo();
                System.out.println(content);
            }

            @Override
            public void onFailure(Call<Salas> call, Throwable t) {
                Log.e("Failure", t.getLocalizedMessage());
            }
        });
    }
}