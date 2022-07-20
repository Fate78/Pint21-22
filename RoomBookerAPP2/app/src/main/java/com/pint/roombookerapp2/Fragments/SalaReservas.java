package com.pint.roombookerapp2.Fragments;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pint.roombookerapp2.API.ApiClient;
import com.pint.roombookerapp2.API.ApiInterface;
import com.pint.roombookerapp2.Methods;
import com.pint.roombookerapp2.MethodsInterface;
import com.pint.roombookerapp2.Models.Reserva;
import com.pint.roombookerapp2.Models.Sala;
import com.pint.roombookerapp2.R;
import com.pint.roombookerapp2.databinding.FragmentSalaReservasBinding;

import java.time.LocalDate;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalaReservas extends Fragment {

    private FragmentSalaReservasBinding binding;
    public static final String TITLE = "title";
    RecyclerView recyclerView;
    Context mCtx;
    String username;
    final MethodsInterface methodsInterface = new Methods();
    EditText ed_data_inicio, ed_data_fim;

    public SalaReservas() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSalaReservasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final FragmentActivity fragmentActivity = getActivity();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(fragmentActivity);

        recyclerView = root.findViewById(R.id.rv_reservas_sala);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<Sala> call = apiInterface.getSalaReservas(51);

        call.enqueue(new Callback<Sala>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(@NonNull Call<Sala> call, @NonNull Response<Sala> response) {
                if (response.body() != null){
                    Log.e("Success",response.body().toString());
                    List<Reserva> reservasList = response.body().getReservas();
                    ListIterator<Reserva> iterator = reservasList.listIterator();

                    while(iterator.hasNext())
                    {
                        Reserva next_iterator = iterator.next();
                        String string_data_reserva = next_iterator.getDataReserva();
                        boolean ativo = next_iterator.isAtivo();
                        LocalDate data_reserva = methodsInterface.stringToDate(string_data_reserva);
                        LocalDate today = methodsInterface.getDateToday();
                        if (data_reserva.compareTo(today)<0 || !ativo)
                            iterator.remove();

                    }
                    recyclerView.setAdapter(new ReservasSalaRecyclerViewAdapter(mCtx, reservasList) );
                }
            }

            @Override
            public void onFailure(@NonNull Call<Sala> call, @NonNull Throwable t) {
                Log.e("Failure", t.getLocalizedMessage());
            }
        });

        return root;
    }
}