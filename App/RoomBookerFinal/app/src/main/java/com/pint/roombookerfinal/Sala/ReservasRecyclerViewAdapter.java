package com.pint.roombookerfinal.Sala;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

public class ReservasRecyclerViewAdapter extends RecyclerView.Adapter<ReservasRecyclerViewAdapter.ViewHolder> {

    private List<Reserva> reservasList;
    private Context mCtx;
    private String input_hora_inicio;
    private String input_hora_fim;
    private String input_data_reserva;
    private String input_num_pessoas;
    private String nsala;
    private String lotacao;

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
            @Override
            public void onClick(View v) {
                final EditText ed_hora_inicio = new EditText(v.getContext());
                final EditText ed_hora_fim = new EditText(v.getContext());
                final EditText ed_data_reserva = new EditText(v.getContext());
                final EditText ed_num_pessoas = new EditText(v.getContext());
                final TextView txt_lotacao = new TextView(v.getContext());

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

                AlertDialog.Builder dialog_reservar = new AlertDialog.Builder(v.getContext());
                dialog_reservar.setTitle("Reservar Sala " + nsala);

                ed_hora_inicio.setInputType(InputType.TYPE_CLASS_DATETIME);
                ed_hora_fim.setInputType(InputType.TYPE_CLASS_DATETIME);
                ed_data_reserva.setInputType(InputType.TYPE_CLASS_DATETIME);
                ed_num_pessoas.setInputType(InputType.TYPE_CLASS_NUMBER);
                dialog_reservar.setView(ed_hora_inicio);
                dialog_reservar.setView(ed_hora_fim);
                dialog_reservar.setView(ed_data_reserva);
                dialog_reservar.setView(ed_num_pessoas);
                dialog_reservar.setView(txt_lotacao);

                ed_hora_inicio.setText(formatTime(reserva.getHoraInicio().toString()));
                ed_hora_fim.setText(formatTime(reserva.getHoraFim().toString()));
                ed_data_reserva.setText(formatTime(reserva.getDataReserva().toString()));
                txt_lotacao.setText(lotacao);

                dialog_reservar.setPositiveButton("Reservar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        input_hora_inicio = ed_hora_inicio.getText().toString();
                        input_hora_fim = ed_hora_fim.getText().toString();
                        input_data_reserva = ed_data_reserva.getText().toString();
                        input_num_pessoas = ed_num_pessoas.getText().toString();
                    }
                });
                dialog_reservar.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                dialog_reservar.show();
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
