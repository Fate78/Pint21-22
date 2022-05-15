package com.pint.roombookerfinal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pint.roombookerfinal.Models.Salas;
import com.pint.roombookerfinal.Sala.SalaActivity;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Salas> salasList;
    private Context mCtx;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView n_sala;

        public ViewHolder(View view){
            super(view);
            n_sala = (TextView) view.findViewById(R.id.txt_nsala);
        }
    }

    public RecyclerViewAdapter(Context mCtx, List<Salas> salasList){
        this.salasList = salasList;
        this.mCtx = mCtx;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_sala, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull RecyclerViewAdapter.ViewHolder holder, int position) {

        Salas salas = salasList.get(position);
        holder.n_sala.setText("Sala nº" + salas.getnSala().toString());
        holder.n_sala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SalaActivity.class);
                intent.putExtra("IdSala", salas.getIdSala());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return salasList.size();
    }

    private void sendSalaId(int id){

    }
}
