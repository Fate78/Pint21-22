package com.pint.roombooker_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_Get, btn_Post, btn_CustomRequest;
    ArrayList<SalasList.SalasDataList> mSalasDataList = new ArrayList<>();
    String BASE_URL = "https://localhost:";
    int numberOfRequestsCompleted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_Get = findViewById(R.id.btn_get);
        btn_Post = findViewById(R.id.btn_post);
        btn_CustomRequest = findViewById(R.id.btn_customRequest);

        btn_Get.setOnClickListener(this);
        btn_Post.setOnClickListener(this);
        btn_CustomRequest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_get:

        }
    }

    Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            if (error instanceof NetworkError){
                Toast.makeText(getApplicationContext(), "No network available", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        }
    };

    private void GETStringAndJSONRequest(String page_1, String page_2){
        mSalasDataList.clear();
        numberOfRequestsCompleted = 0;
        VolleyLog.DEBUG = true;
        RequestQueue queue = SingletonRequestQueue.getInstance(getApplicationContext()).getRequestQueue();
        String uri_page_one = String.format(BASE_URL + "/api/salas?page=%1$s",page_1);
        final String uri_page_two = String.format(BASE_URL + "/api/salas?page=%1$s", page_2);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, uri_page_one, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                VolleyLog.wtf(response, "utf-8");
                GsonBuilder builder = new GsonBuilder();
                Gson mGson = builder.create();
                SalasList salasList = mGson.fromJson(response, SalasList.class);
                mSalasDataList.addAll(salasList.salasDataList);
                ++numberOfRequestsCompleted;
            }
        }, errorListener){
            @Override
            public Priority getPriority() {
                return Priority.LOW;
            }
        };

        queue.add(stringRequest);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(uri_page_two, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                VolleyLog.wtf(response.toString(), "utf-8");
                GsonBuilder builder = new GsonBuilder();
                Gson mGson = builder.create();

                SalasList salasList = mGson.fromJson(response.toString(),SalasList.class);
                mSalasDataList.addAll(salasList.salasDataList);
                ++numberOfRequestsCompleted;
            }
        }, errorListener){

            @Override
            public String getBodyContentType(){
                return "application/json";
            }

            @Override
            public Priority getPriority(){
                return Priority.IMMEDIATE;
            }
        };

        queue.add(jsonObjectRequest);

        queue.addRequestEventListener(new RequestQueue.RequestEventListener() {
            @Override
            public void onRequestEvent(Request<?> request, int event) {
                try {
                    if (request.getCacheEntry() != null){
                        String cacheValue = new String(request.getCacheEntry().data, "UTF-8");
                        VolleyLog.d(request.getCacheKey() + " " + cacheValue);
                    }
                }catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                if (numberOfRequestsCompleted == 2){
                    numberOfRequestsCompleted = 0;
                    startActivity(new Intent(MainActivity.this, RecyclerViewActivity.class).putExtra("users", mSalasDataList));
                }
            }
        });
    }
}