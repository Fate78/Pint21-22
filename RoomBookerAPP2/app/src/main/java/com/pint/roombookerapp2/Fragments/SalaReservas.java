package com.pint.roombookerapp2.Fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.pint.roombookerapp2.API.ApiClient;
import com.pint.roombookerapp2.API.ApiInterface;
import com.pint.roombookerapp2.Adapters.ReservasSalaRecyclerViewAdapter;
import com.pint.roombookerapp2.Methods;
import com.pint.roombookerapp2.MethodsInterface;
import com.pint.roombookerapp2.Models.Reserva;
import com.pint.roombookerapp2.Models.Sala;
import com.pint.roombookerapp2.R;
import com.pint.roombookerapp2.databinding.FragmentSalaReservasBinding;

import java.time.LocalDate;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalaReservas extends Fragment {

    private FragmentSalaReservasBinding binding;
    public static final String TITLE = "title";
    RecyclerView recyclerView;
    Context mCtx;
    String username;
    final MethodsInterface methodsInterface = new Methods();
    EditText ed_data_inicio, ed_data_fim;
    ImageView img_qrCode;

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

        recyclerView = root.findViewById(R.id.rv_reservas_sala);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        ed_data_inicio = root.findViewById(R.id.ed_data_inicio);
        ed_data_fim = root.findViewById(R.id.ed_data_fim);
        img_qrCode = root.findViewById(R.id.img_qrCode);

        //Disable Keyboard
        methodsInterface.disableSoftInputFromAppearing(ed_data_inicio);
        methodsInterface.disableSoftInputFromAppearing(ed_data_fim);

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
                    String data_inicio,data_fim;
                    data_inicio = methodsInterface.formatDateForAPI(ed_data_inicio.getText().toString());
                    data_fim = methodsInterface.formatDateForAPI(ed_data_fim.getText().toString());
                    getReservasBetweenDates(data_inicio, data_fim);
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
                    String data_inicio,data_fim;
                    data_inicio = methodsInterface.formatDateForAPI(ed_data_inicio.getText().toString());
                    data_fim = methodsInterface.formatDateForAPI(ed_data_fim.getText().toString());
                    getReservasBetweenDates(data_inicio, data_fim);
                }
            }
        });

        generateQrCode("51");

        return root;
    }

    public void getReservasBetweenDates(String data_inicio, String data_fim)
    {


        ApiInterface apiInterface = ApiClient.createService(ApiInterface.class);
        Call<Sala> call = apiInterface.getReservasSalaBetweenDates(51, data_inicio, data_fim);

        call.enqueue(new Callback<Sala>() {
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

    public void generateQrCode(String idSala)
    {
        MultiFormatWriter writer = new MultiFormatWriter();
        try {
            BitMatrix matrix = writer.encode(idSala, BarcodeFormat.QR_CODE, 350, 350);
            BarcodeEncoder encoder = new BarcodeEncoder();
            Bitmap bitmap  = encoder.createBitmap(matrix);
            img_qrCode.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}