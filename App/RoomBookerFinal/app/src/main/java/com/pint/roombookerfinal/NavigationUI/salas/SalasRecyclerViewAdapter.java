package com.pint.roombookerfinal.NavigationUI.salas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pint.roombookerfinal.Models.Sala;
import com.pint.roombookerfinal.R;
import com.pint.roombookerfinal.Sala.SalaActivity;

import java.util.List;

public class SalasRecyclerViewAdapter extends RecyclerView.Adapter<SalasRecyclerViewAdapter.ViewHolder> {

    private final List<Sala> salasList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView n_sala;
        public final TextView nomecentro;

        public ViewHolder(View view){
            super(view);
            n_sala = view.findViewById(R.id.txt_nsala);
            nomecentro = view.findViewById(R.id.txt_nomecentro);
        }
    }

    public SalasRecyclerViewAdapter(Context mCtx, List<Sala> salasList){
        this.salasList = salasList;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_sala, parent, false);

        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull SalasRecyclerViewAdapter.ViewHolder holder, int position) {

        Sala sala = salasList.get(position);
        holder.n_sala.setText("Sala nº" + sala.getnSala().toString());
        holder.n_sala.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), SalaActivity.class);
            intent.putExtra("IdSala", sala.getIdSala());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return salasList.size();
    }
}
