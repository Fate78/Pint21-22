package com.pint.roombookerfinal.NavigationUI.centros;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pint.roombookerfinal.API.ApiClient;
import com.pint.roombookerfinal.API.ApiInterface;
import com.pint.roombookerfinal.Methods;
import com.pint.roombookerfinal.MethodsInterface;
import com.pint.roombookerfinal.Models.CentroGeo;
import com.pint.roombookerfinal.Models.Utilizador;
import com.pint.roombookerfinal.R;
import com.pint.roombookerfinal.SharedPrefManager;
import com.pint.roombookerfinal.databinding.FragmentCentrosBinding;

import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CentrosFragment extends Fragment {

    private FragmentCentrosBinding binding;
    RadioButton radioButton, radioButtonCheck;
    RadioGroup radioGroup;
    Integer selectedCentroId;
    String selectedCentroName;
    String TokenType = "Bearer ";
    final MethodsInterface methodsInterface = new Methods();

    public static CentrosFragment newInstance() {
        return new CentrosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentCentrosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        radioGroup = root.findViewById(R.id.centros_radio_group);

        String username = new SharedPrefManager(root.getContext()).getUsername();
        String AuthToken = new SharedPrefManager(root.getContext()).getAuthToken();

        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<Utilizador> call = apiInterface.getUtilizador(username, TokenType + AuthToken);
        call.enqueue(new Callback<Utilizador>() {
            @Override
            public void onResponse(@NonNull Call<Utilizador> call,
                                   @NonNull Response<Utilizador> response)
            {
                if(response.code()==401)
                {
                    methodsInterface.logout(root.getContext());
                }

                if (response.body() != null) {
                    Log.e("Success",response.body().toString());
                    List<CentroGeo> centroGeoList = response.body().getUtilizadorCentro();
                    ListIterator<CentroGeo> iterator = centroGeoList.listIterator();
                    while(iterator.hasNext())
                    {
                        CentroGeo next_iterator = iterator.next();
                        boolean ativo = next_iterator.isAtivo();
                        if (!ativo)
                            iterator.remove();
                    }

                    for (CentroGeo centroGeo :centroGeoList){
                        RadioButton button = new RadioButton(getActivity());
                        button.setId(centroGeo.getIdCentro());
                        button.setText(centroGeo.getNomeCentro());
                        radioGroup.addView(button);
                        selectedCentroId = new SharedPrefManager(getActivity()).getCentroId();
                        if(centroGeo.getIdCentro() == selectedCentroId)
                            radioGroup.check(selectedCentroId);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Utilizador> call, @NonNull Throwable t) {
                Log.e("Failure", t.getLocalizedMessage());
            }
        });

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            radioButton = requireActivity().findViewById(checkedId);
            selectedCentroName = radioButton.getText().toString();
            saveCentro(checkedId, selectedCentroName);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void saveCentro(Integer centroId, String centroNome){
        new SharedPrefManager(getActivity()).saveCentro(centroId, centroNome);
    }
}