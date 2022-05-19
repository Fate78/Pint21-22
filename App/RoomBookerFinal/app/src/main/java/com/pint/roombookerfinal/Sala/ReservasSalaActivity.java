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
import com.pint.roombookerfinal.Models.Reserva;
import com.pint.roombookerfinal.Models.Sala;
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
        Call<Sala> call = apiInterface.getSala(salaId);

        call.enqueue(new Callback<Sala>() {
            @Override
            public void onResponse(Call<Sala> call, Response<Sala> response) {
                if (response.body() != null) {
                    Log.e("Success",response.body().toString());
                    Sala sala = response.body();
                    System.out.println("++++++ on Response ++++++");
                    List<Reserva> reservasList = (List<Reserva>) response.body().getReservas();
                    for (Reserva reserva :reservasList) {
                        String content = "";
                        content += "Horario Inicio: " + reserva.getHoraInicio() + "\n";
                        content += "Horario Fim: " + reserva.getHoraFim() + "\n";
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
            public void onFailure(Call<Sala> call, Throwable t) {
                Log.e("Failure", t.getLocalizedMessage());
            }
        });
    }
}