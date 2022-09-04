package com.pint.roombookerfinal.API;

import com.pint.roombookerfinal.Models.AuthToken;
import com.pint.roombookerfinal.Models.Authenticate;
import com.pint.roombookerfinal.Models.CentroGeo;
import com.pint.roombookerfinal.Models.Reserva;
import com.pint.roombookerfinal.Models.Sala;
import com.pint.roombookerfinal.Models.Utilizador;

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

    //Utilizador
    @POST("/api/utilizadores/authenticate")
    Call<AuthToken> authenticate(@Body Authenticate authenticate);

    @GET("api/utilizadores/{username}")
    Call<Utilizador> getUtilizador(@Path("username") String username, @Header("Authorization") String authToken);

    @GET("api/utilizadores/{username}/reservas")
    Call<Utilizador> getUtilizadorReservas(@Path("username") String username, @Header("Authorization") String authToken);

    //Edit User
    @PUT("api/utilizadores/{id}")
    Call<Utilizador> updateUtilizador(@Path("id") int id, @Body Utilizador utilizador, @Header("Authorization") String authToken);

    //Send Email
    @POST("api/utilizadores/SendEmail/{user_email}")
    Call<Void> sendEmailVerification(@Path("user_email") String user_email, @Header("Authorization") String authToken);

    //ChangePassword
    @POST("api/utilizadores/changePassword/{token}/{newPassword}")
    Call<Void> changePassword(@Path("token") String token,
                                    @Path("newPassword") String newPassword,
                                    @Header("Authorization") String authToken);

    //VerifyEmail
    @POST("api/utilizadores/VerifyEmailCode/{code}/{email}")
    Call<Void> verifyEmailCode(@Path("code") String code,
                                    @Path("email") String email,
                                    @Header("Authorization") String authToken);

    //Reservas
    @GET("api/reservas/{date}")
    Call<List<Reserva>> getReservasbyDate(@Path("date") String date);

    @POST("api/reservas")
    Call<Reserva> createReserva(@Body Reserva reserva, @Header("Authorization") String authToken);

    @PUT("api/reservas/{id}")
    Call<Reserva> updateReserva(@Path("id") int id, @Body Reserva reserva, @Header("Authorization") String authToken);
}
