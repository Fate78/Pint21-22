package com.pint.roombookerapp2.Salas;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.pint.roombookerapp2.API.ApiClient;
import com.pint.roombookerapp2.API.ApiInterface;
import com.pint.roombookerapp2.Adapters.SpinAdapter;
import com.pint.roombookerapp2.Methods;
import com.pint.roombookerapp2.MethodsInterface;
import com.pint.roombookerapp2.Models.CentroGeo;
import com.pint.roombookerapp2.Models.Sala;
import com.pint.roombookerapp2.R;
import com.pint.roombookerapp2.SharedPrefManager;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditarSalaActivity extends AppCompatActivity {

    private Spinner spinnerCentros, spinnerSalas;
    ArrayList<String> centroName;
    Sala[] salas_array;
    EditText ed_nSala, ed_lotacao, ed_limpeza, ed_centro;
    final MethodsInterface methodsInterface = new Methods();
    Button btn_select, btn_update;
    Context mCtx;
    int id_sala;
    String centro_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_sala);

        spinnerCentros = findViewById(R.id.spn_centro);
        spinnerSalas = findViewById(R.id.spn_sala);

        ed_nSala = findViewById(R.id.ed_edit_nSala);
        ed_lotacao = findViewById(R.id.ed_edit_lotacao);
        ed_limpeza = findViewById(R.id.ed_edit_limpeza);
        ed_centro = findViewById(R.id.ed_edit_centro);
        btn_select = findViewById(R.id.btn_select);

        id_sala = new SharedPrefManager(this).getSalaId();
        centro_name = new SharedPrefManager(this).getCentroName();

        getSala(id_sala);
        ed_centro.setText(centro_name);

        loadSpinnerCentro();

        spinnerCentros.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String centro = spinnerCentros.getItemAtPosition(spinnerCentros.getSelectedItemPosition()).toString();
                loadSpinnerSala(centro);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerSalas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Sala sala = (Sala) spinnerSalas.getItemAtPosition(position);
                btn_select.setOnClickListener(v -> {
                    int selectedCentroId = spinnerCentros.getSelectedItemPosition();
                    String selectedCentro = spinnerCentros.getItemAtPosition(selectedCentroId).toString();
                    new SharedPrefManager(v.getContext()).saveSalaInfo(sala.getIdSala(), selectedCentro);
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void loadSpinnerCentro()
    {
        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<List<CentroGeo>> call = apiInterface.getCentros();

        call.enqueue(new Callback<List<CentroGeo>>() {
            @Override
            public void onResponse(@NonNull Call<List<CentroGeo>> call, @NonNull Response<List<CentroGeo>> response) {
                if (response.body() != null) {
                    Log.e("Success", response.body().toString());
                    List<CentroGeo> centroGeoList = response.body();
                    ListIterator<CentroGeo> iterator = centroGeoList.listIterator();
                    while(iterator.hasNext())
                    {
                        CentroGeo next_iterator = iterator.next();
                        boolean ativo = next_iterator.isAtivo();
                        if (!ativo)
                            iterator.remove();
                    }
                    if (centroGeoList.isEmpty())
                        Toast.makeText(EditarSalaActivity.this, "Não existem centros",
                                Toast.LENGTH_LONG).show();
                    else
                        centroName = new ArrayList<String>();
                        for(CentroGeo centroGeo : centroGeoList)
                            centroName.add(centroGeo.getNomeCentro());
                        spinnerCentros.setAdapter(new ArrayAdapter<String>(EditarSalaActivity.this, android.R.layout.simple_spinner_dropdown_item, centroName));
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<CentroGeo>> call, @NonNull Throwable t) {
                Log.e("Failure", t.getLocalizedMessage());
                Toast.makeText(EditarSalaActivity.this, "Falha na ligação",
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    private void loadSpinnerSala(String centroName)
    {
        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<CentroGeo> call = apiInterface.getCentro(centroName);

        call.enqueue(new Callback<CentroGeo>() {
            @Override
            public void onResponse(@NonNull Call<CentroGeo> call, @NonNull Response<CentroGeo> response) {
                try{
                    if (response.body() != null) {
                        Log.e("Success", response.body().toString());
                        List<Sala> salasList = response.body().getSalas();
                        ListIterator<Sala> iterator = salasList.listIterator();
                        while(iterator.hasNext())
                        {
                            Sala next_iterator = iterator.next();
                            boolean ativo = next_iterator.getAtivo();
                            if (!ativo)
                                iterator.remove();
                        }
                        if (salasList.isEmpty())
                            Toast.makeText(EditarSalaActivity.this, "Não existem centros",
                                    Toast.LENGTH_LONG).show();
                        else
                            salas_array = new Sala[salasList.size()];
                            for(int i=0; i<salasList.size(); i++) {
                                salas_array[i] = new Sala();
                                Integer id_sala = salasList.get(i).getIdSala();
                                Integer n_sala = salasList.get(i).getnSala();
                                Integer lotacao = salasList.get(i).getLotacaoMax();
                                String limpeza = salasList.get(i).getTempoMinLimp();
                                salas_array[i].setIdSala(id_sala);
                                salas_array[i].setnSala(n_sala);
                                salas_array[i].setLotacaoMax(lotacao);
                                salas_array[i].setTempoMinLimp(limpeza);
                            }
                            spinnerSalas.setAdapter(new SpinAdapter(EditarSalaActivity.this, android.R.layout.simple_spinner_item, salas_array));
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<CentroGeo> call, @NonNull Throwable t) {
                Log.e("Failure", t.getLocalizedMessage());
                Toast.makeText(EditarSalaActivity.this, "Falha na ligação",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getSala(int id_sala)
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
                    ed_nSala.setText(sala.getnSala().toString());
                    ed_lotacao.setText(sala.getLotacaoMax().toString());
                    ed_limpeza.setText(methodsInterface.formatTimeForUser(sala.getTempoMinLimp()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<Sala> call, @NonNull Throwable t) {
                Log.e("Failure", t.getLocalizedMessage());
                Toast.makeText(EditarSalaActivity.this, "Falha na ligação",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}