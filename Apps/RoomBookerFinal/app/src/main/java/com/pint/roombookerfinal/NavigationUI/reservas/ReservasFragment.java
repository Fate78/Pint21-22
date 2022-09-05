package com.pint.roombookerfinal.NavigationUI.reservas;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
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
import com.pint.roombookerfinal.R;
import com.pint.roombookerfinal.SharedPrefManager;
import com.pint.roombookerfinal.databinding.FragmentReservasBinding;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservasFragment extends Fragment {

    private FragmentReservasBinding binding;
    RecyclerView recyclerView;
    Context mCtx;
    String username;
    final MethodsInterface methodsInterface = new Methods();
    String TokenType = "Bearer ";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentReservasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final FragmentActivity fragmentActivity = getActivity();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(fragmentActivity);

        recyclerView = root.findViewById(R.id.rv_reservas_utilizador);
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
                    methodsInterface.logout(root.getContext());
                }
                if (response.body() != null){
                    Log.e("Success",response.body().toString());
                    List<Reserva> reservasList = response.body().getReservas();
                    ListIterator<Reserva> iterator = reservasList.listIterator();

                    while(iterator.hasNext())
                    {
                        Reserva next_iterator = iterator.next();
                        String string_data_reserva = next_iterator.getDataReserva();
                        String string_hora_inicio_reserva = next_iterator.getHoraInicio();
                        boolean ativo = next_iterator.isAtivo();
                        LocalDate data_reserva = methodsInterface.stringToDate(string_data_reserva);
                        LocalDate today = methodsInterface.getDateToday();
                        LocalTime hora_inicio = methodsInterface.stringToTime(string_hora_inicio_reserva);

                        if ((data_reserva.compareTo(today)==0 &&
                                hora_inicio.compareTo(methodsInterface.getTimeNow())<0)
                                || !ativo || data_reserva.compareTo(today)<0)
                            iterator.remove();

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}