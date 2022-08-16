package com.pint.roombookerfinal.API;

import android.content.Context;

import androidx.annotation.NonNull;

import com.pint.roombookerfinal.SharedPrefManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {
    Context context;

    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        String token = new SharedPrefManager(context).getAuthToken();

        Request newRequest=chain.request().newBuilder()
                .header("Authorization","Bearer "+ token)
                .build();

        return chain.proceed(newRequest);
    }
}
