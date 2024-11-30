package com.rytis.armw.ui.tournaments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rytis.armw.MainActivity;
import com.rytis.armw.R;
import com.rytis.armw.Retrofit_Pre;
import com.rytis.armw.auth.TokenManager;
import com.rytis.armw.dataModels.TournamentModel;
import com.rytis.armw.databinding.FragmentHomeBinding;
import com.rytis.armw.routes.TournamentRoute;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;


    private final ArrayList<TournamentModel.TournamentRespGetData.TournamentData> tournamentArrayList = new ArrayList<>();
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        checkperms();
        recyclerView = view.findViewById(R.id.list_recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        HomeAdapter homeAdapter = new HomeAdapter(getContext(), tournamentArrayList);
        recyclerView.setAdapter(homeAdapter);
        recyclerView.setClickable(true);
        dataInit(homeAdapter);
        
    }

    private void checkperms() {
        if (TokenManager.getJwtToken(binding.getRoot().getContext()) != null) {

            binding.floatingActionButton.setVisibility(View.VISIBLE);
            binding.floatingActionButton.setOnClickListener(v -> {

                Intent i = new Intent(binding.getRoot().getContext(), RegisterTournament.class);

                startActivity(i);
            });
        }
    }

    private void dataInit(HomeAdapter homeAdapter) {
        Retrofit_Pre retro_p = new Retrofit_Pre();
        Retrofit retro = retro_p.getRetrofit();

        TournamentRoute getTournaments = retro.create(TournamentRoute.class);
        getTournaments.getTournaments().enqueue(new Callback<TournamentModel.TournamentRespGetData>() {
            @Override
            public void onResponse(Call<TournamentModel.TournamentRespGetData> call, Response<TournamentModel.TournamentRespGetData> response) {
                if (response.isSuccessful()) {
                    TournamentModel.TournamentRespGetData tournamentRespGetData = response.body();
                    List<TournamentModel.TournamentRespGetData.TournamentData> tournaments = tournamentRespGetData.getResult();
                    tournamentArrayList.clear();
                    tournamentArrayList.addAll(tournaments);
                    homeAdapter.notifyDataSetChanged();
                } else {
                    System.out.println("Response code: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<TournamentModel.TournamentRespGetData> call, Throwable t) {
                Log.e("TournamentApi", "Error fetching tournaments", t);
                if (t instanceof IOException) {
                    Toast.makeText(homeAdapter.context, "Network error. Please check your connection.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(homeAdapter.context, "Something went wrong. Please try again later.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}