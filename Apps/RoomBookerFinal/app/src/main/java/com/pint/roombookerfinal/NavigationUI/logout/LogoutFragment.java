package com.pint.roombookerfinal.NavigationUI.logout;

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
import com.pint.roombookerfinal.databinding.FragmentLogoutBinding;

public class LogoutFragment extends Fragment {

    private LogoutViewModel mViewModel;
    SharedPrefManager sharedPrefManager;
    final MethodsInterface methodsInterface = new Methods();
    private FragmentLogoutBinding binding;
    public static LogoutFragment newInstance() {
        return new LogoutFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentLogoutBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        methodsInterface.logout(root.getContext());
        return inflater.inflate(R.layout.fragment_logout, container, false);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}