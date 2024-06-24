package com.rytis.armw.ui.tournaments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rytis.armw.R;
import com.rytis.armw.dataModels.TournamentModel;
import com.rytis.armw.databinding.FragmentHomeBinding;
import com.rytis.armw.routes.TournamentRoute;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private String date;
    private final ArrayList<TournamentModel.TournamentRespGetData> tournamentArrayList = new ArrayList<TournamentModel.TournamentRespGetData>();
    private String[] tournamentDates_m, tournamentDates_d, tournamentNames;
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


        recyclerView = view.findViewById(R.id.list_recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        HomeAdapter homeAdapter = new HomeAdapter(getContext(), tournamentArrayList);
        recyclerView.setAdapter(homeAdapter);
        recyclerView.setClickable(true);
        dataInit(homeAdapter);
        
    }

    private void dataInit(HomeAdapter homeAdapter) {
        Retrofit retro = new Retrofit.Builder().baseUrl("http://10.107.0.155:3000").addConverterFactory(GsonConverterFactory.create()).build();

        TournamentRoute getTournaments = retro.create(TournamentRoute.class);
        getTournaments.getTournaments().enqueue(new Callback<List<TournamentModel.TournamentRespGetData>>() {
            @Override
            public void onResponse(Call<List<TournamentModel.TournamentRespGetData>> call, Response<List<TournamentModel.TournamentRespGetData>> response) {
                if (response.isSuccessful()) {
                    List<TournamentModel.TournamentRespGetData> tournaments = response.body();
                    tournamentArrayList.clear();
                    assert tournaments != null;
                    tournamentArrayList.addAll(tournaments);
                    System.out.println("MeFirst");
                    homeAdapter.notifyDataSetChanged();
                } else {
                    System.out.println("Response code: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<List<TournamentModel.TournamentRespGetData>> call, Throwable t) {
                System.out.println(t);
            }
        });

    }
}