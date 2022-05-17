package com.pint.roombookerfinal.Sala;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pint.roombookerfinal.Models.Reservas;
import com.pint.roombookerfinal.R;

import java.util.List;

public class ReservasRecyclerViewAdapter extends RecyclerView.Adapter<ReservasRecyclerViewAdapter.ViewHolder> {

    private List<Reservas> reservasList;
    private Context mCtx;

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

    public ReservasRecyclerViewAdapter(Context mCtx, List<Reservas> reservasList){
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

        Reservas reservas = reservasList.get(position);
        holder.hora_inicio.setText(formatTime(reservas.getHoraInicio().toString()));
        holder.hora_fim.setText(formatTime(reservas.getHoraFim().toString()));
        holder.data_reserva.setText(formatDate(reservas.getDataReserva().toString()));
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
