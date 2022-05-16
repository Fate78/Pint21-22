package com.pint.roombookerfinal.Sala;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

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
    EditText edNSala, edLocalizacao, edLotacao, edLimpeza;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sala);

        edNSala = (EditText) findViewById(R.id.edNSala);
        edLocalizacao = (EditText) findViewById(R.id.edLocalizacao);
        edLotacao = (EditText) findViewById(R.id.edLotacao);
        edLimpeza = (EditText) findViewById(R.id.edLimpeza);

        salaId = getIntent().getIntExtra("IdSala",0);
        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<Salas> call = apiInterface.getSala(salaId);

        call.enqueue(new Callback<Salas>() {
            @Override
            public void onResponse(Call<Salas> call, Response<Salas> response) {
                Log.e("Success",response.body().toString());
                Salas sala = response.body();
                System.out.println("++++++ on Response ++++++");
                /*String content = "";
                content += "Sala nº " + sala.getnSala();
                content += "\nLotação: " + sala.getLotacaoMax();
                content += "\nTempo Limpeza: " + sala.getTempoMinLimp();
                content += "\nAtiva: " + sala.getAtivo();
                System.out.println(content);*/
                edNSala.setText(sala.getnSala().toString());
                edLocalizacao.setText("TBD");
                edLotacao.setText(sala.getLotacaoMax().toString());
                edLimpeza.setText(sala.getTempoMinLimp().toString());
            }

            @Override
            public void onFailure(Call<Salas> call, Throwable t) {
                Log.e("Failure", t.getLocalizedMessage());
            }
        });
    }
}