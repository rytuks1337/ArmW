package com.rytis.armw.ui.queue_controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.rytis.armw.MainActivity;
import com.rytis.armw.R;
import com.rytis.armw.databinding.FragmentEileBeforeAuthBinding;
import com.rytis.armw.databinding.FragmentEileMainMasterBinding;

public class EileMainMaster extends Fragment {


    FragmentEileMainMasterBinding binding;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private int tournamentId;
    private int groupId;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("tournament", Context.MODE_PRIVATE);
        tournamentId = sharedPreferences.getInt("tournament_id", 0);
        if(tournamentId == 0){
            FragmentEileBeforeAuthBinding binding1 = FragmentEileBeforeAuthBinding.inflate(inflater, container, false);
            Button selectTournament = binding1.btnSelectTournament;
            selectTournament.setOnClickListener(v -> {
                ((MainActivity) getActivity()).getHome();
            });
            return binding1.getRoot();
        }
        binding = FragmentEileMainMasterBinding.inflate(inflater, container, false);
        tabLayout = binding.tabLayoutEile;
        viewPager = binding.viewPagerEile;

        FragmentStateAdapter pagerAdapter = new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                if (position == 1) {
                    return WresQueue.newInstance(tournamentId);
                } else {
                    return Kategorijosf.newInstance(tournamentId);
                }
            }

            @Override
            public int getItemCount() {
                return 2;
            }
        };

        viewPager.setAdapter(pagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            if (position == 1) {
                tab.setText(R.string.eil);
            } else {
                tab.setText(R.string.kategorijos);
            }
        }).attach();

        return binding.getRoot();
    }

}