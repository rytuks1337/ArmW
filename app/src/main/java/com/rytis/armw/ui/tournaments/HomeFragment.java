package com.rytis.armw.ui.tournaments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rytis.armw.R;
import com.rytis.armw.Retrofit_Pre;
import com.rytis.armw.auth.TokenManager;
import com.rytis.armw.dataModels.TournamentModel;
import com.rytis.armw.databinding.FragmentHomeBinding;
import com.rytis.armw.routes.TournamentRoute;
import com.rytis.armw.ui.tournaments.register_tournament.RegisterTournament;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment implements androidx.appcompat.widget.SearchView.OnQueryTextListener {

    private FragmentHomeBinding binding;


    private final ArrayList<TournamentModel.TournamentRespGetData.TournamentData> tournamentArrayList = new ArrayList<>();
    private RecyclerView recyclerView;
    private LinearLayout searchOptions;
    private CheckBox checkPersonalTournamentCheckBox;
    private CheckBox checkHigherPermissionsCheckBox;
    private androidx.appcompat.widget.SearchView searchView;
    private int page;
    private boolean inMine;
    private boolean hasPrivilages;
    private String searchText;
    private static final int TEXT_INPUT_DELAY = 1500;
    private Handler handler;
    private Runnable searchRunnable;
    private HomeAdapter homeAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.searchView.setQueryHint("Ieškoti varžybas");
        handler = new Handler();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(searchRunnable != null) {
            handler.removeCallbacks(searchRunnable);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        page=1;

        checkperms();
        recyclerView = view.findViewById(R.id.list_recycle);
        searchOptions = view.findViewById(R.id.filterSearch);
        checkHigherPermissionsCheckBox = view.findViewById(R.id.checkHigherPermissions);
        checkPersonalTournamentCheckBox = view.findViewById(R.id.checkPersonalTournament);
        searchView = view.findViewById(R.id.searchView);


        checkHigherPermissionsCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> dataInit());
        checkPersonalTournamentCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> dataInit());

        searchView.setOnQueryTextListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        homeAdapter = new HomeAdapter(getContext(), tournamentArrayList);
        recyclerView.setAdapter(homeAdapter);
        recyclerView.setClickable(true);
        dataInit();
        
    }


    private void checkperms() {
        if (TokenManager.getJwtToken(binding.getRoot().getContext()) != null) {

            binding.floatingActionButton.setVisibility(View.VISIBLE);
            binding.searchView.setVisibility(View.VISIBLE);

            binding.floatingActionButton.setOnClickListener(v -> {

                Intent i = new Intent(binding.getRoot().getContext(), RegisterTournament.class);

                startActivity(i);
            });

        }
    }

    private void dataInit() {
        Retrofit_Pre retro_p = new Retrofit_Pre(binding.getRoot().getContext());
        Retrofit retro = retro_p.getRetrofit(true);

        if(checkPersonalTournamentCheckBox.isChecked()){
            inMine=true;
        }else{
            inMine=false;
        }
        if(checkHigherPermissionsCheckBox.isChecked()){
            hasPrivilages=true;
        }else{
            hasPrivilages=false;
        }

        TournamentRoute getTournaments = retro.create(TournamentRoute.class);
        getTournaments.getTournaments(page, this.inMine,  this.hasPrivilages, false, searchText).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<TournamentModel.TournamentRespGetData> call, Response<TournamentModel.TournamentRespGetData> response) {
                if (response.isSuccessful()) {
                    TournamentModel.TournamentRespGetData tournamentRespGetData = response.body();
                    List<TournamentModel.TournamentRespGetData.TournamentData> tournaments = tournamentRespGetData.getResult();
                    System.out.println(response.code());
                    tournamentArrayList.clear();
                    tournamentArrayList.addAll(tournaments);
                    homeAdapter.notifyDataSetChanged();

                } else {
                    System.out.println(response.code());
                    tournamentArrayList.clear();
                    homeAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<TournamentModel.TournamentRespGetData> call, Throwable t) {
                if (t instanceof IOException) {
                    Toast.makeText(homeAdapter.context, "Network error. Please check your connection.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(homeAdapter.context, "Something went wrong. Please try again later.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        debounceSearch(newText);
        return false;
    }
    private void debounceSearch(String query) {
        if (searchRunnable != null) {
            System.out.println("Removed");
            handler.removeCallbacks(searchRunnable);
        }
        searchText = query;
        searchRunnable = () -> dataInit();
        handler.postDelayed(searchRunnable, TEXT_INPUT_DELAY);
    }
}