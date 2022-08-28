package com.pint.roombookerapp2.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.pint.roombookerapp2.API.ApiClient;
import com.pint.roombookerapp2.API.ApiInterface;
import com.pint.roombookerapp2.Methods;
import com.pint.roombookerapp2.MethodsInterface;
import com.pint.roombookerapp2.Models.Sala;
import com.pint.roombookerapp2.R;
import com.pint.roombookerapp2.SharedPrefManager;
import com.pint.roombookerapp2.databinding.FragmentSalaInfoBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalaInfo extends Fragment {

    public static final String TITLE = "title";
    private FragmentSalaInfoBinding binding;
    Integer id_sala;
    String centro_name;
    EditText ed_centro, ed_lotacao, ed_limpeza;
    TextView txt_nsala;
    ImageView img_qrCode;
    final MethodsInterface methodsInterface = new Methods();
    private boolean shouldRefreshOnResume = false;

    public SalaInfo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentSalaInfoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ed_centro = root.findViewById(R.id.ed_info_centro);
        ed_lotacao = root.findViewById(R.id.ed_info_lotacao);
        ed_limpeza = root.findViewById(R.id.ed_info_limpeza);
        txt_nsala = root.findViewById(R.id.txt_NSala2);
        img_qrCode = root.findViewById(R.id.img_qrCode2);

        id_sala = new SharedPrefManager(root.getContext()).getSalaId();
        centro_name = new SharedPrefManager(root.getContext()).getCentroName();

        getSalaInfo(id_sala, root.getContext());
        ed_centro.setText(centro_name);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstance){
        super.onViewCreated(view, savedInstance);
        if (getArguments() != null) {
            ((TextView)view.findViewById(R.id.textView)).setText(getArguments().getString(TITLE));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // Check should we need to refresh the fragment
        if(shouldRefreshOnResume){
            // refresh fragment
            id_sala = new SharedPrefManager(getContext()).getSalaId();
            centro_name = new SharedPrefManager(getContext()).getCentroName();
            getSalaInfo(id_sala, getContext());
            ed_centro.setText(centro_name);

        }
    }

    @Override
    public void onStop() {
        super.onStop();
        shouldRefreshOnResume = true;
    }

    public void getSalaInfo(int id_sala, Context mCtx)
    {
        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<Sala> call = apiInterface.getSala(id_sala);

        call.enqueue(new Callback<Sala>() {
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(@NonNull Call<Sala> call, @NonNull Response<Sala> response) {
                if (response.body() != null){
                    Log.e("Success",response.body().toString());
                    Sala sala = response.body();
                    int nSala = sala.getnSala();
                    int lotacao = sala.getLotacaoMax();
                    String limpeza = sala.getTempoMinLimp();
                    txt_nsala.setText("Sala " + nSala);
                    ed_lotacao.setText(lotacao);
                    ed_limpeza.setText(methodsInterface.formatTimeForUser(limpeza) + " Min.");
                    methodsInterface.generateQrCode(img_qrCode, id_sala, nSala, lotacao, limpeza, mCtx);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Sala> call, @NonNull Throwable t) {
                Log.e("Failure", t.getLocalizedMessage());
                Toast.makeText(getContext(), "Falha na ligação",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}