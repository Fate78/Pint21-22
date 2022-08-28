package com.pint.roombookerfinal.Sala;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
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
import com.pint.roombookerfinal.SharedPrefManager;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservasSalaActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Context mCtx;
    MethodsInterface methodsInterface = new Methods();
    final ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
    private EditText ed_lotacao;
    private EditText ed_tempo_limp;
    private String nSala;
    String TokenType = "Bearer ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservas_sala);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

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
                            Reserva next_iterator = iterator.next();
                            String string_data_reserva = next_iterator.getDataReserva();
                            boolean ativo = next_iterator.isAtivo();
                            LocalDate data_reserva = methodsInterface.stringToDate(string_data_reserva);
                            LocalDate today = methodsInterface.getDateToday();

                            if (data_reserva.compareTo(today) < 0 || !ativo) {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.reservar_button, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.action_add_reserva:
                createDialogReservar(ReservasSalaActivity.this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createDialogReservar(Context mCtx){
        int salaId = getIntent().getIntExtra("IdSala", 0);
        String nSala = getIntent().getStringExtra("NSala");

        final Dialog dialog = new Dialog(mCtx);
        dialog.setContentView(R.layout.reservar_dialog);
        dialog.setTitle("Reservar Sala " + nSala);

        EditText ed_hora_inicio = dialog.findViewById(R.id.ed_hora_inicio);
        EditText ed_hora_fim = dialog.findViewById(R.id.ed_hora_fim);
        EditText ed_data_reserva = dialog.findViewById(R.id.ed_data_reserva);
        EditText ed_num_pessoas = dialog.findViewById(R.id.ed_num_pessoas);
        EditText ed_lotacao = dialog.findViewById(R.id.ed_lotacao);
        EditText ed_tempo_limp = dialog.findViewById(R.id.ed_tempo_limpeza);
        Button btn_accept = dialog.findViewById(R.id.btn_accept);
        Button btn_cancel = dialog.findViewById(R.id.btn_cancel);

        //disable keyboard
        methodsInterface.disableSoftInputFromAppearing(ed_hora_inicio);
        methodsInterface.disableSoftInputFromAppearing(ed_hora_fim);
        methodsInterface.disableSoftInputFromAppearing(ed_data_reserva);
        methodsInterface.disableSoftInputFromAppearing(ed_lotacao);
        methodsInterface.disableSoftInputFromAppearing(ed_tempo_limp);

        ed_hora_inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ed_hora_inicio = dialog.findViewById(R.id.ed_hora_inicio);
                methodsInterface.popTimePicker(v, ed_hora_inicio);
            }
        });

        ed_hora_fim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ed_hora_fim = dialog.findViewById(R.id.ed_hora_fim);
                methodsInterface.popTimePicker(v, ed_hora_fim);
            }
        });

        ed_data_reserva.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ed_data_reserva = dialog.findViewById(R.id.ed_data_reserva);
                methodsInterface.popDatePicker(v, ed_data_reserva);
            }
        }));

        ed_lotacao.setText(getIntent().getStringExtra("Lotacao"));
        ed_tempo_limp.setText(getIntent().getStringExtra("Limpeza"));

        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(1000,900);

        btn_accept.setOnClickListener(v12 -> {
            if(ed_hora_inicio.getText().toString().isEmpty() || ed_hora_fim.getText().toString().isEmpty()
                    || ed_data_reserva.getText().toString().isEmpty() || ed_num_pessoas.getText().toString().isEmpty())
            {
                Toast.makeText(mCtx, "Preencha todos os campos!", Toast.LENGTH_LONG).show();
            }
            else {
                String string_hora_inicio = ed_hora_inicio.getText().toString();
                String string_hora_fim = ed_hora_fim.getText().toString();
                String string_data_reserva = ed_data_reserva.getText().toString();
                String string_tempo_limp = ed_tempo_limp.getText().toString();
                String string_lotacao = ed_lotacao.getText().toString();

                Integer num_pessoas = Integer.parseInt(ed_num_pessoas.getText().toString());
                Integer userId = new SharedPrefManager(v12.getContext()).getUserId();

                String formattedDate = methodsInterface.formatDateForAPI(string_data_reserva);
                LocalDate data_reserva = methodsInterface.stringToDate(formattedDate);
                LocalTime hora_inicio = methodsInterface.stringToTime(string_hora_inicio);
                LocalTime hora_fim = methodsInterface.stringToTime(string_hora_fim);

                Duration tempo_limp = methodsInterface.stringToDuration(string_tempo_limp);

                if (data_reserva.compareTo(methodsInterface.getDateToday()) >= 0) {
                    Call<List<Reserva>> reservaCall = apiInterface.getReservasbyDate(formattedDate);
                    reservaCall.enqueue(new Callback<List<Reserva>>() {
                        @Override
                        public void onResponse(@NonNull Call<List<Reserva>> call, @NonNull Response<List<Reserva>> response) {
                            int error_counter = 0;
                            if (response.body() != null) {
                                int next_index = 1;
                                Log.e("Success", response.body().toString());
                                List<Reserva> reservaList = (List<Reserva>) response.body();
                                for (Reserva reserva : reservaList) {
                                    Reserva next_reserva = null;
                                    LocalTime next_hora_inicio = null;
                                    LocalTime hora_fim_max = null;
                                    LocalTime res_hora_inicio = null;
                                    LocalTime res_hora_fim = null;
                                    LocalTime hora_inicio_min = null;

                                    //If next exists
                                    if (reservaList.size() >= next_index + 1) {
                                        next_reserva = reservaList.get(next_index);
                                        next_index++;
                                        next_hora_inicio = methodsInterface.stringToTime(next_reserva.getHoraInicio());
                                        hora_fim_max = methodsInterface.addDurationToHour(hora_fim, tempo_limp);
                                    } else {
                                        hora_fim_max = LocalTime.parse("23:00");
                                        next_hora_inicio = LocalTime.parse("23:00");
                                    }
                                    res_hora_inicio = methodsInterface.stringToTime(reserva.getHoraInicio());
                                    res_hora_fim = methodsInterface.stringToTime(reserva.getHoraFim());
                                    hora_inicio_min = methodsInterface.addDurationToHour(res_hora_fim, tempo_limp);

                                    System.out.println(hora_inicio_min);
                                    System.out.println(hora_fim_max);
                                    System.out.println(next_hora_inicio);
                                    if (hora_inicio.compareTo(hora_inicio_min) < 0 || hora_fim_max.compareTo(next_hora_inicio) > 0) {
                                        error_counter++;
                                    }
                                }
                            }
                            if (error_counter == 0) {
                                System.out.println("Nova reserva criada");
                                Reserva newReserva = new Reserva(
                                        salaId, userId, string_hora_inicio, string_hora_fim,
                                        methodsInterface.formatDateForAPI(string_data_reserva),
                                        num_pessoas, true);
                                criarReserva(newReserva, v12.getContext());
                            } else
                                System.out.println(error_counter);
                        }

                        @Override
                        public void onFailure(@NonNull Call<List<Reserva>> call, @NonNull Throwable t) {
                            Log.e("Failure", t.getLocalizedMessage());
                        }
                    });
                } else
                    System.out.println("Não é possível criar reserva nesta data");
            }
        });
        btn_cancel.setOnClickListener(v1 -> dialog.dismiss());
    }

    public void criarReserva(Reserva reserva, Context mCtx)
    {
        String AuthToken = new SharedPrefManager(mCtx).getAuthToken();

        Call<Reserva> reservaPost = apiInterface.createReserva(reserva, TokenType + AuthToken);
        reservaPost.enqueue(new Callback<Reserva>() {
            @Override
            public void onResponse(@NonNull Call<Reserva> call1,
                                   @NonNull Response<Reserva> response) {
                Reserva responseReserva = response.body();
                if (response.isSuccessful() && responseReserva != null) {
                    Toast.makeText(mCtx, String.format(
                                    "Reserva para dia %s das %s às %s foi criada",
                                    responseReserva.getDataReserva(),
                                    responseReserva.getHoraInicio(),
                                    responseReserva.getHoraFim()),
                            Toast.LENGTH_LONG).show();
                } else {
                    System.out.println(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Reserva> call1, @NonNull Throwable t) {
                Log.e("Failure", t.getLocalizedMessage());
            }
        });
    }
}