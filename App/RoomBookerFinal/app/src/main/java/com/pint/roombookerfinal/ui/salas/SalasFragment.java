package com.pint.roombookerfinal.ui.salas;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pint.roombookerfinal.ApiClient;
import com.pint.roombookerfinal.ApiInterface;
import com.pint.roombookerfinal.Models.Salas;
import com.pint.roombookerfinal.R;
import com.pint.roombookerfinal.RecyclerViewAdapter;
import com.pint.roombookerfinal.databinding.FragmentSalasBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalasFragment extends Fragment {

    private FragmentSalasBinding binding;
    RecyclerView recyclerView;
    Context mCtx;
    FragmentManager fragmentManager;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SalasViewModel galleryViewModel =
                new ViewModelProvider(this).get(SalasViewModel.class);

        binding = FragmentSalasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final FragmentActivity fragmentActivity = getActivity();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(fragmentActivity);

        recyclerView = root.findViewById(R.id.rv_salas1);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<List<Salas>> call = apiInterface.getSalas();
        System.out.println("++++++ Request ++++++");

        call.enqueue(new Callback<List<Salas>>() {
            @Override
            public void onResponse(Call<List<Salas>> call, Response<List<Salas>> response) {
                Log.e("Success",response.body().toString());
                List<Salas> salasList = response.body();
                System.out.println("++++++ on Response ++++++");
                for (Salas salas:salasList) {
                    String content = "";
                    content += "Sala Nº " + salas.getnSala() + "\n";
                }
                recyclerView.setAdapter(new RecyclerViewAdapter(mCtx, salasList) );
            }

            @Override
            public void onFailure(Call<List<Salas>> call, Throwable t) {
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