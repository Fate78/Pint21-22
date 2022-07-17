package com.pint.roombookerfinal;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    final Context mCtx;
    final String loginPreferences = "LoginDetails";
    final String centroPreferences = "CentroId";
    public SharedPrefManager(Context mCtx)
    {
        this.mCtx = mCtx;
    }

    public void saveLoginDetails(Integer userId, String username, String email, String password){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(loginPreferences, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("UserId", userId);
        editor.putString("Username", username);
        editor.putString("Password", password);
        editor.putString("Email", email);
        editor.apply();
    }

    public void clearLoginDetails(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(loginPreferences, Context.MODE_PRIVATE);
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
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(loginPreferences, Context.MODE_PRIVATE);
        boolean isUsernameEmpty = sharedPreferences.getString("Username","").isEmpty();
        boolean isPasswordEmpty = sharedPreferences.getString("Password","").isEmpty();
        return isUsernameEmpty || isPasswordEmpty;
    }
}
