package com.pint.roombookerfinal.Sala;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservarRecyclerViewAdapter extends
        RecyclerView.Adapter<ReservarRecyclerViewAdapter.ViewHolder> {

    private Integer userId, num_pessoas, error_counter;
    private String string_hora_inicio, string_hora_fim, string_data_reserva, nsala, lotacao, string_tempo_limp;
    private EditText ed_hora_inicio, ed_hora_fim, ed_data_reserva,
            ed_num_pessoas, ed_lotacao, ed_tempo_limp;
    private Button btn_accept, btn_cancel;
    private final List<Reserva> reservasList;
    final MethodsInterface methodsInterface = new Methods();
    final ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView hora_inicio;
        public final TextView hora_fim;
        public final TextView data_reserva;

        public ViewHolder(View view){
            super(view);
            hora_inicio = view.findViewById(R.id.txt_hora_inicio);
            hora_fim = view.findViewById(R.id.txt_hora_fim);
            data_reserva = view.findViewById(R.id.txt_data);
        }
    }

    public ReservarRecyclerViewAdapter(Context mCtx, List<Reserva> reservasList){
        this.reservasList = reservasList;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent,
                                         int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_reserva, parent, false);

        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull
                                             ReservarRecyclerViewAdapter.ViewHolder holder,
                                 int position) {

        Reserva reserva = reservasList.get(position);
        holder.hora_inicio.setText(
                methodsInterface.formatTimeForUser(reserva.getHoraInicio()));
        holder.hora_fim.setText(
                methodsInterface.formatTimeForUser(reserva.getHoraFim()));
        holder.data_reserva.setText(
                (methodsInterface.formatDateForUser(reserva.getDataReserva())));

        holder.data_reserva.setOnClickListener(v -> {
            Call<Sala> call = apiInterface.getSala(reserva.getIdSala());

            call.enqueue(new Callback<Sala>() {
                @Override
                public void onResponse(@NonNull Call<Sala> call, @NonNull Response<Sala> response)
                {
                    if (response.body() != null) {
                        Log.e("Success",response.body().toString());
                        Sala sala = response.body();
                        lotacao = sala.getLotacaoMax().toString();
                        nsala = sala.getnSala().toString();
                        string_tempo_limp = methodsInterface.formatTimeForUser(
                                sala.getTempoMinLimp());
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Sala> call, @NonNull Throwable t) {
                    Log.e("Failure", t.getLocalizedMessage());
                }
            });
            final Dialog dialog = new Dialog(v.getContext());
            dialog.setContentView(R.layout.reservar_dialog);

            dialog.setTitle("Reservar Sala " + nsala);

            ed_hora_inicio =  dialog.findViewById(R.id.ed_hora_inicio);
            ed_hora_fim = dialog.findViewById(R.id.ed_hora_fim);
            ed_data_reserva = dialog.findViewById(R.id.ed_data_reserva);
            ed_num_pessoas = dialog.findViewById(R.id.ed_num_pessoas);
            ed_lotacao = dialog.findViewById(R.id.ed_lotacao);
            ed_tempo_limp = dialog.findViewById(R.id.ed_tempo_limpeza);
            btn_accept = dialog.findViewById(R.id.btn_accept);
            btn_cancel = dialog.findViewById(R.id.btn_cancel);

            ed_hora_inicio.setText(methodsInterface.formatTimeForUser(
                    reserva.getHoraInicio()));
            ed_hora_fim.setText(methodsInterface.formatTimeForUser(
                    reserva.getHoraFim()));
            ed_data_reserva.setText(methodsInterface.formatDateForUser(
                    reserva.getDataReserva()));
            ed_lotacao.setText(lotacao);
            ed_tempo_limp.setText(string_tempo_limp);

            dialog.show();
            Window window = dialog.getWindow();
            window.setLayout(1000,900);

            btn_accept.setOnClickListener(v12 -> {
                string_hora_inicio = ed_hora_inicio.getText().toString();
                string_hora_fim = ed_hora_fim.getText().toString();
                string_data_reserva = ed_data_reserva.getText().toString();
                num_pessoas = Integer.parseInt(ed_num_pessoas.getText().toString());
                userId = new SharedPrefManager(v12.getContext()).getUserId();

                String formattedDate = methodsInterface.formatDateForAPI(string_data_reserva);
                LocalDate data_reserva = methodsInterface.stringToDate(formattedDate);
                LocalTime hora_inicio = methodsInterface.stringToTime(string_hora_inicio);
                LocalTime hora_fim = methodsInterface.stringToTime(string_hora_fim);
                Duration tempo_limp = methodsInterface.stringToDuration(string_tempo_limp);

                if (data_reserva.compareTo(methodsInterface.getDateToday())>=0)
                {
                    Call<List<Reserva>> reservaCall = apiInterface.getReservasbyDate(formattedDate);
                    reservaCall.enqueue(new Callback<List<Reserva>>() {
                        @Override
                        public void onResponse(@NonNull Call<List<Reserva>> call, @NonNull Response<List<Reserva>> response)
                        {
                            if (response.body() != null) {
                                int next_index = 0;
                                error_counter = 0;
                                Log.e("Success",response.body().toString());
                                List<Reserva> reservaList = (List<Reserva>) response.body();
                                for (Reserva reserva :reservaList){
                                    Reserva next_reserva = reservaList.get(next_index);
                                    next_index++;
                                    LocalTime res_hora_inicio = methodsInterface.stringToTime(reserva.getHoraInicio());
                                    LocalTime res_hora_fim = methodsInterface.stringToTime(reserva.getHoraFim());
                                    LocalTime next_hora_inicio = methodsInterface.stringToTime(next_reserva.getHoraInicio());
                                    LocalTime hora_inicio_min = methodsInterface.addDurationToHour(res_hora_fim, tempo_limp);
                                    LocalTime hora_fim_max = methodsInterface.addDurationToHour(hora_fim, tempo_limp);

                                    if(hora_inicio.compareTo(hora_inicio_min)<0 || hora_fim_max.compareTo(next_hora_inicio)>0)
                                    {
                                        error_counter++;
                                    }
                                }
                                if(error_counter==0)
                                {
                                    System.out.println("Nova reserva criada");
                                    Reserva newReserva = new Reserva(
                                            reserva.getIdSala(), userId, string_hora_inicio, string_hora_fim,
                                            methodsInterface.formatDateForAPI(string_data_reserva),
                                            num_pessoas, true);
                                    criarReserva(newReserva, v12.getContext());
                                }
                                else
                                    System.out.println(error_counter);
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<List<Reserva>> call, @NonNull Throwable t) {
                            Log.e("Failure", t.getLocalizedMessage());
                        }
                    });
                }else
                    System.out.println("Não é possível criar reserva nesta data");
            });
            btn_cancel.setOnClickListener(v1 -> dialog.dismiss());
        });
    }

    @Override
    public int getItemCount() {
        return reservasList.size();
    }

    public void criarReserva(Reserva reserva, Context mCtx)
    {
        Call<Reserva> reservaPost = apiInterface.createReserva(reserva);
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
