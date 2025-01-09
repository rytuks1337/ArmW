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
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.rytis.armw.MainActivity;
import com.rytis.armw.R;
import com.rytis.armw.Retrofit_Pre;
import com.rytis.armw.databinding.FragmentEileBeforeAuthBinding;
import com.rytis.armw.databinding.FragmentEileMainMasterBinding;
import com.rytis.armw.models.RoleGet;
import com.rytis.armw.models.VarzybosGet;
import com.rytis.armw.routes.TournamentRoute;
import com.rytis.armw.ui.tournaments.register_tournament.RegisterTournament;
import com.rytis.armw.ui.queue_controller.ControlTournament;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EileMainMaster extends Fragment {


    FragmentEileMainMasterBinding binding;
    String currentState;
    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private int tournamentId;
    private int groupId;
    private String currentRole;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("tournament", Context.MODE_PRIVATE);
        tournamentId = sharedPreferences.getInt("tournament_id", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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
        FragmentStateAdapter pageAdapter = getPagerAdapter();
        viewPager.setAdapter(pageAdapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {

            switch (currentState) {
                case "INIT":
                    tab.setText(position == 1 ? R.string.control_tournament : R.string.kategorijos);
                    break;
                case "IN_PROGRESS":
                    tab.setText(position == 1 ? R.string.eil : R.string.kategorijos);
                    break;
                case "FINISHED":
                    tab.setText(R.string.kategorijos);
                    break;
                default:
                    tab.setText("Unknown");
            }
        }).attach();



        return binding.getRoot();
    }


    private FragmentStateAdapter getPagerAdapter(){
        return new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {


                if (position == 1) {
                    return ControlTournament.newInstance(tournamentId);
                } else {
                    return Kategorijosf.newInstance(tournamentId);
                }

            }

            @Override
            public int getItemCount() {
                // Dynamically determine the number of tabs based on the state
                if (currentState == null) {
                    throw new IllegalStateException("Current state is not set");
                }

                switch (currentState) {
                    case "INIT":
                        return 2;
                    case "SETUP":
                        return 2;
                    case "IN_PROGRESS":
                        return 2;
                    case "FINISHED":
                        return 1;
                    default:
                        return 0;
                }
            }
        };
    }

}