package com.pint.roombookerapp2.Adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.pint.roombookerapp2.Fragments.SalaInfo;
import com.pint.roombookerapp2.Fragments.SalaReservas;

public class AdapterPager extends FragmentStateAdapter {

    private final int num_tabs = 2;

    public AdapterPager(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = new SalaReservas();
        Bundle args = new Bundle();
        args.putString(SalaInfo.TITLE, "Tab" + (position+1));
        switch(position){
            case 0:
                fragment = new SalaReservas();
                break;
            case 1:
                fragment = new SalaInfo();
                break;
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return num_tabs;
    }
}
