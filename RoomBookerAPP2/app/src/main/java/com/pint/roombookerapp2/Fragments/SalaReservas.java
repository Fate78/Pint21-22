package com.pint.roombookerapp2.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pint.roombookerapp2.API.ApiClient;
import com.pint.roombookerapp2.API.ApiInterface;
import com.pint.roombookerapp2.Adapters.ReservasSalaRecyclerViewAdapter;
import com.pint.roombookerapp2.Methods;
import com.pint.roombookerapp2.MethodsInterface;
import com.pint.roombookerapp2.Models.Reserva;
import com.pint.roombookerapp2.Models.Sala;
import com.pint.roombookerapp2.R;
import com.pint.roombookerapp2.SharedPrefManager;
import com.pint.roombookerapp2.databinding.FragmentSalaReservasBinding;

import java.time.LocalDate;
import java.util.List;
import java.util.ListIterator;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalaReservas extends Fragment {

    private FragmentSalaReservasBinding binding;
    private Handler handler;
    Timer timer;
    private boolean shouldRefreshOnResume = false;
    public static final String TITLE = "title";
    RecyclerView recyclerView;
    Context mCtx;
    String username, nSala;
    final MethodsInterface methodsInterface = new Methods();
    EditText ed_data_inicio, ed_data_fim;
    TextView txt_nsala;
    ImageView img_qrCode;
    Integer id_sala;

    public SalaReservas() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentSalaReservasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final FragmentActivity fragmentActivity = getActivity();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(fragmentActivity);

        id_sala = new SharedPrefManager(root.getContext()).getSalaId();
        recyclerView = root.findViewById(R.id.rv_reservas_sala);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        txt_nsala = root.findViewById(R.id.txt_NSala);
        ed_data_inicio = root.findViewById(R.id.ed_data_inicio);
        ed_data_fim = root.findViewById(R.id.ed_data_fim);
        img_qrCode = root.findViewById(R.id.img_qrCode);

        //Disable Keyboard
        methodsInterface.disableSoftInputFromAppearing(ed_data_inicio);
        methodsInterface.disableSoftInputFromAppearing(ed_data_fim);

        String today = methodsInterface.formatDateForUser(methodsInterface.getDateToday().toString());
        ed_data_inicio.setText(today);
        ed_data_fim.setText(today);

        doAutoGetReservas();

        ed_data_inicio.setOnClickListener(v -> {
            methodsInterface.popDatePicker(v, ed_data_inicio);
        });

        ed_data_fim.setOnClickListener(v-> {
            methodsInterface.popDatePicker(v, ed_data_fim);
        });

        ed_data_inicio.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!ed_data_inicio.getText().toString().isEmpty() && !ed_data_fim.getText().toString().isEmpty())
                {
                    doAutoGetReservas();
                }
            }
        });

        ed_data_fim.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!ed_data_inicio.getText().toString().isEmpty() && !ed_data_fim.getText().toString().isEmpty())
                {
                    doAutoGetReservas();
                }
            }
        });

        getSala(id_sala, root.getContext());

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Check should we need to refresh the fragment
        if(shouldRefreshOnResume){
            // refresh fragment
            id_sala = new SharedPrefManager(getContext()).getSalaId();
            getSala(id_sala, getContext());
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        shouldRefreshOnResume = true;
    }

    public void getReservasBetweenDates(int id_sala, String data_inicio, String data_fim)
    {
        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<Sala> call = apiInterface.getReservasSalaBetweenDates(id_sala, data_inicio, data_fim);

        call.enqueue(new Callback<Sala>() {
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(@NonNull Call<Sala> call, @NonNull Response<Sala> response) {
                if (response.body() != null){
                    Log.e("Success",response.body().toString());
                    List<Reserva> reservasList = response.body().getReservas();
                    ListIterator<Reserva> iterator = reservasList.listIterator();

                    while(iterator.hasNext())
                    {
                        Reserva next_iterator = iterator.next();
                        String string_data_reserva = next_iterator.getDataReserva();
                        boolean ativo = next_iterator.isAtivo();
                        LocalDate data_reserva = methodsInterface.stringToDate(string_data_reserva);
                        LocalDate today = methodsInterface.getDateToday();
                        if (!ativo)
                            iterator.remove();
                    }
                    if (reservasList.isEmpty())
                        Toast.makeText(getContext(), "Não existem reservas para esta data",
                                Toast.LENGTH_LONG).show();
                    else
                        recyclerView.setAdapter(new ReservasSalaRecyclerViewAdapter(mCtx, reservasList) );

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

    public void getSala(int id_sala, Context mCtx)
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

    public void doAutoGetReservas() {
        handler = new Handler();
        timer = new Timer();

        TimerTask getReservasTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            id_sala = new SharedPrefManager(getContext()).getSalaId();
                            String data_inicio = methodsInterface.formatDateForAPI(ed_data_inicio.getText().toString());
                            String data_fim = methodsInterface.formatDateForAPI(ed_data_fim.getText().toString());
                            getReservasBetweenDates(id_sala, data_inicio, data_fim);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        };
        timer.schedule(getReservasTask, 0, 30000);
    }
}