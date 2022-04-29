package com.pint.roombookerfinal;

import com.pint.roombookerfinal.Models.Salas;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    //https://localhost:7063/
    @GET("/api/salas")
    Call<Salas> getData();
}
