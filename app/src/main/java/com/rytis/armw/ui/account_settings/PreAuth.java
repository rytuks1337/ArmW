package com.rytis.armw.ui.account_settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.rytis.armw.MainActivity;
import com.rytis.armw.R;
import com.rytis.armw.auth.LoginScreen;
import com.rytis.armw.auth.OnLoginSuccessListener;
import com.rytis.armw.auth.RegisterScreen;
import com.rytis.armw.databinding.FragmentPreAuthBinding;

public class PreAuth extends Fragment {

    private FragmentPreAuthBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentPreAuthBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding = FragmentPreAuthBinding.inflate(getLayoutInflater());

        Button sign_in = view.findViewById(R.id.pre_sign_in_button);
        Button register = view.findViewById(R.id.pre_register_in_button);

        sign_in.setOnClickListener(v -> {
            onLoginSuccessListener.onLoginSuccess();
        });
        register.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), RegisterScreen.class);
            startActivity(i);
        });
    }
    private OnLoginSuccessListener onLoginSuccessListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnLoginSuccessListener) {
            onLoginSuccessListener = (OnLoginSuccessListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnSignInClickListener");
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}