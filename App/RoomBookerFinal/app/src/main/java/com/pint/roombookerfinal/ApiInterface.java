package com.pint.roombookerfinal;

import com.pint.roombookerfinal.Models.Reserva;
import com.pint.roombookerfinal.Models.Sala;
import com.pint.roombookerfinal.Models.Utilizador;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {
    //Salas
    @Headers("Accept: application/json")
    @GET("/api/salas")
    public Call<List<Sala>> getSalas();

    @GET("/api/salas/{n_sala}")
    public Call<Sala> getSala(@Path("n_sala") int n_sala);

    //Utilizador
    @GET("/api/utilizadores")
    public Call<List<Utilizador>> getUtilizadores();

    @GET("api/utilizadores/{username}")
    public Call<Utilizador> getUtilizador(@Path("username") String username);

    //Reservas
    @POST("api/reservas")
    public Call<Reserva> createReserva(@Body Reserva reserva);

}
