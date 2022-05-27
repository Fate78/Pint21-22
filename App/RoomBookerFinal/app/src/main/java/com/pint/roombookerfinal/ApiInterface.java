package com.pint.roombookerfinal;

import com.pint.roombookerfinal.Models.CentroGeo;
import com.pint.roombookerfinal.Models.Reserva;
import com.pint.roombookerfinal.Models.Sala;
import com.pint.roombookerfinal.Models.Utilizador;

import java.util.Date;
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
    public Call<List<CentroGeo>> getCentros();

    @GET("/api/centros/{centroName}")
    public Call<CentroGeo> getCentro(@Path("centroName") String centroName);

    @GET("/api/centros/{id}")
    public Call<CentroGeo> getCentrobyId(@Path("id") Integer centroId);

    //Salas
    @Headers("Accept: application/json")
    @GET("/api/salas")
    public Call<List<Sala>> getSalas();

    @GET("/api/salas/{id}")
    public Call<Sala> getSala(@Path("id") int id);

    @GET("/api/salas/{id}/reservas")
    public Call<Sala> getSalaReservas(@Path("id") int id);

    //Utilizador
    @GET("/api/utilizadores")
    public Call<List<Utilizador>> getUtilizadores();

    @GET("api/utilizadores/{username}")
    public Call<Utilizador> getUtilizador(@Path("username") String username);

    //Reservas
    @GET("api/reservas/{date}")
    public Call<Reserva> getReservasbyDate(@Path("date") Date date);

    @POST("api/reservas")
    public Call<Reserva> createReserva(@Body Reserva reserva);

}
