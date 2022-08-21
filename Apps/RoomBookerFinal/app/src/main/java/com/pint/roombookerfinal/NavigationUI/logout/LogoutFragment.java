package com.pint.roombookerfinal.NavigationUI.logout;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.pint.roombookerfinal.Methods;
import com.pint.roombookerfinal.MethodsInterface;
import com.pint.roombookerfinal.R;
import com.pint.roombookerfinal.SharedPrefManager;

public class LogoutFragment extends Fragment {

    private LogoutViewModel mViewModel;
    SharedPrefManager sharedPrefManager;
    Context mCtx;
    final MethodsInterface methodsInterface = new Methods();
    public static LogoutFragment newInstance() {
        return new LogoutFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        methodsInterface.logout(mCtx);
        return inflater.inflate(R.layout.fragment_logout, container, false);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}