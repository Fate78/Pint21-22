package com.pint.roombookerfinal.Sala;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pint.roombookerfinal.API.ApiClient;
import com.pint.roombookerfinal.API.ApiInterface;
import com.pint.roombookerfinal.Methods;
import com.pint.roombookerfinal.MethodsInterface;
import com.pint.roombookerfinal.Models.Reserva;
import com.pint.roombookerfinal.Models.Sala;
import com.pint.roombookerfinal.R;

import java.time.LocalDate;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservasSalaActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Context mCtx;
    MethodsInterface methodsInterface = new Methods();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservas_sala);

        int salaId = getIntent().getIntExtra("IdSala", 0);
        String nSala = getIntent().getStringExtra("NSala");
        String activityTitle = "Reservas Sala " + nSala;
        setTitle(activityTitle);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.rv_reservas);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<Sala> call = apiInterface.getSalaReservas(salaId);

        call.enqueue(new Callback<Sala>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(@NonNull Call<Sala> call, @NonNull Response<Sala> response) {
                if (response.body() != null) {
                    Log.e("Success",response.body().toString());

                    List<Reserva> reservasList = response.body().getReservas();
                    ListIterator<Reserva> iterator = reservasList.listIterator();
                    if(reservasList.isEmpty())
                    {
                        Toast.makeText(ReservasSalaActivity.this, "Não foram encontradas reservas!!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        while (iterator.hasNext()) {
                            String string_data_reserva = iterator.next().getDataReserva();
                            LocalDate data_reserva = methodsInterface.stringToDate(string_data_reserva);
                            LocalDate today = methodsInterface.getDateToday();

                            if (data_reserva.compareTo(today) < 0) {
                                iterator.remove();
                            }
                        }
                    }
                    recyclerView.setAdapter(new ReservarRecyclerViewAdapter(mCtx, reservasList) );
                }
            }

            @Override
            public void onFailure(@NonNull Call<Sala> call, @NonNull Throwable t) {
                Log.e("Failure", t.getLocalizedMessage());
            }
        });
    }
}