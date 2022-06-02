package com.pint.roombookerfinal.NavigationUI.salas;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pint.roombookerfinal.API.ApiClient;
import com.pint.roombookerfinal.API.ApiInterface;
import com.pint.roombookerfinal.Models.CentroGeo;
import com.pint.roombookerfinal.Models.Sala;
import com.pint.roombookerfinal.R;
import com.pint.roombookerfinal.Sala.SalasRecyclerViewAdapter;
import com.pint.roombookerfinal.SharedPrefManager;
import com.pint.roombookerfinal.databinding.FragmentSalasBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalasFragment extends Fragment {

    private FragmentSalasBinding binding;
    RecyclerView recyclerView;
    Context mCtx;
    Integer centroId;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSalasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final FragmentActivity fragmentActivity = getActivity();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(fragmentActivity);

        recyclerView = root.findViewById(R.id.rv_salas1);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        centroId = new SharedPrefManager(getActivity()).getCentroId();

        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<CentroGeo> call = apiInterface.getCentrobyId(centroId);
        System.out.println("++++++ Request ++++++");

        call.enqueue(new Callback<CentroGeo>() {
            @Override
            public void onResponse(@NonNull Call<CentroGeo> call, @NonNull Response<CentroGeo> response) {
                if (response.body() != null) {
                    Log.e("Success",response.body().toString());
                    List<Sala> salasList = response.body().getSalas();
                    recyclerView.setAdapter(new SalasRecyclerViewAdapter(mCtx, salasList) );
                }
            }

            @Override
            public void onFailure(@NonNull Call<CentroGeo> call, @NonNull Throwable t) {
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