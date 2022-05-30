package com.pint.roombookerfinal.Sala;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pint.roombookerfinal.API.ApiClient;
import com.pint.roombookerfinal.API.ApiInterface;
import com.pint.roombookerfinal.Models.Reserva;
import com.pint.roombookerfinal.Models.Sala;
import com.pint.roombookerfinal.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservasSalaActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Context mCtx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservas_sala);

        int salaId = getIntent().getIntExtra("IdSala", 0);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.rv_reservas);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        //api/salas/{id}/reservas
        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<Sala> call = apiInterface.getSalaReservas(salaId);

        call.enqueue(new Callback<Sala>() {
            @Override
            public void onResponse(@NonNull Call<Sala> call, @NonNull Response<Sala> response) {
                if (response.body() != null) {
                    Log.e("Success",response.body().toString());
                    Sala sala = response.body();
                    List<Reserva> reservasList = response.body().getReservas();
                    recyclerView.setAdapter(new ReservarRecyclerViewAdapter(mCtx, reservasList) );
                    if(reservasList.isEmpty())
                    {
                        Toast.makeText(ReservasSalaActivity.this, "Não foram encontradas reservas!!", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Sala> call, @NonNull Throwable t) {
                Log.e("Failure", t.getLocalizedMessage());
            }
        });
    }
}