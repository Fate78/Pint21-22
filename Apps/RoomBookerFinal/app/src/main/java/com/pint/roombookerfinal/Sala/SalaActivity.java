package com.pint.roombookerfinal.Sala;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.pint.roombookerfinal.API.ApiClient;
import com.pint.roombookerfinal.API.ApiInterface;
import com.pint.roombookerfinal.Methods;
import com.pint.roombookerfinal.MethodsInterface;
import com.pint.roombookerfinal.Models.Sala;
import com.pint.roombookerfinal.R;
import com.pint.roombookerfinal.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalaActivity extends AppCompatActivity {
    private int salaId;
    EditText edNSala, edLocalizacao, edLotacao, edLimpeza;
    Button btn_reservas;
    private String selectedCentroName, nSala, tempo_limpeza, lotacao;
    final MethodsInterface methodsInterface = new Methods();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sala);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        edNSala = findViewById(R.id.edNSala);
        edLocalizacao = findViewById(R.id.edLocalizacao);
        edLotacao = findViewById(R.id.edLotacao);
        edLimpeza = findViewById(R.id.edLimpeza);
        btn_reservas = findViewById(R.id.btn_reservas);

        salaId = getIntent().getIntExtra("IdSala",0);
        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<Sala> call = apiInterface.getSala(salaId);

        call.enqueue(new Callback<Sala>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<Sala> call, @NonNull Response<Sala> response) {
                if (response.body() != null) {
                    Log.e("Success",response.body().toString());
                    Sala sala = response.body();
                    String activityTitle = "Detalhes Sala " + sala.getnSala();
                    setTitle(activityTitle);

                    nSala = sala.getnSala().toString();
                    tempo_limpeza = methodsInterface.formatTimeForUser(sala.getTempoMinLimp());
                    lotacao = sala.getLotacaoMax().toString();
                    edNSala.setText(nSala);
                    edLotacao.setText(lotacao);
                    edLimpeza.setText(tempo_limpeza);
                    selectedCentroName = new SharedPrefManager(getApplicationContext()).getCentroNome();
                    edLocalizacao.setText(selectedCentroName);
                }
                else
                {
                    Toast.makeText(SalaActivity.this, "No results!!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Sala> call, @NonNull Throwable t) {
                Log.e("Failure", t.getLocalizedMessage());
            }
        });

        btn_reservas.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), ReservasSalaActivity.class);
            intent.putExtra("IdSala", salaId);
            intent.putExtra("NSala", nSala);
            intent.putExtra("Lotacao", lotacao);
            intent.putExtra("TempoLimpeza", tempo_limpeza);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private String formatTime(String time){
        StringBuilder sb = new StringBuilder(time);
        sb.delete(sb.length()-3, sb.length());
        return sb.toString();
    }
}