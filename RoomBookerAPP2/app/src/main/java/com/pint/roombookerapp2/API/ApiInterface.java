package com.pint.roombookerapp2.API;

import com.pint.roombookerapp2.Models.AuthToken;
import com.pint.roombookerapp2.Models.Authenticate;
import com.pint.roombookerapp2.Models.CentroGeo;
import com.pint.roombookerapp2.Models.Reserva;
import com.pint.roombookerapp2.Models.Sala;
import com.pint.roombookerapp2.Models.Utilizador;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

    @GET("/api/salas/{id}/reservas/{dateBegin}/{dateEnd}")
    Call<Sala> getReservasSalaBetweenDates(@Path("id") int id,
                                           @Path("dateBegin") String dateBegin,
                                           @Path("dateEnd") String dateEnd);

    @PUT("api/salas/{id}")
    Call<Sala> updateSala(@Path("id") int id, @Body Sala sala, @Header("Authorization") String authToken);

    //Utilizador
    @POST("/api/utilizadores/authenticate")
    Call<AuthToken> authenticate(@Body Authenticate authenticate);

    @GET("/api/utilizadores")
    Call<List<Utilizador>> getUtilizadores(@Header("Authorization") String authToken);

    @GET("api/utilizadores/{username}")
    Call<Utilizador> getUtilizador(@Path("username") String username, @Header("Authorization") String authToken);

    @GET("api/utilizadores/{username}/reservas")
    Call<Utilizador> getUtilizadorReservas(@Path("username") String username, @Header("Authorization") String authToken);

    //Reservas
    @GET("api/reservas/{date}")
    Call<List<Reserva>> getReservasbyDate(@Path("date") String date);

    @POST("api/reservas")
    Call<Reserva> createReserva(@Body Reserva reserva, @Header("Authorization") String authToken);

    @PUT("api/reservas/{id}")
    Call<Reserva> updateReserva(@Path("id") int id, @Body Reserva reserva, @Header("Authorization") String authToken);

}
