package com.rytis.armw.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.rytis.armw.R;
import com.rytis.armw.dataModels.UserloginModel;
import com.rytis.armw.routes.AuthenticationRoute;
import com.rytis.armw.databinding.FragmentHomeBinding;
import com.rytis.armw.models.Tournament;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private String date;
    private ArrayList<Tournament> tournamentArrayList;
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

        dataInit();
        recyclerView = view.findViewById(R.id.list_recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setClickable(true);
        HomeAdapter homeAdapter = new HomeAdapter(getContext(), tournamentArrayList);
        recyclerView.setAdapter(homeAdapter);
//        view.findViewById(R.id.addTournament).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                System.out.println("yup");
//            }
//        });
        
    }

    private void dataInit() {
        tournamentArrayList = new ArrayList<>();

        tournamentNames = new String[]{
                "Klaipeda",
                "Kaunas",
                "Klaipeda",
                "Kaunas",
                "Klaipeda",
                "Kaunas",
                "Klaipeda",
                "Kaunas",
                "Klaipeda",
                "Kaunas"
        };
        tournamentDates_m = new String[]{
                "June",
                "May",
                "June",
                "May",
                "June",
                "May",
                "June",
                "May",
                "June",
                "May",
        };
        tournamentDates_d = new String[]{
                "05",
                "27",
                "05",
                "27",
                "05",
                "27",
                "05",
                "27",
                "05",
                "27",
        };

        for(int i=0;i<tournamentNames.length;i++){
            Tournament tournament = new Tournament(tournamentDates_m[i], tournamentDates_d[i], tournamentNames[i]);
            tournamentArrayList.add(tournament);
        }

    }
}