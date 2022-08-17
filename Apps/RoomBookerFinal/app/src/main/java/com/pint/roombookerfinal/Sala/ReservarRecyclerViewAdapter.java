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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.pint.roombookerfinal.API.ApiClient;
import com.pint.roombookerfinal.API.ApiInterface;
import com.pint.roombookerfinal.Methods;
import com.pint.roombookerfinal.MethodsInterface;
import com.pint.roombookerfinal.Models.CentroGeo;
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

    private String lotacao, nsala, string_tempo_limp;
    int hour, minute, view_id, id_centro;
    private final List<Reserva> reservasList;
    final MethodsInterface methodsInterface = new Methods();
    final ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
    String TokenType = "Bearer ";

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView hora_inicio;
        public final TextView hora_fim;
        public final TextView data_reserva;
        public final TextView centro_name;
        public final TextView n_sala;
        public final ImageButton btn_delete;

        public ViewHolder(View view){
            super(view);
            hora_inicio = view.findViewById(R.id.txt_hora_inicio);
            hora_fim = view.findViewById(R.id.txt_hora_fim);
            data_reserva = view.findViewById(R.id.txt_data);
            centro_name = view.findViewById(R.id.txt_centro);
            n_sala = view.findViewById(R.id.txt_sala);
            btn_delete = view.findViewById(R.id.imgBtn_delete);
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

        holder.btn_delete.setVisibility(View.GONE);
        Reserva reserva = reservasList.get(position);
        holder.hora_inicio.setText(
                methodsInterface.formatTimeForUser(reserva.getHoraInicio()));
        holder.hora_fim.setText(
                methodsInterface.formatTimeForUser(reserva.getHoraFim()));
        holder.data_reserva.setText(
                (methodsInterface.formatDateForUser(reserva.getDataReserva())));

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
                    id_centro = sala.getIdCentro();
                    holder.n_sala.setText("Sala " + nsala);

                    Call<CentroGeo> callCentro = apiInterface.getCentrobyId(id_centro);
                    callCentro.enqueue(new Callback<CentroGeo>() {
                        @Override
                        public void onResponse(@NonNull Call<CentroGeo> call, @NonNull Response<CentroGeo> response) {
                            if (response.body() != null) {
                                Log.e("Success",response.body().toString());
                                String nome_centro = response.body().getNomeCentro();
                                holder.centro_name.setText(nome_centro);
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<CentroGeo> call, @NonNull Throwable t) {
                            Log.e("Failure", t.getLocalizedMessage());
                        }
                    });
                }
            }

            @Override
            public void onFailure(@NonNull Call<Sala> call, @NonNull Throwable t) {
                Log.e("Failure", t.getLocalizedMessage());
            }
        });

        holder.data_reserva.setOnClickListener(v -> {
            createDialogReservar(v.getContext(), reserva);
        });
    }

    @Override
    public int getItemCount() {
        return reservasList.size();
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createDialogReservar(Context mCtx, Reserva reserva){
        final Dialog dialog = new Dialog(mCtx);
        dialog.setContentView(R.layout.reservar_dialog);

        dialog.setTitle("Reservar Sala " + nsala);

        EditText ed_hora_inicio =  dialog.findViewById(R.id.ed_hora_inicio);
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

        ed_lotacao.setText(lotacao);
        ed_hora_inicio.setText(methodsInterface.formatTimeForUser(
                reserva.getHoraInicio()));
        ed_hora_fim.setText(methodsInterface.formatTimeForUser(
                reserva.getHoraFim()));
        ed_data_reserva.setText(methodsInterface.formatDateForUser(
                reserva.getDataReserva()));
        ed_tempo_limp.setText(string_tempo_limp);
        ed_num_pessoas.setText(Integer.toString(reserva.getNumPessoas()));

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

                Integer userId = new SharedPrefManager(v12.getContext()).getUserId();

                String formattedDate = methodsInterface.formatDateForAPI(string_data_reserva);
                LocalDate data_reserva = methodsInterface.stringToDate(formattedDate);
                LocalTime hora_inicio = methodsInterface.stringToTime(string_hora_inicio);
                LocalTime hora_fim = methodsInterface.stringToTime(string_hora_fim);
                Duration tempo_limp = methodsInterface.stringToDuration(string_tempo_limp);
                int num_pessoas = Integer.parseInt(ed_num_pessoas.getText().toString());
                int lotacao = Integer.parseInt(ed_lotacao.getText().toString());

                if (data_reserva.compareTo(methodsInterface.getDateToday()) >= 0 && hora_inicio.compareTo(methodsInterface.getTimeNow())>0 && ed_num_pessoas.getText() != null && ed_data_reserva.getText() != null && ed_hora_inicio.getText() != null
                        && ed_hora_fim.getText() != null && num_pessoas <= lotacao) {

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

                                    if (reserva.isAtivo()) {
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
                            }
                            if (error_counter == 0) {
                                Reserva newReserva = new Reserva(
                                        reserva.getIdSala(), userId, string_hora_inicio, string_hora_fim,
                                        methodsInterface.formatDateForAPI(string_data_reserva),
                                        num_pessoas, true);
                                criarReserva(newReserva, v12.getContext());
                                dialog.dismiss();
                            } else
                                Toast.makeText(mCtx, "O horário inserido é inválido!",
                                        Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(@NonNull Call<List<Reserva>> call, @NonNull Throwable t) {
                            Log.e("Failure", t.getLocalizedMessage());
                        }
                    });
                } else if (num_pessoas > lotacao)
                    Toast.makeText(mCtx, "Excedeu a lotação da sala",
                            Toast.LENGTH_LONG).show();
                else if (hora_inicio.compareTo(methodsInterface.getTimeNow())<=0)
                    Toast.makeText(mCtx, "Hora inválida",
                            Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(mCtx, "Verifique que preencheu todos os campos",
                            Toast.LENGTH_LONG).show();
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
                if (responseReserva != null) {
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
