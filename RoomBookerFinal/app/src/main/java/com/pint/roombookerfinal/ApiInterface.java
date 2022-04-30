package com.pint.roombookerfinal;

import com.pint.roombookerfinal.Models.Salas;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    //https://localhost:7063/
    @GET("/api/salas")
    public Call<List<Salas>> getSalas();

    @GET("/api/salas/{n_sala}")
    public Call<Salas> getSala(@Path("n_sala") int n_sala);

}
