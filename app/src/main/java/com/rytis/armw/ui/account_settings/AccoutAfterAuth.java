package com.rytis.armw.ui.account_settings;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.rytis.armw.MainActivity;
import com.rytis.armw.auth.TokenManager;
import com.rytis.armw.databinding.FragmentAccoutAfterAuthBinding;


public class AccoutAfterAuth extends Fragment {

    FragmentAccoutAfterAuthBinding binding;
    private EditText editTextName, editTextEmail, editTextamzius;
    private RadioGroup radioGroup;
    private RadioButton radioVyr, radioMot;
    private Button btnSave, btnCancel;
    public AccoutAfterAuth() {} //Empty constructor

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
        editTextName = binding.editTextName;
        editTextEmail = binding.editTextEmail;
        editTextamzius = binding.editTextamzius;
        radioGroup = binding.radioGroup;
        radioVyr = binding.radioVyr;
        radioMot = binding.radioMot;
        btnSave = binding.buttonSave;
        btnCancel = binding.buttonCancel;

        btnCancel.setOnClickListener(View -> {
            TokenManager.clearJwtToken(requireContext());
            ((MainActivity) getActivity()).updateBottom();

        });

        return root;
    }

}