package com.pint.roombookerfinal.NavigationUI.reservas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pint.roombookerfinal.Methods;
import com.pint.roombookerfinal.MethodsInterface;
import com.pint.roombookerfinal.Models.Reserva;
import com.pint.roombookerfinal.R;

import java.util.List;

public class ReservasUtilizadorRecyclerViewAdapter extends
        RecyclerView.Adapter<ReservasUtilizadorRecyclerViewAdapter.ViewHolder> implements View.OnClickListener {

    private final List<Reserva> reservasList;
    final MethodsInterface methodsInterface = new Methods();

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txt_data:
            case R.id.txt_hora_inicio:
            case R.id.txt_hora_fim:
                //Open Dialog to update or cancel reserva if possible
                break;
        }
    }

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

    public ReservasUtilizadorRecyclerViewAdapter(Context mCtx, List<Reserva> reservasList){
        this.reservasList = reservasList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_reserva, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservasUtilizadorRecyclerViewAdapter.ViewHolder holder, int position) {
        Reserva reserva = reservasList.get(position);
        holder.hora_inicio.setText(
                methodsInterface.formatTimeForUser(reserva.getHoraInicio()));
        holder.hora_fim.setText(
                methodsInterface.formatTimeForUser(reserva.getHoraFim()));
        holder.data_reserva.setText(
                (methodsInterface.formatDateForUser(reserva.getDataReserva())));
        holder.data_reserva.setOnClickListener(this);
        holder.hora_inicio.setOnClickListener(this);
        holder.hora_fim.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return reservasList.size();
    }
}
