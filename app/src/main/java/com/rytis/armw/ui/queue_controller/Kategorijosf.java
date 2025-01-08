package com.rytis.armw.ui.queue_controller;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rytis.armw.Retrofit_Pre;
import com.rytis.armw.models.Grupe;
import com.rytis.armw.R;
import com.rytis.armw.models.GrupeList;
import com.rytis.armw.routes.TournamentRoute;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class Kategorijosf extends Fragment {

    private static final String TOURNAMENT_ID = "tournament_id";
    private RecyclerView recyclerView;
    private GroupBasicListAdaptor groupAdapter;
    private List<GrupeList.GrupeBasic> pogrupisList = new ArrayList<>();
    private int tournamentId;

    public static Kategorijosf newInstance(int tournamentId) {
        Kategorijosf fragment = new Kategorijosf();
        Bundle args = new Bundle();
        args.putInt(TOURNAMENT_ID, tournamentId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tournamentId = getArguments().getInt(TOURNAMENT_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_groupf, container, false);
        recyclerView = view.findViewById(R.id.recycler_groups);
        groupAdapter = new GroupBasicListAdaptor(getContext(), pogrupisList);
        recyclerView.setAdapter(groupAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getGroups();

        return view;
    }

    public void addBasicGroup(GrupeList.GrupeBasic grupe) {
        pogrupisList.add(grupe);
        groupAdapter.notifyDataSetChanged();
    }

    public void getGroups() {
        Retrofit_Pre retrofit_pre = new Retrofit_Pre(getContext());
        Retrofit retrofit = retrofit_pre.getRetrofit(true);

        TournamentRoute tournamentRoute = retrofit.create(TournamentRoute.class);
        Call<GrupeList> call = tournamentRoute.getGroups(tournamentId);
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<GrupeList> call, Response<GrupeList> response) {
                if (response.isSuccessful()) {
                    GrupeList grupes = response.body();
                    if (grupes != null) {
                        List<GrupeList.GrupeBasic> grupesBasicList = grupes.getGrupesBasic();
                        for (GrupeList.GrupeBasic grupebasic : grupesBasicList) {
                            addBasicGroup(grupebasic);
                        }
                    }
                } else {
                    System.out.println("Response" + response.message());
                }
            }

            @Override
            public void onFailure(Call<GrupeList> call, Throwable throwable) {
                Toast.makeText(null, "Error: " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}