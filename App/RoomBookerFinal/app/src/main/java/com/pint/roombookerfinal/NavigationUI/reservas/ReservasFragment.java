package com.pint.roombookerfinal.NavigationUI.reservas;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pint.roombookerfinal.API.ApiClient;
import com.pint.roombookerfinal.API.ApiInterface;
import com.pint.roombookerfinal.Models.Reserva;
import com.pint.roombookerfinal.Models.Utilizador;
import com.pint.roombookerfinal.R;
import com.pint.roombookerfinal.SharedPrefManager;
import com.pint.roombookerfinal.databinding.FragmentReservasBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservasFragment extends Fragment {

    private FragmentReservasBinding binding;
    RecyclerView recyclerView;
    Context mCtx;
    SharedPrefManager sharedPrefManager;
    String username;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ReservasViewModel slideshowViewModel =
                new ViewModelProvider(this).get(ReservasViewModel.class);

        binding = FragmentReservasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final FragmentActivity fragmentActivity = getActivity();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(fragmentActivity);

        recyclerView = root.findViewById(R.id.rv_reservas_utilizador);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        sharedPrefManager = new SharedPrefManager(this.getActivity());
        username = sharedPrefManager.getUsername();

        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<Utilizador> call = apiInterface.getUtilizadorReservas(username);
        call.enqueue(new Callback<Utilizador>() {
            @Override
            public void onResponse(@NonNull Call<Utilizador> call, @NonNull Response<Utilizador> response) {
                if (response.body() != null){
                    Log.e("Success",response.body().toString());
                    List<Reserva> reservaList = response.body().getReservas();
                    //recyclerView.setAdapter(new SalasRecyclerViewAdapter(mCtx, reservaList) );
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