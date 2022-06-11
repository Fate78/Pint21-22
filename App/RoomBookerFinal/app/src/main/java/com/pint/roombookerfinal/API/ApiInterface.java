package com.pint.roombookerfinal.API;

import com.pint.roombookerfinal.Models.CentroGeo;
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

    //Centros
    @GET("/api/centros")
    Call<List<CentroGeo>> getCentros();

    @GET("/api/centros/{centroName}")
    Call<CentroGeo> getCentro(@Path("centroName") String centroName);

    @GET("/api/centros/{id}")
    Call<CentroGeo> getCentrobyId(@Path("id") Integer centroId);

    //Salas
    @Headers("Accept: application/json")
    @GET("/api/salas")
    Call<List<Sala>> getSalas();

    @GET("/api/salas/{id}")
    Call<Sala> getSala(@Path("id") int id);

    @GET("/api/salas/{id}/reservas")
    Call<Sala> getSalaReservas(@Path("id") int id);

    //Utilizador
    @GET("/api/utilizadores")
    Call<List<Utilizador>> getUtilizadores();

    @GET("api/utilizadores/{username}")
    Call<Utilizador> getUtilizador(@Path("username") String username);

    @GET("api/utilizadores/{username}/reservas")
    Call<Utilizador> getUtilizadorReservas(@Path("username") String username);

    //Reservas
    @GET("api/reservas/{date}")
    Call<List<Reserva>> getReservasbyDate(@Path("date") String date);

    @POST("api/reservas")
    Call<Reserva> createReserva(@Body Reserva reserva);

}
