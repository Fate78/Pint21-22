package com.pint.roombookerfinal.ui.salas;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.pint.roombookerfinal.ApiClient;
import com.pint.roombookerfinal.ApiInterface;
import com.pint.roombookerfinal.Models.Salas;
import com.pint.roombookerfinal.databinding.FragmentSalaDetailsBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalaDetailsFragment extends Fragment {

    private SalaDetailsViewModel mViewModel;
    private FragmentSalaDetailsBinding binding;
    Context mCtx;

    public static SalaDetailsFragment newInstance() {
        return new SalaDetailsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSalaDetailsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final FragmentActivity fragmentActivity = getActivity();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(fragmentActivity);

        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<Salas> call = apiInterface.getSala(getSalaId());

        call.enqueue(new Callback<Salas>() {
            @Override
            public void onResponse(Call<Salas> call, Response<Salas> response) {
                Log.e("Success",response.body().toString());
                Salas sala = response.body();
                System.out.println("++++++ on Response ++++++");
                String content = "";
                content += "Sala nº " + sala.getnSala();
                content += "\nLotação: " + sala.getLotacaoMax();
                content += "\nTempo Limpeza: " + sala.getTempoMinLimp();
                content += "\nAtiva: " + sala.getAtivo();
                System.out.println(content);
            }

            @Override
            public void onFailure(Call<Salas> call, Throwable t) {
                Log.e("Failure", t.getLocalizedMessage());
            }
        });
        return root;
    }

    private Integer getSalaId(){
        Bundle bundle = this.getArguments();
        Integer id_sala=null;
        if (bundle!=null)
            id_sala = bundle.getInt("IdSala");
        return id_sala;
    }

}