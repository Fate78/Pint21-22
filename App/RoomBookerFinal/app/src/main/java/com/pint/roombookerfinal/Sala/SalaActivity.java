package com.pint.roombookerfinal.Sala;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
    Button btn_reservas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sala);

        edNSala = (EditText) findViewById(R.id.edNSala);
        edLocalizacao = (EditText) findViewById(R.id.edLocalizacao);
        edLotacao = (EditText) findViewById(R.id.edLotacao);
        edLimpeza = (EditText) findViewById(R.id.edLimpeza);
        btn_reservas = (Button) findViewById(R.id.btn_reservas);

        salaId = getIntent().getIntExtra("IdSala",0);
        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<Salas> call = apiInterface.getSala(salaId);

        call.enqueue(new Callback<Salas>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<Salas> call, @NonNull Response<Salas> response) {
                if (response.body() != null) {
                    Log.e("Success",response.body().toString());
                    Salas sala = response.body();
                    System.out.println("++++++ on Response ++++++");
                    edNSala.setText(sala.getnSala().toString());
                    edLocalizacao.setText("TBD");
                    edLotacao.setText(sala.getLotacaoMax().toString());
                    edLimpeza.setText(formatTime(sala.getTempoMinLimp().toString()));
                }
                else
                {
                    Toast.makeText(SalaActivity.this, "No results!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Salas> call, @NonNull Throwable t) {
                Log.e("Failure", t.getLocalizedMessage());
            }
        });

        btn_reservas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ReservasSalaActivity.class);
                intent.putExtra("IdSala", salaId);
                v.getContext().startActivity(intent);
            }
        });
    }

    private String formatTime(String time){
        StringBuilder sb = new StringBuilder(time);
        sb.delete(sb.length()-3, sb.length());
        return sb.toString();
    }
}