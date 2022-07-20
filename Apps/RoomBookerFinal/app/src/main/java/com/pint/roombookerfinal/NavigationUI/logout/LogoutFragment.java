package com.pint.roombookerfinal.NavigationUI.logout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pint.roombookerfinal.R;
import com.pint.roombookerfinal.SharedPrefManager;
import com.pint.roombookerfinal.Utilizador.LoginActivity;

public class LogoutFragment extends Fragment {

    private LogoutViewModel mViewModel;
    SharedPrefManager sharedPrefManager;
    Context mCtx;
    public static LogoutFragment newInstance() {
        return new LogoutFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        logout();
        return inflater.inflate(R.layout.fragment_logout, container, false);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void logout(){
        Intent intent;
        intent = new Intent(getActivity(), LoginActivity.class);
        sharedPrefManager = new SharedPrefManager(this.getActivity());
        sharedPrefManager.clearLoginDetails();
        sharedPrefManager.clearCentroDetails();
        startActivity(intent);
    }
}