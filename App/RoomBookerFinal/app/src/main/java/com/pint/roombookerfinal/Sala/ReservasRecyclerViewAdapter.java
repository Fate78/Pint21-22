package com.pint.roombookerfinal.Sala;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.pint.roombookerfinal.ApiClient;
import com.pint.roombookerfinal.ApiInterface;
import com.pint.roombookerfinal.Models.Reserva;
import com.pint.roombookerfinal.Models.Sala;
import com.pint.roombookerfinal.R;
import com.pint.roombookerfinal.SharedPrefManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservasRecyclerViewAdapter extends RecyclerView.Adapter<ReservasRecyclerViewAdapter.ViewHolder> {

    private SharedPrefManager sharedPrefManager;
    private List<Reserva> reservasList;
    private Context mCtx;
    private Integer userId;
    private String input_hora_inicio;
    private String input_hora_fim;
    private String input_data_reserva;
    private Integer input_num_pessoas;
    private String nsala;
    private String lotacao;
    private EditText ed_hora_inicio, ed_hora_fim, ed_data_reserva, ed_num_pessoas, ed_lotacao;
    private Button btn_accept, btn_cancel;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView hora_inicio;
        public TextView hora_fim;
        public TextView data_reserva;

        public ViewHolder(View view){
            super(view);
            hora_inicio = (TextView) view.findViewById(R.id.txt_hora_inicio);
            hora_fim = (TextView) view.findViewById(R.id.txt_hora_fim);
            data_reserva = (TextView) view.findViewById(R.id.txt_data);
        }
    }

    public ReservasRecyclerViewAdapter(Context mCtx, List<Reserva> reservasList){
        this.reservasList = reservasList;
        this.mCtx = mCtx;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_reserva, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull ReservasRecyclerViewAdapter.ViewHolder holder, int position) {

        Reserva reserva = reservasList.get(position);

        holder.hora_inicio.setText(formatTime(reserva.getHoraInicio().toString()));
        holder.hora_fim.setText(formatTime(reserva.getHoraFim().toString()));
        holder.data_reserva.setText(formatDate(reserva.getDataReserva().toString()));

        holder.data_reserva.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                System.out.println(reservasList);
                ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
                Call<Sala> call = apiInterface.getSala(reserva.getIdSala());

                call.enqueue(new Callback<Sala>() {
                    @Override
                    public void onResponse(Call<Sala> call, Response<Sala> response) {
                        if (response.body() != null) {
                            Log.e("Success",response.body().toString());
                            Sala sala = response.body();
                            lotacao = sala.getLotacaoMax().toString();
                            nsala = sala.getnSala().toString();
                            System.out.println("++++++ on Response ++++++");
                        }
                    }

                    @Override
                    public void onFailure(Call<Sala> call, Throwable t) {
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
                btn_accept = dialog.findViewById(R.id.btn_accept);
                btn_cancel = dialog.findViewById(R.id.btn_cancel);

                ed_hora_inicio.setText(formatTime(reserva.getHoraInicio().toString()));
                ed_hora_fim.setText(formatTime(reserva.getHoraFim().toString()));
                ed_data_reserva.setText(formatDate(reserva.getDataReserva().toString()));
                ed_lotacao.setText(lotacao);

                dialog.show();

                btn_accept.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        input_hora_inicio = ed_hora_inicio.getText().toString();
                        input_hora_fim = ed_hora_fim.getText().toString();
                        input_data_reserva = ed_data_reserva.getText().toString();
                        input_num_pessoas = Integer.parseInt(ed_num_pessoas.getText().toString());
                        userId = new SharedPrefManager(v.getContext()).getUserId();
                        Reserva newReserva = new Reserva(reserva.getIdSala(), userId, input_hora_inicio, input_hora_fim, input_data_reserva, input_num_pessoas, true);

                        Call<Reserva> reservaCall = apiInterface.createReserva(newReserva);
                        reservaCall.enqueue(new Callback<Reserva>() {
                            @Override
                            public void onResponse(Call<Reserva> call, Response<Reserva> response) {
                                Reserva responseReserva = response.body();
                                if(response.isSuccessful() && responseReserva != null){
                                    Toast.makeText(v.getContext(), String.format("Reserva para dia %s das %s às %s foi criada",
                                            responseReserva.getDataReserva(),
                                            responseReserva.getHoraInicio(),
                                            responseReserva.getHoraFim()),
                                            Toast.LENGTH_LONG).show();
                                }
                                else{
                                    System.out.println(response.message());
                                }
                            }

                            @Override
                            public void onFailure(Call<Reserva> call, Throwable t) {
                                Log.e("Failure", t.getLocalizedMessage());
                            }
                        });
                    }
                });
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return reservasList.size();
    }

    private String formatDate(String date){
        date = date.split("T")[0];
        return date;
    }

    private String formatTime(String time){
        StringBuilder sb = new StringBuilder(time);
        sb.delete(sb.length()-3, sb.length());
        return sb.toString();
    }
}
