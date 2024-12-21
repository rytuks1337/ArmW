package com.rytis.armw.ui.queue_controller;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.rytis.armw.MainActivity;
import com.rytis.armw.databinding.FragmentEileBeforeAuthBinding;


public class EileBeforeSelect extends Fragment {

    FragmentEileBeforeAuthBinding binding;
    private Button selectTournament;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEileBeforeAuthBinding.inflate(inflater, container, false);
        selectTournament = binding.btnSelectTournament;
        selectTournament.setOnClickListener(v -> {
            ((MainActivity) getActivity()).getHome();
        });

        return binding.getRoot();
    }

}