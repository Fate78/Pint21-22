package com.pint.roombookerapp2.Fragments;

import android.annotation.SuppressLint;
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
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

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
    EditText ed_centro, ed_lotacao, ed_limpeza;
    TextView txt_nsala;
    ImageView img_qrCode;
    final MethodsInterface methodsInterface = new Methods();

    public SalaInfo() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentSalaInfoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final FragmentActivity fragmentActivity = getActivity();
        FragmentTransaction ft = fragmentActivity.getSupportFragmentManager().beginTransaction();
        if (Build.VERSION.SDK_INT >= 26) {
            ft.setReorderingAllowed(false);
        }
        ft.detach(this).attach(this).commit();


        ed_centro = root.findViewById(R.id.ed_info_centro);
        ed_lotacao = root.findViewById(R.id.ed_info_lotacao);
        ed_limpeza = root.findViewById(R.id.ed_info_limpeza);
        txt_nsala = root.findViewById(R.id.txt_NSala2);
        img_qrCode = root.findViewById(R.id.img_qrCode2);

        id_sala = new SharedPrefManager(root.getContext()).getSalaId();

        getSalaInfo(id_sala);
        methodsInterface.generateQrCode(id_sala, img_qrCode);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstance){
        super.onViewCreated(view, savedInstance);
        if (getArguments() != null) {
            ((TextView)view.findViewById(R.id.textView)).setText(getArguments().getString(TITLE));
        }
    }

    public void getSalaInfo(int id_sala)
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

                    txt_nsala.setText("Sala " + sala.getnSala().toString());
                    ed_lotacao.setText(sala.getLotacaoMax().toString());
                    ed_limpeza.setText(methodsInterface.formatTimeForUser(sala.getTempoMinLimp()) + " Min.");
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