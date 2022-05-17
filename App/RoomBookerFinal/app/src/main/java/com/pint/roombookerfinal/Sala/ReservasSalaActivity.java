package com.pint.roombookerfinal.Sala;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pint.roombookerfinal.ApiClient;
import com.pint.roombookerfinal.ApiInterface;
import com.pint.roombookerfinal.Models.Reservas;
import com.pint.roombookerfinal.Models.Salas;
import com.pint.roombookerfinal.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservasSalaActivity extends AppCompatActivity {
    private int salaId;
    RecyclerView recyclerView;
    Context mCtx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservas_sala);

        salaId = getIntent().getIntExtra("IdSala",0);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.rv_reservas);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<Salas> call = apiInterface.getSala(salaId);

        call.enqueue(new Callback<Salas>() {
            @Override
            public void onResponse(Call<Salas> call, Response<Salas> response) {
                if (response.body() != null) {
                    Log.e("Success",response.body().toString());
                    Salas sala = response.body();
                    System.out.println("++++++ on Response ++++++");
                    List<Reservas> reservasList = (List<Reservas>) response.body().getReservas();
                    for (Reservas reservas:reservasList) {
                        String content = "";
                        content += "Horario Inicio: " + reservas.getHoraInicio() + "\n";
                        content += "Horario Fim: " + reservas.getHoraFim() + "\n";
                        System.out.println(content);
                    }
                    recyclerView.setAdapter(new ReservasRecyclerViewAdapter(mCtx, reservasList) );
                    if(reservasList.isEmpty())
                    {
                        Toast.makeText(ReservasSalaActivity.this, "Não foram encontradas reservas!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Salas> call, Throwable t) {
                Log.e("Failure", t.getLocalizedMessage());
            }
        });
    }
}