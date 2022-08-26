package com.pint.roombookerfinal.NavigationUI.historico_reservas;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pint.roombookerfinal.API.ApiClient;
import com.pint.roombookerfinal.API.ApiInterface;
import com.pint.roombookerfinal.Methods;
import com.pint.roombookerfinal.MethodsInterface;
import com.pint.roombookerfinal.Models.Reserva;
import com.pint.roombookerfinal.Models.Utilizador;
import com.pint.roombookerfinal.NavigationUI.reservas.ReservasUtilizadorRecyclerViewAdapter;
import com.pint.roombookerfinal.R;
import com.pint.roombookerfinal.SharedPrefManager;
import com.pint.roombookerfinal.databinding.FragmentHistoricoReservasBinding;

import java.time.LocalTime;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoricoReservasFragment extends Fragment {

    private FragmentHistoricoReservasBinding binding;
    RecyclerView recyclerView;
    Context mCtx;
    String username;
    final MethodsInterface methodsInterface = new Methods();
    String TokenType = "Bearer ";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentHistoricoReservasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final FragmentActivity fragmentActivity = getActivity();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(fragmentActivity);

        recyclerView = root.findViewById(R.id.rv_reservas_historico);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        username = new SharedPrefManager(getActivity()).getUsername();

        String AuthToken = new SharedPrefManager(root.getContext()).getAuthToken();

        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<Utilizador> call = apiInterface.getUtilizadorReservas(username, TokenType + AuthToken);

        call.enqueue(new Callback<Utilizador>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(@NonNull Call<Utilizador> call, @NonNull Response<Utilizador> response) {
                if(response.code() == 401)
                {
                    methodsInterface.logout(mCtx);
                }

                if (response.body() != null){
                    Log.e("Success",response.body().toString());
                    List<Reserva> reservasList = response.body().getReservas();
                    ListIterator<Reserva> iterator = reservasList.listIterator();

                    while(iterator.hasNext())
                    {

                        Reserva next_iterator = iterator.next();

                        String data_reserva = next_iterator.getDataReserva();
                        String s_hora_inicio = next_iterator.getHoraInicio();

                        LocalTime hora_inicio = methodsInterface.stringToTime(s_hora_inicio);
                        boolean ativo = next_iterator.isAtivo();

                        if ((data_reserva.compareTo(methodsInterface.getDateToday().toString())>0 &&
                                hora_inicio.compareTo(methodsInterface.getTimeNow())>0)
                            || !ativo || data_reserva.compareTo(methodsInterface.getDateToday().toString())>0)
                        {
                            iterator.remove();
                        }
                    }

                    recyclerView.setAdapter(new ReservasUtilizadorRecyclerViewAdapter(mCtx, reservasList) );
                }
            }

            @Override
            public void onFailure(@NonNull Call<Utilizador> call, @NonNull Throwable t) {
                Log.e("Failure", t.getLocalizedMessage());
            }
        });
        return root;
    }

}