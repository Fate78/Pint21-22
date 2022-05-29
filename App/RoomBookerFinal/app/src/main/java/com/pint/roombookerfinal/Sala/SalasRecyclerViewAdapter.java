package com.pint.roombookerfinal.Sala;

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

import java.util.List;

public class SalasRecyclerViewAdapter extends RecyclerView.Adapter<SalasRecyclerViewAdapter.ViewHolder> {

    private List<Sala> salasList;
    private Context mCtx;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView n_sala;
        public TextView nomecentro;

        public ViewHolder(View view){
            super(view);
            n_sala = (TextView) view.findViewById(R.id.txt_nsala);
            nomecentro = (TextView) view.findViewById(R.id.txt_nomecentro);
        }
    }

    public SalasRecyclerViewAdapter(Context mCtx, List<Sala> salasList){
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
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull SalasRecyclerViewAdapter.ViewHolder holder, int position) {

        Sala sala = salasList.get(position);
        holder.n_sala.setText("Sala nº" + sala.getnSala().toString());
        holder.n_sala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SalaActivity.class);
                intent.putExtra("IdSala", sala.getIdSala());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return salasList.size();
    }
}