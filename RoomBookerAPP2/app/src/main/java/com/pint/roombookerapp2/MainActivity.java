package com.pint.roombookerapp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.pint.roombookerapp2.Adapters.AdapterPager;
import com.pint.roombookerapp2.Salas.EditarSalaActivity;
import com.pint.roombookerapp2.Utilizador.LoginActivity;

public class MainActivity extends AppCompatActivity {
    Button btn_login, btn_logout, btn_sala;
    SharedPrefManager sharedPrefManager;
    Boolean isLoggedout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        btn_login = findViewById(R.id.btn_login2);
        btn_logout = findViewById(R.id.btn_logout);
        btn_sala = findViewById(R.id.btn_sala);
        isLoggedout = new SharedPrefManager(this).isUserLoggedOut();
        sharedPrefManager = new SharedPrefManager(this);

        if(!isLoggedout) {
            btn_login.setVisibility(View.GONE);
            btn_logout.setVisibility(View.VISIBLE);
            btn_sala.setVisibility(View.VISIBLE);
        }
        else{
            btn_login.setVisibility(View.VISIBLE);
            btn_logout.setVisibility(View.GONE);
            btn_sala.setVisibility(View.GONE);
        }

        btn_login.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        });

        btn_logout.setOnClickListener(v -> {
            sharedPrefManager.clearLoginDetails();
            btn_login.setVisibility(View.VISIBLE);
            btn_sala.setVisibility(View.GONE);
            btn_logout.setVisibility(View.GONE);
            Toast.makeText(this, "Successfully logged out!", Toast.LENGTH_SHORT).show();
        });

        btn_sala.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), EditarSalaActivity.class);
            startActivity(intent);
        });

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager2 viewPager2 = findViewById(R.id.pager);

        AdapterPager adapterPager = new AdapterPager(getSupportFragmentManager(), getLifecycle());
        viewPager2.setAdapter(adapterPager);

        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position){
                    case 0:
                        tab.setText("Reservas");
                        break;
                    case 1:
                        tab.setText("Detalhes");
                        break;
                }

            }
        }).attach();
    }

    @Override
    public void onBackPressed(){
        Toast.makeText(MainActivity.this, "There is no back action", Toast.LENGTH_LONG).show();
    }

}