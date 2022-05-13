package com.pint.roombookerfinal;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    Context mCtx;

    public SharedPrefManager(Context mCtx)
    {
        this.mCtx = mCtx;
    }

    public void saveLoginDetails(String username, String email, String password){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Username", username);
        editor.putString("Password", password);
        editor.putString("Email", email);
        editor.apply();
    }

    public String getUsername(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Username","");
    }

    public String getEmail(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        return sharedPreferences.getString("Email","");
    }

    public boolean isUserLoggedOut(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences("LoginDetails", Context.MODE_PRIVATE);
        boolean isUsernameEmpty = sharedPreferences.getString("Username","").isEmpty();
        boolean isPasswordEmpty = sharedPreferences.getString("Password","").isEmpty();
        return isUsernameEmpty || isPasswordEmpty;
    }
}
