package com.rytis.armw.ui.account_settings;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.rytis.armw.R;
import com.rytis.armw.databinding.FragmentAccoutAfterAuthBinding;


public class AccoutAfterAuth extends Fragment {

    FragmentAccoutAfterAuthBinding binding;
    private EditText editTextName, editTextEmail, editTextamzius;
    private RadioGroup radioGroup;
    private RadioButton radioVyr, radioMot;
    public AccoutAfterAuth() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAccoutAfterAuthBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }
}