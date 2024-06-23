package com.rytis.armw.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.rytis.armw.R;
import com.rytis.armw.auth.LoginScreen;
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
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), LoginScreen.class);
                startActivity(i);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), RegisterScreen.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}