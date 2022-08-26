package com.pint.roombookerfinal.API;

import android.content.Context;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {
    Context context;

    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request newRequest=chain.request().newBuilder()
                .header("Accept","application/json")
                .build();

        return chain.proceed(newRequest);
    }
}
