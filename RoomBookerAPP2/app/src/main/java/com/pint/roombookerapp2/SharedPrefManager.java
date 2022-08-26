package com.pint.roombookerapp2;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    final Context mCtx;
    final String loginPreferences = "LoginDetails";
    final String centroPreferences = "CentroId";
    final String salaPreferences = "SalaDetails";
    final String authTokenPreferences = "Token";

    public SharedPrefManager(Context mCtx)
    {
        this.mCtx = mCtx;
    }

    public void saveLoginDetails(Integer userId, String username, String email){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(loginPreferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("UserId", userId);
        editor.putString("Username", username);
        editor.putString("Email", email);
        editor.apply();
    }

    public void clearLoginDetails(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(loginPreferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public String getAuthToken(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(authTokenPreferences, Context.MODE_PRIVATE);
        return sharedPreferences.getString("Token", "");
    }

    public void saveAuthToken(String token){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(authTokenPreferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Token", token);
        editor.apply();
    }

    public void clearAuthToken(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(authTokenPreferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public void clearCentroDetails(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(centroPreferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public void saveCentro(Integer centroId, String centroNome){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(centroPreferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("CentroId", centroId);
        editor.putString("CentroNome", centroNome);
        editor.apply();
    }

    public Integer getCentroId(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(centroPreferences, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("CentroId", 0);
    }

    public String getCentroNome(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(centroPreferences, Context.MODE_PRIVATE);
        return sharedPreferences.getString("CentroNome", "");
    }

    public Integer getUserId(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(loginPreferences, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("UserId", 0);
    }

    public String getUsername(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(loginPreferences, Context.MODE_PRIVATE);
        return sharedPreferences.getString("Username","");
    }

    public String getEmail(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(loginPreferences, Context.MODE_PRIVATE);
        return sharedPreferences.getString("Email","");
    }

    public boolean isUserLoggedOut(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(authTokenPreferences, Context.MODE_PRIVATE);
        return sharedPreferences.getString("Token","").isEmpty();
    }

    public void saveSalaInfo(int id_sala, String name_centro){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(salaPreferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("SalaId", id_sala);
        editor.putString("CentroName", name_centro);
        editor.apply();
    }

    public Integer getSalaId(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(salaPreferences, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("SalaId", 0);
    }

    public String getCentroName(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(salaPreferences, Context.MODE_PRIVATE);
        return sharedPreferences.getString("CentroName", "");
    }
}
