package com.pint.roombookerfinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pint.roombookerfinal.Models.Salas;

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

    public RecyclerViewAdapter(Context mCtx, List<Salas> listsalas){

    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.item_sala, null);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Salas salas = salasList.get(position);
        holder.n_sala.setText(salas.getnSala());
    }

    @Override
    public int getItemCount() {
        return 0;
    }


}
