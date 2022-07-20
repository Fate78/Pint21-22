package com.pint.room_booker.salas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputLayout;
import com.pint.room_booker.R;

public class SalasActivity extends AppCompatActivity {

    TextInputLayout error_salas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salas);


    }
}