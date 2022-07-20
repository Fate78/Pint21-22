package com.pint.roombookerapp2.Fragments;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.pint.roombookerapp2.API.ApiClient;
import com.pint.roombookerapp2.API.ApiInterface;
import com.pint.roombookerapp2.Methods;
import com.pint.roombookerapp2.MethodsInterface;
import com.pint.roombookerapp2.Models.CentroGeo;
import com.pint.roombookerapp2.Models.Reserva;
import com.pint.roombookerapp2.Models.Sala;
import com.pint.roombookerapp2.R;

import java.util.List;

public class ReservasSalaRecyclerViewAdapter extends
        RecyclerView.Adapter<ReservasSalaRecyclerViewAdapter.ViewHolder> {

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

    public ReservasSalaRecyclerViewAdapter(Context mCtx, List<Reserva> reservasList){
        this.reservasList = reservasList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_reserva, parent, false);

        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ReservasSalaRecyclerViewAdapter.ViewHolder holder, int position) {
        Reserva reserva = reservasList.get(position);

        holder.hora_inicio.setText(
                methodsInterface.formatTimeForUser(reserva.getHoraInicio()));
        holder.hora_fim.setText(
                methodsInterface.formatTimeForUser(reserva.getHoraFim()));
        holder.data_reserva.setText(
                (methodsInterface.formatDateForUser(reserva.getDataReserva())));
    }

    @Override
    public int getItemCount() {
        return reservasList.size();
    }

}
