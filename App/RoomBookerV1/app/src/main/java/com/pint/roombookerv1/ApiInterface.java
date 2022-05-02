package com.pint.roombookerv1;

import com.pint.roombookerv1.Models.Salas;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    //https://localhost:7063
    @GET("api/salas")
    static Call<Salas> getData() {
        return null;
    }
}
