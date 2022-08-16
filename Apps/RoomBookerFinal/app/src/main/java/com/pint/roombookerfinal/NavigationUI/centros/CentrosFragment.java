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
import com.pint.roombookerfinal.Models.CentroGeo;
import com.pint.roombookerfinal.R;
import com.pint.roombookerfinal.SharedPrefManager;
import com.pint.roombookerfinal.databinding.FragmentCentrosBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CentrosFragment extends Fragment {

    private FragmentCentrosBinding binding;
    RadioButton radioButton, radioButtonCheck;
    RadioGroup radioGroup;
    Integer selectedCentroId;
    String selectedCentroName;

    public static CentrosFragment newInstance() {
        return new CentrosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentCentrosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        radioGroup = root.findViewById(R.id.centros_radio_group);

        String AuthToken = new SharedPrefManager(root.getContext()).getAuthToken();

        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<List<CentroGeo>> call = apiInterface.getCentros();
        call.enqueue(new Callback<List<CentroGeo>>() {
            @Override
            public void onResponse(@NonNull Call<List<CentroGeo>> call,
                                   @NonNull Response<List<CentroGeo>> response)
            {
                if (response.body() != null) {
                    Log.e("Success",response.body().toString());
                    List<CentroGeo> centroGeoList = response.body();
                    if (centroGeoList != null) {
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
            }

            @Override
            public void onFailure(@NonNull Call<List<CentroGeo>> call, @NonNull Throwable t) {
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