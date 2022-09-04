package com.pint.roombookerfinal.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.auth0.android.jwt.JWT;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.pint.roombookerfinal.Methods;
import com.pint.roombookerfinal.MethodsInterface;
import com.pint.roombookerfinal.R;
import com.pint.roombookerfinal.ScannerActivity;
import com.pint.roombookerfinal.SharedPrefManager;
import com.pint.roombookerfinal.Utilizador.PerfilActivity;
import com.pint.roombookerfinal.databinding.ActivityNavigationDrawerBinding;

public class NavigationDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private AppBarConfiguration mAppBarConfiguration;
    private SharedPrefManager sharedPrefManager;
    TextView username, email;
    String s_username, s_email;
    ImageView img_profile, img_qr_scanner;
    final MethodsInterface methodsInterface = new Methods();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        com.pint.roombookerfinal.databinding.ActivityNavigationDrawerBinding binding = ActivityNavigationDrawerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarNavigationDrawer.toolbar);
        binding.appBarNavigationDrawer.fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_salas, R.id.nav_reservas, R.id.nav_historico_reservas, R.id.nav_centros, R.id.nav_logout)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation_drawer);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        String token = new SharedPrefManager(getApplicationContext()).getAuthToken();
        JWT jwt = new JWT(token);
        Boolean passwordVerificada = jwt.getClaim("VERIF_PASS").asBoolean();
        Boolean emailVerificado = jwt.getClaim("VERIF_EMAIL").asBoolean();

        if(Boolean.FALSE.equals(passwordVerificada) || Boolean.FALSE.equals(emailVerificado))
        {
            methodsInterface.logoutWithFlag(getApplicationContext());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);

        //Get username and email
        username = findViewById(R.id.txt_nav_username);
        email = findViewById(R.id.txt_nav_email);
        s_username = new SharedPrefManager(this).getUsername();
        s_email = new SharedPrefManager(this).getEmail();
        username.setText(s_username);
        email.setText(s_email);

        //Profile Click
        img_profile = findViewById(R.id.img_profile);
        img_profile.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), PerfilActivity.class);
            v.getContext().startActivity(intent);
        });

        img_qr_scanner = findViewById(R.id.img_qr_scanner);
        img_qr_scanner.setOnClickListener(v -> {
            Intent intent = new Intent(NavigationDrawerActivity.this, ScannerActivity.class);
            startActivity(intent);
        });

        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_navigation_drawer);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed(){
        Toast.makeText(NavigationDrawerActivity.this, "There is no back action", Toast.LENGTH_LONG).show();
    }
}