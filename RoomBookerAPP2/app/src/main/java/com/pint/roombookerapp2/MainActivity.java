package com.pint.roombookerapp2;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.pint.roombookerapp2.Adapters.AdapterPager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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